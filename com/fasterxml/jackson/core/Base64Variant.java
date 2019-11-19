package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.util.Arrays;
import java.io.Serializable;

public final class Base64Variant implements Serializable
{
    private static final int INT_SPACE = 32;
    private static final long serialVersionUID = 1L;
    static final char PADDING_CHAR_NONE = '\0';
    public static final int BASE64_VALUE_INVALID = -1;
    public static final int BASE64_VALUE_PADDING = -2;
    private final transient int[] _asciiToBase64;
    private final transient char[] _base64ToAsciiC;
    private final transient byte[] _base64ToAsciiB;
    final String _name;
    private final transient boolean _usesPadding;
    private final transient char _paddingChar;
    private final transient int _maxLineLength;
    
    public Base64Variant(final String name, final String s, final boolean usesPadding, final char paddingChar, final int maxLineLength) {
        super();
        this._asciiToBase64 = new int[128];
        this._base64ToAsciiC = new char[64];
        this._base64ToAsciiB = new byte[64];
        this._name = name;
        this._usesPadding = usesPadding;
        this._paddingChar = paddingChar;
        this._maxLineLength = maxLineLength;
        final int length = s.length();
        if (length != 64) {
            throw new IllegalArgumentException("Base64Alphabet length must be exactly 64 (was " + length + ")");
        }
        s.getChars(0, length, this._base64ToAsciiC, 0);
        Arrays.fill(this._asciiToBase64, -1);
        for (int i = 0; i < length; ++i) {
            final char c = this._base64ToAsciiC[i];
            this._base64ToAsciiB[i] = (byte)c;
            this._asciiToBase64[c] = i;
        }
        if (usesPadding) {
            this._asciiToBase64[paddingChar] = -2;
        }
    }
    
    public Base64Variant(final Base64Variant base64Variant, final String s, final int n) {
        this(base64Variant, s, base64Variant._usesPadding, base64Variant._paddingChar, n);
    }
    
    public Base64Variant(final Base64Variant base64Variant, final String name, final boolean usesPadding, final char paddingChar, final int maxLineLength) {
        super();
        this._asciiToBase64 = new int[128];
        this._base64ToAsciiC = new char[64];
        this._base64ToAsciiB = new byte[64];
        this._name = name;
        final byte[] base64ToAsciiB = base64Variant._base64ToAsciiB;
        System.arraycopy(base64ToAsciiB, 0, this._base64ToAsciiB, 0, base64ToAsciiB.length);
        final char[] base64ToAsciiC = base64Variant._base64ToAsciiC;
        System.arraycopy(base64ToAsciiC, 0, this._base64ToAsciiC, 0, base64ToAsciiC.length);
        final int[] asciiToBase64 = base64Variant._asciiToBase64;
        System.arraycopy(asciiToBase64, 0, this._asciiToBase64, 0, asciiToBase64.length);
        this._usesPadding = usesPadding;
        this._paddingChar = paddingChar;
        this._maxLineLength = maxLineLength;
    }
    
    protected Object readResolve() {
        return Base64Variants.valueOf(this._name);
    }
    
    public String getName() {
        return this._name;
    }
    
    public boolean usesPadding() {
        return this._usesPadding;
    }
    
    public boolean usesPaddingChar(final char c) {
        return c == this._paddingChar;
    }
    
    public boolean usesPaddingChar(final int n) {
        return n == this._paddingChar;
    }
    
    public char getPaddingChar() {
        return this._paddingChar;
    }
    
    public byte getPaddingByte() {
        return (byte)this._paddingChar;
    }
    
    public int getMaxLineLength() {
        return this._maxLineLength;
    }
    
    public int decodeBase64Char(final char c) {
        return (c <= '\u007f') ? this._asciiToBase64[c] : -1;
    }
    
    public int decodeBase64Char(final int n) {
        return (n <= 127) ? this._asciiToBase64[n] : -1;
    }
    
    public int decodeBase64Byte(final byte b) {
        if (b < 0) {
            return -1;
        }
        return this._asciiToBase64[b];
    }
    
