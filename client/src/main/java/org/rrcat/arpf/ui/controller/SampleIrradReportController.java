package org.rrcat.arpf.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SampleIrradReportController implements Initializable {
    @FXML
    private ComboBox<String> orderNumber;
    @FXML
    private Button print;
    @FXML
    private Label orgNameField;
    @FXML
    private Label dateOfOrder;
    @FXML
    private Label descrOfProducts;
    @FXML
    private Label detailOfProduct;
    @FXML
    private Label purposeOfIrrad;
    @FXML
    private Label modeOfIrrad;
    @FXML
    private Label requiredDose;

    //Machine Parameters During Irradiation
    @FXML
    private Label radProcessDate;
    @FXML
    private Label beamEnergy;
    @FXML
    private Label beamCurrent;
    @FXML
    private Label pRR;
    @FXML
    private Label scanWidth;
    @FXML
    private Label scanCurrentTime;
    @FXML
    private Label conveyorSpeed;
    @FXML
    private Label doseRate;
    @FXML
    private Label stoSDistance;
    @FXML
    private Label otherMacParameters;
    @FXML
    private Label operatorRemarks;

    //Dosimetric Measurement Results
    @FXML
    private Label dosimetryDate;
    @FXML
    private Label dosimeterUsed;
    @FXML
    private Label dosimeterLocation;
    @FXML
    private Label dosimetryResult;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
