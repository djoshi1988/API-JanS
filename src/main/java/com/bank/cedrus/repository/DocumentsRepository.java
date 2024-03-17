package com.bank.cedrus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.cedrus.model.Document;

@Repository
public interface DocumentsRepository extends JpaRepository<Document, Long> {
    // Additional query methods can be defined here if needed
}