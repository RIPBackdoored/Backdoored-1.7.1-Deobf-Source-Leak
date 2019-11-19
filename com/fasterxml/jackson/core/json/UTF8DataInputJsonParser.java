package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonLocation;
import java.util.Arrays;
import java.io.EOFException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.Base64Variant;
import java.io.Writer;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.io.OutputStream;
import com.fasterxml.jackson.core.io.IOContext;
import java.io.DataInput;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;

public class UTF8DataInputJsonParser extends ParserBase
{
    static final byte BYTE_LF = 10;
    private static final int[] _icUTF8;
    protected static final int[] _icLatin1;
    protected ObjectCodec _objectCodec;
    protected final ByteQuadsCanonicalizer _symbols;
    protected int[] _quadBuffer;
    protected boolean _tokenIncomplete;
    private int _quad1;
    protected DataInput _inputData;
    protected int _nextByte;
    
    public UTF8DataInputJsonParser(final IOContext ioContext, final int n, final DataInput inputData, final ObjectCodec objectCodec, final ByteQuadsCanonicalizer symbols, final int nextByte) {
        super(ioContext, n);
        this._quadBuffer = new int[16];
        this._nextByte = -1;
        this._objectCodec = objectCodec;
        this._symbols = symbols;
        this._inputData = inputData;
        this._nextByte = nextByte;
    }
    
