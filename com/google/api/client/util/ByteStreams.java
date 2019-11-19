package com.google.api.client.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

public final class ByteStreams
{
    private static final int BUF_SIZE = 4096;
    
    public static long copy(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        Preconditions.<InputStream>checkNotNull(inputStream);
        Preconditions.<OutputStream>checkNotNull(outputStream);
        final byte[] array = new byte[4096];
        long n = 0L;
        while (true) {
            final int read = inputStream.read(array);
            if (read == -1) {
                break;
            }
            outputStream.write(array, 0, read);
            n += read;
        }
        return n;
    }
    
    public static InputStream limit(final InputStream inputStream, final long n) {
        return new LimitedInputStream(inputStream, n);
    }
    
    public static int read(final InputStream inputStream, final byte[] array, final int n, final int n2) throws IOException {
        Preconditions.<InputStream>checkNotNull(inputStream);
        Preconditions.<byte[]>checkNotNull(array);
        if (n2 < 0) {
            throw new IndexOutOfBoundsException("len is negative");
        }
        int i;
        int read;
        for (i = 0; i < n2; i += read) {
            read = inputStream.read(array, n + i, n2 - i);
            if (read == -1) {
                break;
            }
        }
        return i;
    }
    
    private ByteStreams() {
        super();
    }
}
