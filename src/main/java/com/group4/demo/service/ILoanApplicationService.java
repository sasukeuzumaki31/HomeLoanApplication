package com.group4.demo.service;

import com.group4.demo.Dto.LoanApplicationDto;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Status;

import java.util.List;


public interface ILoanApplicationService {
    LoanApplication deleteLoanApplicationId(long loanApplicationId);
    List<LoanApplication> retrieveAllLoanApplication();
    LoanApplication retrieveLoanApplicationById(Long loanApplicationId);
    LoanApplication addLoanApplication(LoanApplicationDto loanApplication);
    LoanApplication updateLoanApplication(long id);
    LoanApplication updateStatusOfLoanApplication(Long loanApplicationId, Status status);
    List<LoanApplication> retrieveLoanApplicationByStatus(String status);

}
