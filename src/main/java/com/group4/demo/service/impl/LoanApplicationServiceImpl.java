package com.group4.demo.service.impl;

import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Status;
import com.group4.demo.repository.ILoanApplicationRepository;
import com.group4.demo.service.ILoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanApplicationServiceImpl implements ILoanApplicationService {
    @Autowired
    ILoanApplicationRepository loanRepo;


    @Override
    public LoanApplication deleteLoanApplicationId(long loanApplicationId) {
        Optional<LoanApplication> application = loanRepo.findById(loanApplicationId);
        if(application.isPresent()) {
            loanRepo.delete(application.get());
            return application.get();
        }
        return null;
    }

    @Override
    public List<LoanApplication> retriveAllLoanApplication() {
        return loanRepo.findAll();
    }

    @Override
    public LoanApplication retriveLoanApplicationById(Long loanApplicationId) {
        Optional<LoanApplication> application = loanRepo.findById(loanApplicationId);
        if(application.isPresent()){
           return application.get();
        }
        return null;
    }

    @Override
    public LoanApplication addLoanApplication(LoanApplication loanApplication) {
        return loanRepo.save(loanApplication);
    }

    @Override
    public LoanApplication updateLoanApplication(LoanApplication loanApplication) {
        Optional<LoanApplication> application = loanRepo.findById(loanApplication.getApplicationId());
        if(application.isPresent()){
            return loanRepo.save(loanApplication);
        }
        return null;
    }

    @Override
    public LoanApplication updateStatusOfLoanApplication(Long loanApplicationId, Status status) {
        Optional<LoanApplication> application = loanRepo.findById(loanApplicationId);
        if(application.isPresent()){
            LoanApplication loanApplication = application.get();
            loanApplication.setStatus(String.valueOf(status));
            return loanRepo.save(loanApplication);
        }
        return null;
    }
}
