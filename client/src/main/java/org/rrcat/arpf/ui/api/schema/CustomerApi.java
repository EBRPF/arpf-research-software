package org.rrcat.arpf.ui.api.schema;

import org.dae.arpf.dto.CustomerDTO;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.Collection;

public interface CustomerApi {
    @GET("/api/v1/authenticate")
    Response<Void>  register(@Body final CustomerDTO dto);

    @GET("/fetch/{registrationId}")
    Call<CustomerDTO> fetchCustomer(@Path("registrationId") final int registrationNo);

    @GET("/search/{registrationId}")
    Call<Collection<CustomerDTO>> searchCustomer(@Path("registrationId") final String registrationNo);
}
