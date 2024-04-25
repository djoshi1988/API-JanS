package com.bank.cedrus.model;

import lombok.Data;

@Data
public class ClaimModel {
    private Long claimReferenceId;
    private Long masterPolicyNumber;
    private String urn;
    private String claimantName;
    private String claimantAddress;
    private String claimantDateOfBirth;
    private String relationshipOfClaimant;
    private String claimantMobileNumber;
    private String claimantEmailId;
    private String claimantKyc1;
    private String claimantKycNumber1;
    private String claimantKyc2;
    private String claimantKycNumber2;
    private String claimantGender;
    private String dateOfAccident;
    private String timeOfAccident;
    private String dayOfAccident;
    private String placeOfOccurence;
    private String natureOfAccident;
    private String dateOfDeath;
    private String causeOfDeath;
    private String causeOfDeathDisability;
    private String typeOfDisability;
    private String claimantBankAccountNumber;
    private String claimantBankName;
    private String claimantBranchIfsc;
    private String premDebitDate;
    private String premRemitDate;
    private String dateOfLodgingClaim;
    private Long claimStatus;
    private String insurerStatus;
    private String reason;
    private String documentType;
    private String contentType;
    private Long documentId;
    private String document;

 }

