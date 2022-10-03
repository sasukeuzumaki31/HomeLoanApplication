package com.group4.demo.service.impl;

import com.group4.demo.Dto.FinanceVerificationDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.FinanceVerificationOfficer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Status;
import com.group4.demo.repository.IFinanceVerificationRepository;
import com.group4.demo.repository.ILoanApplicationRepository;
import com.group4.demo.service.IFinanceVerificationService;
import com.group4.demo.util.HomeLoanBorrowingAmountCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class IFinanceVerificationServiceImpl implements IFinanceVerificationService {

    @Autowired
    IFinanceVerificationRepository financeVerificationRepository;

    @Autowired
    ILoanApplicationRepository loanApplicationRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public FinanceVerificationOfficer addFinanceVerificationOfficer(FinanceVerificationDto financeVerificationDto) {
        FinanceVerificationOfficer financeVerificationOfficer = new FinanceVerificationOfficer();
        financeVerificationOfficer.setFinOfficerName(financeVerificationDto.getFinOfficerName());
        financeVerificationOfficer.setFinOfficerContact(financeVerificationDto.getFinOfficerContact());
        financeVerificationOfficer.setPassword(bcryptEncoder.encode(financeVerificationDto.getPassword()));
        financeVerificationOfficer.setUserId(financeVerificationDto.getUserId());
        financeVerificationOfficer.setRole("FinanceVerificationOfficer");

        return financeVerificationRepository.save(financeVerificationOfficer);

    }

    @Override
    public LoanApplication updateStatus(Long id) throws ResourceNotFoundException {

        LoanApplication loanApplicationOp = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not found for Id " + id));

        LoanApplication loanApplication = loanApplicationOp;
        HomeLoanBorrowingAmountCalculator homeLoanBorrowingAmountCalculator =
                new HomeLoanBorrowingAmountCalculator(loanApplication.getLoanAppliedAmount()
                        , loanApplication.getScheme().getInterestRate(), loanApplication.getScheme().getTenure()
                        , loanApplication.getTotalAnnualIncome(), loanApplication.getMonthlyExpenses()
                        , loanApplication.getOtherMonthlyExpenses());

        double acceptedAmount = homeLoanBorrowingAmountCalculator.getHomeLoanBorrowingAmount();
        loanApplication.setLoanApprovedAmount(acceptedAmount);
        if (acceptedAmount == 0.0) {
            loanApplication.setFinanceVerificationApproval(false);
            loanApplication.setStatus(String.valueOf(Status.REJECTED));
        } else {
            loanApplication.setFinanceVerificationApproval(true);
            loanApplication.setStatus(String.valueOf(Status.PENDING));
        }
        loanApplicationRepository.save(loanApplication);
        return loanApplication;
    }
}
