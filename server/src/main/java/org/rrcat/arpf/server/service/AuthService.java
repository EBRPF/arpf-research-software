package org.rrcat.arpf.server.service;

import org.rrcat.arpf.server.entity.auth.RrcatUser;

public interface AuthService {
    RrcatUser getUserByUid(final String uid);
    void register(final RrcatUser user);
}
