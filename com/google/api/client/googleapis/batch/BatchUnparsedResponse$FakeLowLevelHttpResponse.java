package com.google.api.client.googleapis.batch;

import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import com.google.api.client.http.LowLevelHttpResponse;

private static class FakeLowLevelHttpResponse extends LowLevelHttpResponse
{
    private InputStream partContent;
    private int statusCode;
    private List<String> headerNames;
    private List<String> headerValues;
    
    FakeLowLevelHttpResponse(final InputStream partContent, final int statusCode, final List<String> headerNames, final List<String> headerValues) {
        super();
        this.headerNames = new ArrayList<String>();
        this.headerValues = new ArrayList<String>();
        this.partContent = partContent;
        this.statusCode = statusCode;
        this.headerNames = headerNames;
        this.headerValues = headerValues;
    }
    
    @Override
    public InputStream getContent() {
        return this.partContent;
    }
    
    @Override
    public int getStatusCode() {
        return this.statusCode;
    }
    
    @Override
    public String getContentEncoding() {
        return null;
    }
    
    @Override
    public long getContentLength() {
        return 0L;
    }
    
    @Override
    public String getContentType() {
        return null;
    }
    
    @Override
    public String getStatusLine() {
        return null;
    }
    
    @Override
    public String getReasonPhrase() {
        return null;
    }
    
    @Override
    public int getHeaderCount() {
        return this.headerNames.size();
    }
    
    @Override
    public String getHeaderName(final int n) {
        return this.headerNames.get(n);
    }
    
    @Override
    public String getHeaderValue(final int n) {
        return this.headerValues.get(n);
    }
}
