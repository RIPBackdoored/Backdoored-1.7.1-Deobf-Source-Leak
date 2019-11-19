package com.google.api.client.http;

import java.io.IOException;
import com.google.api.client.util.StreamingContent;

public abstract class LowLevelHttpRequest
{
    private long contentLength;
    private String contentEncoding;
    private String contentType;
    private StreamingContent streamingContent;
    
    public LowLevelHttpRequest() {
        super();
        this.contentLength = -1L;
    }
    
    public abstract void addHeader(final String p0, final String p1) throws IOException;
    
    public final void setContentLength(final long contentLength) throws IOException {
        this.contentLength = contentLength;
    }
    
    public final long getContentLength() {
        return this.contentLength;
    }
    
    public final void setContentEncoding(final String contentEncoding) throws IOException {
        this.contentEncoding = contentEncoding;
    }
    
    public final String getContentEncoding() {
        return this.contentEncoding;
    }
    
    public final void setContentType(final String contentType) throws IOException {
        this.contentType = contentType;
    }
    
    public final String getContentType() {
        return this.contentType;
    }
    
    public final void setStreamingContent(final StreamingContent streamingContent) throws IOException {
        this.streamingContent = streamingContent;
    }
    
    public final StreamingContent getStreamingContent() {
        return this.streamingContent;
    }
    
    public void setTimeout(final int n, final int n2) throws IOException {
    }
    
    public abstract LowLevelHttpResponse execute() throws IOException;
}
