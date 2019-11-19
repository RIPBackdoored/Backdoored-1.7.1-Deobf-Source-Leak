package com.google.api.client.auth.oauth2;

import java.util.Map;
import com.google.api.client.util.Data;
import com.google.api.client.http.UrlEncodedContent;
import java.io.IOException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.Preconditions;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequestInitializer;

public class ClientParametersAuthentication implements HttpRequestInitializer, HttpExecuteInterceptor
{
    private final String clientId;
    private final String clientSecret;
    
    public ClientParametersAuthentication(final String s, final String clientSecret) {
        super();
        this.clientId = Preconditions.<String>checkNotNull(s);
        this.clientSecret = clientSecret;
    }
    
    @Override
    public void initialize(final HttpRequest httpRequest) throws IOException {
        httpRequest.setInterceptor(this);
    }
    
    @Override
    public void intercept(final HttpRequest httpRequest) throws IOException {
        final Map<String, Object> map = Data.mapOf(UrlEncodedContent.getContent(httpRequest).getData());
        map.put("client_id", this.clientId);
        if (this.clientSecret != null) {
            map.put("client_secret", this.clientSecret);
        }
    }
    
    public final String getClientId() {
        return this.clientId;
    }
    
    public final String getClientSecret() {
        return this.clientSecret;
    }
}
