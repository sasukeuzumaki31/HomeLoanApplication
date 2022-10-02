package com.group4.demo.service;

import com.group4.demo.Dto.LandVerificationOfficerDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.LandVerificationOfficer;
import com.group4.demo.entity.LoanApplication;

public interface ILLandVerificationService {
    public void updateStatus(LoanApplication loanApplication) throws ResourceNotFoundException;

    LandVerificationOfficer addLandVerificationOfficer(LandVerificationOfficerDto landVerificationOfficerDto);
}
