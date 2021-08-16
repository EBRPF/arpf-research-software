package org.dae.arpf.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.Date;

@RecordBuilder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
public record OrderRadiationProcessingDTO(
        Integer registrationNo,
        String dosimeterUsed,
        String dosimeterLocation,
        Date processingDate,
        String startTime,
        String endTime,
        String beamEnergy,
        String beamCurrent,
        String PRR,
        Float scanWidth,
        String scanCurrentAndTime,
        String conveyorSpeed,
        String doseRate,
        String sourceToSurfaceDimension,
        String relatedMachineParams,
        String operatorRemarks
) { }
