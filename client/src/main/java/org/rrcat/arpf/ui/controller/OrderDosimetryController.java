package org.rrcat.arpf.ui.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.rrcat.arpf.ui.api.model.OrderDosimetry;

import javax.xml.stream.events.StartDocument;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderDosimetryController implements Initializable {
    @FXML
    private AutoCompleteTextField OrderNumber;
    @FXML
    private TextField OrgNameField;
    @FXML
    private TextField DescrOfProducts;
    @FXML
    private TextField MaterialOfProduct;
    @FXML
    private TextField DetailOfProduct;
    @FXML
    private TextField PurposeOfIrrad;
    @FXML
    private TextField ModeOfIrrad;
    @FXML
    private TextField RequiredDose;
    @FXML
    private TextField DosimeterUsed;
    @FXML
    private TextField DosimeterLocation;
    @FXML
    private DatePicker DateOfOrder;
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
    private DatePicker DosimetryDate;
    @FXML
    private  TextField DosimetryResult;
    @FXML
    private ImageView BeforeIrradImage;
    @FXML
    private Button BeforeImgBtn;
    @FXML
    private ImageView AfterIrradImage;
    @FXML
    private Button AfterImgBtn;
    @FXML
    private CheckBox DosimetryDoneCB;
    @FXML
    private Button SaveRecord_Dosimetry;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OrderNumber.setText("EBRPF-Research-Order-");
        OrderDosimetry orderDosimetry = new OrderDosimetry();

    //Data Retrieval
        //Name of Organisation
        OrgNameField.setText(orderDosimetry.getNameOfOrganisation());
        //Description of Products
        DescrOfProducts.setText(orderDosimetry.getDescrOfProducts());
        //Material of Products
        MaterialOfProduct.setText(orderDosimetry.getMaterialOfProduct());
        //Detail of Products
        DetailOfProduct.setText(orderDosimetry.getDetailOfProduct());
        //Purpose of Irradiation
        PurposeOfIrrad.setText(orderDosimetry.getPurposeOfIrradiation());
        //Mode Of Irradiation
        ModeOfIrrad.setText(orderDosimetry.getModeOfIrradiation());
        //Required Dose
        RequiredDose.setText((orderDosimetry.getRequiredDose()));
        //Dosimeter Used
        DosimeterUsed.setText(orderDosimetry.getDosimeterUsed());
        //Dosimeter Location
        DosimeterLocation.setText(orderDosimetry.getDosimeterLocation());
        //Date of Order
        DateOfOrder.show();

        //Rad Processing Date
        RadProcessDate.show();
        //Rad Start Time
        RadStartTime.setText(orderDosimetry.getStartTime());
        //Completion Time
        CompletionTime.setText(orderDosimetry.getCompletionTime());
        //Beam Energy
        BeamEnergy.setText(orderDosimetry.getBeamEnergy());
        //Beam Current
        BeamCurrent.setText(orderDosimetry.getBeamCurrent());
        //PRR
        PRR.setText(orderDosimetry.getPrr());
        //Scan Width
        ScanWidth.setText(orderDosimetry.getScanWidth());
        //Scan Current Time
        ScanCurrentTime.setText(orderDosimetry.getScanCurrentTime());
        //Conveyor Speed
        ConveyorSpeed.setText(orderDosimetry.getConveyorSpeed());
        //Dose rate
        DoseRate.setText(orderDosimetry.getDoseRate());
        //Source to Surface Distance
        StoSDistance.setText(orderDosimetry.getsTosDistance());
        //Other Machine Related Parameters
        OtherMacParameters.setText(orderDosimetry.getOtherParamaters());
        //Operator Remarks
        OperatorRemarks.setText(orderDosimetry.getOperatorRemarks());

    //Date of Dosimetry Measurement
        DosimetryDate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDate date = DosimetryDate.getValue();
                System.err.println("Selected date: " + date);
            }
        });

        BeforeImgBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        AfterImgBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        SaveRecord_Dosimetry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //DateOfDosimetry
                DosimetryDate.getValue();
                //Dosimetry Results;
                orderDosimetry.setDosimetryResults(DosimetryResult.getText().trim());

            }
        });




    }
}
