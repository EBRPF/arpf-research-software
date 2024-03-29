package org.rrcat.arpf.server.controller;

import org.dae.arpf.dto.OrderDTO;
import org.dae.arpf.dto.OrderRadiationProcessingDTO;
import org.rrcat.arpf.server.entity.Order;
import org.rrcat.arpf.server.entity.OrderRadiationProcessingData;
import org.rrcat.arpf.server.repository.CustomerRepository;
import org.rrcat.arpf.server.repository.OrderRPRepository;
import org.rrcat.arpf.server.repository.OrderRepository;
import org.rrcat.arpf.server.repository.UploadedImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Objects;

@Controller
@RequestMapping("/api/v1/order/rp")
public final class OrderRPController {
    private final OrderRepository orderRepository;
    private final OrderRPRepository orderRPRepository;

    @Autowired
    public OrderRPController(OrderRepository orderRepository, final OrderRPRepository orderRPRepository) {
        this.orderRepository = orderRepository;
        this.orderRPRepository = orderRPRepository;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<OrderRadiationProcessingDTO> registerORP(@RequestBody final OrderRadiationProcessingDTO orderDTO, final HttpServletRequest request) {
        final OrderRadiationProcessingData preRegistered = orderRPRepository.findOrderRadiationProcessingDataByRegistrationNo(orderDTO.registrationNo());
        if (preRegistered != null) {
            throw new IllegalArgumentException("OrderRadiationProcessingData with given registration number already registered");
        }
        final OrderRadiationProcessingData data = orderRPRepository.save(OrderRadiationProcessingData.fromDTO(orderDTO, orderRepository));
        return ResponseEntity.created(URI.create(request.getRequestURI()).resolve("../fetch/" + orderDTO.registrationNo())).body(OrderRadiationProcessingData.toDTO(data));
    }

    @GetMapping("/fetch/{registrationId}")
    @ResponseBody
    public OrderRadiationProcessingDTO fetchORP(@PathVariable final Integer registrationId) {
        final OrderRadiationProcessingData preRegistered = orderRPRepository.findOrderRadiationProcessingDataByRegistrationNo(registrationId);
        final OrderRadiationProcessingDTO dto = OrderRadiationProcessingData.toDTO(preRegistered);
        return Objects.requireNonNull(dto, "fetched Order must exist!");
    }
}
