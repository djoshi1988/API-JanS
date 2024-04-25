package com.bank.cedrus.model;

import lombok.Data;

@Data
public class AccountHolder {
	private String cif;
    private String customerIFSC;
    private String accountHolderName;
    private String gender;
    private String fatherHusbandName;
    private String dob;
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
    private String applicantOccupation;
    private String nomineeName;
    private String nomineeDateOfBirth;
    private String nomineeMobileNumber;
    private String relationshipOfNominee;
    private String nomineeEmailId;
    private String addressofNominee;
    private String addressofGuardian;
    private String relationshipOfGuardian;
    private String guardianMobileNumber;
    private String guardianEmailId;
    private String bcReferralId;
    private String consentForautodebit;

}
