package com.bank.cedrus.controller;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.cedrus.builder.response.ResponseUtils;
import com.bank.cedrus.exception.ExceptionHandlerUtils;
import com.bank.cedrus.model.ValidationResult;
import com.bank.cedrus.model.request.ClaimDetails;
import com.bank.cedrus.model.request.ClaimUpdateInput;
import com.bank.cedrus.model.request.NomineeDetails;
import com.bank.cedrus.service.impl.ClaimDetailsService;
import com.bank.cedrus.validator.ValidationService;

@RestController
@RequestMapping("/api/bank/v3")
public class ClaimController {

	private final ClaimDetailsService claimDetailsService;
 	
	@Autowired
    private ValidationService validationService;
 
	@Autowired
	public ClaimController(ClaimDetailsService claimDetailsService) {
		this.claimDetailsService = claimDetailsService;
   	}

 	@PostMapping("/pushClaimDetails")
	public ResponseEntity<String> processClaim(@RequestBody String req, @RequestHeader("user-name") String userName,
			@RequestHeader("api-key") String apiKey) {	
 		
 		ValidationResult<ClaimDetails> validationResult = validationService.validateAndMap(req, ClaimDetails.class);
 	    
 	    if (validationResult.getErrorMessage() != null) {
  	        return ResponseEntity.ok(new ResponseUtils().getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 	    }
 	    
 	    ClaimDetails claimForm = validationResult.getObject();
 	    
		try {			
			
			if (claimDetailsService.existsByClaimReferenceId(claimForm.getClaimReferenceId())) {
 	            return ResponseEntity.ok(new ResponseUtils().getErrorResponse("Record already exists", String.valueOf(HttpStatus.BAD_REQUEST.value()), claimForm.getToken()));
	        }
			
			claimDetailsService.saveClaimWithDocuments(claimForm);
			return ResponseEntity.ok(new ResponseUtils().getSuccessResponse(claimForm.getToken()));
			
		}
		catch (Exception e) {
 		    return ExceptionHandlerUtils.handleException(e, claimForm.getToken());
 		}

	}
	
	
	@PostMapping("/pushClaimStatustoBank")
	public ResponseEntity<String> pushClaimStatustoBank(@RequestBody String req, @RequestHeader("user-name") String userName,
			@RequestHeader("api-key") String apiKey) {
		
		
		ValidationResult<ClaimUpdateInput> validationResult = validationService.validateAndMap(req, ClaimUpdateInput.class);
 	    
 	    if (validationResult.getErrorMessage() != null) {
  	        return ResponseEntity.ok(new ResponseUtils().getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 	    }
 	    
 	    ClaimUpdateInput claimForm = validationResult.getObject();
		
		try {			
 			
			if (!claimDetailsService.existsByClaimReferenceId(claimForm.getClaimReferenceId())) {
 	            return ResponseEntity.ok(new ResponseUtils().getErrorResponse("Claim doesnt exists", String.valueOf(HttpStatus.BAD_REQUEST.value()), claimForm.getToken()));
	        }
			
			claimDetailsService.updateClaim(claimForm);
			return ResponseEntity.ok(new ResponseUtils().getSuccessResponse(claimForm.getToken()));
			
		}
		catch (Exception e) {
 		    return ExceptionHandlerUtils.handleException(e, claimForm.getToken());
 		}
	
	}
	
	

}
