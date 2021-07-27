package org.rrcat.arpf.ui.api.service;

      
import org.dae.arpf.dto.CustomerDTO;
import org.rrcat.arpf.ui.api.model.RequestLogin;
import org.rrcat.arpf.ui.entity.auth.AuthenticationToken;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
   String URL_BASE= "http://localhost:8080/";
   @POST("api/v1/authenticate")
   Call<AuthenticationToken> LoginAccount(@Body RequestLogin requestLogin);
   @POST("api/v1/customer/register")
   Call<CustomerDTO> RegisterUser(@Header ("Authorization") String token,@Body CustomerDTO CustomerBody);

}


