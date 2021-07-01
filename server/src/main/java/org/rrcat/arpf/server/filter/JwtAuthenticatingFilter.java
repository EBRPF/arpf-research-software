package org.rrcat.arpf.server.filter;

import org.rrcat.arpf.server.auth.BearerTokenExtractor;
import org.rrcat.arpf.server.auth.jwt.JwtValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
public final class JwtAuthenticatingFilter extends GenericFilterBean {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtValidator validator;
    private final BearerTokenExtractor tokenExtractor;
    private final UserDetailsService detailsService;

    @Autowired
    public JwtAuthenticatingFilter(final JwtValidator validator, final BearerTokenExtractor tokenExtractor, @Qualifier("RrcatDetails") UserDetailsService detailsService) {
        this.validator = validator;
        this.tokenExtractor = tokenExtractor;
        this.detailsService = detailsService;
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            final String authValue = ((HttpServletRequest) request).getHeader(AUTHORIZATION_HEADER);
            tokenExtractor.extractToken(authValue)
                    .flatMap(validator::getUidIfValid)
                    .map(detailsService::loadUserByUsername)
                    .map(JwtAuthenticatingFilter::createAuthToken)
                    .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
        }
        chain.doFilter(request, response);
    }

    private static UsernamePasswordAuthenticationToken createAuthToken(final UserDetails details) {
        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }
}
