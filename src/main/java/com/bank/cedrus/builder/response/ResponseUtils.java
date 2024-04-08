package com.bank.cedrus.builder.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import com.bank.cedrus.model.response.Response;
import com.bank.cedrus.service.impl.EncryptionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseUtils {
	
	@Autowired
    private EncryptionService encryptionService;
    
    @Value("${encryption.decryption.enabled:true}") // Default value is true
    private boolean encryptionEnabled;

	public String getSuccessResponse(String requestToken) {
		Response<Object> response = new Response<>(String.valueOf(HttpStatus.OK.value()), requestToken,"");
	    log.info("Success Response  "+ response);
	    String jsonResponse = "";
		try {
			jsonResponse = new ObjectMapper().writeValueAsString(response);
			jsonResponse = encryptionEnabled ? encryptionService.encrypt(new ObjectMapper().writeValueAsString(response)) : new ObjectMapper().writeValueAsString(response);
			} catch (Exception e) { 			 
		}

	    return jsonResponse;
	}
	
	public String getResponse(Response<Object> response) {
	     log.info("Response  "+ response);
	     String jsonResponse = "";
		 try {
			jsonResponse = new ObjectMapper().writeValueAsString(response);
			jsonResponse = encryptionEnabled ? encryptionService.encrypt(new ObjectMapper().writeValueAsString(response)) : new ObjectMapper().writeValueAsString(response);
			} catch (Exception e) { 			 
		 }

	    return jsonResponse;
	}

	public String  getErrorResponse(String errorMessage, String statusCode, String requestToken) {
		Response<Object> response = new Response<>(statusCode, requestToken,errorMessage);
	    log.info("Error Response "+ response);
	    String jsonResponse = "";
		try {
			jsonResponse = new ObjectMapper().writeValueAsString(response);
			jsonResponse = encryptionEnabled ? encryptionService.encrypt(new ObjectMapper().writeValueAsString(response)) : new ObjectMapper().writeValueAsString(response);
			} catch (Exception e) { 			 
		}

	    return jsonResponse;
	}
}
