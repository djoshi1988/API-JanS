package com.bank.cedrus.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.cedrus.model.request.Applicant;
import com.bank.cedrus.repository.ApplicantRepository;

@Service
public class ApplicantService {

	@Autowired
	private ApplicantRepository applicantRepository;

	public boolean existsByUrn(String urn) {
		return applicantRepository.existsByUrn(urn);
	}

	public Applicant findByUrn(String urn) {
		return applicantRepository.findByUrn(urn);
	}

	@Transactional
	public void saveApplicantWithCOI(Applicant applicant) {
		applicant.getCoi().setApplicant(applicant);
		applicantRepository.save(applicant);
	}

	@Transactional
	public void updateApplicantDetails(Applicant applicant) {

		String urn = applicant.getUrn();
		Applicant existingApplicantDetails = applicantRepository.findByUrn(urn);
		BeanUtils.copyProperties(applicant, existingApplicantDetails, "urn");
		applicantRepository.save(existingApplicantDetails);
	}
}
