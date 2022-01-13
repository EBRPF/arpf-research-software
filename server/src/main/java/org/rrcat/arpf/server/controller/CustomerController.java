package org.rrcat.arpf.server.controller;

import org.dae.arpf.dto.CustomerDTO;
import org.rrcat.arpf.server.entity.Customer;
import org.rrcat.arpf.server.entity.UploadedImage;
import org.rrcat.arpf.server.repository.CustomerRepository;
import org.rrcat.arpf.server.repository.UploadedImageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/customer")
public final class CustomerController {
    private final CustomerRepository repository;
    private final UploadedImageRepository imageRepository;

    public CustomerController(final CustomerRepository repository, final UploadedImageRepository imageRepository) {
        this.repository = repository;
        this.imageRepository = imageRepository;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<CustomerDTO> registerCustomer(@RequestBody final CustomerDTO customerDTO, final HttpServletRequest request) {
        final Customer preRegistered = repository.findCustomerByRegistrationNo(customerDTO.registrationNo());
        if (preRegistered != null) {
            throw new IllegalArgumentException("Customer with given registration number already registered");
        }
        final Integer key = Objects.requireNonNull(customerDTO.imageKey(), "Image key must not be null");
        final UploadedImage image = Objects.requireNonNull(imageRepository.findUploadedImageById(key), "Image must already be uploaded");
        final Customer customer = repository.save(Customer.fromDTO(customerDTO, image));
        return ResponseEntity.created(URI.create(request.getRequestURI()).resolve("../fetch/" + customer.getRegistrationNo())).body(Customer.toDTO(customer));
    }

    @GetMapping("/fetch/{registrationId}")
    @ResponseBody
    public CustomerDTO fetchCustomer(@PathVariable final Integer registrationId) {
        final Customer customer = Objects.requireNonNull(repository.findCustomerByRegistrationNo(registrationId), "Customer must already have been registered");
        return Customer.toDTO(customer);
    }

    @GetMapping("/fetch/org/{organizationName}")
    @ResponseBody
    public CustomerDTO fetchCustomerByOrganization(@PathVariable final String organizationName) {
        final Customer customer = Objects.requireNonNull(repository.findCustomerByOrganizationName(organizationName), "Customer must already have been registered");
        return Customer.toDTO(customer);
    }

    @GetMapping("/search/{registrationId}")
    @ResponseBody
    public Collection<CustomerDTO> searchCustomer(@PathVariable final String registrationId) {
        final Collection<Customer> customer = repository.findCustomersByRegistrationNoFuzzy(registrationId);
        return customer.stream().map(Customer::toDTO).collect(Collectors.toSet());
    }

    @GetMapping("/search/org/{organizationName}")
    @ResponseBody
    public Collection<CustomerDTO> searchCustomerByOrganization(@PathVariable final String organizationName) {
        final Collection<Customer> customer = repository.findCustomersByOrganizationNameFuzzy(organizationName);
        return customer.stream().map(Customer::toDTO).collect(Collectors.toSet());
    }
}
