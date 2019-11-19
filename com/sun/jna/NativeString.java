package com.sun.jna;

import java.nio.CharBuffer;

class NativeString implements CharSequence, Comparable
{
    static final String WIDE_STRING = "--WIDE-STRING--";
    private Pointer pointer;
    private String encoding;
    
    public NativeString(final String s) {
        this(s, Native.getDefaultStringEncoding());
    }
    
    public NativeString(final String s, final boolean b) {
        this(s, b ? "--WIDE-STRING--" : Native.getDefaultStringEncoding());
    }
    
    public NativeString(final WString wString) {
        this(wString.toString(), "--WIDE-STRING--");
    }
    
    public NativeString(final String s, final String encoding) {
        super();
        if (s == null) {
            throw new NullPointerException("String must not be null");
        }
        this.encoding = encoding;
        if ("--WIDE-STRING--".equals(this.encoding)) {
            (this.pointer = new StringMemory((s.length() + 1) * Native.WCHAR_SIZE)).setWideString(0L, s);
        }
        else {
            final byte[] bytes = Native.getBytes(s, encoding);
            (this.pointer = new StringMemory(bytes.length + 1)).write(0L, bytes, 0, bytes.length);
            this.pointer.setByte(bytes.length, (byte)0);
        }
    }
    
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof CharSequence && this.compareTo(o) == 0;
    }
    
    @Override
    public String toString() {
        final boolean equals = "--WIDE-STRING--".equals(this.encoding);
        return (equals ? "const wchar_t*" : "const char*") + "(" + (equals ? this.pointer.getWideString(0L) : this.pointer.getString(0L, this.encoding)) + ")";
    }
    
    public Pointer getPointer() {
        return this.pointer;
    }
    
    @Override
    public char charAt(final int n) {
        return this.toString().charAt(n);
    }
    
    @Override
    public int length() {
        return this.toString().length();
    }
    
    @Override
    public CharSequence subSequence(final int n, final int n2) {
        return CharBuffer.wrap(this.toString()).subSequence(n, n2);
    }
    
    @Override
    public int compareTo(final Object o) {
        if (o == null) {
            return 1;
        }
        return this.toString().compareTo(o.toString());
    }
}
