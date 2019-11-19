package com.sun.jna;

import java.util.Collections;
import java.util.WeakHashMap;
import java.nio.ByteBuffer;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;
import java.lang.ref.Reference;
import java.util.Map;

public class Memory extends Pointer
{
    private static final Map<Memory, Reference<Memory>> allocatedMemory;
    private static final WeakMemoryHolder buffers;
    protected long size;
    
    public static void purge() {
        Memory.buffers.clean();
    }
    
    public static void disposeAll() {
        final Iterator<Memory> iterator = new LinkedList<Memory>(Memory.allocatedMemory.keySet()).iterator();
        while (iterator.hasNext()) {
            iterator.next().dispose();
        }
    }
    
    public Memory(final long size) {
        super();
        this.size = size;
        if (size <= 0L) {
            throw new IllegalArgumentException("Allocation size must be greater than zero");
        }
        this.peer = malloc(size);
        if (this.peer == 0L) {
            throw new OutOfMemoryError("Cannot allocate " + size + " bytes");
        }
        Memory.allocatedMemory.put(this, new WeakReference<Memory>(this));
    }
    
    protected Memory() {
        super();
    }
    
    @Override
    public Pointer share(final long n) {
        return this.share(n, this.size() - n);
    }
    
    @Override
    public Pointer share(final long n, final long n2) {
        this.boundsCheck(n, n2);
        return new SharedMemory(n, n2);
    }
    
