package com.bank.cedrus.validator;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mapping.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.cedrus.builder.response.ResponseUtils;
import com.bank.cedrus.model.ValidationResult;
import com.bank.cedrus.service.impl.EncryptionService;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ValidationService {
	
	
	@Autowired
    private EncryptionService encryptionService;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Value("${encryption.decryption.enabled:true}") 
	private boolean decryptionEnabled;
	

    public <T> ValidationResult<T> validateAndMap(String encryptedString, Class<T> clazz)  {
    	
    	 log.info("Encrypted Request "+ encryptedString);
         
         String decryptedString = decryptionEnabled ? decrypt(encryptedString) : encryptedString;
    	 
    	 T object = mapToObject(decryptedString, clazz);
    	 
    	 return validate(object);         
     } 
    
    String decrypt(String encryptedString) {        
    	
    	String json = "";
    	try
    	{
    	  json = encryptionService.decrypt(encryptedString);
    	  log.info("Decrypted Request "+ json);
 	    } catch (Exception e) {
 			 ResponseEntity.ok(new ResponseUtils().getErrorResponse("Decryption Fails", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null));
	    }
    	return json;
    }

    private <T> T mapToObject(String json, Class<T> clazz) {
    	try
    	{
    	  objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
          return objectMapper.readValue(json, clazz);
	    } catch (IOException e) {
	        throw new MappingException("Error mapping encryptedString data to object");
	    }
    }

    public <T> ValidationResult<T> validate(T object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            String errorMessages = violations.stream()
                    .map(violation ->  violation.getMessage())
                    .collect(Collectors.joining("; "));
            return new ValidationResult<>(errorMessages, object);
        }
        
        return new ValidationResult<>(null, object);
    }
}


