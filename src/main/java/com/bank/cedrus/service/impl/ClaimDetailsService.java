package com.bank.cedrus.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.cedrus.model.ClaimDetails;
import com.bank.cedrus.model.ClaimUpdateInput;
import com.bank.cedrus.model.Document;
import com.bank.cedrus.repository.ClaimDetailsRepository;
import com.bank.cedrus.repository.DocumentsRepository;

@Service
public class ClaimDetailsService {

	 @Autowired
	 private ClaimDetailsRepository claimRepository;

	 @Autowired
	 private DocumentsRepository documentRepository;
	 
	 
	 public boolean existsByClaimReferenceId(long claimReferenceId) {
	        return claimRepository.existsByClaimReferenceId(claimReferenceId);
	    }
	 
	 public ClaimDetails findByClaimReferenceId(Long claimReferenceId) {
	        return claimRepository.findByClaimReferenceId(claimReferenceId);
	    }
	 
	 @Transactional
	 public void saveClaimWithDocuments(ClaimDetails claimForm) {		 
		 
		for (Document document : claimForm.getDocumentList()) {
		            document.setClaimDetails(claimForm);
		            documentRepository.save(document);
	 	        }
		
	    claimRepository.save(claimForm);
		

	    }
	 
	  @Transactional
	  public void updateClaim(ClaimUpdateInput claimUpdateInput) {
		  
	        Long claimReferenceId = claimUpdateInput.getClaimReferenceId();
	        ClaimDetails existingClaim = claimRepository.findByClaimReferenceId(claimReferenceId);
	        BeanUtils.copyProperties(claimUpdateInput, existingClaim, "claimReferenceId");
 	        claimRepository.save(existingClaim);	         
	    }
}
