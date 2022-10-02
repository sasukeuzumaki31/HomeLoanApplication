package com.group4.demo.repository;

import com.group4.demo.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILoanApplicationRepository extends JpaRepository<LoanApplication,Long> {

    List<LoanApplication> findByStatus(String status);

    @Query("select l from LoanApplication l, Customer c where c.userId = l.customer.userId and c.userId = ?1")
    LoanApplication findByCustomerId(int id);
}
