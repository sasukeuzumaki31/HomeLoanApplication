package com.group4.demo.service.impl;

import com.group4.demo.Dto.LoanApplicationDto;
import com.group4.demo.entity.*;
import com.group4.demo.repository.ICustomerRepository;
import com.group4.demo.repository.IEMIRepository;
import com.group4.demo.repository.ILoanApplicationRepository;
import com.group4.demo.repository.ISchemeRepository;
import com.group4.demo.service.ILoanApplicationService;
import com.group4.demo.util.EMICalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanApplicationServiceImpl implements ILoanApplicationService {
    @Autowired
    ILoanApplicationRepository loanRepo;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ISchemeRepository schemeRepository;

    @Autowired
    private IEMIRepository repository;


    @Override
    public LoanApplication deleteLoanApplicationId(long loanApplicationId) {
        Optional<LoanApplication> application = loanRepo.findById(loanApplicationId);
        if (application.isPresent()) {
            loanRepo.delete(application.get());
            return application.get();
        }
        return null;
    }

    @Override
    public List<LoanApplication> retrieveAllLoanApplication() {
        return loanRepo.findAll();
    }

    @Override
    public LoanApplication retrieveLoanApplicationById(Long loanApplicationId) {
        Optional<LoanApplication> application = loanRepo.findById(loanApplicationId);
        return application.isPresent() ? application.get() : null;

    }

    @Override
    public LoanApplication addLoanApplication(LoanApplicationDto loanApplication) {

        LoanApplication loanApplication1 = new LoanApplication();

        loanApplication1.setLoanAppliedAmount(loanApplication.getLoanAppliedAmount());
        loanApplication1.setApplicationDate(loanApplication.getApplicationDate());
        loanApplication1.setStatus(String.valueOf(Status.WAITING_FOR_LAND_VERIFICATION_OFFICE_APPROVAL));

        /*
        Find Customer by Id and save loan save it into Customer object
         */
        Customer customer = customerRepository.findById(loanApplication.getCustomerId()).get();
        loanApplication1.setCustomer(customer);

        /*
        fetch scheme and append it to Loan application object
         */
        Scheme scheme = schemeRepository.findById(loanApplication.getSchemeId()).get();
        loanApplication1.setScheme(scheme);

        /*
        Find Customer and  save it into Loan object
         */
        return loanRepo.save(loanApplication1);
    }

    //updating loanApplication
    @Override
    public LoanApplication updateLoanApplication(long id) {
        Optional<LoanApplication> loanApplicationOp = loanRepo.findById(id);

        if (loanApplicationOp.isPresent()) {
            LoanApplication loanApplication = loanApplicationOp.get();
           // loanApplication.setLoanApprovedAmount(loanApplication.getLoanApprovedAmount());
            //loanApplication.setStatus(loanApplicationDto.getStatus());
            //loanApplication.setAdminApproval(loanApplicationDto.isAdminApproval());
            //loanApplication.setFinanceVerificationApproval(loanApplicationDto.isFinanceVerificationApproval());
            //loanApplication.setLandVerificationApproval(loanApplication.isLandVerificationApproval());

            boolean verify = loanApplication.isLandVerificationApproval() && loanApplication.isFinanceVerificationApproval();
            loanApplication.setAdminApproval(verify);
            loanApplication.setStatus(String.valueOf(verify? Status.APPROVED:Status.REJECTED));

            /*
                 Making Loan Agreement with Customer  after loan is aprroved
             */

            LoanAgreement loanAgreement = new LoanAgreement();
            loanAgreement.setLoanApplication(loanApplication);

            EMI emi = new EMI();
            LocalDate dueDate = loanApplication.getApplicationDate().plusYears(loanApplication.getScheme().getTenure());
            emi.setDeuDate(dueDate); //calculate due date

            emi.setLoanAmount(loanApplication.getLoanApprovedAmount());

            EMICalculator emiCalculator = new EMICalculator(loanApplication.getLoanApprovedAmount(), loanApplication.getScheme().getInterestRate(), loanApplication.getScheme().getTenure());

            emi.setEmiAmount(emiCalculator.getEMIAmount()); // find EMI using EMiclass

            int interestAmount = 2000; //find interest
            emi.setInterestAmount(interestAmount);

            emi.setLoanAgreement(loanAgreement);
            /*
            Saving EMI Object into Repo
             */
            repository.save(emi);


            return loanRepo.save(loanApplication);
        }
        return null;
    }

    @Override
    public LoanApplication updateStatusOfLoanApplication(Long loanApplicationId, Status status) {
        Optional<LoanApplication> application = loanRepo.findById(loanApplicationId);
        if (application.isPresent()) {
            LoanApplication loanApplication = application.get();
            loanApplication.setStatus(String.valueOf(status));
            loanApplication.setLandVerificationApproval(true);
            return loanRepo.save(loanApplication);
        }
        return null;
    }

    @Override
    public List<LoanApplication> retrieveLoanApplicationByStatus(String status) {
        return loanRepo.findByStatus(status);
    }
}
