package org.rrcat.arpf.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public final class AuthenticationToken {
    private final String token;
    private final long expirationSeconds;

    public AuthenticationToken(String token, long expirationSeconds) {
        this.token = token;
        this.expirationSeconds = expirationSeconds;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("secondsToExpiration")
    public long getExpirationSeconds() {
        return expirationSeconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationToken that = (AuthenticationToken) o;
        return getExpirationSeconds() == that.getExpirationSeconds() &&
                getToken().equals(that.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken(), getExpirationSeconds());
    }
}
