package org.rrcat.arpf.ui.constants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoleData {
    private RoleData() {

    }

    public static final ObservableList<String> ROLES = FXCollections.observableArrayList(
            "Facility In-Charge",
            "Radiation Processing In-Charge",
            "ESM In-Charge",
            "Quality Control In-Charge",
            "ARPF Office",
            "Operator"
    );
}
