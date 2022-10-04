package com.group4.demo.service.impl;

import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.dto.LoanApplicationDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.*;
import com.group4.demo.repository.ICustomerRepository;
import com.group4.demo.repository.IEMIRepository;
import com.group4.demo.repository.ILoanApplicationRepository;
import com.group4.demo.repository.ISchemeRepository;
import com.group4.demo.service.ILoanApplicationService;
import com.group4.demo.util.EMICalculator;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class ILoanApplicationServiceImpl implements ILoanApplicationService {

    Log logger = LogFactory.getLog(ILoanApplicationServiceImpl.class);
    @Autowired
    ILoanApplicationRepository loanRepo;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ISchemeRepository schemeRepository;

    @Autowired
    private IEMIRepository repository;


    String notFoundMessage = "No Loan Application found";
    @Override
    public LoanApplication deleteLoanApplicationId(long loanApplicationId) throws ResourceNotFoundException {
        logger.info("In deleteLoanApplicationById function in LoanApplicationServiceImpl");
        return loanRepo.findById(loanApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));

    }

    @Override
    public List<LoanApplication> retrieveAllLoanApplication() {
        logger.info("In retrieveAllLoanApplication function in LoanApplicationServiceImpl");
        return loanRepo.findAll();
    }

    @Override
    public LoanApplication retrieveLoanApplicationById(Long loanApplicationId) throws ResourceNotFoundException {
        logger.info("In retrieveLoanApplicationById function in LoanApplicationServiceImpl");
        return loanRepo.findById(loanApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));
    }

    @Override
    public LoanApplication addLoanApplication(LoanApplicationDto loanApplication) throws ResourceNotFoundException, CouldNotBeAddedException {
        logger.info("In addLoanApplication function in LoanApplicationServiceImpl");
        if(loanRepo.findByCustomerId(loanApplication.getCustomerId()) != null){
            throw new CouldNotBeAddedException("Loan application exists with the customer ID:" + loanApplication.getCustomerId());
        }
        LoanApplication loanApplication1 = new LoanApplication();

        Customer customer = customerRepository.findById(loanApplication.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("No Customer found for the given loan"));
        loanApplication1.setLoanAppliedAmount(loanApplication.getLoanAppliedAmount());
        loanApplication1.setApplicationDate(loanApplication.getApplicationDate());
        loanApplication1.setTotalAnnualIncome(loanApplication.getTotalAnnualIncome());
        loanApplication1.setMonthlyExpenses(loanApplication.getMonthlyExpenses());
        loanApplication1.setOtherMonthlyExpenses(loanApplication.getOtherMonthlyExpenses());
        if (customer.getAadharNumber() == null || customer.getPanNumber() == null) {
            loanApplication1.setStatus(String.valueOf(Status.DOCUMENTS_NOT_UPLOADED));
        } else {
            loanApplication1.setStatus(String.valueOf(Status.DOCUMENTS_UPLOADED));
        }


        /*
        Find Customer by Id and save loan save it into Customer object
         */
        loanApplication1.setCustomer(customer);

        /*
        fetch scheme and append it to Loan application object
         */
        Scheme scheme = schemeRepository.findById(loanApplication.getSchemeId())
                .orElseThrow(() -> new ResourceNotFoundException("No Scheme Found For the given loan"));
        loanApplication1.setScheme(scheme);

        /*
        Find Customer and  save it into Loan object
         */
        return loanRepo.save(loanApplication1);
    }

    //updating loanApplication
    @Override
    public LoanApplication updateLoanApplication(long id) throws ResourceNotFoundException {
        logger.info("In updateLoanApplication function in LoanApplicationServiceImpl");

        LoanApplication loanApplication = loanRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));

        if(loanApplication.getStatus().equals(String.valueOf(Status.DOCUMENTS_UPLOADED))){
            loanApplication.setStatus(String.valueOf(Status.WAITING_FOR_LAND_VERIFICATION_OFFICE_APPROVAL));
            return loanRepo.save(loanApplication);
        }else {
            boolean verify = loanApplication.isLandVerificationApproval() && loanApplication.isFinanceVerificationApproval();
            loanApplication.setAdminApproval(verify);

            loanApplication.setStatus(String.valueOf(verify ? Status.APPROVED : Status.REJECTED));
        }

            /*
                 Making Loan Agreement with Customer  after loan is approved
             */

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setLoanApplication(loanApplication);

        EMI emi = new EMI();
        LocalDate dueDate = loanApplication.getApplicationDate().plusYears(loanApplication.getScheme().getTenure());
        emi.setDeuDate(dueDate); //calculate due date

        emi.setLoanAmount(loanApplication.getLoanApprovedAmount());

        EMICalculator emiCalculator = new EMICalculator(loanApplication.getLoanApprovedAmount(), loanApplication.getScheme().getInterestRate(), loanApplication.getScheme().getTenure());

        emi.setEmiAmount(emiCalculator.getEMIAmount()); // find EMI using EMiclass

        double interestAmount = (emi.getEmiAmount() * loanApplication.getScheme().getTenure() * 12)
                - emi.getLoanAmount(); //find interest

        emi.setInterestAmount(Double.parseDouble(String.format("%.2f", interestAmount)));

        emi.setLoanAgreement(loanAgreement);
            /*
            Saving EMI Object into Repo
             */
        repository.save(emi);


        return loanRepo.save(loanApplication);
    }

    @Override
    public LoanApplication updateStatusOfLoanApplication(Long loanApplicationId, Status status) throws ResourceNotFoundException {
        logger.info("In updateStatusOfLoanApplication function in LoanApplicationServiceImpl");
        LoanApplication application = loanRepo.findById(loanApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));
        application.setStatus(String.valueOf(status));
        application.setLandVerificationApproval(true);
        return loanRepo.save(application);
    }

    @Override
    public List<LoanApplication> retrieveLoanApplicationByStatus(String status) {
        logger.info("In retrieveLoanApplicationByStatus function in LoanApplicationServiceImpl");
        return loanRepo.findByStatus(status);
    }
}
