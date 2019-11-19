package com.google.api.client.util;

import java.text.NumberFormat;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.ByteArrayOutputStream;

public class LoggingByteArrayOutputStream extends ByteArrayOutputStream
{
    private int bytesWritten;
    private final int maximumBytesToLog;
    private boolean closed;
    private final Level loggingLevel;
    private final Logger logger;
    
    public LoggingByteArrayOutputStream(final Logger logger, final Level level, final int maximumBytesToLog) {
        super();
        this.logger = Preconditions.<Logger>checkNotNull(logger);
        this.loggingLevel = Preconditions.<Level>checkNotNull(level);
        Preconditions.checkArgument(maximumBytesToLog >= 0);
        this.maximumBytesToLog = maximumBytesToLog;
    }
    
    @Override
    public synchronized void write(final int n) {
        Preconditions.checkArgument(!this.closed);
        ++this.bytesWritten;
        if (this.count < this.maximumBytesToLog) {
            super.write(n);
        }
    }
    
    @Override
    public synchronized void write(final byte[] array, final int n, int n2) {
        Preconditions.checkArgument(!this.closed);
        this.bytesWritten += n2;
        if (this.count < this.maximumBytesToLog) {
            final int n3 = this.count + n2;
            if (n3 > this.maximumBytesToLog) {
                n2 += this.maximumBytesToLog - n3;
            }
            super.write(array, n, n2);
        }
    }
    
    @Override
    public synchronized void close() throws IOException {
        if (!this.closed) {
            if (this.bytesWritten != 0) {
                final StringBuilder append = new StringBuilder().append("Total: ");
                appendBytes(append, this.bytesWritten);
                if (this.count != 0 && this.count < this.bytesWritten) {
                    append.append(" (logging first ");
                    appendBytes(append, this.count);
                    append.append(")");
                }
                this.logger.config(append.toString());
                if (this.count != 0) {
                    this.logger.log(this.loggingLevel, this.toString("UTF-8").replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", " "));
                }
            }
            this.closed = true;
        }
    }
    
    public final int getMaximumBytesToLog() {
        return this.maximumBytesToLog;
    }
    
    public final synchronized int getBytesWritten() {
        return this.bytesWritten;
    }
    
    private static void appendBytes(final StringBuilder sb, final int n) {
        if (n == 1) {
            sb.append("1 byte");
        }
        else {
            sb.append(NumberFormat.getInstance().format(n)).append(" bytes");
        }
    }
}
