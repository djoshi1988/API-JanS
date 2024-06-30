package com.bank.cedrus.model.request;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class OTPValidation {
	
    @Size(min = 3, max = 17, message = "Bank Account Number must be between 3 and 17 digits")
    @Pattern(regexp = "^\\d+$", message = "Invalid Bank Account Number")
 	private String accountNumber;
	
     @Size(min = 31, max = 32, message = "URN must be between 31 and 32 characters")
	private String urn;
    
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid Date Format. Please use yyyy-MM-dd")
 	private String dob;
    
    @Size(min = 6, max = 6, message = "Verification code must be exactly 6 digits")
    @Pattern(regexp = "^\\d+$", message = "Verification code must consist of digits only")
    private String verifyVerificationcode;
    
    private String token;
    
}
