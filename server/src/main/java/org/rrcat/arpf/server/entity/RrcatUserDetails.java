package org.rrcat.arpf.server.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public final class RrcatUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final Collection<GrantedAuthority> authorities;
    private final boolean enabled;

    public RrcatUserDetails(final String username, final String password, final Collection<GrantedAuthority> authorities, final boolean enabled) {
        this.username = username;
        this.password = password;
        this.authorities = Collections.unmodifiableCollection(authorities);
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // -- Ignore

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
