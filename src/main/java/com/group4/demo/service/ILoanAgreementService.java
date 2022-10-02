package com.group4.demo.service;

import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.LoanAgreement;

import java.util.List;

public interface ILoanAgreementService {

    LoanAgreement deleteLoanAgreement(long loanAgreementId) throws ResourceNotFoundException;

    List<LoanAgreement> retrieveAllLoanAgreement();

    LoanAgreement retrieveAgreementById(long loanAgreementId) throws ResourceNotFoundException;

    LoanAgreement addLoanAgreement(LoanAgreement loanAgreement);

    LoanAgreement updateLoanAgreement(LoanAgreement loanAgreement);
}
