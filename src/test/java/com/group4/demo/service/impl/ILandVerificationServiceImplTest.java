package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group4.demo.advices.AuthenticationFailedException;
import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.dto.LandVerificationOfficerDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.EMI;
import com.group4.demo.entity.LandVerificationOfficer;
import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.repository.ILandVerificationRepository;
import com.group4.demo.repository.ILoanApplicationRepository;

import java.time.LocalDate;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
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
        customer.setAadharNumber("123456789987");
        customer.setCustomerName("Customer Name");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("abc.def@xmail.com");
        customer.setGender("M");
        customer.setMobileNumber("1234567890");
        customer.setNationality("INDIAN");
        customer.setPanNumber("abcde5552e");
        customer.setPassword("Pass@123");
        customer.setRole("CUSTOMER");
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
        loanApplication.setStatus("PENDING");
        loanApplication.setTotalAnnualIncome(10.0d);
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("123456789987");
        customer1.setCustomerName("Customer Name");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("abc.def@xmail.com");
        customer1.setGender("M");
        customer1.setMobileNumber("1234567890");
        customer1.setNationality("INDIAN");
        customer1.setPanNumber("abcde5552e");
        customer1.setPassword("Pass@123");
        customer1.setRole("CUSTOMER");
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
        loanApplication1.setStatus("PENDING");
        loanApplication1.setTotalAnnualIncome(10.0d);
        when(iLoanApplicationRepository.save(any())).thenReturn(loanApplication1);
        when(iLoanApplicationRepository.findById( any())).thenReturn(ofResult);

        Customer customer2 = new Customer();
        customer2.setAadharNumber("123456789987");
        customer2.setCustomerName("Customer Name");
        customer2.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer2.setEmail("abc.def@xmail.com");
        customer2.setGender("M");
        customer2.setMobileNumber("1234567890");
        customer2.setNationality("INDIAN");
        customer2.setPanNumber("abcde5552e");
        customer2.setPassword("Pass@123");
        customer2.setRole("CUSTOMER");
        customer2.setUserId(123);

        EMI emi2 = new EMI();
        emi2.setDeuDate(LocalDate.ofEpochDay(1L));
        emi2.setEmiAmount(10.0d);
        emi2.setEmiId(123L);
        emi2.setInterestAmount(10.0d);
        emi2.setLoanAmount(10.0d);

        LoanAgreement loanAgreement2 = new LoanAgreement();
        loanAgreement2.setEmi(emi2);
        loanAgreement2.setLoanAgreementId(123L);

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
        loanApplication2.setLoanAgreement(loanAgreement2);
        loanApplication2.setLoanAppliedAmount(10.0d);
        loanApplication2.setLoanApprovedAmount(10.0d);
        loanApplication2.setMonthlyExpenses(10.0d);
        loanApplication2.setOtherMonthlyExpenses(10.0d);
        loanApplication2.setScheme(scheme2);
        loanApplication2.setStatus("PENDING");
        loanApplication2.setTotalAnnualIncome(10.0d);
        iLandVerificationServiceImpl.updateStatus(loanApplication2);
        verify(iLoanApplicationRepository).save(any());
        verify(iLoanApplicationRepository).findById( any());
        assertTrue(loanApplication2.isLandVerificationApproval());
    }

    @Test
    void testAddLandVerificationOfficer() throws CouldNotBeAddedException {
        LandVerificationOfficer landVerificationOfficer = new LandVerificationOfficer();
        landVerificationOfficer.setOfficeContact("1234567890");
        landVerificationOfficer.setOfficeName("Office Name");
        landVerificationOfficer.setPassword("Pass@123");
        landVerificationOfficer.setRole("Land_Verification_officer");
        landVerificationOfficer.setUserId(123);

        LandVerificationOfficer landVerificationOfficer1 = new LandVerificationOfficer();
        landVerificationOfficer1.setOfficeContact("1234567890");
        landVerificationOfficer1.setOfficeName("Office Name");
        landVerificationOfficer1.setPassword("Pass@123");
        landVerificationOfficer1.setRole("Land_Verification_officer");
        landVerificationOfficer1.setUserId(123);
        when(iLandVerificationRepository.findByOfficeContact( any())).thenReturn(landVerificationOfficer);
        when(iLandVerificationRepository.save(any())).thenReturn(landVerificationOfficer1);
        assertThrows(CouldNotBeAddedException.class, () -> iLandVerificationServiceImpl
                .addLandVerificationOfficer(new LandVerificationOfficerDto("Office Name", "1234567890", 123, "Pass@123")));
        verify(iLandVerificationRepository).findByOfficeContact( any());
    }
    
    @Test
    void testLoginLandOfficer() throws AuthenticationFailedException {
        LandVerificationOfficer landVerificationOfficer = new LandVerificationOfficer();
        landVerificationOfficer.setOfficeContact("1234567890");
        landVerificationOfficer.setOfficeName("Office Name");
        landVerificationOfficer.setPassword("Pass@123");
        landVerificationOfficer.setRole("Land_Verification_officer");
        landVerificationOfficer.setUserId(123);
        Optional<LandVerificationOfficer> ofResult = Optional.of(landVerificationOfficer);
        when(iLandVerificationRepository.findById(any())).thenReturn(ofResult);
        when(passwordEncoder.matches(any(),  any())).thenReturn(true);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("Pass@123");
        userLoginDto.setUserId(123);
        assertEquals("Login successfully", iLandVerificationServiceImpl.loginLandOfficer(userLoginDto));
        verify(iLandVerificationRepository).findById(any());
        verify(passwordEncoder).matches(any(),  any());
    }

    @Test
    void testLoginLandOfficer2() throws AuthenticationFailedException {
        when(iLandVerificationRepository.findById(any())).thenReturn(Optional.empty());
        when(passwordEncoder.matches(any(),  any())).thenReturn(true);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("Pass@123");
        userLoginDto.setUserId(123);
        assertThrows(AuthenticationFailedException.class,
                () -> iLandVerificationServiceImpl.loginLandOfficer(userLoginDto));
        verify(iLandVerificationRepository).findById(any());
    }

    @Test
    void testLoginLandOfficer3() throws AuthenticationFailedException {
        LandVerificationOfficer landVerificationOfficer = new LandVerificationOfficer();
        landVerificationOfficer.setOfficeContact("1234567890");
        landVerificationOfficer.setOfficeName("Office Name");
        landVerificationOfficer.setPassword("Pass@123");
        landVerificationOfficer.setRole("Land_Verification_officer");
        landVerificationOfficer.setUserId(123);
        Optional<LandVerificationOfficer> ofResult = Optional.of(landVerificationOfficer);
        when(iLandVerificationRepository.findById(any())).thenReturn(ofResult);
        when(passwordEncoder.matches(any(),  any())).thenReturn(false);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("Pass@123");
        userLoginDto.setUserId(123);
        assertEquals("Invalid Credentials", iLandVerificationServiceImpl.loginLandOfficer(userLoginDto));
        verify(iLandVerificationRepository).findById(any());
        verify(passwordEncoder).matches(any(),  any());
    }
}

