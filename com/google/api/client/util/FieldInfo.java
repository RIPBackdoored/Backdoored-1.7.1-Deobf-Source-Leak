package com.google.api.client.util;

import java.util.WeakHashMap;
import java.lang.reflect.Type;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import java.util.Map;

public class FieldInfo
{
    private static final Map<Field, FieldInfo> CACHE;
    private final boolean isPrimitive;
    private final Field field;
    private final String name;
    
    public static FieldInfo of(final Enum<?> enum1) {
        try {
            final FieldInfo of = of(enum1.getClass().getField(enum1.name()));
            Preconditions.checkArgument(of != null, "enum constant missing @Value or @NullValue annotation: %s", enum1);
            return of;
        }
        catch (NoSuchFieldException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static FieldInfo of(final Field field) {
        if (field == null) {
            return null;
        }
        synchronized (FieldInfo.CACHE) {
            FieldInfo fieldInfo = FieldInfo.CACHE.get(field);
            final boolean enumConstant = field.isEnumConstant();
            if (fieldInfo == null && !Modifier.isStatic(field.getModifiers())) {
                String s;
                if (enumConstant) {
                    final Value value = field.<Value>getAnnotation(Value.class);
                    if (value != null) {
                        s = value.value();
                    }
                    else {
                        if (field.<NullValue>getAnnotation(NullValue.class) == null) {
                            return null;
                        }
                        s = null;
                    }
                }
                else {
                    final Key key = field.<Key>getAnnotation(Key.class);
                    if (key == null) {
                        return null;
                    }
                    s = key.value();
                    field.setAccessible(true);
                }
                if ("##default".equals(s)) {
                    s = field.getName();
                }
                fieldInfo = new FieldInfo(field, s);
                FieldInfo.CACHE.put(field, fieldInfo);
            }
            return fieldInfo;
        }
    }
    
    FieldInfo(final Field field, final String s) {
        super();
        this.field = field;
        this.name = ((s == null) ? null : s.intern());
        this.isPrimitive = Data.isPrimitive(this.getType());
    }
    
    public Field getField() {
        return this.field;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Class<?> getType() {
        return this.field.getType();
    }
    
    public Type getGenericType() {
        return this.field.getGenericType();
    }
    
    public boolean isFinal() {
        return Modifier.isFinal(this.field.getModifiers());
    }
    
    public boolean isPrimitive() {
        return this.isPrimitive;
    }
    
    public Object getValue(final Object o) {
        return getFieldValue(this.field, o);
    }
    
    public void setValue(final Object o, final Object o2) {
        setFieldValue(this.field, o, o2);
    }
    
    public ClassInfo getClassInfo() {
        return ClassInfo.of(this.field.getDeclaringClass());
    }
    
    public <T extends Enum<T>> T enumValue() {
        return Enum.<T>valueOf(this.field.getDeclaringClass(), this.field.getName());
    }
    
    public static Object getFieldValue(final Field field, final Object o) {
        try {
            return field.get(o);
        }
        catch (IllegalAccessException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
    
    public static void setFieldValue(final Field field, final Object o, final Object o2) {
        if (Modifier.isFinal(field.getModifiers())) {
            final Object fieldValue = getFieldValue(field, o);
            if (o2 == null) {
                if (fieldValue == null) {
                    return;
                }
            }
            else if (o2.equals(fieldValue)) {
                return;
            }
            throw new IllegalArgumentException("expected final value <" + fieldValue + "> but was <" + o2 + "> on " + field.getName() + " field in " + o.getClass().getName());
        }
        try {
            field.set(o, o2);
        }
        catch (SecurityException ex) {
            throw new IllegalArgumentException(ex);
        }
        catch (IllegalAccessException ex2) {
            throw new IllegalArgumentException(ex2);
        }
    }
    
    static {
        CACHE = new WeakHashMap<Field, FieldInfo>();
    }
}
