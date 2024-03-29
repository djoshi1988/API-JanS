package com.bank.cedrus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.cedrus.model.ClaimDetails;

@Repository
public interface ClaimDetailsRepository extends JpaRepository<ClaimDetails, Long> {
 	boolean existsByClaimReferenceId(long claimReferenceId);
 	ClaimDetails findByClaimReferenceId(Long claimReferenceId);
}