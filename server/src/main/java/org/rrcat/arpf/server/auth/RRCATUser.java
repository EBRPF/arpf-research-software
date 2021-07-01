package org.rrcat.arpf.server.auth;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public final class RRCATUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "uid", unique = true)
    private String uid;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Column(name = "role")
    private Role role;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
