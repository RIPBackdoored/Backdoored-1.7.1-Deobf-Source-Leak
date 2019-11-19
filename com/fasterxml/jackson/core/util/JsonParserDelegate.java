package com.fasterxml.jackson.core.util;

import java.io.OutputStream;
import com.fasterxml.jackson.core.Base64Variant;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.Writer;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.JsonParser;

public class JsonParserDelegate extends JsonParser
{
    protected JsonParser delegate;
    
    public JsonParserDelegate(final JsonParser delegate) {
        super();
        this.delegate = delegate;
    }
    
    @Override
    public Object getCurrentValue() {
        return this.delegate.getCurrentValue();
    }
    
    @Override
    public void setCurrentValue(final Object currentValue) {
        this.delegate.setCurrentValue(currentValue);
    }
    
    @Override
    public void setCodec(final ObjectCodec codec) {
        this.delegate.setCodec(codec);
    }
    
    @Override
    public ObjectCodec getCodec() {
        return this.delegate.getCodec();
    }
    
    @Override
    public JsonParser enable(final Feature feature) {
        this.delegate.enable(feature);
        return this;
    }
    
    @Override
    public JsonParser disable(final Feature feature) {
        this.delegate.disable(feature);
        return this;
    }
    
    @Override
    public boolean isEnabled(final Feature feature) {
        return this.delegate.isEnabled(feature);
    }
    
    @Override
    public int getFeatureMask() {
        return this.delegate.getFeatureMask();
    }
    
    @Deprecated
    @Override
    public JsonParser setFeatureMask(final int featureMask) {
        this.delegate.setFeatureMask(featureMask);
        return this;
    }
    
    @Override
    public JsonParser overrideStdFeatures(final int n, final int n2) {
        this.delegate.overrideStdFeatures(n, n2);
        return this;
    }
    
    @Override
    public JsonParser overrideFormatFeatures(final int n, final int n2) {
        this.delegate.overrideFormatFeatures(n, n2);
        return this;
    }
    
    @Override
    public FormatSchema getSchema() {
        return this.delegate.getSchema();
    }
    
    @Override
    public void setSchema(final FormatSchema schema) {
        this.delegate.setSchema(schema);
    }
    
    @Override
    public boolean canUseSchema(final FormatSchema formatSchema) {
        return this.delegate.canUseSchema(formatSchema);
    }
    
    @Override
    public Version version() {
        return this.delegate.version();
    }
    
    @Override
    public Object getInputSource() {
        return this.delegate.getInputSource();
    }
    
    @Override
    public boolean requiresCustomCodec() {
        return this.delegate.requiresCustomCodec();
    }
    
    @Override
    public void close() throws IOException {
        this.delegate.close();
    }
    
    @Override
    public boolean isClosed() {
        return this.delegate.isClosed();
    }
    
    @Override
    public JsonToken currentToken() {
        return this.delegate.currentToken();
    }
    
    @Override
    public int currentTokenId() {
        return this.delegate.currentTokenId();
    }
    
    @Override
    public JsonToken getCurrentToken() {
        return this.delegate.getCurrentToken();
    }
    
    @Override
    public int getCurrentTokenId() {
        return this.delegate.getCurrentTokenId();
    }
    
    @Override
    public boolean hasCurrentToken() {
        return this.delegate.hasCurrentToken();
    }
    
    @Override
    public boolean hasTokenId(final int n) {
        return this.delegate.hasTokenId(n);
    }
    
    @Override
    public boolean hasToken(final JsonToken jsonToken) {
        return this.delegate.hasToken(jsonToken);
    }
    
    @Override
    public String getCurrentName() throws IOException {
        return this.delegate.getCurrentName();
    }
    
