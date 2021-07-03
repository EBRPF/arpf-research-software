package org.rrcat.arpf.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public final class LoginRequest {
    @NotBlank(message = "Uid must not be empty")
    @Size(min = 8, max = 32, message = "Username must be between 8 - 32 characters")
    private final String uid;

    @NotBlank(message = "Password must not be empty")
    @Size(max = 64, message = "Hashed password must be at max 64 characters")
    private final String password;

    public LoginRequest(String uid, String password) {
        this.uid = uid;
        this.password = password;
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginRequest that = (LoginRequest) o;
        return getUid().equals(that.getUid()) &&
                getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getPassword());
    }
}
