package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.InputStream;

public final class MergedStream extends InputStream
{
    private final IOContext _ctxt;
    private final InputStream _in;
    private byte[] _b;
    private int _ptr;
    private final int _end;
    
    public MergedStream(final IOContext ctxt, final InputStream in, final byte[] b, final int ptr, final int end) {
        super();
        this._ctxt = ctxt;
        this._in = in;
        this._b = b;
        this._ptr = ptr;
        this._end = end;
    }
    
    @Override
    public int available() throws IOException {
        if (this._b != null) {
            return this._end - this._ptr;
        }
        return this._in.available();
    }
    
    @Override
    public void close() throws IOException {
        this._free();
        this._in.close();
    }
    
    @Override
    public void mark(final int n) {
        if (this._b == null) {
            this._in.mark(n);
        }
    }
    
    @Override
    public boolean markSupported() {
        return this._b == null && this._in.markSupported();
    }
    
    @Override
    public int read() throws IOException {
        if (this._b != null) {
            final int n = this._b[this._ptr++] & 0xFF;
            if (this._ptr >= this._end) {
                this._free();
            }
            return n;
        }
        return this._in.read();
    }
    
    @Override
    public int read(final byte[] array) throws IOException {
        return this.read(array, 0, array.length);
    }
    
    @Override
    public int read(final byte[] array, final int n, int n2) throws IOException {
        if (this._b != null) {
            final int n3 = this._end - this._ptr;
            if (n2 > n3) {
                n2 = n3;
            }
            System.arraycopy(this._b, this._ptr, array, n, n2);
            this._ptr += n2;
            if (this._ptr >= this._end) {
                this._free();
            }
            return n2;
        }
        return this._in.read(array, n, n2);
    }
    
    @Override
    public void reset() throws IOException {
        if (this._b == null) {
            this._in.reset();
        }
    }
    
    @Override
    public long skip(long n) throws IOException {
        long n2 = 0L;
        if (this._b != null) {
            final int n3 = this._end - this._ptr;
            if (n3 > n) {
                this._ptr += (int)n;
                return n;
            }
            this._free();
            n2 += n3;
            n -= n3;
        }
        if (n > 0L) {
            n2 += this._in.skip(n);
        }
        return n2;
    }
    
    private void _free() {
        final byte[] b = this._b;
        if (b != null) {
            this._b = null;
            if (this._ctxt != null) {
                this._ctxt.releaseReadIOBuffer(b);
            }
        }
    }
}
