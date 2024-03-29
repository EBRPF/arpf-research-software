package org.rrcat.arpf.ui.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import com.jfoenix.controls.*;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.dae.arpf.dto.DosimetryDTO;
import org.dae.arpf.dto.DosimetryDTOBuilder;
import org.dae.arpf.dto.OrderDTO;
import org.dae.arpf.dto.UploadedImageDTO;
import org.rrcat.arpf.ui.api.model.OrderDosimetry;
import org.rrcat.arpf.ui.api.schema.DosimetryApi;
import org.rrcat.arpf.ui.api.schema.OrderApi;
import org.rrcat.arpf.ui.api.schema.ShippingDetailsApi;
import org.rrcat.arpf.ui.constants.OrderFormData;
import org.rrcat.arpf.ui.di.annotations.AlertingExceptionConsumer;
import org.rrcat.arpf.ui.di.annotations.ImageFileSupplier;
import org.rrcat.arpf.ui.service.ImageUploadService;
import retrofit2.Call;
import retrofit2.Response;

import javax.inject.Inject;
import javax.xml.stream.events.StartDocument;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OrderDosimetryController implements Initializable {

    @FXML
    private AutoCompleteTextField OrderNumber;
    @FXML
    private JFXComboBox orgName;
    @FXML
    private JFXTextField productDesc;
    @FXML
    private JFXTextField MaterialOfProduct;
    @FXML
    private JFXTextField productDetail;
    @FXML
    private JFXComboBox irradiationPurpose;
    @FXML
    private JFXComboBox irradiationMode;
    @FXML
    private JFXTextField requireDose;
    @FXML
    private JFXTextField dosimeterUsed;
    @FXML
    private JFXTextField dosimeterLocation;
    @FXML
    private JFXDatePicker receiptDatePicker;
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
    private JFXDatePicker measurementDate;
    @FXML
    private  JFXTextField measurement;
    @FXML
    private ImageView beforeImageKey;
    @FXML
    private JFXButton BeforeImgBtn;
    @FXML
    private ImageView afterImageKey;
    @FXML
    private JFXButton AfterImgBtn;
    @FXML
    private JFXCheckBox DosimetryDoneCB;
    @FXML
    private JFXButton SaveRecord_Dosimetry;
    @FXML
    private JFXTextField dosimetryDoneBy;

    private final AtomicReference<UploadedImageDTO> currentBeforeUploadedImageReference = new AtomicReference<>();
    private final AtomicReference<UploadedImageDTO> currentAfterUploadedImageReference = new AtomicReference<>();





    private final ImageUploadService uploadService;
    private final DosimetryApi dosimetryApi;

    private final Supplier<File> uploadFileSupplier;
    private final Consumer<Throwable> exceptionHandler;



    @Inject
    public OrderDosimetryController(final ImageUploadService uploadService, final @ImageFileSupplier Supplier<File> uploadFileSupplier, final @AlertingExceptionConsumer Consumer<Throwable> exceptionHandler, final DosimetryApi dosimetryApi) {
        this.uploadService = uploadService;
        this.uploadFileSupplier = uploadFileSupplier;
        this.exceptionHandler = exceptionHandler;
        this.dosimetryApi = dosimetryApi;
    }


    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        DosimetryDoneCB.selectedProperty().addListener(this::onCheckboxUpdate);
        SaveRecord_Dosimetry.setDisable(!DosimetryDoneCB.isSelected());
    }


    private void onClickUpload(final AtomicReference<UploadedImageDTO> imageReference, ImageView view) {
        CompletableFuture.completedFuture(uploadFileSupplier.get())
                .thenApply((file) -> {
                    final UploadedImageDTO dto = uploadService.upload(file);
                    onUploadFileSuccessfully(file, view);
                    return dto;
                })
                .thenAccept(imageReference::set)
                .exceptionally(throwable -> onUploadFileFailure(throwable, view));
    }

    @FXML
    private void onClickSubmit() {
        if (this.currentBeforeUploadedImageReference.get() == null || this.currentAfterUploadedImageReference.get() == null) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Submit Failed");
            alert.setHeaderText(String.format("%s image not selected",this.currentBeforeUploadedImageReference.get() == null ? "Before":"After"));
            alert.setContentText("Kindly select an image to be uploaded. Try again after selecting an image.");
            alert.show();
            return;
        }


        final DosimetryDTO dosimetryDTO = DosimetryDTOBuilder.builder()
                .registrationNo(Integer.parseInt(OrderNumber.getText()))
                .measurementDate(Date.from(measurementDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .measurement(measurement.getText())
                .beforeImageKey(currentBeforeUploadedImageReference.get().id())
                .afterImageKey(currentAfterUploadedImageReference.get().id())
                .dosimetryDoneBy(dosimetryDoneBy.getText())
                .build();

        try {
            final Call<DosimetryDTO> call = dosimetryApi.registerDosimetry(dosimetryDTO);
            final Response<DosimetryDTO> response = call.execute();
            final Alert alert;
            if (response.code() == 201 && response.body() != null) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Order Dosimetry");
                alert.setHeaderText("Dosimetry has been successfully recorded with ID: " + response.body().registrationNo());
                alert.setContentText(null);

            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Order Dosimetry");
                alert.setHeaderText("Order Dosimetry attempt failed.");
                alert.setContentText("Response: " + response.code() + " Message:" + response.message() + " Body:" + response.body());
            }
            alert.show();
        } catch (final Exception exception) {
            exception.printStackTrace();
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Order Dosimetry");
            alert.setHeaderText("Order Dosimetry attempt failed.");
            alert.setContentText("Exception: " + exception.getClass().getName() + " " + exception.getMessage());
            alert.show();
        }



    }


    private void onUploadFileSuccessfully(File file, ImageView view) {
        try {
            view.setImage(new Image(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Void onUploadFileFailure(Throwable throwable, ImageView view) {

        view.imageProperty().set(null);
        exceptionHandler.accept(throwable);
        return null;

    }



    private void onCheckboxUpdate(final ObservableValue<? extends Boolean> observable, final Boolean oldValue, final Boolean newValue) {
        SaveRecord_Dosimetry.setDisable(!newValue);
    }

    public void onClickUploadAfter(ActionEvent actionEvent) {
        onClickUpload(currentAfterUploadedImageReference, afterImageKey );
    }

    public void onClickUploadBefore(ActionEvent actionEvent) {
        onClickUpload(currentBeforeUploadedImageReference, beforeImageKey);
    }
}
