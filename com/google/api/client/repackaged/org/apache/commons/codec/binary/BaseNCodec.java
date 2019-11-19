package com.google.api.client.repackaged.org.apache.commons.codec.binary;

import com.google.api.client.repackaged.org.apache.commons.codec.DecoderException;
import com.google.api.client.repackaged.org.apache.commons.codec.EncoderException;
import com.google.api.client.repackaged.org.apache.commons.codec.BinaryDecoder;
import com.google.api.client.repackaged.org.apache.commons.codec.BinaryEncoder;

public abstract class BaseNCodec implements BinaryEncoder, BinaryDecoder
{
    public static final int MIME_CHUNK_SIZE = 76;
    public static final int PEM_CHUNK_SIZE = 64;
    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    protected static final int MASK_8BITS = 255;
    protected static final byte PAD_DEFAULT = 61;
    protected final byte PAD = 61;
    private final int unencodedBlockSize;
    private final int encodedBlockSize;
    protected final int lineLength;
    private final int chunkSeparatorLength;
    protected byte[] buffer;
    protected int pos;
    private int readPos;
    protected boolean eof;
    protected int currentLinePos;
    protected int modulus;
    
    protected BaseNCodec(final int unencodedBlockSize, final int encodedBlockSize, final int n, final int chunkSeparatorLength) {
        super();
        this.unencodedBlockSize = unencodedBlockSize;
        this.encodedBlockSize = encodedBlockSize;
        this.lineLength = ((n > 0 && chunkSeparatorLength > 0) ? (n / encodedBlockSize * encodedBlockSize) : 0);
        this.chunkSeparatorLength = chunkSeparatorLength;
    }
    
    boolean hasData() {
        return this.buffer != null;
    }
    
    int available() {
        return (this.buffer != null) ? (this.pos - this.readPos) : 0;
    }
    
    protected int getDefaultBufferSize() {
        return 8192;
    }
    
    private void resizeBuffer() {
        if (this.buffer == null) {
            this.buffer = new byte[this.getDefaultBufferSize()];
            this.pos = 0;
            this.readPos = 0;
        }
        else {
            final byte[] buffer = new byte[this.buffer.length * 2];
            System.arraycopy(this.buffer, 0, buffer, 0, this.buffer.length);
            this.buffer = buffer;
        }
    }
    
    protected void ensureBufferSize(final int n) {
        if (this.buffer == null || this.buffer.length < this.pos + n) {
            this.resizeBuffer();
        }
    }
    
    int readResults(final byte[] array, final int n, final int n2) {
        if (this.buffer != null) {
            final int min = Math.min(this.available(), n2);
            System.arraycopy(this.buffer, this.readPos, array, n, min);
            this.readPos += min;
            if (this.readPos >= this.pos) {
                this.buffer = null;
            }
            return min;
        }
        return this.eof ? -1 : 0;
    }
    
    protected static boolean isWhiteSpace(final byte b) {
        switch (b) {
            case 9:
            case 10:
            case 13:
            case 32:
                return true;
            default:
                return false;
        }
    }
    
    private void reset() {
        this.buffer = null;
        this.pos = 0;
        this.readPos = 0;
        this.currentLinePos = 0;
        this.modulus = 0;
        this.eof = false;
    }
    
    public Object encode(final Object o) throws EncoderException {
        if (!(o instanceof byte[])) {
            throw new EncoderException("Parameter supplied to Base-N encode is not a byte[]");
        }
        return this.encode((byte[])o);
    }
    
    public String encodeToString(final byte[] array) {
        return StringUtils.newStringUtf8(this.encode(array));
    }
    
    public Object decode(final Object o) throws DecoderException {
        if (o instanceof byte[]) {
            return this.decode((byte[])o);
        }
        if (o instanceof String) {
            return this.decode((String)o);
        }
        throw new DecoderException("Parameter supplied to Base-N decode is not a byte[] or a String");
    }
    
    public byte[] decode(final String s) {
        return this.decode(StringUtils.getBytesUtf8(s));
    }
    
    public byte[] decode(final byte[] array) {
        this.reset();
        if (array == null || array.length == 0) {
            return array;
        }
        this.decode(array, 0, array.length);
        this.decode(array, 0, -1);
        final byte[] array2 = new byte[this.pos];
        this.readResults(array2, 0, array2.length);
        return array2;
    }
    
    public byte[] encode(final byte[] array) {
        this.reset();
        if (array == null || array.length == 0) {
            return array;
        }
        this.encode(array, 0, array.length);
        this.encode(array, 0, -1);
        final byte[] array2 = new byte[this.pos - this.readPos];
        this.readResults(array2, 0, array2.length);
        return array2;
    }
    
    public String encodeAsString(final byte[] array) {
        return StringUtils.newStringUtf8(this.encode(array));
    }
    
    abstract void encode(final byte[] p0, final int p1, final int p2);
    
    abstract void decode(final byte[] p0, final int p1, final int p2);
    
    protected abstract boolean isInAlphabet(final byte p0);
    
    public boolean isInAlphabet(final byte[] array, final boolean b) {
        for (int i = 0; i < array.length; ++i) {
            if (!this.isInAlphabet(array[i]) && (!b || (array[i] != 61 && !isWhiteSpace(array[i])))) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isInAlphabet(final String s) {
        return this.isInAlphabet(StringUtils.getBytesUtf8(s), true);
    }
    
    protected boolean containsAlphabetOrPad(final byte[] array) {
        if (array == null) {
            return false;
        }
        for (final byte b : array) {
            if (61 == b || this.isInAlphabet(b)) {
                return true;
            }
        }
        return false;
    }
    
    public long getEncodedLength(final byte[] array) {
        long n = (array.length + this.unencodedBlockSize - 1) / this.unencodedBlockSize * (long)this.encodedBlockSize;
        if (this.lineLength > 0) {
            n += (n + this.lineLength - 1L) / this.lineLength * this.chunkSeparatorLength;
        }
        return n;
    }
}
