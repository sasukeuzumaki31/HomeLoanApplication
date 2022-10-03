package com.group4.demo.service;

import com.group4.demo.Dto.FinanceVerificationDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.FinanceVerificationOfficer;
import com.group4.demo.entity.LoanApplication;

public interface IFinanceVerificationService {
    public LoanApplication updateStatus(Long id) throws ResourceNotFoundException;

    public FinanceVerificationOfficer addFinanceVerificationOfficer(FinanceVerificationDto financeVerificationDto);

    public FinanceVerificationOfficer loginFinanceVerificationOfficerById(int id, String pass) throws ResourceNotFoundException;
}
