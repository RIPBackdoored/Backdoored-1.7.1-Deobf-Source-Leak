package com.fasterxml.jackson.core;

import java.util.Iterator;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.Writer;
import java.io.OutputStream;
import java.io.IOException;
import com.fasterxml.jackson.core.async.NonBlockingInputFeeder;
import com.fasterxml.jackson.core.util.RequestPayload;
import java.io.Closeable;

public abstract class JsonParser implements Closeable, Versioned
{
    private static final int MIN_BYTE_I = -128;
    private static final int MAX_BYTE_I = 255;
    private static final int MIN_SHORT_I = -32768;
    private static final int MAX_SHORT_I = 32767;
    protected int _features;
    protected transient RequestPayload _requestPayload;
    
    protected JsonParser() {
        super();
    }
    
    protected JsonParser(final int features) {
        super();
        this._features = features;
    }
    
    public abstract ObjectCodec getCodec();
    
    public abstract void setCodec(final ObjectCodec p0);
    
    public Object getInputSource() {
        return null;
    }
    
    public Object getCurrentValue() {
        final JsonStreamContext parsingContext = this.getParsingContext();
        return (parsingContext == null) ? null : parsingContext.getCurrentValue();
    }
    
    public void setCurrentValue(final Object currentValue) {
        final JsonStreamContext parsingContext = this.getParsingContext();
        if (parsingContext != null) {
            parsingContext.setCurrentValue(currentValue);
        }
    }
    
    public void setRequestPayloadOnError(final RequestPayload requestPayload) {
        this._requestPayload = requestPayload;
    }
    
    public void setRequestPayloadOnError(final byte[] array, final String s) {
        this._requestPayload = ((array == null) ? null : new RequestPayload(array, s));
    }
    
    public void setRequestPayloadOnError(final String s) {
        this._requestPayload = ((s == null) ? null : new RequestPayload(s));
    }
    
    public void setSchema(final FormatSchema formatSchema) {
        throw new UnsupportedOperationException("Parser of type " + this.getClass().getName() + " does not support schema of type '" + formatSchema.getSchemaType() + "'");
    }
    
    public FormatSchema getSchema() {
        return null;
    }
    
    public boolean canUseSchema(final FormatSchema formatSchema) {
        return false;
    }
    
    public boolean requiresCustomCodec() {
        return false;
    }
    
    public boolean canParseAsync() {
        return false;
    }
    
    public NonBlockingInputFeeder getNonBlockingInputFeeder() {
        return null;
    }
    
    @Override
    public abstract Version version();
    
    @Override
    public abstract void close() throws IOException;
    
    public abstract boolean isClosed();
    
    public abstract JsonStreamContext getParsingContext();
    
    public abstract JsonLocation getTokenLocation();
    
    public abstract JsonLocation getCurrentLocation();
    
    public int releaseBuffered(final OutputStream outputStream) throws IOException {
        return -1;
    }
    
    public int releaseBuffered(final Writer writer) throws IOException {
        return -1;
    }
    
    public JsonParser enable(final Feature feature) {
        this._features |= feature.getMask();
        return this;
    }
    
    public JsonParser disable(final Feature feature) {
        this._features &= ~feature.getMask();
        return this;
    }
    
    public JsonParser configure(final Feature feature, final boolean b) {
        if (b) {
            this.enable(feature);
        }
        else {
            this.disable(feature);
        }
        return this;
    }
    
    public boolean isEnabled(final Feature feature) {
        return feature.enabledIn(this._features);
    }
    
    public int getFeatureMask() {
        return this._features;
    }
    
    @Deprecated
    public JsonParser setFeatureMask(final int features) {
        this._features = features;
        return this;
    }
    
    public JsonParser overrideStdFeatures(final int n, final int n2) {
        return this.setFeatureMask((this._features & ~n2) | (n & n2));
    }
    
    public int getFormatFeatures() {
        return 0;
    }
    
    public JsonParser overrideFormatFeatures(final int n, final int n2) {
        throw new IllegalArgumentException("No FormatFeatures defined for parser of type " + this.getClass().getName());
    }
    
    public abstract JsonToken nextToken() throws IOException;
    
    public abstract JsonToken nextValue() throws IOException;
    
    public boolean nextFieldName(final SerializableString serializableString) throws IOException {
        return this.nextToken() == JsonToken.FIELD_NAME && serializableString.getValue().equals(this.getCurrentName());
    }
    
    public String nextFieldName() throws IOException {
        return (this.nextToken() == JsonToken.FIELD_NAME) ? this.getCurrentName() : null;
    }
    
    public String nextTextValue() throws IOException {
        return (this.nextToken() == JsonToken.VALUE_STRING) ? this.getText() : null;
    }
    
    public int nextIntValue(final int n) throws IOException {
        return (this.nextToken() == JsonToken.VALUE_NUMBER_INT) ? this.getIntValue() : n;
    }
    
    public long nextLongValue(final long n) throws IOException {
        return (this.nextToken() == JsonToken.VALUE_NUMBER_INT) ? this.getLongValue() : n;
    }
    
