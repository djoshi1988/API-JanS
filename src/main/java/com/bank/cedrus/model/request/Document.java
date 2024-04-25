package com.bank.cedrus.model.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.bank.cedrus.enums.DocumentType;
import com.bank.cedrus.validator.EnumValue;

import lombok.Data;

@Data
public class Document {
	
	
    @NotBlank(message = "Document Type is required")
    private String documentType;

    @NotBlank(message = "Content Type is required")
    @Pattern(regexp = "^(pdf)$", message = "Content Type must be pdf")
    private String contentType;

    @NotNull(message = "Document Id is required")
    @EnumValue(enumClass = DocumentType.class, message = "Invalid document type")
    private Long documentId;

    @NotBlank(message = "Document byte is required")     
    private String document;
    

 }
