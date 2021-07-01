package org.rrcat.arpf.server.auth;

import java.util.Optional;

public interface BearerTokenExtractor {
    Optional<String> extractToken(final String bearer);
}
