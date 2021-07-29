package org.dae.arpf.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record AuthenticationTokenDTO(
        String token,
        long secondsToExpiration
) {
}
