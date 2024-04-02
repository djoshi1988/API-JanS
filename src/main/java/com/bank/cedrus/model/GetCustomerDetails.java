package com.bank.cedrus.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class GetCustomerDetails {
	
    @NotBlank(message = "URN is required")
    @Size(min = 31, max = 32, message = "URN must be between 31 and 32 characters")
	private String urn;
    

    @Size(min = 3, max = 17, message = "Bank Account Number must be between 3 and 17 digits")
    @Pattern(regexp = "^\\d+$", message = "Invalid Bank Account Number")
    @NotBlank(message = "Account Number is required")
	private String accountNumber;
    
    @NotBlank(message = "Cif is required")
    @Size(min = 3, max = 17, message = "Cif must be between 3 and 17 digits")
    @Pattern(regexp = "^\\d+$", message = "Cif must contain only digits")
    private String cif;
    
    @NotBlank(message = "Token is required")
    private String token;
    
    
    public String toFormattedString() {
        return "PMYJAN|JANCDTA|" + accountNumber + "|" + urn + "|" + cif;
    }

}
