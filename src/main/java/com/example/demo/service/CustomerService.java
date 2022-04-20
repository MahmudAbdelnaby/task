package com.example.demo.service;

import com.example.demo.domain.CustomerModel;
import com.example.demo.repository.CustomerRepository;

public interface CustomerService {

    CustomerModel registerCustomer(CustomerModel customerModel);
    CustomerModel loginCustomer(CustomerModel customerModel);

}
