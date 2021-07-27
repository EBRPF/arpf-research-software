package org.rrcat.arpf.ui.controller;

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
import org.rrcat.arpf.ui.ArpfApplication;
import org.rrcat.arpf.ui.Helper;
import org.rrcat.arpf.ui.api.service.ApiInterface;
import org.rrcat.arpf.ui.api.RetrofitFetch;
import org.rrcat.arpf.ui.api.model.RequestLogin;
import org.rrcat.arpf.ui.entity.auth.AuthenticationToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

        ApiInterface client =retrofit.create(ApiInterface.class);
      Call<AuthenticationToken> call= client.LoginAccount(requestLogin);
      call.enqueue(new Callback<AuthenticationToken>() {
          @Override
          public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
         Helper.TOKEN= response.body().getToken();

                  System.out.println("From controller");


             loadSceneAndSendMessage();

          }

          @Override
          public void onFailure(Call<AuthenticationToken> call, Throwable t) {

          }

      });
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
