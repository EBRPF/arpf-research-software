package org.rrcat.arpf.ui.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
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
    private TextField OrgNameField;
    @FXML
    private TextField DescrOfProducts;
    @FXML
    private TextField MaterialOfProduct;
    @FXML
    private TextField DetailOfProduct;
    @FXML
    private TextField PurposeOfIrrad;
    @FXML
    private TextField ModeOfIrrad;
    @FXML
    private TextField RequiredDose;
    @FXML
    private TextField DosimeterUsed;
    @FXML
    private TextField DosimeterLocation;
    @FXML
    private DatePicker DateOfOrder;
    @FXML
    private DatePicker RadProcessDate;
    @FXML
    private TextField RadStartTime;
    @FXML
    private TextField CompletionTime;
    @FXML
    private TextField BeamEnergy;
    @FXML
    private TextField BeamCurrent;
    @FXML
    private TextField PRR;
    @FXML
    private TextField ScanWidth;
    @FXML
    private TextField ScanCurrentTime;
    @FXML
    private TextField ConveyorSpeed;
    @FXML
    private TextField DoseRate;
    @FXML
    private TextField StoSDistance;
    @FXML
    private TextField OtherMacParameters;
    @FXML
    private TextField OperatorRemarks;
    @FXML
    private DatePicker DosimetryDate;
    @FXML
    private  TextField DosimetryResult;
    @FXML
    private ImageView BeforeIrradImage;
    @FXML
    private Button BeforeImgBtn;
    @FXML
    private ImageView AfterIrradImage;
    @FXML
    private Button AfterImgBtn;
    @FXML
    private CheckBox DosimetryDoneCB;
    @FXML
    private Button SaveRecord_Dosimetry;
    @FXML
    private TextField dosimetryDoneBy;

    private final AtomicReference<UploadedImageDTO> currentUploadedImageReference = new AtomicReference<>();


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

        final DosimetryDTO dosimetryDTO = DosimetryDTOBuilder.builder()
                .registrationNo(Integer.parseInt(OrderNumber.getText()))
                .measurementDate(Date.from(DosimetryDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .measurement(DosimetryResult.getText())
                .beforeImageKey(currentUploadedImageReference.get().id())
                .afterImageKey(currentUploadedImageReference.get().id())
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


    private void onUploadFileSuccessfully(File file) {
        try {
            BeforeIrradImage.setImage(new Image(new FileInputStream(file)));
            AfterIrradImage.setImage(new Image(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Void onUploadFileFailure(Throwable throwable) {

        BeforeIrradImage.imageProperty().set(null);
        AfterIrradImage.imageProperty().set(null);
        exceptionHandler.accept(throwable);
        return null;

    }



    private void onCheckboxUpdate(final ObservableValue<? extends Boolean> observable, final Boolean oldValue, final Boolean newValue) {
            DosimetryDoneCB.setDisable(!newValue);
    }
}
