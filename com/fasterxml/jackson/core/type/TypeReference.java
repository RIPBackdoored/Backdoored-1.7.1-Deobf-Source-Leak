package com.fasterxml.jackson.core.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeReference<T> implements Comparable<TypeReference<T>>
{
    protected final Type _type;
    
    protected TypeReference() {
        super();
        final Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
        }
        this._type = ((ParameterizedType)genericSuperclass).getActualTypeArguments()[0];
    }
    
    public Type getType() {
        return this._type;
    }
    
    @Override
    public int compareTo(final TypeReference<T> typeReference) {
        return 0;
    }
    
    @Override
    public /* bridge */ int compareTo(final Object o) {
        return this.compareTo((TypeReference)o);
    }
}
