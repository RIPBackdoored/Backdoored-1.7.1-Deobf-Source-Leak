package com.google.api.client.util;

import java.util.Collection;
import java.util.ArrayList;

static class ArrayValue
{
    final Class<?> componentType;
    final ArrayList<Object> values;
    
    ArrayValue(final Class<?> componentType) {
        super();
        this.values = new ArrayList<Object>();
        this.componentType = componentType;
    }
    
    Object toArray() {
        return Types.toArray(this.values, this.componentType);
    }
    
    void addValue(final Class<?> clazz, final Object o) {
        Preconditions.checkArgument(clazz == this.componentType);
        this.values.add(o);
    }
}
