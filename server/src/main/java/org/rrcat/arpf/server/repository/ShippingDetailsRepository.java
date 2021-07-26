package org.rrcat.arpf.server.repository;

import org.rrcat.arpf.server.entity.OrderRadiationProcessingData;
import org.rrcat.arpf.server.entity.ShippingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ShippingDetailsRepository extends JpaRepository<ShippingDetails, Long> {
    ShippingDetails findShippingDetailsByRegistrationNo(final Integer registrationNo);
    @Query("FROM ShippingDetails WHERE concat(registrationNo, '') LIKE :registrationNo%")
    Collection<ShippingDetails> findShippingDetailsByRegistrationNoFuzzy(final String registrationNo);
}
