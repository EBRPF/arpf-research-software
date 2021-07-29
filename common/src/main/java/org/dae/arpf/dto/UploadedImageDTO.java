package org.dae.arpf.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record UploadedImageDTO(
        Integer id,
        String tag,
        String name
) {
}
