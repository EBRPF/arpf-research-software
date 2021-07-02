package org.rrcat.arpf.server.controller;

import org.rrcat.arpf.server.auth.jwt.JwtGenerator;
import org.rrcat.arpf.server.entity.AuthenticationToken;
import org.rrcat.arpf.server.entity.LoginRequest;
import org.rrcat.arpf.server.entity.RrcatUser;
import org.rrcat.arpf.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/api/v1")
public final class AuthenticationController {
    private final long authExpirationTime;
    private final JwtGenerator generator;
    private final UserRepository repository;


    @Autowired
    public AuthenticationController(@Value("${auth.expiration_seconds}") final long authExpirationTime, final JwtGenerator generator, UserRepository repository) {
        this.authExpirationTime = authExpirationTime;
        this.generator = generator;
        this.repository = repository;
    }

    @RequestMapping("/authenticate")
    public @ResponseBody AuthenticationToken authenticate(@RequestBody final LoginRequest loginRequest) {
        final String uid = loginRequest.getUid();
        final String requestedPassword = loginRequest.getHashedPassword();
        final RrcatUser user = repository.findRRCATUserByUid(uid);
        final String expectedPassword = user.getHashedPassword();
        if (!Objects.equals(expectedPassword, requestedPassword)) {
            throw new BadCredentialsException("Invalid password provided.");
        }
        return generator.generateToken(loginRequest.getUid(), authExpirationTime);
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseStatus(
            value = HttpStatus.FORBIDDEN,
            reason = "Failed to authenticate."
    )
    public void handleUserNotFound() {
    }
}
