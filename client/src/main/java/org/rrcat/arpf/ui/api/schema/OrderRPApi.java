package org.rrcat.arpf.ui.api.schema;

import org.dae.arpf.dto.OrderRadiationProcessingDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderRPApi {
    @POST("/api/v1/order/rp/register")
    Call<OrderRadiationProcessingDTO> registerORP(@Body final OrderRadiationProcessingDTO orderDTO);

    @GET("/api/v1/order/rp/fetch/{registrationId}")
    Call<OrderRadiationProcessingDTO> fetchORP(@Path("registrationId") final Integer registrationId);
}
