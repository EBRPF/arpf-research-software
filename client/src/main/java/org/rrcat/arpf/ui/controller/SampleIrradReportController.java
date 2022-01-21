package org.rrcat.arpf.ui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import org.rrcat.arpf.ui.api.schema.*;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class SampleIrradReportController implements Initializable {
    @FXML
    private JFXComboBox<String> orderNumber;
    @FXML
    private JFXButton print;
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
    private final OrderApi orderApi;
    private final CustomerApi customerApi;
    private final OrderRPApi orderRPApi;
    private final DosimetryApi dosimetryApi;
    private final ShippingDetailsApi shippingDetailsApi;

    @Inject
    public SampleIrradReportController(OrderApi orderApi, CustomerApi customerApi, OrderRPApi orderRPApi, DosimetryApi dosimetryApi, ShippingDetailsApi shippingDetailsApi) {
        this.orderApi = orderApi;
        this.customerApi = customerApi;
        this.orderRPApi = orderRPApi;
        this.dosimetryApi = dosimetryApi;
        this.shippingDetailsApi = shippingDetailsApi;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
