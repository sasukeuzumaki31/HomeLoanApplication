package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.dto.FinanceVerificationDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.FinanceVerificationOfficer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.repository.IFinanceVerificationRepository;
import com.group4.demo.repository.ILoanApplicationRepository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest

class IFinanceVerificationServiceImplTest {
    @MockBean
    private IFinanceVerificationRepository iFinanceVerificationRepository;

    @Autowired
    private IFinanceVerificationServiceImpl iFinanceVerificationServiceImpl;

    @MockBean
    private ILoanApplicationRepository iLoanApplicationRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;


    @Test
    void testAddFinanceVerificationOfficer() throws CouldNotBeAddedException {
        FinanceVerificationOfficer financeVerificationOfficer = new FinanceVerificationOfficer();
        financeVerificationOfficer.setFinOfficerContact("1234567890");
        financeVerificationOfficer.setFinOfficerName("Abcdef");
        financeVerificationOfficer.setPassword("A@bcd12345");
        financeVerificationOfficer.setRole("Finance_Verification_Officer");
        financeVerificationOfficer.setUserId(123);
        when(iFinanceVerificationRepository.save(any()))
                .thenReturn(financeVerificationOfficer);
        when(passwordEncoder.encode(any())).thenReturn("secret");
        assertSame(financeVerificationOfficer, iFinanceVerificationServiceImpl.addFinanceVerificationOfficer(
                new FinanceVerificationDto("Abcdef", "1234567890", 123, "A@bcd12345", "Finance_Verification_Officer")));
        verify(iFinanceVerificationRepository).save( any());
        verify(passwordEncoder).encode(any());
    }

    @Test
    void testUpdateStatus() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("123456789012");
        customer.setCustomerName("Abcdefg");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("abcdefg@gmail.com");
        customer.setGender("Male");
        customer.setMobileNumber("1234567890");
        customer.setNationality("Indian");
        customer.setPanNumber("ABCDE1234F");
        customer.setPassword("A@bcd12345");
        customer.setRole("Customer");
        customer.setUserId(123);

        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(3);
        scheme.setTenure(10);

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setAdminApproval(true);
        loanApplication.setApplicationDate(LocalDate.ofEpochDay(1L));
        loanApplication.setApplicationId(1L);
        loanApplication.setCustomer(customer);
        loanApplication.setFinanceVerificationApproval(true);
        loanApplication.setLandVerificationApproval(true);
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Pending");
        loanApplication.setTotalAnnualIncome(10.0d);
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("123456789012");
        customer1.setCustomerName("Rahul");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("abcdefg@gmail.com");
        customer1.setGender("Male");
        customer1.setMobileNumber("1234567890");
        customer1.setNationality("Indian");
        customer1.setPanNumber("ABCDE1234F");
        customer1.setPassword("A@bcd1234");
        customer1.setRole("Customer");
        customer1.setUserId(10);

        Scheme scheme1 = new Scheme();
        scheme1.setInterestRate(10.0d);
        scheme1.setSchemeId(1);
        scheme1.setTenure(10);

        LoanApplication loanApplication1 = new LoanApplication();
        loanApplication1.setAdminApproval(true);
        loanApplication1.setApplicationDate(LocalDate.ofEpochDay(1L));
        loanApplication1.setApplicationId(1L);
        loanApplication1.setCustomer(customer1);
        loanApplication1.setFinanceVerificationApproval(true);
        loanApplication1.setLandVerificationApproval(true);
        loanApplication1.setLoanAppliedAmount(10.0d);
        loanApplication1.setLoanApprovedAmount(10.0d);
        loanApplication1.setMonthlyExpenses(10.0d);
        loanApplication1.setOtherMonthlyExpenses(10.0d);
        loanApplication1.setScheme(scheme1);
        loanApplication1.setStatus("Pending");
        loanApplication1.setTotalAnnualIncome(10.0d);
        when(iLoanApplicationRepository.save(any())).thenReturn(loanApplication1);
        when(iLoanApplicationRepository.findById( any())).thenReturn(ofResult);
        LoanApplication actualUpdateStatusResult = iFinanceVerificationServiceImpl.updateStatus(1L);
        assertSame(loanApplication, actualUpdateStatusResult);
        assertFalse(actualUpdateStatusResult.isFinanceVerificationApproval());
        assertEquals("REJECTED", actualUpdateStatusResult.getStatus());
        assertEquals(0.0d, actualUpdateStatusResult.getLoanApprovedAmount());
        verify(iLoanApplicationRepository).save(any());
        verify(iLoanApplicationRepository).findById(any());
    }
}

