package org.rrcat.arpf.ui.constants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class CustomerFormData {

    public static final ObservableList<String> INSTITUTE_TYPES = FXCollections.observableArrayList(
            "Government",
            "Private",
            "PSUs",
            "Semi Private",
            "Research Institute",
            "Research University"
    );

    public static final ObservableList<String> STATES =
            FXCollections.observableArrayList(
                    "Andhra Pradesh",
                    "Andaman and Nicobar Islands",
                    "Arunachal Pradesh",
                    "Assam",
                    "Bihar",
                    "Chhattisgarh",
                    "Chandigarh",
                    "Dadra & Nagar Haveli",
                    "Daman and Diu",
                    "Delhi",
                    "Goa",
                    "Gujarat",
                    "Haryana",
                    "Himachal Pradesh",
                    "Jammu and Kashmir",
                    "Jharkhand",
                    "Karnataka",
                    "Kerala",
                    "Ladakh",
                    "Lakshadweep",
                    "Madhya Pradesh",
                    "Maharashtra",
                    "Manipur",
                    "Meghalaya",
                    "Mizoram",
                    "Nagaland",
                    "Odisha",
                    "Punjab",
                    "Puducherry",
                    "Rajasthan",
                    "Sikkim",
                    "Tamil Nadu",
                    "Telangana",
                    "Tripura",
                    "Uttar Pradesh",
                    "Uttarakhand",
                    "West Bengal"
            );

    private CustomerFormData() {

    }
}
