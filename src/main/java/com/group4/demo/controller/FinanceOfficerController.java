package com.group4.demo.controller;


import com.group4.demo.Dto.FinanceVerificationDto;
import com.group4.demo.entity.FinanceVerificationOfficer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Status;
import com.group4.demo.service.IFinanceVerificationService;
import com.group4.demo.service.ILoanApplicationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financeofficer")
public class FinanceOfficerController {

    Log logger = LogFactory.getLog(FinanceOfficerController.class);

    @Autowired
    ILoanApplicationService loanApplicationService;

    @Autowired
    IFinanceVerificationService financeVerificationService;


    @PostMapping("/signup")
    public ResponseEntity<FinanceVerificationOfficer> createNewFinanceVerificationOfficer(@RequestBody FinanceVerificationDto financeOfficerDto)
    {
        FinanceVerificationOfficer newFinanceVerificationOfficer = financeVerificationService.addFinanceVerificationOfficer(financeOfficerDto);
        return new ResponseEntity<>(newFinanceVerificationOfficer, HttpStatus.CREATED);
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<LoanApplication> retrieveLoanApplicationById(@PathVariable Long id)
    {
        LoanApplication loanApplication = loanApplicationService.retrieveLoanApplicationById(id);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

    @PutMapping("/loan/{id}")
    public ResponseEntity<LoanApplication> updateStatusOfLoanApplication(@PathVariable Long id)
    {
        LoanApplication loanApplication = loanApplicationService.updateStatusOfLoanApplication(id, Status.PENDING);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

    @GetMapping("/loans/pending")
    public ResponseEntity<List<LoanApplication>> getPendingApplications()
    {
        List<LoanApplication> pendingApplications = loanApplicationService.retrieveLoanApplicationByStatus("WAITING_FOR_FINANCE_APPROVAL");
        return new ResponseEntity<>(pendingApplications,HttpStatus.OK);
    }

}
