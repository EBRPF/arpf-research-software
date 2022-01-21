package org.rrcat.arpf.ui.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

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

    public static class OrderDosimetryController implements Initializable {


        //Order Detail
        @FXML
        private AutoCompleteTextField<String> OrderNumber;
        @FXML
        private JFXTextField OrgNameField;
        @FXML
        private JFXTextField DescrOfProducts;
        @FXML
        private JFXTextField MaterialOfProduct;
        @FXML
        private JFXTextField DetailOfProduct;
        @FXML
        private JFXComboBox<String> PurposeOfIrrad;
        @FXML
        private JFXComboBox<String> ModeOfIrrad;
        @FXML
        private JFXTextField RequiredDose;
        @FXML
        private JFXTextField DosimeterUsed;
        @FXML
        private JFXTextField DosimeterLocation;
        @FXML
        private JFXDatePicker DateOfOrder;

        //Radiation Processing Data
        @FXML
        private JFXDatePicker RadProcessDate;
        @FXML
        private JFXTextField RadStartTime;
        @FXML
        private JFXTextField CompletionTime;
        @FXML
        private JFXTextField BeamEnergy;
        @FXML
        private JFXTextField BeamCurrent;
        @FXML
        private JFXTextField PRR;
        @FXML
        private JFXTextField ScanWidth;
        @FXML
        private JFXTextField ScanCurrentTime;
        @FXML
        private JFXTextField ConveyorSpeed;
        @FXML
        private JFXTextField DoseRate;
        @FXML
        private JFXTextField StoSDistance;
        @FXML
        private JFXTextField OtherMacParameters;
        @FXML
        private JFXTextField OperatorRemarks;

        //Dosimetry Data
        @FXML
        private JFXDatePicker DosimetryDate;
        @FXML
        private JFXTextField DosimetryResult;

        //Before Irradiation Image
        @FXML
        private ImageView BeforeIrradImage;

        //After Irradiation Image
        @FXML
        private ImageView AfterIrradImage;

        //DosimetryDone CheckBox
        @FXML
        private JFXCheckBox DosimetryDoneCB;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

        }
    }
}
