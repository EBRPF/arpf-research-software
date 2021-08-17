package org.rrcat.arpf.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.dae.arpf.dto.*;
import org.rrcat.arpf.ui.api.schema.CustomerApi;
import org.rrcat.arpf.ui.api.schema.UploadApi;
import org.rrcat.arpf.ui.constants.CustomerFormData;
import org.rrcat.arpf.ui.constants.UploadDirectory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegController implements Initializable {
    @FXML
    private TextField customerRegNo;
    @FXML
    private TextField organizationName;
    @FXML
    private ComboBox<String> instituteType;
    @FXML
    private TextField researchHeadName;
    @FXML
    private TextField researchHeadMobileNo;
    @FXML
    private TextField researchHeadEmail;
    @FXML
    private TextField officeAddress;
    @FXML
    private TextField addressCity;
    @FXML
    private ComboBox<String> addressState;
    @FXML
    private TextField addressPinCode;
    @FXML
    private TextField phoneNo;
    @FXML
    private TextField email;
    @FXML
    private TextField researchOfficerName;
    @FXML
    private TextField researchOfficerMobNo;
    @FXML
    private TextField extraInfo;
    @FXML
    private ImageView registrationScannedImg;
    @FXML
    private Button uploadRegScanned;
    @FXML
    private Button saveRecordCustomer;

    private UploadedImageDTO currentUploadedImage;

    private final UploadApi uploadApi;
    private final CustomerApi customerApi;

    @Inject
    public CustomerRegController(final UploadApi uploadApi, final CustomerApi customerApi) {
        this.uploadApi = uploadApi;
        this.customerApi = customerApi;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        customerRegNo.setText("EBRPF-Research-");
        instituteType.setEditable(false);
        instituteType.setItems(CustomerFormData.INSTITUTE_TYPES);
        addressState.setEditable(true);
        addressState.setItems(CustomerFormData.STATES);
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(final String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @FXML
    private void onClickUpload() throws IOException {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Image");
        final File selectedFile = fileChooser.showOpenDialog(uploadRegScanned.getScene().getWindow());
        if (selectedFile == null) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Upload File");
            alert.setHeaderText("Invalid file selected!");
            alert.setContentText("Received null value as selected file. Please try again");
            alert.show();
            return;
        }
        final RequestBody fileBody =  RequestBody.create(MediaType.parse("image/*"), selectedFile);
        final MultipartBody.Part part = MultipartBody.Part.createFormData("file", selectedFile.getName(), fileBody);
        final Call<UploadedImageDTO> uploadCall = uploadApi.upload(UploadDirectory.REGISTRATION, part);
        try {
            final Response<UploadedImageDTO> response = uploadCall.execute();
            final UploadedImageDTO imageDTO = response.body();
            this.currentUploadedImage = imageDTO;
            if (imageDTO == null) {
                final Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Upload File");
                alert.setHeaderText("Failed to upload file");
                alert.setContentText("Upload failed. Kindly try again/contact system administrator");
                alert.show();
            }
        } catch (final Exception exception) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Upload File");
            alert.setHeaderText("Failed to upload file");
            alert.setContentText("Upload failed (" + exception.getMessage() + "). Kindly try again/contact system administrator");
            alert.show();
            exception.printStackTrace();
        }
    }

    @FXML
    private void onClickSubmit() throws IOException{
        if (this.currentUploadedImage == null) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Submit Failed");
            alert.setHeaderText("Image not selected.");
            alert.setContentText("Kindly select an image to be uploaded. Try again after selecting an image.");
            alert.show();
            return;
        }
        final CustomerDTO dto =
                CustomerDTOBuilder.builder()
                .address(
                        AddressDTOBuilder.builder()
                        .addressText(officeAddress.getText())
                        .city(addressCity.getText())
                        .state(addressState.getValue())
                        .phone(phoneNo.getText())
                        .pinCode(addressPinCode.getText())
                        .build()
                )
                .researchOfficerInfo(
                        ContactInfoDTOBuilder.builder()
                        .name(researchOfficerName.getText())
                        .mobileNo(researchHeadMobileNo.getText())
                        .build()
                )
                .email(email.getText())
                .researchHeadInfo(
                        ContactInfoDTOBuilder.builder()
                        .name(researchHeadName.getText())
                        .mobileNo(researchHeadMobileNo.getText())
                        .email(researchHeadEmail.getText())
                        .build()
                )
                .extraInfo(extraInfo.getText())
                .imageKey(currentUploadedImage.id())
                .organization(
                        OrganizationDTOBuilder.builder()
                        .type(instituteType.getValue())
                        .name(organizationName.getText())
                        .build()
                )
                .registrationNo(Integer.parseInt(customerRegNo.getText().replace("EBRPF-Research-", "")))
                .build();
        Response<Void> response = customerApi.register(dto);
        if (response.code() == 201) {
            System.out.println("Registered");
        } else {
            System.out.println("Failed to register");
        }
    }

}
