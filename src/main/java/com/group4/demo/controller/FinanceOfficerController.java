package com.group4.demo.controller;


import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.dto.FinanceVerificationDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.FinanceVerificationOfficer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.service.IFinanceVerificationService;
import com.group4.demo.service.ILoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/financeofficer")
public class FinanceOfficerController {


    @Autowired
    ILoanApplicationService loanApplicationService;

    @Autowired
    IFinanceVerificationService financeVerificationService;


    @PostMapping("/signup")
    public ResponseEntity<FinanceVerificationOfficer> createNewFinanceVerificationOfficer(@Valid @RequestBody FinanceVerificationDto financeOfficerDto) throws CouldNotBeAddedException {
        FinanceVerificationOfficer newFinanceVerificationOfficer = financeVerificationService.addFinanceVerificationOfficer(financeOfficerDto);
        return new ResponseEntity<>(newFinanceVerificationOfficer, HttpStatus.CREATED);
    }

    @GetMapping("/loan/{id}")
    public ResponseEntity<LoanApplication> retrieveLoanApplicationById(@PathVariable Long id) throws ResourceNotFoundException
    {
        LoanApplication loanApplication = loanApplicationService.retrieveLoanApplicationById(id);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

    @PutMapping("/loan/{id}")
    public ResponseEntity<LoanApplication> updateStatusOfLoanApplication(@PathVariable Long id) throws ResourceNotFoundException
    {
        LoanApplication loanApplication = financeVerificationService.updateStatus(id);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginFinanceVerificationOfficer(@RequestBody UserLoginDto user) throws ResourceNotFoundException {

        String response = financeVerificationService.loginFinanceVerificationOfficer(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/loans/pending")
    public ResponseEntity<List<LoanApplication>> getPendingApplications() {
        List<LoanApplication> pendingApplications = loanApplicationService.retrieveLoanApplicationByStatus(
                "WAITING_FOR_FINANCE_APPROVAL"
        );
        return new ResponseEntity<>(pendingApplications, HttpStatus.OK);
    }




}
