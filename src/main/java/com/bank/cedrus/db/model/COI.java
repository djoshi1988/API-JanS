package com.bank.cedrus.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bank.cedrus.model.Applicant;

import lombok.Data;

@Data
@Entity
@Table(name = "pmjy_applicant_coi_details")
public class COI {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String documentType;
    private String contentType;
    private String document;
    @OneToOne(mappedBy = "coi")
    private Applicant applicant;

 }