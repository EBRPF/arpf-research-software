package org.dae.arpf.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
public record CustomerDTO(
        Integer registrationNo,
        OrganizationDTO organization,
        ContactInfoDTO researchHeadInfo,
        AddressDTO address,
        String email,
        ContactInfoDTO researchOfficerInfo,
        String extraInfo,
        Integer imageKey
) {}

