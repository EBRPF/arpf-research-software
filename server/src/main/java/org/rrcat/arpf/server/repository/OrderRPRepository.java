package org.rrcat.arpf.server.repository;

import org.rrcat.arpf.server.entity.Order;
import org.rrcat.arpf.server.entity.OrderRadiationProcessingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface OrderRPRepository extends JpaRepository<OrderRadiationProcessingData, Long> {
    OrderRadiationProcessingData findOrderByRegistrationNo(final Integer registrationNo);
    @Query("FROM OrderRadiationProcessingData WHERE concat(registrationNo, '') LIKE :registrationNo%")
    Collection<OrderRadiationProcessingData> findOrderByRegistrationNoFuzzy(final String registrationNo);
}
