package com.bank.cedrus.model.response;

import com.bank.cedrus.model.AccountHolder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDetailsResp {
	
	private AccountHolder accountHolderDetails;
	

}
