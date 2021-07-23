package org.dae.arpf.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record CustomerDTO(
        Integer registrationNo,
        OrganizationDTO organization,
        ContactInfoDTO researchHeadInfo,
        AddressDTO address,
        ContactInfoDTO researchOfficerInfo,
        String extraInfo,
        Integer imageKey
) {}

