package com.sun.jna;

import java.util.Map;
import java.lang.reflect.InvocationHandler;

private static final class FunctionInfo
{
    final InvocationHandler handler;
    final Function function;
    final boolean isVarArgs;
    final Map<String, ?> options;
    final Class<?>[] parameterTypes;
    
    FunctionInfo(final InvocationHandler handler, final Function function, final Class<?>[] parameterTypes, final boolean isVarArgs, final Map<String, ?> options) {
        super();
        this.handler = handler;
        this.function = function;
        this.isVarArgs = isVarArgs;
        this.options = options;
        this.parameterTypes = parameterTypes;
    }
}
