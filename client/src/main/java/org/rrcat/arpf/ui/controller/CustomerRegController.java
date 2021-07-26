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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import okio.Options;
import org.rrcat.arpf.ui.api.model.CustomerReg;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegController implements Initializable {
    @FXML
    private TextField customerRegNo;
    @FXML
    private TextField OrgNameField;
    @FXML
    private ComboBox<String> InstituteType;
    @FXML
    private TextField ResearchHeadName;
    @FXML
    private TextField ResearchMobileNo;
    @FXML
    private TextField ResearchEmail;
    @FXML
    private TextField OfficeAddress;
    @FXML
    private TextField ResearchCity;
    @FXML
    private ComboBox<String> ResearchState;
    @FXML
    private TextField ResearchPinCode;
    @FXML
    private TextField PhoneNoField;
    @FXML
    private TextField EmailField;
    @FXML
    private  TextField ScientistName;
    @FXML
    private TextField ScientistMobNo;
    @FXML
    private TextField AnyOtherInfo;
    @FXML
    private ImageView RegistrationScannedImg;
    @FXML
    private Button UploadRegScanned;
    @FXML
    private Button SaveRecord_Customer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Customer Registration Number
        customerRegNo.setText("EBRPF-Research-");
        CustomerReg customerReg = new CustomerReg();
        //Institute Type Combo Box
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Option 1",
                        "Option 2",
                        "Option 3"
                );
        InstituteType.setEditable(true);
        InstituteType.setItems(options);
        InstituteType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(InstituteType.getValue());
            }
        });


        //Save Record
        SaveRecord_Customer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(customerRegNo.getText());
                customerReg.setCustomerRegistrationNo(customerRegNo.getText().trim());

                //Name Of Organisation "fxid : OrgNameField"
                customerReg.setNameOfOrganisation(OrgNameField.getText().trim());

                //Name Of Organisation "fxid : OrgNameField"
                customerReg.setNameOfOrganisation(OrgNameField.getText().trim());



                //Research Activity Head Name "fxid: ResearchHeadName"
                customerReg.setResearchHeadName(ResearchHeadName.getText().trim());

                //Research Activity Head Mobile Number "fxid: ResearchMobileNo"

                if (ResearchMobileNo.getText().length()==10)
                    customerReg.setResearchMobileNo(ResearchMobileNo.getText());

                //Research Activity Head Email ID "fxid: ResearchEmail"

                if (validate(ResearchEmail.getText().trim())==true)
                    customerReg.setResearchEmailId(ResearchEmail.getText().trim());
                else
                    System.out.println("Please enter valid Email ID");

                //Office Address "fxid: OfficeAddress"
                customerReg.setOfficeAddress(OfficeAddress.getText().trim());
                System.out.println(customerReg);
                System.out.println(customerRegNo.getText());

            }
        });


    }


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

}
