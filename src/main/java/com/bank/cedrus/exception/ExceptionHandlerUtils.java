package com.bank.cedrus.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bank.cedrus.builder.response.ResponseUtils;

public class ExceptionHandlerUtils {
	
	 public static ResponseEntity<String> handleException(Exception e, String token) {
	        if (e instanceof ConstraintViolationException) {
	            String errorMessage = ((ConstraintViolationException) e).getConstraintViolations()
	                    .stream()
	                    .map(ConstraintViolation::getMessage)
	                    .collect(Collectors.joining("; "));
	            return ResponseEntity.ok(new ResponseUtils().getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), token));
	        } else {
	            return ResponseEntity.ok(new ResponseUtils().getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), token));
	        }
	    }

}
