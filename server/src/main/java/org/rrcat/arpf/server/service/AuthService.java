package org.rrcat.arpf.server.service;

import org.rrcat.arpf.server.auth.RRCATUser;

import javax.xml.bind.ValidationException;

public interface AuthService {
    RRCATUser getUserByUid(final String uid);
    void register(final RRCATUser user) throws ValidationException;
}
