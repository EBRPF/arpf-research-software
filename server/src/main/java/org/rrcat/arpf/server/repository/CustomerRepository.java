package org.rrcat.arpf.server.repository;

import org.rrcat.arpf.server.entity.Customer;
import org.rrcat.arpf.server.entity.auth.RrcatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByRegistrationNo(final Integer registrationNo);
}
