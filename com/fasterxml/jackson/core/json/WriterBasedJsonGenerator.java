package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.JsonStreamContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import com.fasterxml.jackson.core.io.NumberOutput;
import java.io.InputStream;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.Base64Variant;
import java.io.Reader;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.SerializableString;
import java.io.Writer;

public class WriterBasedJsonGenerator extends JsonGeneratorImpl
{
    protected static final int SHORT_WRITE = 32;
    protected static final char[] HEX_CHARS;
    protected final Writer _writer;
    protected char _quoteChar;
    protected char[] _outputBuffer;
    protected int _outputHead;
    protected int _outputTail;
    protected int _outputEnd;
    protected char[] _entityBuffer;
    protected SerializableString _currentEscape;
    protected char[] _charBuffer;
    
    public WriterBasedJsonGenerator(final IOContext ioContext, final int n, final ObjectCodec objectCodec, final Writer writer) {
        super(ioContext, n, objectCodec);
        this._quoteChar = '\"';
        this._writer = writer;
        this._outputBuffer = ioContext.allocConcatBuffer();
        this._outputEnd = this._outputBuffer.length;
    }
    
    @Override
    public Object getOutputTarget() {
        return this._writer;
    }
    
    @Override
    public int getOutputBuffered() {
        return Math.max(0, this._outputTail - this._outputHead);
    }
    
    @Override
    public boolean canWriteFormattedNumbers() {
        return true;
    }
    
    @Override
    public void writeFieldName(final String s) throws IOException {
        final int writeFieldName = this._writeContext.writeFieldName(s);
        if (writeFieldName == 4) {
            this._reportError("Can not write a field name, expecting a value");
        }
        this._writeFieldName(s, writeFieldName == 1);
    }
    
