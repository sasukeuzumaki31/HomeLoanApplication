package com.group4.demo.service.impl;

import com.group4.demo.entity.Customer;
import com.group4.demo.repository.ICustomerRepository;
import com.group4.demo.repository.ILoanApplicationRepository;
import com.group4.demo.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class ICustomerServiceImpl implements ICustomerService {

    @Autowired
    ICustomerRepository custRepo;

    @Autowired
    ILoanApplicationRepository loanRepo;

    @Override
    public Customer viewCustomer(int custid) {
        Optional<Customer> customer = custRepo.findById(custid);
        if(!customer.isPresent()){
            return null;
        }
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
    public Customer updateCustomer(int id, Customer customer) {
        Optional<Customer> customerOp = custRepo.findById(id);
        if(!customerOp.isPresent()){
            return null;
        }
        return custRepo.save(customer);
    }

    @Override
    public Customer deleteCustomer(int id, Customer customer) {
        Optional<Customer> customerOp = custRepo.findById(id);
        if(!customerOp.isPresent()){
            return null;
        }
        custRepo.delete(customer);
        return customerOp.get();
    }

    @Override
    public List<Customer> viewCustomerList(LocalDate dateOfApplication) {
//        return custRepo.findByDateOfApplication(dateOfApplication);
        return null;
    }

    @Override
    public Customer deleteCustomerById(int custId) {
        Optional<Customer> customerOp = custRepo.findById(custId);
        if(!customerOp.isPresent()) {
            return null;
        }
        custRepo.delete(customerOp.get());
        return customerOp.get();
    }
}
