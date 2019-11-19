package com.sun.jna;

import java.lang.reflect.Method;
import java.security.PrivilegedAction;

static final class Native$4 implements PrivilegedAction<Method> {
    Native$4() {
        super();
    }
    
    @Override
    public Method run() {
        try {
            final Method declaredMethod = ClassLoader.class.getDeclaredMethod("findLibrary", String.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    public /* bridge */ Object run() {
        return this.run();
    }
}