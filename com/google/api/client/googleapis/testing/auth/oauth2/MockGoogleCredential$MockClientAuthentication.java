package com.google.api.client.googleapis.testing.auth.oauth2;

import java.io.IOException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.Beta;
import com.google.api.client.http.HttpExecuteInterceptor;

@Beta
private static class MockClientAuthentication implements HttpExecuteInterceptor
{
    private MockClientAuthentication() {
        super();
    }
    
    public void intercept(final HttpRequest httpRequest) throws IOException {
    }
    
    MockClientAuthentication(final MockGoogleCredential$1 object) {
        this();
    }
}
