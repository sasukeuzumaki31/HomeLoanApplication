package com.group4.demo.service.impl;

import com.group4.demo.Dto.AdminDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Admin;
import com.group4.demo.repository.IAdminRepository;
import com.group4.demo.service.IAdminService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IAdminServiceImpl implements IAdminService {
    Log logger = LogFactory.getLog(IAdminServiceImpl.class);
    @Autowired
    IAdminRepository adminRepo;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public Admin viewAdmin(int adminid) throws ResourceNotFoundException {
        logger.info("Entered into viewAdmin method in AdminService Class");
        return adminRepo.findById(adminid).orElseThrow(()->new ResourceNotFoundException("Admin does not exists with id : " + adminid));
    }

    @Override
    public List<Admin> viewAllAdmin() throws ResourceNotFoundException{
        logger.info("Entered into viewAllAdmin method in AdminService Class");
        if(adminRepo.findAll().isEmpty()){
            throw new ResourceNotFoundException("No User Found");
        }
        logger.info("Exiting viewAdmin method in AdminService Class");
        return adminRepo.findAll();
    }

    @Override
    public Admin addAdmin(AdminDto adminDto) {
        logger.info("Entered into addAdmin method in AdminService Class");
        Admin newAdmin = new Admin();

        newAdmin.setAdminName(adminDto.getAdminName());
        newAdmin.setPassword(bcryptEncoder.encode(adminDto.getPassword()));
        newAdmin.setAdminContact(adminDto.getAdminContact());
        newAdmin.setRole("ADMIN");
        logger.info("Exiting addAdmin method in AdminService Class");
        return adminRepo.save(newAdmin);
    }

    @Override
    public Admin updateAdmin(int id, Admin admin) throws ResourceNotFoundException {
        logger.info("Entered into updateAdmin method in AdminService Class");
        Admin adminOp = adminRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Admin does not exists with id : " + id));
        logger.info("Exiting updateAdmin method in AdminService Class");
        return adminRepo.save(adminOp);
    }

}
