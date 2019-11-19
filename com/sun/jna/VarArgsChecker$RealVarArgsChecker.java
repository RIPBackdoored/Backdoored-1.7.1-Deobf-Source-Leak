package com.sun.jna;

import java.lang.reflect.Method;

private static final class RealVarArgsChecker extends VarArgsChecker
{
    private RealVarArgsChecker() {
        super(null);
    }
    
    @Override
    boolean isVarArgs(final Method method) {
        return method.isVarArgs();
    }
    
    @Override
    int fixedArgs(final Method method) {
        return method.isVarArgs() ? (method.getParameterTypes().length - 1) : 0;
    }
    
    RealVarArgsChecker(final VarArgsChecker$1 object) {
        this();
    }
}
