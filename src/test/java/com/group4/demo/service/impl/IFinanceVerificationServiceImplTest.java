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

    /**
     * Method under test: {@link IFinanceVerificationServiceImpl#addFinanceVerificationOfficer(FinanceVerificationDto)}
     */
    @Test
    void testAddFinanceVerificationOfficer() throws CouldNotBeAddedException {
        FinanceVerificationOfficer financeVerificationOfficer = new FinanceVerificationOfficer();
        financeVerificationOfficer.setFinOfficerContact("Fin Officer Contact");
        financeVerificationOfficer.setFinOfficerName("Fin Officer Name");
        financeVerificationOfficer.setPassword("iloveyou");
        financeVerificationOfficer.setRole("Role");
        financeVerificationOfficer.setUserId(123);

        FinanceVerificationOfficer financeVerificationOfficer1 = new FinanceVerificationOfficer();
        financeVerificationOfficer1.setFinOfficerContact("Fin Officer Contact");
        financeVerificationOfficer1.setFinOfficerName("Fin Officer Name");
        financeVerificationOfficer1.setPassword("iloveyou");
        financeVerificationOfficer1.setRole("Role");
        financeVerificationOfficer1.setUserId(123);
        when(iFinanceVerificationRepository.findByFinOfficerContact((String) any())).thenReturn(financeVerificationOfficer);
        when(iFinanceVerificationRepository.save((FinanceVerificationOfficer) any()))
                .thenReturn(financeVerificationOfficer1);
        assertThrows(CouldNotBeAddedException.class, () -> iFinanceVerificationServiceImpl.addFinanceVerificationOfficer(
                new FinanceVerificationDto("Fin Officer Name", "Fin Officer Contact", 123, "iloveyou")));
        verify(iFinanceVerificationRepository).findByFinOfficerContact((String) any());
    }

    /**
     * Method under test: {@link IFinanceVerificationServiceImpl#addFinanceVerificationOfficer(FinanceVerificationDto)}
     */

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
        customer.setPassword("iloveyou");
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
        when(iLoanApplicationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> iFinanceVerificationServiceImpl.updateStatus(123L));
        verify(iLoanApplicationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link IFinanceVerificationServiceImpl#updateStatus(Long)}
     */
    @Test
    void testUpdateStatus2() throws ResourceNotFoundException {
        when(iLoanApplicationRepository.findById((Long) any())).thenReturn(Optional.empty());

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
        doNothing().when(loanApplication).setApplicationDate((LocalDate) any());
        doNothing().when(loanApplication).setApplicationId(anyLong());
        doNothing().when(loanApplication).setCustomer((Customer) any());
        doNothing().when(loanApplication).setFinanceVerificationApproval(anyBoolean());
        doNothing().when(loanApplication).setLandVerificationApproval(anyBoolean());
        doNothing().when(loanApplication).setLoanAgreement((LoanAgreement) any());
        doNothing().when(loanApplication).setLoanAppliedAmount(anyDouble());
        doNothing().when(loanApplication).setLoanApprovedAmount(anyDouble());
        doNothing().when(loanApplication).setMonthlyExpenses(anyDouble());
        doNothing().when(loanApplication).setOtherMonthlyExpenses(anyDouble());
        doNothing().when(loanApplication).setScheme((Scheme) any());
        doNothing().when(loanApplication).setStatus((String) any());
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
        verify(iLoanApplicationRepository).findById((Long) any());
        verify(loanApplication).setAdminApproval(anyBoolean());
        verify(loanApplication).setApplicationDate((LocalDate) any());
        verify(loanApplication).setApplicationId(anyLong());
        verify(loanApplication).setCustomer((Customer) any());
        verify(loanApplication).setFinanceVerificationApproval(anyBoolean());
        verify(loanApplication).setLandVerificationApproval(anyBoolean());
        verify(loanApplication).setLoanAgreement((LoanAgreement) any());
        verify(loanApplication).setLoanAppliedAmount(anyDouble());
        verify(loanApplication).setLoanApprovedAmount(anyDouble());
        verify(loanApplication).setMonthlyExpenses(anyDouble());
        verify(loanApplication).setOtherMonthlyExpenses(anyDouble());
        verify(loanApplication).setScheme((Scheme) any());
        verify(loanApplication).setStatus((String) any());
        verify(loanApplication).setTotalAnnualIncome(anyDouble());
    }

    /**
     * Method under test: {@link IFinanceVerificationServiceImpl#loginFinanceVerificationOfficer(UserLoginDto)}
     */
    @Test
    void testLoginFinanceVerificationOfficer() throws AuthenticationFailedException {
        FinanceVerificationOfficer financeVerificationOfficer = new FinanceVerificationOfficer();
        financeVerificationOfficer.setFinOfficerContact("Fin Officer Contact");
        financeVerificationOfficer.setFinOfficerName("Fin Officer Name");
        financeVerificationOfficer.setPassword("iloveyou");
        financeVerificationOfficer.setRole("Role");
        financeVerificationOfficer.setUserId(123);
        Optional<FinanceVerificationOfficer> ofResult = Optional.of(financeVerificationOfficer);
        when(iFinanceVerificationRepository.findById((Integer) any())).thenReturn(ofResult);
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(true);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("iloveyou");
        userLoginDto.setUserId(123);
        assertEquals("Login successfully", iFinanceVerificationServiceImpl.loginFinanceVerificationOfficer(userLoginDto));
        verify(iFinanceVerificationRepository).findById((Integer) any());
        verify(passwordEncoder).matches((CharSequence) any(), (String) any());
    }

    /**
     * Method under test: {@link IFinanceVerificationServiceImpl#loginFinanceVerificationOfficer(UserLoginDto)}
     */
    @Test
    void testLoginFinanceVerificationOfficer2() throws AuthenticationFailedException {
        when(iFinanceVerificationRepository.findById((Integer) any())).thenReturn(Optional.empty());
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(true);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("iloveyou");
        userLoginDto.setUserId(123);
        assertThrows(AuthenticationFailedException.class,
                () -> iFinanceVerificationServiceImpl.loginFinanceVerificationOfficer(userLoginDto));
        verify(iFinanceVerificationRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link IFinanceVerificationServiceImpl#loginFinanceVerificationOfficer(UserLoginDto)}
     */
    @Test
    void testLoginFinanceVerificationOfficer3() throws AuthenticationFailedException {
        FinanceVerificationOfficer financeVerificationOfficer = new FinanceVerificationOfficer();
        financeVerificationOfficer.setFinOfficerContact("Fin Officer Contact");
        financeVerificationOfficer.setFinOfficerName("Fin Officer Name");
        financeVerificationOfficer.setPassword("iloveyou");
        financeVerificationOfficer.setRole("Role");
        financeVerificationOfficer.setUserId(123);
        Optional<FinanceVerificationOfficer> ofResult = Optional.of(financeVerificationOfficer);
        when(iFinanceVerificationRepository.findById((Integer) any())).thenReturn(ofResult);
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(false);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("iloveyou");
        userLoginDto.setUserId(123);
        assertEquals("Invalid Credentials",
                iFinanceVerificationServiceImpl.loginFinanceVerificationOfficer(userLoginDto));
        verify(iFinanceVerificationRepository).findById((Integer) any());
        verify(passwordEncoder).matches((CharSequence) any(), (String) any());
    }
}

