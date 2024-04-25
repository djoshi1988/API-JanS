package com.bank.cedrus.model.response;

import lombok.Data;

@Data
public class PremiumDeductionResponse {
	
	
	private String transactionUTR;
    private String transactionTimeStamp;
    private Double transactionAmount;
    private String comment;
    private String debitStatus;
    
    
    public PremiumDeductionResponse(String transactionUTR, String transactionTimeStamp, Double transactionAmount,
			String comment) {
		super();
		this.transactionUTR = transactionUTR;
		this.transactionTimeStamp = transactionTimeStamp;
		this.transactionAmount = transactionAmount;
		this.debitStatus="1";
		this.comment = comment;
	}
    
}
