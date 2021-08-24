package org.rrcat.arpf.ui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.dae.arpf.dto.*;
import org.rrcat.arpf.ui.api.schema.CustomerApi;
import org.rrcat.arpf.ui.api.schema.UploadApi;
import org.rrcat.arpf.ui.constants.CustomerFormData;
import org.rrcat.arpf.ui.di.annotations.AlertingExceptionConsumer;
import org.rrcat.arpf.ui.di.annotations.ImageFileSupplier;
import org.rrcat.arpf.ui.service.ImageUploadService;
import retrofit2.Call;
import retrofit2.Response;

import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CustomerRegController implements Initializable {
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

    private final AtomicReference<UploadedImageDTO> currentUploadedImageReference = new AtomicReference<>();

    private final CustomerApi customerApi;
    private final ImageUploadService uploadService;
    private final Supplier<File> uploadFileSupplier;
    private final Consumer<Throwable> exceptionHandler;

    @Inject
    public CustomerRegController(final CustomerApi customerApi, final ImageUploadService uploadService, final @ImageFileSupplier Supplier<File> uploadFileSupplier, final @AlertingExceptionConsumer Consumer<Throwable> exceptionHandler) {
        this.customerApi = customerApi;
        this.uploadService = uploadService;
        this.uploadFileSupplier = uploadFileSupplier;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        instituteType.setItems(CustomerFormData.INSTITUTE_TYPES);
        addressState.setItems(CustomerFormData.STATES);
        registrationScannedImg.setPreserveRatio(true);
        registrationScannedImg.fitWidthProperty().bind(imageOuterPane.widthProperty());
        //organizationName.widthProperty().addListener((observable, oldValue, newValue) -> System.out.println("width = " + newValue));
        organizationName.heightProperty().addListener((observable, oldValue, newValue) -> System.out.println("height = " + newValue));

    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    @FXML
    private void onClickUpload() throws IOException {
        CompletableFuture.completedFuture(uploadFileSupplier.get())
                .thenApply((file) -> {
                    final UploadedImageDTO dto = uploadService.upload(file);
                    onUploadFileSuccessfully(file);
                    return dto;
                })
                .thenAccept(this.currentUploadedImageReference::set)
                .exceptionally(this::onUploadFileFailure);
    }

    @FXML
    private void onClickSubmit() throws IOException {
        if (this.currentUploadedImageReference.get() == null) {
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
                        .imageKey(currentUploadedImageReference.get().id())
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
                alert.setContentText("Response: " + response.code() + " Message:" + response.message() + " Body:" + response.body());
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

    private void onUploadFileSuccessfully(final File file) {
        try {
            registrationScannedImg.setImage(new Image(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private <T> T onUploadFileFailure(final Throwable exception) {
        registrationScannedImg.imageProperty().set(null);
        exceptionHandler.accept(exception);
        return null;
    }

}