    @Override
    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }
    
    @Override
    public JsonStreamContext getParsingContext() {
        return this.delegate.getParsingContext();
    }
    
    @Override
    public boolean isExpectedStartArrayToken() {
        return this.delegate.isExpectedStartArrayToken();
    }
    
    @Override
    public boolean isExpectedStartObjectToken() {
        return this.delegate.isExpectedStartObjectToken();
    }
    
    @Override
    public boolean isNaN() throws IOException {
        return this.delegate.isNaN();
    }
    
    @Override
    public void clearCurrentToken() {
        this.delegate.clearCurrentToken();
    }
    
    @Override
    public JsonToken getLastClearedToken() {
        return this.delegate.getLastClearedToken();
    }
    
    @Override
    public void overrideCurrentName(final String s) {
        this.delegate.overrideCurrentName(s);
    }
    
    @Override
    public String getText() throws IOException {
        return this.delegate.getText();
    }
    
    @Override
    public boolean hasTextCharacters() {
        return this.delegate.hasTextCharacters();
    }
    
    @Override
    public char[] getTextCharacters() throws IOException {
        return this.delegate.getTextCharacters();
    }
    
    @Override
    public int getTextLength() throws IOException {
        return this.delegate.getTextLength();
    }
    
    @Override
    public int getTextOffset() throws IOException {
        return this.delegate.getTextOffset();
    }
    
    @Override
    public int getText(final Writer writer) throws IOException, UnsupportedOperationException {
        return this.delegate.getText(writer);
    }
    
    @Override
    public BigInteger getBigIntegerValue() throws IOException {
        return this.delegate.getBigIntegerValue();
    }
    
    @Override
    public boolean getBooleanValue() throws IOException {
        return this.delegate.getBooleanValue();
    }
    
    @Override
    public byte getByteValue() throws IOException {
        return this.delegate.getByteValue();
    }
    
    @Override
    public short getShortValue() throws IOException {
        return this.delegate.getShortValue();
    }
    
    @Override
    public BigDecimal getDecimalValue() throws IOException {
        return this.delegate.getDecimalValue();
    }
    
    @Override
    public double getDoubleValue() throws IOException {
        return this.delegate.getDoubleValue();
    }
    
    @Override
    public float getFloatValue() throws IOException {
        return this.delegate.getFloatValue();
    }
    
    @Override
    public int getIntValue() throws IOException {
        return this.delegate.getIntValue();
    }
    
    @Override
    public long getLongValue() throws IOException {
        return this.delegate.getLongValue();
    }
    
    @Override
    public NumberType getNumberType() throws IOException {
        return this.delegate.getNumberType();
    }
    
    @Override
    public Number getNumberValue() throws IOException {
        return this.delegate.getNumberValue();
    }
    
    @Override
    public int getValueAsInt() throws IOException {
        return this.delegate.getValueAsInt();
    }
    
    @Override
    public int getValueAsInt(final int n) throws IOException {
        return this.delegate.getValueAsInt(n);
    }
    
    @Override
    public long getValueAsLong() throws IOException {
        return this.delegate.getValueAsLong();
    }
    
    @Override
    public long getValueAsLong(final long n) throws IOException {
        return this.delegate.getValueAsLong(n);
    }
    
    @Override
    public double getValueAsDouble() throws IOException {
        return this.delegate.getValueAsDouble();
    }
    
    @Override
    public double getValueAsDouble(final double n) throws IOException {
        return this.delegate.getValueAsDouble(n);
    }
    
    @Override
    public boolean getValueAsBoolean() throws IOException {
        return this.delegate.getValueAsBoolean();
    }
    
    @Override
    public boolean getValueAsBoolean(final boolean b) throws IOException {
        return this.delegate.getValueAsBoolean(b);
    }
    
    @Override
    public String getValueAsString() throws IOException {
        return this.delegate.getValueAsString();
    }
    
    @Override
    public String getValueAsString(final String s) throws IOException {
        return this.delegate.getValueAsString(s);
    }
    
    @Override
    public Object getEmbeddedObject() throws IOException {
        return this.delegate.getEmbeddedObject();
    }
    
    @Override
    public byte[] getBinaryValue(final Base64Variant base64Variant) throws IOException {
        return this.delegate.getBinaryValue(base64Variant);
    }
    
    @Override
    public int readBinaryValue(final Base64Variant base64Variant, final OutputStream outputStream) throws IOException {
        return this.delegate.readBinaryValue(base64Variant, outputStream);
    }
    
    @Override
    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }
    
    @Override
    public JsonToken nextToken() throws IOException {
        return this.delegate.nextToken();
    }
    
    @Override
    public JsonToken nextValue() throws IOException {
        return this.delegate.nextValue();
    }
    
    @Override
    public void finishToken() throws IOException {
        this.delegate.finishToken();
    }
    
    @Override
    public JsonParser skipChildren() throws IOException {
        this.delegate.skipChildren();
        return this;
    }
    
    @Override
    public boolean canReadObjectId() {
        return this.delegate.canReadObjectId();
    }
    
    @Override
    public boolean canReadTypeId() {
        return this.delegate.canReadTypeId();
    }
    
    @Override
    public Object getObjectId() throws IOException {
        return this.delegate.getObjectId();
    }
    
    @Override
    public Object getTypeId() throws IOException {
        return this.delegate.getTypeId();
    }
}
