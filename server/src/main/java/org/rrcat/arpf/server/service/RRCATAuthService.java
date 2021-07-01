package org.rrcat.arpf.server.service;

import org.rrcat.arpf.server.entity.RRCATUser;
import org.rrcat.arpf.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;

@Service
@Transactional
public class RRCATAuthService implements AuthService {

    private final UserRepository repository;

    @Autowired
    public RRCATAuthService(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public final RRCATUser getUserByUid(String uid) {
        return repository.findRRCATUserByUid(uid);
    }

    @Override
    public final void register(final RRCATUser user) throws ValidationException {

    }
}
