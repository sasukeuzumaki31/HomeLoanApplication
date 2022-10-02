package com.group4.demo.service.impl;

import com.group4.demo.Dto.AdminDto;
import com.group4.demo.advices.ResourceNotFoundException;
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
    public Admin viewAdmin(int adminid) throws ResourceNotFoundException {
        return adminRepo.findById(adminid).orElseThrow(()->new ResourceNotFoundException("Admin does not exists with id : " + adminid));
    }

    @Override
    public List<Admin> viewAllAdmin() throws ResourceNotFoundException{
        if(adminRepo.findAll().isEmpty()){
            throw new ResourceNotFoundException("No User Found");
        }
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
    public Admin updateAdmin(int id, Admin admin) throws ResourceNotFoundException {
        Admin adminOp = adminRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Admin does not exists with id : " + id));
        return adminRepo.save(adminOp);
    }

//    @Override
//    public Admin deleteAdmin(int id, Admin admin) {
//        Optional<Admin> adminOp = adminRepo.findById(id);
//        if (!adminOp.isPresent()) {
//            return null;
//        }
//        adminRepo.delete(admin);
//        return adminOp.get();
//    }

    @Override
    public Admin deleteAdminById(int adminId) throws ResourceNotFoundException {
        Optional<Admin> adminOp = adminRepo.findById(adminId);
        if (adminOp.isPresent()) {
            adminRepo.deleteById(adminId);
            return adminOp.get();
        }
        else{
            throw new ResourceNotFoundException("User Not found for Id" + adminId);
        }
    }
}
