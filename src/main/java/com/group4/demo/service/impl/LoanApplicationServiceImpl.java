package com.group4.demo.service.impl;

import com.group4.demo.Dto.LoanApplicatonDto;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Status;
import com.group4.demo.repository.ICustomerRepository;
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

    @Autowired
    private ICustomerRepository customerRepository;


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
    public LoanApplication addLoanApplication(LoanApplicatonDto loanApplication) {

        LoanApplication loanApplication1 = new LoanApplication();

        loanApplication1.setLoanAppliedAmount(loanApplication.getLoanAppliedAmount());
        loanApplication1.setApplicationDate(loanApplication.getApplicationDate());
        loanApplication1.setStatus(String.valueOf(Status.PENDING));
        /*
        Find Customer by Id and save loan save it into Customer object
         */
        Customer customer = customerRepository.findById(loanApplication.getCustomerId()).get();
        loanApplication1.setCustomer(customer);


        /*
        Find Customer and  save it into Loan object
         */
        return loanRepo.save(loanApplication1);
    }

    //updating loanApplication
    @Override
    public LoanApplication updateLoanApplication(long id, LoanApplicatonDto loanApplicationDto) {
        Optional<LoanApplication> loanApplicationOp = loanRepo.findById(id);

        if (loanApplicationOp.isPresent()) {
            LoanApplication loanApplication = loanApplicationOp.get();
            loanApplication.setLoanApprovedAmount(loanApplicationDto.getLoanApprovedAmount());
            loanApplication.setStatus(loanApplicationDto.getStatus());
            loanApplication.setAdminApproval(loanApplicationDto.isAdminApproval());
            loanApplication.setFinanceVerificationApproval(loanApplicationDto.isFinanceVerificationApproval());
            loanApplication.setLandVerificationApproval(loanApplication.isLandVerificationApproval());
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
            return loanRepo.save(loanApplication);
        }
        return null;
    }

    @Override
    public List<LoanApplication> retrieveLoanApplicationByStatus(String status) {
        return loanRepo.findByStatus(status);
    }
}
