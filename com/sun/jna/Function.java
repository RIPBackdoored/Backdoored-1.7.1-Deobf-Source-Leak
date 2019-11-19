package com.sun.jna;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

public class Function extends Pointer
{
    public static final int MAX_NARGS = 256;
    public static final int C_CONVENTION = 0;
    public static final int ALT_CONVENTION = 63;
    private static final int MASK_CC = 63;
    public static final int THROW_LAST_ERROR = 64;
    public static final int USE_VARARGS = 384;
    static final Integer INTEGER_TRUE;
    static final Integer INTEGER_FALSE;
    private NativeLibrary library;
    private final String functionName;
    final String encoding;
    final int callFlags;
    final Map<String, ?> options;
    static final String OPTION_INVOKING_METHOD = "invoking-method";
    private static final VarArgsChecker IS_VARARGS;
    
    public static Function getFunction(final String s, final String s2) {
        return NativeLibrary.getInstance(s).getFunction(s2);
    }
    
    public static Function getFunction(final String s, final String s2, final int n) {
        return NativeLibrary.getInstance(s).getFunction(s2, n, null);
    }
    
    public static Function getFunction(final String s, final String s2, final int n, final String s3) {
        return NativeLibrary.getInstance(s).getFunction(s2, n, s3);
    }
    
    public static Function getFunction(final Pointer pointer) {
        return getFunction(pointer, 0, null);
    }
    
    public static Function getFunction(final Pointer pointer, final int n) {
        return getFunction(pointer, n, null);
    }
    
    public static Function getFunction(final Pointer pointer, final int n, final String s) {
        return new Function(pointer, n, s);
    }
    
    Function(final NativeLibrary library, final String functionName, final int callFlags, final String s) {
        super();
        this.checkCallingConvention(callFlags & 0x3F);
        if (functionName == null) {
            throw new NullPointerException("Function name must not be null");
        }
        this.library = library;
        this.functionName = functionName;
        this.callFlags = callFlags;
        this.options = library.options;
        this.encoding = ((s != null) ? s : Native.getDefaultStringEncoding());
        try {
            this.peer = library.getSymbolAddress(functionName);
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            throw new UnsatisfiedLinkError("Error looking up function '" + functionName + "': " + unsatisfiedLinkError.getMessage());
        }
    }
    
    Function(final Pointer pointer, final int callFlags, final String s) {
        super();
        this.checkCallingConvention(callFlags & 0x3F);
        if (pointer == null || pointer.peer == 0L) {
            throw new NullPointerException("Function address may not be null");
        }
        this.functionName = pointer.toString();
        this.callFlags = callFlags;
        this.peer = pointer.peer;
        this.options = (Map<String, ?>)Collections.EMPTY_MAP;
        this.encoding = ((s != null) ? s : Native.getDefaultStringEncoding());
    }
    
    private void checkCallingConvention(final int n) throws IllegalArgumentException {
        if ((n & 0x3F) != n) {
            throw new IllegalArgumentException("Unrecognized calling convention: " + n);
        }
    }
    
    public String getName() {
        return this.functionName;
    }
    
    public int getCallingConvention() {
        return this.callFlags & 0x3F;
    }
    
    public Object invoke(final Class<?> clazz, final Object[] array) {
        return this.invoke(clazz, array, this.options);
    }
    
    public Object invoke(final Class<?> clazz, final Object[] array, final Map<String, ?> map) {
        final Method method = (Method)map.get("invoking-method");
        return this.invoke(method, (Class<?>[])((method != null) ? method.getParameterTypes() : null), clazz, array, map);
    }
    
