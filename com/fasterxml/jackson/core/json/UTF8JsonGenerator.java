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
import com.fasterxml.jackson.core.SerializableString;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.io.IOContext;
import java.io.OutputStream;

public class UTF8JsonGenerator extends JsonGeneratorImpl
{
    private static final byte BYTE_u = 117;
    private static final byte BYTE_0 = 48;
    private static final byte BYTE_LBRACKET = 91;
    private static final byte BYTE_RBRACKET = 93;
    private static final byte BYTE_LCURLY = 123;
    private static final byte BYTE_RCURLY = 125;
    private static final byte BYTE_BACKSLASH = 92;
    private static final byte BYTE_COMMA = 44;
    private static final byte BYTE_COLON = 58;
    private static final int MAX_BYTES_TO_BUFFER = 512;
    private static final byte[] HEX_CHARS;
    private static final byte[] NULL_BYTES;
    private static final byte[] TRUE_BYTES;
    private static final byte[] FALSE_BYTES;
    protected final OutputStream _outputStream;
    protected byte _quoteChar;
    protected byte[] _outputBuffer;
    protected int _outputTail;
    protected final int _outputEnd;
    protected final int _outputMaxContiguous;
    protected char[] _charBuffer;
    protected final int _charBufferLength;
    protected byte[] _entityBuffer;
    protected boolean _bufferRecyclable;
    
    public UTF8JsonGenerator(final IOContext ioContext, final int n, final ObjectCodec objectCodec, final OutputStream outputStream) {
        super(ioContext, n, objectCodec);
        this._quoteChar = 34;
        this._outputStream = outputStream;
        this._bufferRecyclable = true;
        this._outputBuffer = ioContext.allocWriteEncodingBuffer();
        this._outputEnd = this._outputBuffer.length;
        this._outputMaxContiguous = this._outputEnd >> 3;
        this._charBuffer = ioContext.allocConcatBuffer();
        this._charBufferLength = this._charBuffer.length;
        if (this.isEnabled(Feature.ESCAPE_NON_ASCII)) {
            this.setHighestNonEscapedChar(127);
        }
    }
    
    public UTF8JsonGenerator(final IOContext ioContext, final int n, final ObjectCodec objectCodec, final OutputStream outputStream, final byte[] outputBuffer, final int outputTail, final boolean bufferRecyclable) {
        super(ioContext, n, objectCodec);
        this._quoteChar = 34;
        this._outputStream = outputStream;
        this._bufferRecyclable = bufferRecyclable;
        this._outputTail = outputTail;
        this._outputBuffer = outputBuffer;
        this._outputEnd = this._outputBuffer.length;
        this._outputMaxContiguous = this._outputEnd >> 3;
        this._charBuffer = ioContext.allocConcatBuffer();
        this._charBufferLength = this._charBuffer.length;
    }
    
    @Override
    public Object getOutputTarget() {
        return this._outputStream;
    }
    
    @Override
    public int getOutputBuffered() {
        return this._outputTail;
    }
    
