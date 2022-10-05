package com.group4.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.demo.dto.LandVerificationOfficerDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.EMI;
import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.entity.Status;
import com.group4.demo.service.ILandVerificationService;
import com.group4.demo.service.ILoanApplicationService;

import java.time.LocalDate;

import java.util.ArrayList;

import com.group4.demo.service.impl.IAdminServiceImpl;
import com.group4.demo.service.impl.ILandVerificationServiceImpl;
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

@ContextConfiguration(classes = {LandOfficerController.class})
@ExtendWith(SpringExtension.class)
class LandOfficerControllerTest {
    @MockBean
    private ILandVerificationService iLandVerificationService;

    @MockBean
    private ILoanApplicationService iLoanApplicationService;

    @Autowired
    private LandOfficerController landOfficerController;

    @Test
    void testRetrieveLoanApplicationById() throws Exception {
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
        when(iLoanApplicationService.retrieveLoanApplicationById(any())).thenReturn(loanApplication);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/landofficer/loan/{id}", 123L);
        MockMvcBuilders.standaloneSetup(landOfficerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"applicationId\":123,\"applicationDate\":[1970,1,2],\"loanAppliedAmount\":10.0,\"loanApprovedAmount\":10.0"
                                        + ",\"landVerificationApproval\":true,\"financeVerificationApproval\":true,\"adminApproval\":true,\"status\":"
                                        + "\"Status\",\"totalAnnualIncome\":10.0,\"monthlyExpenses\":10.0,\"otherMonthlyExpenses\":10.0,\"scheme\":{\"schemeId"
                                        + "\":123,\"interestRate\":10.0,\"tenure\":1},\"customer\":{\"userId\":123,\"password\":\"Pass@123\",\"role\":\"Role\","
                                        + "\"customerName\":\"Ramesh\",\"mobileNumber\":\"1234567890\",\"email\":\"ramesh123@example.com\",\"dateOfBirth\":[1970"
                                        + ",1,2],\"gender\":\"M\",\"nationality\":\"Indian\",\"aadharNumber\":\"123456789012\",\"panNumber\":\"123456789\"},\"loanAgreement"
                                        + "\":{\"loanAgreementId\":123,\"emi\":{\"emiId\":123,\"deuDate\":[1970,1,2],\"emiAmount\":10.0,\"loanAmount\":10.0,"
                                        + "\"interestAmount\":10.0}}}"));
    }

    @Test
    void testUpdateStatusOfLoanApplication() throws Exception {
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
        when(iLoanApplicationService.updateStatusOfLoanApplication(any(), any()))
                .thenReturn(loanApplication);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/landofficer/loan/{id}", 123L);
        MockMvcBuilders.standaloneSetup(landOfficerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"applicationId\":123,\"applicationDate\":[1970,1,2],\"loanAppliedAmount\":10.0,\"loanApprovedAmount\":10.0"
                                        + ",\"landVerificationApproval\":true,\"financeVerificationApproval\":true,\"adminApproval\":true,\"status\":"
                                        + "\"Status\",\"totalAnnualIncome\":10.0,\"monthlyExpenses\":10.0,\"otherMonthlyExpenses\":10.0,\"scheme\":{\"schemeId"
                                        + "\":123,\"interestRate\":10.0,\"tenure\":1},\"customer\":{\"userId\":123,\"password\":\"Pass@123\",\"role\":\"Role\","
                                        + "\"customerName\":\"Ramesh\",\"mobileNumber\":\"1234567890\",\"email\":\"ramesh123@example.com\",\"dateOfBirth\":[1970"
                                        + ",1,2],\"gender\":\"M\",\"nationality\":\"Indian\",\"aadharNumber\":\"123456789012\",\"panNumber\":\"123456789\"},\"loanAgreement"
                                        + "\":{\"loanAgreementId\":123,\"emi\":{\"emiId\":123,\"deuDate\":[1970,1,2],\"emiAmount\":10.0,\"loanAmount\":10.0,"
                                        + "\"interestAmount\":10.0}}}"));
    }

    @Test
    void testCreateNewCustomer() throws Exception {
        LandVerificationOfficerDto landVerificationOfficerDto = new LandVerificationOfficerDto();
        landVerificationOfficerDto.setOfficeContact("1234567890");
        landVerificationOfficerDto.setOfficeName("Rajesh");
        landVerificationOfficerDto.setPassword("pass@123");
        landVerificationOfficerDto.setUserId(123);
        String content = (new ObjectMapper()).writeValueAsString(landVerificationOfficerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/landofficer/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(landOfficerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testLoginLandOfficer() throws Exception {
        when(iLandVerificationService.loginLandOfficer(any())).thenReturn("Login Land Officer");

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("Pass@123");
        userLoginDto.setUserId(123);
        String content = (new ObjectMapper()).writeValueAsString(userLoginDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/landofficer/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(landOfficerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Login Land Officer"));
    }

    @Test
    void testGetPendingApplications() throws Exception {
        when(iLoanApplicationService.retrieveLoanApplicationByStatus(any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/landofficer/loans/pending");
        MockMvcBuilders.standaloneSetup(landOfficerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetPendingApplications2() throws Exception {
        when(iLoanApplicationService.retrieveLoanApplicationByStatus(any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/landofficer/loans/pending");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(landOfficerController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

