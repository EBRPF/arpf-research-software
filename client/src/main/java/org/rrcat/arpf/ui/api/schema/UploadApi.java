package org.rrcat.arpf.ui.api.schema;

import okhttp3.RequestBody;
import org.dae.arpf.dto.UploadedImageDTO;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UploadApi {
    @Multipart
    @POST("/upload/{dir}")
    Call<UploadedImageDTO> upload(@Path("dir") final String dir, @Part("file") RequestBody filePart);
}
