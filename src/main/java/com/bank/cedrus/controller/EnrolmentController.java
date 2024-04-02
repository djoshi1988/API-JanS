package com.bank.cedrus.controller;

import java.util.Collections;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.cedrus.builder.response.ResponseUtils;
import com.bank.cedrus.iso.ISORequest;
import com.bank.cedrus.iso.ISOResponse;
import com.bank.cedrus.model.AccountHolderDetails;
import com.bank.cedrus.model.Applicant;
import com.bank.cedrus.model.CustomerDetails;
import com.bank.cedrus.model.GetCustomerDetails;
import com.bank.cedrus.model.OTPGeneration;
import com.bank.cedrus.model.OTPValidation;
import com.bank.cedrus.model.PhysicalVerification;
import com.bank.cedrus.model.Response;
import com.bank.cedrus.model.ValidationResult;
import com.bank.cedrus.service.impl.ApplicantService;
import com.bank.cedrus.service.impl.ISOService;
import com.bank.cedrus.validator.ValidationService;

@RestController
@RequestMapping("/api/bank/v3")
public class EnrolmentController {

	private final ApplicantService applicantService;
  
	
	@Autowired
    private ValidationService validationService;
	
	
	@Autowired
    private ISOService isoService;
	
	@Autowired
	public EnrolmentController(ApplicantService ApplicantService) {
		this.applicantService = ApplicantService;
   	}

 	@PostMapping("/triggerOTP")
	public ResponseEntity<String> triggerOTP(@RequestBody String req,
			BindingResult bindingResult, @RequestHeader("user-name") String userName,
			@RequestHeader("api-key") String apiKey) {
		
 
 			ValidationResult<OTPGeneration> validationResult = validationService.validateAndMap(req, OTPGeneration.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(new ResponseUtils().getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			OTPGeneration form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	    	ISORequest isoReq= new ISORequest();
	 	    	isoReq.setField125(form.toFormattedString());
	 	    	
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	        Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue("mobileNumber", model.getValueAtIndex(1));
				return ResponseEntity.ok(new ResponseUtils().getResponse(response));
				
			}
			catch (ConstraintViolationException e) {
				String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));	    	
		        return ResponseEntity.ok(new ResponseUtils().getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), form.getToken()));
			}
			
			catch (Exception e) {
	 			return ResponseEntity.ok(new ResponseUtils().getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), form.getToken()));
			}

	}
 	
 	@PostMapping("/verifyOTP")
	public ResponseEntity<String> verifyOTP(@RequestBody String req,
			BindingResult bindingResult, @RequestHeader("user-name") String userName,
			@RequestHeader("api-key") String apiKey) {
		
 
 			ValidationResult<OTPValidation> validationResult = validationService.validateAndMap(req, OTPValidation.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(new ResponseUtils().getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			OTPValidation form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	    	ISORequest isoReq= new ISORequest();
	 	    	isoReq.setField125(form.toFormattedString());
	 	    	
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue("accountHolderDetails",Collections.singletonList( new AccountHolderDetails().setFields(model.getData())));
 	        
				return ResponseEntity.ok(new ResponseUtils().getResponse(response));
				
			}
			catch (ConstraintViolationException e) {
				String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));	    	
		        return ResponseEntity.ok(new ResponseUtils().getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), form.getToken()));
			}
			
			catch (Exception e) {
	 			return ResponseEntity.ok(new ResponseUtils().getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), form.getToken()));
			}

	}
 	
 	@PostMapping("/physicalVerification")
	public ResponseEntity<String> physicalVerification(@RequestBody String req,
			BindingResult bindingResult, @RequestHeader("user-name") String userName,
			@RequestHeader("api-key") String apiKey) {
		
 
 			ValidationResult<PhysicalVerification> validationResult = validationService.validateAndMap(req, PhysicalVerification.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(new ResponseUtils().getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			PhysicalVerification form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	    	ISORequest isoReq= new ISORequest();
	 	    	isoReq.setField125(form.toFormattedString());
	 	    	
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue("accountHolderDetails",Collections.singletonList( new AccountHolderDetails().setFields(model.getData())));
 	        
				return ResponseEntity.ok(new ResponseUtils().getResponse(response));
				
			}
			catch (ConstraintViolationException e) {
				String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));	    	
		        return ResponseEntity.ok(new ResponseUtils().getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), form.getToken()));
			}
			
			catch (Exception e) {
	 			return ResponseEntity.ok(new ResponseUtils().getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), form.getToken()));
			}

	} 	
 	
 	
 	@PostMapping("/getCustomerDetails")
	public ResponseEntity<String> getCustomerDetails(@RequestBody String req,
			BindingResult bindingResult, @RequestHeader("user-name") String userName,
			@RequestHeader("api-key") String apiKey) {
		
 
 			ValidationResult<GetCustomerDetails> validationResult = validationService.validateAndMap(req, GetCustomerDetails.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(new ResponseUtils().getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			GetCustomerDetails form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	    	ISORequest isoReq= new ISORequest();
	 	    	isoReq.setField125(form.toFormattedString());
	 	    	
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue("accountHolderDetails",new CustomerDetails().fromCustomerDetails(new CustomerDetails().setFields(model.getData())));
 	        
				return ResponseEntity.ok(new ResponseUtils().getResponse(response));
				
			}
			catch (ConstraintViolationException e) {
				String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));	    	
		        return ResponseEntity.ok(new ResponseUtils().getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), form.getToken()));
			}
			
			catch (Exception e) {
	 			return ResponseEntity.ok(new ResponseUtils().getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), form.getToken()));
			}

	}
 	
 	
 	@PostMapping("/premiumDeduction")
	public ResponseEntity<String> premiumDeduction(@RequestBody String req,
			BindingResult bindingResult, @RequestHeader("user-name") String userName,
			@RequestHeader("api-key") String apiKey) {
		
 
 			ValidationResult<GetCustomerDetails> validationResult = validationService.validateAndMap(req, GetCustomerDetails.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(new ResponseUtils().getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			GetCustomerDetails form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	    	ISORequest isoReq= new ISORequest();
	 	    	isoReq.setField125(form.toFormattedString());
	 	    	
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue("accountHolderDetails",new CustomerDetails().fromCustomerDetails(new CustomerDetails().setFields(model.getData())));
 	        
				return ResponseEntity.ok(new ResponseUtils().getResponse(response));
				
			}
			catch (ConstraintViolationException e) {
				String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));	    	
		        return ResponseEntity.ok(new ResponseUtils().getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), form.getToken()));
			}
			
			catch (Exception e) {
	 			return ResponseEntity.ok(new ResponseUtils().getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), form.getToken()));
			}

	} 	
 	
 	
 	@PostMapping("/pushEnrollmentDetails")
	public ResponseEntity<String> processClaim(@RequestBody String req,
			BindingResult bindingResult, @RequestHeader("user-name") String userName,
			@RequestHeader("api-key") String apiKey) {
		
 
 			ValidationResult<Applicant> validationResult = validationService.validateAndMap(req, Applicant.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(new ResponseUtils().getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			Applicant form = validationResult.getObject();
 	    
	 	    try
	 	    {	
			
				if (applicantService.existsByUrn(form.getUrn())) {
	 	            return ResponseEntity.ok(new ResponseUtils().getErrorResponse("Record already exists", String.valueOf(HttpStatus.BAD_REQUEST.value()), form.getToken()));
		        }
				
				applicantService.saveApplicantWithCOI(form);
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
