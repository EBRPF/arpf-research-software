package org.rrcat.arpf.ui.api;

import com.google.gson.GsonBuilder;
import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.dae.arpf.dto.LoginRequestDTO;
import org.rrcat.arpf.ui.api.service.ApiInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitFactory {
    private static final String BASE_URL = "http://localhost:8080/";
    private RetrofitFactory() {

    }

    public static Retrofit create() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
    }

    public static Retrofit createAuthenticating(final LoginRequestDTO dto, final Authenticator authenticator) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createAuthenticatingClient(authenticator))
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
    }

    private static OkHttpClient createAuthenticatingClient(final Authenticator authenticator) {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    final Request.Builder requestBuilder = chain.request().newBuilder();
                    requestBuilder.addHeader("Accept", "application/json");
                    final Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .authenticator(authenticator).build();
    }
}
