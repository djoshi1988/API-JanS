package com.bank.cedrus.model.request;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bank.cedrus.enums.ClaimStatus;
import com.bank.cedrus.enums.ClaimantRelationship;
import com.bank.cedrus.enums.DaysOfWeek;
import com.bank.cedrus.enums.Gender;
import com.bank.cedrus.enums.Guardians;
import com.bank.cedrus.enums.KycDocType;
import com.bank.cedrus.enums.Scheme;
import com.bank.cedrus.validator.EnumValue;

import lombok.Data;

@Data
 public class ClaimDetails{
	
    @NotNull(message = "Claim Reference ID is required")
    private Long claimReferenceId;

    @NotNull(message = "Master Policy Number is required")
    private String masterPolicyNumber;

    @NotBlank(message = "Token is required")
    private String token;

    @NotBlank(message = "URN is required")
    @Size(min = 31, max = 32, message = "URN must be between 31 and 32 characters")
    private String urn;

    @NotBlank(message = "Scheme Name is required")
    @EnumValue(enumClass = Scheme.class, message = "Scheme Name must be one of: {values}")
    private String schemeName;

    @NotBlank(message = "Customer Account Number is required")
    @Size(min = 3, max = 17, message = "Customer Account Number must be between 3 and 17 digits")
    private String customerAccountNumber;

    @NotBlank(message = "Customer Bank Name is required")
    @Size(min = 2, max = 50, message = "Customer Bank Name must be between 2 and 50 characters")
    private String customerBankName;

    @NotBlank(message = "Customer IFSC is required")
    private String customerIFSC;

    @NotBlank(message = "Account Holder Name is required")
    @Size(min = 1, max = 300, message = "Account Holder Name must be between 1 and 300 characters")
    private String accountHolderName;

    @NotBlank(message = "DOB is required")
    private String dob;

    @NotBlank(message = "Gender is required")
    @EnumValue(enumClass = Gender.class, message = "Invalid Gender, must be : {values}")
    private String gender;

    @NotBlank(message = "Insurer Code is required")
    @Size(min = 3, max = 17, message = "Insurer Code must be between 3 and 17 characters")
    private String insurerCode;

    @NotBlank(message = "Bank Code is required")
    @Size(min = 2, max = 30, message = "Bank Code must be between 2 and 30 characters")
    private String bankCode;

    @NotBlank(message = "Branch Code is required")
    @Size(min = 2, max = 30, message = "Branch Code must be between 2 and 30 characters")
    private String branchCode;

    @NotBlank(message = "Bank Branch Email ID is required")
    @Email(message = "Invalid Bank Branch Email ID")
    @Size(min = 5, max = 255, message = "Bank Branch Email ID must be between 5 and 255 characters")
    private String bankBranchEmailId;

    @NotBlank(message = "Mobile Number is required")
    private String mobileNumber;
    
    
    @NotBlank(message = "Email ID is required")
    @Size(min = 5, max = 255, message = "Email ID must be between 5 and 255 characters")
    private String emailId;

    @NotBlank(message = "Address Line 1 is required")
    @Size(min = 2, max = 500, message = "Address Line 1 must be between 2 and 500 characters")
    private String addressLine1;

    @Size(min = 2, max = 500, message = "Address Line 2 must be between 2 and 500 characters")
    private String addressLine2;

    @NotNull(message = "Pincode is required")
    @Min(value = 100000, message = "Pincode must be at least 6 digits.")
    @Max(value = 999999, message = "Pincode must be at most 6 digits.")
    @Digits(integer = 6, fraction = 0, message = "Pincode must contain only digits")
    private Integer pincode;

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 200, message = "City must be between 2 and 200 characters")
    private String city;

    @NotBlank(message = "District is required")
    @Size(min = 2, max = 200, message = "District must be between 2 and 200 characters")
    private String district;

    @NotBlank(message = "State is required")
    @Size(min = 2, max = 200, message = "State must be between 2 and 200 characters")
    private String state;

    @NotBlank(message = "KYC ID 1 is required")
    @EnumValue(enumClass = KycDocType.class, message = "Invalid KYC ID 1, must be : {values}")
    private String kycID1;

    @NotBlank(message = "KYC ID 1 Number is required")
    @Size(min = 1, max = 100, message = "KYC ID 1 Number must be between 1 and 100 characters")
    private String kycID1number;

    @Pattern(regexp = "(YES|NO|Y|N)", message = "Pan must be either YES/Y or NO/N")
    private String pan;
    
    @Size(min = 10, max = 10, message = "Pan Number must be exactly 10 characters long")
    private String panNumber;
    
    @Pattern(regexp = "(YES|NO|Y|N)", message = "Aadhaar must be either YES/Y or NO/N")
    private String aadhaar;
    
    @Size(min = 12, max = 12, message = "Aadhaar Number must be exactly 12 characters long")
    private String aadhaarNumber;
    
    @Pattern(regexp = "(YES|NO|Y|N)", message = "CKYC must be either YES/Y or NO/N")
    private String ckyc;
    
    @Size(min = 14, max = 15, message = "CKYC Number must be between 14 and 15 characters long")
    private String ckycNumber;
    
    @NotBlank(message = "Nominee Name is required")
    @Size(min = 1, max = 300, message = "Nominee Name must be between 1 and 300 characters long")
    private String nomineeName;
    
    @NotBlank(message = "Nominee Date of Birth is required")
    private String nomineeDateOfBirth;
    
    @NotBlank(message = "Nominee Gender is required")
    @Pattern(regexp = "(M|F|T)", message = "Invalid Nominee Gender, it must be M, F, or T")
    private String nomineeGender;    
    
    @NotBlank(message = "Nominee Mobile Number is required")
    @Pattern(regexp = "[6-9][0-9]{9}", message = "Invalid Nominee Mobile Number format")
    private String nomineeMobileNumber;
    
    @NotBlank(message = "Relationship of Nominee is required")
    @Size(min = 1, max = 50, message = "Relationship of Nominee must be between 1 and 50 characters long")
    private String relationshipOfNominee;
    
    @Size(min = 5, max = 255, message = "Email ID must be between 5 and 255 characters long")
    private String nomineeEmailId;    
    
    @Size(min = 2, max = 500, message = "Address of Nominee must be between 2 and 500 characters long")
    private String addressofNominee;    

    @Size(min = 1, max = 300, message = "Correct Nominee Name must be between 1 and 300 characters")
    private String correctNomineeName;

    @Size(min = 1, max = 300, message = "Name of Guardian must be between 1 and 300 characters")
    private String nameofGuardian;

    @Size(min = 2, max = 500, message = "Address of Guardian must be between 2 and 500 characters")
    private String addressOfGuardian;

    @Size(min = 1, max = 50, message = "Relationship of Guardian must be between 1 and 50 characters")
    @EnumValue(enumClass = Guardians.class, message = "Invalid Relationship of Guardian, must be : {values}")
    private String relationShipOfGuardian;

    private String guardianMobileNumber;

    @Email(message = "Invalid Guardian Email ID")
    @Size(min = 5, max = 255, message = "Guardian Email ID must be between 5 and 255 characters")
    private String guardianEmailId;

    @Size(min = 1, max = 300, message = "Claimant Name must be between 1 and 300 characters")
    private String claimantName;

    @Size(min = 2, max = 500, message = "Claimant Address must be between 2 and 500 characters")
    private String claimantAddress;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid Date Format. Please use yyyy-MM-dd")
    private String claimantDateOfBirth;

    @Size(min = 1, max = 50, message = "Relationship of Claimant must be between 1 and 50 characters")
    @EnumValue(enumClass = ClaimantRelationship.class, message = "Invalid Relationship of Claimant, must be : {values}")
    private String relationshipOfClaimant;

    private String claimantMobileNumber;

    @Email(message = "Invalid Claimant Email ID")
    @Size(min = 5, max = 255, message = "Claimant Email ID must be between 5 and 255 characters")
    private String claimantEmailId;

    @Size(min = 1, max = 25, message = "Claimant KYC 1 must be between 1 and 25 characters")
    private String claimantKYC1;

    @Size(min = 1, max = 100, message = "Claimant KYC 1 Number must be between 1 and 100 characters")
    private String claimantKYCNumber1;

    @EnumValue(enumClass = KycDocType.class, message = "Invalid Claimant KYC 2, must be : {values}")
    private String claimantKYC2;

    @Size(min = 1, max = 100, message = "Claimant KYC 2 Number must be between 1 and 100 characters")
    private String claimantKycNumber2;

    @Pattern(regexp = "^(M|F|T)$", message = "Invalid Claimant Gender")
    private String claimantGender;

    private String dateOfAccident;

    private String timeOfAccident;

    @Size(min = 1, max = 25, message = "Day of Accident must be between 1 and 25 characters")
    @EnumValue(enumClass = DaysOfWeek.class, message = "Invalid Day of Accident, must be : {values}")
    private String dayOfAccident;

    private String placeOfOccurence;

    @Size(min = 1, max = 100, message = "Nature of Accident must be between 1 and 100 characters")
    private String natureOfAccident;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", message = "Invalid Date Format. Please use yyyy-MM-dd HH:mm:ss")
    private String dateOfDeath;

    @Size(min = 1, max = 100, message = "Cause of Death must be between 1 and 100 characters")
    private String causeOfDeath;

    @Size(min = 1, max = 100, message = "Cause of Death/Disability must be between 1 and 100 characters")
    private String causeOfDeathDisability;

    @Size(min = 1, max = 100, message = "Type of Disability must be between 1 and 100 characters")
    private String typeOfDisability;

    @Size(min = 3, max = 17, message = "Claimant Bank Account Number must be between 3 and 17 digits")
    @Pattern(regexp = "^\\d+$", message = "Invalid Bank Account Number")
    private String claimantBankAccountNumber;

    @Size(min = 2, max = 50, message = "Claimant Bank Name must be between 2 and 50 characters")
    private String claimantBankName;

    @Size(min = 11, max = 11, message = "Claimant Branch IFSC must be 11 characters long")
    @Pattern(regexp = "^\\w{4}0\\w{6}$", message = "Invalid IFSC Code")
    private String claimantBranchIFSC;

    private String premDebitDate;

    private String premRemitDate;

    private String dateOfLodgingClaim;

    @NotNull(message = "Document List is required")
    private List<Document> documents;    
    
    @EnumValue(enumClass = ClaimStatus.class, message = "Invalid Claim Status")
    private Long claimStatus;
 
    private String insurerStatus;
    
    private String reason;



}
