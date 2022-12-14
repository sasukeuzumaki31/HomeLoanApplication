package com.group4.demo.controller;

import com.group4.demo.advices.AuthenticationFailedException;
import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.dto.CustomerDto;
import com.group4.demo.dto.DocsDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.dto.LoanApplicationDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.service.impl.ICustomerServiceImpl;
import com.group4.demo.service.impl.ILoanAgreementServiceImpl;
import com.group4.demo.service.impl.ISchemeServiceImpl;
import com.group4.demo.service.impl.ILoanApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ILoanApplicationServiceImpl loanApplicationService;

    @Autowired
    private ICustomerServiceImpl customerService;

    @Autowired
    private ISchemeServiceImpl iSchemeService;

    @Autowired
    private ILoanAgreementServiceImpl iLoanAgreementService;


    /*
        Applying for a New Loan - /customer/apply
     */
    @PostMapping("/apply")
    public ResponseEntity<LoanApplication> createNewLoanApplication(@Valid @RequestBody LoanApplicationDto loanApplicatonDto) throws ResourceNotFoundException, CouldNotBeAddedException {

        loanApplicatonDto.setApplicationDate(LocalDate.now());
        LoanApplication savedLoanApplication = loanApplicationService.addLoanApplication(loanApplicatonDto);
        return new ResponseEntity<>(savedLoanApplication, HttpStatus.CREATED);

    }

    /*
        Signup a New Customer - /customer/signup/
     */
    @PostMapping("/signup")
    public ResponseEntity<Customer> createNewCustomer(@Valid @RequestBody CustomerDto customerDto) throws CouldNotBeAddedException {

        Customer newCustomer = customerService.addCustomer(customerDto);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody UserLoginDto customerDto) throws AuthenticationFailedException {

        String response = customerService.loginCustomer(customerDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/document/{id}")
    public ResponseEntity<Customer> updateDocuments(@PathVariable int id, @Valid @RequestBody DocsDto docsDto) throws ResourceNotFoundException {
        Customer customer = customerService.updateCustomer(id, docsDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/loan/{loanApplicationId}")
    public ResponseEntity<LoanApplication> retrieveLoanApplicationById(@PathVariable Long loanApplicationId) throws ResourceNotFoundException {

        LoanApplication loanApplication;
        loanApplication = loanApplicationService.retrieveLoanApplicationById(loanApplicationId);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

    @GetMapping("/schemes")
    public ResponseEntity<List<Scheme>> getAllSchemes() {

        List<Scheme> schemesList = iSchemeService.getAllSchemes();
        return new ResponseEntity<>(schemesList, HttpStatus.OK);
    }

    @GetMapping("/loanagreement/{id}")
    public ResponseEntity<LoanAgreement> retrieveLoanAgreementById(@PathVariable Long id) throws ResourceNotFoundException {
        LoanAgreement loanAgreement = iLoanAgreementService.retrieveAgreementById(id);
        return new ResponseEntity<>(loanAgreement, HttpStatus.OK);
    }


}
