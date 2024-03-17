package com.bank.cedrus.model;


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

@Entity
@Table(name = "pmjy_claim_documents")
public class Document {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "claimReferenceId", referencedColumnName = "claimReferenceId")
    private ClaimDetails claimDetails;

    @NotBlank(message = "Document Type is required")
    private String documentType;

    @NotBlank(message = "Content Type is required")
    @Pattern(regexp = "^(pdf)$", message = "Content Type must be pdf")
    private String contentType;

    @NotNull(message = "Document Id is required")
    private Long documentId;

    @NotBlank(message = "Document byte is required")
    @Lob
    private String document;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
    
    

 }
