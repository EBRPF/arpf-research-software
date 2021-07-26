package org.rrcat.arpf.ui.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
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
        private TextField OrgNameField;
        @FXML
        private TextField DescrOfProducts;
        @FXML
        private TextField MaterialOfProduct;
        @FXML
        private TextField DetailOfProduct;
        @FXML
        private ComboBox<String> PurposeOfIrrad;
        @FXML
        private ComboBox<String> ModeOfIrrad;
        @FXML
        private TextField RequiredDose;
        @FXML
        private TextField DosimeterUsed;
        @FXML
        private TextField DosimeterLocation;
        @FXML
        private DatePicker DateOfOrder;

        //Radiation Processing Data
        @FXML
        private DatePicker RadProcessDate;
        @FXML
        private TextField RadStartTime;
        @FXML
        private TextField CompletionTime;
        @FXML
        private TextField BeamEnergy;
        @FXML
        private TextField BeamCurrent;
        @FXML
        private TextField PRR;
        @FXML
        private TextField ScanWidth;
        @FXML
        private TextField ScanCurrentTime;
        @FXML
        private TextField ConveyorSpeed;
        @FXML
        private TextField DoseRate;
        @FXML
        private TextField StoSDistance;
        @FXML
        private TextField OtherMacParameters;
        @FXML
        private TextField OperatorRemarks;

        //Dosimetry Data
        @FXML
        private DatePicker DosimetryDate;
        @FXML
        private TextField DosimetryResult;

        //Before Irradiation Image
        @FXML
        private ImageView BeforeIrradImage;

        //After Irradiation Image
        @FXML
        private ImageView AfterIrradImage;

        //DosimetryDone CheckBox
        @FXML
        private CheckBox DosimetryDoneCB;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

        }
    }
}
