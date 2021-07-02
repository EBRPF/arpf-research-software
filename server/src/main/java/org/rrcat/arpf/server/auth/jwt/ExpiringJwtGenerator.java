package org.rrcat.arpf.server.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.rrcat.arpf.server.entity.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

@Component
public final class ExpiringJwtGenerator implements JwtGenerator {

    private final SimpleJwtKeyProvider keyProvider;

    @Autowired
    public ExpiringJwtGenerator(final SimpleJwtKeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    public AuthenticationToken generateToken(final String uid, final long expirationSeconds) {
        final Instant expirationInstant = Instant.now().plus(expirationSeconds, ChronoUnit.SECONDS);
        final Date expirationDate = Date.from(expirationInstant);
        final String token = Jwts.builder()
                .setSubject(uid)
                .setExpiration(expirationDate)
                .signWith(keyProvider.getKey(), SignatureAlgorithm.HS512)
                .compact();
        return new AuthenticationToken(token, expirationSeconds);
    }

}
