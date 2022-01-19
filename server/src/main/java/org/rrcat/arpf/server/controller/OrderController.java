package org.rrcat.arpf.server.controller;

import org.dae.arpf.dto.OrderDTO;
import org.rrcat.arpf.server.entity.Order;
import org.rrcat.arpf.server.entity.UploadedImage;
import org.rrcat.arpf.server.repository.CustomerRepository;
import org.rrcat.arpf.server.repository.OrderRepository;
import org.rrcat.arpf.server.repository.UploadedImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.Objects;

@Controller
@RequestMapping("/api/v1/order")
public final class OrderController {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final UploadedImageRepository imageRepository;

    @Autowired
    public OrderController(final OrderRepository orderRepository, CustomerRepository customerRepository, final UploadedImageRepository imageRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.imageRepository = imageRepository;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<OrderDTO> registerOrder(@RequestBody final OrderDTO orderDTO, final HttpServletRequest request) {
        final Order order = orderRepository.save(Order.fromDTO(orderDTO, customerRepository, imageRepository));
        return ResponseEntity.created(URI.create(request.getRequestURI()).resolve("../fetch/" + order.getRegistrationNo())).body(Order.toDTO(order));
    }

    @GetMapping("/fetch/{registrationId}")
    @ResponseBody
    public OrderDTO fetchOrder(@PathVariable final Integer registrationId) {
        final Order preRegistered = orderRepository.findOrderByRegistrationNo(registrationId);
        final OrderDTO dto = Order.toDTO(preRegistered);
        return Objects.requireNonNull(dto, "fetched Order must exist!");
    }
}
