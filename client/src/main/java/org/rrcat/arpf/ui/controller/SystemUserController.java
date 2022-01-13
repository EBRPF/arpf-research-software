package org.rrcat.arpf.ui.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.dae.arpf.dto.UserDTO;
import org.dae.arpf.dto.UserDTOBuilder;
import org.rrcat.arpf.ui.api.schema.UserApi;
import org.rrcat.arpf.ui.constants.RoleData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class SystemUserController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPassword;
    @FXML
    private ComboBox<String> userRole;
    @FXML
    private Button addBtn;

    private final UserApi api;

    @Inject
    public SystemUserController(final UserApi api) {
        this.api = api;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        password.setOnKeyTyped(this::handleButtonState);
        confirmPassword.setOnKeyTyped(this::handleButtonState);
        userRole.setItems(RoleData.ROLES);
    }

    private void handleButtonState(final KeyEvent keyEvent) {
        addBtn.setDisable(!confirmPassword.getText().equalsIgnoreCase(password.getText()));
    }

    @FXML
    private void onButtonClick() {
        final UserDTO dto = UserDTOBuilder.builder().uid(username.getText()).password(password.getText()).enabled(true).role(userRole.getValue()).build();
        final Call<UserDTO> dtoCall = api.registerUser(dto);
        dtoCall.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                final UserDTO dto = response.body();
                final Alert userRegAlert = new Alert(Alert.AlertType.ERROR);
                userRegAlert.setTitle("User Registration");
                userRegAlert.setHeaderText("Created user! " + dto.uid());
                userRegAlert.setContentText(null);
                userRegAlert.show();
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Platform.runLater(() -> {
                    final Alert userRegAlert = new Alert(Alert.AlertType.ERROR);
                    userRegAlert.setTitle("User Registration");
                    userRegAlert.setHeaderText(t.getMessage());
                    userRegAlert.setContentText(null);
                    userRegAlert.show();
                });
            }
        });
    }

}
