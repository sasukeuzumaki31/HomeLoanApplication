package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.dto.CustomerDto;
import com.group4.demo.dto.DocsDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.repository.ICustomerRepository;
import com.group4.demo.repository.ILoanApplicationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest

class ICustomerServiceImplTest {
    @MockBean
    private ICustomerRepository iCustomerRepository;

    @Autowired
    private ICustomerServiceImpl iCustomerServiceImpl;

    @MockBean
    private ILoanApplicationRepository iLoanApplicationRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void testViewCustomer() throws ResourceNotFoundException {
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
        when(iCustomerRepository.findById( any())).thenReturn(ofResult);
        assertSame(customer, iCustomerServiceImpl.viewCustomer(123));
        verify(iCustomerRepository).findById( any());
    }

    @Test
    void testViewCustomer2()  {
        when(iCustomerRepository.findById( any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> iCustomerServiceImpl.viewCustomer(123));
        verify(iCustomerRepository).findById(any());
    }

    @Test
    void testViewAllCustomers() {
        when(iCustomerRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> iCustomerServiceImpl.viewAllCustomers());
        verify(iCustomerRepository).findAll();
    }

    @Test
    void testViewAllCustomers2() throws ResourceNotFoundException {
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

        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(iCustomerRepository.findAll()).thenReturn(customerList);
        List<Customer> actualViewAllCustomersResult = iCustomerServiceImpl.viewAllCustomers();
        assertSame(customerList, actualViewAllCustomersResult);
        assertEquals(1, actualViewAllCustomersResult.size());
        verify(iCustomerRepository, atLeast(1)).findAll();
    }

    @Test
    void testAddCustomer()  {
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
        when(iCustomerRepository.findByMobileNumber(any())).thenReturn(customer);
        assertThrows(CouldNotBeAddedException.class, () -> iCustomerServiceImpl.addCustomer(new CustomerDto("Customer Name",
                "42", "jane.doe@example.org", "2020-03-01", "Gender", "Nationality", "42", "42", "iloveyou")));
        verify(iCustomerRepository).findByMobileNumber( any());
    }

    @Test
    void testUpdateCustomer() throws ResourceNotFoundException {
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
        when(iCustomerRepository.save(any())).thenReturn(customer1);
        when(iCustomerRepository.findById( any())).thenReturn(ofResult);

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

        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setAdminApproval(true);
        loanApplication.setApplicationDate(LocalDate.ofEpochDay(1L));
        loanApplication.setApplicationId(123L);
        loanApplication.setCustomer(customer2);
        loanApplication.setFinanceVerificationApproval(true);
        loanApplication.setLandVerificationApproval(true);
        loanApplication.setLoanAppliedAmount(10.0d);
        loanApplication.setLoanApprovedAmount(10.0d);
        loanApplication.setMonthlyExpenses(10.0d);
        loanApplication.setOtherMonthlyExpenses(10.0d);
        loanApplication.setScheme(scheme);
        loanApplication.setStatus("Status");
        loanApplication.setTotalAnnualIncome(10.0d);

        Customer customer3 = new Customer();
        customer3.setAadharNumber("123456789123");
        customer3.setCustomerName("Rajesh Kumar");
        customer3.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer3.setEmail("rajkmr123@hmail.com");
        customer3.setGender("Male");
        customer3.setMobileNumber("1234567891");
        customer3.setNationality("Indian");
        customer3.setPanNumber("123456789");
        customer3.setPassword("Pass@123");
        customer3.setRole("CUSTOMER");
        customer3.setUserId(123);

        Scheme scheme1 = new Scheme();
        scheme1.setInterestRate(10.0d);
        scheme1.setSchemeId(123);
        scheme1.setTenure(1);

        LoanApplication loanApplication1 = new LoanApplication();
        loanApplication1.setAdminApproval(true);
        loanApplication1.setApplicationDate(LocalDate.ofEpochDay(1L));
        loanApplication1.setApplicationId(123L);
        loanApplication1.setCustomer(customer3);
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
        when(iLoanApplicationRepository.findByCustomerId(anyInt())).thenReturn(loanApplication);
        assertSame(customer1, iCustomerServiceImpl.updateCustomer(1, new DocsDto("42", "42")));
        verify(iCustomerRepository).save( any());
        verify(iCustomerRepository).findById( any());
        verify(iLoanApplicationRepository).findByCustomerId(anyInt());
        verify(iLoanApplicationRepository).save(any());
    }

    @Test
    void testUpdateCustomer2() {
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
        when(iCustomerRepository.save( any())).thenReturn(customer);
        when(iCustomerRepository.findById(any())).thenReturn(Optional.empty());

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

        Scheme scheme1 = new Scheme();
        scheme1.setInterestRate(10.0d);
        scheme1.setSchemeId(123);
        scheme1.setTenure(1);

        LoanApplication loanApplication1 = new LoanApplication();
        loanApplication1.setAdminApproval(true);
        loanApplication1.setApplicationDate(LocalDate.ofEpochDay(1L));
        loanApplication1.setApplicationId(123L);
        loanApplication1.setCustomer(customer2);
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
        when(iLoanApplicationRepository.findByCustomerId(anyInt())).thenReturn(loanApplication);
        assertThrows(ResourceNotFoundException.class,
                () -> iCustomerServiceImpl.updateCustomer(1, new DocsDto("42", "42")));
        verify(iCustomerRepository).findById( any());
    }

    @Test
    void testViewCustomerList()  {
        when(iCustomerRepository.findByDateOfApplication(any())).thenReturn(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> iCustomerServiceImpl.viewCustomerList("2020-03-01"));
        verify(iCustomerRepository).findByDateOfApplication(any());
    }

    @Test
    void testViewCustomerList2() throws ResourceNotFoundException {
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

        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(iCustomerRepository.findByDateOfApplication(any())).thenReturn(customerList);
        List<Customer> actualViewCustomerListResult = iCustomerServiceImpl.viewCustomerList("2020-03-01");
        assertSame(customerList, actualViewCustomerListResult);
        assertEquals(1, actualViewCustomerListResult.size());
        verify(iCustomerRepository).findByDateOfApplication( any());
    }


    @Test
    void testDeleteCustomerById() throws ResourceNotFoundException {
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
        doNothing().when(iCustomerRepository).deleteById( any());
        when(iCustomerRepository.findById(any())).thenReturn(ofResult);
        assertSame(customer, iCustomerServiceImpl.deleteCustomerById(123));
        verify(iCustomerRepository).findById(any());
        verify(iCustomerRepository).deleteById(any());
    }

    @Test
    void testDeleteCustomerById2() {
        doNothing().when(iCustomerRepository).deleteById(any());
        when(iCustomerRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> iCustomerServiceImpl.deleteCustomerById(123));
        verify(iCustomerRepository).findById(any());
    }

    @Test
    void testLoginCustomer() throws ResourceNotFoundException {
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
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("Pass@123");
        userLoginDto.setUserId(123);
        assertEquals("Login successfully", iCustomerServiceImpl.loginCustomer(userLoginDto));
        verify(iCustomerRepository).findById(any());
        verify(passwordEncoder).matches(any(), any());
    }

    @Test
    void testLoginCustomer2()  {
        when(iCustomerRepository.findById(any())).thenReturn(Optional.empty());
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("Pass@123");
        userLoginDto.setUserId(123);
        assertThrows(ResourceNotFoundException.class, () -> iCustomerServiceImpl.loginCustomer(userLoginDto));
        verify(iCustomerRepository).findById(any());
    }

    @Test
    void testLoginCustomer3() throws ResourceNotFoundException {
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
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("pass@123");
        userLoginDto.setUserId(123);
        assertEquals("Invalid Credentials", iCustomerServiceImpl.loginCustomer(userLoginDto));
        verify(iCustomerRepository).findById( any());
        verify(passwordEncoder).matches(any(), any());
    }
}

