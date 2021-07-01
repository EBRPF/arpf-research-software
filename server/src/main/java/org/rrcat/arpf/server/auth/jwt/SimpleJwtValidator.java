package org.rrcat.arpf.server.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public final class SimpleJwtValidator implements JwtValidator {
    private final SimpleJwtKeyProvider keyProvider;

    @Autowired
    public SimpleJwtValidator(SimpleJwtKeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    @Override
    public boolean validate(String token) {
        return getUidIfValid(token).isPresent();
    }

    public Optional<String> getUidIfValid(final String token) {
        try {
            final var claimsJwt = Jwts.parserBuilder().setSigningKey(keyProvider.getKey()).build().parseClaimsJwt(token);
            final Claims claims = claimsJwt.getBody();
            return Optional.of(claims.getSubject());
        } catch (final Exception exception) {
            return Optional.empty();
        }
    }


}
