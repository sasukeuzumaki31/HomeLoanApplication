package com.group4.demo.service;

import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.dto.FinanceVerificationDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.FinanceVerificationOfficer;
import com.group4.demo.entity.LoanApplication;

public interface IFinanceVerificationService {
    LoanApplication updateStatus(Long id) throws ResourceNotFoundException;

    FinanceVerificationOfficer addFinanceVerificationOfficer(FinanceVerificationDto financeVerificationDto) throws CouldNotBeAddedException;

    String loginFinanceVerificationOfficer(UserLoginDto user) throws ResourceNotFoundException;
}
