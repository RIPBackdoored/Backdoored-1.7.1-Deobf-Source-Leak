package com.fasterxml.jackson.core.format;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public static class Std implements InputAccessor
{
    protected final InputStream _in;
    protected final byte[] _buffer;
    protected final int _bufferedStart;
    protected int _bufferedEnd;
    protected int _ptr;
    
    public Std(final InputStream in, final byte[] buffer) {
        super();
        this._in = in;
        this._buffer = buffer;
        this._bufferedStart = 0;
        this._ptr = 0;
        this._bufferedEnd = 0;
    }
    
    public Std(final byte[] buffer) {
        super();
        this._in = null;
        this._buffer = buffer;
        this._bufferedStart = 0;
        this._bufferedEnd = buffer.length;
    }
    
    public Std(final byte[] buffer, final int n, final int n2) {
        super();
        this._in = null;
        this._buffer = buffer;
        this._ptr = n;
        this._bufferedStart = n;
        this._bufferedEnd = n + n2;
    }
    
    @Override
    public boolean hasMoreBytes() throws IOException {
        if (this._ptr < this._bufferedEnd) {
            return true;
        }
        if (this._in == null) {
            return false;
        }
        final int n = this._buffer.length - this._ptr;
        if (n < 1) {
            return false;
        }
        final int read = this._in.read(this._buffer, this._ptr, n);
        if (read <= 0) {
            return false;
        }
        this._bufferedEnd += read;
        return true;
    }
    
    @Override
    public byte nextByte() throws IOException {
        if (this._ptr >= this._bufferedEnd && !this.hasMoreBytes()) {
            throw new EOFException("Failed auto-detect: could not read more than " + this._ptr + " bytes (max buffer size: " + this._buffer.length + ")");
        }
        return this._buffer[this._ptr++];
    }
    
    @Override
    public void reset() {
        this._ptr = this._bufferedStart;
    }
    
    public DataFormatMatcher createMatcher(final JsonFactory jsonFactory, final MatchStrength matchStrength) {
        return new DataFormatMatcher(this._in, this._buffer, this._bufferedStart, this._bufferedEnd - this._bufferedStart, jsonFactory, matchStrength);
    }
}
