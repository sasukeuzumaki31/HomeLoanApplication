package com.group4.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.demo.dto.CustomerDto;
import com.group4.demo.dto.DocsDto;
import com.group4.demo.dto.LoanApplicationDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.service.impl.ICustomerServiceImpl;
import com.group4.demo.service.impl.ILoanAgreementServiceImpl;
import com.group4.demo.service.impl.ILoanApplicationServiceImpl;
import com.group4.demo.service.impl.ISchemeServiceImpl;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    @MockBean
    private ICustomerServiceImpl iCustomerServiceImpl;

    @MockBean
    private ILoanAgreementServiceImpl iLoanAgreementServiceImpl;

    @MockBean
    private ILoanApplicationServiceImpl iLoanApplicationServiceImpl;

    @MockBean
    private ISchemeServiceImpl iSchemeServiceImpl;

    @Test
    void testLoginCustomer() throws Exception {
        when(iCustomerServiceImpl.loginCustomer(any())).thenReturn("Login Customer");

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("Pass@123");
        userLoginDto.setUserId(123);
        String content = (new ObjectMapper()).writeValueAsString(userLoginDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Login Customer"));
    }

    @Test
    void testRetrieveLoanApplicationById() throws Exception {
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
        when(iLoanApplicationServiceImpl.retrieveLoanApplicationById(any())).thenReturn(loanApplication);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/loan/{loanApplicationId}",
                123L);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"applicationId\":123,\"applicationDate\":[1970,1,2],\"loanAppliedAmount\":10.0,\"loanApprovedAmount\":10.0"
                                        + ",\"landVerificationApproval\":true,\"financeVerificationApproval\":true,\"adminApproval\":true,\"status\":"
                                        + "\"Status\",\"totalAnnualIncome\":10.0,\"monthlyExpenses\":10.0,\"otherMonthlyExpenses\":10.0,\"scheme\":{\"schemeId"
                                        + "\":123,\"interestRate\":10.0,\"tenure\":1}}"));
    }

    @Test
    void testCreateNewCustomer() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setAadharNumber("123456789123");
        customerDto.setCustomerName("Rajesh Kumar");
        customerDto.setDateOfBirth("2020-03-01");
        customerDto.setEmail("rajkmr123@hmail.com");
        customerDto.setGender("Male");
        customerDto.setMobileNumber("1234567891");
        customerDto.setNationality("Indian");
        customerDto.setPanNumber("123456789");
        customerDto.setPassword("Pass@123");
        String content = (new ObjectMapper()).writeValueAsString(customerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetAllSchemes() throws Exception {
        when(iSchemeServiceImpl.getAllSchemes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/schemes");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllSchemes2() throws Exception {
        when(iSchemeServiceImpl.getAllSchemes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/customer/schemes");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testCreateNewLoanApplication() throws Exception {
        LoanApplicationDto loanApplicationDto = new LoanApplicationDto();
        loanApplicationDto.setAdminApproval(true);
        loanApplicationDto.setApplicationDate(null);
        loanApplicationDto.setApplicationId(123L);
        loanApplicationDto.setCustomerId(123);
        loanApplicationDto.setFinanceVerificationApproval(true);
        loanApplicationDto.setLandVerificationApproval(true);
        loanApplicationDto.setLoanAppliedAmount(10.0d);
        loanApplicationDto.setLoanApprovedAmount(10.0d);
        loanApplicationDto.setMonthlyExpenses(10.0d);
        loanApplicationDto.setOtherMonthlyExpenses(10.0d);
        loanApplicationDto.setSchemeId(123);
        loanApplicationDto.setStatus("Status");
        loanApplicationDto.setTotalAnnualIncome(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(loanApplicationDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/apply")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
    @Test
    void testRetrieveLoanAgreementById() throws Exception {
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

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setLoanAgreementId(123L);
        loanAgreement.setLoanApplication(loanApplication);
        when(iLoanAgreementServiceImpl.retrieveAgreementById(anyLong())).thenReturn(loanAgreement);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/loanagreement/{id}", 123L);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"loanAgreementId\":123,\"loanApplication\":{\"applicationId\":123,\"applicationDate\":[1970,1,2],\"loanAppliedAmount"
                                        + "\":10.0,\"loanApprovedAmount\":10.0,\"landVerificationApproval\":true,\"financeVerificationApproval\":true,"
                                        + "\"adminApproval\":true,\"status\":\"Status\",\"totalAnnualIncome\":10.0,\"monthlyExpenses\":10.0,\"otherMonthlyExpenses"
                                        + "\":10.0,\"scheme\":{\"schemeId\":123,\"interestRate\":10.0,\"tenure\":1}}}"));
    }

    @Test
    void testUpdateDocuments() throws Exception {
        DocsDto docsDto = new DocsDto();
        docsDto.setAadharNumber("123456789123");
        docsDto.setPanNumber("123456789");
        String content = (new ObjectMapper()).writeValueAsString(docsDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/customer/document/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

