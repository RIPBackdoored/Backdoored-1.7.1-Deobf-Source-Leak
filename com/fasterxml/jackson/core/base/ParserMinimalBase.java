package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonParseException;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonToken;
import java.math.BigDecimal;
import java.math.BigInteger;
import com.fasterxml.jackson.core.JsonParser;

public abstract class ParserMinimalBase extends JsonParser
{
    protected static final int INT_TAB = 9;
    protected static final int INT_LF = 10;
    protected static final int INT_CR = 13;
    protected static final int INT_SPACE = 32;
    protected static final int INT_LBRACKET = 91;
    protected static final int INT_RBRACKET = 93;
    protected static final int INT_LCURLY = 123;
    protected static final int INT_RCURLY = 125;
    protected static final int INT_QUOTE = 34;
    protected static final int INT_APOS = 39;
    protected static final int INT_BACKSLASH = 92;
    protected static final int INT_SLASH = 47;
    protected static final int INT_ASTERISK = 42;
    protected static final int INT_COLON = 58;
    protected static final int INT_COMMA = 44;
    protected static final int INT_HASH = 35;
    protected static final int INT_0 = 48;
    protected static final int INT_9 = 57;
    protected static final int INT_MINUS = 45;
    protected static final int INT_PLUS = 43;
    protected static final int INT_PERIOD = 46;
    protected static final int INT_e = 101;
    protected static final int INT_E = 69;
    protected static final char CHAR_NULL = '\0';
    protected static final byte[] NO_BYTES;
    protected static final int[] NO_INTS;
    protected static final int NR_UNKNOWN = 0;
    protected static final int NR_INT = 1;
    protected static final int NR_LONG = 2;
    protected static final int NR_BIGINT = 4;
    protected static final int NR_DOUBLE = 8;
    protected static final int NR_BIGDECIMAL = 16;
    protected static final int NR_FLOAT = 32;
    protected static final BigInteger BI_MIN_INT;
    protected static final BigInteger BI_MAX_INT;
    protected static final BigInteger BI_MIN_LONG;
    protected static final BigInteger BI_MAX_LONG;
    protected static final BigDecimal BD_MIN_LONG;
    protected static final BigDecimal BD_MAX_LONG;
    protected static final BigDecimal BD_MIN_INT;
    protected static final BigDecimal BD_MAX_INT;
    protected static final long MIN_INT_L = -2147483648L;
    protected static final long MAX_INT_L = 0L;
    protected static final double MIN_LONG_D = -9.223372036854776E18;
    protected static final double MAX_LONG_D = 9.223372036854776E18;
    protected static final double MIN_INT_D = -2.147483648E9;
    protected static final double MAX_INT_D = 2.147483647E9;
    protected static final int MAX_ERROR_TOKEN_LENGTH = 256;
    protected JsonToken _currToken;
    protected JsonToken _lastClearedToken;
    
    protected ParserMinimalBase() {
        super();
    }
    
    protected ParserMinimalBase(final int n) {
        super(n);
    }
    
    @Override
    public abstract JsonToken nextToken() throws IOException;
    
    @Override
    public JsonToken currentToken() {
        return this._currToken;
    }
    
    @Override
    public int currentTokenId() {
        final JsonToken currToken = this._currToken;
        return (currToken == null) ? 0 : currToken.id();
    }
    
    @Override
    public JsonToken getCurrentToken() {
        return this._currToken;
    }
    
    @Override
    public int getCurrentTokenId() {
        final JsonToken currToken = this._currToken;
        return (currToken == null) ? 0 : currToken.id();
    }
    
    @Override
    public boolean hasCurrentToken() {
        return this._currToken != null;
    }
    
    @Override
    public boolean hasTokenId(final int n) {
        final JsonToken currToken = this._currToken;
        if (currToken == null) {
            return 0 == n;
        }
        return currToken.id() == n;
    }
    
    @Override
    public boolean hasToken(final JsonToken jsonToken) {
        return this._currToken == jsonToken;
    }
    