    Object invoke(final Method method, final Class<?>[] array, final Class<?> clazz, final Object[] array2, final Map<String, ?> map) {
        Object[] array3 = new Object[0];
        if (array2 != null) {
            if (array2.length > 256) {
                throw new UnsupportedOperationException("Maximum argument count is 256");
            }
            array3 = new Object[array2.length];
            System.arraycopy(array2, 0, array3, 0, array3.length);
        }
        final TypeMapper typeMapper = (TypeMapper)map.get("type-mapper");
        final boolean equals = Boolean.TRUE.equals(map.get("allow-objects"));
        final boolean b = array3.length > 0 && method != null && isVarArgs(method);
        final int n = (array3.length > 0 && method != null) ? fixedArgs(method) : 0;
        for (int i = 0; i < array3.length; ++i) {
            array3[i] = this.convertArgument(array3, i, method, typeMapper, equals, (method != null) ? ((b && i >= array.length - 1) ? array[array.length - 1].getComponentType() : array[i]) : null);
        }
        Class<?> clazz2 = clazz;
        FromNativeConverter fromNativeConverter = null;
        if (NativeMapped.class.isAssignableFrom(clazz)) {
            clazz2 = ((NativeMappedConverter)(fromNativeConverter = NativeMappedConverter.getInstance(clazz))).nativeType();
        }
        else if (typeMapper != null) {
            fromNativeConverter = typeMapper.getFromNativeConverter(clazz);
            if (fromNativeConverter != null) {
                clazz2 = fromNativeConverter.nativeType();
            }
        }
        Object o = this.invoke(array3, clazz2, equals, n);
        if (fromNativeConverter != null) {
            FunctionResultContext functionResultContext;
            if (method != null) {
                functionResultContext = new MethodResultContext(clazz, this, array2, method);
            }
            else {
                functionResultContext = new FunctionResultContext(clazz, this, array2);
            }
            o = fromNativeConverter.fromNative(o, functionResultContext);
        }
        if (array2 != null) {
            for (int j = 0; j < array2.length; ++j) {
                final Object o2 = array2[j];
                if (o2 != null) {
                    if (o2 instanceof Structure) {
                        if (!(o2 instanceof Structure.ByValue)) {
                            ((Structure)o2).autoRead();
                        }
                    }
                    else if (array3[j] instanceof PostCallRead) {
                        ((PostCallRead)array3[j]).read();
                        if (array3[j] instanceof PointerArray) {
                            final PointerArray pointerArray = (PointerArray)array3[j];
                            if (Structure.ByReference[].class.isAssignableFrom(((Structure)o2).getClass())) {
                                final Class<?> componentType = ((Structure)o2).getClass().getComponentType();
                                final Structure[] array4 = (Structure[])o2;
                                for (int k = 0; k < array4.length; ++k) {
                                    array4[k] = Structure.updateStructureByReference(componentType, array4[k], pointerArray.getPointer(Pointer.SIZE * k));
                                }
                            }
                        }
                    }
                    else if (Structure[].class.isAssignableFrom(((Structure[])o2).getClass())) {
                        Structure.autoRead((Structure[])o2);
                    }
                }
            }
        }
        return o;
    }
    
    Object invoke(final Object[] array, final Class<?> clazz, final boolean b) {
        return this.invoke(array, clazz, b, 0);
    }
    
    Object invoke(final Object[] array, final Class<?> clazz, final boolean b, final int n) {
        Object o = null;
        final int n2 = this.callFlags | (n & 0x3) << 7;
        if (clazz == null || clazz == Void.TYPE || clazz == Void.class) {
            Native.invokeVoid(this, this.peer, n2, array);
            o = null;
        }
        else if (clazz == Boolean.TYPE || clazz == Boolean.class) {
            o = valueOf(Native.invokeInt(this, this.peer, n2, array) != 0);
        }
        else if (clazz == Byte.TYPE || clazz == Byte.class) {
            o = (byte)Native.invokeInt(this, this.peer, n2, array);
        }
        else if (clazz == Short.TYPE || clazz == Short.class) {
            o = (short)Native.invokeInt(this, this.peer, n2, array);
        }
        else if (clazz == Character.TYPE || clazz == Character.class) {
            o = (char)Native.invokeInt(this, this.peer, n2, array);
        }
        else if (clazz == Integer.TYPE || clazz == Integer.class) {
            o = Native.invokeInt(this, this.peer, n2, array);
        }
        else if (clazz == Long.TYPE || clazz == Long.class) {
            o = Native.invokeLong(this, this.peer, n2, array);
        }
        else if (clazz == Float.TYPE || clazz == Float.class) {
            o = Native.invokeFloat(this, this.peer, n2, array);
        }
        else if (clazz == Double.TYPE || clazz == Double.class) {
            o = Native.invokeDouble(this, this.peer, n2, array);
        }
        else if (clazz == String.class) {
            o = this.invokeString(n2, array, false);
        }
        else if (clazz == WString.class) {
            final String invokeString = this.invokeString(n2, array, true);
            if (invokeString != null) {
                o = new WString(invokeString);
            }
        }
        else {
            if (Pointer.class.isAssignableFrom(clazz)) {
                return this.invokePointer(n2, array);
            }
            if (Structure.class.isAssignableFrom(clazz)) {
                if (Structure.ByValue.class.isAssignableFrom(clazz)) {
                    final Structure invokeStructure = Native.invokeStructure(this, this.peer, n2, array, Structure.newInstance(clazz));
                    invokeStructure.autoRead();
                    o = invokeStructure;
                }
                else {
                    o = this.invokePointer(n2, array);
                    if (o != null) {
                        final Structure instance = Structure.newInstance(clazz, (Pointer)o);
                        instance.conditionalAutoRead();
                        o = instance;
                    }
                }
            }
            else if (Callback.class.isAssignableFrom(clazz)) {
                o = this.invokePointer(n2, array);
                if (o != null) {
                    o = CallbackReference.getCallback(clazz, (Pointer)o);
                }
            }
            else if (clazz == String[].class) {
                final Pointer invokePointer = this.invokePointer(n2, array);
                if (invokePointer != null) {
                    o = invokePointer.getStringArray(0L, this.encoding);
                }
            }
            else if (clazz == WString[].class) {
                final Pointer invokePointer2 = this.invokePointer(n2, array);
                if (invokePointer2 != null) {
                    final String[] wideStringArray = invokePointer2.getWideStringArray(0L);
                    final WString[] array2 = new WString[wideStringArray.length];
                    for (int i = 0; i < wideStringArray.length; ++i) {
                        array2[i] = new WString(wideStringArray[i]);
                    }
                    o = array2;
                }
            }
            else if (clazz == Pointer[].class) {
                final Pointer invokePointer3 = this.invokePointer(n2, array);
                if (invokePointer3 != null) {
                    o = invokePointer3.getPointerArray(0L);
                }
            }
            else {
                if (!b) {
                    throw new IllegalArgumentException("Unsupported return type " + clazz + " in function " + this.getName());
                }
                o = Native.invokeObject(this, this.peer, n2, array);
                if (o != null && !clazz.isAssignableFrom(((Pointer)o).getClass())) {
                    throw new ClassCastException("Return type " + clazz + " does not match result " + ((Pointer)o).getClass());
                }
            }
        }
        return o;
    }
    
