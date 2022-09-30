package com.group4.demo.service.impl;

import com.group4.demo.Dto.FinanceVerificationDto;
import com.group4.demo.Dto.LoanApplicationDto;
import com.group4.demo.entity.FinanceVerificationOfficer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Status;
import com.group4.demo.repository.IFinanceVerificationRepository;
import com.group4.demo.repository.ILoanApplicationRepository;
import com.group4.demo.service.IFinanceVerificationService;
import com.group4.demo.util.HomeLoanBorrowingAmountCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IFinanceVerificationServiceImpl implements IFinanceVerificationService {

    @Autowired
    IFinanceVerificationRepository financeVerificationRepository;

    @Autowired
    ILoanApplicationRepository loanApplicationRepository;

    @Override
    public FinanceVerificationOfficer addFinanceVerificationOfficer(FinanceVerificationDto financeVerificationDto) {
        FinanceVerificationOfficer financeVerificationOfficer = new FinanceVerificationOfficer();
        financeVerificationOfficer.setFinOfficerName(financeVerificationDto.getFinOfficerName());
        financeVerificationOfficer.setFinOfficerContact(financeVerificationDto.getFinOfficerContact());
        financeVerificationOfficer.setPassword(financeVerificationDto.getPassword());
        financeVerificationOfficer.setUserId(financeVerificationDto.getUserId());
        financeVerificationOfficer.setRole("FinanceVerificationOfficer");

        return financeVerificationRepository.save(financeVerificationOfficer);

    }

    @Override
    public LoanApplication updateStatus(Long id, LoanApplicationDto loanApplicationDto) {

        LoanApplication loanApplication = loanApplicationRepository.findById(id).get();
        HomeLoanBorrowingAmountCalculator homeLoanBorrowingAmountCalculator =
                new HomeLoanBorrowingAmountCalculator(loanApplication.getLoanAppliedAmount()
                        , loanApplication.getScheme().getInterestRate(), loanApplication.getScheme().getTenure()
                        , loanApplicationDto.getTotalAnnualIncome(), loanApplicationDto.getMonthlyExpenses()
                        , loanApplicationDto.getOtherMonthlyExpenses());

        double acceptedAmmount = homeLoanBorrowingAmountCalculator.getHomeLoanBorrowingAmount();
        if (acceptedAmmount == 0.0) {
            loanApplication.setLoanApprovedAmount(acceptedAmmount);
            loanApplication.setFinanceVerificationApproval(false);
            loanApplication.setStatus(String.valueOf(Status.REJECTED));
        } else {
            loanApplication.setLoanApprovedAmount(homeLoanBorrowingAmountCalculator.getHomeLoanBorrowingAmount());
            loanApplication.setFinanceVerificationApproval(true);
            loanApplication.setStatus(String.valueOf(Status.PENDING));
        }
        loanApplicationRepository.save(loanApplication);
        return loanApplication;
    }
}
