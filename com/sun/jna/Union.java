package com.sun.jna;

import java.util.Iterator;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class Union extends Structure
{
    private StructField activeField;
    
    protected Union() {
        super();
    }
    
    protected Union(final Pointer pointer) {
        super(pointer);
    }
    
    protected Union(final Pointer pointer, final int n) {
        super(pointer, n);
    }
    
    protected Union(final TypeMapper typeMapper) {
        super(typeMapper);
    }
    
    protected Union(final Pointer pointer, final int n, final TypeMapper typeMapper) {
        super(pointer, n, typeMapper);
    }
    
    @Override
    protected List<String> getFieldOrder() {
        final List<Field> fieldList = this.getFieldList();
        final ArrayList list = new ArrayList<String>(fieldList.size());
        final Iterator<Field> iterator = fieldList.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().getName());
        }
        return (List<String>)list;
    }
    
    public void setType(final Class<?> clazz) {
        this.ensureAllocated();
        for (final StructField activeField : this.fields().values()) {
            if (activeField.type == clazz) {
                this.activeField = activeField;
                return;
            }
        }
        throw new IllegalArgumentException("No field of type " + clazz + " in " + this);
    }
    
    public void setType(final String s) {
        this.ensureAllocated();
        final StructField activeField = this.fields().get(s);
        if (activeField != null) {
            this.activeField = activeField;
            return;
        }
        throw new IllegalArgumentException("No field named " + s + " in " + this);
    }
    
    @Override
    public Object readField(final String type) {
        this.ensureAllocated();
        this.setType(type);
        return super.readField(type);
    }
    
    @Override
    public void writeField(final String type) {
        this.ensureAllocated();
        this.setType(type);
        super.writeField(type);
    }
    
    @Override
    public void writeField(final String type, final Object o) {
        this.ensureAllocated();
        this.setType(type);
        super.writeField(type, o);
    }
    
    public Object getTypedValue(final Class<?> clazz) {
        this.ensureAllocated();
        for (final StructField activeField : this.fields().values()) {
            if (activeField.type == clazz) {
                this.activeField = activeField;
                this.read();
                return this.getFieldValue(this.activeField.field);
            }
        }
        throw new IllegalArgumentException("No field of type " + clazz + " in " + this);
    }
    
    public Object setTypedValue(final Object o) {
        final StructField field = this.findField(o.getClass());
        if (field != null) {
            this.activeField = field;
            this.setFieldValue(field.field, o);
            return this;
        }
        throw new IllegalArgumentException("No field of type " + o.getClass() + " in " + this);
    }
    
    private StructField findField(final Class<?> clazz) {
        this.ensureAllocated();
        for (final StructField structField : this.fields().values()) {
            if (structField.type.isAssignableFrom(clazz)) {
                return structField;
            }
        }
        return null;
    }
    
    @Override
    protected void writeField(final StructField structField) {
        if (structField == this.activeField) {
            super.writeField(structField);
        }
    }
    
    @Override
    protected Object readField(final StructField structField) {
        if (structField == this.activeField || (!Structure.class.isAssignableFrom(structField.type) && !String.class.isAssignableFrom(structField.type) && !WString.class.isAssignableFrom(structField.type))) {
            return super.readField(structField);
        }
        return null;
    }
    
    @Override
    protected int getNativeAlignment(final Class<?> clazz, final Object o, final boolean b) {
        return super.getNativeAlignment(clazz, o, true);
    }
}
