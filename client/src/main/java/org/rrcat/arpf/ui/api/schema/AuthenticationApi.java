package org.rrcat.arpf.ui.api.schema;

import org.dae.arpf.dto.AuthenticationTokenDTO;
import org.dae.arpf.dto.LoginRequestDTO;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface AuthenticationApi {
    @GET("/api/v1/authenticate")
    AuthenticationTokenDTO authenticate(@Body final LoginRequestDTO dto);
}
