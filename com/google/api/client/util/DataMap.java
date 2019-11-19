package com.google.api.client.util;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Map;
import java.util.AbstractSet;
import java.util.Set;
import java.util.AbstractMap;

final class DataMap extends AbstractMap<String, Object>
{
    final Object object;
    final ClassInfo classInfo;
    
    DataMap(final Object object, final boolean b) {
        super();
        this.object = object;
        this.classInfo = ClassInfo.of(object.getClass(), b);
        Preconditions.checkArgument(!this.classInfo.isEnum());
    }
    
    @Override
    public EntrySet entrySet() {
        return new EntrySet();
    }
    
    @Override
    public boolean containsKey(final Object o) {
        return this.get(o) != null;
    }
    
    @Override
    public Object get(final Object o) {
        if (!(o instanceof String)) {
            return null;
        }
        final FieldInfo fieldInfo = this.classInfo.getFieldInfo((String)o);
        if (fieldInfo == null) {
            return null;
        }
        return fieldInfo.getValue(this.object);
    }
    
    @Override
    public Object put(final String s, final Object o) {
        final FieldInfo fieldInfo = this.classInfo.getFieldInfo(s);
        Preconditions.<FieldInfo>checkNotNull(fieldInfo, (Object)("no field of key " + s));
        final Object value = fieldInfo.getValue(this.object);
        fieldInfo.setValue(this.object, Preconditions.<Object>checkNotNull(o));
        return value;
    }
    
    @Override
    public /* bridge */ Set entrySet() {
        return this.entrySet();
    }
    
    @Override
    public /* bridge */ Object put(final Object o, final Object o2) {
        return this.put((String)o, o2);
    }
}
