package com.group4.demo.service.impl;

import com.group4.demo.Dto.LandVerificationOfficerDto;
import com.group4.demo.entity.LandVerificationOfficer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.repository.ILandVerificationRepository;
import com.group4.demo.service.ILLandVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ILandVerificationServiceImpl implements ILLandVerificationService {

    @Autowired
    ILandVerificationRepository landVerificationRepository;
    @Override
    public void updateStatus(LoanApplication loanApplication) {
        loanApplication.setLandVerificationApproval(true);

    }

    @Override
    public LandVerificationOfficer addLandVerificationOfficer(LandVerificationOfficerDto landVerificationOfficerDto) {
        LandVerificationOfficer landVerificationOfficer = new LandVerificationOfficer();
        landVerificationOfficer.setOfficeContact(landVerificationOfficerDto.getOfficeContact());
        landVerificationOfficer.setOfficeName(landVerificationOfficerDto.getOfficeName());
        landVerificationOfficer.setPassword(landVerificationOfficerDto.getPassword());
        landVerificationOfficer.setRole("LAND_VERIFICATION_OFFICER");
        return landVerificationRepository.save(landVerificationOfficer);
    }

}
