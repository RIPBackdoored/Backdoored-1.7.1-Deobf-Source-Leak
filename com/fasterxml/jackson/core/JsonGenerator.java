package com.fasterxml.jackson.core;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.core.type.WritableTypeId;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.InputStream;
import java.io.Reader;
import java.io.IOException;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import java.io.Flushable;
import java.io.Closeable;

public abstract class JsonGenerator implements Closeable, Flushable, Versioned
{
    protected PrettyPrinter _cfgPrettyPrinter;
    
    protected JsonGenerator() {
        super();
    }
    
    public abstract JsonGenerator setCodec(final ObjectCodec p0);
    
    public abstract ObjectCodec getCodec();
    
    @Override
    public abstract Version version();
    
    public abstract JsonGenerator enable(final Feature p0);
    
    public abstract JsonGenerator disable(final Feature p0);
    
    public final JsonGenerator configure(final Feature feature, final boolean b) {
        if (b) {
            this.enable(feature);
        }
        else {
            this.disable(feature);
        }
        return this;
    }
    
    public abstract boolean isEnabled(final Feature p0);
    
    public abstract int getFeatureMask();
    
    @Deprecated
    public abstract JsonGenerator setFeatureMask(final int p0);
    
    public JsonGenerator overrideStdFeatures(final int n, final int n2) {
        return this.setFeatureMask((this.getFeatureMask() & ~n2) | (n & n2));
    }
    
    public int getFormatFeatures() {
        return 0;
    }
    
    public JsonGenerator overrideFormatFeatures(final int n, final int n2) {
        throw new IllegalArgumentException("No FormatFeatures defined for generator of type " + this.getClass().getName());
    }
    
    public void setSchema(final FormatSchema formatSchema) {
        throw new UnsupportedOperationException("Generator of type " + this.getClass().getName() + " does not support schema of type '" + formatSchema.getSchemaType() + "'");
    }
    
    public FormatSchema getSchema() {
        return null;
    }
    
    public JsonGenerator setPrettyPrinter(final PrettyPrinter cfgPrettyPrinter) {
        this._cfgPrettyPrinter = cfgPrettyPrinter;
        return this;
    }
    
    public PrettyPrinter getPrettyPrinter() {
        return this._cfgPrettyPrinter;
    }
    
    public abstract JsonGenerator useDefaultPrettyPrinter();
    
    public JsonGenerator setHighestNonEscapedChar(final int n) {
        return this;
    }
    
    public int getHighestEscapedChar() {
        return 0;
    }
    
    public CharacterEscapes getCharacterEscapes() {
        return null;
    }
    
    public JsonGenerator setCharacterEscapes(final CharacterEscapes characterEscapes) {
        return this;
    }
    
    public JsonGenerator setRootValueSeparator(final SerializableString serializableString) {
        throw new UnsupportedOperationException();
    }
    
    public Object getOutputTarget() {
        return null;
    }
    
    public int getOutputBuffered() {
        return -1;
    }
    
    public Object getCurrentValue() {
        final JsonStreamContext outputContext = this.getOutputContext();
        return (outputContext == null) ? null : outputContext.getCurrentValue();
    }
    
    public void setCurrentValue(final Object currentValue) {
        final JsonStreamContext outputContext = this.getOutputContext();
        if (outputContext != null) {
            outputContext.setCurrentValue(currentValue);
        }
    }
    
    public boolean canUseSchema(final FormatSchema formatSchema) {
        return false;
    }
    
    public boolean canWriteObjectId() {
        return false;
    }
    
    public boolean canWriteTypeId() {
        return false;
    }
    
    public boolean canWriteBinaryNatively() {
        return false;
    }
    
    public boolean canOmitFields() {
        return true;
    }
    
    public boolean canWriteFormattedNumbers() {
        return false;
    }
    
    public abstract void writeStartArray() throws IOException;
    
    public void writeStartArray(final int n) throws IOException {
        this.writeStartArray();
    }
    
    public abstract void writeEndArray() throws IOException;
    
    public abstract void writeStartObject() throws IOException;
    
    public void writeStartObject(final Object currentValue) throws IOException {
        this.writeStartObject();
        this.setCurrentValue(currentValue);
    }
    
    public abstract void writeEndObject() throws IOException;
    
    public abstract void writeFieldName(final String p0) throws IOException;
    
    public abstract void writeFieldName(final SerializableString p0) throws IOException;
    
    public void writeFieldId(final long n) throws IOException {
        this.writeFieldName(Long.toString(n));
    }
    
    public void writeArray(final int[] array, final int n, final int n2) throws IOException {
        if (array == null) {
            throw new IllegalArgumentException("null array");
        }
        this._verifyOffsets(array.length, n, n2);
        this.writeStartArray();
        for (int i = n; i < n + n2; ++i) {
            this.writeNumber(array[i]);
        }
        this.writeEndArray();
    }
    
