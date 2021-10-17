package org.rrcat.arpf.ui.controller;

import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.dae.arpf.dto.*;
import org.rrcat.arpf.ui.api.schema.ShippingDetailsApi;
import org.rrcat.arpf.ui.constants.CustomerFormData;
import org.rrcat.arpf.ui.di.annotations.AlertingExceptionConsumer;
import org.rrcat.arpf.ui.di.annotations.ImageFileSupplier;
import org.rrcat.arpf.ui.service.ImageUploadService;
import org.rrcat.arpf.ui.util.Dates;
import retrofit2.Call;
import retrofit2.Response;

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

public class ShippingDetailController implements Initializable {
    @FXML
    private ComboBox<String> orderNumber;
    @FXML
    private TextField organizationName;
    @FXML
    private TextField productDescription;
    @FXML
    private TextField productCount;
    @FXML
    private DatePicker shippedDate;
    @FXML
    private TextField shippingMedium;
    @FXML
    private TextField shippingAdd;
    @FXML
    private TextField shippingCity;
    @FXML
    private ComboBox<String> shippingState;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField shippedCount;
    @FXML
    private ImageView gatePassScannedImg;
    @FXML
    private CheckBox dosimetryDoneCB;
    @FXML
    private Button saveRecordCustomer;


    private final AtomicReference<UploadedImageDTO> currentUploadedImageReference = new AtomicReference<>();


    private final ShippingDetailsApi shippingDetailsApi;

    private final ImageUploadService uploadService;
    private final Supplier<File> uploadFileSupplier;
    private final Consumer<Throwable> exceptionHandler;

    public ShippingDetailController(ShippingDetailsApi shippingDetailsApi, ImageUploadService uploadService, @ImageFileSupplier Supplier<File> uploadFileSupplier, final @AlertingExceptionConsumer Consumer<Throwable> exceptionHandler) {
        this.shippingDetailsApi = shippingDetailsApi;
        this.uploadService = uploadService;
        this.uploadFileSupplier = uploadFileSupplier;
        this.exceptionHandler = exceptionHandler;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dosimetryDoneCB.selectedProperty().addListener(this::onCheckboxUpdate);
        shippingState.setItems(CustomerFormData.STATES);
        gatePassScannedImg.setPreserveRatio(true);


    }

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
    private void onClickSubmit() {
        if (this.currentUploadedImageReference.get() == null) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Submit Failed");
            alert.setHeaderText("Image not selected.");
            alert.setContentText("Kindly select an image to be uploaded. Try again after selecting an image.");
            alert.show();
            return;
        }
        try {
            final  ShippingDetailsDTO dto =
                    ShippingDetailsDTOBuilder.builder()
                            .registrationNo(Integer.parseInt(orderNumber.getValue()))
                            .shippingDate(Dates.localToEpoch(shippedDate.getValue()))
                            .shippingMedium(shippingMedium.getText())
                            .shippingAddress(
                                    AddressDTOBuilder.builder()
                                            .addressText(shippingAdd.getText())
                                            .city(shippingCity.getText())
                                            .pinCode(postalCode.getText())
                                            .build()
                            )
                            .shippedPackets(Integer.parseInt(productCount.getText()))
                            .build();

            final Call<ShippingDetailsDTO> call = shippingDetailsApi.registerShippingDetails(dto);
            final Response<ShippingDetailsDTO> response = call.execute();
            final Alert alert;
            if (response.code() == 201 && response.body() != null) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Shipping Details");
                alert.setHeaderText("Shipping Details has been successfully registered for ID: " + response.body().registrationNo());
                alert.setContentText(null);

            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Shipping Details");
                alert.setHeaderText("Shipping Details attempt failed.");
                alert.setContentText("Response: " + response.code() + " Message:" + response.message() + " Body:" + response.body());
            }
            alert.show();
        } catch (final Exception exception) {
            exception.printStackTrace();
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Shipping Details");
            alert.setHeaderText("Shipping Details attempt failed.");
            alert.setContentText("Exception: " + exception.getClass().getName() + " " + exception.getMessage());
            alert.show();
        }

    }

    private void onUploadFileSuccessfully(final File file) {
        try {
            gatePassScannedImg.setImage(new Image(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private <T> T onUploadFileFailure(final Throwable exception) {
        gatePassScannedImg.imageProperty().set(null);
        exceptionHandler.accept(exception);
        return null;
    }

    private void onCheckboxUpdate(final ObservableValue<? extends Boolean> observable, final Boolean oldValue, final Boolean newValue) {
        saveRecordCustomer.setDisable(!newValue);
    }
}









