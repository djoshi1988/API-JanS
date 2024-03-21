package com.bank.cedrus.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bank.cedrus.enums.ClaimStatus;
import com.bank.cedrus.enums.DocumentType;

public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

    private EnumValue annotation;

    @Override
    public void initialize(EnumValue annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isValid = false;
        Class<? extends Enum<?>> enumClass = annotation.enumClass();          
        
        if (value instanceof String) {
             String stringValue = (String) value;
            for (Enum<?> enumValue : enumClass.getEnumConstants()) {
                if (enumValue.name().equalsIgnoreCase(stringValue)) {
                    return true; 
                }
            }
        } else if (value instanceof Long) {
             Long longValue = (Long) value;
            for (Enum<?> enumValue : enumClass.getEnumConstants()) {
            	 if (value instanceof Long) {
            		 if (enumValue instanceof ClaimStatus) {
		                if (longValue.equals((long) ((ClaimStatus) enumValue).getValue())) {
		                    return true; 
		                }
            		 }
            		 else if (enumValue instanceof DocumentType) {
 		                if (longValue.equals((long) ((DocumentType) enumValue).getValue())) {
 		                    return true; 
 		                }
             		 }
            		 else
            		 {
            			 if (longValue.equals((long) (enumValue).ordinal())) {
  		                    return true; 
  		                }
            		 }
            	 }
            }
        }
        

        if (!isValid) {
        	
        	String enumValuesString = Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).reduce((v1, v2) -> v1 + ", " + v2).orElse("");
        	 
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(annotation.message().replace("{values}", enumValuesString))
                    .addConstraintViolation();
        }

        return isValid;
    }
}