    public void writeArray(final long[] array, final int n, final int n2) throws IOException {
        if (array == null) {
            throw new IllegalArgumentException("null array");
        }
        this._verifyOffsets(array.length, n, n2);
        this.writeStartArray();
        for (int i = n; i < n + n2; ++i) {
            this.writeNumber(array[i]);
        }
        this.writeEndArray();
    }
    
    public void writeArray(final double[] array, final int n, final int n2) throws IOException {
        if (array == null) {
            throw new IllegalArgumentException("null array");
        }
        this._verifyOffsets(array.length, n, n2);
        this.writeStartArray();
        for (int i = n; i < n + n2; ++i) {
            this.writeNumber(array[i]);
        }
        this.writeEndArray();
    }
    
    public abstract void writeString(final String p0) throws IOException;
    
    public void writeString(final Reader reader, final int n) throws IOException {
        this._reportUnsupportedOperation();
    }
    
    public abstract void writeString(final char[] p0, final int p1, final int p2) throws IOException;
    
    public abstract void writeString(final SerializableString p0) throws IOException;
    
    public abstract void writeRawUTF8String(final byte[] p0, final int p1, final int p2) throws IOException;
    
    public abstract void writeUTF8String(final byte[] p0, final int p1, final int p2) throws IOException;
    
    public abstract void writeRaw(final String p0) throws IOException;
    
    public abstract void writeRaw(final String p0, final int p1, final int p2) throws IOException;
    
    public abstract void writeRaw(final char[] p0, final int p1, final int p2) throws IOException;
    
    public abstract void writeRaw(final char p0) throws IOException;
    
    public void writeRaw(final SerializableString serializableString) throws IOException {
        this.writeRaw(serializableString.getValue());
    }
    
    public abstract void writeRawValue(final String p0) throws IOException;
    
    public abstract void writeRawValue(final String p0, final int p1, final int p2) throws IOException;
    
    public abstract void writeRawValue(final char[] p0, final int p1, final int p2) throws IOException;
    
    public void writeRawValue(final SerializableString serializableString) throws IOException {
        this.writeRawValue(serializableString.getValue());
    }
    
    public abstract void writeBinary(final Base64Variant p0, final byte[] p1, final int p2, final int p3) throws IOException;
    
    public void writeBinary(final byte[] array, final int n, final int n2) throws IOException {
        this.writeBinary(Base64Variants.getDefaultVariant(), array, n, n2);
    }
    
    public void writeBinary(final byte[] array) throws IOException {
        this.writeBinary(Base64Variants.getDefaultVariant(), array, 0, array.length);
    }
    
    public int writeBinary(final InputStream inputStream, final int n) throws IOException {
        return this.writeBinary(Base64Variants.getDefaultVariant(), inputStream, n);
    }
    
    public abstract int writeBinary(final Base64Variant p0, final InputStream p1, final int p2) throws IOException;
    
    public void writeNumber(final short n) throws IOException {
        this.writeNumber((int)n);
    }
    
    public abstract void writeNumber(final int p0) throws IOException;
    
    public abstract void writeNumber(final long p0) throws IOException;
    
    public abstract void writeNumber(final BigInteger p0) throws IOException;
    
    public abstract void writeNumber(final double p0) throws IOException;
    
    public abstract void writeNumber(final float p0) throws IOException;
    
    public abstract void writeNumber(final BigDecimal p0) throws IOException;
    
    public abstract void writeNumber(final String p0) throws IOException;
    
    public abstract void writeBoolean(final boolean p0) throws IOException;
    
    public abstract void writeNull() throws IOException;
    
    public void writeEmbeddedObject(final Object o) throws IOException {
        if (o == null) {
            this.writeNull();
            return;
        }
        if (o instanceof byte[]) {
            this.writeBinary((byte[])o);
            return;
        }
        throw new JsonGenerationException("No native support for writing embedded objects of type " + o.getClass().getName(), this);
    }
    
    public void writeObjectId(final Object o) throws IOException {
        throw new JsonGenerationException("No native support for writing Object Ids", this);
    }
    
    public void writeObjectRef(final Object o) throws IOException {
        throw new JsonGenerationException("No native support for writing Object Ids", this);
    }
    
    public void writeTypeId(final Object o) throws IOException {
        throw new JsonGenerationException("No native support for writing Type Ids", this);
    }
    
