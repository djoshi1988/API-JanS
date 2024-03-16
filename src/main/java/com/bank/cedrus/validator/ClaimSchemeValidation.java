package com.bank.cedrus.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ClaimSchemeValidator.class})
public @interface ClaimSchemeValidation {
	
	String message() default "Claim Scheme validation failed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
