package com.bank.cedrus.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class COI {
    @NotBlank(message = "Document Type is required")
    private String documentType;

    @NotBlank(message = "Content Type is required")
    private String contentType;

    @NotBlank(message = "Document is required")
    private String document;

     
}