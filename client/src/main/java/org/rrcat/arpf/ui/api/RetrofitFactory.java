package org.rrcat.arpf.ui.api;

import com.google.gson.GsonBuilder;
import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.dae.arpf.dto.LoginRequestDTO;
import org.rrcat.arpf.ui.api.auth.JwtAuthenticator;
import org.rrcat.arpf.ui.api.schema.AuthenticationApi;
import org.rrcat.arpf.ui.api.service.ApiInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitFactory {
    private RetrofitFactory() {

    }

    public static Retrofit create() {
        return new Retrofit.Builder()
                .baseUrl(ApiInterface.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
    }

    public static Retrofit createAuthenticating(final LoginRequestDTO dto, final AuthenticationApi authenticationApi) {
        return new Retrofit.Builder()
                .baseUrl(ApiInterface.URL_BASE)
                .client(createAuthenticatingClient(new JwtAuthenticator(dto, authenticationApi::authenticate)))
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
