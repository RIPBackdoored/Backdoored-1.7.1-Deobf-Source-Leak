package com.google.api.client.util;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.reflect.Field;
import java.util.Map;

public final class ArrayValueMap
{
    private final Map<String, ArrayValue> keyMap;
    private final Map<Field, ArrayValue> fieldMap;
    private final Object destination;
    
    public ArrayValueMap(final Object destination) {
        super();
        this.keyMap = (Map<String, ArrayValue>)ArrayMap.<Object, Object>create();
        this.fieldMap = (Map<Field, ArrayValue>)ArrayMap.<Object, Object>create();
        this.destination = destination;
    }
    
    public void setValues() {
        for (final Map.Entry<String, ArrayValue> entry : this.keyMap.entrySet()) {
            ((Map)this.destination).put(entry.getKey(), entry.getValue().toArray());
        }
        for (final Map.Entry<Field, ArrayValue> entry2 : this.fieldMap.entrySet()) {
            FieldInfo.setFieldValue(entry2.getKey(), this.destination, entry2.getValue().toArray());
        }
    }
    
    public void put(final Field field, final Class<?> clazz, final Object o) {
        ArrayValue arrayValue = this.fieldMap.get(field);
        if (arrayValue == null) {
            arrayValue = new ArrayValue(clazz);
            this.fieldMap.put(field, arrayValue);
        }
        arrayValue.addValue(clazz, o);
    }
    
    public void put(final String s, final Class<?> clazz, final Object o) {
        ArrayValue arrayValue = this.keyMap.get(s);
        if (arrayValue == null) {
            arrayValue = new ArrayValue(clazz);
            this.keyMap.put(s, arrayValue);
        }
        arrayValue.addValue(clazz, o);
    }
}
