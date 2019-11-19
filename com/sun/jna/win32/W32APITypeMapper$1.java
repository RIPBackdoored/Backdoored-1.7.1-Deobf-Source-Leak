package com.sun.jna.win32;

import com.sun.jna.FromNativeContext;
import com.sun.jna.WString;
import com.sun.jna.StringArray;
import com.sun.jna.ToNativeContext;
import com.sun.jna.TypeConverter;

class W32APITypeMapper$1 implements TypeConverter {
    final /* synthetic */ W32APITypeMapper this$0;
    
    W32APITypeMapper$1(final W32APITypeMapper this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public Object toNative(final Object o, final ToNativeContext toNativeContext) {
        if (o == null) {
            return null;
        }
        if (o instanceof String[]) {
            return new StringArray((String[])o, true);
        }
        return new WString(o.toString());
    }
    
    @Override
    public Object fromNative(final Object o, final FromNativeContext fromNativeContext) {
        if (o == null) {
            return null;
        }
        return o.toString();
    }
    
    @Override
    public Class<?> nativeType() {
        return WString.class;
    }
}