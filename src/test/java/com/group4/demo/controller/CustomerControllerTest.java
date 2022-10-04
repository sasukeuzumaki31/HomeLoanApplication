package com.group4.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.demo.dto.CustomerDto;
import com.group4.demo.dto.LoanApplicationDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.service.impl.ICustomerServiceImpl;
import com.group4.demo.service.impl.ILoanAgreementServiceImpl;
import com.group4.demo.service.impl.ISchemeServiceImpl;
import com.group4.demo.service.impl.ILoanApplicationServiceImpl;
import com.group4.demo.util.EMICalculator;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CustomerController.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    @MockBean
    private ICustomerServiceImpl iCustomerServiceImpl;

    @MockBean
    private ILoanAgreementServiceImpl iLoanAgreementServiceImpl;

    @MockBean
    private ISchemeServiceImpl iSchemeServiceImpl;

    @MockBean
    private ILoanApplicationServiceImpl loanApplicationServiceImpl;

    /**
     * Method under test: {@link CustomerController#loginCustomer(UserLoginDto)}
     */
    @Test
    void testLoginCustomer() throws Exception {
        when(iCustomerServiceImpl.loginCustomer((UserLoginDto) any())).thenReturn("Login Customer");

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("iloveyou");
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

    /**
     * Method under test: {@link CustomerController#retrieveLoanApplicationById(Long)}
     */
    @Test
    void testRetrieveLoanApplicationById() throws Exception {
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
        when(loanApplicationServiceImpl.retrieveLoanApplicationById((Long) any())).thenReturn(loanApplication);
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

    /**
     * Method under test: {@link CustomerController#createNewCustomer(CustomerDto)}
     */
    @Test
    void testCreateNewCustomer() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setAadharNumber("42");
        customerDto.setCustomerName("Customer Name");
        customerDto.setDateOfBirth("2020-03-01");
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setGender("Gender");
        customerDto.setMobileNumber("42");
        customerDto.setNationality("Nationality");
        customerDto.setPanNumber("42");
        customerDto.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(customerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CustomerController#getAllSchemes()}
     */
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

    /**
     * Method under test: {@link CustomerController#getAllSchemes()}
     */
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

    /**
     * Method under test: {@link CustomerController#createNewLoanApplication(LoanApplicationDto)}
     */
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

    /**
     * Method under test: {@link CustomerController#getEMIAmount(EMICalculator)}
     */
    @Test
    void testGetEMIAmount() throws Exception {
        EMICalculator emiCalculator = new EMICalculator();
        emiCalculator.setLoanAmount(10.0d);
        emiCalculator.setRateOfInterest(10.0d);
        emiCalculator.setTenure(1);
        String content = (new ObjectMapper()).writeValueAsString(emiCalculator);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/emi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("0.88"));
    }

    /**
     * Method under test: {@link CustomerController#retrieveLoanAgreementById(Long)}
     */
    @Test
    void testRetrieveLoanAgreementById() throws Exception {
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

    /**
     * Method under test: {@link CustomerController#updateDocuments(int, CustomerDto)}
     */
    @Test
    void testUpdateDocuments() throws Exception {
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
        when(iCustomerServiceImpl.updateCustomer(anyInt(), (CustomerDto) any())).thenReturn(customer);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAadharNumber("42");
        customerDto.setCustomerName("Customer Name");
        customerDto.setDateOfBirth("2020-03-01");
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setGender("Gender");
        customerDto.setMobileNumber("42");
        customerDto.setNationality("Nationality");
        customerDto.setPanNumber("42");
        customerDto.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(customerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/customer/document/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"password\":\"iloveyou\",\"role\":\"Role\",\"customerName\":\"Customer Name\",\"mobileNumber\":\"42\""
                                        + ",\"email\":\"jane.doe@example.org\",\"dateOfBirth\":[1970,1,2],\"gender\":\"Gender\",\"nationality\":\"Nationality"
                                        + "\",\"aadharNumber\":\"42\",\"panNumber\":\"42\"}"));
    }
}

