package com.group4.demo.repository;

import com.group4.demo.entity.FinanceVerificationOfficer;
import com.group4.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFinanceVerificationRepository extends JpaRepository<FinanceVerificationOfficer,Integer> {
}
