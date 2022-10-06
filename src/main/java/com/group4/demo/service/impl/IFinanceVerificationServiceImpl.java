package com.group4.demo.service.impl;

import com.group4.demo.advices.AuthenticationFailedException;
import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.dto.FinanceVerificationDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.FinanceVerificationOfficer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Status;
import com.group4.demo.repository.IFinanceVerificationRepository;
import com.group4.demo.repository.ILoanApplicationRepository;
import com.group4.demo.service.IFinanceVerificationService;
import com.group4.demo.util.HomeLoanBorrowingAmountCalculator;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class IFinanceVerificationServiceImpl implements IFinanceVerificationService {

    Log logger = LogFactory.getLog(IFinanceVerificationServiceImpl.class);
    @Autowired
    IFinanceVerificationRepository financeVerificationRepository;

    @Autowired
    ILoanApplicationRepository loanApplicationRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public FinanceVerificationOfficer addFinanceVerificationOfficer(FinanceVerificationDto financeVerificationDto) throws CouldNotBeAddedException {
        logger.info("Entered in addFinanceVerificationOfficer method in IFinanceVerificationServiceImpl");
        if (financeVerificationRepository.findByFinOfficerContact(financeVerificationDto.getFinOfficerContact()) != null) {
            throw new CouldNotBeAddedException("Officer already exists");
        }
        FinanceVerificationOfficer financeVerificationOfficer = new FinanceVerificationOfficer();
        financeVerificationOfficer.setFinOfficerName(financeVerificationDto.getFinOfficerName());
        financeVerificationOfficer.setFinOfficerContact(financeVerificationDto.getFinOfficerContact());
        financeVerificationOfficer.setPassword(bcryptEncoder.encode(financeVerificationDto.getPassword()));
        financeVerificationOfficer.setUserId(financeVerificationDto.getUserId());
        financeVerificationOfficer.setRole("FinanceVerificationOfficer");

        return financeVerificationRepository.save(financeVerificationOfficer);

    }

    /*
    Finance Verification Officer is checking the eligibility of customer
    based on annual income and expenses
    and updating the loan application status accordingly.
     */
    @Override
    public LoanApplication updateStatus(Long id) throws ResourceNotFoundException {

        logger.info("Entered in updateStatus method in IFinanceVerificationServiceImpl");

        LoanApplication loanApplicationOp = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not found for Id " + id));
        if (loanApplicationOp.getStatus().equals(Status.WAITING_FOR_FINANCE_APPROVAL.toString())) {


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
        } else {
            throw new ResourceNotFoundException("This application is not under your authority");
        }
    }

    @Override
    public String loginFinanceVerificationOfficer(@RequestBody UserLoginDto user) throws AuthenticationFailedException {
        FinanceVerificationOfficer financeVerificationOfficer = financeVerificationRepository.findById(user.getUserId())
                .orElseThrow(() -> new AuthenticationFailedException("Invalid Credentials"));

        if (bcryptEncoder.matches(user.getPassword(), financeVerificationOfficer.getPassword())) {
            return "Login successfully";
        }
        return "Invalid Credentials";
    }

}
