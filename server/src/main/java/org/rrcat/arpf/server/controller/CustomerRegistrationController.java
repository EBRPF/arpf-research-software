package org.rrcat.arpf.server.controller;

import org.dae.arpf.dto.AddressDTO;
import org.dae.arpf.dto.ContactInfoDTO;
import org.dae.arpf.dto.CustomerDTO;
import org.dae.arpf.dto.OrganizationDTO;
import org.rrcat.arpf.server.entity.Customer;
import org.rrcat.arpf.server.entity.UploadedImage;
import org.rrcat.arpf.server.entity.embedable.Address;
import org.rrcat.arpf.server.entity.embedable.ContactInfo;
import org.rrcat.arpf.server.entity.embedable.Organization;
import org.rrcat.arpf.server.repository.CustomerRepository;
import org.rrcat.arpf.server.repository.UploadedImageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Objects;

@Controller
@RequestMapping("/api/v1/")
public final class CustomerRegistrationController {
    private final CustomerRepository repository;
    private final UploadedImageRepository imageRepository;

    public CustomerRegistrationController(final CustomerRepository repository, final UploadedImageRepository imageRepository) {
        this.repository = repository;
        this.imageRepository = imageRepository;
    }

    @PostMapping("/customer/register")
    @ResponseBody
    public ResponseEntity<Void> registerCustomer(@RequestBody final CustomerDTO customerDTO, final HttpServletRequest request) {
        final Customer preRegistered = repository.findCustomerByRegistrationNo(customerDTO.getRegistrationNo());
        if (preRegistered != null) {
            throw new IllegalArgumentException("Customer with given registration number already registered");
        }
        final Integer key = Objects.requireNonNull(customerDTO.getImageKey(), "Image key must not be null");
        final UploadedImage image = Objects.requireNonNull(imageRepository.findUploadedImageById(key), "Image must already be uploaded");
        final Customer customer = repository.save(Customer.fromDTO(customerDTO, image));
        return ResponseEntity.created(URI.create(request.getRequestURI()).resolve("../fetch/" + customer.getRegistrationNo())).build();
    }

    @GetMapping("/customer/fetch/{registrationId}")
    @ResponseBody
    public CustomerDTO fetchCustomer(@PathVariable final Integer registrationNo) {
        final Customer customer = Objects.requireNonNull(repository.findCustomerByRegistrationNo(registrationNo), "Customer must already have been registered");
        final Address address = customer.getAddress();
        final Organization organization = customer.getOrganization();
        final ContactInfo researchHead = customer.getResearchHeadInfo();
        final ContactInfo researchOfficer = customer.getResearchOfficerInfo();
        return CustomerDTO.builder()
                .registrationNo(customer.getRegistrationNo())
                .address(
                        AddressDTO.builder()
                                .addressText(address.getAddressText())
                                .city(address.getCity())
                                .phone(address.getPhone())
                                .pinCode(address.getPinCode())
                                .state(address.getState())
                                .build()
                )
                .extraInfo(customer.getExtraInfo())
                .imageKey(customer.getImage().getId())
                .organization(
                        OrganizationDTO.builder()
                                .name(organization.getName())
                                .type(organization.getType())
                                .build()
                )
                .researchHeadInfo(
                        ContactInfoDTO.builder()
                                .email(researchHead.getEmail())
                                .mobileNo(researchHead.getMobileNo())
                                .email(researchHead.getName())
                                .build()
                )
                .researchOfficerInfo(
                        ContactInfoDTO.builder()
                                .email(researchOfficer.getEmail())
                                .mobileNo(researchOfficer.getMobileNo())
                                .email(researchOfficer.getName())
                                .build()
                )
                .build();
    }
}