    public WritableTypeId writeTypePrefix(final WritableTypeId writableTypeId) throws IOException {
        final Object id = writableTypeId.id;
        final JsonToken valueShape = writableTypeId.valueShape;
        if (this.canWriteTypeId()) {
            writableTypeId.wrapperWritten = false;
            this.writeTypeId(id);
        }
        else {
            final String s = (String)((id instanceof String) ? id : String.valueOf(id));
            writableTypeId.wrapperWritten = true;
            WritableTypeId.Inclusion include = writableTypeId.include;
            if (valueShape != JsonToken.START_OBJECT && include.requiresObjectContext()) {
                include = (writableTypeId.include = WritableTypeId.Inclusion.WRAPPER_ARRAY);
            }
            switch (include) {
                case PARENT_PROPERTY:
                    break;
                case PAYLOAD_PROPERTY:
                    break;
                case METADATA_PROPERTY:
                    this.writeStartObject(writableTypeId.forValue);
                    this.writeStringField(writableTypeId.asProperty, s);
                    return writableTypeId;
                case WRAPPER_OBJECT:
                    this.writeStartObject();
                    this.writeFieldName(s);
                    break;
                default:
                    this.writeStartArray();
                    this.writeString(s);
                    break;
            }
        }
        if (valueShape == JsonToken.START_OBJECT) {
            this.writeStartObject(writableTypeId.forValue);
        }
        else if (valueShape == JsonToken.START_ARRAY) {
            this.writeStartArray();
        }
        return writableTypeId;
    }
    
    public WritableTypeId writeTypeSuffix(final WritableTypeId writableTypeId) throws IOException {
        final JsonToken valueShape = writableTypeId.valueShape;
        if (valueShape == JsonToken.START_OBJECT) {
            this.writeEndObject();
        }
        else if (valueShape == JsonToken.START_ARRAY) {
            this.writeEndArray();
        }
        if (writableTypeId.wrapperWritten) {
            switch (writableTypeId.include) {
                case WRAPPER_ARRAY:
                    this.writeEndArray();
                    break;
                case PARENT_PROPERTY: {
                    final Object id = writableTypeId.id;
                    this.writeStringField(writableTypeId.asProperty, (id instanceof String) ? ((String)id) : String.valueOf(id));
                    break;
                }
                case PAYLOAD_PROPERTY:
                case METADATA_PROPERTY:
                    break;
                default:
                    this.writeEndObject();
                    break;
            }
        }
        return writableTypeId;
    }
    
    public abstract void writeObject(final Object p0) throws IOException;
    
    public abstract void writeTree(final TreeNode p0) throws IOException;
    
    public void writeStringField(final String s, final String s2) throws IOException {
        this.writeFieldName(s);
        this.writeString(s2);
    }
    
    public final void writeBooleanField(final String s, final boolean b) throws IOException {
        this.writeFieldName(s);
        this.writeBoolean(b);
    }
    
    public final void writeNullField(final String s) throws IOException {
        this.writeFieldName(s);
        this.writeNull();
    }
    
    public final void writeNumberField(final String s, final int n) throws IOException {
        this.writeFieldName(s);
        this.writeNumber(n);
    }
    
    public final void writeNumberField(final String s, final long n) throws IOException {
        this.writeFieldName(s);
        this.writeNumber(n);
    }
    
    public final void writeNumberField(final String s, final double n) throws IOException {
        this.writeFieldName(s);
        this.writeNumber(n);
    }
    
    public final void writeNumberField(final String s, final float n) throws IOException {
        this.writeFieldName(s);
        this.writeNumber(n);
    }
    
    public final void writeNumberField(final String s, final BigDecimal bigDecimal) throws IOException {
        this.writeFieldName(s);
        this.writeNumber(bigDecimal);
    }
    
    public final void writeBinaryField(final String s, final byte[] array) throws IOException {
        this.writeFieldName(s);
        this.writeBinary(array);
    }
    
    public final void writeArrayFieldStart(final String s) throws IOException {
        this.writeFieldName(s);
        this.writeStartArray();
    }
    
    public final void writeObjectFieldStart(final String s) throws IOException {
        this.writeFieldName(s);
        this.writeStartObject();
    }
    
    public final void writeObjectField(final String s, final Object o) throws IOException {
        this.writeFieldName(s);
        this.writeObject(o);
    }
    
    public void writeOmittedField(final String s) throws IOException {
    }
    
