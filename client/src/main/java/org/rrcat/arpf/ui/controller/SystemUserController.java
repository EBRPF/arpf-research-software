package org.rrcat.arpf.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.rrcat.arpf.ui.constants.RoleData;

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




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        password.setOnKeyTyped(this::handleButtonState);
        confirmPassword.setOnKeyTyped(this::handleButtonState);
        userRole.setItems(RoleData.ROLES);
    }

    private void handleButtonState(final KeyEvent keyEvent) {
        addBtn.setDisable(!confirmPassword.getText().equalsIgnoreCase(password.getText()));
    }

}
