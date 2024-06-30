package com.bank.cedrus.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class OptOutUpdateStatus {

	@Size(min = 3, max = 17, message = "Bank Account Number must be between 3 and 17 digits")
    @Pattern(regexp = "^\\d+$", message = "Invalid Bank Account Number")
    @NotBlank(message = "Account Number is required")
	private String accountNumber;    

    @Size(min = 3, max = 17, message = "Cif must be between 3 and 17 digits")
    @Pattern(regexp = "^\\d+$", message = "Invalid Cif Number")
    @NotBlank(message = "Cif is required")
	private String cif;
    
    @NotBlank(message = "URN is required")
    @Size(min = 31, max = 32, message = "URN must be between 31 and 32 characters")
	private String urn;
    
    @NotBlank(message = "Effective date is required")
	private String effectiveDate;
    
    @NotBlank(message = "Request date is required")
	private String requestDate;    

    @NotBlank(message = "Token is required")
    private String token;
    

}
