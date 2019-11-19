package com.sun.jna.win32;

import com.sun.jna.FromNativeContext;
import com.sun.jna.ToNativeContext;
import com.sun.jna.TypeConverter;

class W32APITypeMapper$2 implements TypeConverter {
    final /* synthetic */ W32APITypeMapper this$0;
    
    W32APITypeMapper$2(final W32APITypeMapper this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public Object toNative(final Object o, final ToNativeContext toNativeContext) {
        return Boolean.TRUE.equals(o) ? 1 : 0;
    }
    
    @Override
    public Object fromNative(final Object o, final FromNativeContext fromNativeContext) {
        return ((int)o != 0) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    @Override
    public Class<?> nativeType() {
        return Integer.class;
    }
}