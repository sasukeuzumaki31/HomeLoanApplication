package com.group4.demo.service;

import com.group4.demo.entity.LoanApplication;

import java.util.List;


public interface ILoanApplicationService {
    LoanApplication deleteLoanApplicationId(long loanApplicationId);
    List<LoanApplication> retriveAllLoanApplication();
    LoanApplication retriveLoanApplicationById(long loanApplicationId);
    LoanApplication addLoanApplication(LoanApplication loanApplication);
    LoanApplication updateLoanApplication(LoanApplication loanApplication);

}
