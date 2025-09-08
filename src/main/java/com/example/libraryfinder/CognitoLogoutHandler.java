package com.example.libraryfinder.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * Cognito has a custom logout URL. This handler implements the custom logout logic
 * by building the Cognito logout URL with the client ID and logout URI parameters.
 */
public class CognitoLogoutHandler extends SimpleUrlLogoutSuccessHandler {

    private final String domain;
    private final String userPoolClientId;
    private final String logoutRedirectUrl;

    public CognitoLogoutHandler(String domain, String userPoolClientId, String logoutRedirectUrl) {
        this.domain = domain;
        this.userPoolClientId = userPoolClientId;
        this.logoutRedirectUrl = logoutRedirectUrl;
    }

    /**
     * Overrides the default logout behavior to redirect the user to the Cognito logout endpoint.
     */
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        return UriComponentsBuilder
                .fromUri(URI.create(domain + "/logout"))
                .queryParam("client_id", userPoolClientId)
                .queryParam("logout_uri", logoutRedirectUrl)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUriString();
    }
}

