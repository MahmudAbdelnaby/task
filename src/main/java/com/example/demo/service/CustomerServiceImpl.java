package com.example.demo.service;

import com.example.demo.domain.CustomerModel;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements  CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerModel registerCustomer(CustomerModel customerModel) {

        if(customerRepository.existsByUsername(customerModel.getUsername())
                || customerRepository.existsByEmail(customerModel.getEmail())) {
            return null;
        }
        return customerRepository.save(customerModel);
    }

    @Override
    public CustomerModel loginCustomer(CustomerModel customerModel) {
        return customerRepository.findByUsernameAndPassword(customerModel.getUsername(), customerModel.getPassword());
    }
}
