package org.rrcat.arpf.ui.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.rrcat.arpf.ui.api.model.OrderReg;

import java.net.URL;
import java.util.ResourceBundle;

public class NewOrderController implements Initializable {
    @FXML
    private AutoCompleteTextField<String> customerRegNo;
    @FXML
    private TextField OrderNumber;
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
    private ComboBox<String> ModeOfIrrad;
    @FXML
    private TextField RequiredDose;
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
    private TextField OrderConfirmCB;
    @FXML
    private Button SaveRecord_Order;
    @FXML
    private Button UploadForm_Order;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OrderNumber.setText("EBRPF-Research-Order-");
        OrderReg orderReg = new OrderReg();

        ObservableList<String> modeOptions =
                FXCollections.observableArrayList(
                        "Electron Mode",
                        "X-Ray Mode",
                        "Both"
                );
        ModeOfIrrad.setEditable(true);
        ModeOfIrrad.setItems(modeOptions);
        ModeOfIrrad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(ModeOfIrrad.getValue());
            }
        });

        UploadForm_Order.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Radiation Processing Request Form
            }
        });

        SaveRecord_Order.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Customer Registration Number
                orderReg.setCustomerRegistrationNo(customerRegNo.getText().trim());
                //Order Number Registration
                orderReg.setOrderNumber(OrderNumber.getText().trim());
                //Name of Organisation
                OrgNameField.setText(orderReg.getCustomerRegistrationNo());
                //Description of Products
                orderReg.setDescrOfProducts(DescrOfProducts.getText().trim());
                //Material of Products
                orderReg.setMaterialOfProduct(MaterialOfProduct.getText().trim());
                //Purpose of Irradiation
                orderReg.setPurposeOfIrradiation(PurposeOfIrrad.getText().trim());
                //Required Dose
                orderReg.setRequiredDose(RequiredDose.getText().trim());
                //Dimension of Individual Product
                orderReg.setDimensionoOfProduct(DimenOfProduct.getText().trim());
                //Weight of Individual Product
                orderReg.setWeightOfProduct(WeightOfProduct.getText().trim());
                //Total Number of Boxes
                orderReg.setTotalBoxes(TotalSampleBoxes.getText().trim());
                //Any Other Information
                orderReg.setAnyOtherInfo_Order(AnyOrderInfo.getText().trim());
                //Date Picker
                //Facility I/C Comments --->
                orderReg.setFicComments(FIComments.getText().trim());
                //CheckBox
            }
        });


    }
}