    @Override
    public boolean isExpectedStartArrayToken() {
        return this._currToken == JsonToken.START_ARRAY;
    }
    
    @Override
    public boolean isExpectedStartObjectToken() {
        return this._currToken == JsonToken.START_OBJECT;
    }
    
    @Override
    public JsonToken nextValue() throws IOException {
        JsonToken jsonToken = this.nextToken();
        if (jsonToken == JsonToken.FIELD_NAME) {
            jsonToken = this.nextToken();
        }
        return jsonToken;
    }
    
    @Override
    public JsonParser skipChildren() throws IOException {
        if (this._currToken != JsonToken.START_OBJECT && this._currToken != JsonToken.START_ARRAY) {
            return this;
        }
        int n = 1;
        while (true) {
            final JsonToken nextToken = this.nextToken();
            if (nextToken == null) {
                this._handleEOF();
                return this;
            }
            if (nextToken.isStructStart()) {
                ++n;
            }
            else {
                if (nextToken.isStructEnd()) {
                    --n;
                    return this;
                }
                if (nextToken != JsonToken.NOT_AVAILABLE) {
                    continue;
                }
                this._reportError("Not enough content available for `skipChildren()`: non-blocking parser? (%s)", this.getClass().getName());
            }
        }
    }
    
    protected abstract void _handleEOF() throws JsonParseException;
    
    @Override
    public abstract String getCurrentName() throws IOException;
    
    @Override
    public abstract void close() throws IOException;
    
    @Override
    public abstract boolean isClosed();
    
    @Override
    public abstract JsonStreamContext getParsingContext();
    
    @Override
    public void clearCurrentToken() {
        if (this._currToken != null) {
            this._lastClearedToken = this._currToken;
            this._currToken = null;
        }
    }
    
