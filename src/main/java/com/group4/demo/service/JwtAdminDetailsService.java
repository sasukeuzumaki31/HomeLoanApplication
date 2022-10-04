package com.group4.demo.service;

import java.util.ArrayList;

import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.dto.AdminDto;
import com.group4.demo.entity.Admin;
import com.group4.demo.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtAdminDetailsService implements UserDetailsService {
    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    IAdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin user = adminRepository.findByAdminName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getAdminName(), user.getPassword(),
                new ArrayList<>());
    }

    public Admin save(AdminDto user) throws CouldNotBeAddedException {

        Admin newAdmin = adminService.addAdmin(user);


        return adminRepository.save(newAdmin);
    }
}
