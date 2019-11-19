package com.google.api.client.testing.http;

import com.google.api.client.util.Preconditions;
import java.io.OutputStream;
import java.io.IOException;
import com.google.api.client.util.Beta;
import com.google.api.client.http.HttpContent;

@Beta
public class MockHttpContent implements HttpContent
{
    private long length;
    private String type;
    private byte[] content;
    
    public MockHttpContent() {
        super();
        this.length = -1L;
        this.content = new byte[0];
    }
    
    @Override
    public long getLength() throws IOException {
        return this.length;
    }
    
    @Override
    public String getType() {
        return this.type;
    }
    
    @Override
    public void writeTo(final OutputStream outputStream) throws IOException {
        outputStream.write(this.content);
        outputStream.flush();
    }
    
    @Override
    public boolean retrySupported() {
        return true;
    }
    
    public final byte[] getContent() {
        return this.content;
    }
    
    public MockHttpContent setContent(final byte[] array) {
        this.content = Preconditions.<byte[]>checkNotNull(array);
        return this;
    }
    
    public MockHttpContent setLength(final long length) {
        Preconditions.checkArgument(length >= -1L);
        this.length = length;
        return this;
    }
    
    public MockHttpContent setType(final String type) {
        this.type = type;
        return this;
    }
}
