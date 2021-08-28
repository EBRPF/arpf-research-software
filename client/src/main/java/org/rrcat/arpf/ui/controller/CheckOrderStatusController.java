package org.rrcat.arpf.ui.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.dae.arpf.dto.*;
import org.rrcat.arpf.ui.api.schema.*;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CheckOrderStatusController implements Initializable {
    private static final String PREFIX = "ARPF-Research-Order";
    @FXML
    private TextField orderNumber;
    @FXML
    private Label organizationName;
    @FXML
    private Label productDescription;
    @FXML
    private Label irradiationPurpose;
    @FXML
    private Label irradiationMode;
    @FXML
    private CheckBox orderConfirmed;
    @FXML
    private Label irradiationProcessed;
    @FXML
    private Label dosimetryDone;
    @FXML
    private Label shippingDone;
    @FXML
    private Label dateOfOrder;
    @FXML
    private Label radProcessingDate;
    @FXML
    private Label dosimetryDate;
    @FXML
    private Label shippedDate;


    private OrderApi orderApi;
    private CustomerApi customerApi;
    private OrderRPApi orderRPApi;
    private ShippingDetailsApi shippingDetailsApi;
    private DosimetryApi dosimetryApi;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderNumber.focusedProperty().addListener((value, oldVal, newVal) -> {
            if (!newVal) onFocusExit();
        });
    }

    private void onFocusExit() {

        final String reg = orderNumber.getText();
        if (!reg.startsWith(PREFIX)) {
            return;
        }
        final String value = reg.substring(PREFIX.length());
        final int regNumber;
        try {
            regNumber = Integer.parseInt(value);
        } catch (final NumberFormatException exception) {
            exception.printStackTrace();
            return;
        }

        CompletableFuture.completedFuture(new OrderStatusModel()).thenApplyAsync(model -> {
            final Call<OrderDTO> dtoCall = orderApi.fetchOrder(regNumber);
            try {
                final Response<OrderDTO> response = dtoCall.execute();
                final OrderDTO orderDTO = response.body();
                model.setOrderDTO(orderDTO);
            } catch (final IOException exception) {
                exception.printStackTrace();
            }
            return model;
        }).thenApplyAsync((model) -> {
            if (model.getOrderDTO() != null) {
                final int orderId = regNumber;
                final int customerId = model.getOrderDTO().customerId();
                final CompletableFuture<CustomerDTO> customerDTOCompletableFuture = CompletableFuture.supplyAsync(() -> {
                    final Call<CustomerDTO> dtoCall = customerApi.fetchCustomer(customerId);
                    try {
                        final Response<CustomerDTO> response = dtoCall.execute();
                        model.setCustomerDTO(response.body());
                        return response.body();
                    } catch (final IOException exception) {
                        exception.printStackTrace();
                    }
                    return null;
                });
                final CompletableFuture<OrderRadiationProcessingDTO> radiationProcessingDTOCompletableFuture = CompletableFuture.supplyAsync(() -> {
                    final Call<OrderRadiationProcessingDTO> dtoCall = orderRPApi.fetchORP(orderId);
                    try {
                        final Response<OrderRadiationProcessingDTO> response = dtoCall.execute();
                        model.setRadiationProcessingDTO(response.body());
                        return response.body();
                    } catch (final IOException exception) {
                        exception.printStackTrace();
                    }
                    return null;
                });
                final CompletableFuture<ShippingDetailsDTO> shippingDetailsDTOCompletableFuture = CompletableFuture.supplyAsync(() -> {
                    final Call<ShippingDetailsDTO> dtoCall = shippingDetailsApi.fetchShippingDetails(orderId);
                    try {
                        final Response<ShippingDetailsDTO> response = dtoCall.execute();
                        model.setShippingDetailsDTO(response.body());
                        return response.body();
                    } catch (final IOException exception) {
                        exception.printStackTrace();
                    }
                    return null;
                });
                final CompletableFuture<DosimetryDTO> dosimetryDTOCompletableFuture = CompletableFuture.supplyAsync(() -> {
                    final Call<DosimetryDTO> dtoCall = dosimetryApi.fetchDosimetry(orderId);
                    try {
                        final Response<DosimetryDTO> response = dtoCall.execute();
                        model.setDosimetryDTO(response.body());
                        return response.body();
                    } catch (final IOException exception) {
                        exception.printStackTrace();
                    }
                    return null;
                });
                try {
                    CompletableFuture.allOf(
                            customerDTOCompletableFuture,
                            radiationProcessingDTOCompletableFuture,
                            shippingDetailsDTOCompletableFuture,
                            dosimetryDTOCompletableFuture
                    ).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            return model;
        }).thenAccept(model -> {
            Platform.runLater(() -> {
                updateForm(model.getOrderDTO(), model.getCustomerDTO(), model.getRadiationProcessingDTO(), model.getDosimetryDTO(), model.getShippingDetailsDTO());
            });
        });
    }

    private void updateForm(final OrderDTO orderDTO, final CustomerDTO customerDTO, final OrderRadiationProcessingDTO radiationProcessingDTO, final DosimetryDTO dosimetryDTO, final ShippingDetailsDTO shippingDetailsDTO) {
        final DateFormat dateFormat = DateFormat.getDateInstance();
        if (customerDTO != null) {
            organizationName.setText(customerDTO.organization().name());
        } else {
            organizationName.setText("-");
        }
        if (orderDTO != null) {
            productDescription.setText(orderDTO.productDescription());
            irradiationPurpose.setText(orderDTO.irradiationPurpose());
            irradiationMode.setText(orderDTO.irradiationMode());
            orderConfirmed.setSelected(orderDTO.registered());
        } else {
            productDescription.setText("-");
            irradiationPurpose.setText("-");
            irradiationMode.setText("-");
            orderConfirmed.setSelected(false);
        }
        if (radiationProcessingDTO != null) {
            irradiationProcessed.setText("Yes");
            radProcessingDate.setText(dateFormat.format(radiationProcessingDTO.processingDate()));
        } else {
            irradiationProcessed.setText("No");
            radProcessingDate.setText("-");
        }
        if (dosimetryDTO != null) {
            dosimetryDone.setText("Yes");
            dosimetryDate.setText(dateFormat.format(dosimetryDTO.measurementDate()));
        } else {
            dosimetryDone.setText("No");
            dosimetryDate.setText("-");
        }
        if (shippingDetailsDTO != null) {
            dateOfOrder.setText(dateFormat.format(shippingDetailsDTO.shippingDate()));
            shippedDate.setText(dateFormat.format(shippingDetailsDTO.shippingDate()));
            shippingDone.setText("Yes");
        } else {
            dateOfOrder.setText("-");
            shippedDate.setText("-");
            shippingDone.setText("No");
        }

    }

    private static final class OrderStatusModel {
        private OrderDTO orderDTO;
        private CustomerDTO customerDTO;
        private OrderRadiationProcessingDTO radiationProcessingDTO;
        private DosimetryDTO dosimetryDTO;
        private ShippingDetailsDTO shippingDetailsDTO;

        public ShippingDetailsDTO getShippingDetailsDTO() {
            return shippingDetailsDTO;
        }

        public void setShippingDetailsDTO(ShippingDetailsDTO shippingDetailsDTO) {
            this.shippingDetailsDTO = shippingDetailsDTO;
        }

        public OrderDTO getOrderDTO() {
            return orderDTO;
        }

        public void setOrderDTO(OrderDTO orderDTO) {
            this.orderDTO = orderDTO;
        }

        public CustomerDTO getCustomerDTO() {
            return customerDTO;
        }

        public void setCustomerDTO(CustomerDTO customerDTO) {
            this.customerDTO = customerDTO;
        }

        public OrderRadiationProcessingDTO getRadiationProcessingDTO() {
            return radiationProcessingDTO;
        }

        public void setRadiationProcessingDTO(OrderRadiationProcessingDTO radiationProcessingDTO) {
            this.radiationProcessingDTO = radiationProcessingDTO;
        }

        public DosimetryDTO getDosimetryDTO() {
            return dosimetryDTO;
        }

        public void setDosimetryDTO(DosimetryDTO dosimetryDTO) {
            this.dosimetryDTO = dosimetryDTO;
        }
    }
}
