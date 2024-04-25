package com.bank.cedrus.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class GetCOIDetails {
	
    @Pattern(regexp = "\\d{3,17}", message = "Account number must be between 3 and 17 digits")
    private String accountNumber;

    @Pattern(regexp = "\\d{3,17}", message = "CIF must be between 3 and 17 digits")
    private String cif;

    @Size(min = 31, max = 32, message = "URN must be between 31 and 32 characters")
    private String urn;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of birth must be in yyyy-MM-dd format")
    private String dob;

    @NotBlank(message = "Token is required")
    private String token;


}
