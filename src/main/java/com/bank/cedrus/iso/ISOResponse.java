package com.bank.cedrus.iso;

import java.util.List;

import lombok.Data;

@Data
public class ISOResponse {
    private  List<String> data;
    private  String responseCode;
    

    public String getValueAtIndex(int index) {
        if (data != null && index >= 0 && index < data.size()) {
            return data.get(index);
        } else {
            return null; 
        }
    }
    
    public String getResponseCode() {
        return responseCode;
    }
    
    public void setResponseCode(String code) {
    	this.responseCode = (code.equals("000")) ? "Ok" : "Error";
    }
}