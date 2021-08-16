package org.rrcat.arpf.ui.di;

import com.google.inject.AbstractModule;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import okhttp3.Authenticator;
import org.dae.arpf.dto.AuthenticationTokenDTO;
import org.rrcat.arpf.ui.api.RetrofitFactory;
import org.rrcat.arpf.ui.api.schema.CustomerApi;
import org.rrcat.arpf.ui.api.schema.UploadApi;
import retrofit2.Retrofit;


public final class AuthenticationModule extends AbstractModule {
    private final Retrofit authenticatedRetrofit;
    private final FXMLLoader loader;

    public AuthenticationModule(final Authenticator authenticator, final FXMLLoader loader) {
        this.authenticatedRetrofit = RetrofitFactory.createAuthenticating(authenticator);
        this.loader = loader;
    }

    @Override
    protected void configure() {
        bind(FXMLLoader.class).toInstance(loader);
        bind(CustomerApi.class).toInstance(authenticatedRetrofit.create(CustomerApi.class));
        bind(UploadApi.class).toInstance(authenticatedRetrofit.create(UploadApi.class));
    }
}
