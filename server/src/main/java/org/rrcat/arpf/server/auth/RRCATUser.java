package org.rrcat.arpf.server.auth;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Transient;
import java.util.Objects;

public final class RRCATUser {
    private final String uid;
    private String hashedPassword;
    private Role role;

    public RRCATUser(final String uid, final String hashedPassword, final Role role) {
        this.uid = uid;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }

    public String getUid() {
        return uid;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RRCATUser rrcatUser = (RRCATUser) o;
        return Objects.equals(uid, rrcatUser.uid) &&
                Objects.equals(hashedPassword, rrcatUser.hashedPassword) &&
                role == rrcatUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, hashedPassword, role);
    }
}
