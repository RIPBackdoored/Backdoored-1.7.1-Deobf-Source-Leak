package com.fasterxml.jackson.core.json.async;

import com.fasterxml.jackson.core.async.NonBlockingInputFeeder;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.OutputStream;
import java.io.IOException;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.async.ByteArrayFeeder;

public class NonBlockingJsonParser extends NonBlockingJsonParserBase implements ByteArrayFeeder
{
    private static final int[] _icUTF8;
    protected static final int[] _icLatin1;
    protected byte[] _inputBuffer;
    protected int _origBufferLen;
    
    public NonBlockingJsonParser(final IOContext ioContext, final int n, final ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        super(ioContext, n, byteQuadsCanonicalizer);
        this._inputBuffer = NonBlockingJsonParser.NO_BYTES;
    }
    
    @Override
    public ByteArrayFeeder getNonBlockingInputFeeder() {
        return this;
    }
    
    @Override
    public final boolean needMoreInput() {
        return this._inputPtr >= this._inputEnd && !this._endOfInput;
    }
    
    @Override
    public void feedInput(final byte[] inputBuffer, final int inputPtr, final int inputEnd) throws IOException {
        if (this._inputPtr < this._inputEnd) {
            this._reportError("Still have %d undecoded bytes, should not call 'feedInput'", this._inputEnd - this._inputPtr);
        }
        if (inputEnd < inputPtr) {
            this._reportError("Input end (%d) may not be before start (%d)", inputEnd, inputPtr);
        }
        if (this._endOfInput) {
            this._reportError("Already closed, can not feed more input");
        }
        this._currInputProcessed += this._origBufferLen;
        this._currInputRowStart = inputPtr - (this._inputEnd - this._currInputRowStart);
        this._inputBuffer = inputBuffer;
        this._inputPtr = inputPtr;
        this._inputEnd = inputEnd;
        this._origBufferLen = inputEnd - inputPtr;
    }
    
    @Override
    public void endOfInput() {
        this._endOfInput = true;
    }
    
    @Override
    public int releaseBuffered(final OutputStream outputStream) throws IOException {
        final int n = this._inputEnd - this._inputPtr;
        if (n > 0) {
            outputStream.write(this._inputBuffer, this._inputPtr, n);
        }
        return n;
    }
    
    @Override
    protected char _decodeEscaped() throws IOException {
        VersionUtil.throwInternal();
        return ' ';
    }
    
