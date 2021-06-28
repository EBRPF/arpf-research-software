package org.rrcat.arpf.server.repository;

import org.rrcat.arpf.server.auth.RRCATUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<RRCATUser, Long> {
    RRCATUser findRRCATUserByUid(final String uid);
}