    @Override
    public void writeFieldName(final String s) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            this._writePPFieldName(s);
            return;
        }
        final int writeFieldName = this._writeContext.writeFieldName(s);
        if (writeFieldName == 4) {
            this._reportError("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = 44;
        }
        if (this._cfgUnqNames) {
            this._writeStringSegments(s, false);
            return;
        }
        final int length = s.length();
        if (length > this._charBufferLength) {
            this._writeStringSegments(s, true);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        if (length <= this._outputMaxContiguous) {
            if (this._outputTail + length > this._outputEnd) {
                this._flushBuffer();
            }
            this._writeStringSegment(s, 0, length);
        }
        else {
            this._writeStringSegments(s, 0, length);
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    @Override
    public void writeFieldName(final SerializableString serializableString) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            this._writePPFieldName(serializableString);
            return;
        }
        final int writeFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            this._reportError("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = 44;
        }
        if (this._cfgUnqNames) {
            this._writeUnq(serializableString);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        final int appendQuotedUTF8 = serializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (appendQuotedUTF8 < 0) {
            this._writeBytes(serializableString.asQuotedUTF8());
        }
        else {
            this._outputTail += appendQuotedUTF8;
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    private final void _writeUnq(final SerializableString serializableString) throws IOException {
        final int appendQuotedUTF8 = serializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (appendQuotedUTF8 < 0) {
            this._writeBytes(serializableString.asQuotedUTF8());
        }
        else {
            this._outputTail += appendQuotedUTF8;
        }
    }
    
    @Override
    public final void writeStartArray() throws IOException {
        this._verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartArray(this);
        }
        else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = 91;
        }
    }
    
    @Override
    public final void writeEndArray() throws IOException {
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
            this._outputBuffer[this._outputTail++] = 93;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }
    
    @Override
    public final void writeStartObject() throws IOException {
        this._verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
        }
        else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = 123;
        }
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
            this._outputBuffer[this._outputTail++] = 123;
        }
    }
    
    @Override
    public final void writeEndObject() throws IOException {
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
            this._outputBuffer[this._outputTail++] = 125;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }
    
    protected final void _writePPFieldName(final String s) throws IOException {
        final int writeFieldName = this._writeContext.writeFieldName(s);
        if (writeFieldName == 4) {
            this._reportError("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        }
        else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (this._cfgUnqNames) {
            this._writeStringSegments(s, false);
            return;
        }
        final int length = s.length();
        if (length > this._charBufferLength) {
            this._writeStringSegments(s, true);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        s.getChars(0, length, this._charBuffer, 0);
        if (length <= this._outputMaxContiguous) {
            if (this._outputTail + length > this._outputEnd) {
                this._flushBuffer();
            }
            this._writeStringSegment(this._charBuffer, 0, length);
        }
        else {
            this._writeStringSegments(this._charBuffer, 0, length);
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    protected final void _writePPFieldName(final SerializableString serializableString) throws IOException {
        final int writeFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            this._reportError("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        }
        else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        final boolean b = !this._cfgUnqNames;
        if (b) {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = this._quoteChar;
        }
        this._writeBytes(serializableString.asQuotedUTF8());
        if (b) {
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
        final int length = s.length();
        if (length > this._outputMaxContiguous) {
            this._writeStringSegments(s, true);
            return;
        }
        if (this._outputTail + length >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._writeStringSegment(s, 0, length);
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
        final char[] charBuffer = this._charBuffer;
        if (this._outputTail + n >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        while (i > 0) {
            final int read = reader.read(charBuffer, 0, Math.min(i, charBuffer.length));
            if (read <= 0) {
                break;
            }
            if (this._outputTail + n >= this._outputEnd) {
                this._flushBuffer();
            }
            this._writeStringSegments(charBuffer, 0, read);
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
        if (n2 <= this._outputMaxContiguous) {
            if (this._outputTail + n2 > this._outputEnd) {
                this._flushBuffer();
            }
            this._writeStringSegment(array, n, n2);
        }
        else {
            this._writeStringSegments(array, n, n2);
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    @Override
    public final void writeString(final SerializableString serializableString) throws IOException {
        this._verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        final int appendQuotedUTF8 = serializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (appendQuotedUTF8 < 0) {
            this._writeBytes(serializableString.asQuotedUTF8());
        }
        else {
            this._outputTail += appendQuotedUTF8;
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    @Override
    public void writeRawUTF8String(final byte[] array, final int n, final int n2) throws IOException {
        this._verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._writeBytes(array, n, n2);
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    @Override
    public void writeUTF8String(final byte[] array, final int n, final int n2) throws IOException {
        this._verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        if (n2 <= this._outputMaxContiguous) {
            this._writeUTF8Segment(array, n, n2);
        }
        else {
            this._writeUTF8Segments(array, n, n2);
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }
    
    @Override
    public void writeRaw(final String s) throws IOException {
        final int length = s.length();
        final char[] charBuffer = this._charBuffer;
        if (length <= charBuffer.length) {
            s.getChars(0, length, charBuffer, 0);
            this.writeRaw(charBuffer, 0, length);
        }
        else {
            this.writeRaw(s, 0, length);
        }
    }
    
    @Override
    public void writeRaw(final String s, int n, int i) throws IOException {
        final char[] charBuffer = this._charBuffer;
        final int length = charBuffer.length;
        if (i <= length) {
            s.getChars(n, n + i, charBuffer, 0);
            this.writeRaw(charBuffer, 0, i);
            return;
        }
        final int min = Math.min(length, (this._outputEnd >> 2) + (this._outputEnd >> 4));
        final int n2 = min * 3;
        while (i > 0) {
            int min2 = Math.min(min, i);
            s.getChars(n, n + min2, charBuffer, 0);
            if (this._outputTail + n2 > this._outputEnd) {
                this._flushBuffer();
            }
            if (min2 > 1) {
                final char c = charBuffer[min2 - 1];
                if (c >= '\ud800' && c <= '\udbff') {
                    --min2;
                }
            }
            this._writeRawSegment(charBuffer, 0, min2);
            n += min2;
            i -= min2;
        }
    }
    
    @Override
    public void writeRaw(final SerializableString serializableString) throws IOException {
        final byte[] unquotedUTF8 = serializableString.asUnquotedUTF8();
        if (unquotedUTF8.length > 0) {
            this._writeBytes(unquotedUTF8);
        }
    }
    
    @Override
    public void writeRawValue(final SerializableString serializableString) throws IOException {
        this._verifyValueWrite("write a raw (unencoded) value");
        final byte[] unquotedUTF8 = serializableString.asUnquotedUTF8();
        if (unquotedUTF8.length > 0) {
            this._writeBytes(unquotedUTF8);
        }
    }
    
    @Override
    public final void writeRaw(final char[] array, int i, int n) throws IOException {
        final int n2 = n + n + n;
        if (this._outputTail + n2 > this._outputEnd) {
            if (this._outputEnd < n2) {
                this._writeSegmentedRaw(array, i, n);
                return;
            }
            this._flushBuffer();
        }
        n += i;
    Label_0183:
        while (i < n) {
            while (true) {
                final char c = array[i];
                if (c > '\u007f') {
                    final char c2 = array[i++];
                    if (c2 < '\u0800') {
                        this._outputBuffer[this._outputTail++] = (byte)(0xC0 | c2 >> 6);
                        this._outputBuffer[this._outputTail++] = (byte)(0x80 | (c2 & '?'));
                    }
                    else {
                        i = this._outputRawMultiByteChar(c2, array, i, n);
                    }
                    break;
                }
                this._outputBuffer[this._outputTail++] = (byte)c;
                if (++i >= n) {
                    break Label_0183;
                }
            }
        }
    }
    
    @Override
    public void writeRaw(final char c) throws IOException {
        if (this._outputTail + 3 >= this._outputEnd) {
            this._flushBuffer();
        }
        final byte[] outputBuffer = this._outputBuffer;
        if (c <= '\u007f') {
            outputBuffer[this._outputTail++] = (byte)c;
        }
        else if (c < '\u0800') {
            outputBuffer[this._outputTail++] = (byte)(0xC0 | c >> 6);
            outputBuffer[this._outputTail++] = (byte)(0x80 | (c & '?'));
        }
        else {
            this._outputRawMultiByteChar(c, null, 0, 0);
        }
    }
    
    private final void _writeSegmentedRaw(final char[] array, int i, final int n) throws IOException {
        final int outputEnd = this._outputEnd;
        final byte[] outputBuffer = this._outputBuffer;
        final int n2 = i + n;
    Label_0182:
        while (i < n2) {
            while (true) {
                final char c = array[i];
                if (c >= '\u0080') {
                    if (this._outputTail + 3 >= this._outputEnd) {
                        this._flushBuffer();
                    }
                    final char c2 = array[i++];
                    if (c2 < '\u0800') {
                        outputBuffer[this._outputTail++] = (byte)(0xC0 | c2 >> 6);
                        outputBuffer[this._outputTail++] = (byte)(0x80 | (c2 & '?'));
                    }
                    else {
                        i = this._outputRawMultiByteChar(c2, array, i, n2);
                    }
                    break;
                }
                if (this._outputTail >= outputEnd) {
                    this._flushBuffer();
                }
                outputBuffer[this._outputTail++] = (byte)c;
                if (++i >= n2) {
                    break Label_0182;
                }
            }
        }
    }
    
    private void _writeRawSegment(final char[] array, int i, final int n) throws IOException {
    Label_0137:
        while (i < n) {
            while (true) {
                final char c = array[i];
                if (c > '\u007f') {
                    final char c2 = array[i++];
                    if (c2 < '\u0800') {
                        this._outputBuffer[this._outputTail++] = (byte)(0xC0 | c2 >> 6);
                        this._outputBuffer[this._outputTail++] = (byte)(0x80 | (c2 & '?'));
                    }
                    else {
                        i = this._outputRawMultiByteChar(c2, array, i, n);
                    }
                    break;
                }
                this._outputBuffer[this._outputTail++] = (byte)c;
                if (++i >= n) {
                    break Label_0137;
                }
            }
        }
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
        if (this._outputTail + 6 >= this._outputEnd) {
            this._flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            this._writeQuotedShort(n);
            return;
        }
        this._outputTail = NumberOutput.outputInt(n, this._outputBuffer, this._outputTail);
    }
    
    private final void _writeQuotedShort(final short n) throws IOException {
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
        if (this._outputTail + 11 >= this._outputEnd) {
            this._flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            this._writeQuotedInt(n);
            return;
        }
        this._outputTail = NumberOutput.outputInt(n, this._outputBuffer, this._outputTail);
    }
    
    private final void _writeQuotedInt(final int n) throws IOException {
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
    
    private final void _writeQuotedLong(final long n) throws IOException {
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
        if (this._cfgNumbersAsStrings || ((Double.isNaN(n) || Double.isInfinite(n)) && Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this._features))) {
            this.writeString(String.valueOf(n));
            return;
        }
        this._verifyValueWrite("write a number");
        this.writeRaw(String.valueOf(n));
    }
    
    @Override
    public void writeNumber(final float n) throws IOException {
        if (this._cfgNumbersAsStrings || ((Float.isNaN(n) || Float.isInfinite(n)) && Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this._features))) {
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
    
    private final void _writeQuotedRaw(final String s) throws IOException {
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
        final byte[] array = b ? UTF8JsonGenerator.TRUE_BYTES : UTF8JsonGenerator.FALSE_BYTES;
        final int length = array.length;
        System.arraycopy(array, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
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
        byte b = 0;
        switch (writeValue) {
            default:
                return;
            case 1:
                b = 44;
                break;
            case 2:
                b = 58;
                break;
            case 3:
                if (this._rootValueSeparator != null) {
                    final byte[] unquotedUTF8 = this._rootValueSeparator.asUnquotedUTF8();
                    if (unquotedUTF8.length > 0) {
                        this._writeBytes(unquotedUTF8);
                    }
                }
                return;
            case 5:
                this._reportCantWriteValueExpectName(s);
                return;
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = b;
    }
    
    @Override
    public void flush() throws IOException {
        this._flushBuffer();
        if (this._outputStream != null && this.isEnabled(Feature.FLUSH_PASSED_TO_STREAM)) {
            this._outputStream.flush();
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
        this._outputTail = 0;
        if (this._outputStream != null) {
            if (this._ioContext.isResourceManaged() || this.isEnabled(Feature.AUTO_CLOSE_TARGET)) {
                this._outputStream.close();
            }
            else if (this.isEnabled(Feature.FLUSH_PASSED_TO_STREAM)) {
                this._outputStream.flush();
            }
        }
        this._releaseBuffers();
    }
    
    @Override
    protected void _releaseBuffers() {
        final byte[] outputBuffer = this._outputBuffer;
        if (outputBuffer != null && this._bufferRecyclable) {
            this._outputBuffer = null;
            this._ioContext.releaseWriteEncodingBuffer(outputBuffer);
        }
        final char[] charBuffer = this._charBuffer;
        if (charBuffer != null) {
            this._charBuffer = null;
            this._ioContext.releaseConcatBuffer(charBuffer);
        }
    }
    
    private final void _writeBytes(final byte[] array) throws IOException {
        final int length = array.length;
        if (this._outputTail + length > this._outputEnd) {
            this._flushBuffer();
            if (length > 512) {
                this._outputStream.write(array, 0, length);
                return;
            }
        }
        System.arraycopy(array, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
    }
    
    private final void _writeBytes(final byte[] array, final int n, final int n2) throws IOException {
        if (this._outputTail + n2 > this._outputEnd) {
            this._flushBuffer();
            if (n2 > 512) {
                this._outputStream.write(array, n, n2);
                return;
            }
        }
        System.arraycopy(array, n, this._outputBuffer, this._outputTail, n2);
        this._outputTail += n2;
    }
    
    private final void _writeStringSegments(final String s, final boolean b) throws IOException {
        if (b) {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = this._quoteChar;
        }
        int i = s.length();
        int n = 0;
        while (i > 0) {
            final int min = Math.min(this._outputMaxContiguous, i);
            if (this._outputTail + min > this._outputEnd) {
                this._flushBuffer();
            }
            this._writeStringSegment(s, n, min);
            n += min;
            i -= min;
        }
        if (b) {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = this._quoteChar;
        }
    }
    
    private final void _writeStringSegments(final char[] array, int n, int i) throws IOException {
        do {
            final int min = Math.min(this._outputMaxContiguous, i);
            if (this._outputTail + min > this._outputEnd) {
                this._flushBuffer();
            }
            this._writeStringSegment(array, n, min);
            n += min;
            i -= min;
        } while (i > 0);
    }
    
    private final void _writeStringSegments(final String s, int n, int i) throws IOException {
        do {
            final int min = Math.min(this._outputMaxContiguous, i);
            if (this._outputTail + min > this._outputEnd) {
                this._flushBuffer();
            }
            this._writeStringSegment(s, n, min);
            n += min;
            i -= min;
        } while (i > 0);
    }
    
    private final void _writeStringSegment(final char[] array, int i, int n) throws IOException {
        n += i;
        int outputTail = this._outputTail;
        final byte[] outputBuffer = this._outputBuffer;
        final int[] outputEscapes = this._outputEscapes;
        while (i < n) {
            final char c = array[i];
            if (c > '\u007f') {
                break;
            }
            if (outputEscapes[c] != 0) {
                break;
            }
            outputBuffer[outputTail++] = (byte)c;
            ++i;
        }
        this._outputTail = outputTail;
        if (i < n) {
            if (this._characterEscapes != null) {
                this._writeCustomStringSegment2(array, i, n);
            }
            else if (this._maximumNonEscapedChar == 0) {
                this._writeStringSegment2(array, i, n);
            }
            else {
                this._writeStringSegmentASCII2(array, i, n);
            }
        }
    }
    
    private final void _writeStringSegment(final String s, int i, int n) throws IOException {
        n += i;
        int outputTail = this._outputTail;
        final byte[] outputBuffer = this._outputBuffer;
        final int[] outputEscapes = this._outputEscapes;
        while (i < n) {
            final char char1 = s.charAt(i);
            if (char1 > '\u007f') {
                break;
            }
            if (outputEscapes[char1] != 0) {
                break;
            }
            outputBuffer[outputTail++] = (byte)char1;
            ++i;
        }
        this._outputTail = outputTail;
        if (i < n) {
            if (this._characterEscapes != null) {
                this._writeCustomStringSegment2(s, i, n);
            }
            else if (this._maximumNonEscapedChar == 0) {
                this._writeStringSegment2(s, i, n);
            }
            else {
                this._writeStringSegmentASCII2(s, i, n);
            }
        }
    }
    
    private final void _writeStringSegment2(final char[] array, int i, final int n) throws IOException {
        if (this._outputTail + 6 * (n - i) > this._outputEnd) {
            this._flushBuffer();
        }
        int outputTail = this._outputTail;
        final byte[] outputBuffer = this._outputBuffer;
        final int[] outputEscapes = this._outputEscapes;
        while (i < n) {
            final char c = array[i++];
            if (c <= '\u007f') {
                if (outputEscapes[c] == 0) {
                    outputBuffer[outputTail++] = (byte)c;
                }
                else {
                    final int n2 = outputEscapes[c];
                    if (n2 > 0) {
                        outputBuffer[outputTail++] = 92;
                        outputBuffer[outputTail++] = (byte)n2;
                    }
                    else {
                        outputTail = this._writeGenericEscape(c, outputTail);
                    }
                }
            }
            else if (c <= '\u07ff') {
                outputBuffer[outputTail++] = (byte)(0xC0 | c >> 6);
                outputBuffer[outputTail++] = (byte)(0x80 | (c & '?'));
            }
            else {
                outputTail = this._outputMultiByteChar(c, outputTail);
            }
        }
        this._outputTail = outputTail;
    }
    
    private final void _writeStringSegment2(final String s, int i, final int n) throws IOException {
        if (this._outputTail + 6 * (n - i) > this._outputEnd) {
            this._flushBuffer();
        }
        int outputTail = this._outputTail;
        final byte[] outputBuffer = this._outputBuffer;
        final int[] outputEscapes = this._outputEscapes;
        while (i < n) {
            final char char1 = s.charAt(i++);
            if (char1 <= '\u007f') {
                if (outputEscapes[char1] == 0) {
                    outputBuffer[outputTail++] = (byte)char1;
                }
                else {
                    final int n2 = outputEscapes[char1];
                    if (n2 > 0) {
                        outputBuffer[outputTail++] = 92;
                        outputBuffer[outputTail++] = (byte)n2;
                    }
                    else {
                        outputTail = this._writeGenericEscape(char1, outputTail);
                    }
                }
            }
            else if (char1 <= '\u07ff') {
                outputBuffer[outputTail++] = (byte)(0xC0 | char1 >> 6);
                outputBuffer[outputTail++] = (byte)(0x80 | (char1 & '?'));
            }
            else {
                outputTail = this._outputMultiByteChar(char1, outputTail);
            }
        }
        this._outputTail = outputTail;
    }
    
    private final void _writeStringSegmentASCII2(final char[] array, int i, final int n) throws IOException {
        if (this._outputTail + 6 * (n - i) > this._outputEnd) {
            this._flushBuffer();
        }
        int outputTail = this._outputTail;
        final byte[] outputBuffer = this._outputBuffer;
        final int[] outputEscapes = this._outputEscapes;
        final int maximumNonEscapedChar = this._maximumNonEscapedChar;
        while (i < n) {
            final char c = array[i++];
            if (c <= '\u007f') {
                if (outputEscapes[c] == 0) {
                    outputBuffer[outputTail++] = (byte)c;
                }
                else {
                    final int n2 = outputEscapes[c];
                    if (n2 > 0) {
                        outputBuffer[outputTail++] = 92;
                        outputBuffer[outputTail++] = (byte)n2;
                    }
                    else {
                        outputTail = this._writeGenericEscape(c, outputTail);
                    }
                }
            }
            else if (c > maximumNonEscapedChar) {
                outputTail = this._writeGenericEscape(c, outputTail);
            }
            else if (c <= '\u07ff') {
                outputBuffer[outputTail++] = (byte)(0xC0 | c >> 6);
                outputBuffer[outputTail++] = (byte)(0x80 | (c & '?'));
            }
            else {
                outputTail = this._outputMultiByteChar(c, outputTail);
            }
        }
        this._outputTail = outputTail;
    }
    
    private final void _writeStringSegmentASCII2(final String s, int i, final int n) throws IOException {
        if (this._outputTail + 6 * (n - i) > this._outputEnd) {
            this._flushBuffer();
        }
        int outputTail = this._outputTail;
        final byte[] outputBuffer = this._outputBuffer;
        final int[] outputEscapes = this._outputEscapes;
        final int maximumNonEscapedChar = this._maximumNonEscapedChar;
        while (i < n) {
            final char char1 = s.charAt(i++);
            if (char1 <= '\u007f') {
                if (outputEscapes[char1] == 0) {
                    outputBuffer[outputTail++] = (byte)char1;
                }
                else {
                    final int n2 = outputEscapes[char1];
                    if (n2 > 0) {
                        outputBuffer[outputTail++] = 92;
                        outputBuffer[outputTail++] = (byte)n2;
                    }
                    else {
                        outputTail = this._writeGenericEscape(char1, outputTail);
                    }
                }
            }
            else if (char1 > maximumNonEscapedChar) {
                outputTail = this._writeGenericEscape(char1, outputTail);
            }
            else if (char1 <= '\u07ff') {
                outputBuffer[outputTail++] = (byte)(0xC0 | char1 >> 6);
                outputBuffer[outputTail++] = (byte)(0x80 | (char1 & '?'));
            }
            else {
                outputTail = this._outputMultiByteChar(char1, outputTail);
            }
        }
        this._outputTail = outputTail;
    }
    
    private final void _writeCustomStringSegment2(final char[] array, int i, final int n) throws IOException {
        if (this._outputTail + 6 * (n - i) > this._outputEnd) {
            this._flushBuffer();
        }
        int outputTail = this._outputTail;
        final byte[] outputBuffer = this._outputBuffer;
        final int[] outputEscapes = this._outputEscapes;
        final int n2 = (this._maximumNonEscapedChar <= 0) ? 65535 : this._maximumNonEscapedChar;
        final CharacterEscapes characterEscapes = this._characterEscapes;
        while (i < n) {
            final char c = array[i++];
            if (c <= '\u007f') {
                if (outputEscapes[c] == 0) {
                    outputBuffer[outputTail++] = (byte)c;
                }
                else {
                    final int n3 = outputEscapes[c];
                    if (n3 > 0) {
                        outputBuffer[outputTail++] = 92;
                        outputBuffer[outputTail++] = (byte)n3;
                    }
                    else if (n3 == -2) {
                        final SerializableString escapeSequence = characterEscapes.getEscapeSequence(c);
                        if (escapeSequence == null) {
                            this._reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(c) + ", although was supposed to have one");
                        }
                        outputTail = this._writeCustomEscape(outputBuffer, outputTail, escapeSequence, n - i);
                    }
                    else {
                        outputTail = this._writeGenericEscape(c, outputTail);
                    }
                }
            }
            else if (c > n2) {
                outputTail = this._writeGenericEscape(c, outputTail);
            }
            else {
                final SerializableString escapeSequence2 = characterEscapes.getEscapeSequence(c);
                if (escapeSequence2 != null) {
                    outputTail = this._writeCustomEscape(outputBuffer, outputTail, escapeSequence2, n - i);
                }
                else if (c <= '\u07ff') {
                    outputBuffer[outputTail++] = (byte)(0xC0 | c >> 6);
                    outputBuffer[outputTail++] = (byte)(0x80 | (c & '?'));
                }
                else {
                    outputTail = this._outputMultiByteChar(c, outputTail);
                }
            }
        }
        this._outputTail = outputTail;
    }
    
    private final void _writeCustomStringSegment2(final String s, int i, final int n) throws IOException {
        if (this._outputTail + 6 * (n - i) > this._outputEnd) {
            this._flushBuffer();
        }
        int outputTail = this._outputTail;
        final byte[] outputBuffer = this._outputBuffer;
        final int[] outputEscapes = this._outputEscapes;
        final int n2 = (this._maximumNonEscapedChar <= 0) ? 65535 : this._maximumNonEscapedChar;
        final CharacterEscapes characterEscapes = this._characterEscapes;
        while (i < n) {
            final char char1 = s.charAt(i++);
            if (char1 <= '\u007f') {
                if (outputEscapes[char1] == 0) {
                    outputBuffer[outputTail++] = (byte)char1;
                }
                else {
                    final int n3 = outputEscapes[char1];
                    if (n3 > 0) {
                        outputBuffer[outputTail++] = 92;
                        outputBuffer[outputTail++] = (byte)n3;
                    }
                    else if (n3 == -2) {
                        final SerializableString escapeSequence = characterEscapes.getEscapeSequence(char1);
                        if (escapeSequence == null) {
                            this._reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(char1) + ", although was supposed to have one");
                        }
                        outputTail = this._writeCustomEscape(outputBuffer, outputTail, escapeSequence, n - i);
                    }
                    else {
                        outputTail = this._writeGenericEscape(char1, outputTail);
                    }
                }
            }
            else if (char1 > n2) {
                outputTail = this._writeGenericEscape(char1, outputTail);
            }
            else {
                final SerializableString escapeSequence2 = characterEscapes.getEscapeSequence(char1);
                if (escapeSequence2 != null) {
                    outputTail = this._writeCustomEscape(outputBuffer, outputTail, escapeSequence2, n - i);
                }
                else if (char1 <= '\u07ff') {
                    outputBuffer[outputTail++] = (byte)(0xC0 | char1 >> 6);
                    outputBuffer[outputTail++] = (byte)(0x80 | (char1 & '?'));
                }
                else {
                    outputTail = this._outputMultiByteChar(char1, outputTail);
                }
            }
        }
        this._outputTail = outputTail;
    }
    
    private final int _writeCustomEscape(final byte[] array, final int n, final SerializableString serializableString, final int n2) throws IOException, JsonGenerationException {
        final byte[] unquotedUTF8 = serializableString.asUnquotedUTF8();
        final int length = unquotedUTF8.length;
        if (length > 6) {
            return this._handleLongCustomEscape(array, n, this._outputEnd, unquotedUTF8, n2);
        }
        System.arraycopy(unquotedUTF8, 0, array, n, length);
        return n + length;
    }
    
    private final int _handleLongCustomEscape(final byte[] array, int outputTail, final int n, final byte[] array2, final int n2) throws IOException, JsonGenerationException {
        final int length = array2.length;
        if (outputTail + length > n) {
            this._outputTail = outputTail;
            this._flushBuffer();
            outputTail = this._outputTail;
            if (length > array.length) {
                this._outputStream.write(array2, 0, length);
                return outputTail;
            }
            System.arraycopy(array2, 0, array, outputTail, length);
            outputTail += length;
        }
        if (outputTail + 6 * n2 > n) {
            this._flushBuffer();
            return this._outputTail;
        }
        return outputTail;
    }
    
    private final void _writeUTF8Segments(final byte[] array, int n, int i) throws IOException, JsonGenerationException {
        do {
            final int min = Math.min(this._outputMaxContiguous, i);
            this._writeUTF8Segment(array, n, min);
            n += min;
            i -= min;
        } while (i > 0);
    }
    
    private final void _writeUTF8Segment(final byte[] array, final int n, final int n2) throws IOException, JsonGenerationException {
        final int[] outputEscapes = this._outputEscapes;
        int i = n;
        while (i < n + n2) {
            final byte b = array[i++];
            if (b >= 0 && outputEscapes[b] != 0) {
                this._writeUTF8Segment2(array, n, n2);
                return;
            }
        }
        if (this._outputTail + n2 > this._outputEnd) {
            this._flushBuffer();
        }
        System.arraycopy(array, n, this._outputBuffer, this._outputTail, n2);
        this._outputTail += n2;
    }
    
    private final void _writeUTF8Segment2(final byte[] array, int i, int n) throws IOException, JsonGenerationException {
        int outputTail = this._outputTail;
        if (outputTail + n * 6 > this._outputEnd) {
            this._flushBuffer();
            outputTail = this._outputTail;
        }
        final byte[] outputBuffer = this._outputBuffer;
        final int[] outputEscapes = this._outputEscapes;
        n += i;
        while (i < n) {
            final int n3;
            final int n2 = n3 = array[i++];
            if (n3 < 0 || outputEscapes[n3] == 0) {
                outputBuffer[outputTail++] = (byte)n2;
            }
            else {
                final int n4 = outputEscapes[n3];
                if (n4 > 0) {
                    outputBuffer[outputTail++] = 92;
                    outputBuffer[outputTail++] = (byte)n4;
                }
                else {
                    outputTail = this._writeGenericEscape(n3, outputTail);
                }
            }
        }
        this._outputTail = outputTail;
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
                this._outputBuffer[this._outputTail++] = 92;
                this._outputBuffer[this._outputTail++] = 110;
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
                this._outputBuffer[this._outputTail++] = 92;
                this._outputBuffer[this._outputTail++] = 110;
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
                this._outputBuffer[this._outputTail++] = 92;
                this._outputBuffer[this._outputTail++] = 110;
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
    
    private final int _readMore(final InputStream inputStream, final byte[] array, int i, int n, int min) throws IOException {
        int n2;
        for (n2 = 0; i < n; array[n2++] = array[i++]) {}
        i = 0;
        n = n2;
        min = Math.min(min, array.length);
        return n;
    }
    
    private final int _outputRawMultiByteChar(final int n, final char[] array, final int n2, final int n3) throws IOException {
        if (n >= 55296 && n <= 57343) {
            if (n2 >= n3 || array == null) {
                this._reportError(String.format("Split surrogate on writeRaw() input (last character): first character 0x%4x", n));
            }
            this._outputSurrogates(n, array[n2]);
            return n2 + 1;
        }
        final byte[] outputBuffer = this._outputBuffer;
        outputBuffer[this._outputTail++] = (byte)(0xE0 | n >> 12);
        outputBuffer[this._outputTail++] = (byte)(0x80 | (n >> 6 & 0x3F));
        outputBuffer[this._outputTail++] = (byte)(0x80 | (n & 0x3F));
        return n2;
    }
    
    protected final void _outputSurrogates(final int n, final int n2) throws IOException {
        final int decodeSurrogate = this._decodeSurrogate(n, n2);
        if (this._outputTail + 4 > this._outputEnd) {
            this._flushBuffer();
        }
        final byte[] outputBuffer = this._outputBuffer;
        outputBuffer[this._outputTail++] = (byte)(0xF0 | decodeSurrogate >> 18);
        outputBuffer[this._outputTail++] = (byte)(0x80 | (decodeSurrogate >> 12 & 0x3F));
        outputBuffer[this._outputTail++] = (byte)(0x80 | (decodeSurrogate >> 6 & 0x3F));
        outputBuffer[this._outputTail++] = (byte)(0x80 | (decodeSurrogate & 0x3F));
    }
    
    private final int _outputMultiByteChar(final int n, int n2) throws IOException {
        final byte[] outputBuffer = this._outputBuffer;
        if (n >= 55296 && n <= 57343) {
            outputBuffer[n2++] = 92;
            outputBuffer[n2++] = 117;
            outputBuffer[n2++] = UTF8JsonGenerator.HEX_CHARS[n >> 12 & 0xF];
            outputBuffer[n2++] = UTF8JsonGenerator.HEX_CHARS[n >> 8 & 0xF];
            outputBuffer[n2++] = UTF8JsonGenerator.HEX_CHARS[n >> 4 & 0xF];
            outputBuffer[n2++] = UTF8JsonGenerator.HEX_CHARS[n & 0xF];
        }
        else {
            outputBuffer[n2++] = (byte)(0xE0 | n >> 12);
            outputBuffer[n2++] = (byte)(0x80 | (n >> 6 & 0x3F));
            outputBuffer[n2++] = (byte)(0x80 | (n & 0x3F));
        }
        return n2;
    }
    
    private final void _writeNull() throws IOException {
        if (this._outputTail + 4 >= this._outputEnd) {
            this._flushBuffer();
        }
        System.arraycopy(UTF8JsonGenerator.NULL_BYTES, 0, this._outputBuffer, this._outputTail, 4);
        this._outputTail += 4;
    }
    
    private int _writeGenericEscape(int n, int n2) throws IOException {
        final byte[] outputBuffer = this._outputBuffer;
        outputBuffer[n2++] = 92;
        outputBuffer[n2++] = 117;
        if (n > 255) {
            final int n3 = n >> 8 & 0xFF;
            outputBuffer[n2++] = UTF8JsonGenerator.HEX_CHARS[n3 >> 4];
            outputBuffer[n2++] = UTF8JsonGenerator.HEX_CHARS[n3 & 0xF];
            n &= 0xFF;
        }
        else {
            outputBuffer[n2++] = 48;
            outputBuffer[n2++] = 48;
        }
        outputBuffer[n2++] = UTF8JsonGenerator.HEX_CHARS[n >> 4];
        outputBuffer[n2++] = UTF8JsonGenerator.HEX_CHARS[n & 0xF];
        return n2;
    }
    
    protected final void _flushBuffer() throws IOException {
        final int outputTail = this._outputTail;
        if (outputTail > 0) {
            this._outputTail = 0;
            this._outputStream.write(this._outputBuffer, 0, outputTail);
        }
    }
    
    static {
        HEX_CHARS = CharTypes.copyHexBytes();
        NULL_BYTES = new byte[] { 110, 117, 108, 108 };
        TRUE_BYTES = new byte[] { 116, 114, 117, 101 };
        FALSE_BYTES = new byte[] { 102, 97, 108, 115, 101 };
    }
}
