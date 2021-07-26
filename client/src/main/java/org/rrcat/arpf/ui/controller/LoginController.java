package org.rrcat.arpf.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.rrcat.arpf.ui.api.service.ApiInterface;
import org.rrcat.arpf.ui.api.RetrofitFetch;
import org.rrcat.arpf.ui.api.model.RequestLogin;
import org.rrcat.arpf.ui.entity.auth.AuthenticationToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.net.URL;
import java.util.ResourceBundle;

public final class LoginController implements Initializable {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onClickLogin(final ActionEvent event) {
        final RequestLogin requestLogin = new RequestLogin(usernameTextField.getText().toString().trim(),
                passwordTextField.getText().toString().trim());
        sendNetworkRequest(requestLogin);
    }

    private void sendNetworkRequest(RequestLogin requestLogin) {
        Retrofit retrofit = new RetrofitFetch().fetch();

        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<AuthenticationToken> call = client.LoginAccount(requestLogin);
        call.enqueue(new Callback<AuthenticationToken>() {
            @Override
            public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
                System.out.println("here" + response.body().getExpirationSeconds());
            }

            @Override
            public void onFailure(Call<AuthenticationToken> call, Throwable t) {

            }

        });
    }
}
