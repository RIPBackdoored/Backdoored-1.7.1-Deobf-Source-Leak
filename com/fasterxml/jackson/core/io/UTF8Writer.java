package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public final class UTF8Writer extends Writer
{
    static final int SURR1_FIRST = 55296;
    static final int SURR1_LAST = 56319;
    static final int SURR2_FIRST = 56320;
    static final int SURR2_LAST = 57343;
    private final IOContext _context;
    private OutputStream _out;
    private byte[] _outBuffer;
    private final int _outBufferEnd;
    private int _outPtr;
    private int _surrogate;
    
    public UTF8Writer(final IOContext context, final OutputStream out) {
        super();
        this._context = context;
        this._out = out;
        this._outBuffer = context.allocWriteEncodingBuffer();
        this._outBufferEnd = this._outBuffer.length - 4;
        this._outPtr = 0;
    }
    
    @Override
    public Writer append(final char c) throws IOException {
        this.write(c);
        return this;
    }
    
    @Override
    public void close() throws IOException {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            final OutputStream out = this._out;
            this._out = null;
            final byte[] outBuffer = this._outBuffer;
            if (outBuffer != null) {
                this._outBuffer = null;
                this._context.releaseWriteEncodingBuffer(outBuffer);
            }
            out.close();
            final int surrogate = this._surrogate;
            this._surrogate = 0;
            if (surrogate > 0) {
                illegalSurrogate(surrogate);
            }
        }
    }
    
    @Override
    public void flush() throws IOException {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            this._out.flush();
        }
    }
    
    @Override
    public void write(final char[] array) throws IOException {
        this.write(array, 0, array.length);
    }
    
    @Override
    public void write(final char[] array, int i, int n) throws IOException {
        if (n < 2) {
            if (n == 1) {
                this.write(array[i]);
            }
            return;
        }
        if (this._surrogate > 0) {
            final char c = array[i++];
            --n;
            this.write(this.convertSurrogate(c));
        }
        int outPtr = this._outPtr;
        final byte[] outBuffer = this._outBuffer;
        final int outBufferEnd = this._outBufferEnd;
        n += i;
        while (i < n) {
            if (outPtr >= outBufferEnd) {
                this._out.write(outBuffer, 0, outPtr);
                outPtr = 0;
            }
            char surrogate = array[i++];
            Label_0193: {
                if (surrogate < '\u0080') {
                    outBuffer[outPtr++] = (byte)surrogate;
                    int n2 = n - i;
                    final int n3 = outBufferEnd - outPtr;
                    if (n2 > n3) {
                        n2 = n3;
                    }
                    while (i < n2 + i) {
                        surrogate = array[i++];
                        if (surrogate >= '\u0080') {
                            break Label_0193;
                        }
                        outBuffer[outPtr++] = (byte)surrogate;
                    }
                    continue;
                }
            }
            if (surrogate < '\u0800') {
                outBuffer[outPtr++] = (byte)(0xC0 | surrogate >> 6);
                outBuffer[outPtr++] = (byte)(0x80 | (surrogate & '?'));
            }
            else if (surrogate < '\ud800' || surrogate > '\udfff') {
                outBuffer[outPtr++] = (byte)(0xE0 | surrogate >> 12);
                outBuffer[outPtr++] = (byte)(0x80 | (surrogate >> 6 & 0x3F));
                outBuffer[outPtr++] = (byte)(0x80 | (surrogate & '?'));
            }
            else {
                if (surrogate > '\udbff') {
                    this._outPtr = outPtr;
                    illegalSurrogate(surrogate);
                }
                this._surrogate = surrogate;
                if (i >= n) {
                    break;
                }
                final int convertSurrogate = this.convertSurrogate(array[i++]);
                if (convertSurrogate > 1114111) {
                    this._outPtr = outPtr;
                    illegalSurrogate(convertSurrogate);
                }
                outBuffer[outPtr++] = (byte)(0xF0 | convertSurrogate >> 18);
                outBuffer[outPtr++] = (byte)(0x80 | (convertSurrogate >> 12 & 0x3F));
                outBuffer[outPtr++] = (byte)(0x80 | (convertSurrogate >> 6 & 0x3F));
                outBuffer[outPtr++] = (byte)(0x80 | (convertSurrogate & 0x3F));
            }
        }
        this._outPtr = outPtr;
    }
    
    @Override
    public void write(int convertSurrogate) throws IOException {
        if (this._surrogate > 0) {
            convertSurrogate = this.convertSurrogate(convertSurrogate);
        }
        else if (convertSurrogate >= 55296 && convertSurrogate <= 57343) {
            if (convertSurrogate > 56319) {
                illegalSurrogate(convertSurrogate);
            }
            this._surrogate = convertSurrogate;
            return;
        }
        if (this._outPtr >= this._outBufferEnd) {
            this._out.write(this._outBuffer, 0, this._outPtr);
            this._outPtr = 0;
        }
        if (convertSurrogate < 128) {
            this._outBuffer[this._outPtr++] = (byte)convertSurrogate;
        }
        else {
            int outPtr = this._outPtr;
            if (convertSurrogate < 2048) {
                this._outBuffer[outPtr++] = (byte)(0xC0 | convertSurrogate >> 6);
                this._outBuffer[outPtr++] = (byte)(0x80 | (convertSurrogate & 0x3F));
            }
            else if (convertSurrogate <= 65535) {
                this._outBuffer[outPtr++] = (byte)(0xE0 | convertSurrogate >> 12);
                this._outBuffer[outPtr++] = (byte)(0x80 | (convertSurrogate >> 6 & 0x3F));
                this._outBuffer[outPtr++] = (byte)(0x80 | (convertSurrogate & 0x3F));
            }
            else {
                if (convertSurrogate > 1114111) {
                    illegalSurrogate(convertSurrogate);
                }
                this._outBuffer[outPtr++] = (byte)(0xF0 | convertSurrogate >> 18);
                this._outBuffer[outPtr++] = (byte)(0x80 | (convertSurrogate >> 12 & 0x3F));
                this._outBuffer[outPtr++] = (byte)(0x80 | (convertSurrogate >> 6 & 0x3F));
                this._outBuffer[outPtr++] = (byte)(0x80 | (convertSurrogate & 0x3F));
            }
            this._outPtr = outPtr;
        }
    }
    
    @Override
    public void write(final String s) throws IOException {
        this.write(s, 0, s.length());
    }
    
    @Override
    public void write(final String s, int i, int n) throws IOException {
        if (n < 2) {
            if (n == 1) {
                this.write(s.charAt(i));
            }
            return;
        }
        if (this._surrogate > 0) {
            final char char1 = s.charAt(i++);
            --n;
            this.write(this.convertSurrogate(char1));
        }
        int outPtr = this._outPtr;
        final byte[] outBuffer = this._outBuffer;
        final int outBufferEnd = this._outBufferEnd;
        n += i;
        while (i < n) {
            if (outPtr >= outBufferEnd) {
                this._out.write(outBuffer, 0, outPtr);
                outPtr = 0;
            }
            char surrogate = s.charAt(i++);
            Label_0201: {
                if (surrogate < '\u0080') {
                    outBuffer[outPtr++] = (byte)surrogate;
                    int n2 = n - i;
                    final int n3 = outBufferEnd - outPtr;
                    if (n2 > n3) {
                        n2 = n3;
                    }
                    while (i < n2 + i) {
                        surrogate = s.charAt(i++);
                        if (surrogate >= '\u0080') {
                            break Label_0201;
                        }
                        outBuffer[outPtr++] = (byte)surrogate;
                    }
                    continue;
                }
            }
            if (surrogate < '\u0800') {
                outBuffer[outPtr++] = (byte)(0xC0 | surrogate >> 6);
                outBuffer[outPtr++] = (byte)(0x80 | (surrogate & '?'));
            }
            else if (surrogate < '\ud800' || surrogate > '\udfff') {
                outBuffer[outPtr++] = (byte)(0xE0 | surrogate >> 12);
                outBuffer[outPtr++] = (byte)(0x80 | (surrogate >> 6 & 0x3F));
                outBuffer[outPtr++] = (byte)(0x80 | (surrogate & '?'));
            }
            else {
                if (surrogate > '\udbff') {
                    this._outPtr = outPtr;
                    illegalSurrogate(surrogate);
                }
                this._surrogate = surrogate;
                if (i >= n) {
                    break;
                }
                final int convertSurrogate = this.convertSurrogate(s.charAt(i++));
                if (convertSurrogate > 1114111) {
                    this._outPtr = outPtr;
                    illegalSurrogate(convertSurrogate);
                }
                outBuffer[outPtr++] = (byte)(0xF0 | convertSurrogate >> 18);
                outBuffer[outPtr++] = (byte)(0x80 | (convertSurrogate >> 12 & 0x3F));
                outBuffer[outPtr++] = (byte)(0x80 | (convertSurrogate >> 6 & 0x3F));
                outBuffer[outPtr++] = (byte)(0x80 | (convertSurrogate & 0x3F));
            }
        }
        this._outPtr = outPtr;
    }
    
    protected int convertSurrogate(final int n) throws IOException {
        final int surrogate = this._surrogate;
        this._surrogate = 0;
        if (n < 56320 || n > 57343) {
            throw new IOException("Broken surrogate pair: first char 0x" + Integer.toHexString(surrogate) + ", second 0x" + Integer.toHexString(n) + "; illegal combination");
        }
        return 65536 + (surrogate - 55296 << 10) + (n - 56320);
    }
    
    protected static void illegalSurrogate(final int n) throws IOException {
        throw new IOException(illegalSurrogateDesc(n));
    }
    
    protected static String illegalSurrogateDesc(final int n) {
        if (n > 1114111) {
            return "Illegal character point (0x" + Integer.toHexString(n) + ") to output; max is 0x10FFFF as per RFC 4627";
        }
        if (n < 55296) {
            return "Illegal character point (0x" + Integer.toHexString(n) + ") to output";
        }
        if (n <= 56319) {
            return "Unmatched first part of surrogate pair (0x" + Integer.toHexString(n) + ")";
        }
        return "Unmatched second part of surrogate pair (0x" + Integer.toHexString(n) + ")";
    }
    
    @Override
    public /* bridge */ Appendable append(final char c) throws IOException {
        return this.append(c);
    }
}
