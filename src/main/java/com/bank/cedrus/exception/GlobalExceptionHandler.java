package com.bank.cedrus.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bank.cedrus.builder.response.ResponseUtils;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
    private ResponseUtils responseUtils;
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {       
 	   return ResponseEntity.ok(responseUtils.getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ""));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
    	String errorMessage = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));	    	
     	return ResponseEntity.status(HttpStatus.OK).body(responseUtils.getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), ""));
    	
     }


}

