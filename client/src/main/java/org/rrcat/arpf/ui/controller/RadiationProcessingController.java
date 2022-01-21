package org.rrcat.arpf.ui.controller;

import com.jfoenix.controls.*;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.dae.arpf.dto.*;
import org.rrcat.arpf.ui.api.schema.OrderApi;
import org.rrcat.arpf.ui.api.schema.OrderRPApi;
import org.rrcat.arpf.ui.constants.CustomerFormData;
import org.rrcat.arpf.ui.constants.OrderFormData;
import org.rrcat.arpf.ui.di.annotations.AlertingExceptionConsumer;
import org.rrcat.arpf.ui.di.annotations.ImageFileSupplier;
import org.rrcat.arpf.ui.service.ImageUploadService;
import org.rrcat.arpf.ui.util.Dates;
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

public class RadiationProcessingController  implements Initializable {
    @FXML
    private JFXComboBox<String> orderNumber;
    @FXML
    private JFXTextField dosimeterUsed;
    @FXML
    private JFXTextField dosimeterLocation;
    @FXML
    private JFXDatePicker radProcessDate;
    @FXML
    private JFXTextField beamEnergy;
    @FXML
    private JFXTextField beamCurrent;
    @FXML
    private JFXTextField PRR;
    @FXML
    private JFXTextField scanWidth;
    @FXML
    private JFXTextField scanCurrentTime;
    @FXML
    private JFXTextField conveyorSpeed;
    @FXML
    private JFXTextField doseRate;
    @FXML
    private JFXTextField sourceToSurfaceDistance;
    @FXML
    private JFXTextField machineParameters;
    @FXML
    private JFXTextField operatorRemarks;
    @FXML
    private JFXComboBox<String> orgName;
    @FXML
    private JFXTextField productDesc;
    @FXML
    private JFXTextField productMaterial;
    @FXML
    private JFXTextField productDetail;
    @FXML
    private JFXComboBox<String> irradiationPurpose;
    @FXML
    private JFXComboBox<String> irradiationMode;
    @FXML
    private JFXTextField requireDose;
    @FXML
    private JFXTextField productDimensions;
    @FXML
    private JFXTextField productWeight;
    @FXML
    private JFXTextField totalSampleBoxes;
    @FXML
    private JFXTextField extraInfo;
    @FXML
    private JFXDatePicker dateOfReceipt;
    @FXML
    private ImageView requestForm;
    @FXML
    private JFXTextField facilityICComments;
    @FXML
    private JFXCheckBox orderConfirmCB;
    @FXML
    private JFXCheckBox irradiationProcessedCB;
    @FXML
    private JFXButton saveRecordORP;
    @FXML
    private JFXTextField nameOfOperator;
    @FXML
    private JFXButton uploadRegScanned;

    private final AtomicReference<UploadedImageDTO> currentUploadedImageReference = new AtomicReference<>();


    private final OrderRPApi orderRPApi;
    private final OrderApi orderApi;

    private final ImageUploadService uploadService;
    private final Supplier<File> uploadFileSupplier;
    private final Consumer<Throwable> exceptionHandler;

    @Inject
    public RadiationProcessingController(final OrderRPApi orderRPApi, final OrderApi orderApi, final ImageUploadService uploadService, final @ImageFileSupplier Supplier<File> uploadFileSupplier, final @AlertingExceptionConsumer Consumer<Throwable> exceptionHandler) {
        this.orderRPApi = orderRPApi;
        this.orderApi = orderApi;
        this.uploadService = uploadService;
        this.uploadFileSupplier = uploadFileSupplier;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        irradiationProcessedCB.selectedProperty().addListener(this::onCheckboxUpdate);
        saveRecordORP.setDisable(!irradiationProcessedCB.isSelected());
        irradiationMode.setItems(OrderFormData.IRRADIATION_MODE);
        irradiationPurpose.setItems(OrderFormData.IRRADIATION_PURPOSE);

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
    private void onClickSubmit() throws IOException {
        if (this.currentUploadedImageReference.get() == null) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Submit Failed");
            alert.setHeaderText("Image not selected.");
            alert.setContentText("Kindly select an image to be uploaded. Try again after selecting an image.");
            alert.show();
            return;
        }
        final OrderRadiationProcessingDTO dto =
                OrderRadiationProcessingDTOBuilder.builder()
                        .registrationNo(Integer.parseInt(orderNumber.getValue()))
                        .beamCurrent(beamCurrent.getText())
                        .beamEnergy(beamEnergy.getText())
                        .conveyorSpeed(conveyorSpeed.getText())
                        .doseRate(doseRate.getText())
                        .dosimeterLocation(dosimeterLocation.getText())
                        .dosimeterUsed(dosimeterUsed.getText())
                        .processingDate(Dates.localToEpoch(radProcessDate.getValue()))
                        .operatorRemarks(operatorRemarks.getText())
                        .PRR(PRR.getText())
                        .relatedMachineParams(machineParameters.getText())
                        .scanWidth(Float.parseFloat(scanWidth.getText()))
                        .scanCurrentAndTime(scanCurrentTime.getText())
                        .sourceToSurfaceDimension(sourceToSurfaceDistance.getText())
                        .build();
        try {
            final Call<OrderRadiationProcessingDTO> call = orderRPApi.registerORP(dto);
            final Response<OrderRadiationProcessingDTO> response = call.execute();
            final Alert alert;
            if (response.code() == 201 && response.body() != null) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Order Radiation Processing");
                alert.setHeaderText("Order Radiation Processing has been successfully registered for ID: " + response.body().registrationNo());
                alert.setContentText(null);

            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Order Radiation Processing");
                alert.setHeaderText("Order Radiation Processing attempt failed.");
                alert.setContentText("Response: " + response.code() + " Message:" + response.message() + " Body:" + response.body());
            }
            alert.show();
        } catch (final Exception exception) {
            exception.printStackTrace();
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Order Radiation Processing");
            alert.setHeaderText("Order Radiation Processing attempt failed.");
            alert.setContentText("Exception: " + exception.getClass().getName() + " " + exception.getMessage());
            alert.show();
        }
    }

    private void onUploadFileSuccessfully(final File file) {
        try {
            requestForm.setImage(new Image(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private <T> T onUploadFileFailure(final Throwable exception) {
        requestForm.imageProperty().set(null);
        exceptionHandler.accept(exception);
        return null;
    }

    private void onCheckboxUpdate(final ObservableValue<? extends Boolean> observable, final Boolean oldValue, final Boolean newValue) {
        saveRecordORP.setDisable(!newValue);
    }
}
