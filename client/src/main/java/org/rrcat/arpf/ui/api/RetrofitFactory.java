package org.rrcat.arpf.ui.api;

import com.google.gson.GsonBuilder;
import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public final class RetrofitFactory {
    private static final String BASE_URL = "http://localhost:8080/";
    private RetrofitFactory() {

    }

    public static Retrofit create() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public static Retrofit createAuthenticating(final Authenticator authenticator) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createAuthenticatingClient(authenticator))
                .addConverterFactory(JacksonConverterFactory.create())
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
