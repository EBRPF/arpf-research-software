package org.rrcat.arpf.server.service;

import org.rrcat.arpf.server.auth.RRCATUser;
import org.rrcat.arpf.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RRCATUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public RRCATUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        final RRCATUser user = userRepository.findRRCATUserByUid(uid);
        if (user == null) {
            throw new UsernameNotFoundException("User not registered!");
        }
        return User.builder()
                .username(user.getUid())
                .password(user.getHashedPassword())
                .authorities(
                        user
                        .getRole()
                        .getPermissions()
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableList())
                )
                .build();
    }
}