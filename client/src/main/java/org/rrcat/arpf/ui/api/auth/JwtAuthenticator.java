package org.rrcat.arpf.ui.api.auth;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.dae.arpf.dto.AuthenticationTokenDTO;
import org.dae.arpf.dto.LoginRequestDTO;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.function.Function;

public final class JwtAuthenticator implements Authenticator {
    private static final String BEARER_FORMAT = "Bearer %s";
    private final LoginRequestDTO loginRequestDTO;
    private final Function<LoginRequestDTO, AuthenticationTokenDTO> renewFunction;
    private AuthenticationTokenDTO dto;
    private long authenticationTime;

    public JwtAuthenticator(final LoginRequestDTO loginRequestDTO, final Function<LoginRequestDTO, AuthenticationTokenDTO> renewFunction) {
        this.loginRequestDTO = loginRequestDTO;
        this.renewFunction = renewFunction;
    }

    @Override
    public Request authenticate(final @Nullable Route route, final Response response) {
        if (hasTokenExpired()) {
            this.authenticationTime = System.currentTimeMillis() / 1000;
            this.dto = renewFunction.apply(loginRequestDTO);
        }
        if (dto == null) {
            return null;
        }
        return response.request().newBuilder()
                .header("Authorization", String.format(BEARER_FORMAT, dto.token()))
                .build();
    }

    private boolean hasTokenExpired() {
        return dto == null || (System.currentTimeMillis() - authenticationTime) / 1000 > dto.secondsToExpiration();
    }
}
