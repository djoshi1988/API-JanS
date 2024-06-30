package com.bank.cedrus.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.cedrus.builder.RequestBuilder;
import com.bank.cedrus.builder.response.ResponseUtils;
import com.bank.cedrus.common.Constants;
import com.bank.cedrus.iso.ISORequest;
import com.bank.cedrus.iso.ISOResponse;
import com.bank.cedrus.model.AccountHolderDetails;
import com.bank.cedrus.model.CustomerDetails;
import com.bank.cedrus.model.ValidationResult;
import com.bank.cedrus.model.request.GetCustomerDetails;
import com.bank.cedrus.model.request.OTPGeneration;
import com.bank.cedrus.model.request.OTPValidation;
import com.bank.cedrus.model.request.PremiumDeduction;
import com.bank.cedrus.model.response.CustomerDetailsResp;
import com.bank.cedrus.model.response.PremiumDeductionResponse;
import com.bank.cedrus.model.response.Response;
import com.bank.cedrus.model.response.TriggerOtpResp;
import com.bank.cedrus.model.response.VerifyVerificationCodeResp;
import com.bank.cedrus.service.impl.ISOService;
import com.bank.cedrus.validator.ValidationService;

@RestController
@Validated
@RequestMapping("api/registry/v3")
public class OtherChannelMode {
 
  
	@Autowired
    private ValidationService validationService;
	
	@Autowired
    private ResponseUtils responseUtil;
	
	
	@Autowired
    private ISOService isoService;
	

	


 	@PostMapping("/triggerVerificationCode")
	public ResponseEntity<String> triggerOTP(@RequestBody String req,
			BindingResult bindingResult) {
 		    
 		    ValidationResult<OTPGeneration> validationResult = validationService.Map(req, OTPGeneration.class);
 	    
			if (validationResult.getErrorMessage() != null) {
				return ResponseEntity.ok(responseUtil.getErrorResponseForOtherChannel(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
			}
			
			OTPGeneration form = validationResult.getObject();
 		
 		
	 	    try
	 	    {	

	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.OTPGeneration,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	        Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue(new TriggerOtpResp( model.getValueAtIndex(0)));
				return ResponseEntity.ok(responseUtil.getResponseForOtherChannel(response));
				
			}
	 	    catch (Exception e) {
	 	    	 return ResponseEntity.ok(responseUtil.getErrorResponseForOtherChannel(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null)); 
	 		}


	}
 	
 	@PostMapping("/verifyVerificationCode")
	public ResponseEntity<String> verifyOTP(@RequestBody String req,
			BindingResult bindingResult) {	
 		
 		    ValidationResult<OTPValidation> validationResult = validationService.Map(req, OTPValidation.class);
 	    
			if (validationResult.getErrorMessage() != null) {
				return ResponseEntity.ok(responseUtil.getErrorResponseForOtherChannel(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
			}
			
			OTPValidation form = validationResult.getObject();
 		
	 	    try
	 	    {	
	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.OTPValidation,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue(new VerifyVerificationCodeResp(Collections.singletonList(Response.setFields(AccountHolderDetails.class,model.getData()))));
 	        
				return ResponseEntity.ok(responseUtil.getResponseForOtherChannel(response));
				
			}
	 	    catch (Exception e) {
	 	    	 return ResponseEntity.ok(responseUtil.getErrorResponseForOtherChannel(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null)); 
	 		}

	}
 	
 	
 	@PostMapping("/getCustomerDetails")
	public ResponseEntity<String> getCustomerDetails(@RequestBody String req,
			BindingResult bindingResult) { 		
 			 
 			ValidationResult<GetCustomerDetails> validationResult = validationService.Map(req, GetCustomerDetails.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(responseUtil.getErrorResponseForOtherChannel(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			GetCustomerDetails form = validationResult.getObject();
		
	 	    try
	 	    {	
	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.GetCustomerDetails,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue(new CustomerDetailsResp(new CustomerDetails().fromCustomerDetails(Response.setFields( CustomerDetails.class,model.getData()))));
 	        
				return ResponseEntity.ok(responseUtil.getResponseForOtherChannel(response));
				
			}
	 	    catch (Exception e) {
	 	    	 return ResponseEntity.ok(responseUtil.getErrorResponseForOtherChannel(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null)); 
	 		}

	}
 	
	@PostMapping("/premiumDeduction")
	public ResponseEntity<String> premiumDeduction(@RequestBody String req,
			BindingResult bindingResult) {
		
 
 			ValidationResult<PremiumDeduction> validationResult = validationService.Map(req, PremiumDeduction.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(responseUtil.getErrorResponseForOtherChannel(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
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
		 	    		try
		 	    		{
			 	    		model = isoService.executeISOService(isoReq);
				 	    	response = new Response<>(model.getResponseCode(), form.getToken(),null);
				 	    	if (response.isSuccess()) response.setOptionalValue(new PremiumDeductionResponse(rrN, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),form.getPremiumAmount(),"NA"));
		 	    		}
		 	    		catch(Exception e)
		 	    		{
		 	    			response.setOptionalValue(new PremiumDeductionResponse(rrN, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),form.getPremiumAmount(),"NA"));
		 	    		}
		 	    	}
	 	    		
	 	    	}
	 	    	
				return ResponseEntity.ok(responseUtil.getResponseForOtherChannel(response));
				
			}
	 	    catch (Exception e) {
	 	    	 return ResponseEntity.ok(responseUtil.getErrorResponseForOtherChannel(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null)); 
	 		}

	} 	
	
	
}
