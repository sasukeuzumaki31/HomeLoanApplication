package com.group4.demo.service.impl;

import com.group4.demo.entity.LoanApplication;
import com.group4.demo.service.ILLandVerificationService;
import org.springframework.stereotype.Service;

@Service
public class ILandVerificationServiceImpl implements ILLandVerificationService {
    @Override
    public void updateStatus(LoanApplication loanApplication) {
        loanApplication.setLandVerificationApproval(true);

    }
}
