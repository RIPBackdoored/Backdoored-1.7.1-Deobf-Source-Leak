package com.google.api.client.googleapis;

import java.io.IOException;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpExecuteInterceptor;

public final class MethodOverride implements HttpExecuteInterceptor, HttpRequestInitializer
{
    public static final String HEADER = "X-HTTP-Method-Override";
    static final int MAX_URL_LENGTH = 2048;
    private final boolean overrideAllMethods;
    
    public MethodOverride() {
        this(false);
    }
    
    MethodOverride(final boolean overrideAllMethods) {
        super();
        this.overrideAllMethods = overrideAllMethods;
    }
    
    public void initialize(final HttpRequest httpRequest) {
        httpRequest.setInterceptor(this);
    }
    
    public void intercept(final HttpRequest httpRequest) throws IOException {
        if (this.overrideThisMethod(httpRequest)) {
            final String requestMethod = httpRequest.getRequestMethod();
            httpRequest.setRequestMethod("POST");
            httpRequest.getHeaders().set("X-HTTP-Method-Override", requestMethod);
            if (requestMethod.equals("GET")) {
                httpRequest.setContent(new UrlEncodedContent(httpRequest.getUrl().clone()));
                httpRequest.getUrl().clear();
            }
            else if (httpRequest.getContent() == null) {
                httpRequest.setContent(new EmptyContent());
            }
        }
    }
    
    private boolean overrideThisMethod(final HttpRequest httpRequest) throws IOException {
        final String requestMethod = httpRequest.getRequestMethod();
        if (requestMethod.equals("POST")) {
            return false;
        }
        if (requestMethod.equals("GET")) {
            if (httpRequest.getUrl().build().length() <= 2048) {
                return !httpRequest.getTransport().supportsMethod(requestMethod);
            }
        }
        else if (!this.overrideAllMethods) {
            return !httpRequest.getTransport().supportsMethod(requestMethod);
        }
        return true;
    }
}
