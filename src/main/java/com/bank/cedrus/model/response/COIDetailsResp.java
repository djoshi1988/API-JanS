package com.bank.cedrus.model.response;

import lombok.Data;

@Data
public class COIDetailsResp {

    private String accountHolderName;
    private String dob;
    private String mobileNumber;
    private String addressline1;
    private String addressline2;
    private String city;
    private String district;
    private String state;
    private String pincode;
    private String kycID1;
    private String kycID1number;
    private String firstEnrollmentDate;
    private String nomineeName;
    private String nomineeDateOfBirth;
    private String nameofGuardian;
    private String relationshipOfGuardian;
}

