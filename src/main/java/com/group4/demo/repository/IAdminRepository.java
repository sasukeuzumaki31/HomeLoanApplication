package com.group4.demo.repository;

import com.group4.demo.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {

    public Admin findByAdminName(String adminName);
}