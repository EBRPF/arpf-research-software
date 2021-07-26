package org.rrcat.arpf.server.controller;

import org.dae.arpf.dto.DosimetryDTO;
import org.dae.arpf.dto.OrderDTO;
import org.rrcat.arpf.server.entity.DosimetryInfo;
import org.rrcat.arpf.server.entity.Order;
import org.rrcat.arpf.server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Objects;

@Controller
@RequestMapping("/api/v1/dosimetry")
public final class DosimetryController {
    private final DosimetryRepository dosimetryRepository;
    private final OrderRPRepository orderRPRepository;
    private final UploadedImageRepository imageRepository;

    @Autowired
    public DosimetryController(DosimetryRepository dosimetryRepository, OrderRPRepository orderRPRepository, final UploadedImageRepository imageRepository) {
        this.dosimetryRepository = dosimetryRepository;
        this.orderRPRepository = orderRPRepository;
        this.imageRepository = imageRepository;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Void> registerDosimetry(@RequestBody final DosimetryDTO dto, final HttpServletRequest request) {
        final DosimetryInfo preRegistered = dosimetryRepository.findDosimetryInfoByRegistrationNo(dto.registrationNo());
        if (preRegistered != null) {
            throw new IllegalArgumentException("DosimetryInfo with given registration number already registered");
        }
        final DosimetryInfo customer = dosimetryRepository.save(DosimetryInfo.fromDTO(dto, orderRPRepository, imageRepository));
        return ResponseEntity.created(URI.create(request.getRequestURI()).resolve("../fetch/" + dto.registrationNo())).build();
    }

    @GetMapping("/fetch/{registrationId}")
    @ResponseBody
    public DosimetryDTO fetchDosimetry(@PathVariable final Integer registrationId) {
        final DosimetryInfo preRegistered = dosimetryRepository.findDosimetryInfoByRegistrationNo(registrationId);
        final DosimetryDTO dto = DosimetryInfo.toDTO(preRegistered);
        return Objects.requireNonNull(dto, "fetched Order must exist!");
    }
}
