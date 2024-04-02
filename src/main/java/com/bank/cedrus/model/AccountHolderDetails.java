package com.bank.cedrus.model;

import java.util.List;

import lombok.Data;

@Data
public class AccountHolderDetails {
	
	  private String accountHolderName;
	  
	  private String cif;
	  
	  private String dob;
	  
	  private String gender;
	  
	  private String PMJJBYexists;
	  
	  private String PMSBYexists;
	  
	  private String KYCUpdated;
	  
	  public AccountHolderDetails setFields(List<String> values) {
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

}
