package org.dae.arpf.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.Date;


@RecordBuilder
public final record OrderDTO (
        Integer registrationNo,
        Integer customerId,
        String productDescription,
        String productMaterial,
        String productDetails,
        String irradiationPurpose,
        String irradiationMode,
        String requiredDose,
        String productDimensions,
        String productWeight,
        Integer productCount,
        String extraInfo,
        Date receiptDate,
        Integer imageKey,
        String comments,
        boolean registered
) {}
