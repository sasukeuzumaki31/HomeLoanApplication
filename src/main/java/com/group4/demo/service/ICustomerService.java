package com.group4.demo.service;

import com.group4.demo.Dto.CustomerDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Customer;

import java.time.LocalDate;
import java.util.List;

public interface ICustomerService {

    Customer viewCustomer(int custid) throws ResourceNotFoundException;

    List<Customer> viewAllCustomers() throws ResourceNotFoundException;

    Customer addCustomer(CustomerDto customer);

    Customer updateCustomer(int id, CustomerDto customerDto) throws ResourceNotFoundException;

    List<Customer> viewCustomerList(LocalDate dateOfApplication);
    Customer deleteCustomerById(int custId) throws ResourceNotFoundException;
}
