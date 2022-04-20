package com.example.demo.controller;

import com.example.demo.domain.CustomerModel;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ModelAttribute("customer")
    public CustomerModel customerModel() {
        return new CustomerModel();
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "index";
    }

    @PostMapping("/registration")
    public String registerCustomer(@ModelAttribute("customer") CustomerModel customerModel) {

        if(customerService.registerCustomer(customerModel) == null){
            return "redirect:/registration?error";
        }

        return "redirect:/registration?success";
    }

    @PostMapping("/login")
    public String loginCustomer(@ModelAttribute("customer") CustomerModel customerModel) {

        CustomerModel loggedCustomer = customerService.loginCustomer(customerModel);

        if(loggedCustomer == null) {
            return "redirect:/login?error";
        }

        return "redirect:/welcome";
    }

}
