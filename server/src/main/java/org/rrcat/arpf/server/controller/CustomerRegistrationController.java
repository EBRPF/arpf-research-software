package org.rrcat.arpf.server.controller;

import org.dae.arpf.dto.CustomerRegistrationRequest;
import org.rrcat.arpf.server.entity.Customer;
import org.rrcat.arpf.server.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public final class CustomerRegistrationController {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerRegistrationController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public void registerCustomer(CustomerRegistrationRequest registrationRequest) {
        new Customer();

    }

}
