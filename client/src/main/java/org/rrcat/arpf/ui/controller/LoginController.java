package org.rrcat.arpf.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button loginBtn;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    private void LoginAction(ActionEvent event)
    {
        System.out.println("on btn login clicked");
    }
}
