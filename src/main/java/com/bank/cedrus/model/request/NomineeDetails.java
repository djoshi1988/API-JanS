package com.bank.cedrus.model.request;

import java.lang.reflect.Field;
import java.util.StringJoiner;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bank.cedrus.enums.Guardians;
import com.bank.cedrus.validator.EnumValue;

import lombok.Data;

@Data
public class NomineeDetails {	
	
    @NotBlank(message = "Token is required")
    private String token;

    @NotBlank(message = "URN is required")
    @Size(min = 31, max = 32, message = "URN must be between 31 and 32 characters")
    private String urn;	
    
    @NotBlank(message = "Nominee Update Flag is required")
    @Pattern(regexp = "YES|NO|Y|N|Yes|No", message = "Invalid Nominee Update Flag, it must be Yes")
    private String nomineeUpdateFlag;	
	
    @Size(min = 1, max = 300, message = "Nominee Name must be between 1 and 300 characters long")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z .'-<>/]*$", message = "Invalid Nominee Name format")
    private String nomineeName;
    
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid Date of Birth format, it must be in yyyy-MM-dd")
    private String nomineeDateOfBirth;
    
    @Pattern(regexp = "[6-9][0-9]{9}", message = "Invalid Nominee Mobile Number format")
    private String nomineeMobileNumber;
    
    @Size(min = 1, max = 50, message = "Relationship of Nominee must be between 1 and 50 characters long")
    private String relationshipOfNominee;
    
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Invalid Email format")
    @Size(min = 5, max = 255, message = "Email ID must be between 5 and 255 characters long")
    private String nomineeEmailId;    
    
    @Size(min = 2, max = 500, message = "Address of Nominee must be between 2 and 500 characters long")
    private String addressofNominee;    

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
    
    
    public String toFormString() {
        StringJoiner joiner = new StringJoiner("|");
        Class<?> clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
            	 if (!field.getName().equals("token")) {
                     Object value = field.get(this);
                     if (value != null) {
                         joiner.add(value.toString());
                     } else {
                         joiner.add(""); 
                     }
                 }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return joiner.toString();
    }

}
