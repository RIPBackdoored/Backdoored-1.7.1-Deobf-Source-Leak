package com.sun.jna;

public class NativeLong extends IntegerType
{
    private static final long serialVersionUID = 1L;
    public static final int SIZE;
    
    public NativeLong() {
        this(0L);
    }
    
    public NativeLong(final long n) {
        this(n, false);
    }
    
    public NativeLong(final long n, final boolean b) {
        super(NativeLong.SIZE, n, b);
    }
    
    static {
        SIZE = Native.LONG_SIZE;
    }
}
