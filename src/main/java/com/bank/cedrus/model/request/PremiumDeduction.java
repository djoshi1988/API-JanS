package com.bank.cedrus.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bank.cedrus.common.Constants;
import com.bank.cedrus.enums.Mode;
import com.bank.cedrus.enums.Scheme;
import com.bank.cedrus.validator.EnumValue;

import lombok.Data;

@Data
public class PremiumDeduction {
	
    @Size(min = 3, max = 17, message = "Bank Account Number must be between 3 and 17 digits")
    @Pattern(regexp = "^\\d+$", message = "Invalid Bank Account Number")
    @NotBlank(message = "Account Number is required")
	private String customerAccountNumber;
    
    @NotBlank(message = "URN is required")
    @Size(min = 31, max = 32, message = "URN must be between 31 and 32 characters")
    private String urn;
    
    @NotBlank(message = "Cif is required")
    @Size(min = 3, max = 17, message = "Cif must be between 3 and 17 digits")
    @Pattern(regexp = "^\\d+$", message = "Cif must contain only digits")
    private String cif;
    
    @NotBlank(message = "Insurer Code is required")
    @Pattern(regexp = "\\d{3,17}", message = "Insurer Code must be between 3 and 17 digits")
    private String insurerCode;


    @NotBlank(message = "Branch Code is required")
    @Size(min = 2, max = 30, message = "Branch Code must be between 2 and 30 characters")
    private String branchCode;
    
    @NotBlank(message = "Premium Amount is required")
    private Double premiumAmount;
    
    @NotBlank(message = "User ID is required")
    @Size(min = 1, max = 50, message = "User ID must be between 1 and 50 characters")
    private String userId;

    @NotBlank(message = "Scheme is required")
    @EnumValue(enumClass = Scheme.class, message = "Scheme must be one of: {values}")
    private String scheme;

    @EnumValue(enumClass = Mode.class, message = "Mode must be one of: {values}")
    private String mode;
    
    @NotBlank(message = "Token is required")
    private String token;
    
    
    public String toFormattedString() {
        return Constants.PMYJAN+"|JANTACC|" + customerAccountNumber + "|" + urn + "|" + cif + "|" + insurerCode + "|" + branchCode + "|" + premiumAmount * 100;
    }
    
    public String toPremiumString() {
        return "PMYJANJANPPAY|" +  urn ;
    }

    public String toUpdateCustString(String account,String rrn) {
        return Constants.PMYJAN+"|JANCUPD|" +  account  + "|" + urn + "|" + scheme + "|" + premiumAmount + "|" + rrn;
    }

}
