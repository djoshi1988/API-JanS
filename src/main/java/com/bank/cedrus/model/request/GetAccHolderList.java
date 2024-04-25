package com.bank.cedrus.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class GetAccHolderList {
	
    @Size(min = 3, max = 17, message = "Bank Account Number must be between 3 and 17 digits")
    @Pattern(regexp = "^\\d+$", message = "Invalid Bank Account Number")
 	private String accountNumber;	
	
    @Size(min = 31, max = 32, message = "URN must be between 31 and 32 characters")
	private String urn;
    
 	private String dob;
    
    
    @NotBlank(message = "Token is required")
    private String token;
    

}
