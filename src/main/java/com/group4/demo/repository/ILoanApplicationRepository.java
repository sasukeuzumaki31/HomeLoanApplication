package com.group4.demo.repository;

import com.group4.demo.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoanApplicationRepository extends JpaRepository<LoanApplication,Long> {

}
