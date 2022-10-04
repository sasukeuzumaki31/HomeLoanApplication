package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.repository.ILoanAgreementRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest

class ILoanAgreementServiceImplTest {
    @MockBean
    private ILoanAgreementRepository iLoanAgreementRepository;

    @Autowired
    private ILoanAgreementServiceImpl iLoanAgreementServiceImpl;

    @Test
    void testDeleteLoanAgreement() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("42");
        customer.setCustomerName("Customer Name");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobileNumber("42");
        customer.setNationality("Nationality");
        customer.setPanNumber("42");
        customer.setPassword("iloveyou");
        customer.setRole("Role");
        customer.setUserId(123);

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
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setLoanAgreementId(123L);
        loanAgreement.setLoanApplication(loanApplication);
        Optional<LoanAgreement> ofResult = Optional.of(loanAgreement);
        doNothing().when(iLoanAgreementRepository).delete( any());
        when(iLoanAgreementRepository.findById(any())).thenReturn(ofResult);
        assertSame(loanAgreement, iLoanAgreementServiceImpl.deleteLoanAgreement(123L));
        verify(iLoanAgreementRepository).findById( any());
        verify(iLoanAgreementRepository).delete(any());
    }


    @Test
    void testDeleteLoanAgreement2()  {
        doNothing().when(iLoanAgreementRepository).delete(any());
        when(iLoanAgreementRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> iLoanAgreementServiceImpl.deleteLoanAgreement(123L));
        verify(iLoanAgreementRepository).findById(any());
    }


    @Test
    void testRetrieveAllLoanAgreement() {
        ArrayList<LoanAgreement> loanAgreementList = new ArrayList<>();
        when(iLoanAgreementRepository.findAll()).thenReturn(loanAgreementList);
        List<LoanAgreement> actualRetrieveAllLoanAgreementResult = iLoanAgreementServiceImpl.retrieveAllLoanAgreement();
        assertSame(loanAgreementList, actualRetrieveAllLoanAgreementResult);
        assertTrue(actualRetrieveAllLoanAgreementResult.isEmpty());
        verify(iLoanAgreementRepository).findAll();
    }


    @Test
    void testRetrieveAgreementById() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("42");
        customer.setCustomerName("Customer Name");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobileNumber("42");
        customer.setNationality("Nationality");
        customer.setPanNumber("42");
        customer.setPassword("iloveyou");
        customer.setRole("Role");
        customer.setUserId(123);

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
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setLoanAgreementId(123L);
        loanAgreement.setLoanApplication(loanApplication);
        Optional<LoanAgreement> ofResult = Optional.of(loanAgreement);
        when(iLoanAgreementRepository.findById(any())).thenReturn(ofResult);
        assertSame(loanAgreement, iLoanAgreementServiceImpl.retrieveAgreementById(123L));
        verify(iLoanAgreementRepository).findById( any());
    }


    @Test
    void testRetrieveAgreementById2() {
        when(iLoanAgreementRepository.findById( any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> iLoanAgreementServiceImpl.retrieveAgreementById(123L));
        verify(iLoanAgreementRepository).findById( any());
    }


    @Test
    void testAddLoanAgreement() {
        Customer customer = new Customer();
        customer.setAadharNumber("42");
        customer.setCustomerName("Customer Name");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobileNumber("42");
        customer.setNationality("Nationality");
        customer.setPanNumber("42");
        customer.setPassword("iloveyou");
        customer.setRole("Role");
        customer.setUserId(123);

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
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setLoanAgreementId(123L);
        loanAgreement.setLoanApplication(loanApplication);
        when(iLoanAgreementRepository.save(any())).thenReturn(loanAgreement);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("42");
        customer1.setCustomerName("Customer Name");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("jane.doe@example.org");
        customer1.setGender("Gender");
        customer1.setMobileNumber("42");
        customer1.setNationality("Nationality");
        customer1.setPanNumber("42");
        customer1.setPassword("iloveyou");
        customer1.setRole("Role");
        customer1.setUserId(123);

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
        loanApplication1.setLoanAppliedAmount(10.0d);
        loanApplication1.setLoanApprovedAmount(10.0d);
        loanApplication1.setMonthlyExpenses(10.0d);
        loanApplication1.setOtherMonthlyExpenses(10.0d);
        loanApplication1.setScheme(scheme1);
        loanApplication1.setStatus("Status");
        loanApplication1.setTotalAnnualIncome(10.0d);

        LoanAgreement loanAgreement1 = new LoanAgreement();
        loanAgreement1.setLoanAgreementId(123L);
        loanAgreement1.setLoanApplication(loanApplication1);
        assertSame(loanAgreement, iLoanAgreementServiceImpl.addLoanAgreement(loanAgreement1));
        verify(iLoanAgreementRepository).save(any());
    }


    @Test
    void testUpdateLoanAgreement() {
        Customer customer = new Customer();
        customer.setAadharNumber("42");
        customer.setCustomerName("Customer Name");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobileNumber("42");
        customer.setNationality("Nationality");
        customer.setPanNumber("42");
        customer.setPassword("iloveyou");
        customer.setRole("Role");
        customer.setUserId(123);

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
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setLoanAgreementId(123L);
        loanAgreement.setLoanApplication(loanApplication);
        when(iLoanAgreementRepository.save(any())).thenReturn(loanAgreement);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("42");
        customer1.setCustomerName("Customer Name");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("jane.doe@example.org");
        customer1.setGender("Gender");
        customer1.setMobileNumber("42");
        customer1.setNationality("Nationality");
        customer1.setPanNumber("42");
        customer1.setPassword("iloveyou");
        customer1.setRole("Role");
        customer1.setUserId(123);

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
        loanApplication1.setLoanAppliedAmount(10.0d);
        loanApplication1.setLoanApprovedAmount(10.0d);
        loanApplication1.setMonthlyExpenses(10.0d);
        loanApplication1.setOtherMonthlyExpenses(10.0d);
        loanApplication1.setScheme(scheme1);
        loanApplication1.setStatus("Status");
        loanApplication1.setTotalAnnualIncome(10.0d);

        LoanAgreement loanAgreement1 = new LoanAgreement();
        loanAgreement1.setLoanAgreementId(123L);
        loanAgreement1.setLoanApplication(loanApplication1);
        assertSame(loanAgreement, iLoanAgreementServiceImpl.updateLoanAgreement(loanAgreement1));
        verify(iLoanAgreementRepository).save( any());
    }
}

