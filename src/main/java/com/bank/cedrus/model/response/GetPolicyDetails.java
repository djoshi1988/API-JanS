package com.bank.cedrus.model.response;

import lombok.Data;

@Data
public class GetPolicyDetails {

    private String accountNumber;
    private String accountHolderName;
    private String cif;
    private String urn;
    private String tranDetailsReqdDate;
    private String dob;
    private String token;
    private String customerIFSC;
    private String gender;
    private String fatherHusbandName;
    private String mobileNumber;
    private String emailId;
    private String addressline1;
    private String addressline2;
    private String city;
    private String district;
    private String state;
    private String pincode;
    private String kycID1;
    private String kycID1number;
    private String pan;
    private String panNumber;
    private String aadhaar;
    private String aadhaarNumber;
    private String ckyc;
    private String ckycNumber;
    private String firstEnrollmentDate;
    private String nomineeName;
    private String nomineeDateOfBirth;
    private String nomineeMobileNumber;
    private String relationshipOfNominee;
    private String nomineeEmailId;
    private String addressofNominee;
    private String nameofGuardian;
    private String addressofGuardian;
    private String relationshipOfGuardian;
    private String guardianMobileNumber;
    private String guardianEmailId;
    private String transactionUTR;
    private String transactionTimestamp;
    private String transactionAmount;
}

