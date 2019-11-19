package com.google.api.client.testing.http;

import java.io.IOException;
import com.google.api.client.testing.util.TestableByteArrayInputStream;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import com.google.api.client.util.Beta;
import com.google.api.client.http.LowLevelHttpResponse;

@Beta
public class MockLowLevelHttpResponse extends LowLevelHttpResponse
{
    private InputStream content;
    private String contentType;
    private int statusCode;
    private String reasonPhrase;
    private List<String> headerNames;
    private List<String> headerValues;
    private String contentEncoding;
    private long contentLength;
    private boolean isDisconnected;
    
    public MockLowLevelHttpResponse() {
        super();
        this.statusCode = 200;
        this.headerNames = new ArrayList<String>();
        this.headerValues = new ArrayList<String>();
        this.contentLength = -1L;
    }
    
    public MockLowLevelHttpResponse addHeader(final String s, final String s2) {
        this.headerNames.add(Preconditions.<String>checkNotNull(s));
        this.headerValues.add(Preconditions.<String>checkNotNull(s2));
        return this;
    }
    
    public MockLowLevelHttpResponse setContent(final String s) {
        return (s == null) ? this.setZeroContent() : this.setContent(StringUtils.getBytesUtf8(s));
    }
    
    public MockLowLevelHttpResponse setContent(final byte[] array) {
        if (array == null) {
            return this.setZeroContent();
        }
        this.content = new TestableByteArrayInputStream(array);
        this.setContentLength(array.length);
        return this;
    }
    
    public MockLowLevelHttpResponse setZeroContent() {
        this.content = null;
        this.setContentLength(0L);
        return this;
    }
    
    @Override
    public InputStream getContent() throws IOException {
        return this.content;
    }
    
    @Override
    public String getContentEncoding() {
        return this.contentEncoding;
    }
    
    @Override
    public long getContentLength() {
        return this.contentLength;
    }
    
    @Override
    public final String getContentType() {
        return this.contentType;
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
    
    @Override
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
    
    @Override
    public int getStatusCode() {
        return this.statusCode;
    }
    
    @Override
    public String getStatusLine() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.statusCode);
        if (this.reasonPhrase != null) {
            sb.append(this.reasonPhrase);
        }
        return sb.toString();
    }
    
    public final List<String> getHeaderNames() {
        return this.headerNames;
    }
    
    public MockLowLevelHttpResponse setHeaderNames(final List<String> list) {
        this.headerNames = Preconditions.<List<String>>checkNotNull(list);
        return this;
    }
    
    public final List<String> getHeaderValues() {
        return this.headerValues;
    }
    
    public MockLowLevelHttpResponse setHeaderValues(final List<String> list) {
        this.headerValues = Preconditions.<List<String>>checkNotNull(list);
        return this;
    }
    
    public MockLowLevelHttpResponse setContent(final InputStream content) {
        this.content = content;
        return this;
    }
    
    public MockLowLevelHttpResponse setContentType(final String contentType) {
        this.contentType = contentType;
        return this;
    }
    
    public MockLowLevelHttpResponse setContentEncoding(final String contentEncoding) {
        this.contentEncoding = contentEncoding;
        return this;
    }
    
    public MockLowLevelHttpResponse setContentLength(final long contentLength) {
        this.contentLength = contentLength;
        Preconditions.checkArgument(contentLength >= -1L);
        return this;
    }
    
    public MockLowLevelHttpResponse setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
        return this;
    }
    
    public MockLowLevelHttpResponse setReasonPhrase(final String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
        return this;
    }
    
    @Override
    public void disconnect() throws IOException {
        this.isDisconnected = true;
        super.disconnect();
    }
    
    public boolean isDisconnected() {
        return this.isDisconnected;
    }
}
