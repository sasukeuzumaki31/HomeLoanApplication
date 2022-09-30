package com.group4.demo.controller;

import com.group4.demo.Dto.CustomerDto;
import com.group4.demo.Dto.LoanApplicatonDto;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.service.ICustomerService;
import com.group4.demo.service.impl.ICustomerServiceImpl;
import com.group4.demo.service.impl.LoanApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private LoanApplicationServiceImpl loanApplicationService;

    @Autowired
    private ICustomerServiceImpl customerService;


    /*
        Applying for a New Loan - /customer/apply
     */
    @PostMapping("/apply")
    public ResponseEntity<LoanApplication> createNewLoanApplication(@RequestBody LoanApplicatonDto loanApplicatonDto) {

        loanApplicatonDto.setApplicationDate(LocalDate.now());
        LoanApplication savedLoanApplication = loanApplicationService.addLoanApplication(loanApplicatonDto);
        return new ResponseEntity<>(savedLoanApplication, HttpStatus.CREATED);

    }

    /*
        Signup a New Customer - /customer/signup/
     */
    @PostMapping("/signup")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody CustomerDto customerDto) {

        Customer newCustomer = customerService.addCustomer(customerDto);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    /*
       Get Loan by Id - /customer/loan/{id}
    */
    @GetMapping("/loan/{loanApplicationId}")
    public ResponseEntity<LoanApplication> retrieveLoanApplicationById(@PathVariable Long loanApplicationId) {

        LoanApplication loanApplication;
        loanApplication = loanApplicationService.retrieveLoanApplicationById(loanApplicationId);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }


}
