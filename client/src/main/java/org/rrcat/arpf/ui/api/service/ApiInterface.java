package org.rrcat.arpf.ui.api.service;


import org.rrcat.arpf.ui.api.model.RequestLogin;
import org.rrcat.arpf.ui.entity.auth.AuthenticationToken;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
   String URL_BASE= "http://localhost:8080/";
   @POST("api/v1/authenticate")
   Call<AuthenticationToken> LoginAccount(@Body RequestLogin requestLogin);
}

