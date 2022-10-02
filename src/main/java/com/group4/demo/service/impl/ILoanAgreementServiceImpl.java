package com.group4.demo.service.impl;

import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.service.ILoanAgreementService;
import com.group4.demo.repository.ILoanAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class ILoanAgreementServiceImpl implements ILoanAgreementService {

    @Autowired
    ILoanAgreementRepository loanAggRepo;

    @Override
    public LoanAgreement deleteLoanAgreement(long loanAgreementId) throws ResourceNotFoundException {
        LoanAgreement agreement=loanAggRepo.findById(loanAgreementId).orElseThrow(()->new ResourceNotFoundException("Loan Agreement does not exists with id : "+loanAgreementId));
            loanAggRepo.delete(agreement);
            return agreement;
    }

    @Override
    public List<LoanAgreement> retrieveAllLoanAgreement() {
        return loanAggRepo.findAll();
    }

    @Override
    public LoanAgreement retrieveAgreementById(long loanAgreementId) throws ResourceNotFoundException{
        return loanAggRepo.findById(loanAgreementId).orElseThrow(()->new ResourceNotFoundException("Loan Agreement does not exists with id : "+loanAgreementId));
    }

    @Override
    public LoanAgreement addLoanAgreement(LoanAgreement loanAgreement) {
        return loanAggRepo.save(loanAgreement);
    }

    @Override
    public LoanAgreement updateLoanAgreement(@Valid LoanAgreement loanAgreement) {
        return loanAggRepo.save(loanAgreement);
    }
}
