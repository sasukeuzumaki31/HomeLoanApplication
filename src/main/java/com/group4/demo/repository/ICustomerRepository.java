package com.group4.demo.repository;

import com.group4.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

//    @Query("select c from Customer c, LoanApplication l where l.loanApplicationId = c.loanApplicationId " +
//            "and l.applicationDate = :dateOfApplication")
//    List<Customer> findByDateOfApplication(LocalDate dateOfApplication);
}
