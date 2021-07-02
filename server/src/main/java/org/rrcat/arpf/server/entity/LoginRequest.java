package org.rrcat.arpf.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public final class LoginRequest {
    private final String uid;
    private final String hashedPassword;

    public LoginRequest(String uid, String hashedPassword) {
        this.uid = uid;
        this.hashedPassword = hashedPassword;
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("password")
    public String getHashedPassword() {
        return hashedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginRequest that = (LoginRequest) o;
        return getUid().equals(that.getUid()) &&
                getHashedPassword().equals(that.getHashedPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getHashedPassword());
    }
}
