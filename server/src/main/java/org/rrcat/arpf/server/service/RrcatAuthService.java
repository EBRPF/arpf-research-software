package org.rrcat.arpf.server.service;

import org.rrcat.arpf.server.entity.RrcatUser;
import org.rrcat.arpf.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;

@Service
@Transactional
public class RrcatAuthService implements AuthService {

    private final UserRepository repository;

    @Autowired
    public RrcatAuthService(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public final RrcatUser getUserByUid(String uid) {
        return repository.findRRCATUserByUid(uid);
    }

    @Override
    public final void register(final RrcatUser user) {

    }
}
