package com.sun.jna;

import java.lang.reflect.Method;

abstract class VarArgsChecker
{
    private VarArgsChecker() {
        super();
    }
    
    static VarArgsChecker create() {
        try {
            if (Method.class.getMethod("isVarArgs", (Class<?>[])new Class[0]) != null) {
                return new RealVarArgsChecker();
            }
            return new NoVarArgsChecker();
        }
        catch (NoSuchMethodException ex) {
            return new NoVarArgsChecker();
        }
        catch (SecurityException ex2) {
            return new NoVarArgsChecker();
        }
    }
    
    abstract boolean isVarArgs(final Method p0);
    
    abstract int fixedArgs(final Method p0);
    
    VarArgsChecker(final VarArgsChecker$1 object) {
        this();
    }
}
