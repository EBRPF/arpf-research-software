package org.rrcat.arpf.ui.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ShippingDetailController implements Initializable {
    @FXML
    private AutoCompleteTextField<String> OrderNumber;
    @FXML
    private TextField OrgNameField;
    @FXML
    private TextField DescrOfProducts;
    @FXML
    private TextField TotalSampleBoxes;
    @FXML
    private DatePicker ShippedDate;
    @FXML
    private TextField ShippedName;
    @FXML
    private TextField ShippedAddress;
    @FXML
    private TextField ShipCity;
    @FXML
    private ComboBox<String> ShipState;
    @FXML
    private TextField ShipPostalCode;
    @FXML
    private TextField TotalProdShipped;
    @FXML
    private ImageView ScannedGatePass;
    @FXML
    private ImageView DosimetryReport;
    @FXML
    private CheckBox ShippingDoneCB;






    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
