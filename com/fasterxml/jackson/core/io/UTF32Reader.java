package com.fasterxml.jackson.core.io;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class UTF32Reader extends Reader
{
    protected static final int LAST_VALID_UNICODE_CHAR = 1114111;
    protected static final char NC = '\0';
    protected final IOContext _context;
    protected InputStream _in;
    protected byte[] _buffer;
    protected int _ptr;
    protected int _length;
    protected final boolean _bigEndian;
    protected char _surrogate;
    protected int _charCount;
    protected int _byteCount;
    protected final boolean _managedBuffers;
    protected char[] _tmpBuf;
    
    public UTF32Reader(final IOContext context, final InputStream in, final byte[] buffer, final int ptr, final int length, final boolean bigEndian) {
        super();
        this._surrogate = '\0';
        this._context = context;
        this._in = in;
        this._buffer = buffer;
        this._ptr = ptr;
        this._length = length;
        this._bigEndian = bigEndian;
        this._managedBuffers = (in != null);
    }
    
    @Override
    public void close() throws IOException {
        final InputStream in = this._in;
        if (in != null) {
            this._in = null;
            this.freeBuffers();
            in.close();
        }
    }
    
    @Override
    public int read() throws IOException {
        if (this._tmpBuf == null) {
            this._tmpBuf = new char[1];
        }
        if (this.read(this._tmpBuf, 0, 1) < 1) {
            return -1;
        }
        return this._tmpBuf[0];
    }
    
    @Override
    public int read(final char[] array, final int n, final int n2) throws IOException {
        if (this._buffer == null) {
            return -1;
        }
        if (n2 < 1) {
            return n2;
        }
        if (n < 0 || n + n2 > array.length) {
            this.reportBounds(array, n, n2);
        }
        int i = n;
        final int n3 = n2 + n;
        if (this._surrogate != '\0') {
            array[i++] = this._surrogate;
            this._surrogate = '\0';
        }
        else {
            final int n4 = this._length - this._ptr;
            if (n4 < 4 && !this.loadMore(n4)) {
                return -1;
            }
        }
        final int n5 = this._length - 4;
        while (i < n3) {
            final int ptr = this._ptr;
            int n6;
            int n7;
            if (this._bigEndian) {
                n6 = (this._buffer[ptr] << 8 | (this._buffer[ptr + 1] & 0xFF));
                n7 = ((this._buffer[ptr + 2] & 0xFF) << 8 | (this._buffer[ptr + 3] & 0xFF));
            }
            else {
                n7 = ((this._buffer[ptr] & 0xFF) | (this._buffer[ptr + 1] & 0xFF) << 8);
                n6 = ((this._buffer[ptr + 2] & 0xFF) | this._buffer[ptr + 3] << 8);
            }
            this._ptr += 4;
            if (n6 != 0) {
                final int n8 = n6 & 0xFFFF;
                final int n9 = n8 - 1 << 16 | n7;
                if (n8 > 16) {
                    this.reportInvalid(n9, i - n, String.format(" (above 0x%08x)", 1114111));
                }
                array[i++] = (char)(55296 + (n9 >> 10));
                n7 = (0xDC00 | (n9 & 0x3FF));
                if (i >= n3) {
                    this._surrogate = (char)n9;
                    break;
                }
            }
            array[i++] = (char)n7;
            if (this._ptr > n5) {
                break;
            }
        }
        final int n10 = i - n;
        this._charCount += n10;
        return n10;
    }
    
    private void reportUnexpectedEOF(final int n, final int n2) throws IOException {
        throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + n + ", needed " + n2 + ", at char #" + this._charCount + ", byte #" + (this._byteCount + n) + ")");
    }
    
    private void reportInvalid(final int n, final int n2, final String s) throws IOException {
        throw new CharConversionException("Invalid UTF-32 character 0x" + Integer.toHexString(n) + s + " at char #" + (this._charCount + n2) + ", byte #" + (this._byteCount + this._ptr - 1) + ")");
    }
    
    private boolean loadMore(final int length) throws IOException {
        this._byteCount += this._length - length;
        if (length > 0) {
            if (this._ptr > 0) {
                System.arraycopy(this._buffer, this._ptr, this._buffer, 0, length);
                this._ptr = 0;
            }
            this._length = length;
        }
        else {
            this._ptr = 0;
            final int length2 = (this._in == null) ? -1 : this._in.read(this._buffer);
            if (length2 < 1) {
                this._length = 0;
                if (length2 < 0) {
                    if (this._managedBuffers) {
                        this.freeBuffers();
                    }
                    return false;
                }
                this.reportStrangeStream();
            }
            this._length = length2;
        }
        while (this._length < 4) {
            final int n = (this._in == null) ? -1 : this._in.read(this._buffer, this._length, this._buffer.length - this._length);
            if (n < 1) {
                if (n < 0) {
                    if (this._managedBuffers) {
                        this.freeBuffers();
                    }
                    this.reportUnexpectedEOF(this._length, 4);
                }
                this.reportStrangeStream();
            }
            this._length += n;
        }
        return true;
    }
    
    private void freeBuffers() {
        final byte[] buffer = this._buffer;
        if (buffer != null) {
            this._buffer = null;
            this._context.releaseReadIOBuffer(buffer);
        }
    }
    
    private void reportBounds(final char[] array, final int n, final int n2) throws IOException {
        throw new ArrayIndexOutOfBoundsException("read(buf," + n + "," + n2 + "), cbuf[" + array.length + "]");
    }
    
    private void reportStrangeStream() throws IOException {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }
}
