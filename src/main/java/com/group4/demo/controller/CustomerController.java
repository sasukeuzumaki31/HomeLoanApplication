package com.group4.demo.controller;

import com.group4.demo.Dto.CustomerDto;
import com.group4.demo.Dto.LoanApplicatonDto;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.service.impl.ICustomerServiceImpl;
import com.group4.demo.service.impl.ISchemeServiceImpl;
import com.group4.demo.service.impl.LoanApplicationServiceImpl;
import com.group4.demo.util.EMICalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private LoanApplicationServiceImpl loanApplicationService;

    @Autowired
    private ICustomerServiceImpl customerService;

    @Autowired
    private ISchemeServiceImpl iSchemeService;


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
       GET  Loan by Id - /customer/loan/{id}
    */
    @GetMapping("/loan/{loanApplicationId}")
    public ResponseEntity<LoanApplication> retrieveLoanApplicationById(@PathVariable Long loanApplicationId) {

        LoanApplication loanApplication;
        loanApplication = loanApplicationService.retrieveLoanApplicationById(loanApplicationId);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

    @GetMapping("/schemes")
    public ResponseEntity<List<Scheme>> getAllSchemes() {

        List<Scheme> schemesList = iSchemeService.getAllSchemes();
        return new ResponseEntity<>(schemesList, HttpStatus.OK);
    }


    /*
      POST calculate EMI - /customer/emi
   */
    @PostMapping("/emi")
    public ResponseEntity<Double> getEMIAmount(@RequestBody EMICalculator emiCalculator) {


        EMICalculator calc = new EMICalculator(emiCalculator.getLoanAmount(), emiCalculator.getRateOfInterest(), emiCalculator.getTenure());
        double amount = calc.getEMIAmount();
        return new ResponseEntity<>(amount, HttpStatus.OK);
    }

}
