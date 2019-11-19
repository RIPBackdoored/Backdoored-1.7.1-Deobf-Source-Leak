package com.fasterxml.jackson.core.type;

import com.fasterxml.jackson.core.JsonToken;

public class WritableTypeId
{
    public Object forValue;
    public Class<?> forValueType;
    public Object id;
    public String asProperty;
    public Inclusion include;
    public JsonToken valueShape;
    public boolean wrapperWritten;
    public Object extra;
    
    public WritableTypeId() {
        super();
    }
    
    public WritableTypeId(final Object o, final JsonToken jsonToken) {
        this(o, jsonToken, null);
    }
    
    public WritableTypeId(final Object o, final Class<?> forValueType, final JsonToken jsonToken) {
        this(o, jsonToken, null);
        this.forValueType = forValueType;
    }
    
    public WritableTypeId(final Object forValue, final JsonToken valueShape, final Object id) {
        super();
        this.forValue = forValue;
        this.id = id;
        this.valueShape = valueShape;
    }
}
