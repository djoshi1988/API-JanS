package com.bank.cedrus.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.cedrus.common.EntityMapper;
import com.bank.cedrus.db.model.Claims;
import com.bank.cedrus.db.model.Documents;
import com.bank.cedrus.model.ClaimDetails;
import com.bank.cedrus.model.ClaimUpdateInput;
import com.bank.cedrus.model.Document;
import com.bank.cedrus.model.NomineeDetails;
import com.bank.cedrus.repository.ClaimsRepository;
import com.bank.cedrus.repository.DocumentsRepository;

@Service
public class ClaimDetailsService {

	 @Autowired
	 private ClaimsRepository claimRepository;

	 @Autowired
	 private DocumentsRepository documentRepository;
	 
	 
	 public boolean existsByClaimReferenceId(long claimReferenceId) {
	        return claimRepository.existsByClaimReferenceId(claimReferenceId);
	    }
	 
	 public Claims findByClaimReferenceId(Long claimReferenceId) {
	        return claimRepository.findByClaimReferenceId(claimReferenceId);
	    }
	 
	 public boolean existsByUrn(String urn) {
	        return claimRepository.existsByUrn(urn);
	    }
	 
	 public Claims findByUrn(String urn) {
	        return claimRepository.findByUrn(urn);
	    }
	 
	 @Transactional
	 public void saveClaimWithDocuments(ClaimDetails claimForm) {		 
		 
		
		Claims claimEntity = EntityMapper.mapModelToEntity(claimForm, Claims.class); 
 
		for (Document document : claimForm.getDocumentList()) {
			Documents documentEntity = EntityMapper.mapModelToEntity(document, Documents.class);
			documentEntity.setClaim_reference_id(claimForm.getClaimReferenceId());
			documentRepository.save(documentEntity);
		}
		
	    claimRepository.save(claimEntity);
		

	    }
	 
	  @Transactional
	  public void updateClaim(ClaimUpdateInput claimUpdateInput) {
		  
	        Long claimReferenceId = claimUpdateInput.getClaimReferenceId();
	        Claims existingClaim = claimRepository.findByClaimReferenceId(claimReferenceId);
	        BeanUtils.copyProperties(claimUpdateInput, existingClaim, "claimReferenceId");
 	        claimRepository.save(existingClaim);	         
	    }
	  
	  @Transactional
	  public void updateNominee(NomineeDetails form) {
		  
	        String urn = form.getUrn();
	        Claims existingClaim = claimRepository.findByUrn(urn);
	        BeanUtils.copyProperties(form, existingClaim, "urn");
 	        claimRepository.save(existingClaim);
	    }
}
