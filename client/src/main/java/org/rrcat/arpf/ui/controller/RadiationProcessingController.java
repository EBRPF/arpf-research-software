package org.rrcat.arpf.ui.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.rrcat.arpf.ui.api.model.RadiationProcessing;
import org.w3c.dom.Text;

import java.net.URL;
import java.time.LocalDate;
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
    private TextField PurposeOfIrrad;
    @FXML
    private TextField ModeOfIrrad;
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
    @FXML
    private Button SaveRecord_ORP;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OrderNumber.setText("EBRPF-Research-Order-");
        RadiationProcessing radiationProcessing = new RadiationProcessing();

    //Data Retrieval
        //Name of Organisation
        OrgNameField.setText(radiationProcessing.getCustomerRegistrationNo());
        //Description of Products
        DescrOfProducts.setText(radiationProcessing.getDescrOfProducts());
        //Material of Products
        MaterialOfProduct.setText(radiationProcessing.getMaterialOfProduct());
        //Detail of Products
        DetailOfProduct.setText(radiationProcessing.getDetailOfProduct());
        //Purpose of Irradiation
        PurposeOfIrrad.setText(radiationProcessing.getPurposeOfIrradiation());
        //Mode Of Irradiation
        ModeOfIrrad.setText(radiationProcessing.getModeOfIrradiation());
        //Required Dose
        RequireDose.setText((radiationProcessing.getRequiredDose()));
        //Dimension of Individual Product
        DimenOfProduct.setText(radiationProcessing.getDimensionoOfProduct());
        //Weight Of Product
        WeightOfProduct.setText(radiationProcessing.getWeightOfProduct());
        //Total Number Of Product
        TotalSampleBoxes.setText(radiationProcessing.getTotalBoxes());
        //Any Other Information
        AnyOrderInfo.setText(radiationProcessing.getAnyOtherInfo_Order());
        //Date of Order
        DateOfOrder.show();
        //Uploaded Image
        RequestForm.getImage();
        //Facility I/C Comments
        FIComments.setText(radiationProcessing.getFicComments());
        //Order Registered and Processed for Further Action CheckBox

        //Radiation Processing Date
        RadProcessDate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDate date = RadProcessDate.getValue();
                System.err.println("Selected date: " + date);
            }
        });

        //Save Record Button
        SaveRecord_ORP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Dosimeter Used
                radiationProcessing.setDosimeterUsed(DosimeterUsed.getText().trim());
                //Dosimeter Location
                radiationProcessing.setDosimeterLocation(DosimeterLocation.getText().trim());

                //Radiation Processing Start Time
                radiationProcessing.setStartTime(RadStartTime.getText().trim());
                //Completion Time
                radiationProcessing.setCompletionTime(CompletionTime.getText().trim());
                //Beam Energy
                BeamEnergy.setText("   MeV");
                radiationProcessing.setBeamEnergy(BeamEnergy.getText().trim());
                //Beam Current
                BeamCurrent.setText("   mA");
                radiationProcessing.setBeamCurrent(BeamCurrent.getText().trim());
                //PRR
                PRR.setText("   Hz");
                radiationProcessing.setPrr(PRR.getText().trim());
                //Scan Width
                ScanWidth.setText("  A   msec");
                radiationProcessing.setScanWidth(ScanWidth.getText().trim());
                //Conveyor Speed
                radiationProcessing.setConveyorSpeed(ConveyorSpeed.getText().trim());
                //Dose Rate
                radiationProcessing.setDoseRate(DoseRate.getText().trim());
                //Surface to Surface Distance
                radiationProcessing.setsTosDistance(StoSDistance.getText().trim());
                //Other Machine Related Parameters
                radiationProcessing.setOtherParamaters(OtherMacParameters.getText().trim());
                //Operator Remarks
                radiationProcessing.setOperatorRemarks(OperatorRemarks.getText().trim());

            }
        });


    }
}
