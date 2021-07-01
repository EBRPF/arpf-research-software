package org.rrcat.arpf.server.auth;

import org.rrcat.arpf.server.entity.RrcatUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public final class SimpleUserDetailsProvider implements UserDetailsProvider {
    @Override
    public UserDetails forUser(final RrcatUser user) {
        return User.builder()
                .username(user.getUid())
                .password(user.getHashedPassword())
                .disabled(!user.isEnabled())
                .authorities(
                        user.getRole()
                            .getPermissions()
                            .stream()
                            .map(Permission::name)
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toUnmodifiableList())
                )
                .build();
    }
}
