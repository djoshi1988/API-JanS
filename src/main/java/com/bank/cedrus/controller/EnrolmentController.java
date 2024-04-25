package com.bank.cedrus.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.cedrus.builder.RequestBuilder;
import com.bank.cedrus.builder.response.ResponseUtils;
import com.bank.cedrus.common.Constants;
import com.bank.cedrus.exception.ExceptionHandlerUtils;
import com.bank.cedrus.iso.ISORequest;
import com.bank.cedrus.iso.ISOResponse;
import com.bank.cedrus.model.AccountHolderDetails;
import com.bank.cedrus.model.CustomerDetails;
import com.bank.cedrus.model.ValidationResult;
import com.bank.cedrus.model.request.Applicant;
import com.bank.cedrus.model.request.EnrolmentRequestModel;
import com.bank.cedrus.model.request.GetCustomerDetails;
import com.bank.cedrus.model.request.OTPGeneration;
import com.bank.cedrus.model.request.OTPValidation;
import com.bank.cedrus.model.request.PhysicalVerification;
import com.bank.cedrus.model.request.PremiumDeduction;
import com.bank.cedrus.model.response.CustomerDetailsResp;
import com.bank.cedrus.model.response.PhysicalSignatureVerificationResp;
import com.bank.cedrus.model.response.PremiumDeductionResponse;
import com.bank.cedrus.model.response.Response;
import com.bank.cedrus.model.response.TriggerOtpResp;
import com.bank.cedrus.model.response.VerifyVerificationCodeResp;
import com.bank.cedrus.service.impl.ISOService;
import com.bank.cedrus.validator.ValidationService;

@RestController
@RequestMapping("/psbjansuraksha/v3")
public class EnrolmentController {
 
  
	
	@Autowired
    private ValidationService validationService;
	
	@Autowired
    private ResponseUtils responseUtil;
	
	
	@Autowired
    private ISOService isoService;
	
	@Autowired
    private ExceptionHandlerUtils exceptionHandlerUtils;
	


 	@PostMapping("/triggerVerificationCode")
	public ResponseEntity<String> triggerOTP(@RequestBody String req,
			BindingResult bindingResult) {
		
 
 			ValidationResult<OTPGeneration> validationResult = validationService.validateAndMap(req, OTPGeneration.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(responseUtil.getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			OTPGeneration form = validationResult.getObject();
 	    
	 	    try
	 	    {	

	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.OTPGeneration,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	        Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue(new TriggerOtpResp( model.getValueAtIndex(0)));
				return ResponseEntity.ok(responseUtil.getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return exceptionHandlerUtils.handleException(e, form.getToken());
	 		}


	}
 	
 	@PostMapping("/verifyVerificationCode")
	public ResponseEntity<String> verifyOTP(@RequestBody String req,
			BindingResult bindingResult) {
		
 
 			ValidationResult<OTPValidation> validationResult = validationService.validateAndMap(req, OTPValidation.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(responseUtil.getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			OTPValidation form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.OTPValidation,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue(new VerifyVerificationCodeResp(Collections.singletonList(Response.setFields(AccountHolderDetails.class,model.getData()))));
 	        
				return ResponseEntity.ok(responseUtil.getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return exceptionHandlerUtils.handleException(e, form.getToken());
	 		}

	}
 	
 	@PostMapping("/physicalSignatureVerification")
	public ResponseEntity<String> physicalVerification(@RequestBody String req,
			BindingResult bindingResult) {
		
 
 			ValidationResult<PhysicalVerification> validationResult = validationService.validateAndMap(req, PhysicalVerification.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(responseUtil.getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			PhysicalVerification form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.PhysicalVerification,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue(new PhysicalSignatureVerificationResp(Collections.singletonList(Response.setFields(AccountHolderDetails.class,model.getData()))));
 	        
				return ResponseEntity.ok(responseUtil.getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return exceptionHandlerUtils.handleException(e, form.getToken());
	 		}

	} 	
 	
 	
 	@PostMapping("/getCustomerDetails")
	public ResponseEntity<String> getCustomerDetails(@RequestBody String req,
			BindingResult bindingResult) {
		
 
 			ValidationResult<GetCustomerDetails> validationResult = validationService.validateAndMap(req, GetCustomerDetails.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(responseUtil.getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			GetCustomerDetails form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.GetCustomerDetails,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue(new CustomerDetailsResp(new CustomerDetails().fromCustomerDetails(Response.setFields( CustomerDetails.class,model.getData()))));
 	        
				return ResponseEntity.ok(responseUtil.getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return exceptionHandlerUtils.handleException(e, form.getToken());
	 		}

	}
 	
 	
 	@PostMapping("/premiumDeduction")
	public ResponseEntity<String> premiumDeduction(@RequestBody String req,
			BindingResult bindingResult) {
		
 
 			ValidationResult<PremiumDeduction> validationResult = validationService.validateAndMap(req, PremiumDeduction.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(responseUtil.getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
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
	 	    		isoReq= new ISORequest();
	 	    		String creditAccount= model.getValueAtIndex(0);
	 	    		isoReq = isoReq.premiumISORequest(isoReq, form, creditAccount);
	 	    		model = isoService.executeISOService(isoReq);	 	    	
		 	    	response = new Response<>(model.getResponseCode(), form.getToken(),null);
		 	    	
		 	    	if (response.isSuccess()) 
		 	    	{
		 	    		String rrN= isoReq.getField11();
		 	    		isoReq= new ISORequest();
		 	    		isoReq.setField125(form.toUpdateCustString(creditAccount, rrN));
		 	    		model = isoService.executeISOService(isoReq);
			 	    	response = new Response<>(model.getResponseCode(), form.getToken(),null);
			 	    	if (response.isSuccess()) response.setOptionalValue(new PremiumDeductionResponse(rrN, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),form.getPremiumAmount(),"NA"));
		 	    	}
	 	    		
	 	    	}
	 	    	
				return ResponseEntity.ok(responseUtil.getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return exceptionHandlerUtils.handleException(e, form.getToken());
	 		}

	} 	
 	
 	
 	@PostMapping("/pushEnrollmentDetails")
	public ResponseEntity<String> processClaim(@RequestBody String req,
			BindingResult bindingResult) {
		
 
 			ValidationResult<Applicant> validationResult = validationService.validateAndMap(req, Applicant.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(responseUtil.getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			Applicant form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	    	EnrolmentRequestModel enrolModel= new EnrolmentRequestModel();
				BeanUtils.copyProperties( form,enrolModel);
				enrolModel.setDocumentType(form.getCoi().getDocumentType());
				enrolModel.setContentType((form.getCoi().getContentType()));
				//BeanUtils.copyProperties(form.getCoi(), enrolModel);
	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(enrolModel,Constants.Enrolment,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null); 	        
				return ResponseEntity.ok(responseUtil.getResponse(response));
			 
				
			}
	 	    catch (Exception e) {
	 		    return exceptionHandlerUtils.handleException(e, form.getToken());
	 		}

	}
	
	
	
}
