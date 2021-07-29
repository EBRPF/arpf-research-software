package org.dae.arpf.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record LoginRequestDTO(
        String uid,
        String password
) {
}
