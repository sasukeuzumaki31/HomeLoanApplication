package com.group4.demo.service.impl;

import com.group4.demo.entity.LoanApplication;
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
        loanRepo.delete(application.get());
        return application.get();
    }

    @Override
    public List<LoanApplication> retriveAllLoanApplication() {
        return loanRepo.findAll();
    }

    @Override
    public LoanApplication retriveLoanApplicationById(long loanApplicationId) {
        return loanRepo.findById(loanApplicationId).get();
    }

    @Override
    public LoanApplication addLoanApplication(LoanApplication loanApplication) {
        return loanRepo.save(loanApplication);
    }

    @Override
    public LoanApplication updateLoanApplication(LoanApplication loanApplication) {
        LoanApplication application = loanRepo.findById(loanApplication.getApplicationId()).get();
        application.setApplicationDate(loanApplication.getApplicationDate());
        application.setAdminApproval(loanApplication.isAdminApproval());
        application.setLoanAppliedAmount(loanApplication.getLoanAppliedAmount());
        application.setLoanApprovedAmount(loanApplication.getLoanApprovedAmount());
        application.setFinanceVerificationApproval(loanApplication.isFinanceVerificationApproval());
        application.setLandVerificationApproval(loanApplication.isLandVerificationApproval());
        application.setStatus(loanApplication.getStatus());
        return loanRepo.save(application);
    }
}
