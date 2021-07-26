package org.rrcat.arpf.server.entity.auth;


import org.dae.arpf.dto.UserDTO;
import org.rrcat.arpf.server.auth.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "users")
public final class RrcatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_pk")
    private Integer id;

    @Column(name = "uid", unique = true)
    @NotBlank(message = "Uid is mandatory")
    @Size(min = 8, max = 32, message = "Username must be between 8 - 32 characters")
    private String uid;

    @Column(name = "hashed_password")
    @NotBlank(message = "Password is mandatory")
    @Size(max = 64, message = "Hashed password must be at max 64 characters")
    private String hashedPassword;

    @Column(name = "role")
    private Role role;

    @Column(name = "enabled")
    private boolean enabled;

    public String getUid() {
        return uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(final String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RrcatUser rrcatUser = (RrcatUser) o;
        return Objects.equals(uid, rrcatUser.uid) &&
                Objects.equals(hashedPassword, rrcatUser.hashedPassword) &&
                role == rrcatUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, hashedPassword, role);
    }

    public static RrcatUser fromDTO(final UserDTO userDTO, final PasswordEncoder encoder) {
        final RrcatUser user = new RrcatUser();
        user.setUid(userDTO.uid());
        user.setHashedPassword(encoder.encode(userDTO.password()));
        user.setRole(Role.valueOf(userDTO.role().toUpperCase(Locale.ROOT)));
        user.setEnabled(userDTO.enabled());
        return user;
    }
}
