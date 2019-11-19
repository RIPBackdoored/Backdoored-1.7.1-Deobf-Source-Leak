package com.google.api.client.googleapis.auth.clientlogin;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.Key;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpExecuteInterceptor;

public static final class Response implements HttpExecuteInterceptor, HttpRequestInitializer
{
    @Key("Auth")
    public String auth;
    
    public Response() {
        super();
    }
    
    public String getAuthorizationHeaderValue() {
        return ClientLogin.getAuthorizationHeaderValue(this.auth);
    }
    
    public void initialize(final HttpRequest httpRequest) {
        httpRequest.setInterceptor(this);
    }
    
    public void intercept(final HttpRequest httpRequest) {
        httpRequest.getHeaders().setAuthorization(this.getAuthorizationHeaderValue());
    }
}
