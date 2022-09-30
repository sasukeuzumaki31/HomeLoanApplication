package com.group4.demo.service;

import com.group4.demo.Dto.FinanceVerificationDto;
import com.group4.demo.entity.FinanceVerificationOfficer;
import com.group4.demo.entity.LoanApplication;

public interface IFinanceVerificationService {
    public void updateStatus(LoanApplication loanApplication);

    public FinanceVerificationOfficer addFinanceVerificationOfficer(FinanceVerificationDto financeVerificationDto);
}
