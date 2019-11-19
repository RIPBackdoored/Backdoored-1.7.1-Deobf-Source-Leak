package com.fasterxml.jackson.core.util;

public class BufferRecycler
{
    public static final int BYTE_READ_IO_BUFFER = 0;
    public static final int BYTE_WRITE_ENCODING_BUFFER = 1;
    public static final int BYTE_WRITE_CONCAT_BUFFER = 2;
    public static final int BYTE_BASE64_CODEC_BUFFER = 3;
    public static final int CHAR_TOKEN_BUFFER = 0;
    public static final int CHAR_CONCAT_BUFFER = 1;
    public static final int CHAR_TEXT_BUFFER = 2;
    public static final int CHAR_NAME_COPY_BUFFER = 3;
    private static final int[] BYTE_BUFFER_LENGTHS;
    private static final int[] CHAR_BUFFER_LENGTHS;
    protected final byte[][] _byteBuffers;
    protected final char[][] _charBuffers;
    
    public BufferRecycler() {
        this(4, 4);
    }
    
    protected BufferRecycler(final int n, final int n2) {
        super();
        this._byteBuffers = new byte[n][];
        this._charBuffers = new char[n2][];
    }
    
    public final byte[] allocByteBuffer(final int n) {
        return this.allocByteBuffer(n, 0);
    }
    
    public byte[] allocByteBuffer(final int n, int n2) {
        final int byteBufferLength = this.byteBufferLength(n);
        if (n2 < byteBufferLength) {
            n2 = byteBufferLength;
        }
        byte[] balloc = this._byteBuffers[n];
        if (balloc == null || balloc.length < n2) {
            balloc = this.balloc(n2);
        }
        else {
            this._byteBuffers[n] = null;
        }
        return balloc;
    }
    
    public void releaseByteBuffer(final int n, final byte[] array) {
        this._byteBuffers[n] = array;
    }
    
    public final char[] allocCharBuffer(final int n) {
        return this.allocCharBuffer(n, 0);
    }
    
    public char[] allocCharBuffer(final int n, int n2) {
        final int charBufferLength = this.charBufferLength(n);
        if (n2 < charBufferLength) {
            n2 = charBufferLength;
        }
        char[] calloc = this._charBuffers[n];
        if (calloc == null || calloc.length < n2) {
            calloc = this.calloc(n2);
        }
        else {
            this._charBuffers[n] = null;
        }
        return calloc;
    }
    
    public void releaseCharBuffer(final int n, final char[] array) {
        this._charBuffers[n] = array;
    }
    
    protected int byteBufferLength(final int n) {
        return BufferRecycler.BYTE_BUFFER_LENGTHS[n];
    }
    
    protected int charBufferLength(final int n) {
        return BufferRecycler.CHAR_BUFFER_LENGTHS[n];
    }
    
    protected byte[] balloc(final int n) {
        return new byte[n];
    }
    
    protected char[] calloc(final int n) {
        return new char[n];
    }
    
    static {
        BYTE_BUFFER_LENGTHS = new int[] { 8000, 8000, 2000, 2000 };
        CHAR_BUFFER_LENGTHS = new int[] { 4000, 4000, 200, 200 };
    }
}
