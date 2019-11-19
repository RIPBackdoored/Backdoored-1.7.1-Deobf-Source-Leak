package com.google.api.client.http.apache;

import java.io.IOException;
import org.apache.http.HttpEntity;
import java.io.InputStream;
import org.apache.http.StatusLine;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import com.google.api.client.http.LowLevelHttpResponse;

final class ApacheHttpResponse extends LowLevelHttpResponse
{
    private final HttpRequestBase request;
    private final HttpResponse response;
    private final Header[] allHeaders;
    
    ApacheHttpResponse(final HttpRequestBase request, final HttpResponse response) {
        super();
        this.request = request;
        this.response = response;
        this.allHeaders = response.getAllHeaders();
    }
    
    @Override
    public int getStatusCode() {
        final StatusLine statusLine = this.response.getStatusLine();
        return (statusLine == null) ? 0 : statusLine.getStatusCode();
    }
    
    @Override
    public InputStream getContent() throws IOException {
        final HttpEntity entity = this.response.getEntity();
        return (entity == null) ? null : entity.getContent();
    }
    
    @Override
    public String getContentEncoding() {
        final HttpEntity entity = this.response.getEntity();
        if (entity != null) {
            final Header contentEncoding = entity.getContentEncoding();
            if (contentEncoding != null) {
                return contentEncoding.getValue();
            }
        }
        return null;
    }
    
    @Override
    public long getContentLength() {
        final HttpEntity entity = this.response.getEntity();
        return (entity == null) ? -1L : entity.getContentLength();
    }
    
    @Override
    public String getContentType() {
        final HttpEntity entity = this.response.getEntity();
        if (entity != null) {
            final Header contentType = entity.getContentType();
            if (contentType != null) {
                return contentType.getValue();
            }
        }
        return null;
    }
    
    @Override
    public String getReasonPhrase() {
        final StatusLine statusLine = this.response.getStatusLine();
        return (statusLine == null) ? null : statusLine.getReasonPhrase();
    }
    
    @Override
    public String getStatusLine() {
        final StatusLine statusLine = this.response.getStatusLine();
        return (statusLine == null) ? null : statusLine.toString();
    }
    
    public String getHeaderValue(final String s) {
        return this.response.getLastHeader(s).getValue();
    }
    
    @Override
    public int getHeaderCount() {
        return this.allHeaders.length;
    }
    
    @Override
    public String getHeaderName(final int n) {
        return this.allHeaders[n].getName();
    }
    
    @Override
    public String getHeaderValue(final int n) {
        return this.allHeaders[n].getValue();
    }
    
    @Override
    public void disconnect() {
        this.request.abort();
    }
}
