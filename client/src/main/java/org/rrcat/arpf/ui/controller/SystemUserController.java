package org.rrcat.arpf.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SystemUserController implements Initializable {

    @FXML
    private TextField NameOfUser;
    @FXML
    private TextField UsernameField;
    @FXML
    private TextField PasswordField;
    @FXML
    private TextField ConfirmPassword;
    @FXML
    private ComboBox<String> UserRoles;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
