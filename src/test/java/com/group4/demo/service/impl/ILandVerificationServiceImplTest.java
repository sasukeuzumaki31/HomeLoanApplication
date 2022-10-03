package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group4.demo.Dto.LandVerificationOfficerDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.LandVerificationOfficer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.repository.ILandVerificationRepository;
import com.group4.demo.repository.ILoanApplicationRepository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ILandVerificationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ILandVerificationServiceImplTest {
    @MockBean
    private ILandVerificationRepository iLandVerificationRepository;

    @Autowired
    private ILandVerificationServiceImpl iLandVerificationServiceImpl;

    @MockBean
    private ILoanApplicationRepository iLoanApplicationRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void testUpdateStatus() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("123456789876");
        customer.setCustomerName("Customer Name");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("test.customer@xmail.com");
        customer.setGender("MALE");
        customer.setMobileNumber("1234567890");
        customer.setNationality("Nationality");
        customer.setPanNumber("ABCDE5552E");
        customer.setPassword("Pass@123");
        customer.setRole("CUSTOMER");
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
        loanApplication.setStatus("PENDING");
        loanApplication.setTotalAnnualIncome(10.0d);
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("ABCDE5553R");
        customer1.setCustomerName("Customer Name");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("test.customer@xmail.com");
        customer1.setGender("MALE");
        customer1.setMobileNumber("1234567890");
        customer1.setNationality("Nationality");
        customer1.setPanNumber("ABCDE5552E");
        customer1.setPassword("Pass@123");
        customer1.setRole("CUSTOMER");
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
        loanApplication1.setStatus("PENDING");
        loanApplication1.setTotalAnnualIncome(10.0d);
        when(iLoanApplicationRepository.save(any())).thenReturn(loanApplication1);
        when(iLoanApplicationRepository.findById(any())).thenReturn(ofResult);

        Customer customer2 = new Customer();
        customer2.setAadharNumber("ABCDE5553R");
        customer2.setCustomerName("Customer Name");
        customer2.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer2.setEmail("test.customer@xmail.com");
        customer2.setGender("MALE");
        customer2.setMobileNumber("1234567890");
        customer2.setNationality("Nationality");
        customer2.setPanNumber("ABCDE5552E");
        customer2.setPassword("Pass@123");
        customer2.setRole("CUSTOMER");
        customer2.setUserId(123);

        Scheme scheme2 = new Scheme();
        scheme2.setInterestRate(10.0d);
        scheme2.setSchemeId(123);
        scheme2.setTenure(1);

        LoanApplication loanApplication2 = new LoanApplication();
        loanApplication2.setAdminApproval(true);
        loanApplication2.setApplicationDate(LocalDate.ofEpochDay(1L));
        loanApplication2.setApplicationId(123L);
        loanApplication2.setCustomer(customer2);
        loanApplication2.setFinanceVerificationApproval(true);
        loanApplication2.setLandVerificationApproval(true);
        loanApplication2.setLoanAppliedAmount(10.0d);
        loanApplication2.setLoanApprovedAmount(10.0d);
        loanApplication2.setMonthlyExpenses(10.0d);
        loanApplication2.setOtherMonthlyExpenses(10.0d);
        loanApplication2.setScheme(scheme2);
        loanApplication2.setStatus("PENDING");
        loanApplication2.setTotalAnnualIncome(10.0d);
        iLandVerificationServiceImpl.updateStatus(loanApplication2);
        verify(iLoanApplicationRepository).save(any());
        verify(iLoanApplicationRepository).findById(any());
        assertTrue(loanApplication2.isLandVerificationApproval());
    }

    @Test
    void testAddLandVerificationOfficer() {
        LandVerificationOfficer landVerificationOfficer = new LandVerificationOfficer();
        landVerificationOfficer.setOfficeContact("1234567890");
        landVerificationOfficer.setOfficeName("Office Name");
        landVerificationOfficer.setPassword("Pass@123");
        landVerificationOfficer.setRole("LAND_VERIFICATION_OFFICER");
        landVerificationOfficer.setUserId(123);
        when(iLandVerificationRepository.save(any())).thenReturn(landVerificationOfficer);
        when(passwordEncoder.encode(any())).thenReturn("secret");
        assertSame(landVerificationOfficer, iLandVerificationServiceImpl.addLandVerificationOfficer(
                new LandVerificationOfficerDto("Office Name", "1234567890", 123, "Pass@123", "LAND_VERIFICATION_OFFICER")));
        verify(iLandVerificationRepository).save(any());
        verify(passwordEncoder).encode(any());
    }

}

