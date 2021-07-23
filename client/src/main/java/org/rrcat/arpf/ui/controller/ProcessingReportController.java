package org.rrcat.arpf.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProcessingReportController implements Initializable {

    //OrderDetails
    @FXML
    private Label OrderNumber;
    @FXML
    private Label OrgNameField;
    @FXML
    private Label DateOfOrder;
    @FXML
    private Label DescrOfProducts;
    @FXML
    private Label DetailOfProduct;
    @FXML
    private Label PurposeOfIrrad;
    @FXML
    private Label ModeOfIrrad;
    @FXML
    private Label RequiredDose;

    //Machine Parameters During Irradiation
    @FXML
    private Label RadProcessDate;
    @FXML
    private Label BeamEnergy;
    @FXML
    private Label BeamCurrent;
    @FXML
    private Label PRR;
    @FXML
    private Label ScanWidth;
    @FXML
    private Label ScanCurrentTime;
    @FXML
    private Label ConveyorSpeed;
    @FXML
    private Label DoseRate;
    @FXML
    private Label StoSDistance;
    @FXML
    private Label OtherMacParameters;
    @FXML
    private Label OperatorRemarks;

    //Dosimetric Measurement Results
    @FXML
    private Label DosimetryDate;
    @FXML
    private Label DosimeterUsed;
    @FXML
    private Label DosimeterLocation;
    @FXML
    private Label DosimetryResult;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