    @Override
    public JsonToken nextToken() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            if (this._closed) {
                return null;
            }
            if (!this._endOfInput) {
                return JsonToken.NOT_AVAILABLE;
            }
            if (this._currToken == JsonToken.NOT_AVAILABLE) {
                return this._finishTokenWithEOF();
            }
            return this._eofAsNextToken();
        }
        else {
            if (this._currToken == JsonToken.NOT_AVAILABLE) {
                return this._finishToken();
            }
            this._numTypesValid = 0;
            this._tokenInputTotal = this._currInputProcessed + this._inputPtr;
            this._binaryValue = null;
            final int n = this._inputBuffer[this._inputPtr++] & 0xFF;
            switch (this._majorState) {
                case 0:
                    return this._startDocument(n);
                case 1:
                    return this._startValue(n);
                case 2:
                    return this._startFieldName(n);
                case 3:
                    return this._startFieldNameAfterComma(n);
                case 4:
                    return this._startValueExpectColon(n);
                case 5:
                    return this._startValue(n);
                case 6:
                    return this._startValueExpectComma(n);
                default:
                    VersionUtil.throwInternal();
                    return null;
            }
        }
    }
    
    protected final JsonToken _finishToken() throws IOException {
        switch (this._minorState) {
            case 1:
                return this._finishBOM(this._pending32);
            case 4:
                return this._startFieldName(this._inputBuffer[this._inputPtr++] & 0xFF);
            case 5:
                return this._startFieldNameAfterComma(this._inputBuffer[this._inputPtr++] & 0xFF);
            case 7:
                return this._parseEscapedName(this._quadLength, this._pending32, this._pendingBytes);
            case 8:
                return this._finishFieldWithEscape();
            case 9:
                return this._finishAposName(this._quadLength, this._pending32, this._pendingBytes);
            case 10:
                return this._finishUnquotedName(this._quadLength, this._pending32, this._pendingBytes);
            case 12:
                return this._startValue(this._inputBuffer[this._inputPtr++] & 0xFF);
            case 15:
                return this._startValueAfterComma(this._inputBuffer[this._inputPtr++] & 0xFF);
            case 13:
                return this._startValueExpectComma(this._inputBuffer[this._inputPtr++] & 0xFF);
            case 14:
                return this._startValueExpectColon(this._inputBuffer[this._inputPtr++] & 0xFF);
            case 16:
                return this._finishKeywordToken("null", this._pending32, JsonToken.VALUE_NULL);
            case 17:
                return this._finishKeywordToken("true", this._pending32, JsonToken.VALUE_TRUE);
            case 18:
                return this._finishKeywordToken("false", this._pending32, JsonToken.VALUE_FALSE);
            case 19:
                return this._finishNonStdToken(this._nonStdTokenType, this._pending32);
            case 23:
                return this._finishNumberMinus(this._inputBuffer[this._inputPtr++] & 0xFF);
            case 24:
                return this._finishNumberLeadingZeroes();
            case 25:
                return this._finishNumberLeadingNegZeroes();
            case 26:
                return this._finishNumberIntegralPart(this._textBuffer.getBufferWithoutReset(), this._textBuffer.getCurrentSegmentSize());
            case 30:
                return this._finishFloatFraction();
            case 31:
                return this._finishFloatExponent(true, this._inputBuffer[this._inputPtr++] & 0xFF);
            case 32:
                return this._finishFloatExponent(false, this._inputBuffer[this._inputPtr++] & 0xFF);
            case 40:
                return this._finishRegularString();
            case 42:
                this._textBuffer.append((char)this._decodeUTF8_2(this._pending32, this._inputBuffer[this._inputPtr++]));
                if (this._minorStateAfterSplit == 45) {
                    return this._finishAposString();
                }
                return this._finishRegularString();
            case 43:
                if (!this._decodeSplitUTF8_3(this._pending32, this._pendingBytes, this._inputBuffer[this._inputPtr++])) {
                    return JsonToken.NOT_AVAILABLE;
                }
                if (this._minorStateAfterSplit == 45) {
                    return this._finishAposString();
                }
                return this._finishRegularString();
            case 44:
                if (!this._decodeSplitUTF8_4(this._pending32, this._pendingBytes, this._inputBuffer[this._inputPtr++])) {
                    return JsonToken.NOT_AVAILABLE;
                }
                if (this._minorStateAfterSplit == 45) {
                    return this._finishAposString();
                }
                return this._finishRegularString();
            case 41: {
                final int decodeSplitEscaped = this._decodeSplitEscaped(this._quoted32, this._quotedDigits);
                if (decodeSplitEscaped < 0) {
                    return JsonToken.NOT_AVAILABLE;
                }
                this._textBuffer.append((char)decodeSplitEscaped);
                if (this._minorStateAfterSplit == 45) {
                    return this._finishAposString();
                }
                return this._finishRegularString();
            }
            case 45:
                return this._finishAposString();
            case 50:
                return this._finishErrorToken();
            case 51:
                return this._startSlashComment(this._pending32);
            case 52:
                return this._finishCComment(this._pending32, true);
            case 53:
                return this._finishCComment(this._pending32, false);
            case 54:
                return this._finishCppComment(this._pending32);
            case 55:
                return this._finishHashComment(this._pending32);
            default:
                VersionUtil.throwInternal();
                return null;
        }
    }
    
    protected final JsonToken _finishTokenWithEOF() throws IOException {
        final JsonToken currToken = this._currToken;
        switch (this._minorState) {
            case 3:
                return this._eofAsNextToken();
            case 12:
                return this._eofAsNextToken();
            case 16:
                return this._finishKeywordTokenWithEOF("null", this._pending32, JsonToken.VALUE_NULL);
            case 17:
                return this._finishKeywordTokenWithEOF("true", this._pending32, JsonToken.VALUE_TRUE);
            case 18:
                return this._finishKeywordTokenWithEOF("false", this._pending32, JsonToken.VALUE_FALSE);
            case 19:
                return this._finishNonStdTokenWithEOF(this._nonStdTokenType, this._pending32);
            case 50:
                return this._finishErrorTokenWithEOF();
            case 24:
            case 25:
                return this._valueCompleteInt(0, "0");
            case 26: {
                int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
                if (this._numberNegative) {
                    --currentSegmentSize;
                }
                this._intLength = currentSegmentSize;
                return this._valueComplete(JsonToken.VALUE_NUMBER_INT);
            }
            case 30:
                this._expLength = 0;
            case 32:
                return this._valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
            case 31:
                this._reportInvalidEOF(": was expecting fraction after exponent marker", JsonToken.VALUE_NUMBER_FLOAT);
            case 52:
            case 53:
                this._reportInvalidEOF(": was expecting closing '*/' for comment", JsonToken.NOT_AVAILABLE);
            case 54:
            case 55:
                return this._eofAsNextToken();
            default:
                this._reportInvalidEOF(": was expecting rest of token (internal state: " + this._minorState + ")", this._currToken);
                return currToken;
        }
    }
    
    private final JsonToken _startDocument(int i) throws IOException {
        i &= 0xFF;
        if (i == 239 && this._minorState != 1) {
            return this._finishBOM(1);
        }
        while (i <= 32) {
            if (i != 32) {
                if (i == 10) {
                    ++this._currInputRow;
                    this._currInputRowStart = this._inputPtr;
                }
                else if (i == 13) {
                    ++this._currInputRowAlt;
                    this._currInputRowStart = this._inputPtr;
                }
                else if (i != 9) {
                    this._throwInvalidSpace(i);
                }
            }
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 3;
                if (this._closed) {
                    return null;
                }
                if (this._endOfInput) {
                    return this._eofAsNextToken();
                }
                return JsonToken.NOT_AVAILABLE;
            }
            else {
                i = (this._inputBuffer[this._inputPtr++] & 0xFF);
            }
        }
        return this._startValue(i);
    }
    
    private final JsonToken _finishBOM(int pending32) throws IOException {
        while (this._inputPtr < this._inputEnd) {
            final int n = this._inputBuffer[this._inputPtr++] & 0xFF;
            switch (pending32) {
                case 3:
                    this._currInputProcessed -= 3L;
                    return this._startDocument(n);
                case 2:
                    if (n != 191) {
                        this._reportError("Unexpected byte 0x%02x following 0xEF 0xBB; should get 0xBF as third byte of UTF-8 BOM", n);
                        break;
                    }
                    break;
                case 1:
                    if (n != 187) {
                        this._reportError("Unexpected byte 0x%02x following 0xEF; should get 0xBB as second byte UTF-8 BOM", n);
                        break;
                    }
                    break;
            }
            ++pending32;
        }
        this._pending32 = pending32;
        this._minorState = 1;
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    private final JsonToken _startFieldName(int skipWS) throws IOException {
        if (skipWS <= 32) {
            skipWS = this._skipWS(skipWS);
            if (skipWS <= 0) {
                this._minorState = 4;
                return this._currToken;
            }
        }
        this._updateTokenLocation();
        if (skipWS == 34) {
            if (this._inputPtr + 13 <= this._inputEnd) {
                final String fastParseName = this._fastParseName();
                if (fastParseName != null) {
                    return this._fieldComplete(fastParseName);
                }
            }
            return this._parseEscapedName(0, 0, 0);
        }
        if (skipWS == 125) {
            return this._closeObjectScope();
        }
        return this._handleOddName(skipWS);
    }
    
    private final JsonToken _startFieldNameAfterComma(int n) throws IOException {
        if (n <= 32) {
            n = this._skipWS(n);
            if (n <= 0) {
                this._minorState = 5;
                return this._currToken;
            }
        }
        if (n != 44) {
            if (n == 125) {
                return this._closeObjectScope();
            }
            if (n == 35) {
                return this._finishHashComment(5);
            }
            if (n == 47) {
                return this._startSlashComment(5);
            }
            this._reportUnexpectedChar(n, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
        }
        final int inputPtr = this._inputPtr;
        if (inputPtr >= this._inputEnd) {
            this._minorState = 4;
            return this._currToken = JsonToken.NOT_AVAILABLE;
        }
        n = this._inputBuffer[inputPtr];
        this._inputPtr = inputPtr + 1;
        if (n <= 32) {
            n = this._skipWS(n);
            if (n <= 0) {
                this._minorState = 4;
                return this._currToken;
            }
        }
        this._updateTokenLocation();
        if (n == 34) {
            if (this._inputPtr + 13 <= this._inputEnd) {
                final String fastParseName = this._fastParseName();
                if (fastParseName != null) {
                    return this._fieldComplete(fastParseName);
                }
            }
            return this._parseEscapedName(0, 0, 0);
        }
        if (n == 125 && Feature.ALLOW_TRAILING_COMMA.enabledIn(this._features)) {
            return this._closeObjectScope();
        }
        return this._handleOddName(n);
    }
    
    private final JsonToken _startValue(int skipWS) throws IOException {
        if (skipWS <= 32) {
            skipWS = this._skipWS(skipWS);
            if (skipWS <= 0) {
                this._minorState = 12;
                return this._currToken;
            }
        }
        this._updateTokenLocation();
        if (skipWS == 34) {
            return this._startString();
        }
        switch (skipWS) {
            case 35:
                return this._finishHashComment(12);
            case 45:
                return this._startNegativeNumber();
            case 47:
                return this._startSlashComment(12);
            case 48:
                return this._startNumberLeadingZero();
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                return this._startPositiveNumber(skipWS);
            case 102:
                return this._startFalseToken();
            case 110:
                return this._startNullToken();
            case 116:
                return this._startTrueToken();
            case 91:
                return this._startArrayScope();
            case 93:
                return this._closeArrayScope();
            case 123:
                return this._startObjectScope();
            case 125:
                return this._closeObjectScope();
            default:
                return this._startUnexpectedValue(false, skipWS);
        }
    }
    
    private final JsonToken _startValueExpectComma(int n) throws IOException {
        if (n <= 32) {
            n = this._skipWS(n);
            if (n <= 0) {
                this._minorState = 13;
                return this._currToken;
            }
        }
        if (n != 44) {
            if (n == 93) {
                return this._closeArrayScope();
            }
            if (n == 125) {
                return this._closeObjectScope();
            }
            if (n == 47) {
                return this._startSlashComment(13);
            }
            if (n == 35) {
                return this._finishHashComment(13);
            }
            this._reportUnexpectedChar(n, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
        }
        final int inputPtr = this._inputPtr;
        if (inputPtr >= this._inputEnd) {
            this._minorState = 15;
            return this._currToken = JsonToken.NOT_AVAILABLE;
        }
        n = this._inputBuffer[inputPtr];
        this._inputPtr = inputPtr + 1;
        if (n <= 32) {
            n = this._skipWS(n);
            if (n <= 0) {
                this._minorState = 15;
                return this._currToken;
            }
        }
        this._updateTokenLocation();
        if (n == 34) {
            return this._startString();
        }
        switch (n) {
            case 35:
                return this._finishHashComment(15);
            case 45:
                return this._startNegativeNumber();
            case 47:
                return this._startSlashComment(15);
            case 48:
                return this._startNumberLeadingZero();
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                return this._startPositiveNumber(n);
            case 102:
                return this._startFalseToken();
            case 110:
                return this._startNullToken();
            case 116:
                return this._startTrueToken();
            case 91:
                return this._startArrayScope();
            case 93:
                if (this.isEnabled(Feature.ALLOW_TRAILING_COMMA)) {
                    return this._closeArrayScope();
                }
                break;
            case 123:
                return this._startObjectScope();
            case 125:
                if (this.isEnabled(Feature.ALLOW_TRAILING_COMMA)) {
                    return this._closeObjectScope();
                }
                break;
        }
        return this._startUnexpectedValue(true, n);
    }
    
    private final JsonToken _startValueExpectColon(int n) throws IOException {
        if (n <= 32) {
            n = this._skipWS(n);
            if (n <= 0) {
                this._minorState = 14;
                return this._currToken;
            }
        }
        if (n != 58) {
            if (n == 47) {
                return this._startSlashComment(14);
            }
            if (n == 35) {
                return this._finishHashComment(14);
            }
            this._reportUnexpectedChar(n, "was expecting a colon to separate field name and value");
        }
        final int inputPtr = this._inputPtr;
        if (inputPtr >= this._inputEnd) {
            this._minorState = 12;
            return this._currToken = JsonToken.NOT_AVAILABLE;
        }
        n = this._inputBuffer[inputPtr];
        this._inputPtr = inputPtr + 1;
        if (n <= 32) {
            n = this._skipWS(n);
            if (n <= 0) {
                this._minorState = 12;
                return this._currToken;
            }
        }
        this._updateTokenLocation();
        if (n == 34) {
            return this._startString();
        }
        switch (n) {
            case 35:
                return this._finishHashComment(12);
            case 45:
                return this._startNegativeNumber();
            case 47:
                return this._startSlashComment(12);
            case 48:
                return this._startNumberLeadingZero();
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                return this._startPositiveNumber(n);
            case 102:
                return this._startFalseToken();
            case 110:
                return this._startNullToken();
            case 116:
                return this._startTrueToken();
            case 91:
                return this._startArrayScope();
            case 123:
                return this._startObjectScope();
            default:
                return this._startUnexpectedValue(false, n);
        }
    }
    
    private final JsonToken _startValueAfterComma(int skipWS) throws IOException {
        if (skipWS <= 32) {
            skipWS = this._skipWS(skipWS);
            if (skipWS <= 0) {
                this._minorState = 15;
                return this._currToken;
            }
        }
        this._updateTokenLocation();
        if (skipWS == 34) {
            return this._startString();
        }
        switch (skipWS) {
            case 35:
                return this._finishHashComment(15);
            case 45:
                return this._startNegativeNumber();
            case 47:
                return this._startSlashComment(15);
            case 48:
                return this._startNumberLeadingZero();
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                return this._startPositiveNumber(skipWS);
            case 102:
                return this._startFalseToken();
            case 110:
                return this._startNullToken();
            case 116:
                return this._startTrueToken();
            case 91:
                return this._startArrayScope();
            case 93:
                if (this.isEnabled(Feature.ALLOW_TRAILING_COMMA)) {
                    return this._closeArrayScope();
                }
                break;
            case 123:
                return this._startObjectScope();
            case 125:
                if (this.isEnabled(Feature.ALLOW_TRAILING_COMMA)) {
                    return this._closeObjectScope();
                }
                break;
        }
        return this._startUnexpectedValue(true, skipWS);
    }
    
    protected JsonToken _startUnexpectedValue(final boolean b, final int n) throws IOException {
        switch (n) {
            case 93:
                if (!this._parsingContext.inArray()) {
                    break;
                }
            case 44:
                if (this.isEnabled(Feature.ALLOW_MISSING_VALUES)) {
                    --this._inputPtr;
                    return this._valueComplete(JsonToken.VALUE_NULL);
                }
                break;
            case 39:
                if (this.isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
                    return this._startAposString();
                }
                break;
            case 43:
                return this._finishNonStdToken(2, 1);
            case 78:
                return this._finishNonStdToken(0, 1);
            case 73:
                return this._finishNonStdToken(1, 1);
        }
        this._reportUnexpectedChar(n, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }
    
    private final int _skipWS(int i) throws IOException {
        do {
            if (i != 32) {
                if (i == 10) {
                    ++this._currInputRow;
                    this._currInputRowStart = this._inputPtr;
                }
                else if (i == 13) {
                    ++this._currInputRowAlt;
                    this._currInputRowStart = this._inputPtr;
                }
                else if (i != 9) {
                    this._throwInvalidSpace(i);
                }
            }
            if (this._inputPtr >= this._inputEnd) {
                this._currToken = JsonToken.NOT_AVAILABLE;
                return 0;
            }
            i = (this._inputBuffer[this._inputPtr++] & 0xFF);
        } while (i <= 32);
        return i;
    }
    
    private final JsonToken _startSlashComment(final int pending32) throws IOException {
        if (!Feature.ALLOW_COMMENTS.enabledIn(this._features)) {
            this._reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd) {
            this._pending32 = pending32;
            this._minorState = 51;
            return this._currToken = JsonToken.NOT_AVAILABLE;
        }
        final byte b = this._inputBuffer[this._inputPtr++];
        if (b == 42) {
            return this._finishCComment(pending32, false);
        }
        if (b == 47) {
            return this._finishCppComment(pending32);
        }
        this._reportUnexpectedChar(b & 0xFF, "was expecting either '*' or '/' for a comment");
        return null;
    }
    
    private final JsonToken _finishHashComment(final int pending32) throws IOException {
        if (!Feature.ALLOW_YAML_COMMENTS.enabledIn(this._features)) {
            this._reportUnexpectedChar(35, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_YAML_COMMENTS' not enabled for parser)");
        }
        while (this._inputPtr < this._inputEnd) {
            final int n = this._inputBuffer[this._inputPtr++] & 0xFF;
            if (n < 32) {
                if (n == 10) {
                    ++this._currInputRow;
                    this._currInputRowStart = this._inputPtr;
                }
                else if (n == 13) {
                    ++this._currInputRowAlt;
                    this._currInputRowStart = this._inputPtr;
                }
                else {
                    if (n != 9) {
                        this._throwInvalidSpace(n);
                        continue;
                    }
                    continue;
                }
                return this._startAfterComment(pending32);
            }
        }
        this._minorState = 55;
        this._pending32 = pending32;
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    private final JsonToken _finishCppComment(final int pending32) throws IOException {
        while (this._inputPtr < this._inputEnd) {
            final int n = this._inputBuffer[this._inputPtr++] & 0xFF;
            if (n < 32) {
                if (n == 10) {
                    ++this._currInputRow;
                    this._currInputRowStart = this._inputPtr;
                }
                else if (n == 13) {
                    ++this._currInputRowAlt;
                    this._currInputRowStart = this._inputPtr;
                }
                else {
                    if (n != 9) {
                        this._throwInvalidSpace(n);
                        continue;
                    }
                    continue;
                }
                return this._startAfterComment(pending32);
            }
        }
        this._minorState = 54;
        this._pending32 = pending32;
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    private final JsonToken _finishCComment(final int pending32, boolean b) throws IOException {
        while (this._inputPtr < this._inputEnd) {
            final int n = this._inputBuffer[this._inputPtr++] & 0xFF;
            if (n < 32) {
                if (n == 10) {
                    ++this._currInputRow;
                    this._currInputRowStart = this._inputPtr;
                }
                else if (n == 13) {
                    ++this._currInputRowAlt;
                    this._currInputRowStart = this._inputPtr;
                }
                else if (n != 9) {
                    this._throwInvalidSpace(n);
                }
            }
            else {
                if (n == 42) {
                    b = true;
                    continue;
                }
                if (n == 47 && b) {
                    return this._startAfterComment(pending32);
                }
            }
            b = false;
        }
        this._minorState = (b ? 52 : 53);
        this._pending32 = pending32;
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    private final JsonToken _startAfterComment(final int minorState) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            this._minorState = minorState;
            return this._currToken = JsonToken.NOT_AVAILABLE;
        }
        final int n = this._inputBuffer[this._inputPtr++] & 0xFF;
        switch (minorState) {
            case 4:
                return this._startFieldName(n);
            case 5:
                return this._startFieldNameAfterComma(n);
            case 12:
                return this._startValue(n);
            case 13:
                return this._startValueExpectComma(n);
            case 14:
                return this._startValueExpectColon(n);
            case 15:
                return this._startValueAfterComma(n);
            default:
                VersionUtil.throwInternal();
                return null;
        }
    }
    
    protected JsonToken _startFalseToken() throws IOException {
        int inputPtr = this._inputPtr;
        if (inputPtr + 4 < this._inputEnd) {
            final byte[] inputBuffer = this._inputBuffer;
            if (inputBuffer[inputPtr++] == 97 && inputBuffer[inputPtr++] == 108 && inputBuffer[inputPtr++] == 115 && inputBuffer[inputPtr++] == 101) {
                final int n = inputBuffer[inputPtr] & 0xFF;
                if (n < 48 || n == 93 || n == 125) {
                    this._inputPtr = inputPtr;
                    return this._valueComplete(JsonToken.VALUE_FALSE);
                }
            }
        }
        this._minorState = 18;
        return this._finishKeywordToken("false", 1, JsonToken.VALUE_FALSE);
    }
    
    protected JsonToken _startTrueToken() throws IOException {
        int inputPtr = this._inputPtr;
        if (inputPtr + 3 < this._inputEnd) {
            final byte[] inputBuffer = this._inputBuffer;
            if (inputBuffer[inputPtr++] == 114 && inputBuffer[inputPtr++] == 117 && inputBuffer[inputPtr++] == 101) {
                final int n = inputBuffer[inputPtr] & 0xFF;
                if (n < 48 || n == 93 || n == 125) {
                    this._inputPtr = inputPtr;
                    return this._valueComplete(JsonToken.VALUE_TRUE);
                }
            }
        }
        this._minorState = 17;
        return this._finishKeywordToken("true", 1, JsonToken.VALUE_TRUE);
    }
    
    protected JsonToken _startNullToken() throws IOException {
        int inputPtr = this._inputPtr;
        if (inputPtr + 3 < this._inputEnd) {
            final byte[] inputBuffer = this._inputBuffer;
            if (inputBuffer[inputPtr++] == 117 && inputBuffer[inputPtr++] == 108 && inputBuffer[inputPtr++] == 108) {
                final int n = inputBuffer[inputPtr] & 0xFF;
                if (n < 48 || n == 93 || n == 125) {
                    this._inputPtr = inputPtr;
                    return this._valueComplete(JsonToken.VALUE_NULL);
                }
            }
        }
        this._minorState = 16;
        return this._finishKeywordToken("null", 1, JsonToken.VALUE_NULL);
    }
    
    protected JsonToken _finishKeywordToken(final String s, int pending32, final JsonToken jsonToken) throws IOException {
        final int length = s.length();
        while (this._inputPtr < this._inputEnd) {
            final byte b = this._inputBuffer[this._inputPtr];
            if (pending32 == length) {
                if (b < 48 || b == 93 || b == 125) {
                    return this._valueComplete(jsonToken);
                }
            }
            else if (b == s.charAt(pending32)) {
                ++pending32;
                ++this._inputPtr;
                continue;
            }
            this._minorState = 50;
            this._textBuffer.resetWithCopy(s, 0, pending32);
            return this._finishErrorToken();
        }
        this._pending32 = pending32;
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    protected JsonToken _finishKeywordTokenWithEOF(final String s, final int n, final JsonToken currToken) throws IOException {
        if (n == s.length()) {
            return this._currToken = currToken;
        }
        this._textBuffer.resetWithCopy(s, 0, n);
        return this._finishErrorTokenWithEOF();
    }
    
    protected JsonToken _finishNonStdToken(final int nonStdTokenType, int pending32) throws IOException {
        final String nonStdToken = this._nonStdToken(nonStdTokenType);
        final int length = nonStdToken.length();
        while (this._inputPtr < this._inputEnd) {
            final byte b = this._inputBuffer[this._inputPtr];
            if (pending32 == length) {
                if (b < 48 || b == 93 || b == 125) {
                    return this._valueNonStdNumberComplete(nonStdTokenType);
                }
            }
            else if (b == nonStdToken.charAt(pending32)) {
                ++pending32;
                ++this._inputPtr;
                continue;
            }
            this._minorState = 50;
            this._textBuffer.resetWithCopy(nonStdToken, 0, pending32);
            return this._finishErrorToken();
        }
        this._nonStdTokenType = nonStdTokenType;
        this._pending32 = pending32;
        this._minorState = 19;
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    protected JsonToken _finishNonStdTokenWithEOF(final int n, final int n2) throws IOException {
        final String nonStdToken = this._nonStdToken(n);
        if (n2 == nonStdToken.length()) {
            return this._valueNonStdNumberComplete(n);
        }
        this._textBuffer.resetWithCopy(nonStdToken, 0, n2);
        return this._finishErrorTokenWithEOF();
    }
    
    protected JsonToken _finishErrorToken() throws IOException {
        while (this._inputPtr < this._inputEnd) {
            final char c = (char)this._inputBuffer[this._inputPtr++];
            if (Character.isJavaIdentifierPart(c)) {
                this._textBuffer.append(c);
                if (this._textBuffer.size() < 256) {
                    continue;
                }
            }
            return this._reportErrorToken(this._textBuffer.contentsAsString());
        }
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    protected JsonToken _finishErrorTokenWithEOF() throws IOException {
        return this._reportErrorToken(this._textBuffer.contentsAsString());
    }
    
    protected JsonToken _reportErrorToken(final String s) throws IOException {
        this._reportError("Unrecognized token '%s': was expecting %s", this._textBuffer.contentsAsString(), "'null', 'true' or 'false'");
        return JsonToken.NOT_AVAILABLE;
    }
    
    protected JsonToken _startPositiveNumber(int i) throws IOException {
        this._numberNegative = false;
        char[] array = this._textBuffer.emptyAndGetCurrentSegment();
        array[0] = (char)i;
        if (this._inputPtr >= this._inputEnd) {
            this._minorState = 26;
            this._textBuffer.setCurrentLength(1);
            return this._currToken = JsonToken.NOT_AVAILABLE;
        }
        int intLength = 1;
        i = (this._inputBuffer[this._inputPtr] & 0xFF);
        while (true) {
            while (i >= 48) {
                if (i > 57) {
                    if (i == 101 || i == 69) {
                        this._intLength = intLength;
                        ++this._inputPtr;
                        return this._startFloat(array, intLength, i);
                    }
                    this._intLength = intLength;
                    this._textBuffer.setCurrentLength(intLength);
                    return this._valueComplete(JsonToken.VALUE_NUMBER_INT);
                }
                else {
                    if (intLength >= array.length) {
                        array = this._textBuffer.expandCurrentSegment();
                    }
                    array[intLength++] = (char)i;
                    if (++this._inputPtr >= this._inputEnd) {
                        this._minorState = 26;
                        this._textBuffer.setCurrentLength(intLength);
                        return this._currToken = JsonToken.NOT_AVAILABLE;
                    }
                    i = (this._inputBuffer[this._inputPtr] & 0xFF);
                }
            }
            if (i == 46) {
                this._intLength = intLength;
                ++this._inputPtr;
                return this._startFloat(array, intLength, i);
            }
            continue;
        }
    }
    
    protected JsonToken _startNegativeNumber() throws IOException {
        this._numberNegative = true;
        if (this._inputPtr >= this._inputEnd) {
            this._minorState = 23;
            return this._currToken = JsonToken.NOT_AVAILABLE;
        }
        final int n = this._inputBuffer[this._inputPtr++] & 0xFF;
        if (n <= 48) {
            if (n == 48) {
                return this._finishNumberLeadingNegZeroes();
            }
            this.reportUnexpectedNumberChar(n, "expected digit (0-9) to follow minus sign, for valid numeric value");
        }
        else if (n > 57) {
            if (n == 73) {
                return this._finishNonStdToken(3, 2);
            }
            this.reportUnexpectedNumberChar(n, "expected digit (0-9) to follow minus sign, for valid numeric value");
        }
        char[] array = this._textBuffer.emptyAndGetCurrentSegment();
        array[0] = '-';
        array[1] = (char)n;
        if (this._inputPtr >= this._inputEnd) {
            this._minorState = 26;
            this._textBuffer.setCurrentLength(2);
            this._intLength = 1;
            return this._currToken = JsonToken.NOT_AVAILABLE;
        }
        int i = this._inputBuffer[this._inputPtr];
        int n2 = 2;
        while (true) {
            while (i >= 48) {
                if (i > 57) {
                    if (i == 101 || i == 69) {
                        this._intLength = n2 - 1;
                        ++this._inputPtr;
                        return this._startFloat(array, n2, i);
                    }
                    this._intLength = n2 - 1;
                    this._textBuffer.setCurrentLength(n2);
                    return this._valueComplete(JsonToken.VALUE_NUMBER_INT);
                }
                else {
                    if (n2 >= array.length) {
                        array = this._textBuffer.expandCurrentSegment();
                    }
                    array[n2++] = (char)i;
                    if (++this._inputPtr >= this._inputEnd) {
                        this._minorState = 26;
                        this._textBuffer.setCurrentLength(n2);
                        return this._currToken = JsonToken.NOT_AVAILABLE;
                    }
                    i = (this._inputBuffer[this._inputPtr] & 0xFF);
                }
            }
            if (i == 46) {
                this._intLength = n2 - 1;
                ++this._inputPtr;
                return this._startFloat(array, n2, i);
            }
            continue;
        }
    }
    
    protected JsonToken _startNumberLeadingZero() throws IOException {
        int inputPtr = this._inputPtr;
        if (inputPtr >= this._inputEnd) {
            this._minorState = 24;
            return this._currToken = JsonToken.NOT_AVAILABLE;
        }
        final int n = this._inputBuffer[inputPtr++] & 0xFF;
        if (n < 48) {
            if (n == 46) {
                this._inputPtr = inputPtr;
                this._intLength = 1;
                final char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
                emptyAndGetCurrentSegment[0] = '0';
                return this._startFloat(emptyAndGetCurrentSegment, 1, n);
            }
        }
        else {
            if (n <= 57) {
                return this._finishNumberLeadingZeroes();
            }
            if (n == 101 || n == 69) {
                this._inputPtr = inputPtr;
                this._intLength = 1;
                final char[] emptyAndGetCurrentSegment2 = this._textBuffer.emptyAndGetCurrentSegment();
                emptyAndGetCurrentSegment2[0] = '0';
                return this._startFloat(emptyAndGetCurrentSegment2, 1, n);
            }
            if (n != 93 && n != 125) {
                this.reportUnexpectedNumberChar(n, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'");
            }
        }
        return this._valueCompleteInt(0, "0");
    }
    
    protected JsonToken _finishNumberMinus(final int n) throws IOException {
        if (n <= 48) {
            if (n == 48) {
                return this._finishNumberLeadingNegZeroes();
            }
            this.reportUnexpectedNumberChar(n, "expected digit (0-9) to follow minus sign, for valid numeric value");
        }
        else if (n > 57) {
            if (n == 73) {
                return this._finishNonStdToken(3, 2);
            }
            this.reportUnexpectedNumberChar(n, "expected digit (0-9) to follow minus sign, for valid numeric value");
        }
        final char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment[0] = '-';
        emptyAndGetCurrentSegment[1] = (char)n;
        this._intLength = 1;
        return this._finishNumberIntegralPart(emptyAndGetCurrentSegment, 2);
    }
    
    protected JsonToken _finishNumberLeadingZeroes() throws IOException {
        while (this._inputPtr < this._inputEnd) {
            final int n = this._inputBuffer[this._inputPtr++] & 0xFF;
            if (n < 48) {
                if (n == 46) {
                    final char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
                    emptyAndGetCurrentSegment[0] = '0';
                    this._intLength = 1;
                    return this._startFloat(emptyAndGetCurrentSegment, 1, n);
                }
            }
            else if (n > 57) {
                if (n == 101 || n == 69) {
                    final char[] emptyAndGetCurrentSegment2 = this._textBuffer.emptyAndGetCurrentSegment();
                    emptyAndGetCurrentSegment2[0] = '0';
                    this._intLength = 1;
                    return this._startFloat(emptyAndGetCurrentSegment2, 1, n);
                }
                if (n != 93 && n != 125) {
                    this.reportUnexpectedNumberChar(n, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'");
                }
            }
            else {
                if (!this.isEnabled(Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
                    this.reportInvalidNumber("Leading zeroes not allowed");
                }
                if (n == 48) {
                    continue;
                }
                final char[] emptyAndGetCurrentSegment3 = this._textBuffer.emptyAndGetCurrentSegment();
                emptyAndGetCurrentSegment3[0] = (char)n;
                this._intLength = 1;
                return this._finishNumberIntegralPart(emptyAndGetCurrentSegment3, 1);
            }
            --this._inputPtr;
            return this._valueCompleteInt(0, "0");
        }
        this._minorState = 24;
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    protected JsonToken _finishNumberLeadingNegZeroes() throws IOException {
        while (this._inputPtr < this._inputEnd) {
            final int n = this._inputBuffer[this._inputPtr++] & 0xFF;
            if (n < 48) {
                if (n == 46) {
                    final char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
                    emptyAndGetCurrentSegment[0] = '-';
                    emptyAndGetCurrentSegment[1] = '0';
                    this._intLength = 1;
                    return this._startFloat(emptyAndGetCurrentSegment, 2, n);
                }
            }
            else if (n > 57) {
                if (n == 101 || n == 69) {
                    final char[] emptyAndGetCurrentSegment2 = this._textBuffer.emptyAndGetCurrentSegment();
                    emptyAndGetCurrentSegment2[0] = '-';
                    emptyAndGetCurrentSegment2[1] = '0';
                    this._intLength = 1;
                    return this._startFloat(emptyAndGetCurrentSegment2, 2, n);
                }
                if (n != 93 && n != 125) {
                    this.reportUnexpectedNumberChar(n, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'");
                }
            }
            else {
                if (!this.isEnabled(Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
                    this.reportInvalidNumber("Leading zeroes not allowed");
                }
                if (n == 48) {
                    continue;
                }
                final char[] emptyAndGetCurrentSegment3 = this._textBuffer.emptyAndGetCurrentSegment();
                emptyAndGetCurrentSegment3[0] = '-';
                emptyAndGetCurrentSegment3[1] = (char)n;
                this._intLength = 1;
                return this._finishNumberIntegralPart(emptyAndGetCurrentSegment3, 2);
            }
            --this._inputPtr;
            return this._valueCompleteInt(0, "0");
        }
        this._minorState = 25;
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    protected JsonToken _finishNumberIntegralPart(char[] expandCurrentSegment, int n) throws IOException {
        final int n2 = this._numberNegative ? -1 : 0;
        while (this._inputPtr < this._inputEnd) {
            final int n3 = this._inputBuffer[this._inputPtr] & 0xFF;
            if (n3 < 48) {
                if (n3 == 46) {
                    this._intLength = n + n2;
                    ++this._inputPtr;
                    return this._startFloat(expandCurrentSegment, n, n3);
                }
            }
            else {
                if (n3 <= 57) {
                    ++this._inputPtr;
                    if (n >= expandCurrentSegment.length) {
                        expandCurrentSegment = this._textBuffer.expandCurrentSegment();
                    }
                    expandCurrentSegment[n++] = (char)n3;
                    continue;
                }
                if (n3 == 101 || n3 == 69) {
                    this._intLength = n + n2;
                    ++this._inputPtr;
                    return this._startFloat(expandCurrentSegment, n, n3);
                }
            }
            this._intLength = n + n2;
            this._textBuffer.setCurrentLength(n);
            return this._valueComplete(JsonToken.VALUE_NUMBER_INT);
        }
        this._minorState = 26;
        this._textBuffer.setCurrentLength(n);
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    protected JsonToken _startFloat(char[] array, int currentLength, int n) throws IOException {
        int n2 = 0;
        Label_0145: {
            if (n == 46) {
                if (currentLength >= array.length) {
                    array = this._textBuffer.expandCurrentSegment();
                }
                array[currentLength++] = '.';
                while (this._inputPtr < this._inputEnd) {
                    n = this._inputBuffer[this._inputPtr++];
                    if (n < 48 || n > 57) {
                        n &= 0xFF;
                        this.reportUnexpectedNumberChar(n, "Decimal point not followed by a digit");
                        break Label_0145;
                    }
                    if (currentLength >= array.length) {
                        array = this._textBuffer.expandCurrentSegment();
                    }
                    array[currentLength++] = (char)n;
                    ++n2;
                }
                this._textBuffer.setCurrentLength(currentLength);
                this._minorState = 30;
                this._fractLength = n2;
                return this._currToken = JsonToken.NOT_AVAILABLE;
            }
        }
        this._fractLength = n2;
        int n3 = 0;
        if (n == 101 || n == 69) {
            if (currentLength >= array.length) {
                array = this._textBuffer.expandCurrentSegment();
            }
            array[currentLength++] = (char)n;
            if (this._inputPtr >= this._inputEnd) {
                this._textBuffer.setCurrentLength(currentLength);
                this._minorState = 31;
                this._expLength = 0;
                return this._currToken = JsonToken.NOT_AVAILABLE;
            }
            n = this._inputBuffer[this._inputPtr++];
            if (n == 45 || n == 43) {
                if (currentLength >= array.length) {
                    array = this._textBuffer.expandCurrentSegment();
                }
                array[currentLength++] = (char)n;
                if (this._inputPtr >= this._inputEnd) {
                    this._textBuffer.setCurrentLength(currentLength);
                    this._minorState = 32;
                    this._expLength = 0;
                    return this._currToken = JsonToken.NOT_AVAILABLE;
                }
                n = this._inputBuffer[this._inputPtr++];
            }
            while (n >= 48 && n <= 57) {
                ++n3;
                if (currentLength >= array.length) {
                    array = this._textBuffer.expandCurrentSegment();
                }
                array[currentLength++] = (char)n;
                if (this._inputPtr >= this._inputEnd) {
                    this._textBuffer.setCurrentLength(currentLength);
                    this._minorState = 32;
                    this._expLength = n3;
                    return this._currToken = JsonToken.NOT_AVAILABLE;
                }
                n = this._inputBuffer[this._inputPtr++];
            }
            n &= 0xFF;
            this.reportUnexpectedNumberChar(n, "Exponent indicator not followed by a digit");
        }
        --this._inputPtr;
        this._textBuffer.setCurrentLength(currentLength);
        this._expLength = n3;
        return this._valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
    }
    
    protected JsonToken _finishFloatFraction() throws IOException {
        int fractLength = this._fractLength;
        char[] array = this._textBuffer.getBufferWithoutReset();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        byte b;
        while ((b = this._inputBuffer[this._inputPtr++]) >= 48 && b <= 57) {
            ++fractLength;
            if (currentSegmentSize >= array.length) {
                array = this._textBuffer.expandCurrentSegment();
            }
            array[currentSegmentSize++] = (char)b;
            if (this._inputPtr >= this._inputEnd) {
                this._textBuffer.setCurrentLength(currentSegmentSize);
                this._fractLength = fractLength;
                return JsonToken.NOT_AVAILABLE;
            }
        }
        this.reportUnexpectedNumberChar(b, "Decimal point not followed by a digit");
        this._fractLength = fractLength;
        this._textBuffer.setCurrentLength(currentSegmentSize);
        if (b != 101 && b != 69) {
            --this._inputPtr;
            this._textBuffer.setCurrentLength(currentSegmentSize);
            this._expLength = 0;
            return this._valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
        }
        this._textBuffer.append((char)b);
        this._expLength = 0;
        if (this._inputPtr >= this._inputEnd) {
            this._minorState = 31;
            return JsonToken.NOT_AVAILABLE;
        }
        this._minorState = 32;
        return this._finishFloatExponent(true, this._inputBuffer[this._inputPtr++] & 0xFF);
    }
    
    protected JsonToken _finishFloatExponent(final boolean b, int n) throws IOException {
        if (b) {
            this._minorState = 32;
            if (n == 45 || n == 43) {
                this._textBuffer.append((char)n);
                if (this._inputPtr >= this._inputEnd) {
                    this._minorState = 32;
                    this._expLength = 0;
                    return JsonToken.NOT_AVAILABLE;
                }
                n = this._inputBuffer[this._inputPtr++];
            }
        }
        char[] array = this._textBuffer.getBufferWithoutReset();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        int expLength = this._expLength;
        while (n >= 48 && n <= 57) {
            ++expLength;
            if (currentSegmentSize >= array.length) {
                array = this._textBuffer.expandCurrentSegment();
            }
            array[currentSegmentSize++] = (char)n;
            if (this._inputPtr >= this._inputEnd) {
                this._textBuffer.setCurrentLength(currentSegmentSize);
                this._expLength = expLength;
                return JsonToken.NOT_AVAILABLE;
            }
            n = this._inputBuffer[this._inputPtr++];
        }
        n &= 0xFF;
        this.reportUnexpectedNumberChar(n, "Exponent indicator not followed by a digit");
        --this._inputPtr;
        this._textBuffer.setCurrentLength(currentSegmentSize);
        this._expLength = expLength;
        return this._valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
    }
    
    private final String _fastParseName() throws IOException {
        final byte[] inputBuffer = this._inputBuffer;
        final int[] icLatin1 = NonBlockingJsonParser._icLatin1;
        int inputPtr = this._inputPtr;
        final int n = inputBuffer[inputPtr++] & 0xFF;
        if (icLatin1[n] == 0) {
            final int n2 = inputBuffer[inputPtr++] & 0xFF;
            if (icLatin1[n2] == 0) {
                final int n3 = n << 8 | n2;
                final int n4 = inputBuffer[inputPtr++] & 0xFF;
                if (icLatin1[n4] == 0) {
                    final int n5 = n3 << 8 | n4;
                    final int n6 = inputBuffer[inputPtr++] & 0xFF;
                    if (icLatin1[n6] == 0) {
                        final int quad1 = n5 << 8 | n6;
                        final int n7 = inputBuffer[inputPtr++] & 0xFF;
                        if (icLatin1[n7] == 0) {
                            this._quad1 = quad1;
                            return this._parseMediumName(inputPtr, n7);
                        }
                        if (n7 == 34) {
                            this._inputPtr = inputPtr;
                            return this._findName(quad1, 4);
                        }
                        return null;
                    }
                    else {
                        if (n6 == 34) {
                            this._inputPtr = inputPtr;
                            return this._findName(n5, 3);
                        }
                        return null;
                    }
                }
                else {
                    if (n4 == 34) {
                        this._inputPtr = inputPtr;
                        return this._findName(n3, 2);
                    }
                    return null;
                }
            }
            else {
                if (n2 == 34) {
                    this._inputPtr = inputPtr;
                    return this._findName(n, 1);
                }
                return null;
            }
        }
        else {
            if (n == 34) {
                this._inputPtr = inputPtr;
                return "";
            }
            return null;
        }
    }
    
    private final String _parseMediumName(int n, int n2) throws IOException {
        final byte[] inputBuffer = this._inputBuffer;
        final int[] icLatin1 = NonBlockingJsonParser._icLatin1;
        final int n3 = inputBuffer[n++] & 0xFF;
        if (icLatin1[n3] == 0) {
            n2 = (n2 << 8 | n3);
            final int n4 = inputBuffer[n++] & 0xFF;
            if (icLatin1[n4] == 0) {
                n2 = (n2 << 8 | n4);
                final int n5 = inputBuffer[n++] & 0xFF;
                if (icLatin1[n5] == 0) {
                    n2 = (n2 << 8 | n5);
                    final int n6 = inputBuffer[n++] & 0xFF;
                    if (icLatin1[n6] == 0) {
                        return this._parseMediumName2(n, n6, n2);
                    }
                    if (n6 == 34) {
                        this._inputPtr = n;
                        return this._findName(this._quad1, n2, 4);
                    }
                    return null;
                }
                else {
                    if (n5 == 34) {
                        this._inputPtr = n;
                        return this._findName(this._quad1, n2, 3);
                    }
                    return null;
                }
            }
            else {
                if (n4 == 34) {
                    this._inputPtr = n;
                    return this._findName(this._quad1, n2, 2);
                }
                return null;
            }
        }
        else {
            if (n3 == 34) {
                this._inputPtr = n;
                return this._findName(this._quad1, n2, 1);
            }
            return null;
        }
    }
    
    private final String _parseMediumName2(int n, int n2, final int n3) throws IOException {
        final byte[] inputBuffer = this._inputBuffer;
        final int[] icLatin1 = NonBlockingJsonParser._icLatin1;
        final int n4 = inputBuffer[n++] & 0xFF;
        if (icLatin1[n4] != 0) {
            if (n4 == 34) {
                this._inputPtr = n;
                return this._findName(this._quad1, n3, n2, 1);
            }
            return null;
        }
        else {
            n2 = (n2 << 8 | n4);
            final int n5 = inputBuffer[n++] & 0xFF;
            if (icLatin1[n5] != 0) {
                if (n5 == 34) {
                    this._inputPtr = n;
                    return this._findName(this._quad1, n3, n2, 2);
                }
                return null;
            }
            else {
                n2 = (n2 << 8 | n5);
                final int n6 = inputBuffer[n++] & 0xFF;
                if (icLatin1[n6] != 0) {
                    if (n6 == 34) {
                        this._inputPtr = n;
                        return this._findName(this._quad1, n3, n2, 3);
                    }
                    return null;
                }
                else {
                    n2 = (n2 << 8 | n6);
                    if ((inputBuffer[n++] & 0xFF) == 0x22) {
                        this._inputPtr = n;
                        return this._findName(this._quad1, n3, n2, 4);
                    }
                    return null;
                }
            }
        }
    }
    
    private final JsonToken _parseEscapedName(int n, int n2, int n3) throws IOException {
        int[] quadBuffer = this._quadBuffer;
        final int[] icLatin1 = NonBlockingJsonParser._icLatin1;
        while (this._inputPtr < this._inputEnd) {
            int decodeCharEscape = this._inputBuffer[this._inputPtr++] & 0xFF;
            if (icLatin1[decodeCharEscape] == 0) {
                if (n3 < 4) {
                    ++n3;
                    n2 = (n2 << 8 | decodeCharEscape);
                }
                else {
                    if (n >= quadBuffer.length) {
                        quadBuffer = (this._quadBuffer = ParserBase.growArrayBy(quadBuffer, quadBuffer.length));
                    }
                    quadBuffer[n++] = n2;
                    n2 = decodeCharEscape;
                    n3 = 1;
                }
            }
            else if (decodeCharEscape == 34) {
                if (n3 > 0) {
                    if (n >= quadBuffer.length) {
                        quadBuffer = (this._quadBuffer = ParserBase.growArrayBy(quadBuffer, quadBuffer.length));
                    }
                    quadBuffer[n++] = NonBlockingJsonParserBase._padLastQuad(n2, n3);
                    String s = this._symbols.findName(quadBuffer, n);
                    if (s == null) {
                        s = this._addName(quadBuffer, n, n3);
                    }
                    return this._fieldComplete(s);
                }
                return this._fieldComplete("");
            }
            else {
                if (decodeCharEscape != 92) {
                    this._throwUnquotedSpace(decodeCharEscape, "name");
                }
                else {
                    decodeCharEscape = this._decodeCharEscape();
                    if (decodeCharEscape < 0) {
                        this._minorState = 8;
                        this._minorStateAfterSplit = 7;
                        this._quadLength = n;
                        this._pending32 = n2;
                        this._pendingBytes = n3;
                        return this._currToken = JsonToken.NOT_AVAILABLE;
                    }
                }
                if (n >= quadBuffer.length) {
                    quadBuffer = (this._quadBuffer = ParserBase.growArrayBy(quadBuffer, quadBuffer.length));
                }
                if (decodeCharEscape > 127) {
                    if (n3 >= 4) {
                        quadBuffer[n++] = n2;
                        n2 = 0;
                        n3 = 0;
                    }
                    if (decodeCharEscape < 2048) {
                        n2 = (n2 << 8 | (0xC0 | decodeCharEscape >> 6));
                        ++n3;
                    }
                    else {
                        n2 = (n2 << 8 | (0xE0 | decodeCharEscape >> 12));
                        if (++n3 >= 4) {
                            quadBuffer[n++] = n2;
                            n2 = 0;
                            n3 = 0;
                        }
                        n2 = (n2 << 8 | (0x80 | (decodeCharEscape >> 6 & 0x3F)));
                        ++n3;
                    }
                    decodeCharEscape = (0x80 | (decodeCharEscape & 0x3F));
                }
                if (n3 < 4) {
                    ++n3;
                    n2 = (n2 << 8 | decodeCharEscape);
                }
                else {
                    quadBuffer[n++] = n2;
                    n2 = decodeCharEscape;
                    n3 = 1;
                }
            }
        }
        this._quadLength = n;
        this._pending32 = n2;
        this._pendingBytes = n3;
        this._minorState = 7;
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    private JsonToken _handleOddName(final int n) throws IOException {
        switch (n) {
            case 35:
                if (Feature.ALLOW_YAML_COMMENTS.enabledIn(this._features)) {
                    return this._finishHashComment(4);
                }
                break;
            case 47:
                return this._startSlashComment(4);
            case 39:
                if (this.isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
                    return this._finishAposName(0, 0, 0);
                }
                break;
            case 93:
                return this._closeArrayScope();
        }
        if (!this.isEnabled(Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            this._reportUnexpectedChar((char)n, "was expecting double-quote to start field name");
        }
        if (CharTypes.getInputCodeUtf8JsNames()[n] != 0) {
            this._reportUnexpectedChar(n, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        return this._finishUnquotedName(0, n, 1);
    }
    
    private JsonToken _finishUnquotedName(int quadLength, int pending32, int pendingBytes) throws IOException {
        int[] quadBuffer = this._quadBuffer;
        final int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        while (this._inputPtr < this._inputEnd) {
            final int n = this._inputBuffer[this._inputPtr] & 0xFF;
            if (inputCodeUtf8JsNames[n] != 0) {
                if (pendingBytes > 0) {
                    if (quadLength >= quadBuffer.length) {
                        quadBuffer = (this._quadBuffer = ParserBase.growArrayBy(quadBuffer, quadBuffer.length));
                    }
                    quadBuffer[quadLength++] = pending32;
                }
                String s = this._symbols.findName(quadBuffer, quadLength);
                if (s == null) {
                    s = this._addName(quadBuffer, quadLength, pendingBytes);
                }
                return this._fieldComplete(s);
            }
            ++this._inputPtr;
            if (pendingBytes < 4) {
                ++pendingBytes;
                pending32 = (pending32 << 8 | n);
            }
            else {
                if (quadLength >= quadBuffer.length) {
                    quadBuffer = (this._quadBuffer = ParserBase.growArrayBy(quadBuffer, quadBuffer.length));
                }
                quadBuffer[quadLength++] = pending32;
                pending32 = n;
                pendingBytes = 1;
            }
        }
        this._quadLength = quadLength;
        this._pending32 = pending32;
        this._pendingBytes = pendingBytes;
        this._minorState = 10;
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    private JsonToken _finishAposName(int n, int n2, int n3) throws IOException {
        int[] quadBuffer = this._quadBuffer;
        final int[] icLatin1 = NonBlockingJsonParser._icLatin1;
        while (this._inputPtr < this._inputEnd) {
            int decodeCharEscape = this._inputBuffer[this._inputPtr++] & 0xFF;
            if (decodeCharEscape == 39) {
                if (n3 > 0) {
                    if (n >= quadBuffer.length) {
                        quadBuffer = (this._quadBuffer = ParserBase.growArrayBy(quadBuffer, quadBuffer.length));
                    }
                    quadBuffer[n++] = NonBlockingJsonParserBase._padLastQuad(n2, n3);
                    String s = this._symbols.findName(quadBuffer, n);
                    if (s == null) {
                        s = this._addName(quadBuffer, n, n3);
                    }
                    return this._fieldComplete(s);
                }
                return this._fieldComplete("");
            }
            else {
                if (decodeCharEscape != 34 && icLatin1[decodeCharEscape] != 0) {
                    if (decodeCharEscape != 92) {
                        this._throwUnquotedSpace(decodeCharEscape, "name");
                    }
                    else {
                        decodeCharEscape = this._decodeCharEscape();
                        if (decodeCharEscape < 0) {
                            this._minorState = 8;
                            this._minorStateAfterSplit = 9;
                            this._quadLength = n;
                            this._pending32 = n2;
                            this._pendingBytes = n3;
                            return this._currToken = JsonToken.NOT_AVAILABLE;
                        }
                    }
                    if (decodeCharEscape > 127) {
                        if (n3 >= 4) {
                            if (n >= quadBuffer.length) {
                                quadBuffer = (this._quadBuffer = ParserBase.growArrayBy(quadBuffer, quadBuffer.length));
                            }
                            quadBuffer[n++] = n2;
                            n2 = 0;
                            n3 = 0;
                        }
                        if (decodeCharEscape < 2048) {
                            n2 = (n2 << 8 | (0xC0 | decodeCharEscape >> 6));
                            ++n3;
                        }
                        else {
                            n2 = (n2 << 8 | (0xE0 | decodeCharEscape >> 12));
                            if (++n3 >= 4) {
                                if (n >= quadBuffer.length) {
                                    quadBuffer = (this._quadBuffer = ParserBase.growArrayBy(quadBuffer, quadBuffer.length));
                                }
                                quadBuffer[n++] = n2;
                                n2 = 0;
                                n3 = 0;
                            }
                            n2 = (n2 << 8 | (0x80 | (decodeCharEscape >> 6 & 0x3F)));
                            ++n3;
                        }
                        decodeCharEscape = (0x80 | (decodeCharEscape & 0x3F));
                    }
                }
                if (n3 < 4) {
                    ++n3;
                    n2 = (n2 << 8 | decodeCharEscape);
                }
                else {
                    if (n >= quadBuffer.length) {
                        quadBuffer = (this._quadBuffer = ParserBase.growArrayBy(quadBuffer, quadBuffer.length));
                    }
                    quadBuffer[n++] = n2;
                    n2 = decodeCharEscape;
                    n3 = 1;
                }
            }
        }
        this._quadLength = n;
        this._pending32 = n2;
        this._pendingBytes = n3;
        this._minorState = 9;
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    protected final JsonToken _finishFieldWithEscape() throws IOException {
        int decodeSplitEscaped = this._decodeSplitEscaped(this._quoted32, this._quotedDigits);
        if (decodeSplitEscaped < 0) {
            this._minorState = 8;
            return JsonToken.NOT_AVAILABLE;
        }
        if (this._quadLength >= this._quadBuffer.length) {
            this._quadBuffer = ParserBase.growArrayBy(this._quadBuffer, 32);
        }
        int pending32 = this._pending32;
        int pendingBytes = this._pendingBytes;
        if (decodeSplitEscaped > 127) {
            if (pendingBytes >= 4) {
                this._quadBuffer[this._quadLength++] = pending32;
                pending32 = 0;
                pendingBytes = 0;
            }
            if (decodeSplitEscaped < 2048) {
                pending32 = (pending32 << 8 | (0xC0 | decodeSplitEscaped >> 6));
                ++pendingBytes;
            }
            else {
                int n = pending32 << 8 | (0xE0 | decodeSplitEscaped >> 12);
                if (++pendingBytes >= 4) {
                    this._quadBuffer[this._quadLength++] = n;
                    n = 0;
                    pendingBytes = 0;
                }
                pending32 = (n << 8 | (0x80 | (decodeSplitEscaped >> 6 & 0x3F)));
                ++pendingBytes;
            }
            decodeSplitEscaped = (0x80 | (decodeSplitEscaped & 0x3F));
        }
        int n2;
        if (pendingBytes < 4) {
            ++pendingBytes;
            n2 = (pending32 << 8 | decodeSplitEscaped);
        }
        else {
            this._quadBuffer[this._quadLength++] = pending32;
            n2 = decodeSplitEscaped;
            pendingBytes = 1;
        }
        if (this._minorStateAfterSplit == 9) {
            return this._finishAposName(this._quadLength, n2, pendingBytes);
        }
        return this._parseEscapedName(this._quadLength, n2, pendingBytes);
    }
    
    private int _decodeSplitEscaped(int n, int n2) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            this._quoted32 = n;
            this._quotedDigits = n2;
            return -1;
        }
        byte b = this._inputBuffer[this._inputPtr++];
        if (n2 == -1) {
            switch (b) {
                case 98:
                    return 8;
                case 116:
                    return 9;
                case 110:
                    return 10;
                case 102:
                    return 12;
                case 114:
                    return 13;
                case 34:
                case 47:
                case 92:
                    return b;
                case 117:
                    if (this._inputPtr >= this._inputEnd) {
                        this._quotedDigits = 0;
                        this._quoted32 = 0;
                        return -1;
                    }
                    b = this._inputBuffer[this._inputPtr++];
                    n2 = 0;
                    break;
                default:
                    return this._handleUnrecognizedCharacterEscape((char)b);
            }
        }
        int n3 = b & 0xFF;
        while (true) {
            final int charToHex = CharTypes.charToHex(n3);
            if (charToHex < 0) {
                this._reportUnexpectedChar(n3, "expected a hex-digit for character escape sequence");
            }
            n = (n << 4 | charToHex);
            if (++n2 == 4) {
                return n;
            }
            if (this._inputPtr >= this._inputEnd) {
                this._quotedDigits = n2;
                this._quoted32 = n;
                return -1;
            }
            n3 = (this._inputBuffer[this._inputPtr++] & 0xFF);
        }
    }
    
    protected JsonToken _startString() throws IOException {
        int i = this._inputPtr;
        int n = 0;
        final char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        final int[] icUTF8 = NonBlockingJsonParser._icUTF8;
        final int min = Math.min(this._inputEnd, i + emptyAndGetCurrentSegment.length);
        final byte[] inputBuffer = this._inputBuffer;
        while (i < min) {
            final int n2 = inputBuffer[i] & 0xFF;
            if (icUTF8[n2] != 0) {
                if (n2 == 34) {
                    this._inputPtr = i + 1;
                    this._textBuffer.setCurrentLength(n);
                    return this._valueComplete(JsonToken.VALUE_STRING);
                }
                break;
            }
            else {
                ++i;
                emptyAndGetCurrentSegment[n++] = (char)n2;
            }
        }
        this._textBuffer.setCurrentLength(n);
        this._inputPtr = i;
        return this._finishRegularString();
    }
    
    private final JsonToken _finishRegularString() throws IOException {
        final int[] icUTF8 = NonBlockingJsonParser._icUTF8;
        final byte[] inputBuffer = this._inputBuffer;
        char[] array = this._textBuffer.getBufferWithoutReset();
        int currentLength = this._textBuffer.getCurrentSegmentSize();
        int i = this._inputPtr;
        final int n = this._inputEnd - 5;
        while (i < this._inputEnd) {
            if (currentLength >= array.length) {
                array = this._textBuffer.finishCurrentSegment();
                currentLength = 0;
            }
            while (i < Math.min(this._inputEnd, i + (array.length - currentLength))) {
                int n2 = inputBuffer[i++] & 0xFF;
                if (icUTF8[n2] != 0) {
                    if (n2 == 34) {
                        this._inputPtr = i;
                        this._textBuffer.setCurrentLength(currentLength);
                        return this._valueComplete(JsonToken.VALUE_STRING);
                    }
                    if (i < n) {
                        switch (icUTF8[n2]) {
                            case 1:
                                this._inputPtr = i;
                                n2 = this._decodeFastCharEscape();
                                i = this._inputPtr;
                                break;
                            case 2:
                                n2 = this._decodeUTF8_2(n2, this._inputBuffer[i++]);
                                break;
                            case 3:
                                n2 = this._decodeUTF8_3(n2, this._inputBuffer[i++], this._inputBuffer[i++]);
                                break;
                            case 4: {
                                final int decodeUTF8_4 = this._decodeUTF8_4(n2, this._inputBuffer[i++], this._inputBuffer[i++], this._inputBuffer[i++]);
                                array[currentLength++] = (char)(0xD800 | decodeUTF8_4 >> 10);
                                if (currentLength >= array.length) {
                                    array = this._textBuffer.finishCurrentSegment();
                                    currentLength = 0;
                                }
                                n2 = (0xDC00 | (decodeUTF8_4 & 0x3FF));
                                break;
                            }
                            default:
                                if (n2 < 32) {
                                    this._throwUnquotedSpace(n2, "string value");
                                    break;
                                }
                                this._reportInvalidChar(n2);
                                break;
                        }
                        if (currentLength >= array.length) {
                            array = this._textBuffer.finishCurrentSegment();
                            currentLength = 0;
                        }
                        array[currentLength++] = (char)n2;
                        break;
                    }
                    this._inputPtr = i;
                    this._textBuffer.setCurrentLength(currentLength);
                    if (!this._decodeSplitMultiByte(n2, icUTF8[n2], i < this._inputEnd)) {
                        this._minorStateAfterSplit = 40;
                        return this._currToken = JsonToken.NOT_AVAILABLE;
                    }
                    array = this._textBuffer.getBufferWithoutReset();
                    currentLength = this._textBuffer.getCurrentSegmentSize();
                    i = this._inputPtr;
                    break;
                }
                else {
                    array[currentLength++] = (char)n2;
                }
            }
        }
        this._inputPtr = i;
        this._minorState = 40;
        this._textBuffer.setCurrentLength(currentLength);
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    protected JsonToken _startAposString() throws IOException {
        int i = this._inputPtr;
        int n = 0;
        final char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        final int[] icUTF8 = NonBlockingJsonParser._icUTF8;
        final int min = Math.min(this._inputEnd, i + emptyAndGetCurrentSegment.length);
        final byte[] inputBuffer = this._inputBuffer;
        while (i < min) {
            final int n2 = inputBuffer[i] & 0xFF;
            if (n2 == 39) {
                this._inputPtr = i + 1;
                this._textBuffer.setCurrentLength(n);
                return this._valueComplete(JsonToken.VALUE_STRING);
            }
            if (icUTF8[n2] != 0) {
                break;
            }
            ++i;
            emptyAndGetCurrentSegment[n++] = (char)n2;
        }
        this._textBuffer.setCurrentLength(n);
        this._inputPtr = i;
        return this._finishAposString();
    }
    
    private final JsonToken _finishAposString() throws IOException {
        final int[] icUTF8 = NonBlockingJsonParser._icUTF8;
        final byte[] inputBuffer = this._inputBuffer;
        char[] array = this._textBuffer.getBufferWithoutReset();
        int currentLength = this._textBuffer.getCurrentSegmentSize();
        int i = this._inputPtr;
        final int n = this._inputEnd - 5;
        while (i < this._inputEnd) {
            if (currentLength >= array.length) {
                array = this._textBuffer.finishCurrentSegment();
                currentLength = 0;
            }
            while (i < Math.min(this._inputEnd, i + (array.length - currentLength))) {
                int n2 = inputBuffer[i++] & 0xFF;
                if (icUTF8[n2] != 0 && n2 != 34) {
                    if (i < n) {
                        switch (icUTF8[n2]) {
                            case 1:
                                this._inputPtr = i;
                                n2 = this._decodeFastCharEscape();
                                i = this._inputPtr;
                                break;
                            case 2:
                                n2 = this._decodeUTF8_2(n2, this._inputBuffer[i++]);
                                break;
                            case 3:
                                n2 = this._decodeUTF8_3(n2, this._inputBuffer[i++], this._inputBuffer[i++]);
                                break;
                            case 4: {
                                final int decodeUTF8_4 = this._decodeUTF8_4(n2, this._inputBuffer[i++], this._inputBuffer[i++], this._inputBuffer[i++]);
                                array[currentLength++] = (char)(0xD800 | decodeUTF8_4 >> 10);
                                if (currentLength >= array.length) {
                                    array = this._textBuffer.finishCurrentSegment();
                                    currentLength = 0;
                                }
                                n2 = (0xDC00 | (decodeUTF8_4 & 0x3FF));
                                break;
                            }
                            default:
                                if (n2 < 32) {
                                    this._throwUnquotedSpace(n2, "string value");
                                    break;
                                }
                                this._reportInvalidChar(n2);
                                break;
                        }
                        if (currentLength >= array.length) {
                            array = this._textBuffer.finishCurrentSegment();
                            currentLength = 0;
                        }
                        array[currentLength++] = (char)n2;
                        break;
                    }
                    this._inputPtr = i;
                    this._textBuffer.setCurrentLength(currentLength);
                    if (!this._decodeSplitMultiByte(n2, icUTF8[n2], i < this._inputEnd)) {
                        this._minorStateAfterSplit = 45;
                        return this._currToken = JsonToken.NOT_AVAILABLE;
                    }
                    array = this._textBuffer.getBufferWithoutReset();
                    currentLength = this._textBuffer.getCurrentSegmentSize();
                    i = this._inputPtr;
                    break;
                }
                else {
                    if (n2 == 39) {
                        this._inputPtr = i;
                        this._textBuffer.setCurrentLength(currentLength);
                        return this._valueComplete(JsonToken.VALUE_STRING);
                    }
                    array[currentLength++] = (char)n2;
                }
            }
        }
        this._inputPtr = i;
        this._minorState = 45;
        this._textBuffer.setCurrentLength(currentLength);
        return this._currToken = JsonToken.NOT_AVAILABLE;
    }
    
    private final boolean _decodeSplitMultiByte(int pending32, final int n, final boolean b) throws IOException {
        switch (n) {
            case 1:
                pending32 = this._decodeSplitEscaped(0, -1);
                if (pending32 < 0) {
                    this._minorState = 41;
                    return false;
                }
                this._textBuffer.append((char)pending32);
                return true;
            case 2:
                if (b) {
                    pending32 = this._decodeUTF8_2(pending32, this._inputBuffer[this._inputPtr++]);
                    this._textBuffer.append((char)pending32);
                    return true;
                }
                this._minorState = 42;
                this._pending32 = pending32;
                return false;
            case 3:
                pending32 &= 0xF;
                if (b) {
                    return this._decodeSplitUTF8_3(pending32, 1, this._inputBuffer[this._inputPtr++]);
                }
                this._minorState = 43;
                this._pending32 = pending32;
                this._pendingBytes = 1;
                return false;
            case 4:
                pending32 &= 0x7;
                if (b) {
                    return this._decodeSplitUTF8_4(pending32, 1, this._inputBuffer[this._inputPtr++]);
                }
                this._pending32 = pending32;
                this._pendingBytes = 1;
                this._minorState = 44;
                return false;
            default:
                if (pending32 < 32) {
                    this._throwUnquotedSpace(pending32, "string value");
                }
                else {
                    this._reportInvalidChar(pending32);
                }
                this._textBuffer.append((char)pending32);
                return true;
        }
    }
    
    private final boolean _decodeSplitUTF8_3(int pending32, final int n, int n2) throws IOException {
        if (n == 1) {
            if ((n2 & 0xC0) != 0x80) {
                this._reportInvalidOther(n2 & 0xFF, this._inputPtr);
            }
            pending32 = (pending32 << 6 | (n2 & 0x3F));
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 43;
                this._pending32 = pending32;
                this._pendingBytes = 2;
                return false;
            }
            n2 = this._inputBuffer[this._inputPtr++];
        }
        if ((n2 & 0xC0) != 0x80) {
            this._reportInvalidOther(n2 & 0xFF, this._inputPtr);
        }
        this._textBuffer.append((char)(pending32 << 6 | (n2 & 0x3F)));
        return true;
    }
    
    private final boolean _decodeSplitUTF8_4(int n, int n2, int n3) throws IOException {
        if (n2 == 1) {
            if ((n3 & 0xC0) != 0x80) {
                this._reportInvalidOther(n3 & 0xFF, this._inputPtr);
            }
            n = (n << 6 | (n3 & 0x3F));
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 44;
                this._pending32 = n;
                this._pendingBytes = 2;
                return false;
            }
            n2 = 2;
            n3 = this._inputBuffer[this._inputPtr++];
        }
        if (n2 == 2) {
            if ((n3 & 0xC0) != 0x80) {
                this._reportInvalidOther(n3 & 0xFF, this._inputPtr);
            }
            n = (n << 6 | (n3 & 0x3F));
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 44;
                this._pending32 = n;
                this._pendingBytes = 3;
                return false;
            }
            n3 = this._inputBuffer[this._inputPtr++];
        }
        if ((n3 & 0xC0) != 0x80) {
            this._reportInvalidOther(n3 & 0xFF, this._inputPtr);
        }
        final int n4 = (n << 6 | (n3 & 0x3F)) - 65536;
        this._textBuffer.append((char)(0xD800 | n4 >> 10));
        this._textBuffer.append((char)(0xDC00 | (n4 & 0x3FF)));
        return true;
    }
    
    private final int _decodeCharEscape() throws IOException {
        if (this._inputEnd - this._inputPtr < 5) {
            return this._decodeSplitEscaped(0, -1);
        }
        return this._decodeFastCharEscape();
    }
    
    private final int _decodeFastCharEscape() throws IOException {
        final byte b = this._inputBuffer[this._inputPtr++];
        switch (b) {
            case 98:
                return 8;
            case 116:
                return 9;
            case 110:
                return 10;
            case 102:
                return 12;
            case 114:
                return 13;
            case 34:
            case 47:
            case 92:
                return (char)b;
            case 117: {
                byte b2 = this._inputBuffer[this._inputPtr++];
                final int charToHex;
                if ((charToHex = CharTypes.charToHex(b2)) >= 0) {
                    b2 = this._inputBuffer[this._inputPtr++];
                    final int charToHex2 = CharTypes.charToHex(b2);
                    if (charToHex2 >= 0) {
                        final int n = charToHex << 4 | charToHex2;
                        b2 = this._inputBuffer[this._inputPtr++];
                        final int charToHex3 = CharTypes.charToHex(b2);
                        if (charToHex3 >= 0) {
                            final int n2 = n << 4 | charToHex3;
                            b2 = this._inputBuffer[this._inputPtr++];
                            final int charToHex4 = CharTypes.charToHex(b2);
                            if (charToHex4 >= 0) {
                                return n2 << 4 | charToHex4;
                            }
                        }
                    }
                }
                this._reportUnexpectedChar(b2 & 0xFF, "expected a hex-digit for character escape sequence");
                return -1;
            }
            default:
                return this._handleUnrecognizedCharacterEscape((char)b);
        }
    }
    
    private final int _decodeUTF8_2(final int n, final int n2) throws IOException {
        if ((n2 & 0xC0) != 0x80) {
            this._reportInvalidOther(n2 & 0xFF, this._inputPtr);
        }
        return (n & 0x1F) << 6 | (n2 & 0x3F);
    }
    
    private final int _decodeUTF8_3(int n, final int n2, final int n3) throws IOException {
        n &= 0xF;
        if ((n2 & 0xC0) != 0x80) {
            this._reportInvalidOther(n2 & 0xFF, this._inputPtr);
        }
        n = (n << 6 | (n2 & 0x3F));
        if ((n3 & 0xC0) != 0x80) {
            this._reportInvalidOther(n3 & 0xFF, this._inputPtr);
        }
        return n << 6 | (n3 & 0x3F);
    }
    
    private final int _decodeUTF8_4(int n, final int n2, final int n3, final int n4) throws IOException {
        if ((n2 & 0xC0) != 0x80) {
            this._reportInvalidOther(n2 & 0xFF, this._inputPtr);
        }
        n = ((n & 0x7) << 6 | (n2 & 0x3F));
        if ((n3 & 0xC0) != 0x80) {
            this._reportInvalidOther(n3 & 0xFF, this._inputPtr);
        }
        n = (n << 6 | (n3 & 0x3F));
        if ((n4 & 0xC0) != 0x80) {
            this._reportInvalidOther(n4 & 0xFF, this._inputPtr);
        }
        return (n << 6 | (n4 & 0x3F)) - 65536;
    }
    
    @Override
    public /* bridge */ NonBlockingInputFeeder getNonBlockingInputFeeder() {
        return this.getNonBlockingInputFeeder();
    }
    
    static {
        _icUTF8 = CharTypes.getInputCodeUtf8();
        _icLatin1 = CharTypes.getInputCodeLatin1();
    }
}
