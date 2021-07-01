package org.rrcat.arpf.server.auth.jwt;

import java.util.Optional;

public interface JwtValidator {
    boolean validate(final String token);
    Optional<String> getUidIfValid(final String token);
}
