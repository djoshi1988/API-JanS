package com.bank.cedrus.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

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
import com.bank.cedrus.model.GetAccountHolderList;
import com.bank.cedrus.model.ValidationResult;
import com.bank.cedrus.model.request.GetAccHolderList;
import com.bank.cedrus.model.request.GetCOIDetails;
import com.bank.cedrus.model.request.GetPolicyDetails;
import com.bank.cedrus.model.response.AccountHolderListResp;
import com.bank.cedrus.model.response.COIDetailsResp;
import com.bank.cedrus.model.response.PolicyDetailsResp;
import com.bank.cedrus.model.response.Response;
import com.bank.cedrus.service.impl.ISOService;
import com.bank.cedrus.validator.ValidationService;

@RestController
@RequestMapping("/psbjansuraksha/v3")
public class CommonController {
	
	
	@Autowired
    private ValidationService validationService;
	
	@Autowired
    private ResponseUtils responseUtil;
	
	
	@Autowired
    private ISOService isoService;
	
	@Autowired
    private ExceptionHandlerUtils exceptionHandlerUtils;

	
	@PostMapping("/getAccHolderList")
	public ResponseEntity<String> getAccHolderList(@RequestBody String req,
			BindingResult bindingResult) {
		
 
 			ValidationResult<GetAccHolderList> validationResult = validationService.validateAndMap(req, GetAccHolderList.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(responseUtil.getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			GetAccHolderList form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.GetAccHolderList,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	
  	 	        Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue(new AccountHolderListResp(Collections.singletonList(Response.setFields(GetAccountHolderList.class,model.getData()))));
				return ResponseEntity.ok(responseUtil.getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return exceptionHandlerUtils.handleException(e, form.getToken());
	 		}
	}
	
	@PostMapping("/getPolicyDetails")
	public ResponseEntity<String> getPolicyDetails(@RequestBody String req,
			BindingResult bindingResult) {
		
 
 			ValidationResult<GetPolicyDetails> validationResult = validationService.validateAndMap(req, GetPolicyDetails.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(responseUtil.getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			GetPolicyDetails form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	 
	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.GetPolicyDetails,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 
	 	        Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue(Response.setFields(PolicyDetailsResp.class,model.getData()));
				return ResponseEntity.ok(responseUtil.getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return exceptionHandlerUtils.handleException(e, form.getToken());
	 		}

	}
	
	@PostMapping("/getCOIDetails")
	public ResponseEntity<String> getCOIDetails(@RequestBody String req,
			BindingResult bindingResult) {
		
 
 			ValidationResult<GetCOIDetails> validationResult = validationService.validateAndMap(req, GetCOIDetails.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(responseUtil.getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			GetCOIDetails form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	 
	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.GetCOIDetails,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	
	 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
	 	        if (response.isSuccess()) response.setOptionalValue(Response.setFields(COIDetailsResp.class,model.getData()));
				return ResponseEntity.ok(responseUtil.getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return exceptionHandlerUtils.handleException(e, form.getToken());
	 		}

	}

}
