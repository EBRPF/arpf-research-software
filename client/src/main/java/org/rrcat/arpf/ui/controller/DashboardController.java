package org.rrcat.arpf.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.rrcat.arpf.ui.api.schema.*;

import javax.swing.table.TableColumn;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class DashboardController implements Initializable {
    private static final String PREFIX = "ARPF-Research-Order-";
    @FXML
    private TextField orderNumber;
    @FXML
    private TableColumn orgName;
    @FXML
    private TableColumn instituteType;
    @FXML
    private TableColumn officeAddress;
    @FXML
    private TableColumn phoneNo;
    @FXML
    private TableColumn productDesc;

    private OrderApi orderApi;
    private CustomerApi customerApi;
    private OrderRPApi orderRPApi;
    private ShippingDetailsApi shippingDetailsApi;
    private DosimetryApi dosimetryApi;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderNumber.focusedProperty().addListener((value, oldVal, newVal) -> {
            if(!newVal) onFocusExit();
        });
    }

    private void onFocusExit() {
        final String reg = orderNumber.getText();
        if(!reg.startsWith(PREFIX)) {
            return;
        }
        final String value = reg.substring(PREFIX.length());
        final int regNumber;
        try{
            regNumber = Integer.parseInt(value);
        } catch (final NumberFormatException exception){
            exception.printStackTrace();
            return;
        }

    }
}
