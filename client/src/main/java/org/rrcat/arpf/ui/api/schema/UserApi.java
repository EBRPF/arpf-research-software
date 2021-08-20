package org.rrcat.arpf.ui.api.schema;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("/api/v1/user/register")
    Call<Void> registerUser(@Body final UserDTO userDTO);

    @POST("/api/v1/user/update/role")
    Call<Void> setRole(@Body final UserDTO userDTO);

    @POST("/api/v1/user/update/enabled")
    Call<Void> setEnabled(@Body final UserDTO userDTO);

    @POST("/api/v1/user/update/password")
    Call<Void> setPassword(@Body final UserDTO userDTO);
}
