package com.sun.jna;

import java.nio.ByteBuffer;

private static class Opaque extends Pointer
{
    private final String MSG;
    
    private Opaque(final long n) {
        super(n);
        this.MSG = "This pointer is opaque: " + this;
    }
    
    @Override
    public Pointer share(final long n, final long n2) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void clear(final long n) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public long indexOf(final long n, final byte b) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void read(final long n, final byte[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void read(final long n, final char[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void read(final long n, final short[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void read(final long n, final int[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void read(final long n, final long[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void read(final long n, final float[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void read(final long n, final double[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void read(final long n, final Pointer[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void write(final long n, final byte[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void write(final long n, final char[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void write(final long n, final short[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void write(final long n, final int[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void write(final long n, final long[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void write(final long n, final float[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void write(final long n, final double[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void write(final long n, final Pointer[] array, final int n2, final int n3) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public ByteBuffer getByteBuffer(final long n, final long n2) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public byte getByte(final long n) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public char getChar(final long n) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public short getShort(final long n) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public int getInt(final long n) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public long getLong(final long n) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public float getFloat(final long n) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public double getDouble(final long n) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public Pointer getPointer(final long n) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public String getString(final long n, final String s) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public String getWideString(final long n) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void setByte(final long n, final byte b) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void setChar(final long n, final char c) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void setShort(final long n, final short n2) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void setInt(final long n, final int n2) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void setLong(final long n, final long n2) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void setFloat(final long n, final float n2) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void setDouble(final long n, final double n2) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void setPointer(final long n, final Pointer pointer) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void setString(final long n, final String s, final String s2) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void setWideString(final long n, final String s) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public void setMemory(final long n, final long n2, final byte b) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public String dump(final long n, final int n2) {
        throw new UnsupportedOperationException(this.MSG);
    }
    
    @Override
    public String toString() {
        return "const@0x" + Long.toHexString(this.peer);
    }
    
    Opaque(final long n, final Pointer$1 object) {
        this(n);
    }
}
