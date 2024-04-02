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
import com.bank.cedrus.model.ClaimDetails;
import com.bank.cedrus.model.ClaimUpdateInput;
import com.bank.cedrus.model.NomineeDetails;
import com.bank.cedrus.model.ValidationResult;
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
		catch (ConstraintViolationException e) {
			String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));	    	
	        return ResponseEntity.ok(new ResponseUtils().getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), claimForm.getToken()));
		}
		catch (Exception e) {
 			return ResponseEntity.ok(new ResponseUtils().getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), claimForm != null ? claimForm.getToken() : null));
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
		catch (ConstraintViolationException e) {
			String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));	    	
	        return ResponseEntity.ok(new ResponseUtils().getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), claimForm.getToken()));
		}
		catch (Exception e) {
 			return ResponseEntity.ok(new ResponseUtils().getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), claimForm.getToken()));
		}
	
	}
	
	@PostMapping("/nomineeUpdateStatus")
	public ResponseEntity<String> nomineeUpdateStatus(@RequestBody String req, @RequestHeader("user-name") String userName,
			@RequestHeader("api-key") String apiKey) {
		
		
		ValidationResult<NomineeDetails> validationResult = validationService.validateAndMap(req, NomineeDetails.class);
 	    
 	    if (validationResult.getErrorMessage() != null) {
  	        return ResponseEntity.ok(new ResponseUtils().getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 	    }
 	    
 	    NomineeDetails form = validationResult.getObject();
		
		try {			
 			
			if (!claimDetailsService.existsByUrn(form.getUrn())) {
 	            return ResponseEntity.ok(new ResponseUtils().getErrorResponse("Claim doesnt exists", String.valueOf(HttpStatus.BAD_REQUEST.value()), form.getToken()));
	        }
			
			claimDetailsService.updateNominee(form);
			return ResponseEntity.ok(new ResponseUtils().getSuccessResponse(form.getToken()));
			
		}
		catch (ConstraintViolationException e) {
			String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));	    	
	        return ResponseEntity.ok(new ResponseUtils().getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), form.getToken()));
		}
		catch (Exception e) {
 			return ResponseEntity.ok(new ResponseUtils().getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), form.getToken()));
		}
	
	}

}
