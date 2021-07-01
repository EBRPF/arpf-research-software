package org.rrcat.arpf.server.service;

import org.rrcat.arpf.server.auth.RRCATUser;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;

@Service
@Transactional
public class RRCATAuthService implements AuthService {

    @Override
    public RRCATUser getUserByUid(String uid) {
        return null;
    }

    @Override
    public void register(RRCATUser user) throws ValidationException {

    }
}
