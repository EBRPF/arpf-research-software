package org.rrcat.arpf.ui.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerRegController implements Initializable {
    @FXML
    private AutoCompleteTextField customerRegNo;
    @FXML
    private TextField OrgNameField;
    @FXML
    private ComboBox<String> InstituteType;
    @FXML
    private TextField ResearchHeadName;
    @FXML
    private TextField ResearchMobileNo;
    @FXML
    private TextField ResearchEmail;
    @FXML
    private TextField OfficeAddress;
    @FXML
    private TextField ResearchCity;
    @FXML
    private ComboBox<String> ResearchState;
    @FXML
    private TextField ResearchPinCode;
    @FXML
    private TextField PhoneNoField;
    @FXML
    private TextField EmailField;
    @FXML
    private  TextField ScientistName;
    @FXML
    private TextField ScientistMobNo;
    @FXML
    private TextField AnyOtherInfo;
    @FXML
    private ImageView RegistrationScannedImg;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
