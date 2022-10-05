package com.group4.demo.service;

import com.group4.demo.advices.AuthenticationFailedException;
import com.group4.demo.advices.CouldNotBeAddedException;
import com.group4.demo.dto.CustomerDto;
import com.group4.demo.dto.DocsDto;
import com.group4.demo.dto.UserLoginDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Customer;

import java.util.List;

public interface ICustomerService {

    Customer viewCustomer(int custid) throws ResourceNotFoundException;

    List<Customer> viewAllCustomers() throws ResourceNotFoundException;

    Customer addCustomer(CustomerDto customer) throws CouldNotBeAddedException;

    Customer updateCustomer(int id, DocsDto docsDto) throws ResourceNotFoundException;

    List<Customer> viewCustomerList(String dateOfApplication) throws ResourceNotFoundException;

    Customer deleteCustomerById(int custId) throws ResourceNotFoundException;


    String loginCustomer(UserLoginDto userLoginDto) throws AuthenticationFailedException;
}
