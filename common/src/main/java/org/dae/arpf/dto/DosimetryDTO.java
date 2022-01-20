package org.dae.arpf.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.Date;

@RecordBuilder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
public record DosimetryDTO(
        Integer registrationNo,
        Date measurementDate,
        String measurement,
        Integer beforeImageKey,
        Integer afterImageKey,
        String dosimetryDoneBy
) {
}
