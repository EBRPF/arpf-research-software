package org.rrcat.arpf.server.controller;

import org.rrcat.arpf.server.auth.jwt.JwtGenerator;
import org.rrcat.arpf.server.entity.AuthenticationToken;
import org.rrcat.arpf.server.entity.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1")
public final class AuthenticationController {
    private final JwtGenerator generator;
    private final long authExpirationTime;

    @Autowired
    public AuthenticationController(final JwtGenerator generator, @Value("${auth.expiration_seconds}") final long authExpirationTime) {
        this.generator = generator;
        this.authExpirationTime = authExpirationTime;
    }

    @RequestMapping("/authenticate")
    public @ResponseBody AuthenticationToken authenticate(@RequestBody final LoginRequest loginRequest) {
        return generator.generateToken(loginRequest.getUid(), authExpirationTime);
    }
}
