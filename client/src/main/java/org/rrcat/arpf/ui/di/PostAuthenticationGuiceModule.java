package org.rrcat.arpf.ui.di;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Window;
import okhttp3.Authenticator;
import org.dae.arpf.dto.AuthenticationTokenDTO;
import org.rrcat.arpf.ui.api.RetrofitFactory;
import org.rrcat.arpf.ui.api.schema.*;
import org.rrcat.arpf.ui.di.annotations.AlertingExceptionConsumer;
import org.rrcat.arpf.ui.di.annotations.ImageFileSupplier;
import org.rrcat.arpf.ui.service.ImageUploadService;
import org.rrcat.arpf.ui.service.RetrofitImageUploadService;
import org.rrcat.arpf.ui.upload.AlertingUploadExceptionHandler;
import org.rrcat.arpf.ui.upload.UploadFileSupplier;
import retrofit2.Retrofit;

import java.io.File;
import java.util.function.Consumer;
import java.util.function.Supplier;


public final class PostAuthenticationGuiceModule extends AbstractModule {
    private final Retrofit authenticatedRetrofit;
    private final FXMLLoader loader;
    private final Window window;

    public PostAuthenticationGuiceModule(final Authenticator authenticator, final FXMLLoader loader, final Window window) {
        this.authenticatedRetrofit = RetrofitFactory.createAuthenticating(authenticator);
        this.loader = loader;
        this.window = window;
    }

    @Override
    protected void configure() {
        bind(Window.class).toInstance(window);
        bind(new TypeLiteral<Consumer<Throwable>>(){}).annotatedWith(AlertingExceptionConsumer.class).to(AlertingUploadExceptionHandler.class);
        bind(new TypeLiteral<Supplier<File>>(){}).annotatedWith(ImageFileSupplier.class).to(UploadFileSupplier.class);
        bind(FXMLLoader.class).toInstance(loader);
        bind(CustomerApi.class).toInstance(authenticatedRetrofit.create(CustomerApi.class));
        bind(UploadApi.class).toInstance(authenticatedRetrofit.create(UploadApi.class));
        bind(OrderApi.class).toInstance(authenticatedRetrofit.create(OrderApi.class));
        bind(OrderRPApi.class).toInstance(authenticatedRetrofit.create(OrderRPApi.class));
        bind(DosimetryApi.class).toInstance(authenticatedRetrofit.create(DosimetryApi.class));
        bind(ShippingDetailsApi.class).toInstance(authenticatedRetrofit.create(ShippingDetailsApi.class));
        bind(ImageUploadService.class).to(RetrofitImageUploadService.class);
    }
}
