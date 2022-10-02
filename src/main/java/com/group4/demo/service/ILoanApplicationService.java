package com.group4.demo.service;

import com.group4.demo.Dto.LoanApplicationDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Status;

import java.util.List;


public interface ILoanApplicationService {
    LoanApplication deleteLoanApplicationId(long loanApplicationId) throws ResourceNotFoundException;
    List<LoanApplication> retrieveAllLoanApplication();
    LoanApplication retrieveLoanApplicationById(Long loanApplicationId) throws ResourceNotFoundException;
    LoanApplication addLoanApplication(LoanApplicationDto loanApplication) throws ResourceNotFoundException;
    LoanApplication updateLoanApplication(long id) throws ResourceNotFoundException;
    LoanApplication updateStatusOfLoanApplication(Long loanApplicationId, Status status) throws ResourceNotFoundException;
    List<LoanApplication> retrieveLoanApplicationByStatus(String status);

}
