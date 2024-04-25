package com.bank.cedrus.iso;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bank.cedrus.enums.Scheme;
import com.bank.cedrus.model.request.PremiumDeduction;
import com.bank.cedrus.common.Constants;

import lombok.Data;




@Data
public class ISORequest implements Serializable {
    private String MTI = "1200";
    private String field3 = "970000";
    private String field4 = "0000000000000000";
    private String field11 = new SimpleDateFormat("yyMMddhhmmss").format(new Date());
    private String field12 = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    private String field17 = new SimpleDateFormat("yyyyMMdd").format(new Date());
    private String field24 = "200";
    private String field32 = "023";
    private String field34 = "IST";
    private String field37 = new SimpleDateFormat("yyMMddhhmmss").format(new Date());
    private String field49 = "INR";
    private String field102 = "023                00000000000000";
    private String field103;
    private String field123 = "PMY";
    private String field125;
    private String field126 = "PSBJAN";
    private String field127;
    
    public ISORequest premiumISORequest(ISORequest isoReq, PremiumDeduction form, String creditAccount)
    {
    	String poolingAccount =""+Scheme.PMJJBY == form.getScheme() ? Constants.PMJBY_POOLING_ACCOUNT : Constants.PMSBY_POOLING_ACCOUNT;
    	isoReq.setField125(form.toPremiumString());
 		isoReq.setField3("400000");
 		isoReq.setField4( String.format("%016d", (int) (form.getPremiumAmount() * 100)));
 		isoReq.setField102("023                " + poolingAccount);
 		isoReq.setField103(" 023                 "+ creditAccount);
 		isoReq.setField126(null);
 		isoReq.setField127("PSBJAN");    	
    	return isoReq;
    }
    
}

