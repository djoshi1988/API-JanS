package com.bank.cedrus.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.cedrus.builder.response.ResponseUtils;

@Service
public class ExceptionHandlerUtils {
	
	@Autowired
    private ResponseUtils responseUtils;
	
	 public ResponseEntity<String> handleException(Exception e, String token) {
	        if (e instanceof ConstraintViolationException) {
	            String errorMessage = ((ConstraintViolationException) e).getConstraintViolations()
	                    .stream()
	                    .map(ConstraintViolation::getMessage)
	                    .collect(Collectors.joining("; "));
	            return ResponseEntity.ok(responseUtils.getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), token));
	        } else {
	            return ResponseEntity.ok(responseUtils.getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), token));
	        }
	    }

}
