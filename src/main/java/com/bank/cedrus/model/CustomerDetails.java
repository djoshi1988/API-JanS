package com.bank.cedrus.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import lombok.Data;

@Data
public class CustomerDetails {
    private String cif;
    private String customerIFSC;
    private String accountHolderName;
    private String gender;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fatherHusbandName;
    private String dob;
    private String mobileNumber;
    private String emailAddress;
    private String addressline1;
    private String addressline2;
    private String city;
    private String cityLGDCode;
    private String district;
    private String districtLGDCode;
    private String state;
    private String stateLGDCode;
    private String pincode;
    private String kycID1;
    private String kycID1number;
    private String kycID2;
    private String kycID2number;
    private String pan;
    private String panNumber;
    private String aadhaar;
    private String aadhaarNumber;
    private String ckyc;
    private String ckycNumber;
    private String nomineeFirstName;
    private String nomineeMiddleName;
    private String nomineeLastName;
    private String nomineeDateOfBirth;
    private String nomineeMobileNumber;
    private String relationOfNominee;
    private String nomineeEmailAddress;
    private String nomineeAddressLine1;
    private String nomineeAddressLine2;
    private String nomineeCity;
    private String nomineeCityLGDCode;
    private String nomineeDistrict;
    private String nomineeDistrictLGDCode;
    private String nomineeState;
    private String nomineeStateLGDCode;
    private String nomineePincode;
    private String nameofGuardian;
    private String addressofGuardian;
    private String relationshipofGuardian;
    private String guardianMobileNumber;
    private String guardianEmailId;
    private String consentForautodebit;
    private String ruralUrban;
    
    
    public CustomerDetails setFields(List<String> values) {
        if (values.size() != getClass().getDeclaredFields().length) {
            throw new IllegalArgumentException("Invalid number of values provided");
        }

        int index = 0;
        try {
            for (java.lang.reflect.Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                field.set(this, values.get(index++));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return this;
    }

    
    public  AccountHolder fromCustomerDetails(CustomerDetails customerDetails) {
    	AccountHolder accountHolder = new AccountHolder();
        try {
            BeanUtils.copyProperties(accountHolder, customerDetails);
          } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return accountHolder;
    }


}


@Data
class AccountHolder {
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