    public Boolean nextBooleanValue() throws IOException {
        final JsonToken nextToken = this.nextToken();
        if (nextToken == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (nextToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        return null;
    }
    
    public abstract JsonParser skipChildren() throws IOException;
    
    public void finishToken() throws IOException {
    }
    
    public JsonToken currentToken() {
        return this.getCurrentToken();
    }
    
    public int currentTokenId() {
        return this.getCurrentTokenId();
    }
    
    public abstract JsonToken getCurrentToken();
    
    public abstract int getCurrentTokenId();
    
    public abstract boolean hasCurrentToken();
    
    public abstract boolean hasTokenId(final int p0);
    
    public abstract boolean hasToken(final JsonToken p0);
    
    public boolean isExpectedStartArrayToken() {
        return this.currentToken() == JsonToken.START_ARRAY;
    }
    
    public boolean isExpectedStartObjectToken() {
        return this.currentToken() == JsonToken.START_OBJECT;
    }
    
    public boolean isNaN() throws IOException {
        return false;
    }
    
    public abstract void clearCurrentToken();
    
    public abstract JsonToken getLastClearedToken();
    
    public abstract void overrideCurrentName(final String p0);
    
    public abstract String getCurrentName() throws IOException;
    
    public String currentName() throws IOException {
        return this.getCurrentName();
    }
    
    public abstract String getText() throws IOException;
    
    public int getText(final Writer writer) throws IOException, UnsupportedOperationException {
        final String text = this.getText();
        if (text == null) {
            return 0;
        }
        writer.write(text);
        return text.length();
    }
    
    public abstract char[] getTextCharacters() throws IOException;
    
    public abstract int getTextLength() throws IOException;
    
    public abstract int getTextOffset() throws IOException;
    
    public abstract boolean hasTextCharacters();
    
    public abstract Number getNumberValue() throws IOException;
    
    public abstract NumberType getNumberType() throws IOException;
    
    public byte getByteValue() throws IOException {
        final int intValue = this.getIntValue();
        if (intValue < -128 || intValue > 255) {
            throw this._constructError("Numeric value (" + this.getText() + ") out of range of Java byte");
        }
        return (byte)intValue;
    }
    
    public short getShortValue() throws IOException {
        final int intValue = this.getIntValue();
        if (intValue < -32768 || intValue > 32767) {
            throw this._constructError("Numeric value (" + this.getText() + ") out of range of Java short");
        }
        return (short)intValue;
    }
    
    public abstract int getIntValue() throws IOException;
    
    public abstract long getLongValue() throws IOException;
    
    public abstract BigInteger getBigIntegerValue() throws IOException;
    
    public abstract float getFloatValue() throws IOException;
    
    public abstract double getDoubleValue() throws IOException;
    
    public abstract BigDecimal getDecimalValue() throws IOException;
    
    public boolean getBooleanValue() throws IOException {
        final JsonToken currentToken = this.currentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (currentToken == JsonToken.VALUE_FALSE) {
            return false;
        }
        throw new JsonParseException(this, String.format("Current token (%s) not of boolean type", currentToken)).withRequestPayload(this._requestPayload);
    }
    
    public Object getEmbeddedObject() throws IOException {
        return null;
    }
    
    public abstract byte[] getBinaryValue(final Base64Variant p0) throws IOException;
    
    public byte[] getBinaryValue() throws IOException {
        return this.getBinaryValue(Base64Variants.getDefaultVariant());
    }
    
    public int readBinaryValue(final OutputStream outputStream) throws IOException {
        return this.readBinaryValue(Base64Variants.getDefaultVariant(), outputStream);
    }
    
    public int readBinaryValue(final Base64Variant base64Variant, final OutputStream outputStream) throws IOException {
        this._reportUnsupportedOperation();
        return 0;
    }
    
    public int getValueAsInt() throws IOException {
        return this.getValueAsInt(0);
    }
    
    public int getValueAsInt(final int n) throws IOException {
        return n;
    }
    
    public long getValueAsLong() throws IOException {
        return this.getValueAsLong(0L);
    }
    
    public long getValueAsLong(final long n) throws IOException {
        return n;
    }
    
    public double getValueAsDouble() throws IOException {
        return this.getValueAsDouble(0.0);
    }
    
    public double getValueAsDouble(final double n) throws IOException {
        return n;
    }
    
    public boolean getValueAsBoolean() throws IOException {
        return this.getValueAsBoolean(false);
    }
    
    public boolean getValueAsBoolean(final boolean b) throws IOException {
        return b;
    }
    
    public String getValueAsString() throws IOException {
        return this.getValueAsString(null);
    }
    
    public abstract String getValueAsString(final String p0) throws IOException;
    
    public boolean canReadObjectId() {
        return false;
    }
    
    public boolean canReadTypeId() {
        return false;
    }
    
    public Object getObjectId() throws IOException {
        return null;
    }
    
    public Object getTypeId() throws IOException {
        return null;
    }
    
    public <T> T readValueAs(final Class<T> clazz) throws IOException {
        return this._codec().<T>readValue(this, clazz);
    }
    
    public <T> T readValueAs(final TypeReference<?> typeReference) throws IOException {
        return this._codec().<T>readValue(this, typeReference);
    }
    
    public <T> Iterator<T> readValuesAs(final Class<T> clazz) throws IOException {
        return this._codec().<T>readValues(this, clazz);
    }
    
    public <T> Iterator<T> readValuesAs(final TypeReference<?> typeReference) throws IOException {
        return this._codec().<T>readValues(this, typeReference);
    }
    
    public <T extends TreeNode> T readValueAsTree() throws IOException {
        return this._codec().<T>readTree(this);
    }
    
    protected ObjectCodec _codec() {
        final ObjectCodec codec = this.getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for parser, needed for deserialization");
        }
        return codec;
    }
    
    protected JsonParseException _constructError(final String s) {
        return new JsonParseException(this, s).withRequestPayload(this._requestPayload);
    }
    
    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Operation not supported by parser of type " + this.getClass().getName());
    }
}
