package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group4.demo.dto.LoanApplicationDto;
import com.group4.demo.advices.ResourceNotFoundException;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ILoanApplicationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class LoanApplicationServiceImplTest {
    @MockBean
    private ICustomerRepository iCustomerRepository;

    @MockBean
    private IEMIRepository iEMIRepository;

    @MockBean
    private ILoanApplicationRepository iLoanApplicationRepository;

    @MockBean
    private ISchemeRepository iSchemeRepository;

    @Autowired
    private ILoanApplicationServiceImpl loanApplicationServiceImpl;

    @Test
    void testDeleteLoanApplicationId() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("123456789123");
        customer.setCustomerName("Rajesh Kumar");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("rajkmr123@hmail.com");
        customer.setGender("Male");
        customer.setMobileNumber("1234567891");
        customer.setNationality("Indian");
        customer.setPanNumber("123456789");
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
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);
        when(iLoanApplicationRepository.findById(any())).thenReturn(ofResult);
        assertSame(loanApplication, loanApplicationServiceImpl.deleteLoanApplicationId(123L));
        verify(iLoanApplicationRepository).findById(any());
    }


    @Test
    void testDeleteLoanApplicationId2()  {
        when(iLoanApplicationRepository.findById( any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> loanApplicationServiceImpl.deleteLoanApplicationId(123L));
        verify(iLoanApplicationRepository).findById(any());
    }


    @Test
    void testRetrieveAllLoanApplication() {
        ArrayList<LoanApplication> loanApplicationList = new ArrayList<>();
        when(iLoanApplicationRepository.findAll()).thenReturn(loanApplicationList);
        List<LoanApplication> actualRetrieveAllLoanApplicationResult = loanApplicationServiceImpl
                .retrieveAllLoanApplication();
        assertSame(loanApplicationList, actualRetrieveAllLoanApplicationResult);
        assertTrue(actualRetrieveAllLoanApplicationResult.isEmpty());
        verify(iLoanApplicationRepository).findAll();
    }


    @Test
    void testRetrieveLoanApplicationById() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("123456789123");
        customer.setCustomerName("Rajesh Kumar");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("rajkmr123@hmail.com");
        customer.setGender("Male");
        customer.setMobileNumber("1234567891");
        customer.setNationality("Indian");
        customer.setPanNumber("123456789");
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
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);
        when(iLoanApplicationRepository.findById(any())).thenReturn(ofResult);
        assertSame(loanApplication, loanApplicationServiceImpl.retrieveLoanApplicationById(123L));
        verify(iLoanApplicationRepository).findById( any());
    }

    @Test
    void testRetrieveLoanApplicationById2(){
        when(iLoanApplicationRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> loanApplicationServiceImpl.retrieveLoanApplicationById(123L));
        verify(iLoanApplicationRepository).findById(any());
    }

    @Test
    void testAddLoanApplication() throws Exception {
        Customer customer = new Customer();
        customer.setAadharNumber("123456789123");
        customer.setCustomerName("Rajesh Kumar");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("rajkmr123@hmail.com");
        customer.setGender("Male");
        customer.setMobileNumber("1234567891");
        customer.setNationality("Indian");
        customer.setPanNumber("123456789");
        customer.setPassword("Pass@123");
        customer.setRole("CUSTOMER");
        customer.setUserId(123);
        Optional<Customer> ofResult = Optional.of(customer);
        when(iCustomerRepository.findById(any())).thenReturn(ofResult);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("123456789123");
        customer1.setCustomerName("Rajesh Kumar");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("rajkmr123@hmail.com");
        customer1.setGender("Male");
        customer1.setMobileNumber("1234567891");
        customer1.setNationality("Indian");
        customer1.setPanNumber("123456789");
        customer1.setPassword("Pass@123");
        customer1.setRole("CUSTOMER");
        customer1.setUserId(123);

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
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);
        when(iLoanApplicationRepository.save( any())).thenReturn(loanApplication);

        Scheme scheme1 = new Scheme();
        scheme1.setInterestRate(10.0d);
        scheme1.setSchemeId(123);
        scheme1.setTenure(1);
        Optional<Scheme> ofResult1 = Optional.of(scheme1);
        when(iSchemeRepository.findById(any())).thenReturn(ofResult1);
        assertSame(loanApplication, loanApplicationServiceImpl.addLoanApplication(new LoanApplicationDto()));
        verify(iCustomerRepository).findById(any());
        verify(iLoanApplicationRepository).save( any());
        verify(iSchemeRepository).findById( any());
    }

    @Test
    void testAddLoanApplication2() {
        when(iCustomerRepository.findById(any())).thenReturn(Optional.empty());
        Customer customer = mock(Customer.class);
        when(customer.getPanNumber()).thenReturn("123456789123");
        when(customer.getAadharNumber()).thenReturn("123456789123");
        doNothing().when(customer).setAadharNumber(any());
        doNothing().when(customer).setCustomerName( any());
        doNothing().when(customer).setDateOfBirth( any());
        doNothing().when(customer).setEmail(any());
        doNothing().when(customer).setGender(any());
        doNothing().when(customer).setMobileNumber( any());
        doNothing().when(customer).setNationality( any());
        doNothing().when(customer).setPanNumber(any());
        doNothing().when(customer).setPassword(any());
        doNothing().when(customer).setRole( any());
        doNothing().when(customer).setUserId(anyInt());
        customer.setAadharNumber("123456789123");
        customer.setCustomerName("Rajesh Kumar");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("rajkmr123@hmail.com");
        customer.setGender("Male");
        customer.setMobileNumber("1234567891");
        customer.setNationality("Indian");
        customer.setPanNumber("123456789");
        customer.setPassword("Pass@123");
        customer.setRole("CUSTOMER");
        customer.setUserId(123);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("123456789123");
        customer1.setCustomerName("Rajesh Kumar");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("rajkmr123@hmail.com");
        customer1.setGender("Male");
        customer1.setMobileNumber("1234567891");
        customer1.setNationality("Indian");
        customer1.setPanNumber("123456789");
        customer1.setPassword("Pass@123");
        customer1.setRole("CUSTOMER");
        customer1.setUserId(123);

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
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);
        when(iLoanApplicationRepository.save( any())).thenReturn(loanApplication);

        Scheme scheme1 = new Scheme();
        scheme1.setInterestRate(10.0d);
        scheme1.setSchemeId(123);
        scheme1.setTenure(1);
        Optional<Scheme> ofResult = Optional.of(scheme1);
        when(iSchemeRepository.findById(any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class,
                () -> loanApplicationServiceImpl.addLoanApplication(new LoanApplicationDto()));
        verify(iCustomerRepository).findById( any());
        verify(customer).setAadharNumber( any());
        verify(customer).setCustomerName( any());
        verify(customer).setDateOfBirth( any());
        verify(customer).setEmail( any());
        verify(customer).setGender(any());
        verify(customer).setMobileNumber(any());
        verify(customer).setNationality( any());
        verify(customer).setPanNumber( any());
        verify(customer).setPassword( any());
        verify(customer).setRole( any());
        verify(customer).setUserId(anyInt());
    }


    @Test
    void testAddLoanApplication3() {
        Customer customer = mock(Customer.class);
        when(customer.getPanNumber()).thenReturn("123456789123");
        when(customer.getAadharNumber()).thenReturn("123456789123");
        doNothing().when(customer).setAadharNumber(any());
        doNothing().when(customer).setCustomerName(any());
        doNothing().when(customer).setDateOfBirth( any());
        doNothing().when(customer).setEmail(any());
        doNothing().when(customer).setGender( any());
        doNothing().when(customer).setMobileNumber( any());
        doNothing().when(customer).setNationality(any());
        doNothing().when(customer).setPanNumber( any());
        doNothing().when(customer).setPassword(any());
        doNothing().when(customer).setRole( any());
        doNothing().when(customer).setUserId(anyInt());
        customer.setAadharNumber("123456789123");
        customer.setCustomerName("Rajesh Kumar");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("rajkmr123@hmail.com");
        customer.setGender("Male");
        customer.setMobileNumber("1234567891");
        customer.setNationality("Indian");
        customer.setPanNumber("123456789");
        customer.setPassword("Pass@123");
        customer.setRole("CUSTOMER");
        customer.setUserId(123);
        Optional<Customer> ofResult = Optional.of(customer);
        when(iCustomerRepository.findById(any())).thenReturn(ofResult);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("123456789123");
        customer1.setCustomerName("Rajesh Kumar");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("rajkmr123@hmail.com");
        customer1.setGender("Male");
        customer1.setMobileNumber("1234567891");
        customer1.setNationality("Indian");
        customer1.setPanNumber("123456789");
        customer1.setPassword("Pass@123");
        customer1.setRole("CUSTOMER");
        customer1.setUserId(123);

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
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);
        when(iLoanApplicationRepository.save(any())).thenReturn(loanApplication);
        when(iSchemeRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> loanApplicationServiceImpl.addLoanApplication(new LoanApplicationDto()));
        verify(iCustomerRepository).findById(any());
        verify(customer).getAadharNumber();
        verify(customer).getPanNumber();
        verify(customer).setAadharNumber(any());
        verify(customer).setCustomerName(any());
        verify(customer).setDateOfBirth(any());
        verify(customer).setEmail(any());
        verify(customer).setGender(any());
        verify(customer).setMobileNumber(any());
        verify(customer).setNationality(any());
        verify(customer).setPanNumber( any());
        verify(customer).setPassword(any());
        verify(customer).setRole(any());
        verify(customer).setUserId(anyInt());
        verify(iSchemeRepository).findById(any());
    }

    @Test
    void testUpdateLoanApplication() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("123456789123");
        customer.setCustomerName("Rajesh Kumar");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("rajkmr123@hmail.com");
        customer.setGender("Male");
        customer.setMobileNumber("1234567891");
        customer.setNationality("Indian");
        customer.setPanNumber("123456789");
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
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("123456789123");
        customer1.setCustomerName("Rajesh Kumar");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("rajkmr123@hmail.com");
        customer1.setGender("Male");
        customer1.setMobileNumber("1234567891");
        customer1.setNationality("Indian");
        customer1.setPanNumber("123456789");
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
        loanApplication1.setStatus("Status");
        loanApplication1.setTotalAnnualIncome(10.0d);
        when(iLoanApplicationRepository.save(any())).thenReturn(loanApplication1);
        when(iLoanApplicationRepository.findById(any())).thenReturn(ofResult);

        Customer customer2 = new Customer();
        customer2.setAadharNumber("123456789123");
        customer2.setCustomerName("Rajesh Kumar");
        customer2.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer2.setEmail("rajkmr123@hmail.com");
        customer2.setGender("Male");
        customer2.setMobileNumber("1234567891");
        customer2.setNationality("Indian");
        customer2.setPanNumber("123456789");
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
        loanApplication2.setStatus("Status");
        loanApplication2.setTotalAnnualIncome(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setLoanAgreementId(123L);
        loanAgreement.setLoanApplication(loanApplication2);

        EMI emi = new EMI();
        emi.setDeuDate(LocalDate.ofEpochDay(1L));
        emi.setEmiId(123L);
        emi.setEmiAmount(10.0d);
        emi.setInterestAmount(10.0d);
        emi.setLoanAgreement(loanAgreement);
        emi.setLoanAmount(10.0d);
        when(iEMIRepository.save( any())).thenReturn(emi);
        assertSame(loanApplication1, loanApplicationServiceImpl.updateLoanApplication(123L));
        verify(iLoanApplicationRepository).save(any());
        verify(iLoanApplicationRepository).findById(any());
        verify(iEMIRepository).save(any());
    }

    @Test
    void testUpdateStatusOfLoanApplication() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("123456789123");
        customer.setCustomerName("Rajesh Kumar");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("rajkmr123@hmail.com");
        customer.setGender("Male");
        customer.setMobileNumber("1234567891");
        customer.setNationality("Indian");
        customer.setPanNumber("123456789");
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
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);
        Optional<LoanApplication> ofResult = Optional.of(loanApplication);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("123456789123");
        customer1.setCustomerName("Rajesh Kumar");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("rajkmr123@hmail.com");
        customer1.setGender("Male");
        customer1.setMobileNumber("1234567891");
        customer1.setNationality("Indian");
        customer1.setPanNumber("123456789");
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
        loanApplication1.setStatus("Status");
        loanApplication1.setTotalAnnualIncome(10.0d);
        when(iLoanApplicationRepository.save( any())).thenReturn(loanApplication1);
        when(iLoanApplicationRepository.findById( any())).thenReturn(ofResult);
        assertSame(loanApplication1, loanApplicationServiceImpl.updateStatusOfLoanApplication(123L, Status.PENDING));
        verify(iLoanApplicationRepository).save(any());
        verify(iLoanApplicationRepository).findById(any());
    }

    @Test
    void testRetrieveLoanApplicationByStatus() {
        ArrayList<LoanApplication> loanApplicationList = new ArrayList<>();
        when(iLoanApplicationRepository.findByStatus(any())).thenReturn(loanApplicationList);
        List<LoanApplication> actualRetrieveLoanApplicationByStatusResult = loanApplicationServiceImpl
                .retrieveLoanApplicationByStatus("Status");
        assertSame(loanApplicationList, actualRetrieveLoanApplicationByStatusResult);
        assertTrue(actualRetrieveLoanApplicationByStatusResult.isEmpty());
        verify(iLoanApplicationRepository).findByStatus(any());
    }
}

