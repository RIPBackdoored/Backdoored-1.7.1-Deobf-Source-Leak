package com.google.api.client.http.javanet;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

private final class SizeValidatingInputStream extends FilterInputStream
{
    private long bytesRead;
    final /* synthetic */ NetHttpResponse this$0;
    
    public SizeValidatingInputStream(final NetHttpResponse this$0, final InputStream inputStream) {
        this.this$0 = this$0;
        super(inputStream);
        this.bytesRead = 0L;
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        final int read = this.in.read(array, n, n2);
        if (read == -1) {
            this.throwIfFalseEOF();
        }
        else {
            this.bytesRead += read;
        }
        return read;
    }
    
    @Override
    public int read() throws IOException {
        final int read = this.in.read();
        if (read == -1) {
            this.throwIfFalseEOF();
        }
        else {
            ++this.bytesRead;
        }
        return read;
    }
    
    private void throwIfFalseEOF() throws IOException {
        final long contentLength = this.this$0.getContentLength();
        if (contentLength == -1L) {
            return;
        }
        if (this.bytesRead != 0L && this.bytesRead < contentLength) {
            throw new IOException("Connection closed prematurely: bytesRead = " + this.bytesRead + ", Content-Length = " + contentLength);
        }
    }
}
