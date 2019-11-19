package com.fasterxml.jackson.core;

public enum JsonToken
{
    NOT_AVAILABLE((String)null, -1), 
    START_OBJECT("{", 1), 
    END_OBJECT("}", 2), 
    START_ARRAY("[", 3), 
    END_ARRAY("]", 4), 
    FIELD_NAME((String)null, 5), 
    VALUE_EMBEDDED_OBJECT((String)null, 12), 
    VALUE_STRING((String)null, 6), 
    VALUE_NUMBER_INT((String)null, 7), 
    VALUE_NUMBER_FLOAT((String)null, 8), 
    VALUE_TRUE("true", 9), 
    VALUE_FALSE("false", 10), 
    VALUE_NULL("null", 11);
    
    final String _serialized;
    final char[] _serializedChars;
    final byte[] _serializedBytes;
    final int _id;
    final boolean _isStructStart;
    final boolean _isStructEnd;
    final boolean _isNumber;
    final boolean _isBoolean;
    final boolean _isScalar;
    private static final /* synthetic */ JsonToken[] $VALUES;
    
    public static JsonToken[] values() {
        return JsonToken.$VALUES.clone();
    }
    
    public static JsonToken valueOf(final String s) {
        return Enum.<JsonToken>valueOf(JsonToken.class, s);
    }
    
    private JsonToken(final String serialized, final int id) {
        if (serialized == null) {
            this._serialized = null;
            this._serializedChars = null;
            this._serializedBytes = null;
        }
        else {
            this._serialized = serialized;
            this._serializedChars = serialized.toCharArray();
            final int length = this._serializedChars.length;
            this._serializedBytes = new byte[length];
            for (int i = 0; i < length; ++i) {
                this._serializedBytes[i] = (byte)this._serializedChars[i];
            }
        }
        this._id = id;
        this._isBoolean = (id == 10 || id == 9);
        this._isNumber = (id == 7 || id == 8);
        this._isStructStart = (id == 1 || id == 3);
        this._isStructEnd = (id == 2 || id == 4);
        this._isScalar = (!this._isStructStart && !this._isStructEnd && id != 5 && id != -1);
    }
    
    public final int id() {
        return this._id;
    }
    
    public final String asString() {
        return this._serialized;
    }
    
    public final char[] asCharArray() {
        return this._serializedChars;
    }
    
    public final byte[] asByteArray() {
        return this._serializedBytes;
    }
    
    public final boolean isNumeric() {
        return this._isNumber;
    }
    
    public final boolean isStructStart() {
        return this._isStructStart;
    }
    
    public final boolean isStructEnd() {
        return this._isStructEnd;
    }
    
    public final boolean isScalarValue() {
        return this._isScalar;
    }
    
    public final boolean isBoolean() {
        return this._isBoolean;
    }
    
    static {
        $VALUES = new JsonToken[] { JsonToken.NOT_AVAILABLE, JsonToken.START_OBJECT, JsonToken.END_OBJECT, JsonToken.START_ARRAY, JsonToken.END_ARRAY, JsonToken.FIELD_NAME, JsonToken.VALUE_EMBEDDED_OBJECT, JsonToken.VALUE_STRING, JsonToken.VALUE_NUMBER_INT, JsonToken.VALUE_NUMBER_FLOAT, JsonToken.VALUE_TRUE, JsonToken.VALUE_FALSE, JsonToken.VALUE_NULL };
    }
}
