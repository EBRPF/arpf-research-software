package org.rrcat.arpf.ui.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.dae.arpf.dto.*;
import org.rrcat.arpf.ui.Helper;
import org.rrcat.arpf.ui.api.RetrofitFetch;
import org.rrcat.arpf.ui.api.schema.CustomerApi;
import org.rrcat.arpf.ui.api.schema.UploadApi;
import org.rrcat.arpf.ui.constants.CustomerFormData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegController implements Initializable {
    @FXML
    private TextField customerRegNo;
    @FXML
    private TextField orgNameField;
    @FXML
    private ComboBox<String> instituteType;
    @FXML
    private TextField researchHeadName;
    @FXML
    private TextField researchMobileNo;
    @FXML
    private TextField researchEmail;
    @FXML
    private TextField officeAddress;
    @FXML
    private TextField researchCity;
    @FXML
    private ComboBox<String> researchState;
    @FXML
    private TextField researchPinCode;
    @FXML
    private TextField phoneNoField;
    @FXML
    private TextField emailField;
    @FXML
    private  TextField scientistName;
    @FXML
    private TextField scientistMobNo;
    @FXML
    private TextField anyOtherInfo;
    @FXML
    private ImageView registrationScannedImg;
    @FXML
    private Button uploadRegScanned;
    @FXML
    private Button saveRecordCustomer;

    private final CustomerDTOBuilder customerViewModel = CustomerDTOBuilder.builder();

    private final Retrofit authenticatedRetrofit;
    private final UploadApi uploadApi;
    private final CustomerApi customerApi;

    public CustomerRegController(final Retrofit authenticatedRetrofit, final UploadApi uploadApi, final CustomerApi customerApi) {
        this.authenticatedRetrofit = authenticatedRetrofit;
        this.uploadApi = uploadApi;
        this.customerApi = customerApi;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        //Customer Registration Number
        customerRegNo.setText("EBRPF-Research-");
        //Institute Type Combo Box
        instituteType.setEditable(true);
        instituteType.setItems(CustomerFormData.INSTITUTE_TYPES);
        researchState.setEditable(true);
        researchState.setItems(CustomerFormData.STATES);

        //Save Record
        saveRecordCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Customer Registration Number
                System.out.println(customerRegNo.getText());
                customerReg.setCustomerRegistrationNo(customerRegNo.getText().trim());

                //Name Of Organisation "fxid : OrgNameField"
                customerReg.setNameOfOrganisation(orgNameField.getText().trim());

                //Name Of Organisation "fxid : OrgNameField"
                customerReg.setNameOfOrganisation(orgNameField.getText().trim());



                //Research Activity Head Name "fxid: ResearchHeadName"
                customerReg.setResearchHeadName(researchHeadName.getText().trim());

                //Research Activity Head Mobile Number "fxid: ResearchMobileNo"
                //if (Integer.parseInt(ResearchMobileNo.getText().trim())==10)
                //  customerReg.setResearchMobileNo(Integer.parseInt(ResearchMobileNo.getText().trim()));

                //Research Activity Head Email ID "fxid: ResearchEmail"

                if (validate(researchEmail.getText().trim())==true)
                    customerReg.setResearchEmailId(researchEmail.getText().trim());
                else
                    System.out.println("Please enter valid Email ID");

                //Office Address "fxid: OfficeAddress"
                customerReg.setOfficeAddress(officeAddress.getText().trim());

                //City "fxid: ResearchCity"
                customerReg.setResearchCity(researchCity.getText().trim());

                //Pin Code "fxid: ResearchPinCode"
                customerReg.setPinCode(researchPinCode.getText().trim());

                //Phone Number "fxid: PhoneNoField"
                customerReg.setOrgMobileNumber(phoneNoField.getText().trim());

                //Email "fxid: EmailField"
                if (validate(researchEmail.getText().trim())==true)
                    customerReg.setOrgEmail(emailField.getText().trim());
                else
                    System.out.println("Please enter valid Email ID");

                //Scientist Name "fxid: ScientistName"
                customerReg.setScientistName(scientistName.getText().trim());

                //Scientist Mobile No "fxid: ScientistMobNo"
                //limit to 10 digits
                customerReg.setScientistName(scientistMobNo.getText().trim());

                //Any Other Information "fxid: AnyOtherInfo"
                customerReg.setAnyOtherInfo(anyOtherInfo.getText().trim());

                //Registration Filled Scanned Form Copy "fxid:RegistrationScannedImg"
                System.out.println(customerRegNo.getText());
                OrganizationDTO organizationDTO =new OrganizationDTO(customerReg.getNameOfOrganisation(), instituteType.getValue());
                ContactInfoDTO contactInfoDTO =new ContactInfoDTO(customerReg.getScientistName(), customerReg.getOrgMobileNumber(), customerReg.getResearchEmailId());
                AddressDTO addressDTO =new AddressDTO(customerReg.getOfficeAddress(), customerReg.getResearchCity(), researchState.getValue().trim(), customerReg.getPinCode(), customerReg.getOrgMobileNumber());
               CustomerDTO customerDTO =new CustomerDTO(12,organizationDTO,contactInfoDTO,addressDTO,contactInfoDTO, customerReg.getAnyOtherInfo(), 121);
                sendNetworkRequest(customerDTO);
            }
        });

    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    private void sendNetworkRequest(CustomerDTO customerDTO) {
        Retrofit retrofit= new RetrofitFetch().fetch();

        ApiInterface client =retrofit.create(ApiInterface.class);
        Call<CustomerDTO> call= client.RegisterUser(Helper.TOKEN,customerDTO);
        call.enqueue(new Callback<CustomerDTO>() {

            @Override
            public void onResponse(Call<CustomerDTO> call, Response<CustomerDTO> response) {
                System.out.println(response);
            }

            @Override
            public void onFailure(Call<CustomerDTO> call, Throwable t) {

            }
        });

    }

    @FXML
    private void onClickUpload() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Image");
        fileChooser.showOpenDialog(uploadRegScanned.getScene().getWindow());
    }

}
