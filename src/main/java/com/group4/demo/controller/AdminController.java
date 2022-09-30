package com.group4.demo.controller;

import com.group4.demo.entity.Customer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.service.ICustomerService;
import com.group4.demo.service.ILoanApplicationService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    Log logger = LogFactory.getLog(AdminController.class);

    @Autowired
    ICustomerService customerService;

    @Autowired
    ILoanApplicationService loanApplicationService;

    @GetMapping("/user/{id}")
    public ResponseEntity<Customer> viewCustomer(@PathVariable int id) {
        Customer customer = customerService.viewCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable int id) {
        Customer customer = customerService.deleteCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/applications")
    public ResponseEntity<List<LoanApplication>> retriveAllLoanApplication() {
        List<LoanApplication> applications = loanApplicationService.retriveAllLoanApplication();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

}
