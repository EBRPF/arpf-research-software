package org.rrcat.arpf.ui.api.schema;

import org.dae.arpf.dto.OrderDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderApi {
    @POST("/api/v1/order/register")
    Call<OrderDTO> registerOrder(@Body final OrderDTO dto);

    @GET("/api/v1/order/fetch/{registrationId}")
    Call<OrderDTO> fetchOrder(@Path("registrationId") final String registrationId);
}
