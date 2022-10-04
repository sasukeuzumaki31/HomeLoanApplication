package com.group4.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.demo.dto.FinanceVerificationDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.service.IFinanceVerificationService;
import com.group4.demo.service.ILoanApplicationService;

import java.time.LocalDate;

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

@ContextConfiguration(classes = {FinanceOfficerController.class})
@ExtendWith(SpringExtension.class)
class FinanceOfficerControllerTest {
    @Autowired
    private FinanceOfficerController financeOfficerController;

    @MockBean
    private IFinanceVerificationService iFinanceVerificationService;

    @MockBean
    private ILoanApplicationService iLoanApplicationService;

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
        when(iLoanApplicationService.retrieveLoanApplicationById(any())).thenReturn(loanApplication);
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
                                        + "\":123,\"interestRate\":10.0,\"tenure\":1}}"));
    }

    @Test
    void testCreateNewFinanceVerificationOfficer() throws Exception {
        FinanceVerificationDto financeVerificationDto = new FinanceVerificationDto();
        financeVerificationDto.setFinOfficerContact("1234567891");
        financeVerificationDto.setFinOfficerName("Kamlesh");
        financeVerificationDto.setPassword("pass@123");
        financeVerificationDto.setRole("FINANCE_VERIFICATION_OFFICER");
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

    @Test
    void testLoginFinanceVerificationOfficer() throws Exception {
        when(iFinanceVerificationService.loginFinanceVerificationOfficer(any()))
                .thenReturn("Login Finance Verification Officer");

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("Pass@123");
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


    @Test
    void testUpdateStatusOfLoanApplication() throws Exception {
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
        when(iFinanceVerificationService.updateStatus(any())).thenReturn(loanApplication);
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
                                        + "\":123,\"interestRate\":10.0,\"tenure\":1}}"));
    }

}

