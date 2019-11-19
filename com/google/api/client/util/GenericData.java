package com.google.api.client.util;

import java.util.AbstractSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Locale;
import java.util.EnumSet;
import java.util.Map;
import java.util.AbstractMap;

public class GenericData extends AbstractMap<String, Object> implements Cloneable
{
    Map<String, Object> unknownFields;
    final ClassInfo classInfo;
    
    public GenericData() {
        this(EnumSet.<Flags>noneOf(Flags.class));
    }
    
    public GenericData(final EnumSet<Flags> set) {
        super();
        this.unknownFields = (Map<String, Object>)ArrayMap.<Object, Object>create();
        this.classInfo = ClassInfo.of(this.getClass(), set.contains(Flags.IGNORE_CASE));
    }
    
    @Override
    public final Object get(final Object o) {
        if (!(o instanceof String)) {
            return null;
        }
        String lowerCase = (String)o;
        final FieldInfo fieldInfo = this.classInfo.getFieldInfo(lowerCase);
        if (fieldInfo != null) {
            return fieldInfo.getValue(this);
        }
        if (this.classInfo.getIgnoreCase()) {
            lowerCase = lowerCase.toLowerCase(Locale.US);
        }
        return this.unknownFields.get(lowerCase);
    }
    
    @Override
    public final Object put(String lowerCase, final Object o) {
        final FieldInfo fieldInfo = this.classInfo.getFieldInfo(lowerCase);
        if (fieldInfo != null) {
            final Object value = fieldInfo.getValue(this);
            fieldInfo.setValue(this, o);
            return value;
        }
        if (this.classInfo.getIgnoreCase()) {
            lowerCase = lowerCase.toLowerCase(Locale.US);
        }
        return this.unknownFields.put(lowerCase, o);
    }
    
    public GenericData set(String lowerCase, final Object o) {
        final FieldInfo fieldInfo = this.classInfo.getFieldInfo(lowerCase);
        if (fieldInfo != null) {
            fieldInfo.setValue(this, o);
        }
        else {
            if (this.classInfo.getIgnoreCase()) {
                lowerCase = lowerCase.toLowerCase(Locale.US);
            }
            this.unknownFields.put(lowerCase, o);
        }
        return this;
    }
    
    @Override
    public final void putAll(final Map<? extends String, ?> map) {
        for (final Map.Entry<String, V> entry : map.entrySet()) {
            this.set(entry.getKey(), entry.getValue());
        }
    }
    
    @Override
    public final Object remove(final Object o) {
        if (!(o instanceof String)) {
            return null;
        }
        String lowerCase = (String)o;
        if (this.classInfo.getFieldInfo(lowerCase) != null) {
            throw new UnsupportedOperationException();
        }
        if (this.classInfo.getIgnoreCase()) {
            lowerCase = lowerCase.toLowerCase(Locale.US);
        }
        return this.unknownFields.remove(lowerCase);
    }
    
    @Override
    public Set<Map.Entry<String, Object>> entrySet() {
        return new EntrySet();
    }
    
    public GenericData clone() {
        try {
            final GenericData genericData = (GenericData)super.clone();
            Data.deepCopy(this, genericData);
            genericData.unknownFields = Data.<Map<String, Object>>clone(this.unknownFields);
            return genericData;
        }
        catch (CloneNotSupportedException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    public final Map<String, Object> getUnknownKeys() {
        return this.unknownFields;
    }
    
    public final void setUnknownKeys(final Map<String, Object> unknownFields) {
        this.unknownFields = unknownFields;
    }
    
    public final ClassInfo getClassInfo() {
        return this.classInfo;
    }
    
    public /* bridge */ Object clone() throws CloneNotSupportedException {
        return this.clone();
    }
    
    @Override
    public /* bridge */ Object put(final Object o, final Object o2) {
        return this.put((String)o, o2);
    }
}
