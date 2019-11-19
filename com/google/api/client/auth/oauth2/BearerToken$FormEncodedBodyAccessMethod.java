package com.google.api.client.auth.oauth2;

import com.google.api.client.util.Data;
import com.google.api.client.http.UrlEncodedContent;
import java.util.Map;
import java.io.IOException;
import com.google.api.client.util.Preconditions;
import com.google.api.client.http.HttpRequest;

static final class FormEncodedBodyAccessMethod implements Credential.AccessMethod
{
    FormEncodedBodyAccessMethod() {
        super();
    }
    
    @Override
    public void intercept(final HttpRequest httpRequest, final String s) throws IOException {
        Preconditions.checkArgument(!"GET".equals(httpRequest.getRequestMethod()), (Object)"HTTP GET method is not supported");
        getData(httpRequest).put("access_token", s);
    }
    
    @Override
    public String getAccessTokenFromRequest(final HttpRequest httpRequest) {
        final Object value = getData(httpRequest).get("access_token");
        return (value == null) ? null : value.toString();
    }
    
    private static Map<String, Object> getData(final HttpRequest httpRequest) {
        return Data.mapOf(UrlEncodedContent.getContent(httpRequest).getData());
    }
}