    public void copyCurrentEvent(final JsonParser jsonParser) throws IOException {
        final JsonToken currentToken = jsonParser.currentToken();
        if (currentToken == null) {
            this._reportError("No current event to copy");
        }
        switch (currentToken.id()) {
            case -1:
                this._reportError("No current event to copy");
                break;
            case 1:
                this.writeStartObject();
                break;
            case 2:
                this.writeEndObject();
                break;
            case 3:
                this.writeStartArray();
                break;
            case 4:
                this.writeEndArray();
                break;
            case 5:
                this.writeFieldName(jsonParser.getCurrentName());
                break;
            case 6:
                if (jsonParser.hasTextCharacters()) {
                    this.writeString(jsonParser.getTextCharacters(), jsonParser.getTextOffset(), jsonParser.getTextLength());
                    break;
                }
                this.writeString(jsonParser.getText());
                break;
            case 7: {
                final JsonParser.NumberType numberType = jsonParser.getNumberType();
                if (numberType == JsonParser.NumberType.INT) {
                    this.writeNumber(jsonParser.getIntValue());
                    break;
                }
                if (numberType == JsonParser.NumberType.BIG_INTEGER) {
                    this.writeNumber(jsonParser.getBigIntegerValue());
                    break;
                }
                this.writeNumber(jsonParser.getLongValue());
                break;
            }
            case 8: {
                final JsonParser.NumberType numberType2 = jsonParser.getNumberType();
                if (numberType2 == JsonParser.NumberType.BIG_DECIMAL) {
                    this.writeNumber(jsonParser.getDecimalValue());
                    break;
                }
                if (numberType2 == JsonParser.NumberType.FLOAT) {
                    this.writeNumber(jsonParser.getFloatValue());
                    break;
                }
                this.writeNumber(jsonParser.getDoubleValue());
                break;
            }
            case 9:
                this.writeBoolean(true);
                break;
            case 10:
                this.writeBoolean(false);
                break;
            case 11:
                this.writeNull();
                break;
            case 12:
                this.writeObject(jsonParser.getEmbeddedObject());
                break;
            default:
                this._throwInternal();
                break;
        }
    }
    
    public void copyCurrentStructure(final JsonParser jsonParser) throws IOException {
        final JsonToken currentToken = jsonParser.currentToken();
        if (currentToken == null) {
            this._reportError("No current event to copy");
        }
        int n = currentToken.id();
        if (n == 5) {
            this.writeFieldName(jsonParser.getCurrentName());
            n = jsonParser.nextToken().id();
        }
        switch (n) {
            case 1:
                this.writeStartObject();
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    this.copyCurrentStructure(jsonParser);
                }
                this.writeEndObject();
                break;
            case 3:
                this.writeStartArray();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    this.copyCurrentStructure(jsonParser);
                }
                this.writeEndArray();
                break;
            default:
                this.copyCurrentEvent(jsonParser);
                break;
        }
    }
    
    public abstract JsonStreamContext getOutputContext();
    
    @Override
    public abstract void flush() throws IOException;
    
    public abstract boolean isClosed();
    
    @Override
    public abstract void close() throws IOException;
    
    protected void _reportError(final String s) throws JsonGenerationException {
        throw new JsonGenerationException(s, this);
    }
    
    protected final void _throwInternal() {
        VersionUtil.throwInternal();
    }
    
    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Operation not supported by generator of type " + this.getClass().getName());
    }
    
    protected final void _verifyOffsets(final int n, final int n2, final int n3) {
        if (n2 < 0 || n2 + n3 > n) {
            throw new IllegalArgumentException(String.format("invalid argument(s) (offset=%d, length=%d) for input array of %d element", n2, n3, n));
        }
    }
    
    protected void _writeSimpleObject(final Object o) throws IOException {
        if (o == null) {
            this.writeNull();
            return;
        }
        if (o instanceof String) {
            this.writeString((String)o);
            return;
        }
        if (o instanceof Number) {
            final Number n = (Number)o;
            if (n instanceof Integer) {
                this.writeNumber(n.intValue());
                return;
            }
            if (n instanceof Long) {
                this.writeNumber(n.longValue());
                return;
            }
            if (n instanceof Double) {
                this.writeNumber(n.doubleValue());
                return;
            }
            if (n instanceof Float) {
                this.writeNumber(n.floatValue());
                return;
            }
            if (n instanceof Short) {
                this.writeNumber(n.shortValue());
                return;
            }
            if (n instanceof Byte) {
                this.writeNumber(n.byteValue());
                return;
            }
            if (n instanceof BigInteger) {
                this.writeNumber((BigInteger)n);
                return;
            }
            if (n instanceof BigDecimal) {
                this.writeNumber((BigDecimal)n);
                return;
            }
            if (n instanceof AtomicInteger) {
                this.writeNumber(((AtomicInteger)n).get());
                return;
            }
            if (n instanceof AtomicLong) {
                this.writeNumber(((AtomicLong)n).get());
                return;
            }
        }
        else {
            if (o instanceof byte[]) {
                this.writeBinary((byte[])o);
                return;
            }
            if (o instanceof Boolean) {
                this.writeBoolean((boolean)o);
                return;
            }
            if (o instanceof AtomicBoolean) {
                this.writeBoolean(((AtomicBoolean)o).get());
                return;
            }
        }
        throw new IllegalStateException("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed " + o.getClass().getName() + ")");
    }
}
