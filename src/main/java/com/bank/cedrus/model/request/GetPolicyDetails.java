package com.bank.cedrus.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class GetPolicyDetails {
	
    @NotNull(message = "Account number is required")
    @Pattern(regexp = "\\d{3,17}", message = "Account number must be between 3 and 17 digits")
    private String accountNumber;

    @NotBlank(message = "Account holder name is required")
    @Size(min = 1, max = 300, message = "Account holder name must be between 1 and 300 characters")
    private String accountHolderName;

    @NotNull(message = "CIF is required")
    @Pattern(regexp = "\\d{3,17}", message = "CIF must be between 3 and 17 digits")
    private String cif;

    @Size(min = 31, max = 32, message = "URN must be between 31 and 32 characters")
    private String urn;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "Transaction date must be in yyyy-MM-dd HH:mm:ss format")
    private String tranDetailsReqdDate;

    @NotNull(message = "Date of birth is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of birth must be in yyyy-MM-dd format")
    private String dob;

    @NotBlank(message = "Token is required")
    private String token;


}
