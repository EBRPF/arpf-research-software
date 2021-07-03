package org.rrcat.arpf.server.service;

import org.rrcat.arpf.server.auth.UserDetailsProvider;
import org.rrcat.arpf.server.entity.auth.RrcatUser;
import org.rrcat.arpf.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Qualifier("RrcatUDS")
@Service
@Transactional
public class RrcatUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserDetailsProvider detailsProvider;

    @Autowired
    public RrcatUserDetailsService(final UserRepository userRepository, final UserDetailsProvider detailsProvider) {
        this.userRepository = userRepository;
        this.detailsProvider = detailsProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String uid) {
        final RrcatUser user = userRepository.findRRCATUserByUid(uid);
        if (user == null) {
            throw new UsernameNotFoundException("User not registered!");
        }
        return detailsProvider.forUser(user);
    }

    @Override
    public String toString() {
        return "RrcatUserDetailsService{}";
    }
}
