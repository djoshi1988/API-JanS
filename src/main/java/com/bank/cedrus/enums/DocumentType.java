package com.bank.cedrus.enums;

public enum DocumentType {
    SIGNED_AND_DULLY_FILLED_CLAIM_CUM_DISCHARGE_FORM(4),
    DEATH_CERTIFICATE(5),
    HOSPITAL_DISCHARGE_SUMMARY(6),
    CERTIFICATE_BY_REGISTERED_MEDICAL_PRACTITIONER(7),
    CERTIFICATE_BY_DISTRICT_MAGISTRATE(8),
    FIR_PANCHNAMA(9),
    HOSPITAL_RECORDS_WITH_DECEASED_DETAILS(10),
    KYC_OF_INSURED_MEMBER(11),
    KYC_OF_NOMINEE_OR_CLAIMANT(12),
    PASSBOOK_OR_CANCELLED_CHEQUE_OF_NOMINEE(13),
    NOMINEE_DEATH_CERTIFICATE(14),
    LEGAL_HEIR_CERTIFICATE(15),
    OTHERS(16),
    DISABILITY_CERTIFICATE_BY_CIVIL_SURGEON(17),
    HOSPITAL_RECORDS_SUPPORTING_DISABILITY(18),
    CHECKLIST(19),
    COPY_OF_PASSBOOK_OF_INSURED(20),
    POST_MORTEM_REPORT(21),
    AADHAR_OR_PAN_OF_NOMINEE_OR_CLAIMANT(22),
    AADHAR_OR_PAN_OF_INSURED_MEMBER(23);

    private final int value;

    DocumentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
