package com.bank.cedrus.model;

import lombok.Data;

@Data
public class ClaimUpdateModel {
	
    private Long claimReferenceId;
    private String urn;
    private Long claimStatus;
    private String insurerStatus;
    private String reason;
    private String claimId;

}
