package org.rrcat.arpf.ui.controller;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public final class NavigationController implements Initializable {
    @FXML
    private HBox headerBox;
    @FXML
    private ImageView logoImage;
    @FXML
    private StackPane pageView;

    private final FXMLLoader fxmlLoader;

    @Inject
    public NavigationController(final FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logoImage.setFitWidth(70);
        logoImage.setFitHeight(70);
    }

    @FXML
    public void navigate(final MouseEvent mouseEvent) throws IOException {
        final ObservableList<Node> children = pageView.getChildren();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/order_radiation_processing.fxml"));
        loader.setControllerFactory(fxmlLoader.getControllerFactory());
        children.clear();
        children.add(loader.load());
    }
}
