package com.google.api.client.auth.oauth2;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import com.google.api.client.http.HttpRequest;

static final class AuthorizationHeaderAccessMethod implements Credential.AccessMethod
{
    static final String HEADER_PREFIX = "Bearer ";
    
    AuthorizationHeaderAccessMethod() {
        super();
    }
    
    @Override
    public void intercept(final HttpRequest httpRequest, final String s) throws IOException {
        httpRequest.getHeaders().setAuthorization("Bearer " + s);
    }
    
    @Override
    public String getAccessTokenFromRequest(final HttpRequest httpRequest) {
        final List<String> authorizationAsList = httpRequest.getHeaders().getAuthorizationAsList();
        if (authorizationAsList != null) {
            for (final String s : authorizationAsList) {
                if (s.startsWith("Bearer ")) {
                    return s.substring("Bearer ".length());
                }
            }
        }
        return null;
    }
}
