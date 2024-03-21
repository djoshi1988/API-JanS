package com.bank.cedrus.controller;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.cedrus.builder.response.ResponseUtils;
import com.bank.cedrus.model.ClaimDetails;
import com.bank.cedrus.model.ClaimUpdateInput;
import com.bank.cedrus.service.impl.ClaimDetailsService;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/api/bank/v3")
public class ClaimController {

	private final ClaimDetailsService claimDetailsService;
	private final ObjectMapper objectMapper;
 
	@Autowired
	public ClaimController(ClaimDetailsService claimDetailsService, ObjectMapper objectMapper) {
		this.claimDetailsService = claimDetailsService;
		this.objectMapper = objectMapper;
		this.objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
 	}

 	@PostMapping("/pushClaimDetails")
	public ResponseEntity<Map<String, Object>> processClaim(@Valid @RequestBody ClaimDetails claimForm,
			BindingResult bindingResult, @RequestHeader("user-name") String userName,
			@RequestHeader("api-key") String apiKey) {
		
 
		try {
			
			log.info("Request "+ claimForm);
			
			if (bindingResult.hasErrors()) {
				return ResponseEntity.ok(ResponseUtils.getErrorResponse(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; ")), String.valueOf(HttpStatus.BAD_REQUEST.value()), claimForm.getToken()));

			}
			
			if (claimDetailsService.existsByClaimReferenceId(claimForm.getClaimReferenceId())) {
 	            return ResponseEntity.ok(ResponseUtils.getErrorResponse("Record already exists", String.valueOf(HttpStatus.BAD_REQUEST.value()), claimForm.getToken()));
	        }
			
			claimDetailsService.saveClaimWithDocuments(claimForm);
			return ResponseEntity.ok(ResponseUtils.getSuccessResponse(claimForm.getToken()));
			
		}
		catch (ConstraintViolationException e) {
			String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));	    	
	        return ResponseEntity.ok(ResponseUtils.getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), claimForm.getToken()));
		}
		
		catch (Exception e) {
 			return ResponseEntity.ok(ResponseUtils.getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), claimForm.getToken()));
		}

	}
	
	
	@PostMapping("/pushClaimStatustoBank")
	public ResponseEntity<Map<String, Object>> pushClaimStatustoBank(@Valid @RequestBody ClaimUpdateInput claimForm,
			BindingResult bindingResult, @RequestHeader("user-name") String userName,
			@RequestHeader("api-key") String apiKey) {
		
		try {
			
			log.info("Request  "+ claimForm);
			
			if (bindingResult.hasErrors()) {
				return ResponseEntity.ok(ResponseUtils.getErrorResponse(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; ")), String.valueOf(HttpStatus.BAD_REQUEST.value()), claimForm.getToken()));

			}
			
			if (!claimDetailsService.existsByClaimReferenceId(claimForm.getClaimReferenceId())) {
 	            return ResponseEntity.ok(ResponseUtils.getErrorResponse("Claim doesnt exists", String.valueOf(HttpStatus.BAD_REQUEST.value()), claimForm.getToken()));
	        }
			
			claimDetailsService.updateClaim(claimForm);
			return ResponseEntity.ok(ResponseUtils.getSuccessResponse(claimForm.getToken()));
			
		}
		catch (ConstraintViolationException e) {
			String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));	    	
 			return ResponseEntity.ok(ResponseUtils.getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), claimForm.getToken()));
		}
		
		catch (Exception e) {
 			return ResponseEntity.ok(ResponseUtils.getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), claimForm.getToken()));
		}
	
	}

}
