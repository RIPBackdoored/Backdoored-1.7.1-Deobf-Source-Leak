package com.google.api.client.auth.oauth2;

import java.io.IOException;
import com.google.api.client.http.HttpRequest;

static final class QueryParameterAccessMethod implements Credential.AccessMethod
{
    QueryParameterAccessMethod() {
        super();
    }
    
    @Override
    public void intercept(final HttpRequest httpRequest, final String s) throws IOException {
        httpRequest.getUrl().set("access_token", s);
    }
    
    @Override
    public String getAccessTokenFromRequest(final HttpRequest httpRequest) {
        final Object value = httpRequest.getUrl().get("access_token");
        return (value == null) ? null : value.toString();
    }
}
