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

import com.group4.demo.Dto.CustomerDto;
import com.group4.demo.advices.ResourceNotFoundException;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ICustomerServiceImpl.class})
@ExtendWith(SpringExtension.class)
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
        customer.setAadharNumber("42");
        customer.setCustomerName("Customer Name");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("jane.doe@example.org");
        customer.setGender("M");
        customer.setMobileNumber("42");
        customer.setNationality("Indian");
        customer.setPanNumber("42");
        customer.setPassword("dsdfrt");
        customer.setRole("CUSTOMER");
        customer.setUserId(123);
        Optional<Customer> ofResult = Optional.of(customer);
        when(iCustomerRepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(customer, iCustomerServiceImpl.viewCustomer(123));
        verify(iCustomerRepository).findById((Integer) any());
    }


    @Test
    void testViewCustomer2() throws ResourceNotFoundException {
        when(iCustomerRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> iCustomerServiceImpl.viewCustomer(123));
        verify(iCustomerRepository).findById((Integer) any());
    }


    @Test
    void testViewAllCustomers() throws ResourceNotFoundException {
        when(iCustomerRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> iCustomerServiceImpl.viewAllCustomers());
        verify(iCustomerRepository).findAll();
    }


    @Test
    void testViewAllCustomers2() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("34545443224534465436");
        customer.setCustomerName("jane");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("jane.doe@example.org");
        customer.setGender("F");
        customer.setMobileNumber("3567543246");
        customer.setNationality("American");
        customer.setPanNumber("42");
        customer.setPassword("dfg345");
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
    void testUpdateCustomer() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("4224345345356456");
        customer.setCustomerName("Ria");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("jane.doe@example.org");
        customer.setGender("F");
        customer.setMobileNumber("42");
        customer.setNationality("Indian");
        customer.setPanNumber("4353465462");
        customer.setPassword("ddffg456f");
        customer.setRole("CUSTOMER");
        customer.setUserId(123);
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer1 = new Customer();
        customer1.setAadharNumber("42345345");
        customer1.setCustomerName("Ritesh");
        customer1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer1.setEmail("jane.doe@example.org");
        customer1.setGender("Gender");
        customer1.setMobileNumber("42");
        customer1.setNationality("Indian");
        customer1.setPanNumber("42");
        customer1.setPassword("ghj346");
        customer1.setRole("CUSTOMER");
        customer1.setUserId(123);
        when(iCustomerRepository.save((Customer) any())).thenReturn(customer1);
        when(iCustomerRepository.findById((Integer) any())).thenReturn(ofResult);

        Customer customer2 = new Customer();
        customer2.setAadharNumber("4346562");
        customer2.setCustomerName("Retaa");
        customer2.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer2.setEmail("jane.doe@example.org");
        customer2.setGender("F");
        customer2.setMobileNumber("4546457672");
        customer2.setNationality("Indian");
        customer2.setPanNumber("42");
        customer2.setPassword("tyfghdth");
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
        customer3.setAadharNumber("42");
        customer3.setCustomerName("Customer Name");
        customer3.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer3.setEmail("jane.doe@example.org");
        customer3.setGender("Gender");
        customer3.setMobileNumber("42");
        customer3.setNationality("Nationality");
        customer3.setPanNumber("42");
        customer3.setPassword("iloveyou");
        customer3.setRole("Role");
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
        when(iLoanApplicationRepository.save((LoanApplication) any())).thenReturn(loanApplication1);
        when(iLoanApplicationRepository.findByCustomerId(anyInt())).thenReturn(loanApplication);
        assertSame(customer1, iCustomerServiceImpl.updateCustomer(1, new CustomerDto("Customer Name", "42",
                "jane.doe@example.org", "2020-03-01", "Gender", "Nationality", "42", "42", "iloveyou")));
        verify(iCustomerRepository).save((Customer) any());
        verify(iCustomerRepository).findById((Integer) any());
        verify(iLoanApplicationRepository).findByCustomerId(anyInt());
        verify(iLoanApplicationRepository).save((LoanApplication) any());
    }


    @Test
    void testUpdateCustomer2() throws ResourceNotFoundException {
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
        when(iCustomerRepository.save((Customer) any())).thenReturn(customer);
        when(iCustomerRepository.findById((Integer) any())).thenReturn(Optional.empty());

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
        customer2.setAadharNumber("42");
        customer2.setCustomerName("Customer Name");
        customer2.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer2.setEmail("jane.doe@example.org");
        customer2.setGender("Gender");
        customer2.setMobileNumber("42");
        customer2.setNationality("Nationality");
        customer2.setPanNumber("42");
        customer2.setPassword("iloveyou");
        customer2.setRole("Role");
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
        when(iLoanApplicationRepository.save((LoanApplication) any())).thenReturn(loanApplication1);
        when(iLoanApplicationRepository.findByCustomerId(anyInt())).thenReturn(loanApplication);
        assertThrows(ResourceNotFoundException.class,
                () -> iCustomerServiceImpl.updateCustomer(1, new CustomerDto("Customer Name", "42", "jane.doe@example.org",
                        "2020-03-01", "Gender", "Nationality", "42", "42", "iloveyou")));
        verify(iCustomerRepository).findById((Integer) any());
    }


    @Test
    void testViewCustomerList() throws ResourceNotFoundException {
        when(iCustomerRepository.findByDateOfApplication((LocalDate) any())).thenReturn(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> iCustomerServiceImpl.viewCustomerList("2020-03-01"));
        verify(iCustomerRepository).findByDateOfApplication((LocalDate) any());
    }


    @Test
    void testViewCustomerList2() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadharNumber("42");
        customer.setCustomerName("yyyy-MM-dd");
        customer.setDateOfBirth(LocalDate.ofEpochDay(1L));
        customer.setEmail("jane.doe@example.org");
        customer.setGender("yyyy-MM-dd");
        customer.setMobileNumber("42");
        customer.setNationality("yyyy-MM-dd");
        customer.setPanNumber("42");
        customer.setPassword("iloveyou");
        customer.setRole("yyyy-MM-dd");
        customer.setUserId(123);

        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(iCustomerRepository.findByDateOfApplication((LocalDate) any())).thenReturn(customerList);
        List<Customer> actualViewCustomerListResult = iCustomerServiceImpl.viewCustomerList("2020-03-01");
        assertSame(customerList, actualViewCustomerListResult);
        assertEquals(1, actualViewCustomerListResult.size());
        verify(iCustomerRepository).findByDateOfApplication((LocalDate) any());
    }


    @Test
    void testDeleteCustomerById() throws ResourceNotFoundException {
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
        doNothing().when(iCustomerRepository).deleteById((Integer) any());
        when(iCustomerRepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(customer, iCustomerServiceImpl.deleteCustomerById(123));
        verify(iCustomerRepository).findById((Integer) any());
        verify(iCustomerRepository).deleteById((Integer) any());
    }


    @Test
    void testDeleteCustomerById2() throws ResourceNotFoundException {
        doNothing().when(iCustomerRepository).deleteById((Integer) any());
        when(iCustomerRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> iCustomerServiceImpl.deleteCustomerById(123));
        verify(iCustomerRepository).findById((Integer) any());
    }
}

