package org.rrcat.arpf.server.repository;

import org.rrcat.arpf.server.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByRegistrationNo(final Integer registrationNo);

    @Query("FROM Customer WHERE concat(registrationNo, '') LIKE :registrationNo%")
    Collection<Customer> findCustomersByRegistrationNoFuzzy(final String registrationNo);

    @Query("FROM Customer WHERE organization.name LIKE :orgName%")
    Collection<Customer> findCustomersByorgNameFuzzy(final String orgName);

    Customer findCustomerByorgName(final String orgName);
}
