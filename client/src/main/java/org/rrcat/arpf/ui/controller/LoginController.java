package org.rrcat.arpf.ui.controller;

import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.rrcat.arpf.ui.api.ApiInterface;
import org.rrcat.arpf.ui.api.RetrofitFetch;
import org.rrcat.arpf.ui.api.service.UserClient;
import org.rrcat.arpf.ui.entity.RequestLogin;
import org.rrcat.arpf.ui.entity.auth.AuthenticationToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button loginBtn;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            usernameTextField.setText("Administrator");
            passwordTextField.setText("Lo5PofeWw8@r");
    }
    @FXML
    private void LoginAction(ActionEvent event)
    {

        RequestLogin requestLogin =new RequestLogin(usernameTextField.getText().toString().trim(),
                                                    passwordTextField.getText().toString().trim() );
        sendNetworkRequest(requestLogin);
    }

    private void sendNetworkRequest(RequestLogin requestLogin) {
        Retrofit retrofit= new RetrofitFetch().fetch();

        UserClient client =retrofit.create(UserClient.class);
      Call<AuthenticationToken> call= client.LoginAccount(requestLogin);
      call.enqueue(new Callback<AuthenticationToken>() {
          @Override
          public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
              System.out.println("here"+response.body().getExpirationSeconds());
          }

          @Override
          public void onFailure(Call<AuthenticationToken> call, Throwable t) {

          }

      });
    }
}
