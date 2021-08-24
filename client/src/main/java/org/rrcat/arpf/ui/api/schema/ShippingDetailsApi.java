package org.rrcat.arpf.ui.api.schema;

        import org.dae.arpf.dto.ShippingDetailsDTO;
        import retrofit2.Call;
        import retrofit2.http.Body;
        import retrofit2.http.GET;
        import retrofit2.http.POST;
        import retrofit2.http.Path;

public interface ShippingDetailsApi {
    @POST("/api/v1/shipping/register")
    Call<Void> registerShippingDetails(@Body final ShippingDetailsDTO shippingDTO);

    @GET("/api/v1/shipping/fetch/{registrationId}")
    Call<ShippingDetailsDTO> fetchShippingDetails(@Path("registrationId") final String registrationId);
}


