package org.dae.arpf.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.Date;

@RecordBuilder
public record DosimetryDTO(
        Integer registrationNo,
        Date measurementDate,
        String measurement,
        Integer beforeImageKey,
        Integer afterImageKey
) {
}
