package org.dae.arpf.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.Date;

@RecordBuilder
public record ShippingDetailsDTO(
        Integer registrationNo,
        Date shippingDate,
        String shippingMedium,
        AddressDTO shippingAddress,
        Integer shippedPackets,
        Integer gatePassImageKey,
        Integer dosimetryReportImageKey
) {
}
