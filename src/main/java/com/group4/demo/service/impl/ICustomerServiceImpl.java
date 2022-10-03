package com.group4.demo.service.impl;

import com.group4.demo.Dto.CustomerDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Customer;
import com.group4.demo.entity.LoanApplication;
import com.group4.demo.entity.Status;
import com.group4.demo.repository.ICustomerRepository;
import com.group4.demo.repository.ILoanApplicationRepository;
import com.group4.demo.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ICustomerServiceImpl implements ICustomerService {

    @Autowired
    ICustomerRepository custRepo;

    @Autowired
    ILoanApplicationRepository loanRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public Customer viewCustomer(int custId) throws ResourceNotFoundException{
        return custRepo.findById(custId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not found for Id " + custId));
    }

    @Override
    public List<Customer> viewAllCustomers() throws ResourceNotFoundException{
        if(custRepo.findAll().isEmpty()){
            throw new ResourceNotFoundException("No Users Found");
        }
        return custRepo.findAll();
    }

    @Override
    public Customer addCustomer(CustomerDto customer) {

        Customer newCustomer = new Customer();
        newCustomer.setCustomerName(customer.getCustomerName());
        newCustomer.setGender(customer.getGender());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setPassword(bcryptEncoder.encode(customer.getPassword()));
        newCustomer.setMobileNumber(customer.getMobileNumber());
        newCustomer.setAadharNumber(customer.getAadharNumber());
        newCustomer.setPanNumber(customer.getPanNumber());
        newCustomer.setNationality(customer.getNationality());
        newCustomer.setRole("CUSTOMER");

        /*
        Converting Date from String to LocalDate
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        newCustomer.setDateOfBirth(LocalDate.parse(customer.getDateOfBirth(), formatter));

        return custRepo.save(newCustomer);
    }

    @Override
    public Customer updateCustomer(int id, CustomerDto customerDto) throws ResourceNotFoundException{
        Optional<Customer> customerOp = custRepo.findById(id);
        if(customerOp.isPresent()){
            Customer customer = customerOp.get();
            customer.setPanNumber(customerDto.getPanNumber());
            customer.setAadharNumber(customerDto.getAadharNumber());
            LoanApplication loanApplication = loanRepo.findByCustomerId(id);
            loanApplication.setStatus(String.valueOf(Status.DOCUMENTS_UPLOADED));
            loanRepo.save(loanApplication);
            return custRepo.save(customer);
        }
        else{
            throw new ResourceNotFoundException("User Not found for Id" + id);
        }
    }

    @Override
    public List<Customer> viewCustomerList(LocalDate dateOfApplication) {
//        return custRepo.findByDateOfApplication(dateOfApplication);
        return null;
    }

    @Override
    public Customer deleteCustomerById(int custId) throws ResourceNotFoundException{
        Optional<Customer> customerOp = custRepo.findById(custId);
        if(customerOp.isPresent()){
            custRepo.deleteById(custId);
            return customerOp.get();
        }
        else{
            throw new ResourceNotFoundException("User Not found for Id" + custId);
        }
    }
}
