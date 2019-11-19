package com.fasterxml.jackson.core.json;

import java.io.CharConversionException;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.io.UTF32Reader;
import java.io.InputStreamReader;
import com.fasterxml.jackson.core.io.MergedStream;
import java.io.ByteArrayInputStream;
import java.io.Reader;
import java.io.DataInput;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonEncoding;
import java.io.InputStream;
import com.fasterxml.jackson.core.io.IOContext;

public final class ByteSourceJsonBootstrapper
{
    public static final byte UTF8_BOM_1 = -17;
    public static final byte UTF8_BOM_2 = -69;
    public static final byte UTF8_BOM_3 = -65;
    private final IOContext _context;
    private final InputStream _in;
    private final byte[] _inputBuffer;
    private int _inputPtr;
    private int _inputEnd;
    private final boolean _bufferRecyclable;
    private boolean _bigEndian;
    private int _bytesPerChar;
    
    public ByteSourceJsonBootstrapper(final IOContext context, final InputStream in) {
        super();
        this._bigEndian = true;
        this._context = context;
        this._in = in;
        this._inputBuffer = context.allocReadIOBuffer();
        final int n = 0;
        this._inputPtr = n;
        this._inputEnd = n;
        this._bufferRecyclable = true;
    }
    
    public ByteSourceJsonBootstrapper(final IOContext context, final byte[] inputBuffer, final int inputPtr, final int n) {
        super();
        this._bigEndian = true;
        this._context = context;
        this._in = null;
        this._inputBuffer = inputBuffer;
        this._inputPtr = inputPtr;
        this._inputEnd = inputPtr + n;
        this._bufferRecyclable = false;
    }
    
    public JsonEncoding detectEncoding() throws IOException {
        if (this.ensureLoaded(4)) {
            final int n = this._inputBuffer[this._inputPtr] << 24 | (this._inputBuffer[this._inputPtr + 1] & 0xFF) << 16 | (this._inputBuffer[this._inputPtr + 2] & 0xFF) << 8 | (this._inputBuffer[this._inputPtr + 3] & 0xFF);
            if (!this.handleBOM(n)) {
                if (!this.checkUTF32(n)) {
                    if (this.checkUTF16(n >>> 16)) {}
                }
            }
        }
        else if (this.ensureLoaded(2) && this.checkUTF16((this._inputBuffer[this._inputPtr] & 0xFF) << 8 | (this._inputBuffer[this._inputPtr + 1] & 0xFF))) {}
        final JsonEncoding utf8 = JsonEncoding.UTF8;
        this._context.setEncoding(utf8);
        return utf8;
    }
    
    public static int skipUTF8BOM(final DataInput dataInput) throws IOException {
        final int unsignedByte = dataInput.readUnsignedByte();
        if (unsignedByte != 239) {
            return unsignedByte;
        }
        final int unsignedByte2 = dataInput.readUnsignedByte();
        if (unsignedByte2 != 187) {
            throw new IOException("Unexpected byte 0x" + Integer.toHexString(unsignedByte2) + " following 0xEF; should get 0xBB as part of UTF-8 BOM");
        }
        final int unsignedByte3 = dataInput.readUnsignedByte();
        if (unsignedByte3 != 191) {
            throw new IOException("Unexpected byte 0x" + Integer.toHexString(unsignedByte3) + " following 0xEF 0xBB; should get 0xBF as part of UTF-8 BOM");
        }
        return dataInput.readUnsignedByte();
    }
    
    public Reader constructReader() throws IOException {
        final JsonEncoding encoding = this._context.getEncoding();
        switch (encoding.bits()) {
            case 8:
            case 16: {
                InputStream in = this._in;
                if (in == null) {
                    in = new ByteArrayInputStream(this._inputBuffer, this._inputPtr, this._inputEnd);
                }
                else if (this._inputPtr < this._inputEnd) {
                    in = new MergedStream(this._context, in, this._inputBuffer, this._inputPtr, this._inputEnd);
                }
                return new InputStreamReader(in, encoding.getJavaName());
            }
            case 32:
                return new UTF32Reader(this._context, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, this._context.getEncoding().isBigEndian());
            default:
                throw new RuntimeException("Internal error");
        }
    }
    
    public JsonParser constructParser(final int n, final ObjectCodec objectCodec, final ByteQuadsCanonicalizer byteQuadsCanonicalizer, final CharsToNameCanonicalizer charsToNameCanonicalizer, final int n2) throws IOException {
        if (this.detectEncoding() == JsonEncoding.UTF8 && JsonFactory.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(n2)) {
            return new UTF8StreamJsonParser(this._context, n, this._in, objectCodec, byteQuadsCanonicalizer.makeChild(n2), this._inputBuffer, this._inputPtr, this._inputEnd, this._bufferRecyclable);
        }
        return new ReaderBasedJsonParser(this._context, n, this.constructReader(), objectCodec, charsToNameCanonicalizer.makeChild(n2));
    }
    
