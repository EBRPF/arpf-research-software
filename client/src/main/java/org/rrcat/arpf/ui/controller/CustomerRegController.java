package org.rrcat.arpf.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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

import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegController implements Initializable {
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
    private StackPane imageOuterPane;
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
        instituteType.setEditable(false);
        instituteType.setItems(CustomerFormData.INSTITUTE_TYPES);
        addressState.setEditable(true);
        addressState.setItems(CustomerFormData.STATES);
        registrationScannedImg.setPreserveRatio(true);
        registrationScannedImg.fitWidthProperty().bind(imageOuterPane.widthProperty());
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
                registrationScannedImg.setImage(null);
            } else {
                registrationScannedImg.setImage(new Image(new FileInputStream(selectedFile)));
            }
        } catch (final Exception exception) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Upload File");
            alert.setHeaderText("Failed to upload file");
            alert.setContentText("Upload failed (" + exception.getMessage() + "). Kindly try again/contact system administrator");
            alert.show();
            registrationScannedImg.setImage(null);
            this.currentUploadedImage = null;
            exception.printStackTrace();
        }
    }

    @FXML
    private void onClickSubmit() throws IOException {
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
                        .mobileNo(researchOfficerMobNo.getText())
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
                .build();
        try {
            final Call<CustomerDTO> call = customerApi.register(dto);
            final Response<CustomerDTO> response = call.execute();
            final Alert alert;
            if (response.code() == 201 && response.body() != null) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Registration");
                alert.setHeaderText("Customer has been successfully registered with ID: " + response.body().registrationNo());
                alert.setContentText(null);

            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Customer Registration");
                alert.setHeaderText("Customer registration attempt failed.");
                alert.setContentText("Response: "+response.code() + " Message:" + response.message() + " Body:" + response.body());
            }
            alert.show();
        } catch (final Exception exception) {
            exception.printStackTrace();
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Customer Registration");
            alert.setHeaderText("Customer registration attempt failed.");
            alert.setContentText("Exception: " + exception.getClass().getName() + " " + exception.getMessage());
            alert.show();
        }
    }

}
