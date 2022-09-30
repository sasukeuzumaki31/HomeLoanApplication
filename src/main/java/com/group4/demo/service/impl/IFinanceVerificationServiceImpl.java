package com.group4.demo.service.impl;

import com.group4.demo.Dto.FinanceVerificationDto;
import com.group4.demo.entity.FinanceVerificationOfficer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.repository.IFinanceVerificationRepository;
import com.group4.demo.service.IFinanceVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IFinanceVerificationServiceImpl implements IFinanceVerificationService {

    @Autowired
    IFinanceVerificationRepository financeVerificationRepository;
    @Override
    public FinanceVerificationOfficer addFinanceVerificationOfficer(FinanceVerificationDto financeVerificationDto)
    {
        FinanceVerificationOfficer financeVerificationOfficer = new FinanceVerificationOfficer();
        financeVerificationOfficer.setFinOfficerName(financeVerificationDto.getFinOfficerName());
        financeVerificationOfficer.setFinOfficerContact(financeVerificationDto.getFinOfficerContact());
        financeVerificationOfficer.setPassword(financeVerificationDto.getPassword());
        financeVerificationOfficer.setUserId(financeVerificationDto.getUserId());
        financeVerificationOfficer.setRole("FinanceVerificationOfficer");

        return financeVerificationRepository.save(financeVerificationOfficer);

    }
    @Override
    public void updateStatus(LoanApplication loanApplication) {

        loanApplication.setFinanceVerificationApproval(true);


    }
}
