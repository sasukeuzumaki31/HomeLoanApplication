package com.group4.demo.service.impl;

import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.service.ILoanAgreementService;
import com.group4.demo.repository.ILoanAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ILoanAgreementServiceImpl implements ILoanAgreementService {

    @Autowired
    ILoanAgreementRepository loanAggRepo;

    @Override
    public LoanAgreement deleteLoanAgreement(long loanAgreementId) {
        Optional<LoanAgreement> agreement=loanAggRepo.findById(loanAgreementId);
        loanAggRepo.delete(agreement.get());
        return agreement.get();
    }

    @Override
    public List<LoanAgreement> retrieveAllLoanAgreement() {
        return loanAggRepo.findAll();
    }

    @Override
    public LoanAgreement retrieveAgreementById(long loanAgreementId) {
        Optional<LoanAgreement> agreement=loanAggRepo.findById(loanAgreementId);
        return agreement.get();
    }

    @Override
    public LoanAgreement addLoanAgreement(LoanAgreement loanAgreement) {
        return loanAggRepo.save(loanAgreement);
    }

    @Override
    public LoanAgreement updateLoanAgreement(LoanAgreement loanAgreement) {
        return loanAggRepo.save(loanAgreement);
    }
}
