package com.bank.cedrus.model;

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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bank.cedrus.enums.ClaimantRelationship;
import com.bank.cedrus.enums.DaysOfWeek;
import com.bank.cedrus.enums.Gender;
import com.bank.cedrus.enums.Guardians;
import com.bank.cedrus.enums.KycDocType;
import com.bank.cedrus.enums.Scheme;
import com.bank.cedrus.validator.EnumValue;

@Entity
@Table(name = "pmjy_claim_details")
public class ClaimDetails implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Claim Reference ID is required")
    @Column(name = "claim_reference_id")
    private Long claimReferenceId;

    @NotNull(message = "Master Policy Number is required")
    private Long masterPolicyNumber;

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
    @Pattern(regexp = "^\\w{4}0\\w{6}$", message = "Customer IFSC must be 11 digits with '0' as the 5th character")
    private String customerIFSC;

    @NotBlank(message = "Account Holder Name is required")
    @Size(min = 1, max = 300, message = "Account Holder Name must be between 1 and 300 characters")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z .,:'-]*$", message = "Invalid Account Holder Name")
    private String accountHolderName;

    @NotBlank(message = "DOB is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid DOB format, use yyyy-MM-dd")
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
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Mobile Number")
    private String mobileNumber;
    
    
    @NotBlank(message = "Email ID is required")
    @Email(message = "Invalid Email ID")
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
    @Pattern(regexp = "^[a-zA-Z0-9&.,()\"'-]*$", message = "Invalid City")
    private String city;

    @NotBlank(message = "District is required")
    @Size(min = 2, max = 200, message = "District must be between 2 and 200 characters")
    @Pattern(regexp = "^[a-zA-Z0-9&.,()\"'-]*$", message = "Invalid District")
    private String district;

    @NotBlank(message = "State is required")
    @Size(min = 2, max = 200, message = "State must be between 2 and 200 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid State")
    private String state;

    @NotBlank(message = "KYC ID 1 is required")
    @Size(min = 1, max = 25, message = "KYC ID 1 must be between 1 and 25 characters")
    @EnumValue(enumClass = KycDocType.class, message = "Invalid KYC ID 1, must be : {values}")
    private String kycID1;

    @NotBlank(message = "KYC ID 1 Number is required")
    @Size(min = 1, max = 100, message = "KYC ID 1 Number must be between 1 and 100 characters")
    private String kycID1number;

    @Pattern(regexp = "(YES|NO|Y|N)", message = "Pan must be either YES/Y or NO/N")
    private String pan;
    
    @Size(min = 10, max = 10, message = "Pan Number must be exactly 10 characters long")
    @Pattern(regexp = "[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}", message = "Invalid Pan Number format")
    private String panNumber;
    
    @Pattern(regexp = "(YES|NO|Y|N)", message = "Aadhaar must be either YES/Y or NO/N")
    private String aadhaar;
    
    @Size(min = 12, max = 12, message = "Aadhaar Number must be exactly 12 characters long")
    @Pattern(regexp = "[0-9]+", message = "Aadhaar Number must contain only digits")
    private String aadhaarNumber;
    
    @Pattern(regexp = "(YES|NO|Y|N)", message = "CKYC must be either YES/Y or NO/N")
    private String ckyc;
    
    @Size(min = 14, max = 15, message = "CKYC Number must be between 14 and 15 characters long")
    @Pattern(regexp = "[0-9]+", message = "CKYC Number must contain only digits")
    private String ckycNumber;
    
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

    @NotNull(message = "Document List is required")
    @OneToMany(mappedBy = "claimDetails", cascade = CascadeType.ALL)
    private List<Document> documentList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClaimReferenceId() {
		return claimReferenceId;
	}

	public void setClaimReferenceId(Long claimReferenceId) {
		this.claimReferenceId = claimReferenceId;
	}

	public Long getMasterPolicyNumber() {
		return masterPolicyNumber;
	}

	public void setMasterPolicyNumber(Long masterPolicyNumber) {
		this.masterPolicyNumber = masterPolicyNumber;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUrn() {
		return urn;
	}

	public void setUrn(String urn) {
		this.urn = urn;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}

	public String getCustomerBankName() {
		return customerBankName;
	}

	public void setCustomerBankName(String customerBankName) {
		this.customerBankName = customerBankName;
	}

	public String getCustomerIFSC() {
		return customerIFSC;
	}

	public void setCustomerIFSC(String customerIFSC) {
		this.customerIFSC = customerIFSC;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getInsurerCode() {
		return insurerCode;
	}

	public void setInsurerCode(String insurerCode) {
		this.insurerCode = insurerCode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBankBranchEmailId() {
		return bankBranchEmailId;
	}

	public void setBankBranchEmailId(String bankBranchEmailId) {
		this.bankBranchEmailId = bankBranchEmailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getKycID1() {
		return kycID1;
	}

	public void setKycID1(String kycID1) {
		this.kycID1 = kycID1;
	}

	public String getKycID1number() {
		return kycID1number;
	}

	public void setKycID1number(String kycID1number) {
		this.kycID1number = kycID1number;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getCkyc() {
		return ckyc;
	}

	public void setCkyc(String ckyc) {
		this.ckyc = ckyc;
	}

	public String getCkycNumber() {
		return ckycNumber;
	}

	public void setCkycNumber(String ckycNumber) {
		this.ckycNumber = ckycNumber;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getNomineeDateOfBirth() {
		return nomineeDateOfBirth;
	}

	public void setNomineeDateOfBirth(String nomineeDateOfBirth) {
		this.nomineeDateOfBirth = nomineeDateOfBirth;
	}

	public String getNomineeGender() {
		return nomineeGender;
	}

	public void setNomineeGender(String nomineeGender) {
		this.nomineeGender = nomineeGender;
	}

	public String getNomineeMobileNumber() {
		return nomineeMobileNumber;
	}

	public void setNomineeMobileNumber(String nomineeMobileNumber) {
		this.nomineeMobileNumber = nomineeMobileNumber;
	}

	public String getRelationshipOfNominee() {
		return relationshipOfNominee;
	}

	public void setRelationshipOfNominee(String relationshipOfNominee) {
		this.relationshipOfNominee = relationshipOfNominee;
	}

	public String getNomineeEmailId() {
		return nomineeEmailId;
	}

	public void setNomineeEmailId(String nomineeEmailId) {
		this.nomineeEmailId = nomineeEmailId;
	}

	public String getAddressofNominee() {
		return addressofNominee;
	}

	public void setAddressofNominee(String addressofNominee) {
		this.addressofNominee = addressofNominee;
	}

	public String getCorrectNomineeName() {
		return correctNomineeName;
	}

	public void setCorrectNomineeName(String correctNomineeName) {
		this.correctNomineeName = correctNomineeName;
	}

	public String getNameofGuardian() {
		return nameofGuardian;
	}

	public void setNameofGuardian(String nameofGuardian) {
		this.nameofGuardian = nameofGuardian;
	}

	public String getAddressOfGuardian() {
		return addressOfGuardian;
	}

	public void setAddressOfGuardian(String addressOfGuardian) {
		this.addressOfGuardian = addressOfGuardian;
	}

	public String getRelationShipOfGuardian() {
		return relationShipOfGuardian;
	}

	public void setRelationShipOfGuardian(String relationShipOfGuardian) {
		this.relationShipOfGuardian = relationShipOfGuardian;
	}

	public String getGuardianMobileNumber() {
		return guardianMobileNumber;
	}

	public void setGuardianMobileNumber(String guardianMobileNumber) {
		this.guardianMobileNumber = guardianMobileNumber;
	}

	public String getGuardianEmailId() {
		return guardianEmailId;
	}

	public void setGuardianEmailId(String guardianEmailId) {
		this.guardianEmailId = guardianEmailId;
	}

	public String getClaimantName() {
		return claimantName;
	}

	public void setClaimantName(String claimantName) {
		this.claimantName = claimantName;
	}

	public String getClaimantAddress() {
		return claimantAddress;
	}

	public void setClaimantAddress(String claimantAddress) {
		this.claimantAddress = claimantAddress;
	}

	public String getClaimantDateOfBirth() {
		return claimantDateOfBirth;
	}

	public void setClaimantDateOfBirth(String claimantDateOfBirth) {
		this.claimantDateOfBirth = claimantDateOfBirth;
	}

	public String getRelationshipOfClaimant() {
		return relationshipOfClaimant;
	}

	public void setRelationshipOfClaimant(String relationshipOfClaimant) {
		this.relationshipOfClaimant = relationshipOfClaimant;
	}

	public String getClaimantMobileNumber() {
		return claimantMobileNumber;
	}

	public void setClaimantMobileNumber(String claimantMobileNumber) {
		this.claimantMobileNumber = claimantMobileNumber;
	}

	public String getClaimantEmailId() {
		return claimantEmailId;
	}

	public void setClaimantEmailId(String claimantEmailId) {
		this.claimantEmailId = claimantEmailId;
	}

	public String getClaimantKYC1() {
		return claimantKYC1;
	}

	public void setClaimantKYC1(String claimantKYC1) {
		this.claimantKYC1 = claimantKYC1;
	}

	public String getClaimantKYCNumber1() {
		return claimantKYCNumber1;
	}

	public void setClaimantKYCNumber1(String claimantKYCNumber1) {
		this.claimantKYCNumber1 = claimantKYCNumber1;
	}

	public String getClaimantKYC2() {
		return claimantKYC2;
	}

	public void setClaimantKYC2(String claimantKYC2) {
		this.claimantKYC2 = claimantKYC2;
	}

	public String getClaimantKycNumber2() {
		return claimantKycNumber2;
	}

	public void setClaimantKycNumber2(String claimantKycNumber2) {
		this.claimantKycNumber2 = claimantKycNumber2;
	}

	public String getClaimantGender() {
		return claimantGender;
	}

	public void setClaimantGender(String claimantGender) {
		this.claimantGender = claimantGender;
	}

	public String getDateOfAccident() {
		return dateOfAccident;
	}

	public void setDateOfAccident(String dateOfAccident) {
		this.dateOfAccident = dateOfAccident;
	}

	public String getTimeOfAccident() {
		return timeOfAccident;
	}

	public void setTimeOfAccident(String timeOfAccident) {
		this.timeOfAccident = timeOfAccident;
	}

	public String getDayOfAccident() {
		return dayOfAccident;
	}

	public void setDayOfAccident(String dayOfAccident) {
		this.dayOfAccident = dayOfAccident;
	}

	public String getPlaceOfOccurrence() {
		return placeOfOccurrence;
	}

	public void setPlaceOfOccurrence(String placeOfOccurrence) {
		this.placeOfOccurrence = placeOfOccurrence;
	}

	public String getNatureOfAccident() {
		return natureOfAccident;
	}

	public void setNatureOfAccident(String natureOfAccident) {
		this.natureOfAccident = natureOfAccident;
	}

	public String getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public String getCauseOfDeath() {
		return causeOfDeath;
	}

	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}

	public String getCauseOfDeathDisability() {
		return causeOfDeathDisability;
	}

	public void setCauseOfDeathDisability(String causeOfDeathDisability) {
		this.causeOfDeathDisability = causeOfDeathDisability;
	}

	public String getTypeOfDisability() {
		return typeOfDisability;
	}

	public void setTypeOfDisability(String typeOfDisability) {
		this.typeOfDisability = typeOfDisability;
	}

	public String getClaimantBankAccountNumber() {
		return claimantBankAccountNumber;
	}

	public void setClaimantBankAccountNumber(String claimantBankAccountNumber) {
		this.claimantBankAccountNumber = claimantBankAccountNumber;
	}

	public String getClaimantBankName() {
		return claimantBankName;
	}

	public void setClaimantBankName(String claimantBankName) {
		this.claimantBankName = claimantBankName;
	}

	public String getClaimantBranchIFSC() {
		return claimantBranchIFSC;
	}

	public void setClaimantBranchIFSC(String claimantBranchIFSC) {
		this.claimantBranchIFSC = claimantBranchIFSC;
	}

	public String getPremDebitDate() {
		return premDebitDate;
	}

	public void setPremDebitDate(String premDebitDate) {
		this.premDebitDate = premDebitDate;
	}

	public String getPremRemitDate() {
		return premRemitDate;
	}

	public void setPremRemitDate(String premRemitDate) {
		this.premRemitDate = premRemitDate;
	}

	public String getDateOfLodgingClaim() {
		return dateOfLodgingClaim;
	}

	public void setDateOfLodgingClaim(String dateOfLodgingClaim) {
		this.dateOfLodgingClaim = dateOfLodgingClaim;
	}

	public List<Document> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}
    
    

}
