package com.bank.cedrus.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.bank.cedrus.model.ClaimModel;
import com.bank.cedrus.model.ClaimUpdateModel;
import com.bank.cedrus.model.ValidationResult;
import com.bank.cedrus.model.request.ClaimDetails;
import com.bank.cedrus.model.request.ClaimUpdateInput;
import com.bank.cedrus.model.request.Document;
import com.bank.cedrus.model.response.Response;
import com.bank.cedrus.service.impl.DocumentRepositoryService;
import com.bank.cedrus.service.impl.ISOService;
import com.bank.cedrus.validator.ValidationService;

@RestController
@RequestMapping("/psbjansuraksha/v3")
public class ClaimController {

	@Autowired
    private ISOService isoService;
	
 	
	@Autowired
    private ValidationService validationService;
	
	@Autowired
    private ResponseUtils responseUtil;
 
	
	@Autowired
    private ExceptionHandlerUtils exceptionHandlerUtils;
	
	@Autowired
	private DocumentRepositoryService documentRepositoryService;
 

 	@PostMapping("/pushClaimDetails")
	public ResponseEntity<String> processClaim(@RequestBody String req) {	
 		
 		ValidationResult<ClaimDetails> validationResult = validationService.validateAndMap(req, ClaimDetails.class);
 	    
 	    if (validationResult.getErrorMessage() != null) {
  	        return ResponseEntity.ok(responseUtil.getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 	    }
 	    
 	    ClaimDetails form = validationResult.getObject();
 	    
		try {	
			ClaimModel claimModel= new ClaimModel();
			BeanUtils.copyProperties( form,claimModel);
			//BeanUtils.copyProperties(form.getDocuments().get(0), claimModel);
			
			for (Document document : form.getDocuments()) {
				documentRepositoryService.saveDocument(form, document);
            }
			
			ISORequest isoReq = RequestBuilder.buildISORequest(claimModel,Constants.SubmitClaims,null);
			ISOResponse model = isoService.executeISOService(isoReq);
 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null); 	        
			return ResponseEntity.ok(responseUtil.getResponse(response));
		 
			
		}
		catch (Exception e) {
 		    return exceptionHandlerUtils.handleException(e, form.getToken());
 		}

	}
	
	
	@PostMapping("/pushClaimStatustoBank")
	public ResponseEntity<String> pushClaimStatustoBank(@RequestBody String req) {
		
		
		ValidationResult<ClaimUpdateInput> validationResult = validationService.validateAndMap(req, ClaimUpdateInput.class);
 	    
 	    if (validationResult.getErrorMessage() != null) {
  	        return ResponseEntity.ok(responseUtil.getErrorResponse(validationResult.getErrorMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), validationResult.getObject().getToken()));
 	    }
 	    
 	    ClaimUpdateInput form = validationResult.getObject();
		
		try {			
 			
			ClaimUpdateModel claimUpdateModel= new ClaimUpdateModel();
			BeanUtils.copyProperties( form,claimUpdateModel);
			ISORequest isoReq = RequestBuilder.buildISORequest(claimUpdateModel,Constants.UpdateClaims,null);
 	    	ISOResponse model = isoService.executeISOService(isoReq);
 	    	Response<Object> response = new Response<>(model.getResponseCode(), form.getToken(),null); 	        
			return ResponseEntity.ok(responseUtil.getResponse(response));
			
		}
		catch (Exception e) {
 		    return exceptionHandlerUtils.handleException(e, form.getToken());
 		}
	
	}
	
	

}
