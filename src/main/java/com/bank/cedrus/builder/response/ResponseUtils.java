package com.bank.cedrus.builder.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bank.cedrus.model.response.Response;
import com.bank.cedrus.service.impl.EncryptionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResponseUtils {
	
	@Autowired
    private EncryptionService encryptionService;
    
	@Value("${encryption.decryption.enabled:true}") 
    private boolean encryptionEnabled;
  

	
	
	public String getResponse(Response<Object> response) {
	      
	     String jsonResponse = "";
	     Map<String, Object> jsonResponseMap = new HashMap<>();
		 try {
			jsonResponse = new ObjectMapper().writeValueAsString(response);
			log.info("Final Response before Encryption  "+ jsonResponse);
			jsonResponse = encryptionEnabled ? encryptionService.encrypt(jsonResponse) : jsonResponse;
			jsonResponseMap.put("metadata", jsonResponse);			
			jsonResponse= new  ObjectMapper().writeValueAsString(jsonResponseMap);
			} catch (Exception e) { 	
				 log.info("Exception while encrypting  "+ e);
		 }
		 
	     return jsonResponse;
	}

	public String  getErrorResponse(String errorMessage, String statusCode, String requestToken) {
		Response<Object> response = new Response<>(statusCode, requestToken,errorMessage);
	    log.info("Error Response "+ response);
	    String jsonResponse = "";
	    Map<String, Object> jsonResponseMap = new HashMap<>();
		try {
			jsonResponse = new ObjectMapper().writeValueAsString(response);
			jsonResponse = encryptionEnabled ? encryptionService.encrypt(jsonResponse) : jsonResponse;
			jsonResponseMap.put("metadata", jsonResponse);
			jsonResponse= new  ObjectMapper().writeValueAsString(jsonResponseMap);
			} catch (Exception e) { 	
				log.info("Exception while encrypting  "+ e);
		}

	    return jsonResponse;
	}
}