    public char encodeBase64BitsAsChar(final int n) {
        return this._base64ToAsciiC[n];
    }
    
    public int encodeBase64Chunk(final int n, final char[] array, int n2) {
        array[n2++] = this._base64ToAsciiC[n >> 18 & 0x3F];
        array[n2++] = this._base64ToAsciiC[n >> 12 & 0x3F];
        array[n2++] = this._base64ToAsciiC[n >> 6 & 0x3F];
        array[n2++] = this._base64ToAsciiC[n & 0x3F];
        return n2;
    }
    
    public void encodeBase64Chunk(final StringBuilder sb, final int n) {
        sb.append(this._base64ToAsciiC[n >> 18 & 0x3F]);
        sb.append(this._base64ToAsciiC[n >> 12 & 0x3F]);
        sb.append(this._base64ToAsciiC[n >> 6 & 0x3F]);
        sb.append(this._base64ToAsciiC[n & 0x3F]);
    }
    
    public int encodeBase64Partial(final int n, final int n2, final char[] array, int n3) {
        array[n3++] = this._base64ToAsciiC[n >> 18 & 0x3F];
        array[n3++] = this._base64ToAsciiC[n >> 12 & 0x3F];
        if (this._usesPadding) {
            array[n3++] = ((n2 == 2) ? this._base64ToAsciiC[n >> 6 & 0x3F] : this._paddingChar);
            array[n3++] = this._paddingChar;
        }
        else if (n2 == 2) {
            array[n3++] = this._base64ToAsciiC[n >> 6 & 0x3F];
        }
        return n3;
    }
    
    public void encodeBase64Partial(final StringBuilder sb, final int n, final int n2) {
        sb.append(this._base64ToAsciiC[n >> 18 & 0x3F]);
        sb.append(this._base64ToAsciiC[n >> 12 & 0x3F]);
        if (this._usesPadding) {
            sb.append((n2 == 2) ? this._base64ToAsciiC[n >> 6 & 0x3F] : this._paddingChar);
            sb.append(this._paddingChar);
        }
        else if (n2 == 2) {
            sb.append(this._base64ToAsciiC[n >> 6 & 0x3F]);
        }
    }
    
    public byte encodeBase64BitsAsByte(final int n) {
        return this._base64ToAsciiB[n];
    }
    
    public int encodeBase64Chunk(final int n, final byte[] array, int n2) {
        array[n2++] = this._base64ToAsciiB[n >> 18 & 0x3F];
        array[n2++] = this._base64ToAsciiB[n >> 12 & 0x3F];
        array[n2++] = this._base64ToAsciiB[n >> 6 & 0x3F];
        array[n2++] = this._base64ToAsciiB[n & 0x3F];
        return n2;
    }
    
    public int encodeBase64Partial(final int n, final int n2, final byte[] array, int n3) {
        array[n3++] = this._base64ToAsciiB[n >> 18 & 0x3F];
        array[n3++] = this._base64ToAsciiB[n >> 12 & 0x3F];
        if (this._usesPadding) {
            final byte b = (byte)this._paddingChar;
            array[n3++] = ((n2 == 2) ? this._base64ToAsciiB[n >> 6 & 0x3F] : b);
            array[n3++] = b;
        }
        else if (n2 == 2) {
            array[n3++] = this._base64ToAsciiB[n >> 6 & 0x3F];
        }
        return n3;
    }
    
    public String encode(final byte[] array) {
        return this.encode(array, false);
    }
    
    public String encode(final byte[] array, final boolean b) {
        final int length = array.length;
        final StringBuilder sb = new StringBuilder(length + (length >> 2) + (length >> 3));
        if (b) {
            sb.append('\"');
        }
        int n = this.getMaxLineLength() >> 2;
        int i = 0;
        while (i <= length - 3) {
            this.encodeBase64Chunk(sb, (array[i++] << 8 | (array[i++] & 0xFF)) << 8 | (array[i++] & 0xFF));
            if (--n <= 0) {
                sb.append('\\');
                sb.append('n');
                n = this.getMaxLineLength() >> 2;
            }
        }
        final int n2 = length - i;
        if (n2 > 0) {
            int n3 = array[i++] << 16;
            if (n2 == 2) {
                n3 |= (array[i++] & 0xFF) << 8;
            }
            this.encodeBase64Partial(sb, n3, n2);
        }
        if (b) {
            sb.append('\"');
        }
        return sb.toString();
    }
    
