package org.rrcat.arpf.server.auth;

import java.util.Arrays;
import java.util.Collection;

public enum  Role {
    CUSTOMER(),
    FACILITY_IN_CHARGE(),
    RP_IN_CHARGE(),
    ESM_IN_CHARGE(),
    QC_IN_CHARGE(),
    OPERATOR();

    private final Collection<String> permissions;

    Role(final String... permissions) {
        this.permissions = Arrays.asList(permissions);
    }

    public Collection<String> getPermissions() {
        return permissions;
    }
}
