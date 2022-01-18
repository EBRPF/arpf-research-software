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
    public void navigateCustomerReg(final MouseEvent mouseEvent) throws IOException {
        navigate("customer_registration");
    }

    @FXML
    public void navigateOrderReg(final MouseEvent mouseEvent) throws IOException {
        navigate("order_registration");
    }

    @FXML
    public void navigateORP(final MouseEvent mouseEvent) throws IOException {
        navigate("order_radiation_processing");
    }

    @FXML
    public void navigateOrderDosi(final MouseEvent mouseEvent) throws IOException {
        navigate("order_dosimetry.fxml");
    }

    @FXML
    public void navigateShippingDetails(final MouseEvent mouseEvent) throws IOException {
        navigate("shipping_details.fxml");
    }

    @FXML
    public void navigateOrderStatus(final MouseEvent mouseEvent) throws IOException {
        navigate("check_order_status.fxml");
    }

    @FXML
    public void navigateSampleIrradRepo(final MouseEvent mouseEvent) throws IOException {
        navigate("sample_irradiation_report.fxml");
    }


    public void navigate(final String resourceName) throws IOException {
        final ObservableList<Node> children = pageView.getChildren();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/" + resourceName + ".fxml"));
        loader.setControllerFactory(fxmlLoader.getControllerFactory());
        children.clear();
        children.add(loader.load());
    }
}
