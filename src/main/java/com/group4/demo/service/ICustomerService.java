package com.group4.demo.service;

import com.group4.demo.Dto.CustomerDto;
import com.group4.demo.Dto.UserLoginDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Customer;

import java.util.List;

public interface ICustomerService {

    Customer viewCustomer(int custid) throws ResourceNotFoundException;

    List<Customer> viewAllCustomers() throws ResourceNotFoundException;

    Customer addCustomer(CustomerDto customer);

    Customer updateCustomer(int id, CustomerDto customerDto) throws ResourceNotFoundException;

    List<Customer> viewCustomerList(String dateOfApplication) throws ResourceNotFoundException;

    Customer deleteCustomerById(int custId) throws ResourceNotFoundException;


    String loginCustomer(UserLoginDto UserLoginDto) throws ResourceNotFoundException;
}
