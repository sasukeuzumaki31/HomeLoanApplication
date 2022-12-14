package com.group4.demo.service;

import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.dto.AdminDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Admin;
import java.util.List;

public interface IAdminService {

    Admin viewAdmin(int adminid) throws ResourceNotFoundException;

    List<Admin> viewAllAdmin() throws ResourceNotFoundException;

    Admin addAdmin(AdminDto adminDto) throws CouldNotBeAddedException;

    Admin updateAdmin(int id, Admin admin) throws ResourceNotFoundException;


}