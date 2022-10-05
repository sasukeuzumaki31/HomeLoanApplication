package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
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
    void testDeleteLoanApplicationId2() {
        when(iLoanApplicationRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> iLoanApplicationServiceImpl.deleteLoanApplicationId(123L));
        verify(iLoanApplicationRepository).findById(any());
    }

    /**
     * Method under test: {@link ILoanApplicationServiceImpl#deleteLoanApplicationId(long)}
     */
    @Test
    void testDeleteLoanApplicationId3() throws ResourceNotFoundException {
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
        assertSame(loanApplication, iLoanApplicationServiceImpl.deleteLoanApplicationId(123L));
        verify(iLoanApplicationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ILoanApplicationServiceImpl#deleteLoanApplicationId(long)}
     */
    @Test
    void testDeleteLoanApplicationId4() throws ResourceNotFoundException {
        when(iLoanApplicationRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> iLoanApplicationServiceImpl.deleteLoanApplicationId(123L));
        verify(iLoanApplicationRepository).findById((Long) any());
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
    void testRetrieveLoanApplicationById2() {
        when(iLoanApplicationRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> iLoanApplicationServiceImpl.retrieveLoanApplicationById(123L));
        verify(iLoanApplicationRepository).findById(any());
    }

    /**
     * Method under test: {@link ILoanApplicationServiceImpl#retrieveLoanApplicationById(Long)}
     */
    @Test
    void testRetrieveLoanApplicationById3() throws ResourceNotFoundException {
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
        assertSame(loanApplication, iLoanApplicationServiceImpl.retrieveLoanApplicationById(123L));
        verify(iLoanApplicationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ILoanApplicationServiceImpl#retrieveLoanApplicationById(Long)}
     */
    @Test
    void testRetrieveLoanApplicationById4() throws ResourceNotFoundException {
        when(iLoanApplicationRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> iLoanApplicationServiceImpl.retrieveLoanApplicationById(123L));
        verify(iLoanApplicationRepository).findById((Long) any());
    }


    @Test
    void testAddLoanApplication() {
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

    /**
     * Method under test: {@link ILoanApplicationServiceImpl#addLoanApplication(LoanApplicationDto)}
     */
    @Test
    void testAddLoanApplication2() throws CouldNotBeAddedException, ResourceNotFoundException {
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
        Optional<Customer> ofResult = Optional.of(customer);
        when(iCustomerRepository.findById((Integer) any())).thenReturn(ofResult);

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

    /**
     * Method under test: {@link ILoanApplicationServiceImpl#updateLoanApplication(long)}
     */
    @Test
    void testUpdateLoanApplication() throws ResourceNotFoundException {
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
        assertThrows(ResourceNotFoundException.class, () -> iLoanApplicationServiceImpl.updateLoanApplication(123L));
        verify(iLoanApplicationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ILoanApplicationServiceImpl#updateLoanApplication(long)}
     */
    @Test
    void testUpdateLoanApplication2() throws ResourceNotFoundException {
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
        assertThrows(ResourceNotFoundException.class, () -> iLoanApplicationServiceImpl.updateLoanApplication(123L));
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
     * Method under test: {@link ILoanApplicationServiceImpl#updateStatusOfLoanApplication(Long, Status)}
     */
    @Test
    void testUpdateStatusOfLoanApplication() throws ResourceNotFoundException {
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
        assertThrows(ResourceNotFoundException.class,
                () -> iLoanApplicationServiceImpl.updateStatusOfLoanApplication(123L, Status.PENDING));
        verify(iLoanApplicationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ILoanApplicationServiceImpl#updateStatusOfLoanApplication(Long, Status)}
     */
    @Test
    void testUpdateStatusOfLoanApplication2() throws ResourceNotFoundException {
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
        assertThrows(ResourceNotFoundException.class,
                () -> iLoanApplicationServiceImpl.updateStatusOfLoanApplication(123L, Status.PENDING));
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
     * Method under test: {@link ILoanApplicationServiceImpl#updateStatusOfLoanApplication(Long, Status)}
     */
    @Test
    void testUpdateStatusOfLoanApplication3() throws ResourceNotFoundException {
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
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);
        when(iLoanApplicationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class,
                () -> iLoanApplicationServiceImpl.updateStatusOfLoanApplication(123L, Status.APPROVED));
        verify(iLoanApplicationRepository).findById((Long) any());
        verify(loanApplication).getStatus();
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
     * Method under test: {@link ILoanApplicationServiceImpl#updateStatusOfLoanApplication(Long, Status)}
     */
    @Test
    void testUpdateStatusOfLoanApplication4() throws ResourceNotFoundException {
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
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);
        when(iLoanApplicationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class,
                () -> iLoanApplicationServiceImpl.updateStatusOfLoanApplication(123L, Status.WAITING_FOR_FINANCE_APPROVAL));
        verify(iLoanApplicationRepository).findById((Long) any());
        verify(loanApplication).getStatus();
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
     * Method under test: {@link ILoanApplicationServiceImpl#updateStatusOfLoanApplication(Long, Status)}
     */
    @Test
    void testUpdateStatusOfLoanApplication5() throws ResourceNotFoundException {
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
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);
        when(iLoanApplicationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> iLoanApplicationServiceImpl
                .updateStatusOfLoanApplication(123L, Status.WAITING_FOR_LAND_VERIFICATION_OFFICE_APPROVAL));
        verify(iLoanApplicationRepository).findById((Long) any());
        verify(loanApplication).getStatus();
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
     * Method under test: {@link ILoanApplicationServiceImpl#updateStatusOfLoanApplication(Long, Status)}
     */
    @Test
    void testUpdateStatusOfLoanApplication6() throws ResourceNotFoundException {
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
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);
        when(iLoanApplicationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class,
                () -> iLoanApplicationServiceImpl.updateStatusOfLoanApplication(123L, Status.REJECTED));
        verify(iLoanApplicationRepository).findById((Long) any());
        verify(loanApplication).getStatus();
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
     * Method under test: {@link ILoanApplicationServiceImpl#updateStatusOfLoanApplication(Long, Status)}
     */
    @Test
    void testUpdateStatusOfLoanApplication7() throws ResourceNotFoundException {
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
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);
        when(iLoanApplicationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class,
                () -> iLoanApplicationServiceImpl.updateStatusOfLoanApplication(123L, Status.DOCUMENTS_NOT_UPLOADED));
        verify(iLoanApplicationRepository).findById((Long) any());
        verify(loanApplication).getStatus();
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
     * Method under test: {@link ILoanApplicationServiceImpl#updateStatusOfLoanApplication(Long, Status)}
     */
    @Test
    void testUpdateStatusOfLoanApplication8() throws ResourceNotFoundException {
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
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);
        when(iLoanApplicationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class,
                () -> iLoanApplicationServiceImpl.updateStatusOfLoanApplication(123L, Status.DOCUMENTS_UPLOADED));
        verify(iLoanApplicationRepository).findById((Long) any());
        verify(loanApplication).getStatus();
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

    /**
     * Method under test: {@link ILoanApplicationServiceImpl#retrieveLoanApplicationByStatus(String)}
     */
    @Test
    void testRetrieveLoanApplicationByStatus2() {
        ArrayList<LoanApplication> loanApplicationList = new ArrayList<>();
        when(iLoanApplicationRepository.findByStatus((String) any())).thenReturn(loanApplicationList);
        List<LoanApplication> actualRetrieveLoanApplicationByStatusResult = iLoanApplicationServiceImpl
                .retrieveLoanApplicationByStatus("Status");
        assertSame(loanApplicationList, actualRetrieveLoanApplicationByStatusResult);
        assertTrue(actualRetrieveLoanApplicationByStatusResult.isEmpty());
        verify(iLoanApplicationRepository).findByStatus((String) any());
    }
}

