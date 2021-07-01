package org.rrcat.arpf.server.service;

import org.rrcat.arpf.server.auth.Permission;
import org.rrcat.arpf.server.auth.UserDetailsProvider;
import org.rrcat.arpf.server.entity.RRCATUser;
import org.rrcat.arpf.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Qualifier("RrcatDetails")
@Service
@Transactional
public class RRCATUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserDetailsProvider detailsProvider;

    @Autowired
    public RRCATUserDetailsService(final UserRepository userRepository, final UserDetailsProvider detailsProvider) {
        this.userRepository = userRepository;
        this.detailsProvider = detailsProvider;
    }

    @Override
    public final UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        final RRCATUser user = userRepository.findRRCATUserByUid(uid);
        if (user == null) {
            throw new UsernameNotFoundException("User not registered!");
        }
        return detailsProvider.forUser(user);
    }
}
