package org.rrcat.arpf.ui.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.dae.arpf.dto.*;
import org.rrcat.arpf.ui.Helper;
import org.rrcat.arpf.ui.api.RetrofitFetch;
import org.rrcat.arpf.ui.api.schema.CustomerApi;
import org.rrcat.arpf.ui.api.schema.UploadApi;
import org.rrcat.arpf.ui.constants.CustomerFormData;
import org.rrcat.arpf.ui.constants.UploadDirectory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

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
    private TextField orgNameField;
    @FXML
    private ComboBox<String> instituteType;
    @FXML
    private TextField researchHeadName;
    @FXML
    private TextField researchMobileNo;
    @FXML
    private TextField researchEmail;
    @FXML
    private TextField officeAddress;
    @FXML
    private TextField researchCity;
    @FXML
    private ComboBox<String> researchState;
    @FXML
    private TextField researchPinCode;
    @FXML
    private TextField phoneNoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField scientistName;
    @FXML
    private TextField scientistMobNo;
    @FXML
    private TextField anyOtherInfo;
    @FXML
    private ImageView registrationScannedImg;
    @FXML
    private Button uploadRegScanned;
    @FXML
    private Button saveRecordCustomer;

    private UploadedImageDTO currentUploadedImage;

    private final CustomerDTOBuilder customerViewModel = CustomerDTOBuilder.builder();

    private final Retrofit authenticatedRetrofit;
    private final UploadApi uploadApi;
    private final CustomerApi customerApi;

    public CustomerRegController(final Retrofit authenticatedRetrofit, final UploadApi uploadApi, final CustomerApi customerApi) {
        this.authenticatedRetrofit = authenticatedRetrofit;
        this.uploadApi = uploadApi;
        this.customerApi = customerApi;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        customerRegNo.setText("EBRPF-Research-");
        instituteType.setEditable(true);
        instituteType.setItems(CustomerFormData.INSTITUTE_TYPES);
        researchState.setEditable(true);
        researchState.setItems(CustomerFormData.STATES);
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @FXML
    private void onClickUpload() throws IOException {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Image");
        final File selectedFile = fileChooser.showOpenDialog(uploadRegScanned.getScene().getWindow());
        if (selectedFile == null) {
            return;
        }
        final RequestBody fileBody = MultipartBody.create(MultipartBody.FORM, selectedFile);

        final Call<UploadedImageDTO> uploadCall = uploadApi.upload(UploadDirectory.REGISTRATION, fileBody);

        final Response<UploadedImageDTO> response = uploadCall.execute();
        final UploadedImageDTO imageDTO = response.body();
        this.currentUploadedImage = imageDTO;
    }

    @FXML
    private void onClickSubmit() throws IOException{

    }

}
