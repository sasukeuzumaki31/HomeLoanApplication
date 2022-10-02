package com.group4.demo.service;

import com.group4.demo.Dto.AdminDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Admin;

import java.util.List;

public interface IAdminService {

    Admin viewAdmin(int adminid) throws ResourceNotFoundException;

    List<Admin> viewAllAdmin();

    Admin addAdmin(AdminDto adminDto);

    Admin updateAdmin(int id, Admin admin) throws ResourceNotFoundException;

    //Admin deleteAdmin(int id, Admin admin);

    Admin deleteAdminById(int adminId);
}