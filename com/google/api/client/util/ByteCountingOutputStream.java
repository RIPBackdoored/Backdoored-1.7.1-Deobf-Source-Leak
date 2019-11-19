package com.google.api.client.util;

import java.io.IOException;
import java.io.OutputStream;

final class ByteCountingOutputStream extends OutputStream
{
    long count;
    
    ByteCountingOutputStream() {
        super();
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        this.count += n2;
    }
    
    @Override
    public void write(final int n) throws IOException {
        ++this.count;
    }
}
