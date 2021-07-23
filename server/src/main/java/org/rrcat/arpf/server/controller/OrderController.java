package org.rrcat.arpf.server.controller;

import org.dae.arpf.dto.OrderDTO;
import org.rrcat.arpf.server.entity.Customer;
import org.rrcat.arpf.server.entity.Order;
import org.rrcat.arpf.server.entity.UploadedImage;
import org.rrcat.arpf.server.repository.OrderRepository;
import org.rrcat.arpf.server.repository.UploadedImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Objects;

@Controller
@RequestMapping("/api/v1/order")
public final class OrderController {

    private final OrderRepository repository;
    private final UploadedImageRepository imageRepository;

    @Autowired
    public OrderController(final OrderRepository repository, final UploadedImageRepository imageRepository) {
        this.repository = repository;
        this.imageRepository = imageRepository;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Void> registerCustomer(@RequestBody final OrderDTO orderDTO, final HttpServletRequest request) {
        final Order preRegistered = repository.findOrderByRegistrationNo(orderDTO.registrationNo());
        if (preRegistered != null) {
            throw new IllegalArgumentException("Order with given registration number already registered");
        }
        final Integer key = Objects.requireNonNull(orderDTO.imageKey(), "Image key must not be null");
        final UploadedImage image = Objects.requireNonNull(imageRepository.findUploadedImageById(key), "Image must already be uploaded");
        final Order customer = repository.save(Order.fromDTO(orderDTO, image));
        return ResponseEntity.created(URI.create(request.getRequestURI()).resolve("../fetch/" + customer.getRegistrationNo())).build();
    }
}
