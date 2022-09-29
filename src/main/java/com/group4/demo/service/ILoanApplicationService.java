package com.group4.demo.service;

import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Status;

import java.util.List;


public interface ILoanApplicationService {
    LoanApplication deleteLoanApplicationId(long loanApplicationId);
    List<LoanApplication> retriveAllLoanApplication();
    LoanApplication retriveLoanApplicationById(Long loanApplicationId);
    LoanApplication addLoanApplication(LoanApplication loanApplication);
    LoanApplication updateLoanApplication(LoanApplication loanApplication);
    LoanApplication updateStatusOfLoanApplication(Long loanApplicationId, Status status);
    List<LoanApplication> retrieveLoanApplicationByStatus(String status);

}
