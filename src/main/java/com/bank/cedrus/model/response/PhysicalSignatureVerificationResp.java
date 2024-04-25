package com.bank.cedrus.model.response;

import java.util.List;

import com.bank.cedrus.model.AccountHolderDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhysicalSignatureVerificationResp {
	
	private List<AccountHolderDetails> accountHolderDetails;
	

}
