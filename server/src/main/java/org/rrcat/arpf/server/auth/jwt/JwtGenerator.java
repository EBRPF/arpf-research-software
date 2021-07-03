package org.rrcat.arpf.server.auth.jwt;

import org.rrcat.arpf.server.entity.auth.AuthenticationToken;

public interface JwtGenerator {
    AuthenticationToken generateToken(final String uid, final long expirationSeconds);
}
