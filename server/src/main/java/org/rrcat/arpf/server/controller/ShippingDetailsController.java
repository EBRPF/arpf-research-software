package org.rrcat.arpf.server.controller;

import org.dae.arpf.dto.OrderRadiationProcessingDTO;
import org.dae.arpf.dto.ShippingDetailsDTO;
import org.rrcat.arpf.server.entity.OrderRadiationProcessingData;
import org.rrcat.arpf.server.entity.ShippingDetails;
import org.rrcat.arpf.server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Objects;

@Controller
@RequestMapping("/api/v1/shipping")
public final class ShippingDetailsController {
    private final ShippingDetailsRepository detailsRepository;
    private final DosimetryRepository dosimetryRepository;
    private final UploadedImageRepository imageRepository;

    @Autowired
    public ShippingDetailsController(ShippingDetailsRepository detailsRepository, DosimetryRepository dosimetryRepository, UploadedImageRepository imageRepository) {
        this.detailsRepository = detailsRepository;
        this.dosimetryRepository = dosimetryRepository;
        this.imageRepository = imageRepository;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Void> registerORP(@RequestBody final ShippingDetailsDTO orderDTO, final HttpServletRequest request) {
        final ShippingDetails preRegistered = detailsRepository.findShippingDetailsByRegistrationNo(orderDTO.registrationNo());
        if (preRegistered != null) {
            throw new IllegalArgumentException("OrderRadiationProcessingData with given registration number already registered");
        }
        final ShippingDetails data = detailsRepository.save(ShippingDetails.fromDTO(orderDTO, dosimetryRepository, imageRepository));
        return ResponseEntity.created(URI.create(request.getRequestURI()).resolve("../fetch/" + orderDTO.registrationNo())).build();
    }

    @GetMapping("/fetch/{registrationId}")
    @ResponseBody
    public ShippingDetailsDTO fetchORP(@PathVariable final Integer registrationId) {
        final ShippingDetails preRegistered = detailsRepository.findShippingDetailsByRegistrationNo(registrationId);
        final ShippingDetailsDTO dto = ShippingDetails.toDTO(preRegistered);
        return Objects.requireNonNull(dto, "fetched ShippingDetails must exist!");
    }
}
