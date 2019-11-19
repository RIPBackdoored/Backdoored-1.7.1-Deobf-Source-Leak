package com.sun.jna;

import java.util.Map;
import java.lang.reflect.Method;

class NativeLibrary$1 extends Function {
    final /* synthetic */ NativeLibrary this$0;
    
    NativeLibrary$1(final NativeLibrary this$0, final NativeLibrary nativeLibrary, final String s, final int n, final String s2) {
        this.this$0 = this$0;
        super(nativeLibrary, s, n, s2);
    }
    
    @Override
    Object invoke(final Object[] array, final Class<?> clazz, final boolean b, final int n) {
        return Native.getLastError();
    }
    
    @Override
    Object invoke(final Method method, final Class<?>[] array, final Class<?> clazz, final Object[] array2, final Map<String, ?> map) {
        return Native.getLastError();
    }
}