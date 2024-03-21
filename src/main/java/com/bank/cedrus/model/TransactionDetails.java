package com.bank.cedrus.model;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TransactionDetails {
    
	private String transactionTimeStamp;

    private String transactionAmount;

    @Size(min = 1, max = 35, message = "Transaction UTR must be between 1 and 35 characters")
    private String transactionUTR;

	

}