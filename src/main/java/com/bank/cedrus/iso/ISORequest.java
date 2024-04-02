package com.bank.cedrus.iso;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private String field123 = "PMY";
    private String field125;
    private String field126 = "PSBJAN";
}

