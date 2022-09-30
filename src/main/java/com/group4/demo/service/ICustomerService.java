package com.group4.demo.service;

import com.group4.demo.Dto.CustomerDto;
import com.group4.demo.entity.Customer;

import java.time.LocalDate;
import java.util.List;

public interface ICustomerService {

    Customer viewCustomer(int custid);

    List<Customer> viewAllCustomers();

    Customer addCustomer(CustomerDto customer);

    Customer updateCustomer(int id, Customer customer);

    Customer deleteCustomer(int id, Customer customer);

    List<Customer> viewCustomerList(LocalDate dateOfApplication);
    Customer deleteCustomerById(int custId);
}
