package org.rrcat.arpf.ui.controller;

import com.google.common.base.Functions;
import com.google.inject.Guice;
import com.google.inject.Injector;
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
import okhttp3.Authenticator;
import org.dae.arpf.dto.AuthenticationTokenDTO;
import org.dae.arpf.dto.LoginRequestDTO;
import org.dae.arpf.dto.LoginRequestDTOBuilder;
import org.rrcat.arpf.ui.api.RetrofitFactory;
import org.rrcat.arpf.ui.api.auth.JwtAuthenticator;
import org.rrcat.arpf.ui.api.schema.AuthenticationApi;
import org.rrcat.arpf.ui.di.AuthenticationModule;
import retrofit2.Call;
import retrofit2.Response;

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
        final Response<AuthenticationTokenDTO> response = authenticate(dto);
        final AuthenticationTokenDTO tokenDTO = response.body();
        if (tokenDTO != null && response.code() == 200) {
            onAuthenticated(dto);
        } else {
            // Failed login
        }

    }

    private Response<AuthenticationTokenDTO> authenticate(final LoginRequestDTO dto) {
        final Call<AuthenticationTokenDTO> tokenDTOCall = api.authenticate(dto);
        try {
            return tokenDTOCall.execute();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void onAuthenticated(final LoginRequestDTO dto) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/navigation.fxml"));
        final Authenticator authenticator = new JwtAuthenticator(dto, Functions.compose(Response<AuthenticationTokenDTO>::body, this::authenticate));
        final AuthenticationModule module = new AuthenticationModule(authenticator, loader);
        final Injector injector = Guice.createInjector(module);
        loader.setControllerFactory(injector::getInstance);
        final Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
    }
}
