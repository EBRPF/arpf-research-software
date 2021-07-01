package org.rrcat.arpf.server.auth.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class SimpleJwtKeyProvider implements JwtProvider {
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public SecretKey getKey() {
        return key;
    }
}
