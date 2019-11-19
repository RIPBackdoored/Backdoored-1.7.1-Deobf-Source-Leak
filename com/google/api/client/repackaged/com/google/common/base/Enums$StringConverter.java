package com.google.api.client.repackaged.com.google.common.base;

import javax.annotation.Nullable;
import java.io.Serializable;

private static final class StringConverter<T extends Enum<T>> extends Converter<String, T> implements Serializable
{
    private final Class<T> enumClass;
    private static final long serialVersionUID = 0L;
    
    StringConverter(final Class<T> clazz) {
        super();
        this.enumClass = Preconditions.<Class<T>>checkNotNull(clazz);
    }
    
    @Override
    protected T doForward(final String s) {
        return Enum.<T>valueOf(this.enumClass, s);
    }
    
    @Override
    protected String doBackward(final T t) {
        return t.name();
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        return o instanceof StringConverter && this.enumClass.equals(((StringConverter)o).enumClass);
    }
    
    @Override
    public int hashCode() {
        return this.enumClass.hashCode();
    }
    
    @Override
    public String toString() {
        return "Enums.stringConverter(" + this.enumClass.getName() + ".class)";
    }
    
    @Override
    protected /* bridge */ Object doBackward(final Object o) {
        return this.doBackward((T)o);
    }
    
    @Override
    protected /* bridge */ Object doForward(final Object o) {
        return this.doForward((String)o);
    }
}
