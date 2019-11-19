package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import java.io.IOException;

public class HttpResponseException extends IOException
{
    private static final long serialVersionUID = -1875819453475890043L;
    private final int statusCode;
    private final String statusMessage;
    private final transient HttpHeaders headers;
    private final String content;
    
    public HttpResponseException(final HttpResponse httpResponse) {
        this(new Builder(httpResponse));
    }
    
    protected HttpResponseException(final Builder builder) {
        super(builder.message);
        this.statusCode = builder.statusCode;
        this.statusMessage = builder.statusMessage;
        this.headers = builder.headers;
        this.content = builder.content;
    }
    
    public final boolean isSuccessStatusCode() {
        return HttpStatusCodes.isSuccess(this.statusCode);
    }
    
    public final int getStatusCode() {
        return this.statusCode;
    }
    
    public final String getStatusMessage() {
        return this.statusMessage;
    }
    
    public HttpHeaders getHeaders() {
        return this.headers;
    }
    
    public final String getContent() {
        return this.content;
    }
    
    public static StringBuilder computeMessageBuffer(final HttpResponse httpResponse) {
        final StringBuilder sb = new StringBuilder();
        final int statusCode = httpResponse.getStatusCode();
        if (statusCode != 0) {
            sb.append(statusCode);
        }
        final String statusMessage = httpResponse.getStatusMessage();
        if (statusMessage != null) {
            if (statusCode != 0) {
                sb.append(' ');
            }
            sb.append(statusMessage);
        }
        return sb;
    }
}
