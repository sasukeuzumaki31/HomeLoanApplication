package com.group4.demo.controller;


import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Status;
import com.group4.demo.service.ILoanApplicationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/financeofficer")
public class FinanceOfficerController {

    Log logger = LogFactory.getLog(FinanceOfficerController.class);

    @Autowired
    ILoanApplicationService loanApplicationService;

    @GetMapping("/loan/{id}")
    public ResponseEntity<LoanApplication> retrieveLoanApplicationById(@PathVariable Long id)
    {
        LoanApplication loanApplication = loanApplicationService.retriveLoanApplicationById(id);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

    @PutMapping("/loan/{id}")
    public ResponseEntity<LoanApplication> updateStatusOfLoanApplication(@PathVariable Long id)
    {
        LoanApplication loanApplication = loanApplicationService.updateStatusOfLoanApplication(id, Status.PENDING);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

}
