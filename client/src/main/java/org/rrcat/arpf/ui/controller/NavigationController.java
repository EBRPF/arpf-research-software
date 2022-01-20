package org.rrcat.arpf.ui.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public void navigateDashboard(final MouseEvent mouseEvent) throws IOException {
        navigate("dashboard");
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
        navigate("order_dosimetry");
    }

    @FXML
    public void navigateShippingDetails(final MouseEvent mouseEvent) throws IOException {
        navigate("shipping_details");
    }

    @FXML
    public void navigateOrderStatus(final MouseEvent mouseEvent) throws IOException {
        navigate("check_order_status");
    }

    @FXML
    public void navigateSampleIrradRepo(final MouseEvent mouseEvent) throws IOException {
        navigate("sample_irradiation_report");
    }


    public void navigate(final String resourceName) throws IOException {
        final ObservableList<Node> children = pageView.getChildren();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/" + resourceName + ".fxml"));
        loader.setControllerFactory(fxmlLoader.getControllerFactory());
        children.clear();
        // Temporary
        children.add(loader.load());
    }
}
