package com.bank.cedrus.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@Table(name = "PMSBY")
public class PMSBY {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FORACID")
    private String foracid;

    @Column(name = "CUST_ID")
    private String custId;

    @Column(name = "POLICY_NO")
    private String policyNo;

    @Column(name = "PREM_AMT")
    private String premAmt;

    @Column(name = "RECEIPT_NO")
    private String receiptNo;

    @Column(name = "RECEIPT_DATE")
    private Date receiptDate;

    @Column(name = "ENTITY_CRE_FLG")
    private String entityCreFlg;

    @Column(name = "DEL_FLG")
    private String delFlg;

    @Column(name = "RCRE_DATE")
    private Date rcreDate;

    @Column(name = "RECRE_USER")
    private String recreUser;

    @Column(name = "LCHG_DATE")
    private Date lchgDate;

    @Column(name = "LCHG_USER")
    private String lchgUser;

    @Column(name = "BC_CODE")
    private String bcCode;

    @Column(name = "MATURITY_DATE")
    private String maturityDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CONSENT_RECV_FLG")
    private String consentRecvFlg;

    @JsonProperty("nomineeName") 
    @Column(name = "NOMINEENAME")
    private String nomineeName;

    @Column(name = "GENDER")
    private String gender;

    @JsonProperty("addressofNominee")
    @Column(name = "NOMADDRESS")
    private String nomAddress;

    @JsonProperty("relationshipOfNominee")
    @Column(name = "NOMRELATION")
    private String nomRelation;

    @JsonProperty("nameofGuardian")
    @Column(name = "GUARDIANNAME")
    private String guardianName;

    @Column(name = "NOMMINOR")
    private String nomMinor;

    @Column(name = "NOMAGE")
    private String nomAge;
}

