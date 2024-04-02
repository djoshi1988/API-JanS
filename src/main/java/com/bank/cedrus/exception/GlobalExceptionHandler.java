package com.bank.cedrus.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bank.cedrus.builder.response.ResponseUtils;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {       
 	   return ResponseEntity.ok(new ResponseUtils().getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ""));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
    	String errorMessage = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));	    	
     	return ResponseEntity.status(HttpStatus.OK).body(new ResponseUtils().getErrorResponse(errorMessage, String.valueOf(HttpStatus.BAD_REQUEST.value()), ""));
    	
     }


}

