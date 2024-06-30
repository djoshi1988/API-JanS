package com.bank.cedrus.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PhysicalVerification {
	
    @Size(min = 3, max = 17, message = "Claimant Bank Account Number must be between 3 and 17 digits")
    @Pattern(regexp = "^\\d+$", message = "Invalid Bank Account Number")
    @NotBlank(message = "Account Number is required")
	private String accountNumber;
	
    @NotBlank(message = "URN is required")
    @Size(min = 31, max = 32, message = "URN must be between 31 and 32 characters")
	private String urn;
    
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid Date Format. Please use yyyy-MM-dd")
	private String dob;
    
    @NotBlank(message = "Sign Verified By Bank is required")
    @Pattern(regexp = "(?i)^YES$", message = "Sign Verified By Bank must be YES")
    private String signVerifiedByBank;
    
    @NotBlank(message = "Token is required")
    private String token;
    

}
