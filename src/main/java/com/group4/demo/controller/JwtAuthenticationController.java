package com.group4.demo.controller;



import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.dto.AdminDto;
import com.group4.demo.advices.AuthenticationFailedException;
import com.group4.demo.config.JwtTokenUtil;
import com.group4.demo.entity.Admin;
import com.group4.demo.entity.JwtRequest;
import com.group4.demo.entity.JwtResponse;
import com.group4.demo.service.IAdminService;
import com.group4.demo.service.JwtAdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    IAdminService adminService;
    @Autowired
    private JwtAdminDetailsService userDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws AuthenticationFailedException {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/admin/signup")
    public ResponseEntity<Admin> saveUser(@Valid @RequestBody AdminDto user) throws CouldNotBeAddedException {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    private void authenticate(String username, String password) throws AuthenticationFailedException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationFailedException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new AuthenticationFailedException("INVALID_CREDENTIALS");
        }
    }
}