    public byte[] decode(final String s) throws IllegalArgumentException {
        final ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();
        this.decode(s, byteArrayBuilder);
        return byteArrayBuilder.toByteArray();
    }
    
    public void decode(final String s, final ByteArrayBuilder byteArrayBuilder) throws IllegalArgumentException {
        int i = 0;
        final int length = s.length();
        while (i < length) {
            final char char1 = s.charAt(i++);
            if (char1 > ' ') {
                final int decodeBase64Char = this.decodeBase64Char(char1);
                if (decodeBase64Char < 0) {
                    this._reportInvalidBase64(char1, 0, null);
                }
                final int n = decodeBase64Char;
                if (i >= length) {
                    this._reportBase64EOF();
                }
                final char char2 = s.charAt(i++);
                final int decodeBase64Char2 = this.decodeBase64Char(char2);
                if (decodeBase64Char2 < 0) {
                    this._reportInvalidBase64(char2, 1, null);
                }
                final int n2 = n << 6 | decodeBase64Char2;
                if (i >= length) {
                    if (!this.usesPadding()) {
                        byteArrayBuilder.append(n2 >> 4);
                        break;
                    }
                    this._reportBase64EOF();
                }
                final char char3 = s.charAt(i++);
                final int decodeBase64Char3 = this.decodeBase64Char(char3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        this._reportInvalidBase64(char3, 2, null);
                    }
                    if (i >= length) {
                        this._reportBase64EOF();
                    }
                    final char char4 = s.charAt(i++);
                    if (!this.usesPaddingChar(char4)) {
                        this._reportInvalidBase64(char4, 3, "expected padding character '" + this.getPaddingChar() + "'");
                    }
                    byteArrayBuilder.append(n2 >> 4);
                    continue;
                }
                final int n3 = n2 << 6 | decodeBase64Char3;
                if (i >= length) {
                    if (!this.usesPadding()) {
                        byteArrayBuilder.appendTwoBytes(n3 >> 2);
                        break;
                    }
                    this._reportBase64EOF();
                }
                final char char5 = s.charAt(i++);
                final int decodeBase64Char4 = this.decodeBase64Char(char5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        this._reportInvalidBase64(char5, 3, null);
                    }
                    byteArrayBuilder.appendTwoBytes(n3 >> 2);
                }
                else {
                    byteArrayBuilder.appendThreeBytes(n3 << 6 | decodeBase64Char4);
                }
                continue;
            }
        }
    }
    
    @Override
    public String toString() {
        return this._name;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this;
    }
    
    @Override
    public int hashCode() {
        return this._name.hashCode();
    }
    
    protected void _reportInvalidBase64(final char c, final int n, final String s) throws IllegalArgumentException {
        String s2;
        if (c <= ' ') {
            s2 = "Illegal white space character (code 0x" + Integer.toHexString(c) + ") as character #" + (n + 1) + " of 4-char base64 unit: can only used between units";
        }
        else if (this.usesPaddingChar(c)) {
            s2 = "Unexpected padding character ('" + this.getPaddingChar() + "') as character #" + (n + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        }
        else if (!Character.isDefined(c) || Character.isISOControl(c)) {
            s2 = "Illegal character (code 0x" + Integer.toHexString(c) + ") in base64 content";
        }
        else {
            s2 = "Illegal character '" + c + "' (code 0x" + Integer.toHexString(c) + ") in base64 content";
        }
        if (s != null) {
            s2 = s2 + ": " + s;
        }
        throw new IllegalArgumentException(s2);
    }
    
    protected void _reportBase64EOF() throws IllegalArgumentException {
        throw new IllegalArgumentException("Unexpected end-of-String in base64 content");
    }
}
