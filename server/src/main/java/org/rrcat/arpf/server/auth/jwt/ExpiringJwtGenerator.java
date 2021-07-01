package org.rrcat.arpf.server.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public final class ExpiringJwtGenerator implements JwtGenerator {

    private final SimpleJwtKeyProvider keyProvider;

    @Autowired
    public ExpiringJwtGenerator(final SimpleJwtKeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    public String generateToken(final String uid) {
        final ZonedDateTime expirationDateTime = LocalDate.now()
                .plus(1, ChronoUnit.HOURS)
                .atStartOfDay(ZoneId.systemDefault());
        final Instant expirationInstant = expirationDateTime.toInstant();
        final Date expirationDate = Date.from(expirationInstant);
        return Jwts.builder()
                .setSubject(uid)
                .setExpiration(expirationDate)
                .signWith(keyProvider.getKey(), SignatureAlgorithm.HS512)
                .compact();
    }

}
