package com.sun.jna;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.Map;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;

public static class Handler implements InvocationHandler
{
    static final Method OBJECT_TOSTRING;
    static final Method OBJECT_HASHCODE;
    static final Method OBJECT_EQUALS;
    private final NativeLibrary nativeLibrary;
    private final Class<?> interfaceClass;
    private final Map<String, Object> options;
    private final InvocationMapper invocationMapper;
    private final Map<Method, FunctionInfo> functions;
    
    public Handler(final String s, final Class<?> interfaceClass, final Map<String, ?> map) {
        super();
        this.functions = new WeakHashMap<Method, FunctionInfo>();
        if (s != null && "".equals(s.trim())) {
            throw new IllegalArgumentException("Invalid library name \"" + s + "\"");
        }
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException(s + " does not implement an interface: " + interfaceClass.getName());
        }
        this.interfaceClass = interfaceClass;
        this.options = new HashMap<String, Object>(map);
        final int n = AltCallingConvention.class.isAssignableFrom(interfaceClass) ? 63 : 0;
        if (this.options.get("calling-convention") == null) {
            this.options.put("calling-convention", n);
        }
        if (this.options.get("classloader") == null) {
            this.options.put("classloader", interfaceClass.getClassLoader());
        }
        this.nativeLibrary = NativeLibrary.getInstance(s, this.options);
        this.invocationMapper = this.options.get("invocation-mapper");
    }
    
    public NativeLibrary getNativeLibrary() {
        return this.nativeLibrary;
    }
    
    public String getLibraryName() {
        return this.nativeLibrary.getName();
    }
    
    public Class<?> getInterfaceClass() {
        return this.interfaceClass;
    }
    
    @Override
    public Object invoke(final Object o, final Method method, Object[] concatenateVarArgs) throws Throwable {
        if (Handler.OBJECT_TOSTRING.equals(method)) {
            return "Proxy interface to " + this.nativeLibrary;
        }
        if (Handler.OBJECT_HASHCODE.equals(method)) {
            return this.hashCode();
        }
        if (Handler.OBJECT_EQUALS.equals(method)) {
            final Object o2 = concatenateVarArgs[0];
            if (o2 != null && Proxy.isProxyClass(o2.getClass())) {
                return Function.valueOf(Proxy.getInvocationHandler(o2) == this);
            }
            return Boolean.FALSE;
        }
        else {
            FunctionInfo functionInfo = this.functions.get(method);
            if (functionInfo == null) {
                synchronized (this.functions) {
                    functionInfo = this.functions.get(method);
                    if (functionInfo == null) {
                        final boolean varArgs = Function.isVarArgs(method);
                        InvocationHandler invocationHandler = null;
                        if (this.invocationMapper != null) {
                            invocationHandler = this.invocationMapper.getInvocationHandler(this.nativeLibrary, method);
                        }
                        Function function = null;
                        Class<?>[] parameterTypes = null;
                        Map<String, Method> map = null;
                        if (invocationHandler == null) {
                            function = this.nativeLibrary.getFunction(method.getName(), method);
                            parameterTypes = method.getParameterTypes();
                            map = new HashMap<String, Method>((Map<? extends String, ? extends Method>)this.options);
                            map.put("invoking-method", method);
                        }
                        functionInfo = new FunctionInfo(invocationHandler, function, parameterTypes, varArgs, map);
                        this.functions.put(method, functionInfo);
                    }
                }
            }
            if (functionInfo.isVarArgs) {
                concatenateVarArgs = Function.concatenateVarArgs(concatenateVarArgs);
            }
            if (functionInfo.handler != null) {
                return functionInfo.handler.invoke(o, method, concatenateVarArgs);
            }
            return functionInfo.function.invoke(method, functionInfo.parameterTypes, method.getReturnType(), concatenateVarArgs, functionInfo.options);
        }
    }
    
    static {
        try {
            OBJECT_TOSTRING = Object.class.getMethod("toString", (Class<?>[])new Class[0]);
            OBJECT_HASHCODE = Object.class.getMethod("hashCode", (Class<?>[])new Class[0]);
            OBJECT_EQUALS = Object.class.getMethod("equals", Object.class);
        }
        catch (Exception ex) {
            throw new Error("Error retrieving Object.toString() method");
        }
    }
}
