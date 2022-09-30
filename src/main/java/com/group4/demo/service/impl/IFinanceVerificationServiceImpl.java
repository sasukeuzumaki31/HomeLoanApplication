package com.group4.demo.service.impl;

import com.group4.demo.entity.LoanApplication;
import com.group4.demo.service.IFinanceVerificationService;
import org.springframework.stereotype.Service;

@Service
public class IFinanceVerificationServiceImpl implements IFinanceVerificationService {
    @Override
    public void updateStatus(LoanApplication loanApplication) {

        loanApplication.setFinanceVerificationApproval(true);


    }
}
