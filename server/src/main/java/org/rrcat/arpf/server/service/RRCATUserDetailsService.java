package org.rrcat.arpf.server.service;

import org.rrcat.arpf.server.auth.Permission;
import org.rrcat.arpf.server.entity.RRCATUser;
import org.rrcat.arpf.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class RRCATUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public RRCATUserDetailsService(final UserRepository userRepository) {
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
                .disabled(!user.isEnabled())
                .authorities(
                        user
                        .getRole()
                        .getPermissions()
                        .stream()
                        .map(Permission::name)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableList())
                )
                .build();
    }
}
