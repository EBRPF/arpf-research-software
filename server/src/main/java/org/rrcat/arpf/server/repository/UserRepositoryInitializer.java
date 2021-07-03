package org.rrcat.arpf.server.repository;

import org.rrcat.arpf.server.auth.Role;
import org.rrcat.arpf.server.entity.RrcatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserRepositoryInitializer {
    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void postConstruct() {
        final PasswordEncoder encoder = new BCryptPasswordEncoder();
        final RrcatUser adminUser = new RrcatUser();
        adminUser.setUid("Administrator");
        adminUser.setHashedPassword(encoder.encode("Lo5PofeWw8@r"));
        adminUser.setRole(Role.FACILITY_IN_CHARGE);
        adminUser.setEnabled(true);
        userRepository.save(adminUser);
    }
}
