package com.google.api.client.http;

public static final class Part
{
    HttpContent content;
    HttpHeaders headers;
    HttpEncoding encoding;
    
    public Part() {
        this(null);
    }
    
    public Part(final HttpContent httpContent) {
        this(null, httpContent);
    }
    
    public Part(final HttpHeaders headers, final HttpContent content) {
        super();
        this.setHeaders(headers);
        this.setContent(content);
    }
    
    public Part setContent(final HttpContent content) {
        this.content = content;
        return this;
    }
    
    public HttpContent getContent() {
        return this.content;
    }
    
    public Part setHeaders(final HttpHeaders headers) {
        this.headers = headers;
        return this;
    }
    
    public HttpHeaders getHeaders() {
        return this.headers;
    }
    
    public Part setEncoding(final HttpEncoding encoding) {
        this.encoding = encoding;
        return this;
    }
    
    public HttpEncoding getEncoding() {
        return this.encoding;
    }
}
