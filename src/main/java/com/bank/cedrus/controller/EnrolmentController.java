package com.bank.cedrus.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.cedrus.builder.RequestBuilder;
import com.bank.cedrus.builder.response.ResponseUtils;
import com.bank.cedrus.common.Constants;
import com.bank.cedrus.exception.ExceptionHandlerUtils;
import com.bank.cedrus.iso.ISORequest;
import com.bank.cedrus.iso.ISOResponse;
import com.bank.cedrus.model.ValidationResult;
import com.bank.cedrus.model.request.Applicant;
import com.bank.cedrus.model.request.GetCustomerDetails;
import com.bank.cedrus.model.request.OTPGeneration;
import com.bank.cedrus.model.request.OTPValidation;
import com.bank.cedrus.model.request.PhysicalVerification;
import com.bank.cedrus.model.request.PremiumDeduction;
import com.bank.cedrus.model.response.AccountHolderDetails;
import com.bank.cedrus.model.response.CustomerDetails;
import com.bank.cedrus.model.response.Response;
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

 	@PostMapping("/triggerVerificationCode")
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

	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.OTPGeneration,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	        Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue("mobileNumber", model.getValueAtIndex(1));
				return ResponseEntity.ok(new ResponseUtils().getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return ExceptionHandlerUtils.handleException(e, form.getToken());
	 		}


	}
 	
 	@PostMapping("/verifyVerificationCode")
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
	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.OTPValidation,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue("accountHolderDetails",Collections.singletonList(Response.setFields(AccountHolderDetails.class,model.getData())));
 	        
				return ResponseEntity.ok(new ResponseUtils().getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return ExceptionHandlerUtils.handleException(e, form.getToken());
	 		}

	}
 	
 	@PostMapping("/physicalSignatureVerification")
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
	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.PhysicalVerification,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue("accountHolderDetails",Collections.singletonList(Response.setFields(AccountHolderDetails.class,model.getData())));
 	        
				return ResponseEntity.ok(new ResponseUtils().getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return ExceptionHandlerUtils.handleException(e, form.getToken());
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
	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.GetCustomerDetails,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue("accountHolderDetails",new CustomerDetails().fromCustomerDetails(Response.setFields( CustomerDetails.class,model.getData())));
 	        
				return ResponseEntity.ok(new ResponseUtils().getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return ExceptionHandlerUtils.handleException(e, form.getToken());
	 		}

	}
 	
 	
 	@PostMapping("/premiumDeduction")
	public ResponseEntity<String> premiumDeduction(@RequestBody String req,
			BindingResult bindingResult, @RequestHeader("user-name") String userName,
			@RequestHeader("api-key") String apiKey) {
		
 
 			ValidationResult<PremiumDeduction> validationResult = validationService.validateAndMap(req, PremiumDeduction.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(new ResponseUtils().getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			PremiumDeduction form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	    	ISORequest isoReq= new ISORequest();
	 	    	isoReq.setField125(form.toFormattedString());
	 	    	
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	    	
	 	    	if (response.isSuccess()) 
	 	    	{
	 	    		String creditAccount= model.getValueAtIndex(1);
	 	    		isoReq = isoReq.premiumISORequest(isoReq, form, creditAccount);
	 	    		model = isoService.executeISOService(isoReq);	 	    	
		 	    	response = new Response<>(model.getResponseCode(), form.getToken(),null);
		 	    	
		 	    	if (response.isSuccess()) 
		 	    	{
		 	    		isoReq= new ISORequest();
		 	    		isoReq.setField125(form.toUpdateCustString(creditAccount, model.getValueAtIndex(1)));
		 	    		model = isoService.executeISOService(isoReq);	 	    	
			 	    	response = new Response<>(model.getResponseCode(), form.getToken(),null);
		 	    	}
	 	    		
	 	    	}
	 	    	
				return ResponseEntity.ok(new ResponseUtils().getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return ExceptionHandlerUtils.handleException(e, form.getToken());
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
	 	    catch (Exception e) {
	 		    return ExceptionHandlerUtils.handleException(e, form.getToken());
	 		}

	}
	
	
	
}
