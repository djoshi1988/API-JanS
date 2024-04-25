package com.bank.cedrus.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bank.cedrus.enums.Scheme;
import com.bank.cedrus.validator.EnumValue;

import lombok.Data;

@Data
public class Applicant {
	
	@NotBlank(message = "Token is required")
    private String token;
	
    @NotBlank(message = "URN is required")
    @Size(min = 31, max = 32, message = "URN must be between 31 and 32 characters")
    private String urn;
    
    @NotBlank(message = "Policy Year is required")
    private String policyYear;	
    
    @Size(min = 3, max = 17, message = "Insurer Code must be between 3 and 17 digits")
    private String insurerCode;
    
    @Size(min = 1, max = 35, message = "Transaction UTR must be between 1 and 35 characters")
    private String transactionUTR;

    private String transactionTimestamp;

    private String transactionAmount;
    
    private String firstEnrollmentDate;
    
    @NotBlank(message = "Source is required")
    @Size(min = 2, max = 50, message = "Source must be between 2 and 50 characters")
    private String source;

    @Size(min = 2, max = 50, message = "Mode must be between 2 and 50 characters")
    private String mode;
    
    @Size(min = 1, max = 50, message = "Transaction Type must be between 1 and 50 characters")
    private String transactionType;

    @Size(min = 1, max = 100, message = "Master Policy Number must be between 1 and 100 characters")
    private String masterPolicyNumber;
    
    @NotBlank(message = "Scheme Name is required")
    @EnumValue(enumClass = Scheme.class, message = "Scheme Name must be one of: {values}")
    private String schemeName;
    
    @NotBlank(message = "Branch Code is required")
    @Size(min = 2, max = 30, message = "Branch Code must be between 2 and 30 characters")
    private String branchCode;

    @NotBlank(message = "Bank Code is required")
    @Size(min = 2, max = 30, message = "Bank Code must be between 2 and 30 characters")
    private String bankCode;

    @NotBlank(message = "Consent for Auto Debit is required")
    @Size(min = 3, max = 3, message = "Consent for Auto Debit must be 3 characters")
    private String consentForAutoDebit;
    
    private String userId1;

    private String userId2;    

    @Size(min = 2, max = 50, message = "Channel ID must be between 2 and 50 characters")
    private String channelId;

    @Size(min = 2, max = 50, message = "Rural/Urban must be between 2 and 50 characters")
    private String ruralUrban;
	
    @NotBlank(message = "Account Number is required")
    @Size(min = 3, max = 17, message = "Account Number must be between 3 and 17 digits")
    private String accountNumber;
    
    @NotBlank(message = "CIF is required")
    @Size(min = 3, max = 17, message = "CIF must be between 3 and 17 digits")
    private String cif;
    
    @NotBlank(message = "Customer IFSC is required")
    @Size(min = 11, max = 11, message = "Customer IFSC must be 11 digits and the 5th character should be '0'")
    private String customerIFSC;

    @NotBlank(message = "Account Holder Name is required")
    @Size(min = 1, max = 300, message = "Account Holder Name must be between 1 and 300 characters")
    private String accountHolderName;
    
    @NotBlank(message = "Gender is required")
    @Size(min = 1, max = 1, message = "Gender must be 'M', 'F', or 'T'")
    private String gender;
    
    @Size(min = 1, max = 150, message = "Father/Husband Name must be between 1 and 150 characters")
    private String fatherHusbandName;
    
    private String dob;

    @Size(min = 10, max = 10, message = "Mobile Number must be between 10 digits")
    private String mobileNumber;

    @Size(min = 5, max = 255, message = "Email ID must be between 5 and 255 characters")
    private String emailId;
    
    @Size(min = 2, max = 500, message = "Address Line 1 must be between 2 and 500 characters")
    private String addressline1;

    @Size(min = 2, max = 500, message = "Address Line 2 must be between 2 and 500 characters")
    private String addressline2;
    
    @Size(min = 6, max = 6, message = "Pincode must be 6 digits")
    private String pincode;

    @Size(min = 2, max = 200, message = "City must be between 2 and 200 characters")
    private String city;

    @Size(min = 2, max = 200, message = "District must be between 2 and 200 characters")
    private String district;

    @Size(min = 2, max = 200, message = "State must be between 2 and 200 characters")
    private String state;

    private String kycID1;

    @Size(min = 1, max = 100, message = "KYC ID1 Number must be between 1 and 100 characters")
    private String kycID1number;

    @Pattern(regexp = "YES|NO|Y|N|Yes|No", message = "PAN must be either YES or NO")
    private String pan;


    private String panNumber;

    @Pattern(regexp = "YES|NO|Y|N|Yes|No", message = "Aadhaar must be either YES or NO")
    private String aadhaar;

    private String aadhaarNumber;    
    
    private String disabilityStatus;

    @Size(min = 2, max = 200, message = "Disability Details must be between 2 and 200 characters")
    private String disabilityDetails;
    
    private String applicantOccupation;    
    
    @Size(min = 1, max = 300, message = "Nominee Name must be between 1 and 300 characters")
    private String nomineeName;   

    private String nomineeDateOfBirth;
    
    @Pattern(regexp = "[6-9][0-9]{9}", message = "Mobile number must start with 6/7/8/9 and be 10 digits long")
    private String nomineeMobileNumber;
    
    @Size(min = 1, max = 50, message = "Relationship of Nominee must be between 1 and 50 characters")
    private String relationshipOfNominee;
  
    @Email(message = "Nominee Email ID must be a valid email address")
    @Size(min = 5, max = 255, message = "Nominee Email ID must be between 5 and 255 characters")
    private String nomineeEmailId;
    
    @Size(min = 2, max = 500, message = "Address of Nominee must be between 2 and 500 characters")
    private String addressofNominee;
    
    @Size(min = 1, max = 300, message = "Name of Guardian must be between 1 and 300 characters")
    private String nameofGuardian;

    @Size(min = 2, max = 500, message = "Address of Guardian must be between 2 and 500 characters")
    private String addressofGuardian;

    @Size(min = 1, max = 50, message = "Relationship of Guardian must be between 1 and 50 characters")
    private String relationshipOfGuardian;   
    
    @Pattern(regexp = "[6-9][0-9]{9}", message = "Mobile number must start with 6/7/8/9 and be 10 digits long")
    private String guardianMobileNumber;    

    @Email(message = "Guardian Email ID must be a valid email address")
    @Size(min = 5, max = 255, message = "Guardian Email ID must be between 5 and 255 characters")
    private String guardianEmailId;
    
    private COI coi;

}


