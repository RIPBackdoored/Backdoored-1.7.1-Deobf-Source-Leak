package com.sun.jna;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class DefaultTypeMapper implements TypeMapper
{
    private List<Entry> toNativeConverters;
    private List<Entry> fromNativeConverters;
    
    public DefaultTypeMapper() {
        super();
        this.toNativeConverters = new ArrayList<Entry>();
        this.fromNativeConverters = new ArrayList<Entry>();
    }
    
    private Class<?> getAltClass(final Class<?> clazz) {
        if (clazz == Boolean.class) {
            return Boolean.TYPE;
        }
        if (clazz == Boolean.TYPE) {
            return Boolean.class;
        }
        if (clazz == Byte.class) {
            return Byte.TYPE;
        }
        if (clazz == Byte.TYPE) {
            return Byte.class;
        }
        if (clazz == Character.class) {
            return Character.TYPE;
        }
        if (clazz == Character.TYPE) {
            return Character.class;
        }
        if (clazz == Short.class) {
            return Short.TYPE;
        }
        if (clazz == Short.TYPE) {
            return Short.class;
        }
        if (clazz == Integer.class) {
            return Integer.TYPE;
        }
        if (clazz == Integer.TYPE) {
            return Integer.class;
        }
        if (clazz == Long.class) {
            return Long.TYPE;
        }
        if (clazz == Long.TYPE) {
            return Long.class;
        }
        if (clazz == Float.class) {
            return Float.TYPE;
        }
        if (clazz == Float.TYPE) {
            return Float.class;
        }
        if (clazz == Double.class) {
            return Double.TYPE;
        }
        if (clazz == Double.TYPE) {
            return Double.class;
        }
        return null;
    }
    
    public void addToNativeConverter(final Class<?> clazz, final ToNativeConverter toNativeConverter) {
        this.toNativeConverters.add(new Entry(clazz, toNativeConverter));
        final Class<?> altClass = this.getAltClass(clazz);
        if (altClass != null) {
            this.toNativeConverters.add(new Entry(altClass, toNativeConverter));
        }
    }
    
    public void addFromNativeConverter(final Class<?> clazz, final FromNativeConverter fromNativeConverter) {
        this.fromNativeConverters.add(new Entry(clazz, fromNativeConverter));
        final Class<?> altClass = this.getAltClass(clazz);
        if (altClass != null) {
            this.fromNativeConverters.add(new Entry(altClass, fromNativeConverter));
        }
    }
    
    public void addTypeConverter(final Class<?> clazz, final TypeConverter typeConverter) {
        this.addFromNativeConverter(clazz, typeConverter);
        this.addToNativeConverter(clazz, typeConverter);
    }
    
    private Object lookupConverter(final Class<?> clazz, final Collection<? extends Entry> collection) {
        for (final Entry entry : collection) {
            if (entry.type.isAssignableFrom(clazz)) {
                return entry.converter;
            }
        }
        return null;
    }
    
    @Override
    public FromNativeConverter getFromNativeConverter(final Class<?> clazz) {
        return (FromNativeConverter)this.lookupConverter(clazz, this.fromNativeConverters);
    }
    
    @Override
    public ToNativeConverter getToNativeConverter(final Class<?> clazz) {
        return (ToNativeConverter)this.lookupConverter(clazz, this.toNativeConverters);
    }
}
