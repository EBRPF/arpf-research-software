package org.dae.arpf.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.Objects;

@RecordBuilder
public final record AddressDTO(
        String addressText,
        String city,
        String state,
        String pinCode,
        String phone
) {}