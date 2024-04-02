package com.bank.cedrus.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bank.cedrus.db.model.COI;

import lombok.Data;

@Data
@Entity
@Table(name = "pmjy_applicant_details")
public class Applicant implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String token;
    private String urn;
    private String policyYear;
    private String insurerCode;
    private String transactionUTR;
    private String transactionTimeStamp;
    private Double transactionAmount;
    private String firstEnrollmentDate;
    private String source;
    private String mode;
    private String transactionType;
    private String masterPolicyNumber;
    private String schemeName;
    private String branchcode;
    private String bankCode;
    private String consentforautodebit;
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
    private Long pincode;
    private String city;
    private String district;
    private String state;
    private String kycID1;
    private String kycID1Number;
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
    private String nomineeEmailAddress;
    private String addressofNominee;
    private String nameofGuardian;
    private String addressofGuardian;
    private String relationshipOfGuardianWithApplicant;
    private String guardianMobileNumber;
    private String guardianEmailId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coi_id")
    private COI coi;

 }

