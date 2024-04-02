package com.bank.cedrus.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.bank.cedrus.enums.DocumentType;
import com.bank.cedrus.validator.EnumValue;

import lombok.Data;

@Data
@Entity
@Table(name = "pmjy_claim_documents")
public class Documents {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long claim_reference_id;

    @NotBlank(message = "Document Type is required")
    private String documentType;

    @NotBlank(message = "Content Type is required")
    @Pattern(regexp = "^(pdf)$", message = "Content Type must be pdf")
    private String contentType;

    @NotNull(message = "Document Id is required")
    @EnumValue(enumClass = DocumentType.class, message = "Invalid document type")
    private Long documentId;

    @NotBlank(message = "Document byte is required")
    @Lob
    private String document;
    

 }