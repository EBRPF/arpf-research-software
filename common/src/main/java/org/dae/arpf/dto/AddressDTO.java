package org.dae.arpf.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.soabase.recordbuilder.core.RecordBuilder;


@RecordBuilder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
public final record AddressDTO(
        String addressText,
        String city,
        String state,
        String pinCode,
        String phone
) {}