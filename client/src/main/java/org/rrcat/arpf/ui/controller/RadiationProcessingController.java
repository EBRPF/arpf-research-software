package org.rrcat.arpf.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.dae.arpf.dto.*;
import org.rrcat.arpf.ui.api.schema.OrderApi;
import org.rrcat.arpf.ui.api.schema.OrderRPApi;
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

public class RadiationProcessingController  implements Initializable {
    @FXML
    private ComboBox<String> orderNumber;
    @FXML
    private TextField dosimeterUsed;
    @FXML
    private TextField dosimeterLocation;
    @FXML
    private DatePicker radProcessDate;
    @FXML
    private TextField radStartTime;
    @FXML
    private TextField completionTime;
    @FXML
    private TextField beamEnergy;
    @FXML
    private TextField beamCurrent;
    @FXML
    private TextField PRR;
    @FXML
    private TextField scanWidth;
    @FXML
    private TextField scanCurrentTime;
    @FXML
    private TextField conveyorSpeed;
    @FXML
    private TextField doseRate;
    @FXML
    private TextField stoSDistance;
    @FXML
    private TextField otherMacParameters;
    @FXML
    private TextField operatorRemarks;
    @FXML
    private TextField orgNameField;
    @FXML
    private TextField descrOfProducts;
    @FXML
    private TextField materialOfProduct;
    @FXML
    private TextField detailOfProduct;
    @FXML
    private TextField PurposeOfIrrad;
    @FXML
    private TextField modeOfIrrad;
    @FXML
    private TextField requireDose;
    @FXML
    private TextField dimenOfProduct;
    @FXML
    private TextField weightOfProduct;
    @FXML
    private TextField totalSampleBoxes;
    @FXML
    private TextField anyOrderInfo;
    @FXML
    private DatePicker dateOfOrder;
    @FXML
    private ImageView requestForm;
    @FXML
    private TextField fIComments;
    @FXML
    private CheckBox orderConfirmCB;
    @FXML
    private CheckBox irradiationProcessedCB;
    @FXML
    private Button saveRecordORP;

    private final AtomicReference<UploadedImageDTO> currentUploadedImageReference = new AtomicReference<>();


    private final OrderRPApi orderRPApi;
    private final OrderApi orderApi;

    private final ImageUploadService uploadService;
    private final Supplier<File> uploadFileSupplier;
    private final Consumer<Throwable> exceptionHandler;

    public RadiationProcessingController(final OrderRPApi orderRPApi, final OrderApi orderApi, final ImageUploadService uploadService, final @ImageFileSupplier Supplier<File> uploadFileSupplier, final @AlertingExceptionConsumer Consumer<Throwable> exceptionHandler) {
        this.orderRPApi = orderRPApi;
        this.orderApi = orderApi;
        this.uploadService = uploadService;
        this.uploadFileSupplier = uploadFileSupplier;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
                        .startTime(radStartTime.getText())
                        .endTime(completionTime.getText())
                        .operatorRemarks(operatorRemarks.getText())
                        .PRR(PRR.getText())
                        .relatedMachineParams(otherMacParameters.getText())
                        .scanWidth(Float.parseFloat(scanWidth.getText()))
                        .scanCurrentAndTime(scanCurrentTime.getText())
                        .sourceToSurfaceDimension(stoSDistance.getText())
                        .build();
        try {
            final Call<OrderRadiationProcessingDTO> call = orderRPApi.registerORP(dto);
            final Response<OrderRadiationProcessingDTO> response = call.execute();
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
}
