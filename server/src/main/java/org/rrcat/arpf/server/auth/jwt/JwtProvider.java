package org.rrcat.arpf.server.auth.jwt;

import javax.crypto.SecretKey;

public interface JwtProvider {
    SecretKey getKey();
}