    private Pointer invokePointer(final int n, final Object[] array) {
        final long invokePointer = Native.invokePointer(this, this.peer, n, array);
        return (invokePointer == 0L) ? null : new Pointer(invokePointer);
    }
    
    private Object convertArgument(final Object[] array, final int n, final Method method, final TypeMapper typeMapper, final boolean b, final Class<?> clazz) {
        Object native1 = array[n];
        if (native1 != null) {
            final Class<? extends Structure> class1 = ((Structure)native1).getClass();
            ToNativeConverter toNativeConverter = null;
            if (NativeMapped.class.isAssignableFrom(class1)) {
                toNativeConverter = NativeMappedConverter.getInstance(class1);
            }
            else if (typeMapper != null) {
                toNativeConverter = typeMapper.getToNativeConverter(class1);
            }
            if (toNativeConverter != null) {
                FunctionParameterContext functionParameterContext;
                if (method != null) {
                    functionParameterContext = new MethodParameterContext(this, array, n, method);
                }
                else {
                    functionParameterContext = new FunctionParameterContext(this, array, n);
                }
                native1 = toNativeConverter.toNative(native1, functionParameterContext);
            }
        }
        if (native1 == null || this.isPrimitiveArray(((Structure)native1).getClass())) {
            return native1;
        }
        final Class<? extends Structure> class2 = ((Structure)native1).getClass();
        if (native1 instanceof Structure) {
            final Structure structure = (Structure)native1;
            structure.autoWrite();
            if (structure instanceof Structure.ByValue) {
                Class<?> class3 = structure.getClass();
                if (method != null) {
                    final Class<?>[] parameterTypes = method.getParameterTypes();
                    if (Function.IS_VARARGS.isVarArgs(method)) {
                        if (n < parameterTypes.length - 1) {
                            class3 = parameterTypes[n];
                        }
                        else {
                            final Class<?> componentType = parameterTypes[parameterTypes.length - 1].getComponentType();
                            if (componentType != Object.class) {
                                class3 = componentType;
                            }
                        }
                    }
                    else {
                        class3 = parameterTypes[n];
                    }
                }
                if (Structure.ByValue.class.isAssignableFrom(class3)) {
                    return structure;
                }
            }
            return structure.getPointer();
        }
        if (native1 instanceof Callback) {
            return CallbackReference.getFunctionPointer((Callback)native1);
        }
        if (native1 instanceof String) {
            return new NativeString((String)native1, false).getPointer();
        }
        if (native1 instanceof WString) {
            return new NativeString(native1.toString(), true).getPointer();
        }
        if (native1 instanceof Boolean) {
            return Boolean.TRUE.equals(native1) ? Function.INTEGER_TRUE : Function.INTEGER_FALSE;
        }
        if (String[].class == class2) {
            return new StringArray((String[])native1, this.encoding);
        }
        if (WString[].class == class2) {
            return new StringArray((WString[])native1);
        }
        if (Pointer[].class == class2) {
            return new PointerArray((Pointer[])native1);
        }
        if (NativeMapped[].class.isAssignableFrom(class2)) {
            return new NativeMappedArray((NativeMapped[])native1);
        }
        if (Structure[].class.isAssignableFrom(class2)) {
            final Structure[] array2 = (Structure[])native1;
            final Class componentType2 = class2.getComponentType();
            final boolean assignable = Structure.ByReference.class.isAssignableFrom(componentType2);
            if (clazz != null && !Structure.ByReference[].class.isAssignableFrom(clazz)) {
                if (assignable) {
                    throw new IllegalArgumentException("Function " + this.getName() + " declared Structure[] at parameter " + n + " but array of " + componentType2 + " was passed");
                }
                for (int i = 0; i < array2.length; ++i) {
                    if (array2[i] instanceof Structure.ByReference) {
                        throw new IllegalArgumentException("Function " + this.getName() + " declared Structure[] at parameter " + n + " but element " + i + " is of Structure.ByReference type");
                    }
                }
            }
            if (assignable) {
                Structure.autoWrite(array2);
                final Pointer[] array3 = new Pointer[array2.length + 1];
                for (int j = 0; j < array2.length; ++j) {
                    array3[j] = ((array2[j] != null) ? array2[j].getPointer() : null);
                }
                return new PointerArray(array3);
            }
            if (array2.length == 0) {
                throw new IllegalArgumentException("Structure array must have non-zero length");
            }
            if (array2[0] == null) {
                Structure.newInstance(componentType2).toArray(array2);
                return array2[0].getPointer();
            }
            Structure.autoWrite(array2);
            return array2[0].getPointer();
        }
        else {
            if (class2.isArray()) {
                throw new IllegalArgumentException("Unsupported array argument type: " + class2.getComponentType());
            }
            if (b) {
                return native1;
            }
            if (!Native.isSupportedNativeType(((Structure[])native1).getClass())) {
                throw new IllegalArgumentException("Unsupported argument type " + ((Structure[])native1).getClass().getName() + " at parameter " + n + " of function " + this.getName());
            }
            return native1;
        }
    }
    
