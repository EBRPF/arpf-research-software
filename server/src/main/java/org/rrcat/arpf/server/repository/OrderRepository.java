package org.rrcat.arpf.server.repository;

import org.rrcat.arpf.server.entity.Customer;
import org.rrcat.arpf.server.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderByRegistrationNo(final Integer registrationNo);
    @Query("FROM Order WHERE concat(registrationNo, '') LIKE :registrationNo%")
    Collection<Order> findOrdersByRegistrationNoFuzzy(final String registrationNo);
}
