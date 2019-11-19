package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.JsonStreamContext;
import java.util.Arrays;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import java.io.IOException;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.JsonParser;
import java.math.BigDecimal;
import java.math.BigInteger;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.io.IOContext;

public abstract class ParserBase extends ParserMinimalBase
{
    protected final IOContext _ioContext;
    protected boolean _closed;
    protected int _inputPtr;
    protected int _inputEnd;
    protected long _currInputProcessed;
    protected int _currInputRow;
    protected int _currInputRowStart;
    protected long _tokenInputTotal;
    protected int _tokenInputRow;
    protected int _tokenInputCol;
    protected JsonReadContext _parsingContext;
    protected JsonToken _nextToken;
    protected final TextBuffer _textBuffer;
    protected char[] _nameCopyBuffer;
    protected boolean _nameCopied;
    protected ByteArrayBuilder _byteArrayBuilder;
    protected byte[] _binaryValue;
    protected int _numTypesValid;
    protected int _numberInt;
    protected long _numberLong;
    protected double _numberDouble;
    protected BigInteger _numberBigInt;
    protected BigDecimal _numberBigDecimal;
    protected boolean _numberNegative;
    protected int _intLength;
    protected int _fractLength;
    protected int _expLength;
    
    protected ParserBase(final IOContext ioContext, final int n) {
        super(n);
        this._currInputRow = 1;
        this._tokenInputRow = 1;
        this._numTypesValid = 0;
        this._ioContext = ioContext;
        this._textBuffer = ioContext.constructTextBuffer();
        this._parsingContext = JsonReadContext.createRootContext(Feature.STRICT_DUPLICATE_DETECTION.enabledIn(n) ? DupDetector.rootDetector(this) : null);
    }
    
    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }
    
    @Override
    public Object getCurrentValue() {
        return this._parsingContext.getCurrentValue();
    }
    
    @Override
    public void setCurrentValue(final Object currentValue) {
        this._parsingContext.setCurrentValue(currentValue);
    }
    
    @Override
    public JsonParser enable(final Feature feature) {
        this._features |= feature.getMask();
        if (feature == Feature.STRICT_DUPLICATE_DETECTION && this._parsingContext.getDupDetector() == null) {
            this._parsingContext = this._parsingContext.withDupDetector(DupDetector.rootDetector(this));
        }
        return this;
    }
    
    @Override
    public JsonParser disable(final Feature feature) {
        this._features &= ~feature.getMask();
        if (feature == Feature.STRICT_DUPLICATE_DETECTION) {
            this._parsingContext = this._parsingContext.withDupDetector(null);
        }
        return this;
    }
    
    @Deprecated
    @Override
    public JsonParser setFeatureMask(final int features) {
        final int n = this._features ^ features;
        if (n != 0) {
            this._checkStdFeatureChanges(this._features = features, n);
        }
        return this;
    }
    
    @Override
    public JsonParser overrideStdFeatures(final int n, final int n2) {
        final int features = this._features;
        final int features2 = (features & ~n2) | (n & n2);
        final int n3 = features ^ features2;
        if (n3 != 0) {
            this._checkStdFeatureChanges(this._features = features2, n3);
        }
        return this;
    }
    
    protected void _checkStdFeatureChanges(final int n, final int n2) {
        final int mask = Feature.STRICT_DUPLICATE_DETECTION.getMask();
        if ((n2 & mask) != 0x0 && (n & mask) != 0x0) {
            if (this._parsingContext.getDupDetector() == null) {
                this._parsingContext = this._parsingContext.withDupDetector(DupDetector.rootDetector(this));
            }
            else {
                this._parsingContext = this._parsingContext.withDupDetector(null);
            }
        }
    }
    
    @Override
    public String getCurrentName() throws IOException {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            final JsonReadContext parent = this._parsingContext.getParent();
            if (parent != null) {
                return parent.getCurrentName();
            }
        }
        return this._parsingContext.getCurrentName();
    }
    
    @Override
    public void overrideCurrentName(final String currentName) {
        JsonReadContext jsonReadContext = this._parsingContext;
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            jsonReadContext = jsonReadContext.getParent();
        }
        try {
            jsonReadContext.setCurrentName(currentName);
        }
        catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    @Override
    public void close() throws IOException {
        if (!this._closed) {
            this._inputPtr = Math.max(this._inputPtr, this._inputEnd);
            this._closed = true;
            try {
                this._closeInput();
            }
            finally {
                this._releaseBuffers();
            }
        }
    }
    
    @Override
    public boolean isClosed() {
        return this._closed;
    }
    
    @Override
    public JsonReadContext getParsingContext() {
        return this._parsingContext;
    }
    
    @Override
    public JsonLocation getTokenLocation() {
        return new JsonLocation(this._getSourceReference(), -1L, this.getTokenCharacterOffset(), this.getTokenLineNr(), this.getTokenColumnNr());
    }
    
    @Override
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._getSourceReference(), -1L, this._currInputProcessed + this._inputPtr, this._currInputRow, this._inputPtr - this._currInputRowStart + 1);
    }
    
    @Override
    public boolean hasTextCharacters() {
        return this._currToken == JsonToken.VALUE_STRING || (this._currToken == JsonToken.FIELD_NAME && this._nameCopied);
    }
    
    @Override
    public byte[] getBinaryValue(final Base64Variant base64Variant) throws IOException {
        if (this._binaryValue == null) {
            if (this._currToken != JsonToken.VALUE_STRING) {
                this._reportError("Current token (" + this._currToken + ") not VALUE_STRING, can not access as binary");
            }
            final ByteArrayBuilder getByteArrayBuilder = this._getByteArrayBuilder();
            this._decodeBase64(this.getText(), getByteArrayBuilder, base64Variant);
            this._binaryValue = getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }
    
    public long getTokenCharacterOffset() {
        return this._tokenInputTotal;
    }
    
    public int getTokenLineNr() {
        return this._tokenInputRow;
    }
    
    public int getTokenColumnNr() {
        final int tokenInputCol = this._tokenInputCol;
        return (tokenInputCol < 0) ? tokenInputCol : (tokenInputCol + 1);
    }
    
    protected abstract void _closeInput() throws IOException;
    
    protected void _releaseBuffers() throws IOException {
        this._textBuffer.releaseBuffers();
        final char[] nameCopyBuffer = this._nameCopyBuffer;
        if (nameCopyBuffer != null) {
            this._nameCopyBuffer = null;
            this._ioContext.releaseNameCopyBuffer(nameCopyBuffer);
        }
    }
    
    @Override
    protected void _handleEOF() throws JsonParseException {
        if (!this._parsingContext.inRoot()) {
            this._reportInvalidEOF(String.format(": expected close marker for %s (start marker at %s)", this._parsingContext.inArray() ? "Array" : "Object", this._parsingContext.getStartLocation(this._getSourceReference())), null);
        }
    }
    
    protected final int _eofAsNextChar() throws JsonParseException {
        this._handleEOF();
        return -1;
    }
    
    public ByteArrayBuilder _getByteArrayBuilder() {
        if (this._byteArrayBuilder == null) {
            this._byteArrayBuilder = new ByteArrayBuilder();
        }
        else {
            this._byteArrayBuilder.reset();
        }
        return this._byteArrayBuilder;
    }
    
    protected final JsonToken reset(final boolean b, final int n, final int n2, final int n3) {
        if (n2 < 1 && n3 < 1) {
            return this.resetInt(b, n);
        }
        return this.resetFloat(b, n, n2, n3);
    }
    
    protected final JsonToken resetInt(final boolean numberNegative, final int intLength) {
        this._numberNegative = numberNegative;
        this._intLength = intLength;
        this._fractLength = 0;
        this._expLength = 0;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_INT;
    }
    
    protected final JsonToken resetFloat(final boolean numberNegative, final int intLength, final int fractLength, final int expLength) {
        this._numberNegative = numberNegative;
        this._intLength = intLength;
        this._fractLength = fractLength;
        this._expLength = expLength;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }
    
    protected final JsonToken resetAsNaN(final String s, final double numberDouble) {
        this._textBuffer.resetWithString(s);
        this._numberDouble = numberDouble;
        this._numTypesValid = 8;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }
    
    @Override
    public boolean isNaN() {
        if (this._currToken == JsonToken.VALUE_NUMBER_FLOAT && (this._numTypesValid & 0x8) != 0x0) {
            final double numberDouble = this._numberDouble;
            return Double.isNaN(numberDouble) || Double.isInfinite(numberDouble);
        }
        return false;
    }
    
    @Override
    public Number getNumberValue() throws IOException {
        if (this._numTypesValid == 0) {
            this._parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            if ((this._numTypesValid & 0x1) != 0x0) {
                return this._numberInt;
            }
            if ((this._numTypesValid & 0x2) != 0x0) {
                return this._numberLong;
            }
            if ((this._numTypesValid & 0x4) != 0x0) {
                return this._numberBigInt;
            }
            return this._numberBigDecimal;
        }
        else {
            if ((this._numTypesValid & 0x10) != 0x0) {
                return this._numberBigDecimal;
            }
            if ((this._numTypesValid & 0x8) == 0x0) {
                this._throwInternal();
            }
            return this._numberDouble;
        }
    }
    
    @Override
    public NumberType getNumberType() throws IOException {
        if (this._numTypesValid == 0) {
            this._parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            if ((this._numTypesValid & 0x1) != 0x0) {
                return NumberType.INT;
            }
            if ((this._numTypesValid & 0x2) != 0x0) {
                return NumberType.LONG;
            }
            return NumberType.BIG_INTEGER;
        }
        else {
            if ((this._numTypesValid & 0x10) != 0x0) {
                return NumberType.BIG_DECIMAL;
            }
            return NumberType.DOUBLE;
        }
    }
    
    @Override
    public int getIntValue() throws IOException {
        if ((this._numTypesValid & 0x1) == 0x0) {
            if (this._numTypesValid == 0) {
                return this._parseIntValue();
            }
            if ((this._numTypesValid & 0x1) == 0x0) {
                this.convertNumberToInt();
            }
        }
        return this._numberInt;
    }
    
    @Override
    public long getLongValue() throws IOException {
        if ((this._numTypesValid & 0x2) == 0x0) {
            if (this._numTypesValid == 0) {
                this._parseNumericValue(2);
            }
            if ((this._numTypesValid & 0x2) == 0x0) {
                this.convertNumberToLong();
            }
        }
        return this._numberLong;
    }
    
    @Override
    public BigInteger getBigIntegerValue() throws IOException {
        if ((this._numTypesValid & 0x4) == 0x0) {
            if (this._numTypesValid == 0) {
                this._parseNumericValue(4);
            }
            if ((this._numTypesValid & 0x4) == 0x0) {
                this.convertNumberToBigInteger();
            }
        }
        return this._numberBigInt;
    }
    
    @Override
    public float getFloatValue() throws IOException {
        return (float)this.getDoubleValue();
    }
    
    @Override
    public double getDoubleValue() throws IOException {
        if ((this._numTypesValid & 0x8) == 0x0) {
            if (this._numTypesValid == 0) {
                this._parseNumericValue(8);
            }
            if ((this._numTypesValid & 0x8) == 0x0) {
                this.convertNumberToDouble();
            }
        }
        return this._numberDouble;
    }
    
    @Override
    public BigDecimal getDecimalValue() throws IOException {
        if ((this._numTypesValid & 0x10) == 0x0) {
            if (this._numTypesValid == 0) {
                this._parseNumericValue(16);
            }
            if ((this._numTypesValid & 0x10) == 0x0) {
                this.convertNumberToBigDecimal();
            }
        }
        return this._numberBigDecimal;
    }
    
    protected void _parseNumericValue(final int n) throws IOException {
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            final int intLength = this._intLength;
            if (intLength <= 9) {
                this._numberInt = this._textBuffer.contentsAsInt(this._numberNegative);
                this._numTypesValid = 1;
                return;
            }
            if (intLength <= 18) {
                final long contentsAsLong = this._textBuffer.contentsAsLong(this._numberNegative);
                if (intLength == 10) {
                    if (this._numberNegative) {
                        if (contentsAsLong >= -2147483648L) {
                            this._numberInt = (int)contentsAsLong;
                            this._numTypesValid = 1;
                            return;
                        }
                    }
                    else if (contentsAsLong <= 0L) {
                        this._numberInt = (int)contentsAsLong;
                        this._numTypesValid = 1;
                        return;
                    }
                }
                this._numberLong = contentsAsLong;
                this._numTypesValid = 2;
                return;
            }
            this._parseSlowInt(n);
        }
        else {
            if (this._currToken == JsonToken.VALUE_NUMBER_FLOAT) {
                this._parseSlowFloat(n);
                return;
            }
            this._reportError("Current token (%s) not numeric, can not use numeric value accessors", this._currToken);
        }
    }
    
    protected int _parseIntValue() throws IOException {
        if (this._currToken == JsonToken.VALUE_NUMBER_INT && this._intLength <= 9) {
            final int contentsAsInt = this._textBuffer.contentsAsInt(this._numberNegative);
            this._numberInt = contentsAsInt;
            this._numTypesValid = 1;
            return contentsAsInt;
        }
        this._parseNumericValue(1);
        if ((this._numTypesValid & 0x1) == 0x0) {
            this.convertNumberToInt();
        }
        return this._numberInt;
    }
    
    private void _parseSlowFloat(final int n) throws IOException {
        try {
            if (n == 16) {
                this._numberBigDecimal = this._textBuffer.contentsAsDecimal();
                this._numTypesValid = 16;
            }
            else {
                this._numberDouble = this._textBuffer.contentsAsDouble();
                this._numTypesValid = 8;
            }
        }
        catch (NumberFormatException ex) {
            this._wrapError("Malformed numeric value '" + this._textBuffer.contentsAsString() + "'", ex);
        }
    }
    
    private void _parseSlowInt(final int n) throws IOException {
        final String contentsAsString = this._textBuffer.contentsAsString();
        try {
            final int intLength = this._intLength;
            final char[] textBuffer = this._textBuffer.getTextBuffer();
            int textOffset = this._textBuffer.getTextOffset();
            if (this._numberNegative) {
                ++textOffset;
            }
            if (NumberInput.inLongRange(textBuffer, textOffset, intLength, this._numberNegative)) {
                this._numberLong = Long.parseLong(contentsAsString);
                this._numTypesValid = 2;
            }
            else {
                this._numberBigInt = new BigInteger(contentsAsString);
                this._numTypesValid = 4;
            }
        }
        catch (NumberFormatException ex) {
            this._wrapError("Malformed numeric value '" + contentsAsString + "'", ex);
        }
    }
    
    protected void convertNumberToInt() throws IOException {
        if ((this._numTypesValid & 0x2) != 0x0) {
            final int numberInt = (int)this._numberLong;
            if (numberInt != this._numberLong) {
                this._reportError("Numeric value (" + this.getText() + ") out of range of int");
            }
            this._numberInt = numberInt;
        }
        else if ((this._numTypesValid & 0x4) != 0x0) {
            if (ParserBase.BI_MIN_INT.compareTo(this._numberBigInt) > 0 || ParserBase.BI_MAX_INT.compareTo(this._numberBigInt) < 0) {
                this.reportOverflowInt();
            }
            this._numberInt = this._numberBigInt.intValue();
        }
        else if ((this._numTypesValid & 0x8) != 0x0) {
            if (this._numberDouble < -2.147483648E9 || this._numberDouble > 2.147483647E9) {
                this.reportOverflowInt();
            }
            this._numberInt = (int)this._numberDouble;
        }
        else if ((this._numTypesValid & 0x10) != 0x0) {
            if (ParserBase.BD_MIN_INT.compareTo(this._numberBigDecimal) > 0 || ParserBase.BD_MAX_INT.compareTo(this._numberBigDecimal) < 0) {
                this.reportOverflowInt();
            }
            this._numberInt = this._numberBigDecimal.intValue();
        }
        else {
            this._throwInternal();
        }
        this._numTypesValid |= 0x1;
    }
    
    protected void convertNumberToLong() throws IOException {
        if ((this._numTypesValid & 0x1) != 0x0) {
            this._numberLong = this._numberInt;
        }
        else if ((this._numTypesValid & 0x4) != 0x0) {
            if (ParserBase.BI_MIN_LONG.compareTo(this._numberBigInt) > 0 || ParserBase.BI_MAX_LONG.compareTo(this._numberBigInt) < 0) {
                this.reportOverflowLong();
            }
            this._numberLong = this._numberBigInt.longValue();
        }
        else if ((this._numTypesValid & 0x8) != 0x0) {
            if (this._numberDouble < -9.223372036854776E18 || this._numberDouble > 9.223372036854776E18) {
                this.reportOverflowLong();
            }
            this._numberLong = (long)this._numberDouble;
        }
        else if ((this._numTypesValid & 0x10) != 0x0) {
            if (ParserBase.BD_MIN_LONG.compareTo(this._numberBigDecimal) > 0 || ParserBase.BD_MAX_LONG.compareTo(this._numberBigDecimal) < 0) {
                this.reportOverflowLong();
            }
            this._numberLong = this._numberBigDecimal.longValue();
        }
        else {
            this._throwInternal();
        }
        this._numTypesValid |= 0x2;
    }
    
    protected void convertNumberToBigInteger() throws IOException {
        if ((this._numTypesValid & 0x10) != 0x0) {
            this._numberBigInt = this._numberBigDecimal.toBigInteger();
        }
        else if ((this._numTypesValid & 0x2) != 0x0) {
            this._numberBigInt = BigInteger.valueOf(this._numberLong);
        }
        else if ((this._numTypesValid & 0x1) != 0x0) {
            this._numberBigInt = BigInteger.valueOf(this._numberInt);
        }
        else if ((this._numTypesValid & 0x8) != 0x0) {
            this._numberBigInt = BigDecimal.valueOf(this._numberDouble).toBigInteger();
        }
        else {
            this._throwInternal();
        }
        this._numTypesValid |= 0x4;
    }
    
    protected void convertNumberToDouble() throws IOException {
        if ((this._numTypesValid & 0x10) != 0x0) {
            this._numberDouble = this._numberBigDecimal.doubleValue();
        }
        else if ((this._numTypesValid & 0x4) != 0x0) {
            this._numberDouble = this._numberBigInt.doubleValue();
        }
        else if ((this._numTypesValid & 0x2) != 0x0) {
            this._numberDouble = (double)this._numberLong;
        }
        else if ((this._numTypesValid & 0x1) != 0x0) {
            this._numberDouble = this._numberInt;
        }
        else {
            this._throwInternal();
        }
        this._numTypesValid |= 0x8;
    }
    
    protected void convertNumberToBigDecimal() throws IOException {
        if ((this._numTypesValid & 0x8) != 0x0) {
            this._numberBigDecimal = NumberInput.parseBigDecimal(this.getText());
        }
        else if ((this._numTypesValid & 0x4) != 0x0) {
            this._numberBigDecimal = new BigDecimal(this._numberBigInt);
        }
        else if ((this._numTypesValid & 0x2) != 0x0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberLong);
        }
        else if ((this._numTypesValid & 0x1) != 0x0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberInt);
        }
        else {
            this._throwInternal();
        }
        this._numTypesValid |= 0x10;
    }
    
    protected void _reportMismatchedEndMarker(final int n, final char c) throws JsonParseException {
        final JsonReadContext parsingContext = this.getParsingContext();
        this._reportError(String.format("Unexpected close marker '%s': expected '%c' (for %s starting at %s)", (char)n, c, parsingContext.typeDesc(), parsingContext.getStartLocation(this._getSourceReference())));
    }
    
    protected char _decodeEscaped() throws IOException {
        throw new UnsupportedOperationException();
    }
    
    protected final int _decodeBase64Escape(final Base64Variant base64Variant, final int n, final int n2) throws IOException {
        if (n != 92) {
            throw this.reportInvalidBase64Char(base64Variant, n, n2);
        }
        final char decodeEscaped = this._decodeEscaped();
        if (decodeEscaped <= ' ') {
            return -1;
        }
        final int decodeBase64Char = base64Variant.decodeBase64Char((int)decodeEscaped);
        if (decodeBase64Char < 0) {
            throw this.reportInvalidBase64Char(base64Variant, decodeEscaped, n2);
        }
        return decodeBase64Char;
    }
    
    protected final int _decodeBase64Escape(final Base64Variant base64Variant, final char c, final int n) throws IOException {
        if (c != '\\') {
            throw this.reportInvalidBase64Char(base64Variant, c, n);
        }
        final char decodeEscaped = this._decodeEscaped();
        if (decodeEscaped <= ' ') {
            return -1;
        }
        final int decodeBase64Char = base64Variant.decodeBase64Char(decodeEscaped);
        if (decodeBase64Char < 0) {
            throw this.reportInvalidBase64Char(base64Variant, decodeEscaped, n);
        }
        return decodeBase64Char;
    }
    
    protected IllegalArgumentException reportInvalidBase64Char(final Base64Variant base64Variant, final int n, final int n2) throws IllegalArgumentException {
        return this.reportInvalidBase64Char(base64Variant, n, n2, null);
    }
    
    protected IllegalArgumentException reportInvalidBase64Char(final Base64Variant base64Variant, final int n, final int n2, final String s) throws IllegalArgumentException {
        String s2;
        if (n <= 32) {
            s2 = String.format("Illegal white space character (code 0x%s) as character #%d of 4-char base64 unit: can only used between units", Integer.toHexString(n), n2 + 1);
        }
        else if (base64Variant.usesPaddingChar(n)) {
            s2 = "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (n2 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        }
        else if (!Character.isDefined(n) || Character.isISOControl(n)) {
            s2 = "Illegal character (code 0x" + Integer.toHexString(n) + ") in base64 content";
        }
        else {
            s2 = "Illegal character '" + (char)n + "' (code 0x" + Integer.toHexString(n) + ") in base64 content";
        }
        if (s != null) {
            s2 = s2 + ": " + s;
        }
        return new IllegalArgumentException(s2);
    }
    
    protected Object _getSourceReference() {
        if (Feature.INCLUDE_SOURCE_IN_LOCATION.enabledIn(this._features)) {
            return this._ioContext.getSourceReference();
        }
        return null;
    }
    
    protected static int[] growArrayBy(final int[] array, final int n) {
        if (array == null) {
            return new int[n];
        }
        return Arrays.copyOf(array, array.length + n);
    }
    
    @Deprecated
    protected void loadMoreGuaranteed() throws IOException {
        if (!this.loadMore()) {
            this._reportInvalidEOF();
        }
    }
    
    @Deprecated
    protected boolean loadMore() throws IOException {
        return false;
    }
    
    protected void _finishString() throws IOException {
    }
    
    @Override
    public /* bridge */ JsonStreamContext getParsingContext() {
        return this.getParsingContext();
    }
}
