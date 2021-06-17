package org.rrcat.arpf.ui.controller;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;


public final class NavigationController implements Initializable {
    @FXML
    private HBox headerBox;

    @FXML
    private ImageView logoImage;

    public NavigationController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logoImage.setFitWidth(70);
        logoImage.setFitHeight(70);
    }
}
