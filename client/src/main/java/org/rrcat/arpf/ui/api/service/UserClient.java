package org.rrcat.arpf.ui.api.service;

import org.rrcat.arpf.ui.entity.RequestLogin;
import org.rrcat.arpf.ui.entity.auth.AuthenticationToken;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {
    @POST("api/v1/authenticate")
    Call<AuthenticationToken> LoginAccount(@Body RequestLogin requestLogin);

}