    public static MatchStrength hasJSONFormat(final InputAccessor inputAccessor) throws IOException {
        if (!inputAccessor.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte b = inputAccessor.nextByte();
        if (b == -17) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -69) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -65) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            b = inputAccessor.nextByte();
        }
        final int skipSpace = skipSpace(inputAccessor, b);
        if (skipSpace < 0) {
            return MatchStrength.INCONCLUSIVE;
        }
        if (skipSpace == 123) {
            final int skipSpace2 = skipSpace(inputAccessor);
            if (skipSpace2 < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (skipSpace2 == 34 || skipSpace2 == 125) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.NO_MATCH;
        }
        else if (skipSpace == 91) {
            final int skipSpace3 = skipSpace(inputAccessor);
            if (skipSpace3 < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (skipSpace3 == 93 || skipSpace3 == 91) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.SOLID_MATCH;
        }
        else {
            final MatchStrength weak_MATCH = MatchStrength.WEAK_MATCH;
            if (skipSpace == 34) {
                return weak_MATCH;
            }
            if (skipSpace <= 57 && skipSpace >= 48) {
                return weak_MATCH;
            }
            if (skipSpace == 45) {
                final int skipSpace4 = skipSpace(inputAccessor);
                if (skipSpace4 < 0) {
                    return MatchStrength.INCONCLUSIVE;
                }
                return (skipSpace4 <= 57 && skipSpace4 >= 48) ? weak_MATCH : MatchStrength.NO_MATCH;
            }
            else {
                if (skipSpace == 110) {
                    return tryMatch(inputAccessor, "ull", weak_MATCH);
                }
                if (skipSpace == 116) {
                    return tryMatch(inputAccessor, "rue", weak_MATCH);
                }
                if (skipSpace == 102) {
                    return tryMatch(inputAccessor, "alse", weak_MATCH);
                }
                return MatchStrength.NO_MATCH;
            }
        }
    }
    
    private static MatchStrength tryMatch(final InputAccessor inputAccessor, final String s, final MatchStrength matchStrength) throws IOException {
        for (int i = 0; i < s.length(); ++i) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != s.charAt(i)) {
                return MatchStrength.NO_MATCH;
            }
        }
        return matchStrength;
    }
    
    private static int skipSpace(final InputAccessor inputAccessor) throws IOException {
        if (!inputAccessor.hasMoreBytes()) {
            return -1;
        }
        return skipSpace(inputAccessor, inputAccessor.nextByte());
    }
    
    private static int skipSpace(final InputAccessor inputAccessor, byte nextByte) throws IOException {
        while (true) {
            final int n = nextByte & 0xFF;
            if (n != 32 && n != 13 && n != 10 && n != 9) {
                return n;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return -1;
            }
            nextByte = inputAccessor.nextByte();
        }
    }
    
    private boolean handleBOM(final int n) throws IOException {
        switch (n) {
            case 65279:
                this._bigEndian = true;
                this._inputPtr += 4;
                this._bytesPerChar = 4;
                return true;
            case -131072:
                this._inputPtr += 4;
                this._bytesPerChar = 4;
                this._bigEndian = false;
                return true;
            case 65534:
                this.reportWeirdUCS4("2143");
                break;
            case -16842752:
                this.reportWeirdUCS4("3412");
                break;
        }
        final int n2 = n >>> 16;
        if (n2 == 65279) {
            this._inputPtr += 2;
            this._bytesPerChar = 2;
            return this._bigEndian = true;
        }
        if (n2 == 65534) {
            this._inputPtr += 2;
            this._bytesPerChar = 2;
            this._bigEndian = false;
            return true;
        }
        if (n >>> 8 == 15711167) {
            this._inputPtr += 3;
            this._bytesPerChar = 1;
            return this._bigEndian = true;
        }
        return false;
    }
    
    private boolean checkUTF32(final int n) throws IOException {
        if (n >> 8 == 0) {
            this._bigEndian = true;
        }
        else if ((n & 0xFFFFFF) == 0x0) {
            this._bigEndian = false;
        }
        else if ((n & 0xFF00FFFF) == 0x0) {
            this.reportWeirdUCS4("3412");
        }
        else {
            if ((n & 0xFFFF00FF) != 0x0) {
                return false;
            }
            this.reportWeirdUCS4("2143");
        }
        this._bytesPerChar = 4;
        return true;
    }
    
    private boolean checkUTF16(final int n) {
        if ((n & 0xFF00) == 0x0) {
            this._bigEndian = true;
        }
        else {
            if ((n & 0xFF) != 0x0) {
                return false;
            }
            this._bigEndian = false;
        }
        this._bytesPerChar = 2;
        return true;
    }
    
    private void reportWeirdUCS4(final String s) throws IOException {
        throw new CharConversionException("Unsupported UCS-4 endianness (" + s + ") detected");
    }
    
    protected boolean ensureLoaded(final int n) throws IOException {
        int read;
        for (int i = this._inputEnd - this._inputPtr; i < n; i += read) {
            if (this._in == null) {
                read = -1;
            }
            else {
                read = this._in.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            }
            if (read < 1) {
                return false;
            }
            this._inputEnd += read;
        }
        return true;
    }
}
