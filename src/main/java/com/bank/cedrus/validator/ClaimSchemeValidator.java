package com.bank.cedrus.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bank.cedrus.enums.Scheme;
import com.bank.cedrus.model.request.ClaimDetails;

public class ClaimSchemeValidator implements ConstraintValidator<ClaimSchemeValidation, ClaimDetails> {

    @Override
    public void initialize(ClaimSchemeValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClaimDetails claimForm, ConstraintValidatorContext context) {
    	
         if (Scheme.PMSBY.equals(claimForm.getSchemeName())) {
            if (claimForm.getTypeOfDisability() == null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Type of disability is mandatory for PMSBY").addConstraintViolation();
                return false;
            }
        }

         if (claimForm.getNomineeDateOfBirth() != null) {
            LocalDate dob = LocalDate.parse(claimForm.getNomineeDateOfBirth());
            LocalDate today = LocalDate.now();
            int age = today.minusYears(dob.getYear()).getYear();
            if (age < 18) {
                 if (claimForm.getNameofGuardian() == null || claimForm.getAddressOfGuardian() == null || claimForm.getRelationShipOfGuardian() == null || claimForm.getGuardianMobileNumber() == null) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("Guardian information is required for nominees under 18 years old").addConstraintViolation();
                    return false;
                }
            }
        }         
         
         if (claimForm.getClaimantName() != null ||
                 claimForm.getClaimantDateOfBirth() != null ||
                 claimForm.getClaimantGender() != null ||
                 claimForm.getClaimantMobileNumber() != null ||
                 claimForm.getClaimantAddress() != null ||
                 claimForm.getClaimantKYC1() != null ||
                 claimForm.getClaimantKYCNumber1() != null ||
                 claimForm.getClaimantGender() != null ||
                 claimForm.getClaimantBankAccountNumber()  != null ||
                 claimForm.getClaimantBankName()  != null ||
                 claimForm.getClaimantBranchIFSC()  != null 
                 ) {
             // If any claimant detail is not null, then ensure all claimant details are not null
             if(! (claimForm.getClaimantName() != null &&
                     claimForm.getClaimantDateOfBirth() != null &&
                     claimForm.getClaimantGender() != null &&
                     claimForm.getClaimantMobileNumber() != null &&
                     claimForm.getClaimantAddress() != null &&
                     claimForm.getClaimantKYC1() != null &&
                     claimForm.getClaimantKYCNumber1() != null &&
                     claimForm.getClaimantGender() != null) &&
            		 claimForm.getClaimantBankAccountNumber()  != null &&
                     claimForm.getClaimantBankName()  != null &&
                     claimForm.getClaimantBranchIFSC()  != null )
             {
            	 context.disableDefaultConstraintViolation();
                 context.buildConstraintViolationWithTemplate("Claimant information is required if no nomination or the nominee is pre-deceasing").addConstraintViolation();
                 return false;
            	 
             }
         } 
         
         
         if (Scheme.PMSBY.equals(claimForm.getSchemeName())) {
             if (claimForm.getDateOfAccident() == null) {
                 context.disableDefaultConstraintViolation();
                 context.buildConstraintViolationWithTemplate("Date Of Accident is mandatory for PMSBY").addConstraintViolation();
                 return false;
             }
         }
         
         if (Scheme.PMSBY.equals(claimForm.getSchemeName())) {
             if (claimForm.getTimeOfAccident() == null) {
                 context.disableDefaultConstraintViolation();
                 context.buildConstraintViolationWithTemplate("Time Of Accident is mandatory for PMSBY").addConstraintViolation();
                 return false;
             }
         }
         
         if (Scheme.PMSBY.equals(claimForm.getSchemeName())) {
             if (claimForm.getDayOfAccident() == null) {
                 context.disableDefaultConstraintViolation();
                 context.buildConstraintViolationWithTemplate("Day Of Accident is mandatory for PMSBY").addConstraintViolation();
                 return false;
             }
         }
         
         if (Scheme.PMSBY.equals(claimForm.getSchemeName())) {
             if (claimForm.getPlaceOfOccurrence() == null) {
                 context.disableDefaultConstraintViolation();
                 context.buildConstraintViolationWithTemplate("Place Of Occurrence is mandatory for PMSBY").addConstraintViolation();
                 return false;
             }
         }
         
         if (Scheme.PMSBY.equals(claimForm.getSchemeName())) {
             if (claimForm.getNatureOfAccident() == null) {
                 context.disableDefaultConstraintViolation();
                 context.buildConstraintViolationWithTemplate("Nature Of Accident is mandatory for PMSBY").addConstraintViolation();
                 return false;
             }
         }
         
         if (Scheme.PMSBY.equals(claimForm.getSchemeName())) {
             if (claimForm.getCauseOfDeathDisability() == null) {
                 context.disableDefaultConstraintViolation();
                 context.buildConstraintViolationWithTemplate("Cause Of Death Disability is mandatory for PMSBY").addConstraintViolation();
                 return false;
             }
         }
         
         if (Scheme.PMSBY.equals(claimForm.getSchemeName())) {
             if (claimForm.getTypeOfDisability() == null) {
                 context.disableDefaultConstraintViolation();
                 context.buildConstraintViolationWithTemplate("Type Of Disability is mandatory for PMSBY").addConstraintViolation();
                 return false;
             }
         }
         
         if (Scheme.PMJJBY.equals(claimForm.getSchemeName())) {
             if (claimForm.getCauseOfDeath() == null) {
                 context.disableDefaultConstraintViolation();
                 context.buildConstraintViolationWithTemplate("Cause of Death is mandatory for PMJJBY").addConstraintViolation();
                 return false;
             }
         }
         
         if (Scheme.PMJJBY.equals(claimForm.getSchemeName())) {
             if (claimForm.getDateOfDeath() == null) {
                 context.disableDefaultConstraintViolation();
                 context.buildConstraintViolationWithTemplate("Date of Death is mandatory for PMJJBY").addConstraintViolation();
                 return false;
             }
         }
         
         
         
        // Validation passes
        return true;
    }
    
    
   
}

