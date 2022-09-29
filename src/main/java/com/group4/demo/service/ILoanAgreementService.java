package com.group4.demo.service;

import com.group4.demo.entity.LoanAgreement;

import java.util.List;

public interface ILoanAgreementService {

    LoanAgreement deleteLoanAgreement(long loanAgreementId);

    List<LoanAgreement> retrieveAllLoanAgreement();

    LoanAgreement retrieveAgreementById(long loanAgreementId);

    LoanAgreement addLoanAgreement(LoanAgreement loanAgreement);

    LoanAgreement updateLoanAgreement(LoanAgreement loanAgreement);
}
