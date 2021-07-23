package org.dae.arpf.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public final record OrganizationDTO(
        String name,
        String type
) {}