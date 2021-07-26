package org.rrcat.arpf.server.repository;

import org.rrcat.arpf.server.entity.DosimetryInfo;
import org.rrcat.arpf.server.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface DosimetryRepository extends JpaRepository<DosimetryInfo, Long> {
    DosimetryInfo findDosimetryInfoByRegistrationNo(final Integer registrationNo);
    @Query("FROM DosimetryInfo WHERE concat(registrationNo, '') LIKE :registrationNo%")
    Collection<DosimetryInfo> findDosimetryInfoByRegistrationNoFuzzy(final String registrationNo);
}
