package org.rrcat.arpf.ui.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
    private ComboBox<String> PurposeOfIrrad;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
