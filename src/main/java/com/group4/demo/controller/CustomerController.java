package com.group4.demo.controller;

import com.group4.demo.Dto.LoanApplicatonDto;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.service.impl.LoanApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private LoanApplicationServiceImpl loanApplicationService;

    /*
    Applying for a New Loan
     */
    @PostMapping("/apply")
    public ResponseEntity<LoanApplication> createNewLoanApplication(@RequestBody LoanApplicatonDto loanApplicatonDto) {

        loanApplicatonDto.setApplicationDate(LocalDate.now());
        LoanApplication savedLoanApplication = loanApplicationService.addLoanApplication(loanApplicatonDto);
        return new ResponseEntity<>(savedLoanApplication, HttpStatus.CREATED);

    }

}