    public Memory align(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Byte boundary must be positive: " + n);
        }
        int i = 0;
        while (i < 32) {
            if (n == 1 << i) {
                final long n2 = ~(n - 1L);
                if ((this.peer & n2) == this.peer) {
                    return this;
                }
                final long n3 = this.peer + n - 1L & n2;
                final long n4 = this.peer + this.size - n3;
                if (n4 <= 0L) {
                    throw new IllegalArgumentException("Insufficient memory to align to the requested boundary");
                }
                return (Memory)this.share(n3 - this.peer, n4);
            }
            else {
                ++i;
            }
        }
        throw new IllegalArgumentException("Byte boundary must be a power of two");
    }
    
    @Override
    protected void finalize() {
        this.dispose();
    }
    
    protected synchronized void dispose() {
        try {
            free(this.peer);
        }
        finally {
            Memory.allocatedMemory.remove(this);
            this.peer = 0L;
        }
    }
    
    public void clear() {
        this.clear(this.size);
    }
    
    public boolean valid() {
        return this.peer != 0L;
    }
    
    public long size() {
        return this.size;
    }
    
    protected void boundsCheck(final long n, final long n2) {
        if (n < 0L) {
            throw new IndexOutOfBoundsException("Invalid offset: " + n);
        }
        if (n + n2 > this.size) {
            throw new IndexOutOfBoundsException("Bounds exceeds available space : size=" + this.size + ", offset=" + (n + n2));
        }
    }
    
    @Override
    public void read(final long n, final byte[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 1L);
        super.read(n, array, n2, n3);
    }
    
    @Override
    public void read(final long n, final short[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 2L);
        super.read(n, array, n2, n3);
    }
    
    @Override
    public void read(final long n, final char[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 2L);
        super.read(n, array, n2, n3);
    }
    
    @Override
    public void read(final long n, final int[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 4L);
        super.read(n, array, n2, n3);
    }
    
    @Override
    public void read(final long n, final long[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 8L);
        super.read(n, array, n2, n3);
    }
    
    @Override
    public void read(final long n, final float[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 4L);
        super.read(n, array, n2, n3);
    }
    
    @Override
    public void read(final long n, final double[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 8L);
        super.read(n, array, n2, n3);
    }
    
    @Override
    public void write(final long n, final byte[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 1L);
        super.write(n, array, n2, n3);
    }
    
    @Override
    public void write(final long n, final short[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 2L);
        super.write(n, array, n2, n3);
    }
    
    @Override
    public void write(final long n, final char[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 2L);
        super.write(n, array, n2, n3);
    }
    
    @Override
    public void write(final long n, final int[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 4L);
        super.write(n, array, n2, n3);
    }
    
    @Override
    public void write(final long n, final long[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 8L);
        super.write(n, array, n2, n3);
    }
    
    @Override
    public void write(final long n, final float[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 4L);
        super.write(n, array, n2, n3);
    }
    
    @Override
    public void write(final long n, final double[] array, final int n2, final int n3) {
        this.boundsCheck(n, n3 * 8L);
        super.write(n, array, n2, n3);
    }
    
    @Override
    public byte getByte(final long n) {
        this.boundsCheck(n, 1L);
        return super.getByte(n);
    }
    
    @Override
    public char getChar(final long n) {
        this.boundsCheck(n, 1L);
        return super.getChar(n);
    }
    
    @Override
    public short getShort(final long n) {
        this.boundsCheck(n, 2L);
        return super.getShort(n);
    }
    
    @Override
    public int getInt(final long n) {
        this.boundsCheck(n, 4L);
        return super.getInt(n);
    }
    
    @Override
    public long getLong(final long n) {
        this.boundsCheck(n, 8L);
        return super.getLong(n);
    }
    
    @Override
    public float getFloat(final long n) {
        this.boundsCheck(n, 4L);
        return super.getFloat(n);
    }
    
    @Override
    public double getDouble(final long n) {
        this.boundsCheck(n, 8L);
        return super.getDouble(n);
    }
    
    @Override
    public Pointer getPointer(final long n) {
        this.boundsCheck(n, Pointer.SIZE);
        return super.getPointer(n);
    }
    
    @Override
    public ByteBuffer getByteBuffer(final long n, final long n2) {
        this.boundsCheck(n, n2);
        final ByteBuffer byteBuffer = super.getByteBuffer(n, n2);
        Memory.buffers.put(byteBuffer, this);
        return byteBuffer;
    }
    
    @Override
    public String getString(final long n, final String s) {
        this.boundsCheck(n, 0L);
        return super.getString(n, s);
    }
    
    @Override
    public String getWideString(final long n) {
        this.boundsCheck(n, 0L);
        return super.getWideString(n);
    }
    
    @Override
    public void setByte(final long n, final byte b) {
        this.boundsCheck(n, 1L);
        super.setByte(n, b);
    }
    
    @Override
    public void setChar(final long n, final char c) {
        this.boundsCheck(n, Native.WCHAR_SIZE);
        super.setChar(n, c);
    }
    
    @Override
    public void setShort(final long n, final short n2) {
        this.boundsCheck(n, 2L);
        super.setShort(n, n2);
    }
    
    @Override
    public void setInt(final long n, final int n2) {
        this.boundsCheck(n, 4L);
        super.setInt(n, n2);
    }
    
    @Override
    public void setLong(final long n, final long n2) {
        this.boundsCheck(n, 8L);
        super.setLong(n, n2);
    }
    
    @Override
    public void setFloat(final long n, final float n2) {
        this.boundsCheck(n, 4L);
        super.setFloat(n, n2);
    }
    
    @Override
    public void setDouble(final long n, final double n2) {
        this.boundsCheck(n, 8L);
        super.setDouble(n, n2);
    }
    
    @Override
    public void setPointer(final long n, final Pointer pointer) {
        this.boundsCheck(n, Pointer.SIZE);
        super.setPointer(n, pointer);
    }
    
    @Override
    public void setString(final long n, final String s, final String s2) {
        this.boundsCheck(n, Native.getBytes(s, s2).length + 1L);
        super.setString(n, s, s2);
    }
    
    @Override
    public void setWideString(final long n, final String s) {
        this.boundsCheck(n, (s.length() + 1L) * Native.WCHAR_SIZE);
        super.setWideString(n, s);
    }
    
    @Override
    public String toString() {
        return "allocated@0x" + Long.toHexString(this.peer) + " (" + this.size + " bytes)";
    }
    
    protected static void free(final long n) {
        if (n != 0L) {
            Native.free(n);
        }
    }
    
    protected static long malloc(final long n) {
        return Native.malloc(n);
    }
    
    public String dump() {
        return this.dump(0L, (int)this.size());
    }
    
    static {
        allocatedMemory = Collections.<Memory, Reference<Memory>>synchronizedMap(new WeakHashMap<Memory, Reference<Memory>>());
        buffers = new WeakMemoryHolder();
    }
}
