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

    @GET("/api/v1/customer/fetch/{customerId}")
    Call<CustomerDTO> fetchCustomer(@Path("customerId") final int customerId);

    @GET("/api/v1/customer/fetch/org/{organizationName}")
    Call<CustomerDTO> fetchCustomerByOrganization(@Path("organizationName") final String organizationName);

    @GET("/api/v1/customer/search/{customerId}")
    Call<Collection<CustomerDTO>> searchCustomer(@Path("customerId") final String customerId);

    @GET("/api/v1/customer/search/org/{organizationName}")
    Call<Collection<CustomerDTO>> searchCustomerByOrganization(@Path("organizationName") final String organizationName);
}
