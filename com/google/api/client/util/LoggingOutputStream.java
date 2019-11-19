package com.google.api.client.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.OutputStream;
import java.io.FilterOutputStream;

public class LoggingOutputStream extends FilterOutputStream
{
    private final LoggingByteArrayOutputStream logStream;
    
    public LoggingOutputStream(final OutputStream outputStream, final Logger logger, final Level level, final int n) {
        super(outputStream);
        this.logStream = new LoggingByteArrayOutputStream(logger, level, n);
    }
    
    @Override
    public void write(final int n) throws IOException {
        this.out.write(n);
        this.logStream.write(n);
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        this.out.write(array, n, n2);
        this.logStream.write(array, n, n2);
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
