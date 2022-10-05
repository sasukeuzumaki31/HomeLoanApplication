package com.group4.demo.service.impl;

import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.service.ILoanAgreementService;
import com.group4.demo.repository.ILoanAgreementRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class ILoanAgreementServiceImpl implements ILoanAgreementService {
    Log logger = LogFactory.getLog(ILoanAgreementServiceImpl.class);

    @Autowired
    ILoanAgreementRepository loanAggRepo;

    @Override
    public List<LoanAgreement> retrieveAllLoanAgreement() {
        logger.info("Entered into retrieveAllLoanAgreement method");
        return loanAggRepo.findAll();

    }

    @Override
    public LoanAgreement retrieveAgreementById(long loanAgreementId) throws ResourceNotFoundException {
        logger.info("Entered into retrieveAgreementById method" + loanAgreementId);

        return loanAggRepo.findById(loanAgreementId).orElseThrow(() -> new ResourceNotFoundException("Loan Agreement does not exists with id : " + loanAgreementId));
    }

    @Override
    public LoanAgreement addLoanAgreement(LoanAgreement loanAgreement) {
        logger.info("Entered into addLoanAgreement method");
        return loanAggRepo.save(loanAgreement);
    }

    @Override
    public LoanAgreement updateLoanAgreement(@Valid LoanAgreement loanAgreement) {
        logger.info("Entered into updateLoanAgreement method");
        return loanAggRepo.save(loanAgreement);
    }
}
