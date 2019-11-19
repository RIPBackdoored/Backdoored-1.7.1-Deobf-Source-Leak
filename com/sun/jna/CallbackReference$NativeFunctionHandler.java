package com.sun.jna;

import java.lang.reflect.Proxy;
import java.lang.reflect.Method;
import java.util.Map;
import java.lang.reflect.InvocationHandler;

private static class NativeFunctionHandler implements InvocationHandler
{
    private final Function function;
    private final Map<String, ?> options;
    
    public NativeFunctionHandler(final Pointer pointer, final int n, final Map<String, ?> options) {
        super();
        this.options = options;
        this.function = new Function(pointer, n, (String)options.get("string-encoding"));
    }
    
    @Override
    public Object invoke(final Object o, final Method method, Object[] concatenateVarArgs) throws Throwable {
        if (Library.Handler.OBJECT_TOSTRING.equals(method)) {
            return "Proxy interface to " + this.function + " (" + CallbackReference.findCallbackClass(((Method)this.options.get("invoking-method")).getDeclaringClass()).getName() + ")";
        }
        if (Library.Handler.OBJECT_HASHCODE.equals(method)) {
            return this.hashCode();
        }
        if (!Library.Handler.OBJECT_EQUALS.equals(method)) {
            if (Function.isVarArgs(method)) {
                concatenateVarArgs = Function.concatenateVarArgs(concatenateVarArgs);
            }
            return this.function.invoke(method.getReturnType(), concatenateVarArgs, this.options);
        }
        final Object o2 = concatenateVarArgs[0];
        if (o2 != null && Proxy.isProxyClass(o2.getClass())) {
            return Function.valueOf(Proxy.getInvocationHandler(o2) == this);
        }
        return Boolean.FALSE;
    }
    
    public Pointer getPointer() {
        return this.function;
    }
}
