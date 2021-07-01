package org.rrcat.arpf.server.service;

import org.rrcat.arpf.server.entity.RrcatUser;

import javax.xml.bind.ValidationException;

public interface AuthService {
    RrcatUser getUserByUid(final String uid);
    void register(final RrcatUser user) throws ValidationException;
}
