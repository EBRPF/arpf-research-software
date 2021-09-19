package org.rrcat.arpf.ui.constants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class OrderFormData {
    private OrderFormData() {
    }
    public static final ObservableList<String> INSTITUTE_TYPES = FXCollections.observableArrayList(
            "Government",
            "Private",
            "PSUs",
            "Semi Private",
            "Research Institute",
            "Research University"
    );
}