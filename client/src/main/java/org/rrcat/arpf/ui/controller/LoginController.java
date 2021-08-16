package org.rrcat.arpf.ui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dae.arpf.dto.AuthenticationTokenDTO;
import org.dae.arpf.dto.LoginRequestDTO;
import org.dae.arpf.dto.LoginRequestDTOBuilder;
import org.rrcat.arpf.ui.api.RetrofitFactory;
import org.rrcat.arpf.ui.api.schema.AuthenticationApi;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button loginBtn;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    private AuthenticationApi api;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.api = RetrofitFactory.create().create(AuthenticationApi.class);
        usernameTextField.setText("Administrator");
        passwordTextField.setText("Lo5PofeWw8@r");
    }
    @FXML
    private void onClickLogin(final ActionEvent event) throws IOException {
        final LoginRequestDTO dto = LoginRequestDTOBuilder.builder()
                .uid(usernameTextField.getText())
                .password(passwordTextField.getText())
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(dto));

        final Call<AuthenticationTokenDTO> tokenDTOCall = api.authenticate(dto);
        final Response<AuthenticationTokenDTO> response = tokenDTOCall.execute();
        final AuthenticationTokenDTO tokenDTO = response.body();
        System.out.println(tokenDTO);
        if (tokenDTO == null) {
            // TODO Proper error message
            return;
        }

    }

    private void loadSceneAndSendMessage()  {
        try {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //Load second scene
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/newCustomerRegistration.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                        Stage stage = (Stage) usernameTextField.getScene().getWindow();
                        // do what you have to do
                        stage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //Get controller of scene2
                    CustomerRegController scene2Controller = loader.getController();
                    //Pass whatever data you want. You can have multiple method calls here

                    //Show scene 2 in new window
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Second Window");
                    stage.show();
                }
            });

        } finally {

        }
    }
}
