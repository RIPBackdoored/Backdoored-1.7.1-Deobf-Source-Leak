package com.google.api.client.http;

import java.io.IOException;
import com.google.api.client.util.Preconditions;

public final class BasicAuthentication implements HttpRequestInitializer, HttpExecuteInterceptor
{
    private final String username;
    private final String password;
    
    public BasicAuthentication(final String s, final String s2) {
        super();
        this.username = Preconditions.<String>checkNotNull(s);
        this.password = Preconditions.<String>checkNotNull(s2);
    }
    
    @Override
    public void initialize(final HttpRequest httpRequest) throws IOException {
        httpRequest.setInterceptor(this);
    }
    
    @Override
    public void intercept(final HttpRequest httpRequest) throws IOException {
        httpRequest.getHeaders().setBasicAuthentication(this.username, this.password);
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
}
