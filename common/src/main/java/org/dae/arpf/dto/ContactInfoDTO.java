package org.dae.arpf.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public final record ContactInfoDTO(
        String name,
        String mobileNo,
        String email
) { }