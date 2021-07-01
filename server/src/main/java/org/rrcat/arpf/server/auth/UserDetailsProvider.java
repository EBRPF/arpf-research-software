package org.rrcat.arpf.server.auth;

import org.rrcat.arpf.server.entity.RrcatUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsProvider {
    UserDetails forUser(final RrcatUser user);
}
