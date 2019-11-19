package com.google.api.client.http;

import java.io.IOException;

public final class HttpRequestFactory
{
    private final HttpTransport transport;
    private final HttpRequestInitializer initializer;
    
    HttpRequestFactory(final HttpTransport transport, final HttpRequestInitializer initializer) {
        super();
        this.transport = transport;
        this.initializer = initializer;
    }
    
    public HttpTransport getTransport() {
        return this.transport;
    }
    
    public HttpRequestInitializer getInitializer() {
        return this.initializer;
    }
    
    public HttpRequest buildRequest(final String requestMethod, final GenericUrl url, final HttpContent content) throws IOException {
        final HttpRequest buildRequest = this.transport.buildRequest();
        if (this.initializer != null) {
            this.initializer.initialize(buildRequest);
        }
        buildRequest.setRequestMethod(requestMethod);
        if (url != null) {
            buildRequest.setUrl(url);
        }
        if (content != null) {
            buildRequest.setContent(content);
        }
        return buildRequest;
    }
    
    public HttpRequest buildDeleteRequest(final GenericUrl genericUrl) throws IOException {
        return this.buildRequest("DELETE", genericUrl, null);
    }
    
    public HttpRequest buildGetRequest(final GenericUrl genericUrl) throws IOException {
        return this.buildRequest("GET", genericUrl, null);
    }
    
    public HttpRequest buildPostRequest(final GenericUrl genericUrl, final HttpContent httpContent) throws IOException {
        return this.buildRequest("POST", genericUrl, httpContent);
    }
    
    public HttpRequest buildPutRequest(final GenericUrl genericUrl, final HttpContent httpContent) throws IOException {
        return this.buildRequest("PUT", genericUrl, httpContent);
    }
    
    public HttpRequest buildPatchRequest(final GenericUrl genericUrl, final HttpContent httpContent) throws IOException {
        return this.buildRequest("PATCH", genericUrl, httpContent);
    }
    
    public HttpRequest buildHeadRequest(final GenericUrl genericUrl) throws IOException {
        return this.buildRequest("HEAD", genericUrl, null);
    }
}
