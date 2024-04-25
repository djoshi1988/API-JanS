package com.bank.cedrus.model.response;

import java.util.List;

import com.bank.cedrus.model.GetAccountHolderList;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountHolderListResp {
	
	private List<GetAccountHolderList> accountHolderList;
	

}
