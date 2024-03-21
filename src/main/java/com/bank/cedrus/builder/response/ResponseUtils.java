package com.bank.cedrus.builder.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.bank.cedrus.controller.ClaimController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseUtils {

	public static Map<String, Object> getSuccessResponse(String requestToken) {
	    Map<String, Object> response = new HashMap<>();
	    response.put("message", "success");
	    response.put("success", true);
	    response.put("status", "200");
	    response.put("token", requestToken); 
	    response.put("timeStamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	    log.info("Success Response  "+ response);
	    return response;
	}

	public static Map<String, Object> getErrorResponse(String errorMessage, String statusCode, String requestToken) {
	    Map<String, Object> response = new HashMap<>();
	    response.put("message", errorMessage);
	    response.put("success", false);
	    response.put("status", statusCode);
	    response.put("token", requestToken); 
	    response.put("timeStamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	    log.info("Error Response "+ response);
	    return response;
	}
}
