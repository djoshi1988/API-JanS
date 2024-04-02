package com.bank.cedrus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.cedrus.db.model.Claims;

@Repository
public interface ClaimsRepository extends JpaRepository<Claims, Long> {
 	boolean existsByClaimReferenceId(long claimReferenceId);
 	Claims findByClaimReferenceId(Long claimReferenceId);
 	boolean existsByUrn(String urn);
 	Claims findByUrn(String urn);
}