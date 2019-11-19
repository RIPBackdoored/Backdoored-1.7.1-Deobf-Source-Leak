package com.sun.jna;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class StringArray extends Memory implements Function.PostCallRead
{
    private String encoding;
    private List<NativeString> natives;
    private Object[] original;
    
    public StringArray(final String[] array) {
        this(array, false);
    }
    
    public StringArray(final String[] array, final boolean b) {
        this((Object[])array, b ? "--WIDE-STRING--" : Native.getDefaultStringEncoding());
    }
    
    public StringArray(final String[] array, final String s) {
        this((Object[])array, s);
    }
    
    public StringArray(final WString[] array) {
        this(array, "--WIDE-STRING--");
    }
    
    private StringArray(final Object[] original, final String encoding) {
        super((original.length + 1) * Pointer.SIZE);
        this.natives = new ArrayList<NativeString>();
        this.original = original;
        this.encoding = encoding;
        for (int i = 0; i < original.length; ++i) {
            Pointer pointer = null;
            if (original[i] != null) {
                final NativeString nativeString = new NativeString(original[i].toString(), encoding);
                this.natives.add(nativeString);
                pointer = nativeString.getPointer();
            }
            this.setPointer(Pointer.SIZE * i, pointer);
        }
        this.setPointer(Pointer.SIZE * original.length, null);
    }
    
    @Override
    public void read() {
        final boolean b = this.original instanceof WString[];
        final boolean equals = "--WIDE-STRING--".equals(this.encoding);
        for (int i = 0; i < this.original.length; ++i) {
            final Pointer pointer = this.getPointer(i * Pointer.SIZE);
            String s = null;
            if (pointer != null) {
                s = (equals ? pointer.getWideString(0L) : pointer.getString(0L, this.encoding));
                if (b) {
                    s = (String)new WString(s);
                }
            }
            this.original[i] = s;
        }
    }
    
    @Override
    public String toString() {
        return ("--WIDE-STRING--".equals(this.encoding) ? "const wchar_t*[]" : "const char*[]") + Arrays.<Object>asList(this.original);
    }
}
