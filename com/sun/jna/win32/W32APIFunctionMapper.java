package com.sun.jna.win32;

import java.lang.reflect.Method;
import com.sun.jna.NativeLibrary;
import com.sun.jna.FunctionMapper;

public class W32APIFunctionMapper implements FunctionMapper
{
    public static final FunctionMapper UNICODE;
    public static final FunctionMapper ASCII;
    private final String suffix;
    
    protected W32APIFunctionMapper(final boolean b) {
        super();
        this.suffix = (b ? "W" : "A");
    }
    
    @Override
    public String getFunctionName(final NativeLibrary nativeLibrary, final Method method) {
        String s = method.getName();
        if (!s.endsWith("W") && !s.endsWith("A")) {
            try {
                s = nativeLibrary.getFunction(s + this.suffix, 63).getName();
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError) {}
        }
        return s;
    }
    
    static {
        UNICODE = new W32APIFunctionMapper(true);
        ASCII = new W32APIFunctionMapper(false);
    }
}
