package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.InputStream;
import com.fasterxml.jackson.core.Base64Variant;
import java.io.Reader;
import java.io.IOException;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.JsonGenerator;

public class JsonGeneratorDelegate extends JsonGenerator
{
    protected JsonGenerator delegate;
    protected boolean delegateCopyMethods;
    
    public JsonGeneratorDelegate(final JsonGenerator jsonGenerator) {
        this(jsonGenerator, true);
    }
    
    public JsonGeneratorDelegate(final JsonGenerator delegate, final boolean delegateCopyMethods) {
        super();
        this.delegate = delegate;
        this.delegateCopyMethods = delegateCopyMethods;
    }
    
    @Override
    public Object getCurrentValue() {
        return this.delegate.getCurrentValue();
    }
    
    @Override
    public void setCurrentValue(final Object currentValue) {
        this.delegate.setCurrentValue(currentValue);
    }
    
    public JsonGenerator getDelegate() {
        return this.delegate;
    }
    
    @Override
    public ObjectCodec getCodec() {
        return this.delegate.getCodec();
    }
    
    @Override
    public JsonGenerator setCodec(final ObjectCodec codec) {
        this.delegate.setCodec(codec);
        return this;
    }
    
    @Override
    public void setSchema(final FormatSchema schema) {
        this.delegate.setSchema(schema);
    }
    
    @Override
    public FormatSchema getSchema() {
        return this.delegate.getSchema();
    }
    
    @Override
    public Version version() {
        return this.delegate.version();
    }
    
    @Override
    public Object getOutputTarget() {
        return this.delegate.getOutputTarget();
    }
    
    @Override
    public int getOutputBuffered() {
        return this.delegate.getOutputBuffered();
    }
    
    @Override
    public boolean canUseSchema(final FormatSchema formatSchema) {
        return this.delegate.canUseSchema(formatSchema);
    }
    
    @Override
    public boolean canWriteTypeId() {
        return this.delegate.canWriteTypeId();
    }
    
    @Override
    public boolean canWriteObjectId() {
        return this.delegate.canWriteObjectId();
    }
    
    @Override
    public boolean canWriteBinaryNatively() {
        return this.delegate.canWriteBinaryNatively();
    }
    
    @Override
    public boolean canOmitFields() {
        return this.delegate.canOmitFields();
    }
    
    @Override
    public JsonGenerator enable(final Feature feature) {
        this.delegate.enable(feature);
        return this;
    }
    
