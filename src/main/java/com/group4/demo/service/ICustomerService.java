package com.group4.demo.service;

import com.group4.demo.entity.Customer;

import java.time.LocalDate;
import java.util.List;

public interface ICustomerService {

    Customer viewCustomer(int custid);

    List<Customer> viewAllCustomers();

    Customer addCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer deleteCustomer(Customer customer);

    List<Customer> viewCustomerList(LocalDate dateOfApplication);
}
