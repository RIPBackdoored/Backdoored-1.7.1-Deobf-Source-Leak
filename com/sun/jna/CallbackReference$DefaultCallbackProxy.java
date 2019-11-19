package com.sun.jna;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

private class DefaultCallbackProxy implements CallbackProxy
{
    private final Method callbackMethod;
    private ToNativeConverter toNative;
    private final FromNativeConverter[] fromNative;
    private final String encoding;
    final /* synthetic */ CallbackReference this$0;
    
    public DefaultCallbackProxy(final CallbackReference this$0, final Method callbackMethod, final TypeMapper typeMapper, final String encoding) {
        this.this$0 = this$0;
        super();
        this.callbackMethod = callbackMethod;
        this.encoding = encoding;
        final Class<?>[] parameterTypes = callbackMethod.getParameterTypes();
        final Class<?> returnType = callbackMethod.getReturnType();
        this.fromNative = new FromNativeConverter[parameterTypes.length];
        if (NativeMapped.class.isAssignableFrom(returnType)) {
            this.toNative = NativeMappedConverter.getInstance(returnType);
        }
        else if (typeMapper != null) {
            this.toNative = typeMapper.getToNativeConverter(returnType);
        }
        for (int i = 0; i < this.fromNative.length; ++i) {
            if (NativeMapped.class.isAssignableFrom(parameterTypes[i])) {
                this.fromNative[i] = new NativeMappedConverter(parameterTypes[i]);
            }
            else if (typeMapper != null) {
                this.fromNative[i] = typeMapper.getFromNativeConverter(parameterTypes[i]);
            }
        }
        if (!callbackMethod.isAccessible()) {
            try {
                callbackMethod.setAccessible(true);
            }
            catch (SecurityException ex) {
                throw new IllegalArgumentException("Callback method is inaccessible, make sure the interface is public: " + callbackMethod);
            }
        }
    }
    
    public Callback getCallback() {
        return CallbackReference.access$000(this.this$0);
    }
    
    private Object invokeCallback(final Object[] array) {
        final Class<?>[] parameterTypes = this.callbackMethod.getParameterTypes();
        final Object[] array2 = new Object[array.length];
        for (int i = 0; i < array.length; ++i) {
            final Class<?> clazz = parameterTypes[i];
            final Object o = array[i];
            if (this.fromNative[i] != null) {
                array2[i] = this.fromNative[i].fromNative(o, new CallbackParameterContext(clazz, this.callbackMethod, array, i));
            }
            else {
                array2[i] = this.convertArgument(o, clazz);
            }
        }
        Object convertResult = null;
        final Callback callback = this.getCallback();
        if (callback != null) {
            try {
                convertResult = this.convertResult(this.callbackMethod.invoke(callback, array2));
            }
            catch (IllegalArgumentException ex) {
                Native.getCallbackExceptionHandler().uncaughtException(callback, ex);
            }
            catch (IllegalAccessException ex2) {
                Native.getCallbackExceptionHandler().uncaughtException(callback, ex2);
            }
            catch (InvocationTargetException ex3) {
                Native.getCallbackExceptionHandler().uncaughtException(callback, ex3.getTargetException());
            }
        }
        for (int j = 0; j < array2.length; ++j) {
            if (array2[j] instanceof Structure && !(array2[j] instanceof Structure.ByValue)) {
                ((Structure)array2[j]).autoWrite();
            }
        }
        return convertResult;
    }
    
    @Override
    public Object callback(final Object[] array) {
        try {
            return this.invokeCallback(array);
        }
        catch (Throwable t) {
            Native.getCallbackExceptionHandler().uncaughtException(this.getCallback(), t);
            return null;
        }
    }
    
    private Object convertArgument(Object o, final Class<?> clazz) {
        if (o instanceof Pointer) {
            if (clazz == String.class) {
                o = ((Pointer)o).getString(0L, this.encoding);
            }
            else if (clazz == WString.class) {
                o = new WString(((Pointer)o).getWideString(0L));
            }
            else if (clazz == String[].class) {
                o = ((Pointer)o).getStringArray(0L, this.encoding);
            }
            else if (clazz == WString[].class) {
                o = ((Pointer)o).getWideStringArray(0L);
            }
            else if (Callback.class.isAssignableFrom(clazz)) {
                o = CallbackReference.getCallback(clazz, (Pointer)o);
            }
            else if (Structure.class.isAssignableFrom(clazz)) {
                if (Structure.ByValue.class.isAssignableFrom(clazz)) {
                    final Structure instance = Structure.newInstance(clazz);
                    final byte[] array = new byte[instance.size()];
                    ((Pointer)o).read(0L, array, 0, array.length);
                    instance.getPointer().write(0L, array, 0, array.length);
                    instance.read();
                    o = instance;
                }
                else {
                    final Structure instance2 = Structure.newInstance(clazz, (Pointer)o);
                    instance2.conditionalAutoRead();
                    o = instance2;
                }
            }
        }
        else if ((Boolean.TYPE == clazz || Boolean.class == clazz) && o instanceof Number) {
            o = Function.valueOf(((Number)o).intValue() != 0);
        }
        return o;
    }
    
    private Object convertResult(Object native1) {
        if (this.toNative != null) {
            native1 = this.toNative.toNative(native1, new CallbackResultContext(this.callbackMethod));
        }
        if (native1 == null) {
            return null;
        }
        final Class<?> class1 = native1.getClass();
        if (Structure.class.isAssignableFrom(class1)) {
            if (Structure.ByValue.class.isAssignableFrom(class1)) {
                return native1;
            }
            return ((Structure)native1).getPointer();
        }
        else {
            if (class1 == Boolean.TYPE || class1 == Boolean.class) {
                return Boolean.TRUE.equals(native1) ? Function.INTEGER_TRUE : Function.INTEGER_FALSE;
            }
            if (class1 == String.class || class1 == WString.class) {
                return CallbackReference.access$100(native1, class1 == WString.class);
            }
            if (class1 == String[].class || class1 == WString.class) {
                final StringArray stringArray = (class1 == String[].class) ? new StringArray((String[])native1, this.encoding) : new StringArray((WString[])native1);
                CallbackReference.allocations.put(native1, stringArray);
                return stringArray;
            }
            if (Callback.class.isAssignableFrom(class1)) {
                return CallbackReference.getFunctionPointer((Callback)native1);
            }
            return native1;
        }
    }
    
    @Override
    public Class<?>[] getParameterTypes() {
        return this.callbackMethod.getParameterTypes();
    }
    
    @Override
    public Class<?> getReturnType() {
        return this.callbackMethod.getReturnType();
    }
}
