package com.group4.demo.service.impl;

import com.group4.demo.Dto.AdminDto;
import com.group4.demo.entity.Admin;
import com.group4.demo.repository.IAdminRepository;
import com.group4.demo.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IAdminServiceImpl implements IAdminService {

    @Autowired
    IAdminRepository adminRepo;

    @Override
    public Admin viewAdmin(int adminId) {
        Optional<Admin> admin = adminRepo.findById(adminId);
        if (!admin.isPresent()) {
            return null;
        }
        return admin.get();
    }

    @Override
    public List<Admin> viewAllAdmins() {
        return adminRepo.findAll();
    }

    @Override
    public Admin addAdmin(AdminDto adminDto) {
        Admin newAdmin = new Admin();
        newAdmin.setAdminName(adminDto.getAdminName());
        newAdmin.setAdminContact(adminDto.getAdminContact());
        newAdmin.setPassword(adminDto.getPassword());
        newAdmin.setRole("ADMIN");

        return adminRepo.save(newAdmin);
    }

    @Override
    public Admin updateAdmin(int id, Admin admin) {
        return null;
    }

    @Override
    public Admin deleteAdmin(int id, Admin admin) {
        return null;
    }

    @Override
    public Admin deleteAdminById(int adminId) {
        return null;
    }
}
