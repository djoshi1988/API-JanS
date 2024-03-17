package com.bank.cedrus.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValue, CharSequence> {

    private EnumValue annotation;

    @Override
    public void initialize(EnumValue annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isValid = false;
        Class<? extends Enum<?>> enumClass = annotation.enumClass();
        Enum<?>[] enumValues = enumClass.getEnumConstants();

        if (enumValues != null) {
            for (Enum<?> enumValue : enumValues) {
                if (value.toString().equalsIgnoreCase(enumValue.name())) {
                    isValid = true;
                    break;
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
