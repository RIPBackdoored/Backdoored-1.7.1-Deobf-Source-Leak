package com.sun.jna;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;

static final class Native$3 implements InvocationHandler {
    final /* synthetic */ Library.Handler val$handler;
    final /* synthetic */ Library val$library;
    
    Native$3(final Library.Handler val$handler, final Library val$library) {
        this.val$handler = val$handler;
        this.val$library = val$library;
        super();
    }
    
    @Override
    public Object invoke(final Object o, final Method method, final Object[] array) throws Throwable {
        synchronized (this.val$handler.getNativeLibrary()) {
            return this.val$handler.invoke(this.val$library, method, array);
        }
    }
}