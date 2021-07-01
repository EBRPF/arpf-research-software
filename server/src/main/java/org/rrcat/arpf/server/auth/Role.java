package org.rrcat.arpf.server.auth;

import java.util.Arrays;
import java.util.Collection;

import static org.rrcat.arpf.server.auth.Permission.*;

public enum  Role {
    FACILITY_IN_CHARGE(Permission.values()),
    RP_IN_CHARGE(ORDER_RADIATION_PROCESSING, CHECK_ORDER_STATUS, SAMPLE_IRRADIATION_REPORT),
    ESM_IN_CHARGE(/* todo: Not yet provided */),
    QC_IN_CHARGE(ORDER_DOSIMETRY, SHIPPING_DETAILS, SAMPLE_IRRADIATION_REPORT),
    OPERATOR(ORDER_RADIATION_PROCESSING),
    ARPF_OFFICE(REGISTER_CUSTOMER, REGISTER_ORDER, SHIPPING_DETAILS);

    private final Collection<Permission> permissions;

    Role(final Permission... permissions) {
        this.permissions = Arrays.asList(permissions);
    }

    public Collection<Permission> getPermissions() {
        return permissions;
    }
}
