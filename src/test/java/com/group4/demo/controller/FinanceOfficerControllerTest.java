package com.group4.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.demo.dto.FinanceVerificationDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.EMI;
import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.service.IFinanceVerificationService;
import com.group4.demo.service.ILoanApplicationService;

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

@ContextConfiguration(classes = {FinanceOfficerController.class})
@ExtendWith(SpringExtension.class)
class FinanceOfficerControllerTest {
    @Autowired
    private FinanceOfficerController financeOfficerController;

    @MockBean
    private IFinanceVerificationService iFinanceVerificationService;

    @MockBean
    private ILoanApplicationService iLoanApplicationService;

    /**
     * Method under test: {@link FinanceOfficerController#retrieveLoanApplicationById(Long)}
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
        when(iLoanApplicationService.retrieveLoanApplicationById((Long) any())).thenReturn(loanApplication);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/financeofficer/loan/{id}", 123L);
        MockMvcBuilders.standaloneSetup(financeOfficerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"applicationId\":123,\"applicationDate\":[1970,1,2],\"loanAppliedAmount\":10.0,\"loanApprovedAmount\":10.0"
                                        + ",\"landVerificationApproval\":true,\"financeVerificationApproval\":true,\"adminApproval\":true,\"status\":"
                                        + "\"Status\",\"totalAnnualIncome\":10.0,\"monthlyExpenses\":10.0,\"otherMonthlyExpenses\":10.0,\"scheme\":{\"schemeId"
                                        + "\":123,\"interestRate\":10.0,\"tenure\":1},\"customer\":{\"userId\":123,\"password\":\"iloveyou\",\"role\":\"Role\","
                                        + "\"customerName\":\"Customer Name\",\"mobileNumber\":\"42\",\"email\":\"jane.doe@example.org\",\"dateOfBirth\":[1970"
                                        + ",1,2],\"gender\":\"Gender\",\"nationality\":\"Nationality\",\"aadharNumber\":\"42\",\"panNumber\":\"42\"},\"loanAgreement"
                                        + "\":{\"loanAgreementId\":123,\"emi\":{\"emiId\":123,\"deuDate\":[1970,1,2],\"emiAmount\":10.0,\"loanAmount\":10.0,"
                                        + "\"interestAmount\":10.0}}}"));
    }

    /**
     * Method under test: {@link FinanceOfficerController#createNewFinanceVerificationOfficer(FinanceVerificationDto)}
     */
    @Test
    void testCreateNewFinanceVerificationOfficer() throws Exception {
        FinanceVerificationDto financeVerificationDto = new FinanceVerificationDto();
        financeVerificationDto.setFinOfficerContact("Fin Officer Contact");
        financeVerificationDto.setFinOfficerName("Fin Officer Name");
        financeVerificationDto.setPassword("iloveyou");
        financeVerificationDto.setUserId(123);
        String content = (new ObjectMapper()).writeValueAsString(financeVerificationDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/financeofficer/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(financeOfficerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link FinanceOfficerController#loginFinanceVerificationOfficer(UserLoginDto)}
     */
    @Test
    void testLoginFinanceVerificationOfficer() throws Exception {
        when(iFinanceVerificationService.loginFinanceVerificationOfficer((UserLoginDto) any()))
                .thenReturn("Login Finance Verification Officer");

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("iloveyou");
        userLoginDto.setUserId(123);
        String content = (new ObjectMapper()).writeValueAsString(userLoginDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/financeofficer/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(financeOfficerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Login Finance Verification Officer"));
    }

    /**
     * Method under test: {@link FinanceOfficerController#getPendingApplications()}
     */
    @Test
    void testGetPendingApplications() throws Exception {
        when(iLoanApplicationService.retrieveLoanApplicationByStatus((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/financeofficer/loans/pending");
        MockMvcBuilders.standaloneSetup(financeOfficerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FinanceOfficerController#getPendingApplications()}
     */
    @Test
    void testGetPendingApplications2() throws Exception {
        when(iLoanApplicationService.retrieveLoanApplicationByStatus((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/financeofficer/loans/pending");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(financeOfficerController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FinanceOfficerController#updateStatusOfLoanApplication(Long)}
     */
    @Test
    void testUpdateStatusOfLoanApplication() throws Exception {
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
        when(iFinanceVerificationService.updateStatus((Long) any())).thenReturn(loanApplication);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/financeofficer/loan/{id}", 123L);
        MockMvcBuilders.standaloneSetup(financeOfficerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"applicationId\":123,\"applicationDate\":[1970,1,2],\"loanAppliedAmount\":10.0,\"loanApprovedAmount\":10.0"
                                        + ",\"landVerificationApproval\":true,\"financeVerificationApproval\":true,\"adminApproval\":true,\"status\":"
                                        + "\"Status\",\"totalAnnualIncome\":10.0,\"monthlyExpenses\":10.0,\"otherMonthlyExpenses\":10.0,\"scheme\":{\"schemeId"
                                        + "\":123,\"interestRate\":10.0,\"tenure\":1},\"customer\":{\"userId\":123,\"password\":\"iloveyou\",\"role\":\"Role\","
                                        + "\"customerName\":\"Customer Name\",\"mobileNumber\":\"42\",\"email\":\"jane.doe@example.org\",\"dateOfBirth\":[1970"
                                        + ",1,2],\"gender\":\"Gender\",\"nationality\":\"Nationality\",\"aadharNumber\":\"42\",\"panNumber\":\"42\"},\"loanAgreement"
                                        + "\":{\"loanAgreementId\":123,\"emi\":{\"emiId\":123,\"deuDate\":[1970,1,2],\"emiAmount\":10.0,\"loanAmount\":10.0,"
                                        + "\"interestAmount\":10.0}}}"));
    }
}

