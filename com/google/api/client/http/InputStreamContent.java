package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import java.io.InputStream;

public final class InputStreamContent extends AbstractInputStreamContent
{
    private long length;
    private boolean retrySupported;
    private final InputStream inputStream;
    
    public InputStreamContent(final String s, final InputStream inputStream) {
        super(s);
        this.length = -1L;
        this.inputStream = Preconditions.<InputStream>checkNotNull(inputStream);
    }
    
    @Override
    public long getLength() {
        return this.length;
    }
    
    @Override
    public boolean retrySupported() {
        return this.retrySupported;
    }
    
    public InputStreamContent setRetrySupported(final boolean retrySupported) {
        this.retrySupported = retrySupported;
        return this;
    }
    
    @Override
    public InputStream getInputStream() {
        return this.inputStream;
    }
    
    @Override
    public InputStreamContent setType(final String type) {
        return (InputStreamContent)super.setType(type);
    }
    
    @Override
    public InputStreamContent setCloseInputStream(final boolean closeInputStream) {
        return (InputStreamContent)super.setCloseInputStream(closeInputStream);
    }
    
    public InputStreamContent setLength(final long length) {
        this.length = length;
        return this;
    }
    
    @Override
    public /* bridge */ AbstractInputStreamContent setCloseInputStream(final boolean closeInputStream) {
        return this.setCloseInputStream(closeInputStream);
    }
    
    @Override
    public /* bridge */ AbstractInputStreamContent setType(final String type) {
        return this.setType(type);
    }
}
