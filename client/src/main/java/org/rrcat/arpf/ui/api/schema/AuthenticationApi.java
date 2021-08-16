package org.rrcat.arpf.ui.api.schema;

import org.dae.arpf.dto.AuthenticationTokenDTO;
import org.dae.arpf.dto.LoginRequestDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticationApi {
    @POST("/api/v1/authenticate")
    Call<AuthenticationTokenDTO> authenticate(@Body final LoginRequestDTO dto);
}
