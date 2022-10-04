package com.group4.demo.service.impl;

import com.group4.demo.dto.LandVerificationOfficerDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.LandVerificationOfficer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.repository.ILandVerificationRepository;
import com.group4.demo.repository.ILoanApplicationRepository;
import com.group4.demo.service.ILandVerificationService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ILandVerificationServiceImpl implements ILandVerificationService {

    Log logger = LogFactory.getLog(ILoanAgreementServiceImpl.class);
    @Autowired
    ILandVerificationRepository landVerificationRepository;

    @Autowired
    ILoanApplicationRepository loanApplicationRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public void updateStatus(LoanApplication loanApplication) throws ResourceNotFoundException {
        logger.info("Entered into updateStatus method in LandVerification Class");
        if(loanApplicationRepository.findById(loanApplication.getApplicationId()).isPresent()){
            loanApplication.setLandVerificationApproval(true);
            loanApplicationRepository.save(loanApplication);
        }else{
            throw new ResourceNotFoundException("Application Not Found" + loanApplication);
        }
        logger.info("Exited from updateStatus method in LandVerification Class");

    }

    @Override
    public LandVerificationOfficer addLandVerificationOfficer(LandVerificationOfficerDto landVerificationOfficerDto) {
        logger.info("Entered into addLandVerificationOfficer method in LandVerification Class");
        LandVerificationOfficer landVerificationOfficer = new LandVerificationOfficer();
        landVerificationOfficer.setOfficeContact(landVerificationOfficerDto.getOfficeContact());
        landVerificationOfficer.setOfficeName(landVerificationOfficerDto.getOfficeName());
        landVerificationOfficer.setPassword(bcryptEncoder.encode(landVerificationOfficerDto.getPassword()));
        landVerificationOfficer.setRole("LAND_VERIFICATION_OFFICER");
        logger.info("Exited from addLandVerificationOfficer method in LandVerification Class");
        return landVerificationRepository.save(landVerificationOfficer);

    }

    @Override
    public String loginLandOfficer(UserLoginDto userLoginDto) throws ResourceNotFoundException {
        LandVerificationOfficer landVerificationOfficer = landVerificationRepository.findById(userLoginDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials "));
        String password = userLoginDto.getPassword();

        if (bcryptEncoder.matches(password, landVerificationOfficer.getPassword())) {
            return "Login successfully";
        }
        return "Invalid Credentials";

    }

}
