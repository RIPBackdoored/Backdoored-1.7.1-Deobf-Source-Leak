package com.sun.jna;

import java.lang.reflect.Method;

private static final class NoVarArgsChecker extends VarArgsChecker
{
    private NoVarArgsChecker() {
        super(null);
    }
    
    @Override
    boolean isVarArgs(final Method method) {
        return false;
    }
    
    @Override
    int fixedArgs(final Method method) {
        return 0;
    }
    
    NoVarArgsChecker(final VarArgsChecker$1 object) {
        this();
    }
}
