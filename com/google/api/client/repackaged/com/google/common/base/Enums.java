package com.google.api.client.repackaged.com.google.common.base;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.WeakHashMap;
import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.lang.reflect.Field;
import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import java.lang.ref.WeakReference;
import java.util.Map;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
public final class Enums
{
    @GwtIncompatible
    private static final Map<Class<? extends Enum<?>>, Map<String, WeakReference<? extends Enum<?>>>> enumConstantCache;
    
    private Enums() {
        super();
    }
    
    @GwtIncompatible
    public static Field getField(final Enum<?> enum1) {
        final Class<?> declaringClass = enum1.getDeclaringClass();
        try {
            return declaringClass.getDeclaredField(enum1.name());
        }
        catch (NoSuchFieldException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    public static <T extends Enum<T>> Optional<T> getIfPresent(final Class<T> clazz, final String s) {
        Preconditions.<Class<T>>checkNotNull(clazz);
        Preconditions.<String>checkNotNull(s);
        return Platform.<T>getEnumIfPresent(clazz, s);
    }
    
    @GwtIncompatible
    private static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> populateCache(final Class<T> clazz) {
        final HashMap<String, WeakReference<? extends Enum<?>>> hashMap = new HashMap<String, WeakReference<? extends Enum<?>>>();
        for (final Enum<?> enum1 : EnumSet.<T>allOf(clazz)) {
            hashMap.put(enum1.name(), new WeakReference<Enum>(enum1));
        }
        Enums.enumConstantCache.put(clazz, hashMap);
        return hashMap;
    }
    
    @GwtIncompatible
    static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> getEnumConstants(final Class<T> clazz) {
        synchronized (Enums.enumConstantCache) {
            Map<String, WeakReference<? extends Enum<?>>> populateCache = Enums.enumConstantCache.get(clazz);
            if (populateCache == null) {
                populateCache = Enums.<Enum>populateCache((Class<Enum>)clazz);
            }
            return populateCache;
        }
    }
    
    public static <T extends Enum<T>> Converter<String, T> stringConverter(final Class<T> clazz) {
        return (Converter<String, T>)new StringConverter((Class<Enum>)clazz);
    }
    
    static {
        enumConstantCache = new WeakHashMap<Class<? extends Enum<?>>, Map<String, WeakReference<? extends Enum<?>>>>();
    }
}
