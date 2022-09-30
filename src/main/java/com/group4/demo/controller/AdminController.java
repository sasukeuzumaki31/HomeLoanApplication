package com.group4.demo.controller;

import com.group4.demo.Dto.AdminDto;
import com.group4.demo.Dto.SchemeDto;
import com.group4.demo.Dto.LoanApplicatonDto;
import com.group4.demo.entity.Admin;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.service.IAdminService;
import com.group4.demo.service.ICustomerService;
import com.group4.demo.service.ILoanApplicationService;
import com.group4.demo.service.ISchemeService;
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

    @Autowired
    ISchemeService schemeService;

    @Autowired
    IAdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<Customer>> getUsers(){
        List<Customer> customers = customerService.viewAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
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
    public ResponseEntity<List<LoanApplication>> retrieveAllLoanApplication() {
        List<LoanApplication> applications = loanApplicationService.retrieveAllLoanApplication();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<LoanApplication> getApplicationById(@PathVariable long id){
        LoanApplication loanApplication = loanApplicationService.retrieveLoanApplicationById(id);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

    @PutMapping("/application/{id}")
    public ResponseEntity<LoanApplication> updateApplicationById(@PathVariable long id, @RequestBody LoanApplicatonDto loanApplicatonDto){
        LoanApplication  savedLoanApplication = loanApplicationService.updateLoanApplication(id, loanApplicatonDto);
        return new ResponseEntity<>( savedLoanApplication, HttpStatus.OK);
    }

    @GetMapping("/application/pending")
    public ResponseEntity<List<LoanApplication>> getPendingApplications(){
        List<LoanApplication> pendingApplications = loanApplicationService.retrieveLoanApplicationByStatus("PENDING");
        return new ResponseEntity<>(pendingApplications, HttpStatus.OK);
    }

    @GetMapping("/scheme")
    public ResponseEntity<List<Scheme>> getAllSchemes(){
        List<Scheme> schemeList = schemeService.getAllSchemes();
        return new ResponseEntity<>(schemeList,HttpStatus.OK);
    }
    @GetMapping("/scheme/{id}")
    public ResponseEntity<Scheme> getSchemeById(@PathVariable int id){
        Scheme scheme = schemeService.getSchemeById(id);
        return new ResponseEntity<>(scheme,HttpStatus.OK);
    }

    @PostMapping("/scheme")
    public ResponseEntity<Scheme> addScheme(@RequestBody SchemeDto schemeDto){
        Scheme scheme = schemeService.addScheme(schemeDto);
        return new ResponseEntity<>(scheme,HttpStatus.CREATED);
    }
    @DeleteMapping("/scheme/{id}")
    public ResponseEntity<Scheme> deleteSchemeById(@PathVariable int id) {
        Scheme scheme = schemeService.deleteSchemeById(id);
        return new ResponseEntity<>(scheme, HttpStatus.OK);
    }
    @PutMapping("/scheme/{id}")
    public ResponseEntity<Scheme> updateScheme(@PathVariable int id,@RequestBody SchemeDto schemeDto){
        Scheme scheme1 = schemeService.updateScheme(id,schemeDto);
        return new ResponseEntity<>(scheme1, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Admin> createNewAdmin(@RequestBody AdminDto adminDto){
        Admin newAdmin = adminService.addAdmin(adminDto);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }
}
