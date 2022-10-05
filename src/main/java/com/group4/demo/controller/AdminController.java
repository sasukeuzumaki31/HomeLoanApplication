package com.group4.demo.controller;

import com.group4.demo.dto.SchemeDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.*;
import com.group4.demo.service.IAdminService;
import com.group4.demo.service.ICustomerService;
import com.group4.demo.service.ILoanApplicationService;
import com.group4.demo.service.ISchemeService;
import com.group4.demo.service.impl.ILoanAgreementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ICustomerService customerService;

    @Autowired
    ILoanApplicationService loanApplicationService;

    @Autowired
    ISchemeService schemeService;

    @Autowired
    IAdminService adminService;

    @Autowired
    ILoanAgreementServiceImpl iLoanAgreementService;

    @GetMapping("/users")
    public ResponseEntity<List<Customer>> getUsers() throws ResourceNotFoundException{
        List<Customer> customers = customerService.viewAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/users/{date}")
    public ResponseEntity<List<Customer>> viewCustomerList(@PathVariable String date) throws ResourceNotFoundException{
        List<Customer> customers = customerService.viewCustomerList(date);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Customer> viewCustomer(@PathVariable int id) throws ResourceNotFoundException{
        Customer customer = customerService.viewCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable int id) throws ResourceNotFoundException{
        Customer customer = customerService.deleteCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/applications")
    public ResponseEntity<List<LoanApplication>> retrieveAllLoanApplication() {
        List<LoanApplication> applications = loanApplicationService.retrieveAllLoanApplication();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<LoanApplication> getApplicationById(@PathVariable long id) throws ResourceNotFoundException {
        LoanApplication loanApplication = loanApplicationService.retrieveLoanApplicationById(id);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

    @PutMapping("/application/{id}")
    public ResponseEntity<LoanApplication> updateApplicationById(@PathVariable long id) throws ResourceNotFoundException{
        LoanApplication  loanApplication = loanApplicationService.updateLoanApplication(id);
        return new ResponseEntity<>( loanApplication, HttpStatus.OK);
    }

    @GetMapping("/application/pending")
    public ResponseEntity<List<LoanApplication>> getPendingApplications(){
        List<LoanApplication> pendingApplications = loanApplicationService.retrieveLoanApplicationByStatus("PENDING");
        return new ResponseEntity<>(pendingApplications, HttpStatus.OK);
    }

    @GetMapping("/applications/documentsuploaded")
    public ResponseEntity<List<LoanApplication>> getDocumentUploaded(){

        List<LoanApplication> pendingApplications = loanApplicationService.retrieveLoanApplicationByStatus(String.valueOf(Status.DOCUMENTS_UPLOADED));
        return new ResponseEntity<>(pendingApplications, HttpStatus.OK);
    }

    @PutMapping("application/document/{id}")
    public ResponseEntity<LoanApplication> raiseLandOfficerTicket(@PathVariable long id) throws ResourceNotFoundException{
        LoanApplication  savedLoanApplication = loanApplicationService.updateLoanApplication(id);
        return new ResponseEntity<>( savedLoanApplication, HttpStatus.OK);
    }

    public ILoanApplicationService getLoanApplicationService() {
        return loanApplicationService;
    }

    @GetMapping("/scheme")
    public ResponseEntity<List<Scheme>> getAllSchemes(){
        List<Scheme> schemeList = schemeService.getAllSchemes();
        return new ResponseEntity<>(schemeList,HttpStatus.OK);
    }
    @GetMapping("/scheme/{id}")
    public ResponseEntity<Scheme> getSchemeById(@PathVariable int id) throws ResourceNotFoundException{
        Scheme scheme = schemeService.getSchemeById(id);
        return new ResponseEntity<>(scheme,HttpStatus.OK);
    }

    @PostMapping("/scheme")
    public ResponseEntity<Scheme> addScheme(@Valid @RequestBody SchemeDto schemeDto){
        Scheme scheme = schemeService.addScheme(schemeDto);
        return new ResponseEntity<>(scheme,HttpStatus.OK);
    }
    @DeleteMapping("/scheme/{id}")
    public ResponseEntity<Scheme> deleteSchemeById(@PathVariable int id) throws ResourceNotFoundException {
        Scheme scheme = schemeService.deleteSchemeById(id);
        return new ResponseEntity<>(scheme, HttpStatus.OK);
    }
    @PutMapping("/scheme/{id}")
    public ResponseEntity<Scheme> updateScheme(@PathVariable int id,@RequestBody SchemeDto schemeDto) throws ResourceNotFoundException {
        Scheme scheme1 = schemeService.updateScheme(id,schemeDto);
        return new ResponseEntity<>(scheme1, HttpStatus.OK);
    }

    @GetMapping("/loanagreement/{id}")
    public ResponseEntity<LoanAgreement> retrieveLoanAgreementById(@PathVariable Long id) throws ResourceNotFoundException
    {
        LoanAgreement loanAgreement = iLoanAgreementService.retrieveAgreementById(id);
        return new ResponseEntity<>(loanAgreement,HttpStatus.OK);
    }

    @DeleteMapping("/loanagreement/{id}")
    public ResponseEntity<LoanAgreement> deleteLoanAgreementById(@PathVariable Long id) throws ResourceNotFoundException
    {
        LoanAgreement loanAgreement = iLoanAgreementService.deleteLoanAgreement(id);
        return new ResponseEntity<>(loanAgreement,HttpStatus.OK);
    }

}