    @Override
    public JsonGenerator disable(final Feature feature) {
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
    public JsonGenerator setFeatureMask(final int featureMask) {
        this.delegate.setFeatureMask(featureMask);
        return this;
    }
    
    @Override
    public JsonGenerator overrideStdFeatures(final int n, final int n2) {
        this.delegate.overrideStdFeatures(n, n2);
        return this;
    }
    
    @Override
    public JsonGenerator overrideFormatFeatures(final int n, final int n2) {
        this.delegate.overrideFormatFeatures(n, n2);
        return this;
    }
    
    @Override
    public JsonGenerator setPrettyPrinter(final PrettyPrinter prettyPrinter) {
        this.delegate.setPrettyPrinter(prettyPrinter);
        return this;
    }
    
    @Override
    public PrettyPrinter getPrettyPrinter() {
        return this.delegate.getPrettyPrinter();
    }
    
    @Override
    public JsonGenerator useDefaultPrettyPrinter() {
        this.delegate.useDefaultPrettyPrinter();
        return this;
    }
    
    @Override
    public JsonGenerator setHighestNonEscapedChar(final int highestNonEscapedChar) {
        this.delegate.setHighestNonEscapedChar(highestNonEscapedChar);
        return this;
    }
    
    @Override
    public int getHighestEscapedChar() {
        return this.delegate.getHighestEscapedChar();
    }
    
    @Override
    public CharacterEscapes getCharacterEscapes() {
        return this.delegate.getCharacterEscapes();
    }
    
    @Override
    public JsonGenerator setCharacterEscapes(final CharacterEscapes characterEscapes) {
        this.delegate.setCharacterEscapes(characterEscapes);
        return this;
    }
    
    @Override
    public JsonGenerator setRootValueSeparator(final SerializableString rootValueSeparator) {
        this.delegate.setRootValueSeparator(rootValueSeparator);
        return this;
    }
    
    @Override
    public void writeStartArray() throws IOException {
        this.delegate.writeStartArray();
    }
    
    @Override
    public void writeStartArray(final int n) throws IOException {
        this.delegate.writeStartArray(n);
    }
    
    @Override
    public void writeEndArray() throws IOException {
        this.delegate.writeEndArray();
    }
    
    @Override
    public void writeStartObject() throws IOException {
        this.delegate.writeStartObject();
    }
    
    @Override
    public void writeStartObject(final Object o) throws IOException {
        this.delegate.writeStartObject(o);
    }
    
    @Override
    public void writeEndObject() throws IOException {
        this.delegate.writeEndObject();
    }
    
    @Override
    public void writeFieldName(final String s) throws IOException {
        this.delegate.writeFieldName(s);
    }
    
    @Override
    public void writeFieldName(final SerializableString serializableString) throws IOException {
        this.delegate.writeFieldName(serializableString);
    }
    
    @Override
    public void writeFieldId(final long n) throws IOException {
        this.delegate.writeFieldId(n);
    }
    
    @Override
    public void writeArray(final int[] array, final int n, final int n2) throws IOException {
        this.delegate.writeArray(array, n, n2);
    }
    
    @Override
    public void writeArray(final long[] array, final int n, final int n2) throws IOException {
        this.delegate.writeArray(array, n, n2);
    }
    
    @Override
    public void writeArray(final double[] array, final int n, final int n2) throws IOException {
        this.delegate.writeArray(array, n, n2);
    }
    
    @Override
    public void writeString(final String s) throws IOException {
        this.delegate.writeString(s);
    }
    
    @Override
    public void writeString(final Reader reader, final int n) throws IOException {
        this.delegate.writeString(reader, n);
    }
    
    @Override
    public void writeString(final char[] array, final int n, final int n2) throws IOException {
        this.delegate.writeString(array, n, n2);
    }
    
    @Override
    public void writeString(final SerializableString serializableString) throws IOException {
        this.delegate.writeString(serializableString);
    }
    
    @Override
    public void writeRawUTF8String(final byte[] array, final int n, final int n2) throws IOException {
        this.delegate.writeRawUTF8String(array, n, n2);
    }
    
    @Override
    public void writeUTF8String(final byte[] array, final int n, final int n2) throws IOException {
        this.delegate.writeUTF8String(array, n, n2);
    }
    
    @Override
    public void writeRaw(final String s) throws IOException {
        this.delegate.writeRaw(s);
    }
    
    @Override
    public void writeRaw(final String s, final int n, final int n2) throws IOException {
        this.delegate.writeRaw(s, n, n2);
    }
    
    @Override
    public void writeRaw(final SerializableString serializableString) throws IOException {
        this.delegate.writeRaw(serializableString);
    }
    
    @Override
    public void writeRaw(final char[] array, final int n, final int n2) throws IOException {
        this.delegate.writeRaw(array, n, n2);
    }
    
    @Override
    public void writeRaw(final char c) throws IOException {
        this.delegate.writeRaw(c);
    }
    
    @Override
    public void writeRawValue(final String s) throws IOException {
        this.delegate.writeRawValue(s);
    }
    
    @Override
    public void writeRawValue(final String s, final int n, final int n2) throws IOException {
        this.delegate.writeRawValue(s, n, n2);
    }
    
    @Override
    public void writeRawValue(final char[] array, final int n, final int n2) throws IOException {
        this.delegate.writeRawValue(array, n, n2);
    }
    
    @Override
    public void writeBinary(final Base64Variant base64Variant, final byte[] array, final int n, final int n2) throws IOException {
        this.delegate.writeBinary(base64Variant, array, n, n2);
    }
    
    @Override
    public int writeBinary(final Base64Variant base64Variant, final InputStream inputStream, final int n) throws IOException {
        return this.delegate.writeBinary(base64Variant, inputStream, n);
    }
    
    @Override
    public void writeNumber(final short n) throws IOException {
        this.delegate.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final int n) throws IOException {
        this.delegate.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final long n) throws IOException {
        this.delegate.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final BigInteger bigInteger) throws IOException {
        this.delegate.writeNumber(bigInteger);
    }
    
    @Override
    public void writeNumber(final double n) throws IOException {
        this.delegate.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final float n) throws IOException {
        this.delegate.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final BigDecimal bigDecimal) throws IOException {
        this.delegate.writeNumber(bigDecimal);
    }
    
    @Override
    public void writeNumber(final String s) throws IOException, UnsupportedOperationException {
        this.delegate.writeNumber(s);
    }
    
    @Override
    public void writeBoolean(final boolean b) throws IOException {
        this.delegate.writeBoolean(b);
    }
    
    @Override
    public void writeNull() throws IOException {
        this.delegate.writeNull();
    }
    
    @Override
    public void writeOmittedField(final String s) throws IOException {
        this.delegate.writeOmittedField(s);
    }
    
    @Override
    public void writeObjectId(final Object o) throws IOException {
        this.delegate.writeObjectId(o);
    }
    
    @Override
    public void writeObjectRef(final Object o) throws IOException {
        this.delegate.writeObjectRef(o);
    }
    
    @Override
    public void writeTypeId(final Object o) throws IOException {
        this.delegate.writeTypeId(o);
    }
    
    @Override
    public void writeEmbeddedObject(final Object o) throws IOException {
        this.delegate.writeEmbeddedObject(o);
    }
    
    @Override
    public void writeObject(final Object o) throws IOException {
        if (this.delegateCopyMethods) {
            this.delegate.writeObject(o);
            return;
        }
        if (o == null) {
            this.writeNull();
        }
        else {
            final ObjectCodec codec = this.getCodec();
            if (codec != null) {
                codec.writeValue(this, o);
                return;
            }
            this._writeSimpleObject(o);
        }
    }
    
    @Override
    public void writeTree(final TreeNode treeNode) throws IOException {
        if (this.delegateCopyMethods) {
            this.delegate.writeTree(treeNode);
            return;
        }
        if (treeNode == null) {
            this.writeNull();
        }
        else {
            final ObjectCodec codec = this.getCodec();
            if (codec == null) {
                throw new IllegalStateException("No ObjectCodec defined");
            }
            codec.writeTree(this, treeNode);
        }
    }
    
    @Override
    public void copyCurrentEvent(final JsonParser jsonParser) throws IOException {
        if (this.delegateCopyMethods) {
            this.delegate.copyCurrentEvent(jsonParser);
        }
        else {
            super.copyCurrentEvent(jsonParser);
        }
    }
    
    @Override
    public void copyCurrentStructure(final JsonParser jsonParser) throws IOException {
        if (this.delegateCopyMethods) {
            this.delegate.copyCurrentStructure(jsonParser);
        }
        else {
            super.copyCurrentStructure(jsonParser);
        }
    }
    
    @Override
    public JsonStreamContext getOutputContext() {
        return this.delegate.getOutputContext();
    }
    
    @Override
    public void flush() throws IOException {
        this.delegate.flush();
    }
    
    @Override
    public void close() throws IOException {
        this.delegate.close();
    }
    
    @Override
    public boolean isClosed() {
        return this.delegate.isClosed();
    }
}
