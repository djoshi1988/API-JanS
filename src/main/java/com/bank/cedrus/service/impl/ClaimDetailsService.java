package com.bank.cedrus.service.impl;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.bank.cedrus.model.ClaimDetails;
import com.bank.cedrus.repository.ClaimDetailsRepository;

@Service
public class ClaimDetailsService {
    private final ClaimDetailsRepository claimDetailsRepository;

    public ClaimDetailsService(ClaimDetailsRepository claimDetailsRepository) {
        this.claimDetailsRepository = claimDetailsRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public ClaimDetails saveClaimDetails(ClaimDetails claimDetails) {
         return claimDetailsRepository.save(claimDetails);
    }
}
