package com.bank.cedrus.db.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bank.cedrus.enums.ClaimStatus;
import com.bank.cedrus.enums.ClaimantRelationship;
import com.bank.cedrus.enums.DaysOfWeek;
import com.bank.cedrus.enums.Guardians;
import com.bank.cedrus.enums.KycDocType;
import com.bank.cedrus.enums.Scheme;
import com.bank.cedrus.validator.EnumValue;

import lombok.Data;

@Data
@Entity
@Table(name = "pmjy_claim_details")
public class Claims implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Claim Reference ID is required")
    @Column(name = "claim_reference_id")
    private Long claimReferenceId;

    @NotBlank(message = "URN is required")
    @Size(min = 31, max = 32, message = "URN must be between 31 and 32 characters")
    private String urn;

    @NotBlank(message = "Scheme Name is required")
    @EnumValue(enumClass = Scheme.class, message = "Scheme Name must be one of: {values}")
    private String schemeName;

    @NotBlank(message = "Customer Account Number is required")
    @Size(min = 3, max = 17, message = "Customer Account Number must be between 3 and 17 digits")
    private String customerAccountNumber;

    @NotBlank(message = "DOB is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid DOB format, use yyyy-MM-dd")
    private String dob;
    
    @NotBlank(message = "Nominee Name is required")
    @Size(min = 1, max = 300, message = "Nominee Name must be between 1 and 300 characters long")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z .'-<>/]*$", message = "Invalid Nominee Name format")
    private String nomineeName;
    
    @NotBlank(message = "Nominee Date of Birth is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid Date of Birth format, it must be in yyyy-MM-dd")
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
    
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Invalid Email format")
    @Size(min = 5, max = 255, message = "Email ID must be between 5 and 255 characters long")
    private String nomineeEmailId;    
    
    @Size(min = 2, max = 500, message = "Address of Nominee must be between 2 and 500 characters long")
    private String addressofNominee;    

    @Size(min = 1, max = 300, message = "Correct Nominee Name must be between 1 and 300 characters")
    @Pattern(regexp = "^[a-zA-Z .<'>/,-]*$", message = "Invalid Correct Nominee Name")
    private String correctNomineeName;

    @Size(min = 1, max = 300, message = "Name of Guardian must be between 1 and 300 characters")
    @Pattern(regexp = "^[a-zA-Z .<'>/-]*$", message = "Invalid Name of Guardian")
    private String nameofGuardian;

    @Size(min = 2, max = 500, message = "Address of Guardian must be between 2 and 500 characters")
    private String addressOfGuardian;

    @Size(min = 1, max = 50, message = "Relationship of Guardian must be between 1 and 50 characters")
    @EnumValue(enumClass = Guardians.class, message = "Invalid Relationship of Guardian, must be : {values}")
    private String relationShipOfGuardian;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Guardian Mobile Number")
    private String guardianMobileNumber;

    @Email(message = "Invalid Guardian Email ID")
    @Size(min = 5, max = 255, message = "Guardian Email ID must be between 5 and 255 characters")
    private String guardianEmailId;
    
    @Size(min = 1, max = 300, message = "Claimant Name must be between 1 and 300 characters")
    @Pattern(regexp = "^[a-zA-Z .<'>/-]*$", message = "Invalid Claimant Name")
    private String claimantName;

    @Size(min = 2, max = 500, message = "Claimant Address must be between 2 and 500 characters")
    private String claimantAddress;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid Date Format. Please use yyyy-MM-dd")
    private String claimantDateOfBirth;

    @Size(min = 1, max = 50, message = "Relationship of Claimant must be between 1 and 50 characters")
    @EnumValue(enumClass = ClaimantRelationship.class, message = "Invalid Relationship of Claimant, must be : {values}")
    private String relationshipOfClaimant;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Claimant Mobile Number")
    private String claimantMobileNumber;

    @Email(message = "Invalid Claimant Email ID")
    @Size(min = 5, max = 255, message = "Claimant Email ID must be between 5 and 255 characters")
    private String claimantEmailId;

    @Size(min = 1, max = 25, message = "Claimant KYC 1 must be between 1 and 25 characters")
    @Pattern(regexp = "(?i)^(AADHAR|PAN|VOTERID|DRIVINGL|PASSPORT|MGNREGA)$", message = "Invalid Claimant KYC 1")
    private String claimantKYC1;

    @Size(min = 1, max = 100, message = "Claimant KYC 1 Number must be between 1 and 100 characters")
    private String claimantKYCNumber1;

    @EnumValue(enumClass = KycDocType.class, message = "Invalid Claimant KYC 2, must be : {values}")
    private String claimantKYC2;

    @Size(min = 1, max = 100, message = "Claimant KYC 2 Number must be between 1 and 100 characters")
    private String claimantKycNumber2;

    @Pattern(regexp = "^(M|F|T)$", message = "Invalid Claimant Gender")
    private String claimantGender;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid Date Format. Please use yyyy-MM-dd")
    private String dateOfAccident;

    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$", message = "Invalid Time Format. Please use HH:mm:ss")
    private String timeOfAccident;

    @Size(min = 1, max = 25, message = "Day of Accident must be between 1 and 25 characters")
    @EnumValue(enumClass = DaysOfWeek.class, message = "Invalid Day of Accident, must be : {values}")
    private String dayOfAccident;

    private String placeOfOccurrence;

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
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid Bank Name")
    private String claimantBankName;

    @Size(min = 11, max = 11, message = "Claimant Branch IFSC must be 11 characters long")
    @Pattern(regexp = "^\\w{4}0\\w{6}$", message = "Invalid IFSC Code")
    private String claimantBranchIFSC;

    @NotBlank(message = "Premium Debit Date is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid Date Format. Please use yyyy-MM-dd")
    private String premDebitDate;

    @NotBlank(message = "Premium Remit Date is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid Date Format. Please use yyyy-MM-dd")
    private String premRemitDate;

    @NotBlank(message = "Date of Lodging Claim is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid Date Format. Please use yyyy-MM-dd")
    private String dateOfLodgingClaim; 
    
    @EnumValue(enumClass = ClaimStatus.class, message = "Invalid Claim Status")
    private Long claimStatus;
 
    private String insurerStatus;
    
    private String reason;



}
