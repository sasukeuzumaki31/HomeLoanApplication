package com.group4.demo.service.impl;

import com.group4.demo.Dto.AdminDto;
import com.group4.demo.entity.Admin;
import com.group4.demo.repository.IAdminRepository;
import com.group4.demo.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IAdminServiceImpl implements IAdminService {

    @Autowired
    IAdminRepository adminRepo;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public Admin viewAdmin(int adminid) {
        Optional<Admin> admin = adminRepo.findById(adminid);
        if (!admin.isPresent()) {
            return null;
        }
        return admin.get();
    }

    @Override
    public List<Admin> viewAllAdmin() {
        return adminRepo.findAll();
    }

    @Override
    public Admin addAdmin(AdminDto adminDto) {
        Admin newAdmin = new Admin();

        newAdmin.setAdminName(adminDto.getAdminName());
        newAdmin.setPassword(bcryptEncoder.encode(adminDto.getPassword()));
        newAdmin.setAdminContact(adminDto.getAdminContact());
        newAdmin.setRole("ADMIN");

        return adminRepo.save(newAdmin);
    }

    @Override
    public Admin updateAdmin(int id, Admin admin) {
        Optional<Admin> adminOp = adminRepo.findById(id);
        if (!adminOp.isPresent()) {
            return null;
        }
        return adminRepo.save(admin);
    }

    @Override
    public Admin deleteAdmin(int id, Admin admin) {
        Optional<Admin> adminOp = adminRepo.findById(id);
        if (!adminOp.isPresent()) {
            return null;
        }
        adminRepo.delete(admin);
        return adminOp.get();
    }

    @Override
    public Admin deleteAdminById(int adminId) {
        Optional<Admin> adminOp = adminRepo.findById(adminId);
        if (!adminOp.isPresent()) {
            return null;
        }
        adminRepo.delete(adminOp.get());
        return adminOp.get();
    }
}
