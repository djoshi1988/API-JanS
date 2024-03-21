package com.bank.cedrus.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bank.cedrus.enums.ClaimStatus;
import com.bank.cedrus.validator.EnumValue;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ClaimUpdateInput {

    @NotNull(message = "ClaimReferenceId is required")
    private Long claimReferenceId;

    @NotNull(message = "urn is required")
    @Size(min = 21, max = 32, message = "Urn must be between 21 and 32 digits")
    private String urn;

    @NotNull(message = "Claim Status is required, must be : {values}")
    @EnumValue(enumClass = ClaimStatus.class, message = "Invalid Claim Status")
    private Long claimStatus;

    private String insurerStatus;

    @Size(max = 500, message = "Reason must be less than or equal to 500 characters")
    private String reason;

    @NotNull(message = "Claim Id is required")
    @Size(min = 1, max = 100, message = "Claim Id must be between 1 and 100 characters")
    private String claimId;

    private TransactionDetails transactionDetails;

    @NotBlank(message = "Token is required")
    private String token;

}
