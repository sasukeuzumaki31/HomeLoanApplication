package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group4.demo.advices.AuthenticationFailedException;
import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.dto.FinanceVerificationDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.EMI;
import com.group4.demo.entity.FinanceVerificationOfficer;
import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.repository.IFinanceVerificationRepository;
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

@ContextConfiguration(classes = {IFinanceVerificationServiceImpl.class})
@ExtendWith(SpringExtension.class)
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
        financeVerificationOfficer.setFinOfficerName("Fin Officer Name");
        financeVerificationOfficer.setPassword("Pass@123");
        financeVerificationOfficer.setRole("Role");
        financeVerificationOfficer.setUserId(123);

        FinanceVerificationOfficer financeVerificationOfficer1 = new FinanceVerificationOfficer();
        financeVerificationOfficer1.setFinOfficerContact("1234567890");
        financeVerificationOfficer1.setFinOfficerName("Fin Officer Name");
        financeVerificationOfficer1.setPassword("Pass@123");
        financeVerificationOfficer1.setRole("Role");
        financeVerificationOfficer1.setUserId(123);
        when(iFinanceVerificationRepository.findByFinOfficerContact(any())).thenReturn(financeVerificationOfficer);
        when(iFinanceVerificationRepository.save(any()))
                .thenReturn(financeVerificationOfficer1);
        assertThrows(CouldNotBeAddedException.class, () -> iFinanceVerificationServiceImpl.addFinanceVerificationOfficer(
                new FinanceVerificationDto("Fin Officer Name", "1234567890", 123, "Pass@123")));
        verify(iFinanceVerificationRepository).findByFinOfficerContact(any());
    }

    @Test
    void testUpdateStatus() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("42");
        customer.setCustomerName("Customer Name");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobileNumber("42");
        customer.setNationality("Nationality");
        customer.setPanNumber("42");
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
        customer1.setAadharNumber("42");
        customer1.setCustomerName("Customer Name");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("jane.doe@example.org");
        customer1.setGender("Gender");
        customer1.setMobileNumber("42");
        customer1.setNationality("Nationality");
        customer1.setPanNumber("42");
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
        when(iLoanApplicationRepository.findById( any())).thenReturn(ofResult);
        LoanApplication actualUpdateStatusResult = iFinanceVerificationServiceImpl.updateStatus(123L);
        assertSame(loanApplication, actualUpdateStatusResult);
        assertFalse(actualUpdateStatusResult.isFinanceVerificationApproval());
        assertEquals("REJECTED", actualUpdateStatusResult.getStatus());
        assertEquals(0.0d, actualUpdateStatusResult.getLoanApprovedAmount());
        verify(iLoanApplicationRepository).save(any());
        verify(iLoanApplicationRepository).findById( any());
    }

    @Test
    void testLoginFinanceVerificationOfficer() throws AuthenticationFailedException {
        FinanceVerificationOfficer financeVerificationOfficer = new FinanceVerificationOfficer();
        financeVerificationOfficer.setFinOfficerContact("1234567890");
        financeVerificationOfficer.setFinOfficerName("Fin Officer Name");
        financeVerificationOfficer.setPassword("Pass@123");
        financeVerificationOfficer.setRole("Role");
        financeVerificationOfficer.setUserId(123);
        Optional<FinanceVerificationOfficer> ofResult = Optional.of(financeVerificationOfficer);
        when(iFinanceVerificationRepository.findById(any())).thenReturn(ofResult);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("Pass@123");
        userLoginDto.setUserId(123);
        assertEquals("Login successfully", iFinanceVerificationServiceImpl.loginFinanceVerificationOfficer(userLoginDto));
        verify(iFinanceVerificationRepository).findById(any());
        verify(passwordEncoder).matches(any(), any());
    }

    @Test
    void testLoginFinanceVerificationOfficer2() throws AuthenticationFailedException {
        when(iFinanceVerificationRepository.findById(any())).thenReturn(Optional.empty());
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("Pass@123");
        userLoginDto.setUserId(123);
        assertThrows(AuthenticationFailedException.class,
                () -> iFinanceVerificationServiceImpl.loginFinanceVerificationOfficer(userLoginDto));
        verify(iFinanceVerificationRepository).findById(any());
    }

    @Test
    void testLoginFinanceVerificationOfficer3() throws AuthenticationFailedException {
        FinanceVerificationOfficer financeVerificationOfficer = new FinanceVerificationOfficer();
        financeVerificationOfficer.setFinOfficerContact("1234567890");
        financeVerificationOfficer.setFinOfficerName("Fin Officer Name");
        financeVerificationOfficer.setPassword("Pass@123");
        financeVerificationOfficer.setRole("Role");
        financeVerificationOfficer.setUserId(123);
        Optional<FinanceVerificationOfficer> ofResult = Optional.of(financeVerificationOfficer);
        when(iFinanceVerificationRepository.findById(any())).thenReturn(ofResult);
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("Pass@123");
        userLoginDto.setUserId(123);
        assertEquals("Invalid Credentials",
                iFinanceVerificationServiceImpl.loginFinanceVerificationOfficer(userLoginDto));
        verify(iFinanceVerificationRepository).findById(any());
        verify(passwordEncoder).matches(any(), any());
    }
}

