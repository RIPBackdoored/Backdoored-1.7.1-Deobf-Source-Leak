package com.google.api.client.testing.http.javanet;

import java.util.ArrayList;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.OutputStream;
import com.google.api.client.util.Beta;
import java.net.HttpURLConnection;

@Beta
public class MockHttpURLConnection extends HttpURLConnection
{
    private boolean doOutputCalled;
    private OutputStream outputStream;
    @Deprecated
    public static final byte[] INPUT_BUF;
    @Deprecated
    public static final byte[] ERROR_BUF;
    private InputStream inputStream;
    private InputStream errorStream;
    private Map<String, List<String>> headers;
    
    public MockHttpURLConnection(final URL url) {
        super(url);
        this.outputStream = new ByteArrayOutputStream(0);
        this.inputStream = null;
        this.errorStream = null;
        this.headers = new LinkedHashMap<String, List<String>>();
    }
    
    @Override
    public void disconnect() {
    }
    
    @Override
    public boolean usingProxy() {
        return false;
    }
    
    @Override
    public void connect() throws IOException {
    }
    
    @Override
    public int getResponseCode() throws IOException {
        return this.responseCode;
    }
    
    @Override
    public void setDoOutput(final boolean b) {
        this.doOutputCalled = true;
    }
    
    @Override
    public OutputStream getOutputStream() throws IOException {
        if (this.outputStream != null) {
            return this.outputStream;
        }
        return super.getOutputStream();
    }
    
    public final boolean doOutputCalled() {
        return this.doOutputCalled;
    }
    
    public MockHttpURLConnection setOutputStream(final OutputStream outputStream) {
        this.outputStream = outputStream;
        return this;
    }
    
    public MockHttpURLConnection setResponseCode(final int responseCode) {
        Preconditions.checkArgument(responseCode >= -1);
        this.responseCode = responseCode;
        return this;
    }
    
    public MockHttpURLConnection addHeader(final String s, final String s2) {
        Preconditions.<String>checkNotNull(s);
        Preconditions.<String>checkNotNull(s2);
        if (this.headers.containsKey(s)) {
            this.headers.get(s).add(s2);
        }
        else {
            final ArrayList<String> list = new ArrayList<String>();
            list.add(s2);
            this.headers.put(s, list);
        }
        return this;
    }
    
    public MockHttpURLConnection setInputStream(final InputStream inputStream) {
        Preconditions.<InputStream>checkNotNull(inputStream);
        if (this.inputStream == null) {
            this.inputStream = inputStream;
        }
        return this;
    }
    
    public MockHttpURLConnection setErrorStream(final InputStream errorStream) {
        Preconditions.<InputStream>checkNotNull(errorStream);
        if (this.errorStream == null) {
            this.errorStream = errorStream;
        }
        return this;
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        if (this.responseCode < 400) {
            return this.inputStream;
        }
        throw new IOException();
    }
    
    @Override
    public InputStream getErrorStream() {
        return this.errorStream;
    }
    
    @Override
    public Map<String, List<String>> getHeaderFields() {
        return this.headers;
    }
    
    @Override
    public String getHeaderField(final String s) {
        final List<String> list = this.headers.get(s);
        return (list == null) ? null : list.get(0);
    }
    
    static {
        INPUT_BUF = new byte[1];
        ERROR_BUF = new byte[5];
    }
}
