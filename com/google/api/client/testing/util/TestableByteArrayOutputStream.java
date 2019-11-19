package com.google.api.client.testing.util;

import java.io.IOException;
import com.google.api.client.util.Beta;
import java.io.ByteArrayOutputStream;

@Beta
public class TestableByteArrayOutputStream extends ByteArrayOutputStream
{
    private boolean closed;
    
    public TestableByteArrayOutputStream() {
        super();
    }
    
    @Override
    public void close() throws IOException {
        this.closed = true;
    }
    
    public final byte[] getBuffer() {
        return this.buf;
    }
    
    public final boolean isClosed() {
        return this.closed;
    }
}
