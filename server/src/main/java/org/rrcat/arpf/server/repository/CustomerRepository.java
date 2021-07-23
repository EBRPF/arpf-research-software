package org.rrcat.arpf.server.repository;

import org.rrcat.arpf.server.entity.Customer;
import org.rrcat.arpf.server.entity.UploadedImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByRegistrationNo(final Integer registrationNo);
    @Query("FROM Customer WHERE concat(registrationNo, '') LIKE :registrationNo%")
    Collection<Customer> findCustomersByRegistrationNoFuzzy(final String registrationNo);
}
