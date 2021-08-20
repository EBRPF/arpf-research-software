package org.rrcat.arpf.ui.service;

import javafx.application.Platform;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.dae.arpf.dto.UploadedImageDTO;
import org.rrcat.arpf.ui.api.schema.UploadApi;
import org.rrcat.arpf.ui.constants.UploadDirectory;
import retrofit2.Call;
import retrofit2.Response;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class RetrofitImageUploadService implements ImageUploadService {
    private final UploadApi uploadApi;

    @Inject
    public RetrofitImageUploadService(final UploadApi uploadApi) {
        this.uploadApi = uploadApi;
    }

    @Override
    public UploadedImageDTO upload(final File file) {
        final RequestBody fileBody =  RequestBody.create(MediaType.parse("image/*"), file);
        final MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileBody);
        final Call<UploadedImageDTO> uploadCall = uploadApi.upload(UploadDirectory.REGISTRATION, part);
        final Response<UploadedImageDTO> response;
        try {
            response = uploadCall.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Objects.requireNonNull(response.body(), "Upload failed unexpectedly!");
    }
}
