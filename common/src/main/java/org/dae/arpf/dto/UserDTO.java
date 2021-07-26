package org.dae.arpf.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record UserDTO(
        String uid,
        String password,
        String role,
        Boolean enabled
) {
}
