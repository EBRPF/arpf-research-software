package org.rrcat.arpf.ui.api.schema;

import org.dae.arpf.dto.CustomerDTO;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.Collection;

public interface CustomerApi {
    @POST("/api/v1/customer/register")
    Call<CustomerDTO>  register(@Body final CustomerDTO dto);

    @GET("/fetch/{registrationId}")
    Call<CustomerDTO> fetchCustomer(@Path("registrationId") final int registrationNo);

    @GET("/search/{registrationId}")
    Call<Collection<CustomerDTO>> searchCustomer(@Path("registrationId") final String registrationNo);
}
