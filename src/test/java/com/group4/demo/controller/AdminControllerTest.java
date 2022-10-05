package com.group4.demo.controller;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.dto.SchemeDto;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.EMI;
import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Scheme;
import com.group4.demo.service.IAdminService;
import com.group4.demo.service.ICustomerService;
import com.group4.demo.service.ILoanApplicationService;
import com.group4.demo.service.ISchemeService;
import com.group4.demo.service.impl.ILoanAgreementServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private IAdminService iAdminService;

    @MockBean
    private ICustomerService iCustomerService;

    @MockBean
    private ILoanAgreementServiceImpl iLoanAgreementServiceImpl;

    @MockBean
    private ILoanApplicationService iLoanApplicationService;

    @MockBean
    private ISchemeService iSchemeService;

    @Test
    void testViewCustomerList() throws Exception {
        when(iCustomerService.viewCustomerList((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/users/{date}", "2020-03-01");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testViewCustomerList2() throws Exception {
        when(iCustomerService.viewAllCustomers()).thenReturn(new ArrayList<>());
        when(iCustomerService.viewCustomerList((String) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/users/{date}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testViewCustomer() throws Exception {
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
        when(iCustomerService.viewCustomer(anyInt())).thenReturn(customer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/user/{id}", 1);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"password\":\"iloveyou\",\"role\":\"Role\",\"customerName\":\"Customer Name\",\"mobileNumber\":\"42\""
                                        + ",\"email\":\"jane.doe@example.org\",\"dateOfBirth\":[1970,1,2],\"gender\":\"Gender\",\"nationality\":\"Nationality"
                                        + "\",\"aadharNumber\":\"42\",\"panNumber\":\"42\"}"));
    }


    @Test
    void testDeleteCustomerById() throws Exception {
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
        when(iCustomerService.deleteCustomerById(anyInt())).thenReturn(customer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/admin/user/{id}", 1);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"password\":\"iloveyou\",\"role\":\"Role\",\"customerName\":\"Customer Name\",\"mobileNumber\":\"42\""
                                        + ",\"email\":\"jane.doe@example.org\",\"dateOfBirth\":[1970,1,2],\"gender\":\"Gender\",\"nationality\":\"Nationality"
                                        + "\",\"aadharNumber\":\"42\",\"panNumber\":\"42\"}"));
    }


    @Test
    void testRetrieveAllLoanApplication() throws Exception {
        when(iLoanApplicationService.retrieveAllLoanApplication()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/applications");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testRetrieveAllLoanApplication2() throws Exception {
        when(iLoanApplicationService.retrieveAllLoanApplication()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admin/applications");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetAllSchemes() throws Exception {
        when(iSchemeService.getAllSchemes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/scheme");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllSchemes2() throws Exception {
        when(iSchemeService.getAllSchemes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admin/scheme");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetSchemeById() throws Exception {
        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);
        when(iSchemeService.getSchemeById(anyInt())).thenReturn(scheme);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/scheme/{id}", 1);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"schemeId\":123,\"interestRate\":10.0,\"tenure\":1}"));
    }


    @Test
    void testAddScheme() throws Exception {
        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);
        when(iSchemeService.addScheme((SchemeDto) any())).thenReturn(scheme);

        SchemeDto schemeDto = new SchemeDto();
        schemeDto.setInterestRate(10.0d);
        schemeDto.setSchemeId(123);
        schemeDto.setTenure(1);
        String content = (new ObjectMapper()).writeValueAsString(schemeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/scheme")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"schemeId\":123,\"interestRate\":10.0,\"tenure\":1}"));
    }

    @Test
    void testDeleteSchemeById() throws Exception {
        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);
        when(iSchemeService.deleteSchemeById(anyInt())).thenReturn(scheme);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/admin/scheme/{id}", 1);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"schemeId\":123,\"interestRate\":10.0,\"tenure\":1}"));
    }

    @Test
    void testUpdateScheme() throws Exception {
        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);
        when(iSchemeService.updateScheme(anyInt(), (SchemeDto) any())).thenReturn(scheme);

        SchemeDto schemeDto = new SchemeDto();
        schemeDto.setInterestRate(10.0d);
        schemeDto.setSchemeId(123);
        schemeDto.setTenure(1);
        String content = (new ObjectMapper()).writeValueAsString(schemeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/admin/scheme/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"schemeId\":123,\"interestRate\":10.0,\"tenure\":1}"));
    }


    @Test
    void testConstructor() {
        assertNull((new AdminController()).getLoanApplicationService());
    }


    @Test
    void testDeleteLoanAgreementById() throws Exception {
        EMI emi = new EMI();
        emi.setDeuDate(LocalDate.ofEpochDay(1L));
        emi.setEmiAmount(10.0d);
        emi.setEmiId(123L);
        emi.setInterestAmount(10.0d);
        emi.setLoanAmount(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setEmi(emi);
        loanAgreement.setLoanAgreementId(123L);
        when(iLoanAgreementServiceImpl.deleteLoanAgreement(anyLong())).thenReturn(loanAgreement);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/admin/loanagreement/{id}", 123L);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"loanAgreementId\":123,\"emi\":{\"emiId\":123,\"deuDate\":[1970,1,2],\"emiAmount\":10.0,\"loanAmount\":10.0,"
                                        + "\"interestAmount\":10.0}}"));
    }


    @Test
    void testGetApplicationById() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/application/{id}", 123L);
        MockMvcBuilders.standaloneSetup(adminController)
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


    @Test
    void testGetDocumentUploaded() throws Exception {
        when(iLoanApplicationService.retrieveLoanApplicationByStatus((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/admin/applications/documentsuploaded");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetDocumentUploaded2() throws Exception {
        when(iLoanApplicationService.retrieveLoanApplicationByStatus((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admin/applications/documentsuploaded");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetPendingApplications() throws Exception {
        when(iLoanApplicationService.retrieveLoanApplicationByStatus((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/application/pending");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetPendingApplications2() throws Exception {
        when(iLoanApplicationService.retrieveLoanApplicationByStatus((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admin/application/pending");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetUsers() throws Exception {
        when(iCustomerService.viewAllCustomers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/users");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testRaiseLandOfficerTicket() throws Exception {
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
        when(iLoanApplicationService.updateLoanApplication(anyLong())).thenReturn(loanApplication);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/admin/application/document/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(adminController)
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

    @Test
    void testRetrieveLoanAgreementById() throws Exception {
        EMI emi = new EMI();
        emi.setDeuDate(LocalDate.ofEpochDay(1L));
        emi.setEmiAmount(10.0d);
        emi.setEmiId(123L);
        emi.setInterestAmount(10.0d);
        emi.setLoanAmount(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setEmi(emi);
        loanAgreement.setLoanAgreementId(123L);
        when(iLoanAgreementServiceImpl.retrieveAgreementById(anyLong())).thenReturn(loanAgreement);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/loanagreement/{id}", 123L);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"loanAgreementId\":123,\"emi\":{\"emiId\":123,\"deuDate\":[1970,1,2],\"emiAmount\":10.0,\"loanAmount\":10.0,"
                                        + "\"interestAmount\":10.0}}"));
    }


    @Test
    void testUpdateApplicationById() throws Exception {
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
        when(iLoanApplicationService.updateLoanApplication(anyLong())).thenReturn(loanApplication);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/admin/application/{id}", 123L);
        MockMvcBuilders.standaloneSetup(adminController)
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

