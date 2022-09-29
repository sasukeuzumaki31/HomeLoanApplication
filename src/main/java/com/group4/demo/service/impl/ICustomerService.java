package com.group4.demo.service.impl;

import com.group4.demo.entity.Customer;
import com.group4.demo.repository.ICustomerRepository;
import com.group4.demo.repository.ILoanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ICustomerService implements com.group4.demo.service.ICustomerService {

    @Autowired
    ICustomerRepository custRepo;

    @Autowired
    ILoanApplicationRepository loanRepo;

    @Override
    public Customer viewCustomer(int custid) {
        Optional<Customer> customer = custRepo.findById(custid);
        return customer.get();
    }

    @Override
    public List<Customer> viewAllCustomers() {
        return custRepo.findAll();
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return custRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return custRepo.save(customer);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        custRepo.delete(customer);
        return customer;
    }

    @Override
    public List<Customer> viewCustomerList(LocalDate dateOfApplication) {
//        return custRepo.findByDateOfApplication(dateOfApplication);
        return null;
    }
}
