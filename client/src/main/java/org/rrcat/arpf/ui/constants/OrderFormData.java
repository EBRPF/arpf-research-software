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
    public static final ObservableList<String> IRRADIATION_MODE = FXCollections.observableArrayList(
            "Electron Mode",
            "Photon Mode",
            "X-Ray Mode"
    );
    public static final ObservableList<String> IRRADIATION_PURPOSE = FXCollections.observableArrayList(
            "Mutation Breeding",
            "2",
            "3",
            "4"
    );
}
