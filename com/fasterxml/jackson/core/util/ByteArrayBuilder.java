package com.fasterxml.jackson.core.util;

import java.util.LinkedList;
import java.io.OutputStream;

public final class ByteArrayBuilder extends OutputStream
{
    public static final byte[] NO_BYTES;
    private static final int INITIAL_BLOCK_SIZE = 500;
    private static final int MAX_BLOCK_SIZE = 262144;
    static final int DEFAULT_BLOCK_ARRAY_SIZE = 40;
    private final BufferRecycler _bufferRecycler;
    private final LinkedList<byte[]> _pastBlocks;
    private int _pastLen;
    private byte[] _currBlock;
    private int _currBlockPtr;
    
    public ByteArrayBuilder() {
        this(null);
    }
    
    public ByteArrayBuilder(final BufferRecycler bufferRecycler) {
        this(bufferRecycler, 500);
    }
    
    public ByteArrayBuilder(final int n) {
        this(null, n);
    }
    
    public ByteArrayBuilder(final BufferRecycler bufferRecycler, final int n) {
        super();
        this._pastBlocks = new LinkedList<byte[]>();
        this._bufferRecycler = bufferRecycler;
        this._currBlock = ((bufferRecycler == null) ? new byte[n] : bufferRecycler.allocByteBuffer(2));
    }
    
    public void reset() {
        this._pastLen = 0;
        this._currBlockPtr = 0;
        if (!this._pastBlocks.isEmpty()) {
            this._pastBlocks.clear();
        }
    }
    
    public int size() {
        return this._pastLen + this._currBlockPtr;
    }
    
    public void release() {
        this.reset();
        if (this._bufferRecycler != null && this._currBlock != null) {
            this._bufferRecycler.releaseByteBuffer(2, this._currBlock);
            this._currBlock = null;
        }
    }
    
    public void append(final int n) {
        if (this._currBlockPtr >= this._currBlock.length) {
            this._allocMore();
        }
        this._currBlock[this._currBlockPtr++] = (byte)n;
    }
    
    public void appendTwoBytes(final int n) {
        if (this._currBlockPtr + 1 < this._currBlock.length) {
            this._currBlock[this._currBlockPtr++] = (byte)(n >> 8);
            this._currBlock[this._currBlockPtr++] = (byte)n;
        }
        else {
            this.append(n >> 8);
            this.append(n);
        }
    }
    
    public void appendThreeBytes(final int n) {
        if (this._currBlockPtr + 2 < this._currBlock.length) {
            this._currBlock[this._currBlockPtr++] = (byte)(n >> 16);
            this._currBlock[this._currBlockPtr++] = (byte)(n >> 8);
            this._currBlock[this._currBlockPtr++] = (byte)n;
        }
        else {
            this.append(n >> 16);
            this.append(n >> 8);
            this.append(n);
        }
    }
    
    public void appendFourBytes(final int n) {
        if (this._currBlockPtr + 3 < this._currBlock.length) {
            this._currBlock[this._currBlockPtr++] = (byte)(n >> 24);
            this._currBlock[this._currBlockPtr++] = (byte)(n >> 16);
            this._currBlock[this._currBlockPtr++] = (byte)(n >> 8);
            this._currBlock[this._currBlockPtr++] = (byte)n;
        }
        else {
            this.append(n >> 24);
            this.append(n >> 16);
            this.append(n >> 8);
            this.append(n);
        }
    }
    
    public byte[] toByteArray() {
        final int n = this._pastLen + this._currBlockPtr;
        return ByteArrayBuilder.NO_BYTES;
    }
    
    public byte[] resetAndGetFirstSegment() {
        this.reset();
        return this._currBlock;
    }
    
    public byte[] finishCurrentSegment() {
        this._allocMore();
        return this._currBlock;
    }
    
    public byte[] completeAndCoalesce(final int currBlockPtr) {
        this._currBlockPtr = currBlockPtr;
        return this.toByteArray();
    }
    
    public byte[] getCurrentSegment() {
        return this._currBlock;
    }
    
    public void setCurrentSegmentLength(final int currBlockPtr) {
        this._currBlockPtr = currBlockPtr;
    }
    
    public int getCurrentSegmentLength() {
        return this._currBlockPtr;
    }
    
    @Override
    public void write(final byte[] array) {
        this.write(array, 0, array.length);
    }
    
    @Override
    public void write(final byte[] array, int n, int n2) {
        while (true) {
            final int min = Math.min(this._currBlock.length - this._currBlockPtr, n2);
            if (min > 0) {
                System.arraycopy(array, n, this._currBlock, this._currBlockPtr, min);
                n += min;
                this._currBlockPtr += min;
                n2 -= min;
            }
            if (n2 <= 0) {
                break;
            }
            this._allocMore();
        }
    }
    
    @Override
    public void write(final int n) {
        this.append(n);
    }
    
    @Override
    public void close() {
    }
    
    @Override
    public void flush() {
    }
    
    private void _allocMore() {
        final int pastLen = this._pastLen + this._currBlock.length;
        if (pastLen < 0) {
            throw new IllegalStateException("Maximum Java array size (2GB) exceeded by `ByteArrayBuilder`");
        }
        this._pastLen = pastLen;
        int max = Math.max(this._pastLen >> 1, 1000);
        if (max > 262144) {
            max = 262144;
        }
        this._pastBlocks.add(this._currBlock);
        this._currBlock = new byte[max];
        this._currBlockPtr = 0;
    }
    
    static {
        NO_BYTES = new byte[0];
    }
}
