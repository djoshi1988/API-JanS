package com.bank.cedrus.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static String PMJBY_POOLING_ACCOUNT;
    public static String PMSBY_POOLING_ACCOUNT;

    @Value("${pmjby.pooling.account}")
    public void setPmjbyPoolingAccount(String pmjbyPoolingAccount) {
        PMJBY_POOLING_ACCOUNT = pmjbyPoolingAccount;
    }

    @Value("${pmsby.pooling.account}")
    public void setPmsbyPoolingAccount(String pmsbyPoolingAccount) {
        PMSBY_POOLING_ACCOUNT = pmsbyPoolingAccount;
    }
    
    public static String PMYJAN= "PMYJAN";    
    
    public static String OTPGeneration= "JANGOTP";
    
    public static String OTPValidation= "JANVOTP";
    
    public static String PhysicalVerification= "JANPVER";
    
    public static String GetCustomerDetails= "JANCDTA";
    
    public static String PremiumDeduction= "JANTACC";
    
    public static String GetAccHolderList= "JANACCHODATA";
    
    public static String OptOutUpdateStatus= "JANOPTOUT";
    
    public static String GetPolicyDetails= "JANPDATA";
    
    public static String NomineeDetails= "JANOMUPSTA";
    
    public static String Enrolment= "JANPUSHENRL";
    
    public static String SubmitClaims= "JANCLMSSUB";
    
    public static String UpdateClaims= "JANCLMSUPD";
    
    public static String GetCOIDetails= "JANCOIDATA";
    
}