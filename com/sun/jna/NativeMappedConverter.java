package com.sun.jna;

import java.util.WeakHashMap;
import java.lang.ref.SoftReference;
import java.lang.ref.Reference;
import java.util.Map;

public class NativeMappedConverter implements TypeConverter
{
    private static final Map<Class<?>, Reference<NativeMappedConverter>> converters;
    private final Class<?> type;
    private final Class<?> nativeType;
    private final NativeMapped instance;
    
    public static NativeMappedConverter getInstance(final Class<?> clazz) {
        synchronized (NativeMappedConverter.converters) {
            final Reference<NativeMappedConverter> reference = NativeMappedConverter.converters.get(clazz);
            NativeMappedConverter nativeMappedConverter = (reference != null) ? reference.get() : null;
            if (nativeMappedConverter == null) {
                nativeMappedConverter = new NativeMappedConverter(clazz);
                NativeMappedConverter.converters.put(clazz, new SoftReference<NativeMappedConverter>(nativeMappedConverter));
            }
            return nativeMappedConverter;
        }
    }
    
    public NativeMappedConverter(final Class<?> type) {
        super();
        if (!NativeMapped.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException("Type must derive from " + NativeMapped.class);
        }
        this.type = type;
        this.instance = this.defaultValue();
        this.nativeType = this.instance.nativeType();
    }
    
    public NativeMapped defaultValue() {
        try {
            return (NativeMapped)this.type.newInstance();
        }
        catch (InstantiationException ex) {
            throw new IllegalArgumentException("Can't create an instance of " + this.type + ", requires a no-arg constructor: " + ex);
        }
        catch (IllegalAccessException ex2) {
            throw new IllegalArgumentException("Not allowed to create an instance of " + this.type + ", requires a public, no-arg constructor: " + ex2);
        }
    }
    
    @Override
    public Object fromNative(final Object o, final FromNativeContext fromNativeContext) {
        return this.instance.fromNative(o, fromNativeContext);
    }
    
    @Override
    public Class<?> nativeType() {
        return this.nativeType;
    }
    
    @Override
    public Object toNative(Object defaultValue, final ToNativeContext toNativeContext) {
        if (defaultValue == null) {
            if (Pointer.class.isAssignableFrom(this.nativeType)) {
                return null;
            }
            defaultValue = this.defaultValue();
        }
        return ((NativeMapped)defaultValue).toNative();
    }
    
    static {
        converters = new WeakHashMap<Class<?>, Reference<NativeMappedConverter>>();
    }
}
