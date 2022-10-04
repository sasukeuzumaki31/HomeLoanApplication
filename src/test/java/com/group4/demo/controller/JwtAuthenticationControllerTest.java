package com.group4.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.demo.config.JwtTokenUtil;
import com.group4.demo.dto.AdminDto;
import com.group4.demo.entity.JwtRequest;
import com.group4.demo.service.IAdminService;
import com.group4.demo.service.JwtAdminDetailsService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class JwtAuthenticationControllerTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private IAdminService iAdminService;

    @MockBean
    private JwtAdminDetailsService jwtAdminDetailsService;

    @Autowired
    private JwtAuthenticationController jwtAuthenticationController;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Test
    void testCreateAuthenticationToken() throws Exception {
        when(jwtTokenUtil.generateToken(any())).thenReturn("ABC123");
        when(jwtAdminDetailsService.loadUserByUsername(any()))
                .thenReturn(new User("janedoe", "iloveyou", new ArrayList<>()));
        when(authenticationManager.authenticate(any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setPassword("iloveyou");
        jwtRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(jwtRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(jwtAuthenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"token\":\"ABC123\"}"));
    }


    @Test
    void testSaveUser() throws Exception {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminContact("Admin Contact");
        adminDto.setAdminName("Admin Name");
        adminDto.setPassword("iloveyou");
        adminDto.setRole("Role");
        adminDto.setUserId(123);
        String content = (new ObjectMapper()).writeValueAsString(adminDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(jwtAuthenticationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

