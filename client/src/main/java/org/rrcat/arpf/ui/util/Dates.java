package org.rrcat.arpf.ui.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class Dates {
    private Dates() {
    }

    public static Date localToEpoch(final LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
