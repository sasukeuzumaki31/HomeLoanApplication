package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.dto.LoanApplicationDto;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.EMI;
import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.entity.Status;
import com.group4.demo.repository.ICustomerRepository;
import com.group4.demo.repository.IEMIRepository;
import com.group4.demo.repository.ILoanApplicationRepository;
import com.group4.demo.repository.ISchemeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ILoanApplicationServiceImplTest {
    @MockBean
    private ICustomerRepository iCustomerRepository;

    @MockBean
    private IEMIRepository iEMIRepository;

    @MockBean
    private ILoanApplicationRepository iLoanApplicationRepository;

    @Autowired
    private ILoanApplicationServiceImpl iLoanApplicationServiceImpl;

    @MockBean
    private ISchemeRepository iSchemeRepository;

    
    @Test
    void testDeleteLoanApplicationId() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("123456789012");
        customer.setCustomerName("Ramu");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("abcde@gmail.com");
        customer.setGender("Male");
        customer.setMobileNumber("1234567890");
        customer.setNationality("Indian");
        customer.setPanNumber("ABCDE1234F");
        customer.setPassword("Pass@123");
        customer.setRole("Role");
        customer.setUserId(123);

        EMI emi = new EMI();
        emi.setDeuDate(LocalDate.ofEpochDay(1L));
        emi.setEmiAmount(10.0d);
        emi.setEmiId(123L);
        emi.setInterestAmount(10.0d);
        emi.setLoanAmount(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setEmi(emi);
        loanAgreement.setLoanAgreementId(123L);

        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setAdminApproval(true);
        loanApplication.setApplicationDate(LocalDate.ofEpochDay(1L));
        loanApplication.setApplicationId(123L);
        loanApplication.setCustomer(customer);
        loanApplication.setFinanceVerificationApproval(true);
        loanApplication.setLandVerificationApproval(true);
        loanApplication.setLoanAgreement(loanAgreement);
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);
        when(iLoanApplicationRepository.findById(any())).thenReturn(ofResult);
        assertSame(loanApplication, iLoanApplicationServiceImpl.deleteLoanApplicationId(123L));
        verify(iLoanApplicationRepository).findById(any());
    }

    
    @Test
    void testDeleteLoanApplicationId2()  {
        when(iLoanApplicationRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> iLoanApplicationServiceImpl.deleteLoanApplicationId(123L));
        verify(iLoanApplicationRepository).findById(any());
    }

    
    @Test
    void testRetrieveAllLoanApplication() {
        ArrayList<LoanApplication> loanApplicationList = new ArrayList<>();
        when(iLoanApplicationRepository.findAll()).thenReturn(loanApplicationList);
        List<LoanApplication> actualRetrieveAllLoanApplicationResult = iLoanApplicationServiceImpl
                .retrieveAllLoanApplication();
        assertSame(loanApplicationList, actualRetrieveAllLoanApplicationResult);
        assertTrue(actualRetrieveAllLoanApplicationResult.isEmpty());
        verify(iLoanApplicationRepository).findAll();
    }

    
    @Test
    void testRetrieveLoanApplicationById() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("123456789012");
        customer.setCustomerName("Ramu");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("abcde@gmail.com");
        customer.setGender("Male");
        customer.setMobileNumber("1234567890");
        customer.setNationality("Indian");
        customer.setPanNumber("ABCDE1234F");
        customer.setPassword("Pass@123");
        customer.setRole("Role");
        customer.setUserId(123);

        EMI emi = new EMI();
        emi.setDeuDate(LocalDate.ofEpochDay(1L));
        emi.setEmiAmount(10.0d);
        emi.setEmiId(123L);
        emi.setInterestAmount(10.0d);
        emi.setLoanAmount(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setEmi(emi);
        loanAgreement.setLoanAgreementId(123L);

        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setAdminApproval(true);
        loanApplication.setApplicationDate(LocalDate.ofEpochDay(1L));
        loanApplication.setApplicationId(123L);
        loanApplication.setCustomer(customer);
        loanApplication.setFinanceVerificationApproval(true);
        loanApplication.setLandVerificationApproval(true);
        loanApplication.setLoanAgreement(loanAgreement);
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);
        when(iLoanApplicationRepository.findById(any())).thenReturn(ofResult);
        assertSame(loanApplication, iLoanApplicationServiceImpl.retrieveLoanApplicationById(123L));
        verify(iLoanApplicationRepository).findById(any());
    }

   
    @Test
    void testRetrieveLoanApplicationById2()  {
        when(iLoanApplicationRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> iLoanApplicationServiceImpl.retrieveLoanApplicationById(123L));
        verify(iLoanApplicationRepository).findById(any());
    }

    
    @Test
    void testAddLoanApplication()  {
        Customer customer = new Customer();
        customer.setAadharNumber("123456789012");
        customer.setCustomerName("Ramu");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("abcde@gmail.com");
        customer.setGender("Male");
        customer.setMobileNumber("1234567890");
        customer.setNationality("Indian");
        customer.setPanNumber("ABCDE1234F");
        customer.setPassword("Pass@123");
        customer.setRole("Role");
        customer.setUserId(123);
        Optional<Customer> ofResult = Optional.of(customer);
        when(iCustomerRepository.findById(any())).thenReturn(ofResult);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("123456789012");
        customer1.setCustomerName("Ramu");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("abcde@gmail.com");
        customer1.setGender("Male");
        customer1.setMobileNumber("1234567890");
        customer1.setNationality("Indian");
        customer1.setPanNumber("ABCDE1234F");
        customer1.setPassword("Pass@123");
        customer1.setRole("Role");
        customer1.setUserId(123);

        EMI emi = new EMI();
        emi.setDeuDate(LocalDate.ofEpochDay(1L));
        emi.setEmiAmount(10.0d);
        emi.setEmiId(123L);
        emi.setInterestAmount(10.0d);
        emi.setLoanAmount(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setEmi(emi);
        loanAgreement.setLoanAgreementId(123L);

        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setAdminApproval(true);
        loanApplication.setApplicationDate(LocalDate.ofEpochDay(1L));
        loanApplication.setApplicationId(123L);
        loanApplication.setCustomer(customer1);
        loanApplication.setFinanceVerificationApproval(true);
        loanApplication.setLandVerificationApproval(true);
        loanApplication.setLoanAgreement(loanAgreement);
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);
        when(iLoanApplicationRepository.findByCustomerId(anyInt())).thenReturn(loanApplication);
        assertThrows(CouldNotBeAddedException.class,
                () -> iLoanApplicationServiceImpl.addLoanApplication(new LoanApplicationDto()));
        verify(iLoanApplicationRepository).findByCustomerId(anyInt());
    }

    
    @Test
    void testUpdateLoanApplication() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("123456789012");
        customer.setCustomerName("Ramu");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("abcde@gmail.com");
        customer.setGender("Male");
        customer.setMobileNumber("1234567890");
        customer.setNationality("Indian");
        customer.setPanNumber("ABCDE1234F");
        customer.setPassword("Pass@123");
        customer.setRole("Role");
        customer.setUserId(123);

        EMI emi = new EMI();
        emi.setDeuDate(LocalDate.ofEpochDay(1L));
        emi.setEmiAmount(10.0d);
        emi.setEmiId(123L);
        emi.setInterestAmount(10.0d);
        emi.setLoanAmount(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setEmi(emi);
        loanAgreement.setLoanAgreementId(123L);

        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setAdminApproval(true);
        loanApplication.setApplicationDate(LocalDate.ofEpochDay(1L));
        loanApplication.setApplicationId(123L);
        loanApplication.setCustomer(customer);
        loanApplication.setFinanceVerificationApproval(true);
        loanApplication.setLandVerificationApproval(true);
        loanApplication.setLoanAgreement(loanAgreement);
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("123456789012");
        customer1.setCustomerName("Ramu");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("abcde@gmail.com");
        customer1.setGender("Male");
        customer1.setMobileNumber("1234567890");
        customer1.setNationality("Indian");
        customer1.setPanNumber("ABCDE1234F");
        customer1.setPassword("Pass@123");
        customer1.setRole("Role");
        customer1.setUserId(123);

        EMI emi1 = new EMI();
        emi1.setDeuDate(LocalDate.ofEpochDay(1L));
        emi1.setEmiAmount(10.0d);
        emi1.setEmiId(123L);
        emi1.setInterestAmount(10.0d);
        emi1.setLoanAmount(10.0d);

        LoanAgreement loanAgreement1 = new LoanAgreement();
        loanAgreement1.setEmi(emi1);
        loanAgreement1.setLoanAgreementId(123L);

        Scheme scheme1 = new Scheme();
        scheme1.setInterestRate(10.0d);
        scheme1.setSchemeId(123);
        scheme1.setTenure(1);

        LoanApplication loanApplication1 = new LoanApplication();
        loanApplication1.setAdminApproval(true);
        loanApplication1.setApplicationDate(LocalDate.ofEpochDay(1L));
        loanApplication1.setApplicationId(123L);
        loanApplication1.setCustomer(customer1);
        loanApplication1.setFinanceVerificationApproval(true);
        loanApplication1.setLandVerificationApproval(true);
        loanApplication1.setLoanAgreement(loanAgreement1);
        loanApplication1.setLoanAppliedAmount(10.0d);
        loanApplication1.setLoanApprovedAmount(10.0d);
        loanApplication1.setMonthlyExpenses(10.0d);
        loanApplication1.setOtherMonthlyExpenses(10.0d);
        loanApplication1.setScheme(scheme1);
        loanApplication1.setStatus("Status");
        loanApplication1.setTotalAnnualIncome(10.0d);
        when(iLoanApplicationRepository.save(any())).thenReturn(loanApplication1);
        when(iLoanApplicationRepository.findById(any())).thenReturn(ofResult);

        EMI emi2 = new EMI();
        emi2.setDeuDate(LocalDate.ofEpochDay(1L));
        emi2.setEmiAmount(10.0d);
        emi2.setEmiId(123L);
        emi2.setInterestAmount(10.0d);
        emi2.setLoanAmount(10.0d);
        when(iEMIRepository.save(any())).thenReturn(emi2);
        assertSame(loanApplication1, iLoanApplicationServiceImpl.updateLoanApplication(123L));
        verify(iLoanApplicationRepository).save(any());
        verify(iLoanApplicationRepository).findById(any());
        verify(iEMIRepository).save(any());
    }

    
    @Test
    void testUpdateStatusOfLoanApplication() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("123456789012");
        customer.setCustomerName("Ramu");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("abcde@gmail.com");
        customer.setGender("Male");
        customer.setMobileNumber("1234567890");
        customer.setNationality("Indian");
        customer.setPanNumber("ABCDE1234F");
        customer.setPassword("Pass@123");
        customer.setRole("Role");
        customer.setUserId(123);

        EMI emi = new EMI();
        emi.setDeuDate(LocalDate.ofEpochDay(1L));
        emi.setEmiAmount(10.0d);
        emi.setEmiId(123L);
        emi.setInterestAmount(10.0d);
        emi.setLoanAmount(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setEmi(emi);
        loanAgreement.setLoanAgreementId(123L);

        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setAdminApproval(true);
        loanApplication.setApplicationDate(LocalDate.ofEpochDay(1L));
        loanApplication.setApplicationId(123L);
        loanApplication.setCustomer(customer);
        loanApplication.setFinanceVerificationApproval(true);
        loanApplication.setLandVerificationApproval(true);
        loanApplication.setLoanAgreement(loanAgreement);
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("123456789012");
        customer1.setCustomerName("Ramu");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("abcde@gmail.com");
        customer1.setGender("Male");
        customer1.setMobileNumber("1234567890");
        customer1.setNationality("Indian");
        customer1.setPanNumber("ABCDE1234F");
        customer1.setPassword("Pass@123");
        customer1.setRole("Role");
        customer1.setUserId(123);

        EMI emi1 = new EMI();
        emi1.setDeuDate(LocalDate.ofEpochDay(1L));
        emi1.setEmiAmount(10.0d);
        emi1.setEmiId(123L);
        emi1.setInterestAmount(10.0d);
        emi1.setLoanAmount(10.0d);

        LoanAgreement loanAgreement1 = new LoanAgreement();
        loanAgreement1.setEmi(emi1);
        loanAgreement1.setLoanAgreementId(123L);

        Scheme scheme1 = new Scheme();
        scheme1.setInterestRate(10.0d);
        scheme1.setSchemeId(123);
        scheme1.setTenure(1);

        LoanApplication loanApplication1 = new LoanApplication();
        loanApplication1.setAdminApproval(true);
        loanApplication1.setApplicationDate(LocalDate.ofEpochDay(1L));
        loanApplication1.setApplicationId(123L);
        loanApplication1.setCustomer(customer1);
        loanApplication1.setFinanceVerificationApproval(true);
        loanApplication1.setLandVerificationApproval(true);
        loanApplication1.setLoanAgreement(loanAgreement1);
        loanApplication1.setLoanAppliedAmount(10.0d);
        loanApplication1.setLoanApprovedAmount(10.0d);
        loanApplication1.setMonthlyExpenses(10.0d);
        loanApplication1.setOtherMonthlyExpenses(10.0d);
        loanApplication1.setScheme(scheme1);
        loanApplication1.setStatus("Status");
        loanApplication1.setTotalAnnualIncome(10.0d);
        when(iLoanApplicationRepository.save(any())).thenReturn(loanApplication1);
        when(iLoanApplicationRepository.findById(any())).thenReturn(ofResult);
        assertSame(loanApplication1, iLoanApplicationServiceImpl.updateStatusOfLoanApplication(123L, Status.PENDING));
        verify(iLoanApplicationRepository).save(any());
        verify(iLoanApplicationRepository).findById(any());
    }

   
    @Test
    void testRetrieveLoanApplicationByStatus() {
        ArrayList<LoanApplication> loanApplicationList = new ArrayList<>();
        when(iLoanApplicationRepository.findByStatus(any())).thenReturn(loanApplicationList);
        List<LoanApplication> actualRetrieveLoanApplicationByStatusResult = iLoanApplicationServiceImpl
                .retrieveLoanApplicationByStatus("Status");
        assertSame(loanApplicationList, actualRetrieveLoanApplicationByStatusResult);
        assertTrue(actualRetrieveLoanApplicationByStatusResult.isEmpty());
        verify(iLoanApplicationRepository).findByStatus(any());
    }
}