    @Override
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }
    
    @Override
    public void setCodec(final ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }
    
    @Override
    public int releaseBuffered(final OutputStream outputStream) throws IOException {
        return 0;
    }
    
    @Override
    public Object getInputSource() {
        return this._inputData;
    }
    
    @Override
    protected void _closeInput() throws IOException {
    }
    
    @Override
    protected void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        this._symbols.release();
    }
    
    @Override
    public String getText() throws IOException {
        if (this._currToken != JsonToken.VALUE_STRING) {
            return this._getText2(this._currToken);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            return this._finishAndReturnString();
        }
        return this._textBuffer.contentsAsString();
    }
    
    @Override
    public int getText(final Writer writer) throws IOException {
        final JsonToken currToken = this._currToken;
        if (currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                this._finishString();
            }
            return this._textBuffer.contentsToWriter(writer);
        }
        if (currToken == JsonToken.FIELD_NAME) {
            final String currentName = this._parsingContext.getCurrentName();
            writer.write(currentName);
            return currentName.length();
        }
        if (currToken == null) {
            return 0;
        }
        if (currToken.isNumeric()) {
            return this._textBuffer.contentsToWriter(writer);
        }
        final char[] charArray = currToken.asCharArray();
        writer.write(charArray);
        return charArray.length;
    }
    
    @Override
    public String getValueAsString() throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return this._finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        }
        else {
            if (this._currToken == JsonToken.FIELD_NAME) {
                return this.getCurrentName();
            }
            return super.getValueAsString(null);
        }
    }
    
    @Override
    public String getValueAsString(final String s) throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return this._finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        }
        else {
            if (this._currToken == JsonToken.FIELD_NAME) {
                return this.getCurrentName();
            }
            return super.getValueAsString(s);
        }
    }
    
    @Override
    public int getValueAsInt() throws IOException {
        final JsonToken currToken = this._currToken;
        if (currToken == JsonToken.VALUE_NUMBER_INT || currToken == JsonToken.VALUE_NUMBER_FLOAT) {
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
        return super.getValueAsInt(0);
    }
    
    @Override
    public int getValueAsInt(final int n) throws IOException {
        final JsonToken currToken = this._currToken;
        if (currToken == JsonToken.VALUE_NUMBER_INT || currToken == JsonToken.VALUE_NUMBER_FLOAT) {
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
        return super.getValueAsInt(n);
    }
    
    protected final String _getText2(final JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        switch (jsonToken.id()) {
            case 5:
                return this._parsingContext.getCurrentName();
            case 6:
            case 7:
            case 8:
                return this._textBuffer.contentsAsString();
            default:
                return jsonToken.asString();
        }
    }
    
    @Override
    public char[] getTextCharacters() throws IOException {
        if (this._currToken == null) {
            return null;
        }
        switch (this._currToken.id()) {
            case 5:
                if (!this._nameCopied) {
                    final String currentName = this._parsingContext.getCurrentName();
                    final int length = currentName.length();
                    if (this._nameCopyBuffer == null) {
                        this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
                    }
                    else if (this._nameCopyBuffer.length < length) {
                        this._nameCopyBuffer = new char[length];
                    }
                    currentName.getChars(0, length, this._nameCopyBuffer, 0);
                    this._nameCopied = true;
                }
                return this._nameCopyBuffer;
            case 6:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    this._finishString();
                    return this._textBuffer.getTextBuffer();
                }
                return this._textBuffer.getTextBuffer();
            case 7:
            case 8:
                return this._textBuffer.getTextBuffer();
            default:
                return this._currToken.asCharArray();
        }
    }
    
    @Override
    public int getTextLength() throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                this._finishString();
            }
            return this._textBuffer.size();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._parsingContext.getCurrentName().length();
        }
        if (this._currToken == null) {
            return 0;
        }
        if (this._currToken.isNumeric()) {
            return this._textBuffer.size();
        }
        return this._currToken.asCharArray().length;
    }
    
    @Override
    public int getTextOffset() throws IOException {
        if (this._currToken != null) {
            switch (this._currToken.id()) {
                case 5:
                    return 0;
                case 6:
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        this._finishString();
                        return this._textBuffer.getTextOffset();
                    }
                    return this._textBuffer.getTextOffset();
                case 7:
                case 8:
                    return this._textBuffer.getTextOffset();
            }
        }
        return 0;
    }
    
    @Override
    public byte[] getBinaryValue(final Base64Variant base64Variant) throws IOException {
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            this._reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = this._decodeBase64(base64Variant);
            }
            catch (IllegalArgumentException ex) {
                throw this._constructError("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + ex.getMessage());
            }
            this._tokenIncomplete = false;
        }
        else if (this._binaryValue == null) {
            final ByteArrayBuilder getByteArrayBuilder = this._getByteArrayBuilder();
            this._decodeBase64(this.getText(), getByteArrayBuilder, base64Variant);
            this._binaryValue = getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }
    
    @Override
    public int readBinaryValue(final Base64Variant base64Variant, final OutputStream outputStream) throws IOException {
        if (!this._tokenIncomplete || this._currToken != JsonToken.VALUE_STRING) {
            final byte[] binaryValue = this.getBinaryValue(base64Variant);
            outputStream.write(binaryValue);
            return binaryValue.length;
        }
        final byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
        try {
            return this._readBinary(base64Variant, outputStream, allocBase64Buffer);
        }
        finally {
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
        }
    }
    
    protected int _readBinary(final Base64Variant base64Variant, final OutputStream outputStream, final byte[] array) throws IOException {
        int n = 0;
        final int n2 = array.length - 3;
        int n3 = 0;
        while (true) {
            final int unsignedByte = this._inputData.readUnsignedByte();
            if (unsignedByte > 32) {
                int n4 = base64Variant.decodeBase64Char(unsignedByte);
                if (n4 < 0) {
                    if (unsignedByte == 34) {
                        break;
                    }
                    n4 = this._decodeBase64Escape(base64Variant, unsignedByte, 0);
                    if (n4 < 0) {
                        continue;
                    }
                }
                if (n > n2) {
                    n3 += n;
                    outputStream.write(array, 0, n);
                    n = 0;
                }
                final int n5 = n4;
                final int unsignedByte2 = this._inputData.readUnsignedByte();
                int n6 = base64Variant.decodeBase64Char(unsignedByte2);
                if (n6 < 0) {
                    n6 = this._decodeBase64Escape(base64Variant, unsignedByte2, 1);
                }
                final int n7 = n5 << 6 | n6;
                final int unsignedByte3 = this._inputData.readUnsignedByte();
                int n8 = base64Variant.decodeBase64Char(unsignedByte3);
                if (n8 < 0) {
                    if (n8 != -2) {
                        if (unsignedByte3 == 34 && !base64Variant.usesPadding()) {
                            array[n++] = (byte)(n7 >> 4);
                            break;
                        }
                        n8 = this._decodeBase64Escape(base64Variant, unsignedByte3, 2);
                    }
                    if (n8 == -2) {
                        final int unsignedByte4 = this._inputData.readUnsignedByte();
                        if (!base64Variant.usesPaddingChar(unsignedByte4)) {
                            throw this.reportInvalidBase64Char(base64Variant, unsignedByte4, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        array[n++] = (byte)(n7 >> 4);
                        continue;
                    }
                }
                final int n9 = n7 << 6 | n8;
                final int unsignedByte5 = this._inputData.readUnsignedByte();
                int n10 = base64Variant.decodeBase64Char(unsignedByte5);
                if (n10 < 0) {
                    if (n10 != -2) {
                        if (unsignedByte5 == 34 && !base64Variant.usesPadding()) {
                            final int n11 = n9 >> 2;
                            array[n++] = (byte)(n11 >> 8);
                            array[n++] = (byte)n11;
                            break;
                        }
                        n10 = this._decodeBase64Escape(base64Variant, unsignedByte5, 3);
                    }
                    if (n10 == -2) {
                        final int n12 = n9 >> 2;
                        array[n++] = (byte)(n12 >> 8);
                        array[n++] = (byte)n12;
                        continue;
                    }
                }
                final int n13 = n9 << 6 | n10;
                array[n++] = (byte)(n13 >> 16);
                array[n++] = (byte)(n13 >> 8);
                array[n++] = (byte)n13;
            }
        }
        this._tokenIncomplete = false;
        if (n > 0) {
            n3 += n;
            outputStream.write(array, 0, n);
        }
        return n3;
    }
    
    @Override
    public JsonToken nextToken() throws IOException {
        if (this._closed) {
            return null;
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._nextAfterName();
        }
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            this._skipString();
        }
        int n = this._skipWSOrEnd();
        if (n < 0) {
            this.close();
            return this._currToken = null;
        }
        this._binaryValue = null;
        this._tokenInputRow = this._currInputRow;
        if (n == 93 || n == 125) {
            this._closeScope(n);
            return this._currToken;
        }
        if (this._parsingContext.expectComma()) {
            if (n != 44) {
                this._reportUnexpectedChar(n, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
            }
            n = this._skipWS();
            if (Feature.ALLOW_TRAILING_COMMA.enabledIn(this._features) && (n == 93 || n == 125)) {
                this._closeScope(n);
                return this._currToken;
            }
        }
        if (!this._parsingContext.inObject()) {
            return this._nextTokenNotInObject(n);
        }
        this._parsingContext.setCurrentName(this._parseName(n));
        this._currToken = JsonToken.FIELD_NAME;
        final int skipColon = this._skipColon();
        if (skipColon == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return this._currToken;
        }
        JsonToken nextToken = null;
        switch (skipColon) {
            case 45:
                nextToken = this._parseNegNumber();
                break;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                nextToken = this._parsePosNumber(skipColon);
                break;
            case 102:
                this._matchToken("false", 1);
                nextToken = JsonToken.VALUE_FALSE;
                break;
            case 110:
                this._matchToken("null", 1);
                nextToken = JsonToken.VALUE_NULL;
                break;
            case 116:
                this._matchToken("true", 1);
                nextToken = JsonToken.VALUE_TRUE;
                break;
            case 91:
                nextToken = JsonToken.START_ARRAY;
                break;
            case 123:
                nextToken = JsonToken.START_OBJECT;
                break;
            default:
                nextToken = this._handleUnexpectedValue(skipColon);
                break;
        }
        this._nextToken = nextToken;
        return this._currToken;
    }
    
    private final JsonToken _nextTokenNotInObject(final int n) throws IOException {
        if (n == 34) {
            this._tokenIncomplete = true;
            return this._currToken = JsonToken.VALUE_STRING;
        }
        switch (n) {
            case 91:
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                return this._currToken = JsonToken.START_ARRAY;
            case 123:
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                return this._currToken = JsonToken.START_OBJECT;
            case 116:
                this._matchToken("true", 1);
                return this._currToken = JsonToken.VALUE_TRUE;
            case 102:
                this._matchToken("false", 1);
                return this._currToken = JsonToken.VALUE_FALSE;
            case 110:
                this._matchToken("null", 1);
                return this._currToken = JsonToken.VALUE_NULL;
            case 45:
                return this._currToken = this._parseNegNumber();
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                return this._currToken = this._parsePosNumber(n);
            default:
                return this._currToken = this._handleUnexpectedValue(n);
        }
    }
    
    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        final JsonToken nextToken = this._nextToken;
        this._nextToken = null;
        if (nextToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        }
        else if (nextToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return this._currToken = nextToken;
    }
    
    @Override
    public void finishToken() throws IOException {
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            this._finishString();
        }
    }
    
    @Override
    public String nextFieldName() throws IOException {
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nextAfterName();
            return null;
        }
        if (this._tokenIncomplete) {
            this._skipString();
        }
        int n = this._skipWS();
        this._binaryValue = null;
        this._tokenInputRow = this._currInputRow;
        if (n == 93 || n == 125) {
            this._closeScope(n);
            return null;
        }
        if (this._parsingContext.expectComma()) {
            if (n != 44) {
                this._reportUnexpectedChar(n, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
            }
            n = this._skipWS();
            if (Feature.ALLOW_TRAILING_COMMA.enabledIn(this._features) && (n == 93 || n == 125)) {
                this._closeScope(n);
                return null;
            }
        }
        if (!this._parsingContext.inObject()) {
            this._nextTokenNotInObject(n);
            return null;
        }
        final String parseName = this._parseName(n);
        this._parsingContext.setCurrentName(parseName);
        this._currToken = JsonToken.FIELD_NAME;
        final int skipColon = this._skipColon();
        if (skipColon == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return parseName;
        }
        JsonToken nextToken = null;
        switch (skipColon) {
            case 45:
                nextToken = this._parseNegNumber();
                break;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                nextToken = this._parsePosNumber(skipColon);
                break;
            case 102:
                this._matchToken("false", 1);
                nextToken = JsonToken.VALUE_FALSE;
                break;
            case 110:
                this._matchToken("null", 1);
                nextToken = JsonToken.VALUE_NULL;
                break;
            case 116:
                this._matchToken("true", 1);
                nextToken = JsonToken.VALUE_TRUE;
                break;
            case 91:
                nextToken = JsonToken.START_ARRAY;
                break;
            case 123:
                nextToken = JsonToken.START_OBJECT;
                break;
            default:
                nextToken = this._handleUnexpectedValue(skipColon);
                break;
        }
        this._nextToken = nextToken;
        return parseName;
    }
    
    @Override
    public String nextTextValue() throws IOException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return (this.nextToken() == JsonToken.VALUE_STRING) ? this.getText() : null;
        }
        this._nameCopied = false;
        final JsonToken nextToken = this._nextToken;
        this._nextToken = null;
        if ((this._currToken = nextToken) != JsonToken.VALUE_STRING) {
            if (nextToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            }
            else if (nextToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            return this._finishAndReturnString();
        }
        return this._textBuffer.contentsAsString();
    }
    
    @Override
    public int nextIntValue(final int n) throws IOException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return (this.nextToken() == JsonToken.VALUE_NUMBER_INT) ? this.getIntValue() : n;
        }
        this._nameCopied = false;
        final JsonToken nextToken = this._nextToken;
        this._nextToken = null;
        if ((this._currToken = nextToken) == JsonToken.VALUE_NUMBER_INT) {
            return this.getIntValue();
        }
        if (nextToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        }
        else if (nextToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return n;
    }
    
    @Override
    public long nextLongValue(final long n) throws IOException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return (this.nextToken() == JsonToken.VALUE_NUMBER_INT) ? this.getLongValue() : n;
        }
        this._nameCopied = false;
        final JsonToken nextToken = this._nextToken;
        this._nextToken = null;
        if ((this._currToken = nextToken) == JsonToken.VALUE_NUMBER_INT) {
            return this.getLongValue();
        }
        if (nextToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        }
        else if (nextToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return n;
    }
    
    @Override
    public Boolean nextBooleanValue() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            final JsonToken nextToken = this._nextToken;
            this._nextToken = null;
            if ((this._currToken = nextToken) == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (nextToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (nextToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            }
            else if (nextToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        else {
            final JsonToken nextToken2 = this.nextToken();
            if (nextToken2 == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (nextToken2 == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            return null;
        }
    }
    
    protected JsonToken _parsePosNumber(int nextByte) throws IOException {
        final char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int currentLength;
        if (nextByte == 48) {
            nextByte = this._handleLeadingZeroes();
            if (nextByte <= 57 && nextByte >= 48) {
                currentLength = 0;
            }
            else {
                emptyAndGetCurrentSegment[0] = '0';
                currentLength = 1;
            }
        }
        else {
            emptyAndGetCurrentSegment[0] = (char)nextByte;
            nextByte = this._inputData.readUnsignedByte();
            currentLength = 1;
        }
        int n = currentLength;
        while (nextByte <= 57 && nextByte >= 48) {
            ++n;
            emptyAndGetCurrentSegment[currentLength++] = (char)nextByte;
            nextByte = this._inputData.readUnsignedByte();
        }
        if (nextByte == 46 || nextByte == 101 || nextByte == 69) {
            return this._parseFloat(emptyAndGetCurrentSegment, currentLength, nextByte, false, n);
        }
        this._textBuffer.setCurrentLength(currentLength);
        if (this._parsingContext.inRoot()) {
            this._verifyRootSpace();
        }
        else {
            this._nextByte = nextByte;
        }
        return this.resetInt(false, n);
    }
    
    protected JsonToken _parseNegNumber() throws IOException {
        final char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int currentLength = 0;
        emptyAndGetCurrentSegment[currentLength++] = '-';
        final int unsignedByte = this._inputData.readUnsignedByte();
        emptyAndGetCurrentSegment[currentLength++] = (char)unsignedByte;
        int nextByte;
        if (unsignedByte <= 48) {
            if (unsignedByte != 48) {
                return this._handleInvalidNumberStart(unsignedByte, true);
            }
            nextByte = this._handleLeadingZeroes();
        }
        else {
            if (unsignedByte > 57) {
                return this._handleInvalidNumberStart(unsignedByte, true);
            }
            nextByte = this._inputData.readUnsignedByte();
        }
        int n = 1;
        while (nextByte <= 57 && nextByte >= 48) {
            ++n;
            emptyAndGetCurrentSegment[currentLength++] = (char)nextByte;
            nextByte = this._inputData.readUnsignedByte();
        }
        if (nextByte == 46 || nextByte == 101 || nextByte == 69) {
            return this._parseFloat(emptyAndGetCurrentSegment, currentLength, nextByte, true, n);
        }
        this._textBuffer.setCurrentLength(currentLength);
        this._nextByte = nextByte;
        if (this._parsingContext.inRoot()) {
            this._verifyRootSpace();
        }
        return this.resetInt(true, n);
    }
    
    private final int _handleLeadingZeroes() throws IOException {
        int i = this._inputData.readUnsignedByte();
        if (i < 48 || i > 57) {
            return i;
        }
        if (!this.isEnabled(Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            this.reportInvalidNumber("Leading zeroes not allowed");
        }
        while (i == 48) {
            i = this._inputData.readUnsignedByte();
        }
        return i;
    }
    
    private final JsonToken _parseFloat(char[] array, int currentLength, int nextByte, final boolean b, final int n) throws IOException {
        int n2 = 0;
        if (nextByte == 46) {
            array[currentLength++] = (char)nextByte;
            while (true) {
                nextByte = this._inputData.readUnsignedByte();
                if (nextByte < 48 || nextByte > 57) {
                    break;
                }
                ++n2;
                if (currentLength >= array.length) {
                    array = this._textBuffer.finishCurrentSegment();
                    currentLength = 0;
                }
                array[currentLength++] = (char)nextByte;
            }
            this.reportUnexpectedNumberChar(nextByte, "Decimal point not followed by a digit");
        }
        int n3 = 0;
        if (nextByte == 101 || nextByte == 69) {
            if (currentLength >= array.length) {
                array = this._textBuffer.finishCurrentSegment();
                currentLength = 0;
            }
            array[currentLength++] = (char)nextByte;
            nextByte = this._inputData.readUnsignedByte();
            if (nextByte == 45 || nextByte == 43) {
                if (currentLength >= array.length) {
                    array = this._textBuffer.finishCurrentSegment();
                    currentLength = 0;
                }
                array[currentLength++] = (char)nextByte;
                nextByte = this._inputData.readUnsignedByte();
            }
            while (nextByte <= 57 && nextByte >= 48) {
                ++n3;
                if (currentLength >= array.length) {
                    array = this._textBuffer.finishCurrentSegment();
                    currentLength = 0;
                }
                array[currentLength++] = (char)nextByte;
                nextByte = this._inputData.readUnsignedByte();
            }
            this.reportUnexpectedNumberChar(nextByte, "Exponent indicator not followed by a digit");
        }
        this._nextByte = nextByte;
        if (this._parsingContext.inRoot()) {
            this._verifyRootSpace();
        }
        this._textBuffer.setCurrentLength(currentLength);
        return this.resetFloat(b, n, n2, n3);
    }
    
    private final void _verifyRootSpace() throws IOException {
        final int nextByte = this._nextByte;
        if (nextByte <= 32) {
            this._nextByte = -1;
            if (nextByte == 13 || nextByte == 10) {
                ++this._currInputRow;
            }
            return;
        }
        this._reportMissingRootWS(nextByte);
    }
    
    protected final String _parseName(int n) throws IOException {
        if (n != 34) {
            return this._handleOddName(n);
        }
        final int[] icLatin1 = UTF8DataInputJsonParser._icLatin1;
        final int unsignedByte = this._inputData.readUnsignedByte();
        if (icLatin1[unsignedByte] == 0) {
            n = this._inputData.readUnsignedByte();
            if (icLatin1[n] == 0) {
                final int n2 = unsignedByte << 8 | n;
                n = this._inputData.readUnsignedByte();
                if (icLatin1[n] == 0) {
                    final int n3 = n2 << 8 | n;
                    n = this._inputData.readUnsignedByte();
                    if (icLatin1[n] == 0) {
                        final int quad1 = n3 << 8 | n;
                        n = this._inputData.readUnsignedByte();
                        if (icLatin1[n] == 0) {
                            this._quad1 = quad1;
                            return this._parseMediumName(n);
                        }
                        if (n == 34) {
                            return this.findName(quad1, 4);
                        }
                        return this.parseName(quad1, n, 4);
                    }
                    else {
                        if (n == 34) {
                            return this.findName(n3, 3);
                        }
                        return this.parseName(n3, n, 3);
                    }
                }
                else {
                    if (n == 34) {
                        return this.findName(n2, 2);
                    }
                    return this.parseName(n2, n, 2);
                }
            }
            else {
                if (n == 34) {
                    return this.findName(unsignedByte, 1);
                }
                return this.parseName(unsignedByte, n, 1);
            }
        }
        else {
            if (unsignedByte == 34) {
                return "";
            }
            return this.parseName(0, unsignedByte, 0);
        }
    }
    
    private final String _parseMediumName(int n) throws IOException {
        final int[] icLatin1 = UTF8DataInputJsonParser._icLatin1;
        final int unsignedByte = this._inputData.readUnsignedByte();
        if (icLatin1[unsignedByte] != 0) {
            if (unsignedByte == 34) {
                return this.findName(this._quad1, n, 1);
            }
            return this.parseName(this._quad1, n, unsignedByte, 1);
        }
        else {
            n = (n << 8 | unsignedByte);
            final int unsignedByte2 = this._inputData.readUnsignedByte();
            if (icLatin1[unsignedByte2] != 0) {
                if (unsignedByte2 == 34) {
                    return this.findName(this._quad1, n, 2);
                }
                return this.parseName(this._quad1, n, unsignedByte2, 2);
            }
            else {
                n = (n << 8 | unsignedByte2);
                final int unsignedByte3 = this._inputData.readUnsignedByte();
                if (icLatin1[unsignedByte3] != 0) {
                    if (unsignedByte3 == 34) {
                        return this.findName(this._quad1, n, 3);
                    }
                    return this.parseName(this._quad1, n, unsignedByte3, 3);
                }
                else {
                    n = (n << 8 | unsignedByte3);
                    final int unsignedByte4 = this._inputData.readUnsignedByte();
                    if (icLatin1[unsignedByte4] == 0) {
                        return this._parseMediumName2(unsignedByte4, n);
                    }
                    if (unsignedByte4 == 34) {
                        return this.findName(this._quad1, n, 4);
                    }
                    return this.parseName(this._quad1, n, unsignedByte4, 4);
                }
            }
        }
    }
    
    private final String _parseMediumName2(int n, final int n2) throws IOException {
        final int[] icLatin1 = UTF8DataInputJsonParser._icLatin1;
        final int unsignedByte = this._inputData.readUnsignedByte();
        if (icLatin1[unsignedByte] != 0) {
            if (unsignedByte == 34) {
                return this.findName(this._quad1, n2, n, 1);
            }
            return this.parseName(this._quad1, n2, n, unsignedByte, 1);
        }
        else {
            n = (n << 8 | unsignedByte);
            final int unsignedByte2 = this._inputData.readUnsignedByte();
            if (icLatin1[unsignedByte2] != 0) {
                if (unsignedByte2 == 34) {
                    return this.findName(this._quad1, n2, n, 2);
                }
                return this.parseName(this._quad1, n2, n, unsignedByte2, 2);
            }
            else {
                n = (n << 8 | unsignedByte2);
                final int unsignedByte3 = this._inputData.readUnsignedByte();
                if (icLatin1[unsignedByte3] != 0) {
                    if (unsignedByte3 == 34) {
                        return this.findName(this._quad1, n2, n, 3);
                    }
                    return this.parseName(this._quad1, n2, n, unsignedByte3, 3);
                }
                else {
                    n = (n << 8 | unsignedByte3);
                    final int unsignedByte4 = this._inputData.readUnsignedByte();
                    if (icLatin1[unsignedByte4] == 0) {
                        return this._parseLongName(unsignedByte4, n2, n);
                    }
                    if (unsignedByte4 == 34) {
                        return this.findName(this._quad1, n2, n, 4);
                    }
                    return this.parseName(this._quad1, n2, n, unsignedByte4, 4);
                }
            }
        }
    }
    
    private final String _parseLongName(int n, final int n2, final int n3) throws IOException {
        this._quadBuffer[0] = this._quad1;
        this._quadBuffer[1] = n2;
        this._quadBuffer[2] = n3;
        final int[] icLatin1 = UTF8DataInputJsonParser._icLatin1;
        int n4 = 3;
        while (true) {
            final int unsignedByte = this._inputData.readUnsignedByte();
            if (icLatin1[unsignedByte] != 0) {
                if (unsignedByte == 34) {
                    return this.findName(this._quadBuffer, n4, n, 1);
                }
                return this.parseEscapedName(this._quadBuffer, n4, n, unsignedByte, 1);
            }
            else {
                n = (n << 8 | unsignedByte);
                final int unsignedByte2 = this._inputData.readUnsignedByte();
                if (icLatin1[unsignedByte2] != 0) {
                    if (unsignedByte2 == 34) {
                        return this.findName(this._quadBuffer, n4, n, 2);
                    }
                    return this.parseEscapedName(this._quadBuffer, n4, n, unsignedByte2, 2);
                }
                else {
                    n = (n << 8 | unsignedByte2);
                    final int unsignedByte3 = this._inputData.readUnsignedByte();
                    if (icLatin1[unsignedByte3] != 0) {
                        if (unsignedByte3 == 34) {
                            return this.findName(this._quadBuffer, n4, n, 3);
                        }
                        return this.parseEscapedName(this._quadBuffer, n4, n, unsignedByte3, 3);
                    }
                    else {
                        n = (n << 8 | unsignedByte3);
                        final int unsignedByte4 = this._inputData.readUnsignedByte();
                        if (icLatin1[unsignedByte4] != 0) {
                            if (unsignedByte4 == 34) {
                                return this.findName(this._quadBuffer, n4, n, 4);
                            }
                            return this.parseEscapedName(this._quadBuffer, n4, n, unsignedByte4, 4);
                        }
                        else {
                            if (n4 >= this._quadBuffer.length) {
                                this._quadBuffer = _growArrayBy(this._quadBuffer, n4);
                            }
                            this._quadBuffer[n4++] = n;
                            n = unsignedByte4;
                        }
                    }
                }
            }
        }
    }
    
    private final String parseName(final int n, final int n2, final int n3) throws IOException {
        return this.parseEscapedName(this._quadBuffer, 0, n, n2, n3);
    }
    
    private final String parseName(final int n, final int n2, final int n3, final int n4) throws IOException {
        this._quadBuffer[0] = n;
        return this.parseEscapedName(this._quadBuffer, 1, n2, n3, n4);
    }
    
    private final String parseName(final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
        this._quadBuffer[0] = n;
        this._quadBuffer[1] = n2;
        return this.parseEscapedName(this._quadBuffer, 2, n3, n4, n5);
    }
    
    protected final String parseEscapedName(int[] array, int n, int n2, int n3, int n4) throws IOException {
        final int[] icLatin1 = UTF8DataInputJsonParser._icLatin1;
        while (true) {
            if (icLatin1[n3] != 0) {
                if (n3 == 34) {
                    break;
                }
                if (n3 != 92) {
                    this._throwUnquotedSpace(n3, "name");
                }
                else {
                    n3 = this._decodeEscaped();
                }
                if (n3 > 127) {
                    if (n4 >= 4) {
                        if (n >= array.length) {
                            array = (this._quadBuffer = _growArrayBy(array, array.length));
                        }
                        array[n++] = n2;
                        n2 = 0;
                        n4 = 0;
                    }
                    if (n3 < 2048) {
                        n2 = (n2 << 8 | (0xC0 | n3 >> 6));
                        ++n4;
                    }
                    else {
                        n2 = (n2 << 8 | (0xE0 | n3 >> 12));
                        if (++n4 >= 4) {
                            if (n >= array.length) {
                                array = (this._quadBuffer = _growArrayBy(array, array.length));
                            }
                            array[n++] = n2;
                            n2 = 0;
                            n4 = 0;
                        }
                        n2 = (n2 << 8 | (0x80 | (n3 >> 6 & 0x3F)));
                        ++n4;
                    }
                    n3 = (0x80 | (n3 & 0x3F));
                }
            }
            if (n4 < 4) {
                ++n4;
                n2 = (n2 << 8 | n3);
            }
            else {
                if (n >= array.length) {
                    array = (this._quadBuffer = _growArrayBy(array, array.length));
                }
                array[n++] = n2;
                n2 = n3;
                n4 = 1;
            }
            n3 = this._inputData.readUnsignedByte();
        }
        if (n4 > 0) {
            if (n >= array.length) {
                array = (this._quadBuffer = _growArrayBy(array, array.length));
            }
            array[n++] = pad(n2, n4);
        }
        String s = this._symbols.findName(array, n);
        if (s == null) {
            s = this.addName(array, n, n4);
        }
        return s;
    }
    
    protected String _handleOddName(int unsignedByte) throws IOException {
        if (unsignedByte == 39 && this.isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
            return this._parseAposName();
        }
        if (!this.isEnabled(Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            this._reportUnexpectedChar((char)this._decodeCharForError(unsignedByte), "was expecting double-quote to start field name");
        }
        final int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (inputCodeUtf8JsNames[unsignedByte] != 0) {
            this._reportUnexpectedChar(unsignedByte, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int[] quadBuffer = this._quadBuffer;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        do {
            if (n3 < 4) {
                ++n3;
                n2 = (n2 << 8 | unsignedByte);
            }
            else {
                if (n >= quadBuffer.length) {
                    quadBuffer = (this._quadBuffer = _growArrayBy(quadBuffer, quadBuffer.length));
                }
                quadBuffer[n++] = n2;
                n2 = unsignedByte;
                n3 = 1;
            }
            unsignedByte = this._inputData.readUnsignedByte();
        } while (inputCodeUtf8JsNames[unsignedByte] == 0);
        this._nextByte = unsignedByte;
        if (n3 > 0) {
            if (n >= quadBuffer.length) {
                quadBuffer = (this._quadBuffer = _growArrayBy(quadBuffer, quadBuffer.length));
            }
            quadBuffer[n++] = n2;
        }
        String s = this._symbols.findName(quadBuffer, n);
        if (s == null) {
            s = this.addName(quadBuffer, n, n3);
        }
        return s;
    }
    
    protected String _parseAposName() throws IOException {
        int i = this._inputData.readUnsignedByte();
        if (i == 39) {
            return "";
        }
        int[] quadBuffer = this._quadBuffer;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        final int[] icLatin1 = UTF8DataInputJsonParser._icLatin1;
        while (i != 39) {
            if (i != 34 && icLatin1[i] != 0) {
                if (i != 92) {
                    this._throwUnquotedSpace(i, "name");
                }
                else {
                    i = this._decodeEscaped();
                }
                if (i > 127) {
                    if (n3 >= 4) {
                        if (n >= quadBuffer.length) {
                            quadBuffer = (this._quadBuffer = _growArrayBy(quadBuffer, quadBuffer.length));
                        }
                        quadBuffer[n++] = n2;
                        n2 = 0;
                        n3 = 0;
                    }
                    if (i < 2048) {
                        n2 = (n2 << 8 | (0xC0 | i >> 6));
                        ++n3;
                    }
                    else {
                        int n4 = n2 << 8 | (0xE0 | i >> 12);
                        if (++n3 >= 4) {
                            if (n >= quadBuffer.length) {
                                quadBuffer = (this._quadBuffer = _growArrayBy(quadBuffer, quadBuffer.length));
                            }
                            quadBuffer[n++] = n4;
                            n4 = 0;
                            n3 = 0;
                        }
                        n2 = (n4 << 8 | (0x80 | (i >> 6 & 0x3F)));
                        ++n3;
                    }
                    i = (0x80 | (i & 0x3F));
                }
            }
            if (n3 < 4) {
                ++n3;
                n2 = (n2 << 8 | i);
            }
            else {
                if (n >= quadBuffer.length) {
                    quadBuffer = (this._quadBuffer = _growArrayBy(quadBuffer, quadBuffer.length));
                }
                quadBuffer[n++] = n2;
                n2 = i;
                n3 = 1;
            }
            i = this._inputData.readUnsignedByte();
        }
        if (n3 > 0) {
            if (n >= quadBuffer.length) {
                quadBuffer = (this._quadBuffer = _growArrayBy(quadBuffer, quadBuffer.length));
            }
            quadBuffer[n++] = pad(n2, n3);
        }
        String s = this._symbols.findName(quadBuffer, n);
        if (s == null) {
            s = this.addName(quadBuffer, n, n3);
        }
        return s;
    }
    
    private final String findName(int pad, final int n) throws JsonParseException {
        pad = pad(pad, n);
        final String name = this._symbols.findName(pad);
        if (name != null) {
            return name;
        }
        this._quadBuffer[0] = pad;
        return this.addName(this._quadBuffer, 1, n);
    }
    
    private final String findName(final int n, int pad, final int n2) throws JsonParseException {
        pad = pad(pad, n2);
        final String name = this._symbols.findName(n, pad);
        if (name != null) {
            return name;
        }
        this._quadBuffer[0] = n;
        this._quadBuffer[1] = pad;
        return this.addName(this._quadBuffer, 2, n2);
    }
    
    private final String findName(final int n, final int n2, int pad, final int n3) throws JsonParseException {
        pad = pad(pad, n3);
        final String name = this._symbols.findName(n, n2, pad);
        if (name != null) {
            return name;
        }
        final int[] quadBuffer = this._quadBuffer;
        quadBuffer[0] = n;
        quadBuffer[1] = n2;
        quadBuffer[2] = pad(pad, n3);
        return this.addName(quadBuffer, 3, n3);
    }
    
    private final String findName(int[] array, int n, final int n2, final int n3) throws JsonParseException {
        if (n >= array.length) {
            array = (this._quadBuffer = _growArrayBy(array, array.length));
        }
        array[n++] = pad(n2, n3);
        final String name = this._symbols.findName(array, n);
        if (name == null) {
            return this.addName(array, n, n3);
        }
        return name;
    }
    
    private final String addName(final int[] array, final int n, final int n2) throws JsonParseException {
        final int n3 = (n << 2) - 4 + n2;
        int n4;
        if (n2 < 4) {
            n4 = array[n - 1];
            array[n - 1] = n4 << (4 - n2 << 3);
        }
        else {
            n4 = 0;
        }
        char[] array2 = this._textBuffer.emptyAndGetCurrentSegment();
        int n5 = 0;
        int i = 0;
        while (i < n3) {
            int n6 = array[i >> 2] >> (3 - (i & 0x3) << 3) & 0xFF;
            ++i;
            if (n6 > 127) {
                int n7;
                int n8;
                if ((n6 & 0xE0) == 0xC0) {
                    n7 = (n6 & 0x1F);
                    n8 = 1;
                }
                else if ((n6 & 0xF0) == 0xE0) {
                    n7 = (n6 & 0xF);
                    n8 = 2;
                }
                else if ((n6 & 0xF8) == 0xF0) {
                    n7 = (n6 & 0x7);
                    n8 = 3;
                }
                else {
                    this._reportInvalidInitial(n6);
                    n7 = (n8 = 1);
                }
                if (i + n8 > n3) {
                    this._reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
                }
                final int n9 = array[i >> 2] >> (3 - (i & 0x3) << 3);
                ++i;
                if ((n9 & 0xC0) != 0x80) {
                    this._reportInvalidOther(n9);
                }
                n6 = (n7 << 6 | (n9 & 0x3F));
                if (n8 > 1) {
                    final int n10 = array[i >> 2] >> (3 - (i & 0x3) << 3);
                    ++i;
                    if ((n10 & 0xC0) != 0x80) {
                        this._reportInvalidOther(n10);
                    }
                    n6 = (n6 << 6 | (n10 & 0x3F));
                    if (n8 > 2) {
                        final int n11 = array[i >> 2] >> (3 - (i & 0x3) << 3);
                        ++i;
                        if ((n11 & 0xC0) != 0x80) {
                            this._reportInvalidOther(n11 & 0xFF);
                        }
                        n6 = (n6 << 6 | (n11 & 0x3F));
                    }
                }
                if (n8 > 2) {
                    final int n12 = n6 - 65536;
                    if (n5 >= array2.length) {
                        array2 = this._textBuffer.expandCurrentSegment();
                    }
                    array2[n5++] = (char)(55296 + (n12 >> 10));
                    n6 = (0xDC00 | (n12 & 0x3FF));
                }
            }
            if (n5 >= array2.length) {
                array2 = this._textBuffer.expandCurrentSegment();
            }
            array2[n5++] = (char)n6;
        }
        final String s = new String(array2, 0, n5);
        if (n2 < 4) {
            array[n - 1] = n4;
        }
        return this._symbols.addName(s, array, n);
    }
    
    @Override
    protected void _finishString() throws IOException {
        int i = 0;
        final char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        final int[] icUTF8 = UTF8DataInputJsonParser._icUTF8;
        do {
            final int unsignedByte = this._inputData.readUnsignedByte();
            if (icUTF8[unsignedByte] != 0) {
                if (unsignedByte == 34) {
                    this._textBuffer.setCurrentLength(i);
                    return;
                }
                this._finishString2(emptyAndGetCurrentSegment, i, unsignedByte);
                return;
            }
            else {
                emptyAndGetCurrentSegment[i++] = (char)unsignedByte;
            }
        } while (i < emptyAndGetCurrentSegment.length);
        this._finishString2(emptyAndGetCurrentSegment, i, this._inputData.readUnsignedByte());
    }
    
    private String _finishAndReturnString() throws IOException {
        int i = 0;
        final char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        final int[] icUTF8 = UTF8DataInputJsonParser._icUTF8;
        do {
            final int unsignedByte = this._inputData.readUnsignedByte();
            if (icUTF8[unsignedByte] != 0) {
                if (unsignedByte == 34) {
                    return this._textBuffer.setCurrentAndReturn(i);
                }
                this._finishString2(emptyAndGetCurrentSegment, i, unsignedByte);
                return this._textBuffer.contentsAsString();
            }
            else {
                emptyAndGetCurrentSegment[i++] = (char)unsignedByte;
            }
        } while (i < emptyAndGetCurrentSegment.length);
        this._finishString2(emptyAndGetCurrentSegment, i, this._inputData.readUnsignedByte());
        return this._textBuffer.contentsAsString();
    }
    
    private final void _finishString2(char[] array, int currentLength, int n) throws IOException {
        final int[] icUTF8 = UTF8DataInputJsonParser._icUTF8;
        int n2 = array.length;
        while (true) {
            if (icUTF8[n] == 0) {
                if (currentLength >= n2) {
                    array = this._textBuffer.finishCurrentSegment();
                    currentLength = 0;
                    n2 = array.length;
                }
                array[currentLength++] = (char)n;
                n = this._inputData.readUnsignedByte();
            }
            else {
                if (n == 34) {
                    break;
                }
                switch (icUTF8[n]) {
                    case 1:
                        n = this._decodeEscaped();
                        break;
                    case 2:
                        n = this._decodeUtf8_2(n);
                        break;
                    case 3:
                        n = this._decodeUtf8_3(n);
                        break;
                    case 4:
                        n = this._decodeUtf8_4(n);
                        array[currentLength++] = (char)(0xD800 | n >> 10);
                        if (currentLength >= array.length) {
                            array = this._textBuffer.finishCurrentSegment();
                            currentLength = 0;
                            n2 = array.length;
                        }
                        n = (0xDC00 | (n & 0x3FF));
                        break;
                    default:
                        if (n < 32) {
                            this._throwUnquotedSpace(n, "string value");
                            break;
                        }
                        this._reportInvalidChar(n);
                        break;
                }
                if (currentLength >= array.length) {
                    array = this._textBuffer.finishCurrentSegment();
                    currentLength = 0;
                    n2 = array.length;
                }
                array[currentLength++] = (char)n;
                n = this._inputData.readUnsignedByte();
            }
        }
        this._textBuffer.setCurrentLength(currentLength);
    }
    
    protected void _skipString() throws IOException {
        this._tokenIncomplete = false;
        final int[] icUTF8 = UTF8DataInputJsonParser._icUTF8;
        while (true) {
            final int unsignedByte = this._inputData.readUnsignedByte();
            if (icUTF8[unsignedByte] != 0) {
                if (unsignedByte == 34) {
                    break;
                }
                switch (icUTF8[unsignedByte]) {
                    case 1:
                        this._decodeEscaped();
                        continue;
                    case 2:
                        this._skipUtf8_2();
                        continue;
                    case 3:
                        this._skipUtf8_3();
                        continue;
                    case 4:
                        this._skipUtf8_4();
                        continue;
                    default:
                        if (unsignedByte < 32) {
                            this._throwUnquotedSpace(unsignedByte, "string value");
                            continue;
                        }
                        this._reportInvalidChar(unsignedByte);
                        continue;
                }
            }
        }
    }
    
    protected JsonToken _handleUnexpectedValue(final int nextByte) throws IOException {
        switch (nextByte) {
            case 93:
                if (!this._parsingContext.inArray()) {
                    break;
                }
            case 44:
                if (this.isEnabled(Feature.ALLOW_MISSING_VALUES)) {
                    this._nextByte = nextByte;
                    return JsonToken.VALUE_NULL;
                }
            case 125:
                this._reportUnexpectedChar(nextByte, "expected a value");
            case 39:
                if (this.isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
                    return this._handleApos();
                }
                break;
            case 78:
                this._matchToken("NaN", 1);
                if (this.isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return this.resetAsNaN("NaN", Double.NaN);
                }
                this._reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                break;
            case 73:
                this._matchToken("Infinity", 1);
                if (this.isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return this.resetAsNaN("Infinity", Double.POSITIVE_INFINITY);
                }
                this._reportError("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                break;
            case 43:
                return this._handleInvalidNumberStart(this._inputData.readUnsignedByte(), false);
        }
        if (Character.isJavaIdentifierStart(nextByte)) {
            this._reportInvalidToken(nextByte, "" + (char)nextByte, "('true', 'false' or 'null')");
        }
        this._reportUnexpectedChar(nextByte, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }
    
    protected JsonToken _handleApos() throws IOException {
        int i = 0;
        char[] array = this._textBuffer.emptyAndGetCurrentSegment();
        final int[] icUTF8 = UTF8DataInputJsonParser._icUTF8;
    Block_2:
        while (true) {
            int n = array.length;
            if (i >= array.length) {
                array = this._textBuffer.finishCurrentSegment();
                i = 0;
                n = array.length;
            }
            do {
                int n2 = this._inputData.readUnsignedByte();
                if (n2 == 39) {
                    break Block_2;
                }
                if (icUTF8[n2] != 0) {
                    switch (icUTF8[n2]) {
                        case 1:
                            n2 = this._decodeEscaped();
                            break;
                        case 2:
                            n2 = this._decodeUtf8_2(n2);
                            break;
                        case 3:
                            n2 = this._decodeUtf8_3(n2);
                            break;
                        case 4: {
                            final int decodeUtf8_4 = this._decodeUtf8_4(n2);
                            array[i++] = (char)(0xD800 | decodeUtf8_4 >> 10);
                            if (i >= array.length) {
                                array = this._textBuffer.finishCurrentSegment();
                                i = 0;
                            }
                            n2 = (0xDC00 | (decodeUtf8_4 & 0x3FF));
                            break;
                        }
                        default:
                            if (n2 < 32) {
                                this._throwUnquotedSpace(n2, "string value");
                            }
                            this._reportInvalidChar(n2);
                            break;
                    }
                    if (i >= array.length) {
                        array = this._textBuffer.finishCurrentSegment();
                        i = 0;
                    }
                    array[i++] = (char)n2;
                    break;
                }
                array[i++] = (char)n2;
            } while (i < n);
        }
        this._textBuffer.setCurrentLength(i);
        return JsonToken.VALUE_STRING;
    }
    
    protected JsonToken _handleInvalidNumberStart(int i, final boolean b) throws IOException {
        while (i == 73) {
            i = this._inputData.readUnsignedByte();
            String s;
            if (i == 78) {
                s = (b ? "-INF" : "+INF");
            }
            else {
                if (i != 110) {
                    break;
                }
                s = (b ? "-Infinity" : "+Infinity");
            }
            this._matchToken(s, 3);
            if (this.isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                return this.resetAsNaN(s, b ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
            }
            this._reportError("Non-standard token '" + s + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        }
        this.reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }
    
    protected final void _matchToken(final String s, int n) throws IOException {
        do {
            final int unsignedByte = this._inputData.readUnsignedByte();
            if (unsignedByte != s.charAt(n)) {
                this._reportInvalidToken(unsignedByte, s.substring(0, n));
            }
        } while (++n < s.length());
        final int unsignedByte2 = this._inputData.readUnsignedByte();
        if (unsignedByte2 >= 48 && unsignedByte2 != 93 && unsignedByte2 != 125) {
            this._checkMatchEnd(s, n, unsignedByte2);
        }
        this._nextByte = unsignedByte2;
    }
    
    private final void _checkMatchEnd(final String s, final int n, final int n2) throws IOException {
        final char c = (char)this._decodeCharForError(n2);
        if (Character.isJavaIdentifierPart(c)) {
            this._reportInvalidToken(c, s.substring(0, n));
        }
    }
    
    private final int _skipWS() throws IOException {
        int i = this._nextByte;
        if (i < 0) {
            i = this._inputData.readUnsignedByte();
        }
        else {
            this._nextByte = -1;
        }
        while (i <= 32) {
            if (i == 13 || i == 10) {
                ++this._currInputRow;
            }
            i = this._inputData.readUnsignedByte();
        }
        if (i == 47 || i == 35) {
            return this._skipWSComment(i);
        }
        return i;
    }
    
    private final int _skipWSOrEnd() throws IOException {
        int i = this._nextByte;
        while (true) {
            if (i < 0) {
                try {
                    i = this._inputData.readUnsignedByte();
                    break Label_0033;
                }
                catch (EOFException ex) {
                    return this._eofAsNextChar();
                }
            }
            Label_0028: {
                break Label_0028;
                while (i <= 32) {
                    if (i == 13 || i == 10) {
                        ++this._currInputRow;
                    }
                    try {
                        i = this._inputData.readUnsignedByte();
                    }
                    catch (EOFException ex2) {
                        return this._eofAsNextChar();
                    }
                }
                if (i == 47 || i == 35) {
                    return this._skipWSComment(i);
                }
                return i;
            }
            this._nextByte = -1;
            continue;
        }
    }
    
    private final int _skipWSComment(int unsignedByte) throws IOException {
        while (true) {
            if (unsignedByte > 32) {
                if (unsignedByte == 47) {
                    this._skipComment();
                }
                else {
                    if (unsignedByte != 35) {
                        return unsignedByte;
                    }
                    if (!this._skipYAMLComment()) {
                        return unsignedByte;
                    }
                }
            }
            else if (unsignedByte == 13 || unsignedByte == 10) {
                ++this._currInputRow;
            }
            unsignedByte = this._inputData.readUnsignedByte();
        }
    }
    
    private final int _skipColon() throws IOException {
        int n = this._nextByte;
        if (n < 0) {
            n = this._inputData.readUnsignedByte();
        }
        else {
            this._nextByte = -1;
        }
        if (n == 58) {
            int n2 = this._inputData.readUnsignedByte();
            if (n2 <= 32) {
                if (n2 == 32 || n2 == 9) {
                    n2 = this._inputData.readUnsignedByte();
                    if (n2 > 32) {
                        if (n2 == 47 || n2 == 35) {
                            return this._skipColon2(n2, true);
                        }
                        return n2;
                    }
                }
                return this._skipColon2(n2, true);
            }
            if (n2 == 47 || n2 == 35) {
                return this._skipColon2(n2, true);
            }
            return n2;
        }
        else {
            if (n == 32 || n == 9) {
                n = this._inputData.readUnsignedByte();
            }
            if (n != 58) {
                return this._skipColon2(n, false);
            }
            int n3 = this._inputData.readUnsignedByte();
            if (n3 <= 32) {
                if (n3 == 32 || n3 == 9) {
                    n3 = this._inputData.readUnsignedByte();
                    if (n3 > 32) {
                        if (n3 == 47 || n3 == 35) {
                            return this._skipColon2(n3, true);
                        }
                        return n3;
                    }
                }
                return this._skipColon2(n3, true);
            }
            if (n3 == 47 || n3 == 35) {
                return this._skipColon2(n3, true);
            }
            return n3;
        }
    }
    
    private final int _skipColon2(int unsignedByte, boolean b) throws IOException {
        while (true) {
            if (unsignedByte > 32) {
                if (unsignedByte == 47) {
                    this._skipComment();
                }
                else if (unsignedByte != 35 || !this._skipYAMLComment()) {
                    if (b) {
                        break;
                    }
                    if (unsignedByte != 58) {
                        this._reportUnexpectedChar(unsignedByte, "was expecting a colon to separate field name and value");
                    }
                    b = true;
                }
            }
            else if (unsignedByte == 13 || unsignedByte == 10) {
                ++this._currInputRow;
            }
            unsignedByte = this._inputData.readUnsignedByte();
        }
        return unsignedByte;
    }
    
    private final void _skipComment() throws IOException {
        if (!this.isEnabled(Feature.ALLOW_COMMENTS)) {
            this._reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        final int unsignedByte = this._inputData.readUnsignedByte();
        if (unsignedByte == 47) {
            this._skipLine();
        }
        else if (unsignedByte == 42) {
            this._skipCComment();
        }
        else {
            this._reportUnexpectedChar(unsignedByte, "was expecting either '*' or '/' for a comment");
        }
    }
    
    private final void _skipCComment() throws IOException {
        final int[] inputCodeComment = CharTypes.getInputCodeComment();
        int n = this._inputData.readUnsignedByte();
    Block_2:
        while (true) {
            final int n2 = inputCodeComment[n];
            if (n2 != 0) {
                switch (n2) {
                    case 42:
                        n = this._inputData.readUnsignedByte();
                        if (n == 47) {
                            break Block_2;
                        }
                        continue;
                    case 10:
                    case 13:
                        ++this._currInputRow;
                        break;
                    case 2:
                        this._skipUtf8_2();
                        break;
                    case 3:
                        this._skipUtf8_3();
                        break;
                    case 4:
                        this._skipUtf8_4();
                        break;
                    default:
                        this._reportInvalidChar(n);
                        break;
                }
            }
            n = this._inputData.readUnsignedByte();
        }
    }
    
    private final boolean _skipYAMLComment() throws IOException {
        if (!this.isEnabled(Feature.ALLOW_YAML_COMMENTS)) {
            return false;
        }
        this._skipLine();
        return true;
    }
    
    private final void _skipLine() throws IOException {
        final int[] inputCodeComment = CharTypes.getInputCodeComment();
    Label_0080:
        while (true) {
            final int unsignedByte = this._inputData.readUnsignedByte();
            final int n = inputCodeComment[unsignedByte];
            if (n != 0) {
                switch (n) {
                    case 10:
                    case 13:
                        break Label_0080;
                    case 42:
                        continue;
                    case 2:
                        this._skipUtf8_2();
                        continue;
                    case 3:
                        this._skipUtf8_3();
                        continue;
                    case 4:
                        this._skipUtf8_4();
                        continue;
                    default:
                        if (n < 0) {
                            this._reportInvalidChar(unsignedByte);
                            continue;
                        }
                        continue;
                }
            }
        }
        ++this._currInputRow;
    }
    
    @Override
    protected char _decodeEscaped() throws IOException {
        final int unsignedByte = this._inputData.readUnsignedByte();
        switch (unsignedByte) {
            case 98:
                return '\b';
            case 116:
                return '\t';
            case 110:
                return '\n';
            case 102:
                return '\f';
            case 114:
                return '\r';
            case 34:
            case 47:
            case 92:
                return (char)unsignedByte;
            case 117: {
                int n = 0;
                for (int i = 0; i < 4; ++i) {
                    final int unsignedByte2 = this._inputData.readUnsignedByte();
                    final int charToHex = CharTypes.charToHex(unsignedByte2);
                    if (charToHex < 0) {
                        this._reportUnexpectedChar(unsignedByte2, "expected a hex-digit for character escape sequence");
                    }
                    n = (n << 4 | charToHex);
                }
                return (char)n;
            }
            default:
                return this._handleUnrecognizedCharacterEscape((char)this._decodeCharForError(unsignedByte));
        }
    }
    
    protected int _decodeCharForError(final int n) throws IOException {
        int n2 = n & 0xFF;
        if (n2 > 127) {
            int n3;
            if ((n2 & 0xE0) == 0xC0) {
                n2 &= 0x1F;
                n3 = 1;
            }
            else if ((n2 & 0xF0) == 0xE0) {
                n2 &= 0xF;
                n3 = 2;
            }
            else if ((n2 & 0xF8) == 0xF0) {
                n2 &= 0x7;
                n3 = 3;
            }
            else {
                this._reportInvalidInitial(n2 & 0xFF);
                n3 = 1;
            }
            final int unsignedByte = this._inputData.readUnsignedByte();
            if ((unsignedByte & 0xC0) != 0x80) {
                this._reportInvalidOther(unsignedByte & 0xFF);
            }
            n2 = (n2 << 6 | (unsignedByte & 0x3F));
            if (n3 > 1) {
                final int unsignedByte2 = this._inputData.readUnsignedByte();
                if ((unsignedByte2 & 0xC0) != 0x80) {
                    this._reportInvalidOther(unsignedByte2 & 0xFF);
                }
                n2 = (n2 << 6 | (unsignedByte2 & 0x3F));
                if (n3 > 2) {
                    final int unsignedByte3 = this._inputData.readUnsignedByte();
                    if ((unsignedByte3 & 0xC0) != 0x80) {
                        this._reportInvalidOther(unsignedByte3 & 0xFF);
                    }
                    n2 = (n2 << 6 | (unsignedByte3 & 0x3F));
                }
            }
        }
        return n2;
    }
    
    private final int _decodeUtf8_2(final int n) throws IOException {
        final int unsignedByte = this._inputData.readUnsignedByte();
        if ((unsignedByte & 0xC0) != 0x80) {
            this._reportInvalidOther(unsignedByte & 0xFF);
        }
        return (n & 0x1F) << 6 | (unsignedByte & 0x3F);
    }
    
    private final int _decodeUtf8_3(int n) throws IOException {
        n &= 0xF;
        final int unsignedByte = this._inputData.readUnsignedByte();
        if ((unsignedByte & 0xC0) != 0x80) {
            this._reportInvalidOther(unsignedByte & 0xFF);
        }
        final int n2 = n << 6 | (unsignedByte & 0x3F);
        final int unsignedByte2 = this._inputData.readUnsignedByte();
        if ((unsignedByte2 & 0xC0) != 0x80) {
            this._reportInvalidOther(unsignedByte2 & 0xFF);
        }
        return n2 << 6 | (unsignedByte2 & 0x3F);
    }
    
    private final int _decodeUtf8_4(int n) throws IOException {
        final int unsignedByte = this._inputData.readUnsignedByte();
        if ((unsignedByte & 0xC0) != 0x80) {
            this._reportInvalidOther(unsignedByte & 0xFF);
        }
        n = ((n & 0x7) << 6 | (unsignedByte & 0x3F));
        final int unsignedByte2 = this._inputData.readUnsignedByte();
        if ((unsignedByte2 & 0xC0) != 0x80) {
            this._reportInvalidOther(unsignedByte2 & 0xFF);
        }
        n = (n << 6 | (unsignedByte2 & 0x3F));
        final int unsignedByte3 = this._inputData.readUnsignedByte();
        if ((unsignedByte3 & 0xC0) != 0x80) {
            this._reportInvalidOther(unsignedByte3 & 0xFF);
        }
        return (n << 6 | (unsignedByte3 & 0x3F)) - 65536;
    }
    
    private final void _skipUtf8_2() throws IOException {
        final int unsignedByte = this._inputData.readUnsignedByte();
        if ((unsignedByte & 0xC0) != 0x80) {
            this._reportInvalidOther(unsignedByte & 0xFF);
        }
    }
    
    private final void _skipUtf8_3() throws IOException {
        final int unsignedByte = this._inputData.readUnsignedByte();
        if ((unsignedByte & 0xC0) != 0x80) {
            this._reportInvalidOther(unsignedByte & 0xFF);
        }
        final int unsignedByte2 = this._inputData.readUnsignedByte();
        if ((unsignedByte2 & 0xC0) != 0x80) {
            this._reportInvalidOther(unsignedByte2 & 0xFF);
        }
    }
    
    private final void _skipUtf8_4() throws IOException {
        final int unsignedByte = this._inputData.readUnsignedByte();
        if ((unsignedByte & 0xC0) != 0x80) {
            this._reportInvalidOther(unsignedByte & 0xFF);
        }
        final int unsignedByte2 = this._inputData.readUnsignedByte();
        if ((unsignedByte2 & 0xC0) != 0x80) {
            this._reportInvalidOther(unsignedByte2 & 0xFF);
        }
        final int unsignedByte3 = this._inputData.readUnsignedByte();
        if ((unsignedByte3 & 0xC0) != 0x80) {
            this._reportInvalidOther(unsignedByte3 & 0xFF);
        }
    }
    
    protected void _reportInvalidToken(final int n, final String s) throws IOException {
        this._reportInvalidToken(n, s, "'null', 'true', 'false' or NaN");
    }
    
    protected void _reportInvalidToken(int unsignedByte, final String s, final String s2) throws IOException {
        final StringBuilder sb = new StringBuilder(s);
        while (true) {
            final char c = (char)this._decodeCharForError(unsignedByte);
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            sb.append(c);
            unsignedByte = this._inputData.readUnsignedByte();
        }
        this._reportError("Unrecognized token '" + sb.toString() + "': was expecting " + s2);
    }
    
    protected void _reportInvalidChar(final int n) throws JsonParseException {
        if (n < 32) {
            this._throwInvalidSpace(n);
        }
        this._reportInvalidInitial(n);
    }
    
    protected void _reportInvalidInitial(final int n) throws JsonParseException {
        this._reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(n));
    }
    
    private void _reportInvalidOther(final int n) throws JsonParseException {
        this._reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(n));
    }
    
    private static int[] _growArrayBy(final int[] array, final int n) {
        if (array == null) {
            return new int[n];
        }
        return Arrays.copyOf(array, array.length + n);
    }
    
    protected final byte[] _decodeBase64(final Base64Variant base64Variant) throws IOException {
        final ByteArrayBuilder getByteArrayBuilder = this._getByteArrayBuilder();
        while (true) {
            final int unsignedByte = this._inputData.readUnsignedByte();
            if (unsignedByte > 32) {
                int n = base64Variant.decodeBase64Char(unsignedByte);
                if (n < 0) {
                    if (unsignedByte == 34) {
                        return getByteArrayBuilder.toByteArray();
                    }
                    n = this._decodeBase64Escape(base64Variant, unsignedByte, 0);
                    if (n < 0) {
                        continue;
                    }
                }
                final int n2 = n;
                final int unsignedByte2 = this._inputData.readUnsignedByte();
                int n3 = base64Variant.decodeBase64Char(unsignedByte2);
                if (n3 < 0) {
                    n3 = this._decodeBase64Escape(base64Variant, unsignedByte2, 1);
                }
                final int n4 = n2 << 6 | n3;
                final int unsignedByte3 = this._inputData.readUnsignedByte();
                int n5 = base64Variant.decodeBase64Char(unsignedByte3);
                if (n5 < 0) {
                    if (n5 != -2) {
                        if (unsignedByte3 == 34 && !base64Variant.usesPadding()) {
                            getByteArrayBuilder.append(n4 >> 4);
                            return getByteArrayBuilder.toByteArray();
                        }
                        n5 = this._decodeBase64Escape(base64Variant, unsignedByte3, 2);
                    }
                    if (n5 == -2) {
                        final int unsignedByte4 = this._inputData.readUnsignedByte();
                        if (!base64Variant.usesPaddingChar(unsignedByte4)) {
                            throw this.reportInvalidBase64Char(base64Variant, unsignedByte4, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        getByteArrayBuilder.append(n4 >> 4);
                        continue;
                    }
                }
                final int n6 = n4 << 6 | n5;
                final int unsignedByte5 = this._inputData.readUnsignedByte();
                int n7 = base64Variant.decodeBase64Char(unsignedByte5);
                if (n7 < 0) {
                    if (n7 != -2) {
                        if (unsignedByte5 == 34 && !base64Variant.usesPadding()) {
                            getByteArrayBuilder.appendTwoBytes(n6 >> 2);
                            return getByteArrayBuilder.toByteArray();
                        }
                        n7 = this._decodeBase64Escape(base64Variant, unsignedByte5, 3);
                    }
                    if (n7 == -2) {
                        getByteArrayBuilder.appendTwoBytes(n6 >> 2);
                        continue;
                    }
                }
                getByteArrayBuilder.appendThreeBytes(n6 << 6 | n7);
            }
        }
    }
    
    @Override
    public JsonLocation getTokenLocation() {
        return new JsonLocation(this._getSourceReference(), -1L, -1L, this._tokenInputRow, -1);
    }
    
    @Override
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._getSourceReference(), -1L, -1L, this._currInputRow, -1);
    }
    
    private void _closeScope(final int n) throws JsonParseException {
        if (n == 93) {
            if (!this._parsingContext.inArray()) {
                this._reportMismatchedEndMarker(n, '}');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_ARRAY;
        }
        if (n == 125) {
            if (!this._parsingContext.inObject()) {
                this._reportMismatchedEndMarker(n, ']');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_OBJECT;
        }
    }
    
    private static final int pad(final int n, final int n2) {
        return (n2 == 4) ? n : (n | -1 << (n2 << 3));
    }
    
    static {
        _icUTF8 = CharTypes.getInputCodeUtf8();
        _icLatin1 = CharTypes.getInputCodeLatin1();
    }
}
