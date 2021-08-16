package org.rrcat.arpf.server.controller;

import org.rrcat.arpf.server.auth.jwt.JwtGenerator;
import org.rrcat.arpf.server.entity.auth.AuthenticationToken;
import org.rrcat.arpf.server.entity.auth.LoginRequest;
import org.rrcat.arpf.server.entity.auth.RrcatUser;
import org.rrcat.arpf.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// https://ip:port/api/v1/authenticate

@Controller
@RequestMapping("/api/v1")
public final class AuthenticationController {
    private final long authExpirationTime;
    private final JwtGenerator generator;
    private final UserRepository repository;
    private final PasswordEncoder encoder;


    @Autowired
    public AuthenticationController(@Value("${auth.expiration_seconds}") final long authExpirationTime, final JwtGenerator generator, final UserRepository repository, final PasswordEncoder encoder) {
        this.authExpirationTime = authExpirationTime;
        this.generator = generator;
        this.repository = repository;
        this.encoder = encoder;
    }

    @PostMapping("/authenticate")
    public @ResponseBody AuthenticationToken authenticate(@RequestBody final LoginRequest loginRequest) {
        final String uid = loginRequest.getUid();
        final String requestedPassword = loginRequest.getPassword();
        final RrcatUser user = repository.findRRCATUserByUid(uid);
        final String expectedPassword = user.getHashedPassword();
        if (!encoder.matches(requestedPassword, expectedPassword)) {
            throw new BadCredentialsException("Invalid password provided.");
        }
        return generator.generateToken(loginRequest.getUid(), authExpirationTime);
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseStatus(
            value = HttpStatus.FORBIDDEN,
            reason = "Failed to authenticate."
    )
    public void handleInvalidLoginAttempt() {
    }
}
