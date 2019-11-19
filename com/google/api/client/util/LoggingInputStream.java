package com.google.api.client.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.io.FilterInputStream;

public class LoggingInputStream extends FilterInputStream
{
    private final LoggingByteArrayOutputStream logStream;
    
    public LoggingInputStream(final InputStream inputStream, final Logger logger, final Level level, final int n) {
        super(inputStream);
        this.logStream = new LoggingByteArrayOutputStream(logger, level, n);
    }
    
    @Override
    public int read() throws IOException {
        final int read = super.read();
        this.logStream.write(read);
        return read;
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        final int read = super.read(array, n, n2);
        if (read > 0) {
            this.logStream.write(array, n, read);
        }
        return read;
    }
    
    @Override
    public void close() throws IOException {
        this.logStream.close();
        super.close();
    }
    
    public final LoggingByteArrayOutputStream getLogStream() {
        return this.logStream;
    }
}