    @Override
    public void writeFieldName(final SerializableString serializableString) throws IOException {
        final int writeFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            this._reportError("Can not write a field name, expecting a value");
        }
        this._writeFieldName(serializableString, writeFieldName == 1);
    }
    
    protected final void _writeFieldName(final String s, final boolean b) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            this._writePPFieldName(s, b);
            return;
        }
        if (this._outputTail + 1 >= this._outputEnd) {
            this._flushBuffer();
        }
        if (b) {
            this._outputBuffer[this._outputTail++] = ',';
        }
        if (this._cfgUnqNames) {
            this._writeString(s);
            return;
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._writeString(s);
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    protected final void _writeFieldName(final SerializableString serializableString, final boolean b) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            this._writePPFieldName(serializableString, b);
            return;
        }
        if (this._outputTail + 1 >= this._outputEnd) {
            this._flushBuffer();
        }
        if (b) {
            this._outputBuffer[this._outputTail++] = ',';
        }
        final char[] quotedChars = serializableString.asQuotedChars();
        if (this._cfgUnqNames) {
            this.writeRaw(quotedChars, 0, quotedChars.length);
            return;
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        final int length = quotedChars.length;
        if (this._outputTail + length + 1 >= this._outputEnd) {
            this.writeRaw(quotedChars, 0, length);
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = this._quoteChar;
        }
        else {
            System.arraycopy(quotedChars, 0, this._outputBuffer, this._outputTail, length);
            this._outputTail += length;
            this._outputBuffer[this._outputTail++] = this._quoteChar;
        }
    }
    
    @Override
    public void writeStartArray() throws IOException {
        this._verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartArray(this);
        }
        else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = '[';
        }
    }
    
    @Override
    public void writeEndArray() throws IOException {
        if (!this._writeContext.inArray()) {
            this._reportError("Current context not Array but " + this._writeContext.typeDesc());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
        }
        else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = ']';
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }
    
    @Override
    public void writeStartObject(final Object currentValue) throws IOException {
        this._verifyValueWrite("start an object");
        final JsonWriteContext childObjectContext = this._writeContext.createChildObjectContext();
        this._writeContext = childObjectContext;
        if (currentValue != null) {
            childObjectContext.setCurrentValue(currentValue);
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
        }
        else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = '{';
        }
    }
    
    @Override
    public void writeStartObject() throws IOException {
        this._verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
        }
        else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = '{';
        }
    }
    
    @Override
    public void writeEndObject() throws IOException {
        if (!this._writeContext.inObject()) {
            this._reportError("Current context not Object but " + this._writeContext.typeDesc());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
        }
        else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = '}';
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }
    
    protected final void _writePPFieldName(final String s, final boolean b) throws IOException {
        if (b) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        }
        else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (this._cfgUnqNames) {
            this._writeString(s);
        }
        else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = this._quoteChar;
            this._writeString(s);
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = this._quoteChar;
        }
    }
    
    protected final void _writePPFieldName(final SerializableString serializableString, final boolean b) throws IOException {
        if (b) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        }
        else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        final char[] quotedChars = serializableString.asQuotedChars();
        if (this._cfgUnqNames) {
            this.writeRaw(quotedChars, 0, quotedChars.length);
        }
        else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = this._quoteChar;
            this.writeRaw(quotedChars, 0, quotedChars.length);
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = this._quoteChar;
        }
    }
    
    @Override
    public void writeString(final String s) throws IOException {
        this._verifyValueWrite("write a string");
        if (s == null) {
            this._writeNull();
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._writeString(s);
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    @Override
    public void writeString(final Reader reader, final int n) throws IOException {
        this._verifyValueWrite("write a string");
        if (reader == null) {
            this._reportError("null reader");
        }
        int i = (n >= 0) ? n : 0;
        final char[] allocateCopyBuffer = this._allocateCopyBuffer();
        if (this._outputTail + n >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        while (i > 0) {
            final int read = reader.read(allocateCopyBuffer, 0, Math.min(i, allocateCopyBuffer.length));
            if (read <= 0) {
                break;
            }
            if (this._outputTail + n >= this._outputEnd) {
                this._flushBuffer();
            }
            this._writeString(allocateCopyBuffer, 0, read);
            i -= read;
        }
        if (this._outputTail + n >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        if (i > 0 && n >= 0) {
            this._reportError("Didn't read enough from reader");
        }
    }
    
    @Override
    public void writeString(final char[] array, final int n, final int n2) throws IOException {
        this._verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._writeString(array, n, n2);
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    @Override
    public void writeString(final SerializableString serializableString) throws IOException {
        this._verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        final char[] quotedChars = serializableString.asQuotedChars();
        final int length = quotedChars.length;
        if (length < 32) {
            if (length > this._outputEnd - this._outputTail) {
                this._flushBuffer();
            }
            System.arraycopy(quotedChars, 0, this._outputBuffer, this._outputTail, length);
            this._outputTail += length;
        }
        else {
            this._flushBuffer();
            this._writer.write(quotedChars, 0, length);
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    @Override
    public void writeRawUTF8String(final byte[] array, final int n, final int n2) throws IOException {
        this._reportUnsupportedOperation();
    }
    
    @Override
    public void writeUTF8String(final byte[] array, final int n, final int n2) throws IOException {
        this._reportUnsupportedOperation();
    }
    
    @Override
    public void writeRaw(final String s) throws IOException {
        final int length = s.length();
        final int n = this._outputEnd - this._outputTail;
        this._flushBuffer();
        if (this._outputEnd - this._outputTail >= length) {
            s.getChars(0, length, this._outputBuffer, this._outputTail);
            this._outputTail += length;
        }
        else {
            this.writeRawLong(s);
        }
    }
    
    @Override
    public void writeRaw(final String s, final int n, final int n2) throws IOException {
        int n3 = this._outputEnd - this._outputTail;
        if (n3 < n2) {
            this._flushBuffer();
            n3 = this._outputEnd - this._outputTail;
        }
        if (n3 >= n2) {
            s.getChars(n, n + n2, this._outputBuffer, this._outputTail);
            this._outputTail += n2;
        }
        else {
            this.writeRawLong(s.substring(n, n + n2));
        }
    }
    
    @Override
    public void writeRaw(final SerializableString serializableString) throws IOException {
        this.writeRaw(serializableString.getValue());
    }
    
    @Override
    public void writeRaw(final char[] array, final int n, final int n2) throws IOException {
        if (n2 < 32) {
            if (n2 > this._outputEnd - this._outputTail) {
                this._flushBuffer();
            }
            System.arraycopy(array, n, this._outputBuffer, this._outputTail, n2);
            this._outputTail += n2;
            return;
        }
        this._flushBuffer();
        this._writer.write(array, n, n2);
    }
    
    @Override
    public void writeRaw(final char c) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = c;
    }
    
    private void writeRawLong(final String s) throws IOException {
        final int n = this._outputEnd - this._outputTail;
        s.getChars(0, n, this._outputBuffer, this._outputTail);
        this._outputTail += n;
        this._flushBuffer();
        int n2 = n;
        int i;
        int outputEnd;
        for (i = s.length() - n; i > this._outputEnd; i -= outputEnd) {
            outputEnd = this._outputEnd;
            s.getChars(n2, n2 + outputEnd, this._outputBuffer, 0);
            this._outputHead = 0;
            this._outputTail = outputEnd;
            this._flushBuffer();
            n2 += outputEnd;
        }
        s.getChars(n2, n2 + i, this._outputBuffer, 0);
        this._outputHead = 0;
        this._outputTail = i;
    }
    
    @Override
    public void writeBinary(final Base64Variant base64Variant, final byte[] array, final int n, final int n2) throws IOException, JsonGenerationException {
        this._verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._writeBinary(base64Variant, array, n, n + n2);
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    @Override
    public int writeBinary(final Base64Variant base64Variant, final InputStream inputStream, final int n) throws IOException, JsonGenerationException {
        this._verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        final byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
        int writeBinary;
        try {
            if (n < 0) {
                writeBinary = this._writeBinary(base64Variant, inputStream, allocBase64Buffer);
            }
            else {
                final int writeBinary2 = this._writeBinary(base64Variant, inputStream, allocBase64Buffer, n);
                if (writeBinary2 > 0) {
                    this._reportError("Too few bytes available: missing " + writeBinary2 + " bytes (out of " + n + ")");
                }
                writeBinary = n;
            }
        }
        finally {
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        return writeBinary;
    }
    
    @Override
    public void writeNumber(final short n) throws IOException {
        this._verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            this._writeQuotedShort(n);
            return;
        }
        if (this._outputTail + 6 >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputTail = NumberOutput.outputInt(n, this._outputBuffer, this._outputTail);
    }
    
    private void _writeQuotedShort(final short n) throws IOException {
        if (this._outputTail + 8 >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._outputTail = NumberOutput.outputInt(n, this._outputBuffer, this._outputTail);
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    @Override
    public void writeNumber(final int n) throws IOException {
        this._verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            this._writeQuotedInt(n);
            return;
        }
        if (this._outputTail + 11 >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputTail = NumberOutput.outputInt(n, this._outputBuffer, this._outputTail);
    }
    
    private void _writeQuotedInt(final int n) throws IOException {
        if (this._outputTail + 13 >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._outputTail = NumberOutput.outputInt(n, this._outputBuffer, this._outputTail);
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    @Override
    public void writeNumber(final long n) throws IOException {
        this._verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            this._writeQuotedLong(n);
            return;
        }
        if (this._outputTail + 21 >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputTail = NumberOutput.outputLong(n, this._outputBuffer, this._outputTail);
    }
    
    private void _writeQuotedLong(final long n) throws IOException {
        if (this._outputTail + 23 >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._outputTail = NumberOutput.outputLong(n, this._outputBuffer, this._outputTail);
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    @Override
    public void writeNumber(final BigInteger bigInteger) throws IOException {
        this._verifyValueWrite("write a number");
        if (bigInteger == null) {
            this._writeNull();
        }
        else if (this._cfgNumbersAsStrings) {
            this._writeQuotedRaw(bigInteger.toString());
        }
        else {
            this.writeRaw(bigInteger.toString());
        }
    }
    
    @Override
    public void writeNumber(final double n) throws IOException {
        if (this._cfgNumbersAsStrings || (this.isEnabled(Feature.QUOTE_NON_NUMERIC_NUMBERS) && (Double.isNaN(n) || Double.isInfinite(n)))) {
            this.writeString(String.valueOf(n));
            return;
        }
        this._verifyValueWrite("write a number");
        this.writeRaw(String.valueOf(n));
    }
    
    @Override
    public void writeNumber(final float n) throws IOException {
        if (this._cfgNumbersAsStrings || (this.isEnabled(Feature.QUOTE_NON_NUMERIC_NUMBERS) && (Float.isNaN(n) || Float.isInfinite(n)))) {
            this.writeString(String.valueOf(n));
            return;
        }
        this._verifyValueWrite("write a number");
        this.writeRaw(String.valueOf(n));
    }
    
    @Override
    public void writeNumber(final BigDecimal bigDecimal) throws IOException {
        this._verifyValueWrite("write a number");
        if (bigDecimal == null) {
            this._writeNull();
        }
        else if (this._cfgNumbersAsStrings) {
            this._writeQuotedRaw(this._asString(bigDecimal));
        }
        else {
            this.writeRaw(this._asString(bigDecimal));
        }
    }
    
    @Override
    public void writeNumber(final String s) throws IOException {
        this._verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            this._writeQuotedRaw(s);
        }
        else {
            this.writeRaw(s);
        }
    }
    
    private void _writeQuotedRaw(final String s) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this.writeRaw(s);
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    @Override
    public void writeBoolean(final boolean b) throws IOException {
        this._verifyValueWrite("write a boolean value");
        if (this._outputTail + 5 >= this._outputEnd) {
            this._flushBuffer();
        }
        int outputTail = this._outputTail;
        final char[] outputBuffer = this._outputBuffer;
        if (b) {
            outputBuffer[outputTail] = 't';
            outputBuffer[++outputTail] = 'r';
            outputBuffer[++outputTail] = 'u';
            outputBuffer[++outputTail] = 'e';
        }
        else {
            outputBuffer[outputTail] = 'f';
            outputBuffer[++outputTail] = 'a';
            outputBuffer[++outputTail] = 'l';
            outputBuffer[++outputTail] = 's';
            outputBuffer[++outputTail] = 'e';
        }
        this._outputTail = outputTail + 1;
    }
    
    @Override
    public void writeNull() throws IOException {
        this._verifyValueWrite("write a null");
        this._writeNull();
    }
    
    @Override
    protected final void _verifyValueWrite(final String s) throws IOException {
        final int writeValue = this._writeContext.writeValue();
        if (this._cfgPrettyPrinter != null) {
            this._verifyPrettyValueWrite(s, writeValue);
            return;
        }
        char c = '\0';
        switch (writeValue) {
            default:
                return;
            case 1:
                c = ',';
                break;
            case 2:
                c = ':';
                break;
            case 3:
                if (this._rootValueSeparator != null) {
                    this.writeRaw(this._rootValueSeparator.getValue());
                }
                return;
            case 5:
                this._reportCantWriteValueExpectName(s);
                return;
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = c;
    }
    
    @Override
    public void flush() throws IOException {
        this._flushBuffer();
        if (this._writer != null && this.isEnabled(Feature.FLUSH_PASSED_TO_STREAM)) {
            this._writer.flush();
        }
    }
    
    @Override
    public void close() throws IOException {
        super.close();
        if (this._outputBuffer != null && this.isEnabled(Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                final JsonStreamContext outputContext = this.getOutputContext();
                if (outputContext.inArray()) {
                    this.writeEndArray();
                }
                else {
                    if (!outputContext.inObject()) {
                        break;
                    }
                    this.writeEndObject();
                }
            }
        }
        this._flushBuffer();
        this._outputHead = 0;
        this._outputTail = 0;
        if (this._writer != null) {
            if (this._ioContext.isResourceManaged() || this.isEnabled(Feature.AUTO_CLOSE_TARGET)) {
                this._writer.close();
            }
            else if (this.isEnabled(Feature.FLUSH_PASSED_TO_STREAM)) {
                this._writer.flush();
            }
        }
        this._releaseBuffers();
    }
    
    @Override
    protected void _releaseBuffers() {
        final char[] outputBuffer = this._outputBuffer;
        if (outputBuffer != null) {
            this._outputBuffer = null;
            this._ioContext.releaseConcatBuffer(outputBuffer);
        }
        final char[] charBuffer = this._charBuffer;
        if (charBuffer != null) {
            this._charBuffer = null;
            this._ioContext.releaseNameCopyBuffer(charBuffer);
        }
    }
    
    private void _writeString(final String s) throws IOException {
        final int length = s.length();
        if (length > this._outputEnd) {
            this._writeLongString(s);
            return;
        }
        if (this._outputTail + length > this._outputEnd) {
            this._flushBuffer();
        }
        s.getChars(0, length, this._outputBuffer, this._outputTail);
        if (this._characterEscapes != null) {
            this._writeStringCustom(length);
        }
        else if (this._maximumNonEscapedChar != 0) {
            this._writeStringASCII(length, this._maximumNonEscapedChar);
        }
        else {
            this._writeString2(length);
        }
    }
    
    private void _writeString2(final int n) throws IOException {
        final int n2 = this._outputTail + n;
        final int[] outputEscapes = this._outputEscapes;
        final int length = outputEscapes.length;
    Label_0137:
        while (this._outputTail < n2) {
            while (true) {
                final char c = this._outputBuffer[this._outputTail];
                if (c < length && outputEscapes[c] != 0) {
                    final int n3 = this._outputTail - this._outputHead;
                    if (n3 > 0) {
                        this._writer.write(this._outputBuffer, this._outputHead, n3);
                    }
                    final char c2 = this._outputBuffer[this._outputTail++];
                    this._prependOrWriteCharacterEscape(c2, outputEscapes[c2]);
                    break;
                }
                if (++this._outputTail >= n2) {
                    break Label_0137;
                }
            }
        }
    }
    
    private void _writeLongString(final String s) throws IOException {
        this._flushBuffer();
        final int length = s.length();
        int i = 0;
        do {
            final int outputEnd = this._outputEnd;
            final int n = (i + outputEnd > length) ? (length - i) : outputEnd;
            s.getChars(i, i + n, this._outputBuffer, 0);
            if (this._characterEscapes != null) {
                this._writeSegmentCustom(n);
            }
            else if (this._maximumNonEscapedChar != 0) {
                this._writeSegmentASCII(n, this._maximumNonEscapedChar);
            }
            else {
                this._writeSegment(n);
            }
            i += n;
        } while (i < length);
    }
    
    private void _writeSegment(final int n) throws IOException {
        final int[] outputEscapes = this._outputEscapes;
        final int length = outputEscapes.length;
        int prependOrWriteCharacterEscape;
        char c;
        for (int i = prependOrWriteCharacterEscape = 0; i < n; ++i, prependOrWriteCharacterEscape = this._prependOrWriteCharacterEscape(this._outputBuffer, i, n, c, outputEscapes[c])) {
            do {
                c = this._outputBuffer[i];
                if (c < length && outputEscapes[c] != 0) {
                    break;
                }
            } while (++i < n);
            final int n2 = i - prependOrWriteCharacterEscape;
            if (n2 > 0) {
                this._writer.write(this._outputBuffer, prependOrWriteCharacterEscape, n2);
                if (i >= n) {
                    break;
                }
            }
        }
    }
    
    private void _writeString(final char[] array, int i, int n) throws IOException {
        if (this._characterEscapes != null) {
            this._writeStringCustom(array, i, n);
            return;
        }
        if (this._maximumNonEscapedChar != 0) {
            this._writeStringASCII(array, i, n, this._maximumNonEscapedChar);
            return;
        }
        n += i;
        final int[] outputEscapes = this._outputEscapes;
        final int length = outputEscapes.length;
        while (i < n) {
            final int n2 = i;
            do {
                final char c = array[i];
                if (c < length && outputEscapes[c] != 0) {
                    break;
                }
            } while (++i < n);
            final int n3 = i - n2;
            if (n3 < 32) {
                if (this._outputTail + n3 > this._outputEnd) {
                    this._flushBuffer();
                }
                if (n3 > 0) {
                    System.arraycopy(array, n2, this._outputBuffer, this._outputTail, n3);
                    this._outputTail += n3;
                }
            }
            else {
                this._flushBuffer();
                this._writer.write(array, n2, n3);
            }
            if (i >= n) {
                break;
            }
            final char c2 = array[i++];
            this._appendCharacterEscape(c2, outputEscapes[c2]);
        }
    }
    
    private void _writeStringASCII(final int n, final int n2) throws IOException, JsonGenerationException {
        final int n3 = this._outputTail + n;
        final int[] outputEscapes = this._outputEscapes;
        final int min = Math.min(outputEscapes.length, n2 + 1);
    Label_0027:
        while (this._outputTail < n3) {
            do {
                final char c = this._outputBuffer[this._outputTail];
                int n4;
                if (c < min) {
                    n4 = outputEscapes[c];
                    if (n4 == 0) {
                        continue;
                    }
                }
                else {
                    if (c <= n2) {
                        continue;
                    }
                    n4 = -1;
                }
                final int n5 = this._outputTail - this._outputHead;
                if (n5 > 0) {
                    this._writer.write(this._outputBuffer, this._outputHead, n5);
                }
                ++this._outputTail;
                this._prependOrWriteCharacterEscape(c, n4);
                continue Label_0027;
            } while (++this._outputTail < n3);
            break;
        }
    }
    
    private void _writeSegmentASCII(final int n, final int n2) throws IOException, JsonGenerationException {
        final int[] outputEscapes = this._outputEscapes;
        final int min = Math.min(outputEscapes.length, n2 + 1);
        int i = 0;
        int n3 = 0;
        int prependOrWriteCharacterEscape = i;
        while (i < n) {
            char c;
            do {
                c = this._outputBuffer[i];
                if (c < min) {
                    n3 = outputEscapes[c];
                    if (n3 != 0) {
                        break;
                    }
                    continue;
                }
                else {
                    if (c > n2) {
                        n3 = -1;
                        break;
                    }
                    continue;
                }
            } while (++i < n);
            final int n4 = i - prependOrWriteCharacterEscape;
            if (n4 > 0) {
                this._writer.write(this._outputBuffer, prependOrWriteCharacterEscape, n4);
                if (i >= n) {
                    break;
                }
            }
            ++i;
            prependOrWriteCharacterEscape = this._prependOrWriteCharacterEscape(this._outputBuffer, i, n, c, n3);
        }
    }
    
    private void _writeStringASCII(final char[] array, int i, int n, final int n2) throws IOException, JsonGenerationException {
        n += i;
        final int[] outputEscapes = this._outputEscapes;
        final int min = Math.min(outputEscapes.length, n2 + 1);
        int n3 = 0;
        while (i < n) {
            final int n4 = i;
            char c;
            do {
                c = array[i];
                if (c < min) {
                    n3 = outputEscapes[c];
                    if (n3 != 0) {
                        break;
                    }
                    continue;
                }
                else {
                    if (c > n2) {
                        n3 = -1;
                        break;
                    }
                    continue;
                }
            } while (++i < n);
            final int n5 = i - n4;
            if (n5 < 32) {
                if (this._outputTail + n5 > this._outputEnd) {
                    this._flushBuffer();
                }
                if (n5 > 0) {
                    System.arraycopy(array, n4, this._outputBuffer, this._outputTail, n5);
                    this._outputTail += n5;
                }
            }
            else {
                this._flushBuffer();
                this._writer.write(array, n4, n5);
            }
            if (i >= n) {
                break;
            }
            ++i;
            this._appendCharacterEscape(c, n3);
        }
    }
    
    private void _writeStringCustom(final int n) throws IOException, JsonGenerationException {
        final int n2 = this._outputTail + n;
        final int[] outputEscapes = this._outputEscapes;
        final int n3 = (this._maximumNonEscapedChar < 1) ? 65535 : this._maximumNonEscapedChar;
        final int min = Math.min(outputEscapes.length, n3 + '\u0001');
        final CharacterEscapes characterEscapes = this._characterEscapes;
    Label_0052:
        while (this._outputTail < n2) {
            do {
                final char c = this._outputBuffer[this._outputTail];
                int n4;
                if (c < min) {
                    n4 = outputEscapes[c];
                    if (n4 == 0) {
                        continue;
                    }
                }
                else if (c > n3) {
                    n4 = -1;
                }
                else {
                    if ((this._currentEscape = characterEscapes.getEscapeSequence(c)) == null) {
                        continue;
                    }
                    n4 = -2;
                }
                final int n5 = this._outputTail - this._outputHead;
                if (n5 > 0) {
                    this._writer.write(this._outputBuffer, this._outputHead, n5);
                }
                ++this._outputTail;
                this._prependOrWriteCharacterEscape(c, n4);
                continue Label_0052;
            } while (++this._outputTail < n2);
            break;
        }
    }
    
    private void _writeSegmentCustom(final int n) throws IOException, JsonGenerationException {
        final int[] outputEscapes = this._outputEscapes;
        final int n2 = (this._maximumNonEscapedChar < 1) ? 65535 : this._maximumNonEscapedChar;
        final int min = Math.min(outputEscapes.length, n2 + '\u0001');
        final CharacterEscapes characterEscapes = this._characterEscapes;
        int i = 0;
        int n3 = 0;
        int prependOrWriteCharacterEscape = i;
        while (i < n) {
            char c;
            do {
                c = this._outputBuffer[i];
                if (c < min) {
                    n3 = outputEscapes[c];
                    if (n3 != 0) {
                        break;
                    }
                    continue;
                }
                else {
                    if (c > n2) {
                        n3 = -1;
                        break;
                    }
                    if ((this._currentEscape = characterEscapes.getEscapeSequence(c)) != null) {
                        n3 = -2;
                        break;
                    }
                    continue;
                }
            } while (++i < n);
            final int n4 = i - prependOrWriteCharacterEscape;
            if (n4 > 0) {
                this._writer.write(this._outputBuffer, prependOrWriteCharacterEscape, n4);
                if (i >= n) {
                    break;
                }
            }
            ++i;
            prependOrWriteCharacterEscape = this._prependOrWriteCharacterEscape(this._outputBuffer, i, n, c, n3);
        }
    }
    
    private void _writeStringCustom(final char[] array, int i, int n) throws IOException, JsonGenerationException {
        n += i;
        final int[] outputEscapes = this._outputEscapes;
        final int n2 = (this._maximumNonEscapedChar < 1) ? 65535 : this._maximumNonEscapedChar;
        final int min = Math.min(outputEscapes.length, n2 + '\u0001');
        final CharacterEscapes characterEscapes = this._characterEscapes;
        int n3 = 0;
        while (i < n) {
            final int n4 = i;
            char c;
            do {
                c = array[i];
                if (c < min) {
                    n3 = outputEscapes[c];
                    if (n3 != 0) {
                        break;
                    }
                    continue;
                }
                else {
                    if (c > n2) {
                        n3 = -1;
                        break;
                    }
                    if ((this._currentEscape = characterEscapes.getEscapeSequence(c)) != null) {
                        n3 = -2;
                        break;
                    }
                    continue;
                }
            } while (++i < n);
            final int n5 = i - n4;
            if (n5 < 32) {
                if (this._outputTail + n5 > this._outputEnd) {
                    this._flushBuffer();
                }
                if (n5 > 0) {
                    System.arraycopy(array, n4, this._outputBuffer, this._outputTail, n5);
                    this._outputTail += n5;
                }
            }
            else {
                this._flushBuffer();
                this._writer.write(array, n4, n5);
            }
            if (i >= n) {
                break;
            }
            ++i;
            this._appendCharacterEscape(c, n3);
        }
    }
    
    protected final void _writeBinary(final Base64Variant base64Variant, final byte[] array, int i, final int n) throws IOException, JsonGenerationException {
        final int n2 = n - 3;
        final int n3 = this._outputEnd - 6;
        int n4 = base64Variant.getMaxLineLength() >> 2;
        while (i <= n2) {
            if (this._outputTail > n3) {
                this._flushBuffer();
            }
            this._outputTail = base64Variant.encodeBase64Chunk((array[i++] << 8 | (array[i++] & 0xFF)) << 8 | (array[i++] & 0xFF), this._outputBuffer, this._outputTail);
            if (--n4 <= 0) {
                this._outputBuffer[this._outputTail++] = '\\';
                this._outputBuffer[this._outputTail++] = 'n';
                n4 = base64Variant.getMaxLineLength() >> 2;
            }
        }
        final int n5 = n - i;
        if (n5 > 0) {
            if (this._outputTail > n3) {
                this._flushBuffer();
            }
            int n6 = array[i++] << 16;
            if (n5 == 2) {
                n6 |= (array[i++] & 0xFF) << 8;
            }
            this._outputTail = base64Variant.encodeBase64Partial(n6, n5, this._outputBuffer, this._outputTail);
        }
    }
    
    protected final int _writeBinary(final Base64Variant base64Variant, final InputStream inputStream, final byte[] array, int i) throws IOException, JsonGenerationException {
        int n = 0;
        int readMore = 0;
        int n2 = -3;
        final int n3 = this._outputEnd - 6;
        int n4 = base64Variant.getMaxLineLength() >> 2;
        while (i > 2) {
            if (n > n2) {
                readMore = this._readMore(inputStream, array, n, readMore, i);
                n = 0;
                if (readMore < 3) {
                    break;
                }
                n2 = readMore - 3;
            }
            if (this._outputTail > n3) {
                this._flushBuffer();
            }
            final int n5 = (array[n++] << 8 | (array[n++] & 0xFF)) << 8 | (array[n++] & 0xFF);
            i -= 3;
            this._outputTail = base64Variant.encodeBase64Chunk(n5, this._outputBuffer, this._outputTail);
            if (--n4 <= 0) {
                this._outputBuffer[this._outputTail++] = '\\';
                this._outputBuffer[this._outputTail++] = 'n';
                n4 = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (i > 0) {
            final int readMore2 = this._readMore(inputStream, array, n, readMore, i);
            int n6 = 0;
            if (readMore2 > 0) {
                if (this._outputTail > n3) {
                    this._flushBuffer();
                }
                int n7 = array[n6++] << 16;
                int n8;
                if (n6 < readMore2) {
                    n7 |= (array[n6] & 0xFF) << 8;
                    n8 = 2;
                }
                else {
                    n8 = 1;
                }
                this._outputTail = base64Variant.encodeBase64Partial(n7, n8, this._outputBuffer, this._outputTail);
                i -= n8;
            }
        }
        return i;
    }
    
    protected final int _writeBinary(final Base64Variant base64Variant, final InputStream inputStream, final byte[] array) throws IOException, JsonGenerationException {
        int n = 0;
        int readMore = 0;
        int n2 = -3;
        int n3 = 0;
        final int n4 = this._outputEnd - 6;
        int n5 = base64Variant.getMaxLineLength() >> 2;
        while (true) {
            if (n > n2) {
                readMore = this._readMore(inputStream, array, n, readMore, array.length);
                n = 0;
                if (readMore < 3) {
                    break;
                }
                n2 = readMore - 3;
            }
            if (this._outputTail > n4) {
                this._flushBuffer();
            }
            final int n6 = (array[n++] << 8 | (array[n++] & 0xFF)) << 8 | (array[n++] & 0xFF);
            n3 += 3;
            this._outputTail = base64Variant.encodeBase64Chunk(n6, this._outputBuffer, this._outputTail);
            if (--n5 <= 0) {
                this._outputBuffer[this._outputTail++] = '\\';
                this._outputBuffer[this._outputTail++] = 'n';
                n5 = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (n < readMore) {
            if (this._outputTail > n4) {
                this._flushBuffer();
            }
            int n7 = array[n++] << 16;
            int n8 = 1;
            if (n < readMore) {
                n7 |= (array[n] & 0xFF) << 8;
                n8 = 2;
            }
            n3 += n8;
            this._outputTail = base64Variant.encodeBase64Partial(n7, n8, this._outputBuffer, this._outputTail);
        }
        return n3;
    }
    
    private int _readMore(final InputStream inputStream, final byte[] array, int i, int n, int min) throws IOException {
        int n2;
        for (n2 = 0; i < n; array[n2++] = array[i++]) {}
        i = 0;
        n = n2;
        min = Math.min(min, array.length);
        return n;
    }
    
    private final void _writeNull() throws IOException {
        if (this._outputTail + 4 >= this._outputEnd) {
            this._flushBuffer();
        }
        int outputTail = this._outputTail;
        final char[] outputBuffer = this._outputBuffer;
        outputBuffer[outputTail] = 'n';
        outputBuffer[++outputTail] = 'u';
        outputBuffer[++outputTail] = 'l';
        outputBuffer[++outputTail] = 'l';
        this._outputTail = outputTail + 1;
    }
    
    private void _prependOrWriteCharacterEscape(char c, final int n) throws IOException, JsonGenerationException {
        if (n >= 0) {
            if (this._outputTail >= 2) {
                int outputHead = this._outputTail - 2;
                this._outputHead = outputHead;
                this._outputBuffer[outputHead++] = '\\';
                this._outputBuffer[outputHead] = (char)n;
                return;
            }
            char[] array = this._entityBuffer;
            if (array == null) {
                array = this._allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            array[1] = (char)n;
            this._writer.write(array, 0, 2);
        }
        else if (n != -2) {
            if (this._outputTail >= 6) {
                final char[] outputBuffer = this._outputBuffer;
                int outputHead2 = this._outputTail - 6;
                outputBuffer[this._outputHead = outputHead2] = '\\';
                outputBuffer[++outputHead2] = 'u';
                if (c > '\u00ff') {
                    final int n2 = c >> 8 & 0xFF;
                    outputBuffer[++outputHead2] = WriterBasedJsonGenerator.HEX_CHARS[n2 >> 4];
                    outputBuffer[++outputHead2] = WriterBasedJsonGenerator.HEX_CHARS[n2 & 0xF];
                    c &= '\u00ff';
                }
                else {
                    outputBuffer[++outputHead2] = '0';
                    outputBuffer[++outputHead2] = '0';
                }
                outputBuffer[++outputHead2] = WriterBasedJsonGenerator.HEX_CHARS[c >> 4];
                outputBuffer[++outputHead2] = WriterBasedJsonGenerator.HEX_CHARS[c & '\u000f'];
                return;
            }
            char[] array2 = this._entityBuffer;
            if (array2 == null) {
                array2 = this._allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            if (c > '\u00ff') {
                final int n3 = c >> 8 & 0xFF;
                final int n4 = c & '\u00ff';
                array2[10] = WriterBasedJsonGenerator.HEX_CHARS[n3 >> 4];
                array2[11] = WriterBasedJsonGenerator.HEX_CHARS[n3 & 0xF];
                array2[12] = WriterBasedJsonGenerator.HEX_CHARS[n4 >> 4];
                array2[13] = WriterBasedJsonGenerator.HEX_CHARS[n4 & 0xF];
                this._writer.write(array2, 8, 6);
            }
            else {
                array2[6] = WriterBasedJsonGenerator.HEX_CHARS[c >> 4];
                array2[7] = WriterBasedJsonGenerator.HEX_CHARS[c & '\u000f'];
                this._writer.write(array2, 2, 6);
            }
        }
        else {
            String s;
            if (this._currentEscape == null) {
                s = this._characterEscapes.getEscapeSequence(c).getValue();
            }
            else {
                s = this._currentEscape.getValue();
                this._currentEscape = null;
            }
            final int length = s.length();
            if (this._outputTail >= length) {
                final int outputHead3 = this._outputTail - length;
                this._outputHead = outputHead3;
                s.getChars(0, length, this._outputBuffer, outputHead3);
                return;
            }
            this._outputHead = this._outputTail;
            this._writer.write(s);
        }
    }
    
    private int _prependOrWriteCharacterEscape(final char[] array, int n, final int n2, char c, final int n3) throws IOException, JsonGenerationException {
        if (n3 >= 0) {
            if (n > 1 && n < n2) {
                n -= 2;
                array[n] = '\\';
                array[n + 1] = (char)n3;
            }
            else {
                char[] array2 = this._entityBuffer;
                if (array2 == null) {
                    array2 = this._allocateEntityBuffer();
                }
                array2[1] = (char)n3;
                this._writer.write(array2, 0, 2);
            }
            return n;
        }
        if (n3 != -2) {
            if (n > 5 && n < n2) {
                n -= 6;
                array[n++] = '\\';
                array[n++] = 'u';
                if (c > '\u00ff') {
                    final int n4 = c >> 8 & 0xFF;
                    array[n++] = WriterBasedJsonGenerator.HEX_CHARS[n4 >> 4];
                    array[n++] = WriterBasedJsonGenerator.HEX_CHARS[n4 & 0xF];
                    c &= '\u00ff';
                }
                else {
                    array[n++] = '0';
                    array[n++] = '0';
                }
                array[n++] = WriterBasedJsonGenerator.HEX_CHARS[c >> 4];
                array[n] = WriterBasedJsonGenerator.HEX_CHARS[c & '\u000f'];
                n -= 5;
            }
            else {
                char[] array3 = this._entityBuffer;
                if (array3 == null) {
                    array3 = this._allocateEntityBuffer();
                }
                this._outputHead = this._outputTail;
                if (c > '\u00ff') {
                    final int n5 = c >> 8 & 0xFF;
                    final int n6 = c & '\u00ff';
                    array3[10] = WriterBasedJsonGenerator.HEX_CHARS[n5 >> 4];
                    array3[11] = WriterBasedJsonGenerator.HEX_CHARS[n5 & 0xF];
                    array3[12] = WriterBasedJsonGenerator.HEX_CHARS[n6 >> 4];
                    array3[13] = WriterBasedJsonGenerator.HEX_CHARS[n6 & 0xF];
                    this._writer.write(array3, 8, 6);
                }
                else {
                    array3[6] = WriterBasedJsonGenerator.HEX_CHARS[c >> 4];
                    array3[7] = WriterBasedJsonGenerator.HEX_CHARS[c & '\u000f'];
                    this._writer.write(array3, 2, 6);
                }
            }
            return n;
        }
        String s;
        if (this._currentEscape == null) {
            s = this._characterEscapes.getEscapeSequence(c).getValue();
        }
        else {
            s = this._currentEscape.getValue();
            this._currentEscape = null;
        }
        final int length = s.length();
        if (n >= length && n < n2) {
            n -= length;
            s.getChars(0, length, array, n);
        }
        else {
            this._writer.write(s);
        }
        return n;
    }
    
    private void _appendCharacterEscape(char c, final int n) throws IOException, JsonGenerationException {
        if (n >= 0) {
            if (this._outputTail + 2 > this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = '\\';
            this._outputBuffer[this._outputTail++] = (char)n;
            return;
        }
        if (n != -2) {
            if (this._outputTail + 5 >= this._outputEnd) {
                this._flushBuffer();
            }
            int outputTail = this._outputTail;
            final char[] outputBuffer = this._outputBuffer;
            outputBuffer[outputTail++] = '\\';
            outputBuffer[outputTail++] = 'u';
            if (c > '\u00ff') {
                final int n2 = c >> 8 & 0xFF;
                outputBuffer[outputTail++] = WriterBasedJsonGenerator.HEX_CHARS[n2 >> 4];
                outputBuffer[outputTail++] = WriterBasedJsonGenerator.HEX_CHARS[n2 & 0xF];
                c &= '\u00ff';
            }
            else {
                outputBuffer[outputTail++] = '0';
                outputBuffer[outputTail++] = '0';
            }
            outputBuffer[outputTail++] = WriterBasedJsonGenerator.HEX_CHARS[c >> 4];
            outputBuffer[outputTail++] = WriterBasedJsonGenerator.HEX_CHARS[c & '\u000f'];
            this._outputTail = outputTail;
            return;
        }
        String s;
        if (this._currentEscape == null) {
            s = this._characterEscapes.getEscapeSequence(c).getValue();
        }
        else {
            s = this._currentEscape.getValue();
            this._currentEscape = null;
        }
        final int length = s.length();
        if (this._outputTail + length > this._outputEnd) {
            this._flushBuffer();
            if (length > this._outputEnd) {
                this._writer.write(s);
                return;
            }
        }
        s.getChars(0, length, this._outputBuffer, this._outputTail);
        this._outputTail += length;
    }
    
    private char[] _allocateEntityBuffer() {
        return this._entityBuffer = new char[] { '\\', '\0', '\\', 'u', '0', '0', '\0', '\0', '\\', 'u', '\0', '\0', '\0', '\0' };
    }
    
    private char[] _allocateCopyBuffer() {
        if (this._charBuffer == null) {
            this._charBuffer = this._ioContext.allocNameCopyBuffer(2000);
        }
        return this._charBuffer;
    }
    
    protected void _flushBuffer() throws IOException {
        final int n = this._outputTail - this._outputHead;
        if (n > 0) {
            final int outputHead = this._outputHead;
            final int n2 = 0;
            this._outputHead = n2;
            this._outputTail = n2;
            this._writer.write(this._outputBuffer, outputHead, n);
        }
    }
    
    static {
        HEX_CHARS = CharTypes.copyHexChars();
    }
}
