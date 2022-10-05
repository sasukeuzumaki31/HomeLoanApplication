package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.dto.AdminDto;
import com.group4.demo.entity.Admin;
import com.group4.demo.repository.IAdminRepository;

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

@ContextConfiguration(classes = {IAdminServiceImpl.class})
@ExtendWith(SpringExtension.class)
class IAdminServiceImplTest {
    @MockBean
    private IAdminRepository iAdminRepository;

    @Autowired
    private IAdminServiceImpl iAdminServiceImpl;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void testViewAdmin() throws ResourceNotFoundException {
        Admin admin = new Admin();
        admin.setAdminContact("1234567890");
        admin.setAdminContact("Rajesh");
        admin.setPassword("Pass@123");
        admin.setRole("Role");
        admin.setUserId(123);
        Optional<Admin> ofResult = Optional.of(admin);
        when(iAdminRepository.findById(any())).thenReturn(ofResult);
        assertSame(admin, iAdminServiceImpl.viewAdmin(1));
        verify(iAdminRepository).findById(any());
    }

    @Test
    void testViewAdmin2() throws ResourceNotFoundException {
        when(iAdminRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> iAdminServiceImpl.viewAdmin(1));
        verify(iAdminRepository).findById(any());
    }

    @Test
    void testViewAllAdmin() throws ResourceNotFoundException {
        when(iAdminRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> iAdminServiceImpl.viewAllAdmin());
        verify(iAdminRepository).findAll();
    }

    @Test
    void testViewAllAdmin2() throws ResourceNotFoundException {
        Admin admin = new Admin();
        admin.setAdminContact("Entered into viewAllAdmin method in AdminService Class");
        admin.setAdminName("Entered into viewAllAdmin method in AdminService Class");
        admin.setPassword("Pass@123");
        admin.setRole("Entered into viewAllAdmin method in AdminService Class");
        admin.setUserId(123);

        ArrayList<Admin> adminList = new ArrayList<>();
        adminList.add(admin);
        when(iAdminRepository.findAll()).thenReturn(adminList);
        List<Admin> actualViewAllAdminResult = iAdminServiceImpl.viewAllAdmin();
        assertSame(adminList, actualViewAllAdminResult);
        assertEquals(1, actualViewAllAdminResult.size());
        verify(iAdminRepository, atLeast(1)).findAll();
    }

    @Test
    void testAddAdmin() throws CouldNotBeAddedException {
        Admin admin = new Admin();
        admin.setAdminContact("1234567890");
        admin.setAdminContact("Rajesh");
        admin.setPassword("Pass@123");
        admin.setRole("Role");
        admin.setUserId(123);

        Admin admin1 = new Admin();
        admin1.setAdminContact("1234567890");
        admin1.setAdminName("Rajesh");
        admin1.setPassword("Pass@123");
        admin1.setRole("Role");
        admin1.setUserId(123);
        when(iAdminRepository.findByAdminName(any())).thenReturn(admin);
        when(iAdminRepository.save(any())).thenReturn(admin1);
        when(passwordEncoder.encode( any())).thenReturn("secret");
        assertThrows(CouldNotBeAddedException.class,
                () -> iAdminServiceImpl.addAdmin(new AdminDto(123, "iloveyou", "Admin Name", "Admin Contact")));
        verify(iAdminRepository).findByAdminName( any());
        verify(passwordEncoder).encode(any());
    }


    @Test
    void testUpdateAdmin() throws ResourceNotFoundException {
        Admin admin = new Admin();
        admin.setAdminContact("1234567890");
        admin.setAdminContact("Rajesh");
        admin.setPassword("Pass@123");
        admin.setRole("Role");
        admin.setUserId(123);
        Optional<Admin> ofResult = Optional.of(admin);

        Admin admin1 = new Admin();
        admin1.setAdminContact("1234567890");
        admin1.setAdminName("Rajesh");
        admin1.setPassword("Pass@123");
        admin1.setRole("Role");
        admin1.setUserId(123);
        when(iAdminRepository.save(any())).thenReturn(admin1);
        when(iAdminRepository.findById(any())).thenReturn(ofResult);

        Admin admin2 = new Admin();
        admin2.setAdminContact("1234567890");
        admin2.setAdminName("Rajesh");
        admin2.setPassword("Pass@123");
        admin2.setRole("Role");
        admin2.setUserId(123);
        assertSame(admin1, iAdminServiceImpl.updateAdmin(1, admin2));
        verify(iAdminRepository).save(any());
        verify(iAdminRepository).findById(any());
    }

    @Test
    void testUpdateAdmin2() throws ResourceNotFoundException {
        Admin admin = new Admin();
        admin.setAdminContact("1234567890");
        admin.setAdminContact("Rajesh");
        admin.setPassword("Pass@123");
        admin.setRole("Role");
        admin.setUserId(123);
        when(iAdminRepository.save(any())).thenReturn(admin);
        when(iAdminRepository.findById(any())).thenReturn(Optional.empty());

        Admin admin1 = new Admin();
        admin1.setAdminContact("1234567890");
        admin1.setAdminName("Rajesh");
        admin1.setPassword("Pass@123");
        admin1.setRole("Role");
        admin1.setUserId(123);
        assertThrows(ResourceNotFoundException.class, () -> iAdminServiceImpl.updateAdmin(1, admin1));
        verify(iAdminRepository).findById(any());
    }
}

