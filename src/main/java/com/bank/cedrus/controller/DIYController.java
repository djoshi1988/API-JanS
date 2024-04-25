package com.bank.cedrus.controller;

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
import com.bank.cedrus.model.GetAccountHolderList;
import com.bank.cedrus.model.ValidationResult;
import com.bank.cedrus.model.request.NomineeDetails;
import com.bank.cedrus.model.request.OptOutUpdateStatus;
import com.bank.cedrus.model.response.Response;
import com.bank.cedrus.service.impl.ISOService;
import com.bank.cedrus.validator.ValidationService;

@RestController
@RequestMapping("/psbjansuraksha/v3")
public class DIYController {
	
	
	@Autowired
    private ValidationService validationService;
	
	@Autowired
    private ResponseUtils responseUtil;
	
	
	@Autowired
    private ISOService isoService;
	
	@Autowired
    private ExceptionHandlerUtils exceptionHandlerUtils;

	
	@PostMapping("/optOutUpdateStatus")
	public ResponseEntity<String> optOutUpdateStatus(@RequestBody String req,
			BindingResult bindingResult) {
		
 
 			ValidationResult<OptOutUpdateStatus> validationResult = validationService.validateAndMap(req, OptOutUpdateStatus.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(responseUtil.getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			OptOutUpdateStatus form = validationResult.getObject();
 	    
	 	    try
	 	    {	
	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.OptOutUpdateStatus,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	        Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
				return ResponseEntity.ok(responseUtil.getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return exceptionHandlerUtils.handleException(e, form.getToken());
	 		}
	}

	
	
	@PostMapping("/nomineeUpdateStatus")
	public ResponseEntity<String> nomineeUpdateStatus(@RequestBody String req,
			BindingResult bindingResult) {
		
 
 			ValidationResult<NomineeDetails> validationResult = validationService.validateAndMap(req, NomineeDetails.class);
 	    
 			if (validationResult.getErrorMessage() != null) {
 				return ResponseEntity.ok(responseUtil.getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 			}
 			
 			NomineeDetails form = validationResult.getObject();
 	    
	 	    try
	 	    {	

	 	    	ISORequest isoReq = RequestBuilder.buildISORequest(form,Constants.NomineeDetails,null);
	 	    	ISOResponse model = isoService.executeISOService(isoReq);	 	    	
	 	        Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null);
				return ResponseEntity.ok(responseUtil.getResponse(response));
				
			}
	 	    catch (Exception e) {
	 		    return exceptionHandlerUtils.handleException(e, form.getToken());
	 		}
	}

}
