package com.bank.cedrus.model.request;

import lombok.Data;

@Data
public class EnrolmentRequestModel {
	
	private String urn;
	private String policyYear;
	private String insurerCode;
	private String transactionUTR;
	private String transactionTimestamp;
	private String transactionAmount;
	private String firstEnrollmentDate;
	private String source;
	private String mode;
	private String transactionType;
	private String masterPolicyNumber;
	private String schemeName;
	private String branchCode;
	private String bankCode;
	private String consentForAutoDebit;
	private String userId1;
	private String userId2;
	private String channelId;
	private String ruralUrban;
	private String accountNumber;
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
	private String pincode;
	private String city;
	private String district;
	private String state;
	private String kycID1;
	private String kycID1number;
	private String pan;
	private String panNumber;
	private String aadhaar;
	private String aadhaarNumber;
	private String disabilityStatus;
	private String disabilityDetails;
	private String applicantOccupation;
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
	private String documentType;
    private String contentType;
    private String document;

}
