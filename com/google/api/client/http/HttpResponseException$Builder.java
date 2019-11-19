package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import java.io.IOException;

public static class Builder
{
    int statusCode;
    String statusMessage;
    HttpHeaders headers;
    String content;
    String message;
    
    public Builder(final int statusCode, final String statusMessage, final HttpHeaders headers) {
        super();
        this.setStatusCode(statusCode);
        this.setStatusMessage(statusMessage);
        this.setHeaders(headers);
    }
    
    public Builder(final HttpResponse httpResponse) {
        this(httpResponse.getStatusCode(), httpResponse.getStatusMessage(), httpResponse.getHeaders());
        try {
            this.content = httpResponse.parseAsString();
            if (this.content.length() == 0) {
                this.content = null;
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        final StringBuilder computeMessageBuffer = HttpResponseException.computeMessageBuffer(httpResponse);
        if (this.content != null) {
            computeMessageBuffer.append(StringUtils.LINE_SEPARATOR).append(this.content);
        }
        this.message = computeMessageBuffer.toString();
    }
    
    public final String getMessage() {
        return this.message;
    }
    
    public Builder setMessage(final String message) {
        this.message = message;
        return this;
    }
    
    public final int getStatusCode() {
        return this.statusCode;
    }
    
    public Builder setStatusCode(final int statusCode) {
        Preconditions.checkArgument(statusCode >= 0);
        this.statusCode = statusCode;
        return this;
    }
    
    public final String getStatusMessage() {
        return this.statusMessage;
    }
    
    public Builder setStatusMessage(final String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
    }
    
    public HttpHeaders getHeaders() {
        return this.headers;
    }
    
    public Builder setHeaders(final HttpHeaders httpHeaders) {
        this.headers = Preconditions.<HttpHeaders>checkNotNull(httpHeaders);
        return this;
    }
    
    public final String getContent() {
        return this.content;
    }
    
    public Builder setContent(final String content) {
        this.content = content;
        return this;
    }
    
    public HttpResponseException build() {
        return new HttpResponseException(this);
    }
}
