package com.example.demo.service;

import com.example.demo.domain.CustomerModel;
import com.example.demo.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService = new CustomerServiceImpl();

    @Test
    public void testRegisterCustomerWithCustomerAlreadyExists() {

        when(customerRepository.existsByUsername(any())).thenReturn(true);

        CustomerModel customerModel = new CustomerModel();
        customerModel.setUsername("user1");

        CustomerModel response = customerService.registerCustomer(customerModel);

        assertNull(response);

    }

    @Test
    public void testRegisterCustomerWithNewCustomer() {

        CustomerModel customerModel = new CustomerModel();
        customerModel.setUsername("user1");
        customerModel.setEmail("user1@gmail.com");
        customerModel.setPhone("123456");

        when(customerRepository.existsByUsername(any())).thenReturn(false);
        when(customerRepository.existsByEmail(any())).thenReturn(false);
        when(customerRepository.save(customerModel)).thenReturn(customerModel);

        CustomerModel response = customerService.registerCustomer(customerModel);

        assertNotNull(response);
        assertEquals("123456", response.getPhone());
        assertEquals("user1", response.getUsername());
        assertEquals("user1@gmail.com", response.getEmail());

    }

    @Test
    public void testLoginCustomer() {

        CustomerModel customerModel = new CustomerModel();
        customerModel.setUsername("user1");
        customerModel.setEmail("user1@gmail.com");
        customerModel.setPassword("pass1");
        customerModel.setPhone("123456");

        when(customerRepository.findByUsernameAndPassword(customerModel.getUsername(), customerModel.getPassword()))
                        .thenReturn(customerModel);

        CustomerModel response = customerService.loginCustomer(customerModel);

        assertNotNull(response);
        assertEquals("user1", response.getUsername());
        assertEquals("user1@gmail.com", response.getEmail());
        assertEquals("pass1", response.getPassword());
        assertEquals("123456", response.getPhone());

    }

}
