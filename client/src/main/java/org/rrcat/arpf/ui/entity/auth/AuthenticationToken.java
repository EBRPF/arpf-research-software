package org.rrcat.arpf.ui.entity.auth;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;


public class AuthenticationToken {

    @SerializedName("token")
    private final String token;
    @SerializedName("secondsToExpiration")
    private final long expirationSeconds;

    public AuthenticationToken(String token, long expirationSeconds) {
        this.token = token;
        this.expirationSeconds = expirationSeconds;
    }
    public String getToken() {
        return token;
    }
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
