package org.rrcat.arpf.ui.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.dae.arpf.dto.*;
import org.rrcat.arpf.ui.api.schema.CustomerApi;
import org.rrcat.arpf.ui.api.schema.OrderApi;
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
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class OrderRegController implements Initializable {
    @FXML
    private AutoCompleteTextField<String> organizationName;
    @FXML
    private TextField productDescription;
    @FXML
    private TextField productMaterial;
    @FXML
    private TextField productDetail;
    @FXML
    private TextField irradiationPurpose;
    @FXML
    private ComboBox<String> irradiationMode;
    @FXML
    private TextField doseRequired;
    @FXML
    private TextField productDimensions;
    @FXML
    private TextField productWeight;
    @FXML
    private TextField totalSampleBoxes;
    @FXML
    private TextField extraInfo;
    @FXML
    private DatePicker receiptDatePicker;
    @FXML
    private ImageView registrationScannedImg;
    @FXML
    private StackPane imageOuterPane;
    @FXML
    private TextField inchargeComments;
    @FXML
    private CheckBox confirmationCheckbox;
    @FXML
    private Button submitOrder;

    private final AtomicReference<UploadedImageDTO> currentUploadedImageReference = new AtomicReference<>();

    private final ImageUploadService uploadService;


    private final Supplier<File> uploadFileSupplier;
    private final Consumer<Throwable> exceptionHandler;

    private final OrderApi api;
    private final CustomerApi customerApi;

    @Inject
    public OrderRegController(final ImageUploadService uploadService, final @ImageFileSupplier Supplier<File> uploadFileSupplier, final @AlertingExceptionConsumer Consumer<Throwable> exceptionHandler, final OrderApi api, CustomerApi customerApi) {
        this.uploadService = uploadService;
        this.uploadFileSupplier = uploadFileSupplier;
        this.exceptionHandler = exceptionHandler;
        this.api = api;
        this.customerApi = customerApi;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        irradiationMode.setItems(OrderFormData.IRRADIATION_MODE);
        confirmationCheckbox.selectedProperty().addListener(this::onCheckboxUpdate);
        submitOrder.setDisable(!confirmationCheckbox.isSelected());
        organizationName.setCompleter(string -> {
            if (string.trim().isEmpty()) {
                return Collections.emptyList();
            }
            try {
                return customerApi.searchCustomerByOrganization(string).execute().body().stream().map(CustomerDTO::organization).map(OrganizationDTO::name).collect(Collectors.toList());
            } catch (IOException exception) {
                exception.printStackTrace();
                return Collections.emptyList();
            }
        });
    }

    @FXML
    private void onClickUpload() {
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
        final Integer customerRegNo;
        final Call<CustomerDTO> customerCall = customerApi.fetchCustomerByOrganization(organizationName.getText());
        try {
            final Response<CustomerDTO> customerResponse = customerCall.execute();
            final CustomerDTO customerDTO = customerResponse.body();
            customerRegNo = customerDTO.registrationNo();
        } catch (IOException exception) {
            exception.printStackTrace();
            final Alert customerRegAlert = new Alert(Alert.AlertType.ERROR);
            customerRegAlert.setTitle("Order Registration");
            customerRegAlert.setHeaderText("Invalid Organization Name");
            customerRegAlert.setContentText(null);
            customerRegAlert.show();
            return;
        }
        final OrderDTO dto = OrderDTOBuilder.builder()
                .comments(inchargeComments.getText())
                .extraInfo(extraInfo.getText())
                .customerId(customerRegNo)
                .imageKey(currentUploadedImageReference.get().id())
                .irradiationMode(irradiationMode.getValue())
                .irradiationPurpose(irradiationPurpose.getText())
                .productCount(Integer.parseInt(totalSampleBoxes.getText()))
                .productDescription(productDescription.getText())
                .productDimensions(productDimensions.getText())
                .productMaterial(productMaterial.getText())
                .receiptDate(Date.from(receiptDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .requiredDose(doseRequired.getText())
                .productWeight(productWeight.getText())
                .registered(confirmationCheckbox.isSelected())
                .build();

        try {
            final Call<OrderDTO> call = api.registerOrder(dto);
            final Response<OrderDTO> response = call.execute();
            final Alert alert;
            if (response.code() == 201 && response.body() != null) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Order Registration");
                alert.setHeaderText("Order has been successfully registered with ID: " + response.body().registrationNo());
                alert.setContentText(null);

            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Order Registration");
                alert.setHeaderText("Order registration attempt failed.");
                alert.setContentText("Response: " + response.code() + " Message:" + response.message() + " Body:" + response.body());
            }
            alert.show();
        } catch (final Exception exception) {
            exception.printStackTrace();
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Order Registration");
            alert.setHeaderText("Order registration attempt failed.");
            alert.setContentText("Exception: " + exception.getClass().getName() + " " + exception.getMessage());
            alert.show();
        }
    }

    private void onCheckboxUpdate(final ObservableValue<? extends Boolean> observable, final Boolean oldValue, final Boolean newValue) {
        submitOrder.setDisable(!newValue);
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