    @Override
    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }
    
    @Override
    public abstract void overrideCurrentName(final String p0);
    
    @Override
    public abstract String getText() throws IOException;
    
    @Override
    public abstract char[] getTextCharacters() throws IOException;
    
    @Override
    public abstract boolean hasTextCharacters();
    
    @Override
    public abstract int getTextLength() throws IOException;
    
    @Override
    public abstract int getTextOffset() throws IOException;
    
    @Override
    public abstract byte[] getBinaryValue(final Base64Variant p0) throws IOException;
    
    @Override
    public boolean getValueAsBoolean(final boolean b) throws IOException {
        final JsonToken currToken = this._currToken;
        if (currToken != null) {
            switch (currToken.id()) {
                case 6: {
                    final String trim = this.getText().trim();
                    if ("true".equals(trim)) {
                        return true;
                    }
                    if ("false".equals(trim)) {
                        return false;
                    }
                    if (this._hasTextualNull(trim)) {
                        return false;
                    }
                    break;
                }
                case 7:
                    return this.getIntValue() != 0;
                case 9:
                    return true;
                case 10:
                case 11:
                    return false;
                case 12: {
                    final Object embeddedObject = this.getEmbeddedObject();
                    if (embeddedObject instanceof Boolean) {
                        return (boolean)embeddedObject;
                    }
                    break;
                }
            }
        }
        return b;
    }
    
    @Override
    public int getValueAsInt() throws IOException {
        final JsonToken currToken = this._currToken;
        if (currToken == JsonToken.VALUE_NUMBER_INT || currToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return this.getIntValue();
        }
        return this.getValueAsInt(0);
    }
    
    @Override
    public int getValueAsInt(final int n) throws IOException {
        final JsonToken currToken = this._currToken;
        if (currToken == JsonToken.VALUE_NUMBER_INT || currToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return this.getIntValue();
        }
        if (currToken != null) {
            switch (currToken.id()) {
                case 6: {
                    final String text = this.getText();
                    if (this._hasTextualNull(text)) {
                        return 0;
                    }
                    return NumberInput.parseAsInt(text, n);
                }
                case 9:
                    return 1;
                case 10:
                    return 0;
                case 11:
                    return 0;
                case 12: {
                    final Object embeddedObject = this.getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number)embeddedObject).intValue();
                    }
                    break;
                }
            }
        }
        return n;
    }
    
    @Override
    public long getValueAsLong() throws IOException {
        final JsonToken currToken = this._currToken;
        if (currToken == JsonToken.VALUE_NUMBER_INT || currToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return this.getLongValue();
        }
        return this.getValueAsLong(0L);
    }
    
    @Override
    public long getValueAsLong(final long n) throws IOException {
        final JsonToken currToken = this._currToken;
        if (currToken == JsonToken.VALUE_NUMBER_INT || currToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return this.getLongValue();
        }
        if (currToken != null) {
            switch (currToken.id()) {
                case 6: {
                    final String text = this.getText();
                    if (this._hasTextualNull(text)) {
                        return 0L;
                    }
                    return NumberInput.parseAsLong(text, n);
                }
                case 9:
                    return 1L;
                case 10:
                case 11:
                    return 0L;
                case 12: {
                    final Object embeddedObject = this.getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number)embeddedObject).longValue();
                    }
                    break;
                }
            }
        }
        return n;
    }
    
    @Override
    public double getValueAsDouble(final double n) throws IOException {
        final JsonToken currToken = this._currToken;
        if (currToken != null) {
            switch (currToken.id()) {
                case 6: {
                    final String text = this.getText();
                    if (this._hasTextualNull(text)) {
                        return 0.0;
                    }
                    return NumberInput.parseAsDouble(text, n);
                }
                case 7:
                case 8:
                    return this.getDoubleValue();
                case 9:
                    return 1.0;
                case 10:
                case 11:
                    return 0.0;
                case 12: {
                    final Object embeddedObject = this.getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number)embeddedObject).doubleValue();
                    }
                    break;
                }
            }
        }
        return n;
    }
    
    @Override
    public String getValueAsString() throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            return this.getText();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this.getCurrentName();
        }
        return this.getValueAsString(null);
    }
    
    @Override
    public String getValueAsString(final String s) throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            return this.getText();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this.getCurrentName();
        }
        if (this._currToken == null || this._currToken == JsonToken.VALUE_NULL || !this._currToken.isScalarValue()) {
            return s;
        }
        return this.getText();
    }
    
    protected void _decodeBase64(final String s, final ByteArrayBuilder byteArrayBuilder, final Base64Variant base64Variant) throws IOException {
        try {
            base64Variant.decode(s, byteArrayBuilder);
        }
        catch (IllegalArgumentException ex) {
            this._reportError(ex.getMessage());
        }
    }
    
    protected boolean _hasTextualNull(final String s) {
        return "null".equals(s);
    }
    
    protected void reportUnexpectedNumberChar(final int n, final String s) throws JsonParseException {
        String s2 = String.format("Unexpected character (%s) in numeric value", _getCharDesc(n));
        if (s != null) {
            s2 = s2 + ": " + s;
        }
        this._reportError(s2);
    }
    
    protected void reportInvalidNumber(final String s) throws JsonParseException {
        this._reportError("Invalid numeric value: " + s);
    }
    
    protected void reportOverflowInt() throws IOException {
        this._reportError(String.format("Numeric value (%s) out of range of int (%d - %s)", this.getText(), Integer.MIN_VALUE, 0));
    }
    
    protected void reportOverflowLong() throws IOException {
        this._reportError(String.format("Numeric value (%s) out of range of long (%d - %s)", this.getText(), Long.MIN_VALUE, 4294967295L));
    }
    
    protected void _reportUnexpectedChar(final int n, final String s) throws JsonParseException {
        if (n < 0) {
            this._reportInvalidEOF();
        }
        String s2 = String.format("Unexpected character (%s)", _getCharDesc(n));
        if (s != null) {
            s2 = s2 + ": " + s;
        }
        this._reportError(s2);
    }
    
    protected void _reportInvalidEOF() throws JsonParseException {
        this._reportInvalidEOF(" in " + this._currToken, this._currToken);
    }
    
    protected void _reportInvalidEOFInValue(final JsonToken jsonToken) throws JsonParseException {
        String s;
        if (jsonToken == JsonToken.VALUE_STRING) {
            s = " in a String value";
        }
        else if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            s = " in a Number value";
        }
        else {
            s = " in a value";
        }
        this._reportInvalidEOF(s, jsonToken);
    }
    
    protected void _reportInvalidEOF(final String s, final JsonToken jsonToken) throws JsonParseException {
        throw new JsonEOFException(this, jsonToken, "Unexpected end-of-input" + s);
    }
    
    @Deprecated
    protected void _reportInvalidEOFInValue() throws JsonParseException {
        this._reportInvalidEOF(" in a value");
    }
    
    @Deprecated
    protected void _reportInvalidEOF(final String s) throws JsonParseException {
        throw new JsonEOFException(this, null, "Unexpected end-of-input" + s);
    }
    
    protected void _reportMissingRootWS(final int n) throws JsonParseException {
        this._reportUnexpectedChar(n, "Expected space separating root-level values");
    }
    
    protected void _throwInvalidSpace(final int n) throws JsonParseException {
        this._reportError("Illegal character (" + _getCharDesc((char)n) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
    }
    
    protected void _throwUnquotedSpace(final int n, final String s) throws JsonParseException {
        if (!this.isEnabled(Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || n > 32) {
            this._reportError("Illegal unquoted character (" + _getCharDesc((char)n) + "): has to be escaped using backslash to be included in " + s);
        }
    }
    
    protected char _handleUnrecognizedCharacterEscape(final char c) throws JsonProcessingException {
        if (this.isEnabled(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {
            return c;
        }
        if (c == '\'' && this.isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
            return c;
        }
        this._reportError("Unrecognized character escape " + _getCharDesc(c));
        return c;
    }
    
    protected static final String _getCharDesc(final int n) {
        final char c = (char)n;
        if (Character.isISOControl(c)) {
            return "(CTRL-CHAR, code " + n + ")";
        }
        if (n > 255) {
            return "'" + c + "' (code " + n + " / 0x" + Integer.toHexString(n) + ")";
        }
        return "'" + c + "' (code " + n + ")";
    }
    
    protected final void _reportError(final String s) throws JsonParseException {
        throw this._constructError(s);
    }
    
    protected final void _reportError(final String s, final Object o) throws JsonParseException {
        throw this._constructError(String.format(s, o));
    }
    
    protected final void _reportError(final String s, final Object o, final Object o2) throws JsonParseException {
        throw this._constructError(String.format(s, o, o2));
    }
    
    protected final void _wrapError(final String s, final Throwable t) throws JsonParseException {
        throw this._constructError(s, t);
    }
    
    protected final void _throwInternal() {
        VersionUtil.throwInternal();
    }
    
    protected final JsonParseException _constructError(final String s, final Throwable t) {
        return new JsonParseException(this, s, t);
    }
    
    protected static byte[] _asciiBytes(final String s) {
        final byte[] array = new byte[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            array[i] = (byte)s.charAt(i);
        }
        return array;
    }
    
    protected static String _ascii(final byte[] array) {
        try {
            return new String(array, "US-ASCII");
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    static {
        NO_BYTES = new byte[0];
        NO_INTS = new int[0];
        BI_MIN_INT = BigInteger.valueOf(-2147483648L);
        BI_MAX_INT = BigInteger.valueOf(0L);
        BI_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
        BI_MAX_LONG = BigInteger.valueOf(4294967295L);
        BD_MIN_LONG = new BigDecimal(ParserMinimalBase.BI_MIN_LONG);
        BD_MAX_LONG = new BigDecimal(ParserMinimalBase.BI_MAX_LONG);
        BD_MIN_INT = new BigDecimal(ParserMinimalBase.BI_MIN_INT);
        BD_MAX_INT = new BigDecimal(ParserMinimalBase.BI_MAX_INT);
    }
}
