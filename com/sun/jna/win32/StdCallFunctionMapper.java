package com.sun.jna.win32;

import java.lang.reflect.Method;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.NativeMappedConverter;
import com.sun.jna.NativeMapped;
import com.sun.jna.FunctionMapper;

public class StdCallFunctionMapper implements FunctionMapper
{
    public StdCallFunctionMapper() {
        super();
    }
    
    protected int getArgumentNativeStackSize(Class<?> nativeType) {
        if (NativeMapped.class.isAssignableFrom(nativeType)) {
            nativeType = NativeMappedConverter.getInstance(nativeType).nativeType();
        }
        if (nativeType.isArray()) {
            return Pointer.SIZE;
        }
        try {
            return Native.getNativeSize(nativeType);
        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unknown native stack allocation size for " + nativeType);
        }
    }
    
    @Override
    public String getFunctionName(final NativeLibrary nativeLibrary, final Method method) {
        String s = method.getName();
        int n = 0;
        final Class<?>[] parameterTypes = method.getParameterTypes();
        for (int length = parameterTypes.length, i = 0; i < length; ++i) {
            n += this.getArgumentNativeStackSize(parameterTypes[i]);
        }
        final String string = s + "@" + n;
        final int n2 = 63;
        try {
            s = nativeLibrary.getFunction(string, n2).getName();
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            try {
                s = nativeLibrary.getFunction("_" + string, n2).getName();
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError2) {}
        }
        return s;
    }
}
