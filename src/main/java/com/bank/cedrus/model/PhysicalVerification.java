package com.bank.cedrus.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PhysicalVerification {
	
    @NotBlank(message = "URN is required")
    @Size(min = 31, max = 32, message = "URN must be between 31 and 32 characters")
	private String urn;
    
    @NotBlank(message = "DOB is required")
	private String dob;
    
    @Size(min = 3, max = 17, message = "Claimant Bank Account Number must be between 3 and 17 digits")
    @Pattern(regexp = "^\\d+$", message = "Invalid Bank Account Number")
    @NotBlank(message = "Account Number is required")
	private String accountNumber;
    
    @NotBlank(message = "Sign Verified By Bank is required")
    @Pattern(regexp = "^YES$", message = "Sign Verified By Bank must be YES")
    private String signVerifiedByBank;
    
    @NotBlank(message = "Token is required")
    private String token;
    
    
    public String toFormattedString() {
        return "PMYJAN|JANPVER|" + accountNumber + "|" + urn + "|" + dob + "|" + signVerifiedByBank;
    }

}
