package com.group4.demo.service;

import com.group4.demo.advices.AuthenticationFailedException;
import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.dto.LandVerificationOfficerDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.LandVerificationOfficer;
import com.group4.demo.entity.LoanApplication;

public interface ILandVerificationService {
    void updateStatus(LoanApplication loanApplication) throws ResourceNotFoundException;

    LandVerificationOfficer addLandVerificationOfficer(LandVerificationOfficerDto landVerificationOfficerDto) throws CouldNotBeAddedException;

    String loginLandOfficer(UserLoginDto userLoginDto) throws AuthenticationFailedException;
}
