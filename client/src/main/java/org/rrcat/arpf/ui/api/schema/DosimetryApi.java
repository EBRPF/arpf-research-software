package org.rrcat.arpf.ui.api.schema;

import org.dae.arpf.dto.DosimetryDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DosimetryApi {
    @POST("/api/v1/dosimetry/register")
    Call<DosimetryDTO> registerDosimetry(@Body final DosimetryDTO dosimetryDTO);

    @GET("/api/v1/dosimetry/fetch/{registrationId}")
    Call<DosimetryDTO> fetchDosimetry(@Path("registrationId") final Integer registrationId);
}
