package com.group4.demo.controller;

import com.group4.demo.Dto.LandVerificationOfficerDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.LandVerificationOfficer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Status;
import com.group4.demo.service.ILLandVerificationService;
import com.group4.demo.service.ILoanApplicationService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/landofficer")
public class LandOfficerController {
    Log logger = LogFactory.getLog(LandOfficerController.class);

    @Autowired
    ILoanApplicationService loanApplicationService;

    @Autowired
    ILLandVerificationService landVerificationService;


    @GetMapping("/loan/{id}")
    public ResponseEntity<LoanApplication> retrieveLoanApplicationById(@PathVariable Long id) throws ResourceNotFoundException {
        LoanApplication loanApplication = loanApplicationService.retrieveLoanApplicationById(id);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

    @PutMapping("/loan/{id}")
    public ResponseEntity<LoanApplication> updateStatusOfLoanApplication(@PathVariable Long id) throws ResourceNotFoundException {
        LoanApplication loanApplication = loanApplicationService.updateStatusOfLoanApplication(id, Status.WAITING_FOR_FINANCE_APPROVAL);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

    @GetMapping("/loans/pending")
    public ResponseEntity<List<LoanApplication>> getPendingApplications() {
        List<LoanApplication> pendingApplications = loanApplicationService.retrieveLoanApplicationByStatus(
                "WAITING_FOR_LAND_VERIFICATION_OFFICE_APPROVAL"
        );
        return new ResponseEntity<>(pendingApplications, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<LandVerificationOfficer> createNewCustomer(@RequestBody LandVerificationOfficerDto landVerificationOfficerDto) {
        LandVerificationOfficer newLandOfficer = landVerificationService.addLandVerificationOfficer(landVerificationOfficerDto);
        return new ResponseEntity<>(newLandOfficer, HttpStatus.CREATED);
    }
}

