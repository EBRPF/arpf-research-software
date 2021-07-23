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

public class RadiationProcessingController  implements Initializable {
    @FXML
    private AutoCompleteTextField<String> OrderNumber;
    @FXML
    private TextField DosimeterUsed;
    @FXML
    private TextField DosimeterLocation;
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
    private TextField RequireDose;
    @FXML
    private TextField DimenOfProduct;
    @FXML
    private TextField WeightOfProduct;
    @FXML
    private TextField TotalSampleBoxes;
    @FXML
    private TextField AnyOrderInfo;
    @FXML
    private DatePicker DateOfOrder;
    @FXML
    private ImageView RequestForm;
    @FXML
    private TextField FIComments;
    @FXML
    private CheckBox OrderConfirmCB;
    @FXML
    private CheckBox IrradiationProcessedCB;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
