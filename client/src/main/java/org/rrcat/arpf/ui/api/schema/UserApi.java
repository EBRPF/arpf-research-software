package org.rrcat.arpf.ui.api.schema;

import org.dae.arpf.dto.UserDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("/api/v1/user/register")
    Call<UserDTO> registerUser(@Body final UserDTO userDTO);

    @POST("/api/v1/user/update/role")
    Call<Void> setRole(@Body final UserDTO userDTO);

    @POST("/api/v1/user/update/enabled")
    Call<Void> setEnabled(@Body final UserDTO userDTO);

    @POST("/api/v1/user/update/password")
    Call<Void> setPassword(@Body final UserDTO userDTO);
}
