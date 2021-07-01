package org.rrcat.arpf.server.repository;

import org.rrcat.arpf.server.entity.RrcatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<RrcatUser, Long> {
    RrcatUser findRRCATUserByUid(final String uid);
}