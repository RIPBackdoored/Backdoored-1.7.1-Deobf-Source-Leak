package com.google.api.client.http.javanet;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.net.HttpURLConnection;
import com.google.api.client.http.LowLevelHttpResponse;

final class NetHttpResponse extends LowLevelHttpResponse
{
    private final HttpURLConnection connection;
    private final int responseCode;
    private final String responseMessage;
    private final ArrayList<String> headerNames;
    private final ArrayList<String> headerValues;
    
    NetHttpResponse(final HttpURLConnection connection) throws IOException {
        super();
        this.headerNames = new ArrayList<String>();
        this.headerValues = new ArrayList<String>();
        this.connection = connection;
        final int responseCode = connection.getResponseCode();
        this.responseCode = ((responseCode == -1) ? 0 : responseCode);
        this.responseMessage = connection.getResponseMessage();
        final ArrayList<String> headerNames = this.headerNames;
        final ArrayList<String> headerValues = this.headerValues;
        for (final Map.Entry<String, List<String>> entry : connection.getHeaderFields().entrySet()) {
            final String s = entry.getKey();
            if (s != null) {
                for (final String s2 : entry.getValue()) {
                    if (s2 != null) {
                        headerNames.add(s);
                        headerValues.add(s2);
                    }
                }
            }
        }
    }
    
    @Override
    public int getStatusCode() {
        return this.responseCode;
    }
    
    @Override
    public InputStream getContent() throws IOException {
        InputStream inputStream;
        try {
            inputStream = this.connection.getInputStream();
        }
        catch (IOException ex) {
            inputStream = this.connection.getErrorStream();
        }
        return (inputStream == null) ? null : new SizeValidatingInputStream(inputStream);
    }
    
    @Override
    public String getContentEncoding() {
        return this.connection.getContentEncoding();
    }
    
    @Override
    public long getContentLength() {
        final String headerField = this.connection.getHeaderField("Content-Length");
        return (headerField == null) ? -1L : Long.parseLong(headerField);
    }
    
    @Override
    public String getContentType() {
        return this.connection.getHeaderField("Content-Type");
    }
    
    @Override
    public String getReasonPhrase() {
        return this.responseMessage;
    }
    
    @Override
    public String getStatusLine() {
        final String headerField = this.connection.getHeaderField(0);
        return (headerField != null && headerField.startsWith("HTTP/1.")) ? headerField : null;
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
    public void disconnect() {
        this.connection.disconnect();
    }
}
