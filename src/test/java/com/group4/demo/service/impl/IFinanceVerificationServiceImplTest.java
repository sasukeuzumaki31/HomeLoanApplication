package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    void testAddFinanceVerificationOfficer() {
        FinanceVerificationOfficer financeVerificationOfficer = new FinanceVerificationOfficer();
        financeVerificationOfficer.setFinOfficerContact("1234567890");
        financeVerificationOfficer.setFinOfficerName("Rajesh");
        financeVerificationOfficer.setPassword("Pass@123");
        financeVerificationOfficer.setRole("Role");
        financeVerificationOfficer.setUserId(123);

        FinanceVerificationOfficer financeVerificationOfficer1 = new FinanceVerificationOfficer();
        financeVerificationOfficer1.setFinOfficerContact("1234567890");
        financeVerificationOfficer1.setFinOfficerName("Rajesh");
        financeVerificationOfficer1.setPassword("Pass@123");
        financeVerificationOfficer1.setRole("Role");
        financeVerificationOfficer1.setUserId(123);
        when(iFinanceVerificationRepository.findByFinOfficerContact( any())).thenReturn(financeVerificationOfficer);
        when(iFinanceVerificationRepository.save(any()))
                .thenReturn(financeVerificationOfficer1);
        assertThrows(CouldNotBeAddedException.class, () -> iFinanceVerificationServiceImpl.addFinanceVerificationOfficer(
                new FinanceVerificationDto("Rajesh", "1234567890", 123, "Pass@123")));
        verify(iFinanceVerificationRepository).findByFinOfficerContact( any());
    }



    @Test
    void testUpdateStatus(){
        Customer customer = new Customer();
        customer.setAadharNumber("123456789012");
        customer.setCustomerName("Ramesh");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("ramesh123@example.com");
        customer.setGender("M");
        customer.setMobileNumber("1234567890");
        customer.setNationality("Indian");
        customer.setPanNumber("123456789");
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
        when(iLoanApplicationRepository.findById( any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> iFinanceVerificationServiceImpl.updateStatus(123L));
        verify(iLoanApplicationRepository).findById(any());
    }

    @Test
    void testUpdateStatus2(){
        when(iLoanApplicationRepository.findById( any())).thenReturn(Optional.empty());

        Customer customer = new Customer();
        customer.setAadharNumber("123456789012");
        customer.setCustomerName("Ramesh");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("ramesh123@example.com");
        customer.setGender("M");
        customer.setMobileNumber("1234567890");
        customer.setNationality("Indian");
        customer.setPanNumber("123456789");
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
        LoanApplication loanApplication = mock(LoanApplication.class);
        when(loanApplication.getStatus()).thenReturn("Status");
        doNothing().when(loanApplication).setAdminApproval(anyBoolean());
        doNothing().when(loanApplication).setApplicationDate( any());
        doNothing().when(loanApplication).setApplicationId(anyLong());
        doNothing().when(loanApplication).setCustomer(any());
        doNothing().when(loanApplication).setFinanceVerificationApproval(anyBoolean());
        doNothing().when(loanApplication).setLandVerificationApproval(anyBoolean());
        doNothing().when(loanApplication).setLoanAgreement(any());
        doNothing().when(loanApplication).setLoanAppliedAmount(anyDouble());
        doNothing().when(loanApplication).setLoanApprovedAmount(anyDouble());
        doNothing().when(loanApplication).setMonthlyExpenses(anyDouble());
        doNothing().when(loanApplication).setOtherMonthlyExpenses(anyDouble());
        doNothing().when(loanApplication).setScheme( any());
        doNothing().when(loanApplication).setStatus(any());
        doNothing().when(loanApplication).setTotalAnnualIncome(anyDouble());
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
        assertThrows(ResourceNotFoundException.class, () -> iFinanceVerificationServiceImpl.updateStatus(123L));
        verify(iLoanApplicationRepository).findById(any() );
        verify(loanApplication).setAdminApproval(anyBoolean());
        verify(loanApplication).setApplicationDate( any());
        verify(loanApplication).setApplicationId(anyLong());
        verify(loanApplication).setCustomer( any());
        verify(loanApplication).setFinanceVerificationApproval(anyBoolean());
        verify(loanApplication).setLandVerificationApproval(anyBoolean());
        verify(loanApplication).setLoanAgreement(any());
        verify(loanApplication).setLoanAppliedAmount(anyDouble());
        verify(loanApplication).setLoanApprovedAmount(anyDouble());
        verify(loanApplication).setMonthlyExpenses(anyDouble());
        verify(loanApplication).setOtherMonthlyExpenses(anyDouble());
        verify(loanApplication).setScheme(any());
        verify(loanApplication).setStatus(any());
        verify(loanApplication).setTotalAnnualIncome(anyDouble());
    }

    @Test
    void testLoginFinanceVerificationOfficer() throws AuthenticationFailedException {
        FinanceVerificationOfficer financeVerificationOfficer = new FinanceVerificationOfficer();
        financeVerificationOfficer.setFinOfficerContact("1234567890");
        financeVerificationOfficer.setFinOfficerName("Rajesh");
        financeVerificationOfficer.setPassword("Pass@123");
        financeVerificationOfficer.setRole("Role");
        financeVerificationOfficer.setUserId(123);
        Optional<FinanceVerificationOfficer> ofResult = Optional.of(financeVerificationOfficer);
        when(iFinanceVerificationRepository.findById( any())).thenReturn(ofResult);
        when(passwordEncoder.matches( any(),  any())).thenReturn(true);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("Pass@123");
        userLoginDto.setUserId(123);
        assertEquals("Login successfully", iFinanceVerificationServiceImpl.loginFinanceVerificationOfficer(userLoginDto));
        verify(iFinanceVerificationRepository).findById( any());
        verify(passwordEncoder).matches(any(), any());
    }

    @Test
    void testLoginFinanceVerificationOfficer2(){
        when(iFinanceVerificationRepository.findById( any())).thenReturn(Optional.empty());
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("Pass@123");
        userLoginDto.setUserId(123);
        assertThrows(AuthenticationFailedException.class,
                () -> iFinanceVerificationServiceImpl.loginFinanceVerificationOfficer(userLoginDto));
        verify(iFinanceVerificationRepository).findById( any());
    }

    @Test
    void testLoginFinanceVerificationOfficer3() throws AuthenticationFailedException {
        FinanceVerificationOfficer financeVerificationOfficer = new FinanceVerificationOfficer();
        financeVerificationOfficer.setFinOfficerContact("1234567890");
        financeVerificationOfficer.setFinOfficerName("Rajesh");
        financeVerificationOfficer.setPassword("Pass@123");
        financeVerificationOfficer.setRole("Role");
        financeVerificationOfficer.setUserId(123);
        Optional<FinanceVerificationOfficer> ofResult = Optional.of(financeVerificationOfficer);
        when(iFinanceVerificationRepository.findById( any())).thenReturn(ofResult);
        when(passwordEncoder.matches( any(),  any())).thenReturn(false);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("pass@123");
        userLoginDto.setUserId(123);
        assertEquals("Invalid Credentials",
                iFinanceVerificationServiceImpl.loginFinanceVerificationOfficer(userLoginDto));
        verify(iFinanceVerificationRepository).findById( any());
        verify(passwordEncoder).matches(any(), any());
    }
}

