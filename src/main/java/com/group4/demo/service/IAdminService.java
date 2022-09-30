package com.group4.demo.service;

import com.group4.demo.Dto.AdminDto;
import com.group4.demo.entity.Admin;

import java.util.List;

public interface IAdminService {

    Admin viewAdmin(int adminid);

    List<Admin> viewAllAdmin();

    Admin addAdmin(AdminDto adminDto);

    Admin updateAdmin(int id, Admin admin);

    Admin deleteAdmin(int id, Admin admin);

    Admin deleteAdminById(int adminId);
}