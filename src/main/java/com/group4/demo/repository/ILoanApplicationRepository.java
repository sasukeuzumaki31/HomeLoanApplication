package com.group4.demo.repository;

import com.group4.demo.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILoanApplicationRepository extends JpaRepository<LoanApplication,Long> {

    List<LoanApplication> findByStatus(String status);
}
