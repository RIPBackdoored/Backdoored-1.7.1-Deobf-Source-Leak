package com.google.api.client.googleapis.batch;

import com.google.api.client.http.LowLevelHttpRequest;
import java.util.List;
import java.io.InputStream;
import com.google.api.client.http.HttpTransport;

private static class FakeResponseHttpTransport extends HttpTransport
{
    private int statusCode;
    private InputStream partContent;
    private List<String> headerNames;
    private List<String> headerValues;
    
    FakeResponseHttpTransport(final int statusCode, final InputStream partContent, final List<String> headerNames, final List<String> headerValues) {
        super();
        this.statusCode = statusCode;
        this.partContent = partContent;
        this.headerNames = headerNames;
        this.headerValues = headerValues;
    }
    
    @Override
    protected LowLevelHttpRequest buildRequest(final String s, final String s2) {
        return new FakeLowLevelHttpRequest(this.partContent, this.statusCode, this.headerNames, this.headerValues);
    }
}
