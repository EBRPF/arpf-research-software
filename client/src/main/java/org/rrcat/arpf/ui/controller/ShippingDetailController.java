package org.rrcat.arpf.ui.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.dae.arpf.dto.*;
import org.rrcat.arpf.ui.api.schema.OrderApi;
import org.rrcat.arpf.ui.api.schema.ShippingDetailsApi;
import org.rrcat.arpf.ui.constants.CustomerFormData;
import org.rrcat.arpf.ui.constants.OrderFormData;
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
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static javafx.scene.input.KeyCode.T;

public class ShippingDetailController implements Initializable {
    @FXML
    private AutoCompleteTextField<String> OrderNumber;
    @FXML
    private TextField OrgNameField;
    @FXML
    private TextField DescrOfProducts;
    @FXML
    private TextField TotalSampleBoxes;
    @FXML
    private DatePicker ShippedDate;
    @FXML
    private TextField ShippedName;
    @FXML
    private TextField ShippedAddress;
    @FXML
    private TextField ShipCity;
    @FXML
    private ComboBox<String> ShipState;
    @FXML
    private TextField ShipPostalCode;
    @FXML
    private TextField TotalProdShipped;
    @FXML
    private ImageView ScannedGatePass;
    @FXML
    private StackPane imageOuterPane;
    @FXML
    private ImageView DosimetryReport;
    @FXML
    private CheckBox ShippingDoneCB;
    @FXML
    private Button saveRecordShipping;

    private final AtomicReference<UploadedImageDTO> currentUploadedImageReference = new AtomicReference<>();

    private final ImageUploadService uploadService;

    private final ShippingDetailsApi shippingDetailsApi;
    private final Consumer<Throwable> exceptionHandler;
    private final ShippingDetailsApi api;
    private final Supplier<File> uploadFileSupplier;


    @Inject
    public ShippingDetailController(final ImageUploadService uploadService, final @ImageFileSupplier Supplier<File> uploadFileSupplier, final @AlertingExceptionConsumer Consumer<Throwable> exceptionHandler, final ShippingDetailsApi api) {
        this.uploadService = uploadService;
        this.uploadFileSupplier = uploadFileSupplier;
        this.exceptionHandler = exceptionHandler;
        this.api = api;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ShipState.setItems(CustomerFormData.STATES);
        ShippingDoneCB.selectedProperty().addListener(this::onCheckboxUpdate);
        saveRecordShipping.setDisable(!ShippingDoneCB.isSelected());
        ScannedGatePass.setPreserveRatio(true);
        ScannedGatePass.fitWidthProperty().bind(imageOuterPane.widthProperty());

        DosimetryReport.setPreserveRatio(true);
        DosimetryReport.fitWidthProperty().bind(imageOuterPane.widthProperty());

    }

    private void onCheckboxUpdate(final ObservableValue<? extends Boolean> observable, final Boolean oldValue, final Boolean newValue) {
        saveRecordShipping.setDisable(!newValue);
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
    public void onClickSubmit() throws IOException {
        if (this.currentUploadedImageReference.get() == null) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Submit Failed");
            alert.setHeaderText("Image not selected.");
            alert.setContentText("Kindly select an image to be uploaded. Try again after selecting an image.");
            alert.show();
            return;

            /*
            final ShippingDetailsDTO dto =
                    ShippingDetailsDTOBuilder.builder()
                            .shippingAddress(
                                    AddressDTOBuilder.builder()
                                            .addressText(ShippedAddress.getText())
                                            .city(ShipCity.getText())
                                            .state(ShipState.getValue())
                                            .pinCode(ShipPostalCode.getText())
                                            .build()
                            )
                            .shippedPackets(
                                    ShippingDetailsDTOBuilder.builder()
                                            .shippedPackets(TotalProdShipped.getText())
                                            .build()
                            )
                            .build();

             */

            final ShippingDetailsDTO shippingDTO = ShippingDetailsDTOBuilder.builder()
                    .shippingDate(Date.from(ShippedDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .shippingMedium(ShippedName.getText())
                    .gatePassImageKey(currentUploadedImageReference.get().id())
                    .dosimetryReportImageKey(currentUploadedImageReference.get().id())
                    .shippingAddress(ShippedAddress.getText())
                    .shippingCity(ShipCity.getText())
                    .ShipState(ShipState.getValue())
                    .shippingPostalCode(ShipPostalCode.getText())
                    .shippedPackets(TotalProdShipped.getText())
                    .registered(ShippingDoneCB.isSelected())
                    .build();


            try {
                final Call<ShippingDetailsDTO> call = shippingDetailsApi.registerShippingDetails(shippingDTO);
                final Response<ShippingDetailsDTO> response = call.execute();
                final Alert alert;
                if (response.code() == 201 && response.body() != null) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Shipping Details");
                    alert.setHeaderText("Shipping Deatils for Customer with ID: " + response.body().registrationNo());
                    alert.setContentText(null);

                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Shipping Details");
                    alert.setHeaderText("Shipping Details Update failed.");
                    alert.setContentText("Response: " + response.code() + " Message:" + response.message() + " Body:" + response.body());
                }
                alert.show();
            } catch (final Exception exception) {
                exception.printStackTrace();
                final Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Shipping Details");
                alert.setHeaderText("Shipping Details Update failed.");
                alert.setContentText("Exception: " + exception.getClass().getName() + " " + exception.getMessage());
                alert.show();
            }
        }

        private void onUploadFileSuccessfully(File file) {
            try {
                ScannedGatePass.setImage(new Image(new FileInputStream(file)));
                DosimetryReport.setImage(new Image(new FileInputStream(file)));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        private Void onUploadFileFailure(Throwable throwable) {
            ScannedGatePass.imageProperty().set(null);
            DosimetryReport.imageProperty().set(null);
            exceptionHandler.accept(exception);
            return null;
        }



    }






}


