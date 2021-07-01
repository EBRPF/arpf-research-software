package org.rrcat.arpf.server.auth.jwt;

public interface JwtGenerator {
    String generateToken(final String uid);
}
