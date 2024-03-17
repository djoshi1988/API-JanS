package com.bank.cedrus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.cedrus.model.ClaimDetails;
import com.bank.cedrus.repository.ClaimDetailsRepository;

@Service
public class ClaimDetailsService {


	@Autowired
    private ClaimDetailsRepository claimDetailsRepository;


    @Transactional(rollbackFor = Exception.class)
    public ClaimDetails saveClaimDetails(ClaimDetails claimDetails) {
         return claimDetailsRepository.save(claimDetails);
    }
}
