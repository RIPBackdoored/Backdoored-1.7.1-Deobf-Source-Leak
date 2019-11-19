package com.google.api.client.googleapis.batch;

import com.google.api.client.http.LowLevelHttpResponse;
import java.util.List;
import java.io.InputStream;
import com.google.api.client.http.LowLevelHttpRequest;

private static class FakeLowLevelHttpRequest extends LowLevelHttpRequest
{
    private InputStream partContent;
    private int statusCode;
    private List<String> headerNames;
    private List<String> headerValues;
    
    FakeLowLevelHttpRequest(final InputStream partContent, final int statusCode, final List<String> headerNames, final List<String> headerValues) {
        super();
        this.partContent = partContent;
        this.statusCode = statusCode;
        this.headerNames = headerNames;
        this.headerValues = headerValues;
    }
    
    @Override
    public void addHeader(final String s, final String s2) {
    }
    
    @Override
    public LowLevelHttpResponse execute() {
        return new FakeLowLevelHttpResponse(this.partContent, this.statusCode, this.headerNames, this.headerValues);
    }
}
