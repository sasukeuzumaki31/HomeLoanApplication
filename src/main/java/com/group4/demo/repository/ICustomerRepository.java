package com.group4.demo.repository;

import com.group4.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select c from Customer c where userId in ( select l.customer.userId from LoanApplication l " +
            "where l.applicationDate = ?1)")
    List<Customer> findByDateOfApplication(LocalDate dateOfApplication);

    Customer findByMobileNumber(String mobileNumber);
}
