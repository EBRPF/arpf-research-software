package org.rrcat.arpf.server.controller;

import org.dae.arpf.dto.UserDTO;
import org.rrcat.arpf.server.auth.Role;
import org.rrcat.arpf.server.entity.auth.RrcatUser;
import org.rrcat.arpf.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Locale;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserController(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Void> registerUser(@RequestBody final UserDTO userDTO, final HttpServletRequest request) {
        final RrcatUser user  = repository.findRRCATUserByUid(userDTO.uid());
        if (user != null) {
            throw new IllegalArgumentException("User with given uid already registered");
        }
        final RrcatUser registeredUser = repository.save(RrcatUser.fromDTO(userDTO, encoder));
        return ResponseEntity.created(URI.create(request.getRequestURI()).resolve("../fetch/" + userDTO.uid())).build();
    }

    @PostMapping("/update/role")
    @ResponseBody
    public ResponseEntity<Void> setRole(@RequestBody final UserDTO userDTO, final HttpServletRequest request) {
        final RrcatUser user  = repository.findRRCATUserByUid(userDTO.uid());
        if (user == null) {
            throw new IllegalArgumentException("User with given uid not registered");
        }
        user.setRole(Role.valueOf(userDTO.role().toUpperCase(Locale.ROOT)));
        final RrcatUser registeredUser = repository.save(user);
        return ResponseEntity.created(URI.create(request.getRequestURI()).resolve("../fetch/" + userDTO.uid())).build();
    }

    @PostMapping("/update/enabled")
    @ResponseBody
    public ResponseEntity<Void> setEnabled(@RequestBody final UserDTO userDTO, final HttpServletRequest request) {
        final RrcatUser user  = repository.findRRCATUserByUid(userDTO.uid());
        if (user == null) {
            throw new IllegalArgumentException("User with given uid not registered");
        }
        user.setEnabled(userDTO.enabled());
        final RrcatUser registeredUser = repository.save(user);
        return ResponseEntity.created(URI.create(request.getRequestURI()).resolve("../fetch/" + userDTO.uid())).build();
    }

    @PostMapping("/update/password")
    @ResponseBody
    public ResponseEntity<Void> setPassword(@RequestBody final UserDTO userDTO, final HttpServletRequest request) {
        final RrcatUser user  = repository.findRRCATUserByUid(userDTO.uid());
        if (user == null) {
            throw new IllegalArgumentException("User with given uid not registered");
        }
        user.setHashedPassword(encoder.encode(userDTO.password()));
        final RrcatUser registeredUser = repository.save(user);
        return ResponseEntity.created(URI.create(request.getRequestURI()).resolve("../fetch/" + userDTO.uid())).build();
    }
}
