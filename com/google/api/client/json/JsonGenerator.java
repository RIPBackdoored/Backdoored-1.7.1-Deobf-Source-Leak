package com.google.api.client.json;

import java.lang.reflect.Field;
import java.util.Iterator;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.GenericData;
import java.util.Map;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Types;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Data;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.IOException;

public abstract class JsonGenerator
{
    public JsonGenerator() {
        super();
    }
    
    public abstract JsonFactory getFactory();
    
    public abstract void flush() throws IOException;
    
    public abstract void close() throws IOException;
    
    public abstract void writeStartArray() throws IOException;
    
    public abstract void writeEndArray() throws IOException;
    
    public abstract void writeStartObject() throws IOException;
    
    public abstract void writeEndObject() throws IOException;
    
    public abstract void writeFieldName(final String p0) throws IOException;
    
    public abstract void writeNull() throws IOException;
    
    public abstract void writeString(final String p0) throws IOException;
    
    public abstract void writeBoolean(final boolean p0) throws IOException;
    
    public abstract void writeNumber(final int p0) throws IOException;
    
    public abstract void writeNumber(final long p0) throws IOException;
    
    public abstract void writeNumber(final BigInteger p0) throws IOException;
    
    public abstract void writeNumber(final float p0) throws IOException;
    
    public abstract void writeNumber(final double p0) throws IOException;
    
    public abstract void writeNumber(final BigDecimal p0) throws IOException;
    
    public abstract void writeNumber(final String p0) throws IOException;
    
    public final void serialize(final Object o) throws IOException {
        this.serialize(false, o);
    }
    
    private void serialize(final boolean b, final Object o) throws IOException {
        if (o == null) {
            return;
        }
        final Class<?> class1 = o.getClass();
        if (Data.isNull(o)) {
            this.writeNull();
        }
        else if (o instanceof String) {
            this.writeString((String)o);
        }
        else if (o instanceof Number) {
            if (b) {
                this.writeString(o.toString());
            }
            else if (o instanceof BigDecimal) {
                this.writeNumber((BigDecimal)o);
            }
            else if (o instanceof BigInteger) {
                this.writeNumber((BigInteger)o);
            }
            else if (o instanceof Long) {
                this.writeNumber((long)o);
            }
            else if (o instanceof Float) {
                final float floatValue = ((Number)o).floatValue();
                Preconditions.checkArgument(!Float.isInfinite(floatValue) && !Float.isNaN(floatValue));
                this.writeNumber(floatValue);
            }
            else if (o instanceof Integer || o instanceof Short || o instanceof Byte) {
                this.writeNumber(((Number)o).intValue());
            }
            else {
                final double doubleValue = ((Number)o).doubleValue();
                Preconditions.checkArgument(!Double.isInfinite(doubleValue) && !Double.isNaN(doubleValue));
                this.writeNumber(doubleValue);
            }
        }
        else if (o instanceof Boolean) {
            this.writeBoolean((boolean)o);
        }
        else if (o instanceof DateTime) {
            this.writeString(((DateTime)o).toStringRfc3339());
        }
        else if (o instanceof Iterable || class1.isArray()) {
            this.writeStartArray();
            final Iterator<Object> iterator = Types.<Object>iterableOf(o).iterator();
            while (iterator.hasNext()) {
                this.serialize(b, iterator.next());
            }
            this.writeEndArray();
        }
        else if (class1.isEnum()) {
            final String name = FieldInfo.of((Enum<?>)o).getName();
            if (name == null) {
                this.writeNull();
            }
            else {
                this.writeString(name);
            }
        }
        else {
            this.writeStartObject();
            final boolean b2 = o instanceof Map && !(o instanceof GenericData);
            final ClassInfo classInfo = b2 ? null : ClassInfo.of(class1);
            for (final Map.Entry<String, Object> entry : Data.mapOf(o).entrySet()) {
                final Object value = entry.getValue();
                if (value != null) {
                    final String s = entry.getKey();
                    boolean b3;
                    if (b2) {
                        b3 = b;
                    }
                    else {
                        final Field field = classInfo.getField(s);
                        b3 = (field != null && field.<JsonString>getAnnotation(JsonString.class) != null);
                    }
                    this.writeFieldName(s);
                    this.serialize(b3, value);
                }
            }
            this.writeEndObject();
        }
    }
    
    public void enablePrettyPrint() throws IOException {
    }
}
