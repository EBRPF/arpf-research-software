package org.rrcat.arpf.server.auth;

import org.rrcat.arpf.server.entity.auth.RrcatUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsProvider {
    UserDetails forUser(final RrcatUser user);
}
