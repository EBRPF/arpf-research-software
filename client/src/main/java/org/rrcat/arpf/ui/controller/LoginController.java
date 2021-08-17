package org.rrcat.arpf.ui.controller;

import com.google.common.base.Functions;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
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
import java.util.concurrent.CompletableFuture;

public class LoginController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ProgressIndicator loginIndicator;

    private AuthenticationApi api;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.api = RetrofitFactory.create().create(AuthenticationApi.class);
        username.setText("Administrator");
        password.setText("Lo5PofeWw8@r");
    }
    @FXML
    private void onClickLogin(final ActionEvent event) throws IOException {
        final LoginRequestDTO dto = LoginRequestDTOBuilder.builder()
                .uid(username.getText())
                .password(password.getText())
                .build();
        username.getParent().setDisable(true);
        loginIndicator.getParent().setVisible(true);
        CompletableFuture.supplyAsync(() -> authenticate(dto)).thenAccept((response) -> {
            try {
                Thread.sleep(1000); // UX
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (response.code() == 200 && response.body() != null) {
                Platform.runLater(() -> {
                    try {
                        onAuthenticated(dto);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                // Failed login
            }
            loginIndicator.getParent().setVisible(false);
            username.getParent().setDisable(false);
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            loginIndicator.getParent().setVisible(false);
            username.getParent().setDisable(false);
            return null;
        });
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
        final Stage stage = (Stage) username.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
    }
}