    private boolean isPrimitiveArray(final Class<?> clazz) {
        return clazz.isArray() && clazz.getComponentType().isPrimitive();
    }
    
    public void invoke(final Object[] array) {
        this.invoke(Void.class, array);
    }
    
    private String invokeString(final int n, final Object[] array, final boolean b) {
        final Pointer invokePointer = this.invokePointer(n, array);
        String s = null;
        if (invokePointer != null) {
            if (b) {
                s = invokePointer.getWideString(0L);
            }
            else {
                s = invokePointer.getString(0L, this.encoding);
            }
        }
        return s;
    }
    
    @Override
    public String toString() {
        if (this.library != null) {
            return "native function " + this.functionName + "(" + this.library.getName() + ")@0x" + Long.toHexString(this.peer);
        }
        return "native function@0x" + Long.toHexString(this.peer);
    }
    
    public Object invokeObject(final Object[] array) {
        return this.invoke(Object.class, array);
    }
    
    public Pointer invokePointer(final Object[] array) {
        return (Pointer)this.invoke(Pointer.class, array);
    }
    
    public String invokeString(final Object[] array, final boolean b) {
        final Object invoke = this.invoke((Class<?>)(b ? WString.class : String.class), array);
        return (invoke != null) ? invoke.toString() : null;
    }
    
    public int invokeInt(final Object[] array) {
        return (int)this.invoke(Integer.class, array);
    }
    
    public long invokeLong(final Object[] array) {
        return (long)this.invoke(Long.class, array);
    }
    
    public float invokeFloat(final Object[] array) {
        return (float)this.invoke(Float.class, array);
    }
    
    public double invokeDouble(final Object[] array) {
        return (double)this.invoke(Double.class, array);
    }
    
    public void invokeVoid(final Object[] array) {
        this.invoke(Void.class, array);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() == this.getClass()) {
            final Function function = (Function)o;
            return function.callFlags == this.callFlags && function.options.equals(this.options) && function.peer == this.peer;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.callFlags + this.options.hashCode() + super.hashCode();
    }
    
    static Object[] concatenateVarArgs(Object[] array) {
        if (array != null && array.length > 0) {
            final Object o = array[array.length - 1];
            final Class<? extends Object[]> clazz = (o != null) ? ((Object[])o).getClass() : null;
            if (clazz != null && clazz.isArray()) {
                final Object[] array2 = (Object[])o;
                for (int i = 0; i < array2.length; ++i) {
                    if (array2[i] instanceof Float) {
                        array2[i] = array2[i];
                    }
                }
                final Object[] array3 = new Object[array.length + array2.length];
                System.arraycopy(array, 0, array3, 0, array.length - 1);
                System.arraycopy(array2, 0, array3, array.length - 1, array2.length);
                array3[array3.length - 1] = null;
                array = array3;
            }
        }
        return array;
    }
    
    static boolean isVarArgs(final Method method) {
        return Function.IS_VARARGS.isVarArgs(method);
    }
    
    static int fixedArgs(final Method method) {
        return Function.IS_VARARGS.fixedArgs(method);
    }
    
    static Boolean valueOf(final boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }
    
    static {
        INTEGER_TRUE = -1;
        INTEGER_FALSE = 0;
        IS_VARARGS = VarArgsChecker.create();
    }
}
