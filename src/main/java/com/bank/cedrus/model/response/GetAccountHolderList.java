package com.bank.cedrus.model.response;

import lombok.Data;

@Data
public class GetAccountHolderList {
	
	  private String accountNumber;
	
	  private String accountHolderName;
	  
	  private String cif;
	  
	  private String urn;
	  
	  private String policyInceptionDate;
	  
	  private String scheme;

}
