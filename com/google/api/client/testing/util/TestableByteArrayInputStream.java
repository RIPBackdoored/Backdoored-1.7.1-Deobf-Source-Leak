package com.google.api.client.testing.util;

import java.io.IOException;
import com.google.api.client.util.Beta;
import java.io.ByteArrayInputStream;

@Beta
public class TestableByteArrayInputStream extends ByteArrayInputStream
{
    private boolean closed;
    
    public TestableByteArrayInputStream(final byte[] array) {
        super(array);
    }
    
    public TestableByteArrayInputStream(final byte[] array, final int n, final int n2) {
        super(array);
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
