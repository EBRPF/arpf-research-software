package org.rrcat.arpf.server.auth;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
public final class SubstringBearerTokenExtractor implements BearerTokenExtractor {
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public Optional<String> extractToken(final String bearer) {
        if (StringUtils.hasText(bearer) && bearer.startsWith(BEARER_PREFIX)) {
            return Optional.of(bearer.substring(BEARER_PREFIX.length()));
        }
        return Optional.empty();
    }
}
