package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.BufferRecyclers;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;

public final class JsonStringEncoder
{
    private static final char[] HC;
    private static final byte[] HB;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected TextBuffer _text;
    protected ByteArrayBuilder _bytes;
    protected final char[] _qbuf;
    
    public JsonStringEncoder() {
        super();
        (this._qbuf = new char[6])[0] = '\\';
        this._qbuf[2] = '0';
        this._qbuf[3] = '0';
    }
    
    @Deprecated
    public static JsonStringEncoder getInstance() {
        return BufferRecyclers.getJsonStringEncoder();
    }
    
    public char[] quoteAsString(final String s) {
        TextBuffer text = this._text;
        if (text == null) {
            text = (this._text = new TextBuffer(null));
        }
        char[] array = text.emptyAndGetCurrentSegment();
        final int[] get7BitOutputEscapes = CharTypes.get7BitOutputEscapes();
        final int length = get7BitOutputEscapes.length;
        int i = 0;
        final int length2 = s.length();
        int currentLength = 0;
    Label_0261:
        while (i < length2) {
            while (true) {
                final char char1 = s.charAt(i);
                if (char1 < length && get7BitOutputEscapes[char1] != 0) {
                    final char char2 = s.charAt(i++);
                    final int n = get7BitOutputEscapes[char2];
                    final int n2 = (n < 0) ? this._appendNumeric(char2, this._qbuf) : this._appendNamed(n, this._qbuf);
                    if (currentLength + n2 > array.length) {
                        final int n3 = array.length - currentLength;
                        if (n3 > 0) {
                            System.arraycopy(this._qbuf, 0, array, currentLength, n3);
                        }
                        array = text.finishCurrentSegment();
                        final int n4 = n2 - n3;
                        System.arraycopy(this._qbuf, n3, array, 0, n4);
                        currentLength = n4;
                    }
                    else {
                        System.arraycopy(this._qbuf, 0, array, currentLength, n2);
                        currentLength += n2;
                    }
                    break;
                }
                if (currentLength >= array.length) {
                    array = text.finishCurrentSegment();
                    currentLength = 0;
                }
                array[currentLength++] = char1;
                if (++i >= length2) {
                    break Label_0261;
                }
            }
        }
        text.setCurrentLength(currentLength);
        return text.contentsAsArray();
    }
    
    public void quoteAsString(final CharSequence charSequence, final StringBuilder sb) {
        final int[] get7BitOutputEscapes = CharTypes.get7BitOutputEscapes();
        final int length = get7BitOutputEscapes.length;
        int i = 0;
        final int length2 = charSequence.length();
    Label_0140:
        while (i < length2) {
            while (true) {
                final char char1 = charSequence.charAt(i);
                if (char1 < length && get7BitOutputEscapes[char1] != 0) {
                    final char char2 = charSequence.charAt(i++);
                    final int n = get7BitOutputEscapes[char2];
                    sb.append(this._qbuf, 0, (n < 0) ? this._appendNumeric(char2, this._qbuf) : this._appendNamed(n, this._qbuf));
                    break;
                }
                sb.append(char1);
                if (++i >= length2) {
                    break Label_0140;
                }
            }
        }
    }
    
    public byte[] quoteAsUTF8(final String s) {
        ByteArrayBuilder bytes = this._bytes;
        if (bytes == null) {
            bytes = (this._bytes = new ByteArrayBuilder(null));
        }
        int i = 0;
        final int length = s.length();
        int appendByte = 0;
        byte[] array = bytes.resetAndGetFirstSegment();
    Label_0492:
        while (i < length) {
            final int[] get7BitOutputEscapes = CharTypes.get7BitOutputEscapes();
            while (true) {
                final char char1 = s.charAt(i);
                if (char1 <= '\u007f' && get7BitOutputEscapes[char1] == 0) {
                    if (appendByte >= array.length) {
                        array = bytes.finishCurrentSegment();
                        appendByte = 0;
                    }
                    array[appendByte++] = (byte)char1;
                    if (++i >= length) {
                        break Label_0492;
                    }
                    continue;
                }
                else {
                    if (appendByte >= array.length) {
                        array = bytes.finishCurrentSegment();
                        appendByte = 0;
                    }
                    final char char2 = s.charAt(i++);
                    if (char2 <= '\u007f') {
                        appendByte = this._appendByte(char2, get7BitOutputEscapes[char2], bytes, appendByte);
                        array = bytes.getCurrentSegment();
                        break;
                    }
                    int n;
                    if (char2 <= '\u07ff') {
                        array[appendByte++] = (byte)(0xC0 | char2 >> 6);
                        n = (0x80 | (char2 & '?'));
                    }
                    else if (char2 < '\ud800' || char2 > '\udfff') {
                        array[appendByte++] = (byte)(0xE0 | char2 >> 12);
                        if (appendByte >= array.length) {
                            array = bytes.finishCurrentSegment();
                            appendByte = 0;
                        }
                        array[appendByte++] = (byte)(0x80 | (char2 >> 6 & 0x3F));
                        n = (0x80 | (char2 & '?'));
                    }
                    else {
                        if (char2 > '\udbff') {
                            _illegal(char2);
                        }
                        if (i >= length) {
                            _illegal(char2);
                        }
                        final int convert = _convert(char2, s.charAt(i++));
                        if (convert > 1114111) {
                            _illegal(convert);
                        }
                        array[appendByte++] = (byte)(0xF0 | convert >> 18);
                        if (appendByte >= array.length) {
                            array = bytes.finishCurrentSegment();
                            appendByte = 0;
                        }
                        array[appendByte++] = (byte)(0x80 | (convert >> 12 & 0x3F));
                        if (appendByte >= array.length) {
                            array = bytes.finishCurrentSegment();
                            appendByte = 0;
                        }
                        array[appendByte++] = (byte)(0x80 | (convert >> 6 & 0x3F));
                        n = (0x80 | (convert & 0x3F));
                    }
                    if (appendByte >= array.length) {
                        array = bytes.finishCurrentSegment();
                        appendByte = 0;
                    }
                    array[appendByte++] = (byte)n;
                    break;
                }
            }
        }
        return this._bytes.completeAndCoalesce(appendByte);
    }
    
    public byte[] encodeAsUTF8(final String s) {
        ByteArrayBuilder bytes = this._bytes;
        if (bytes == null) {
            bytes = (this._bytes = new ByteArrayBuilder(null));
        }
        int i = 0;
        final int length = s.length();
        int n = 0;
        byte[] array = bytes.resetAndGetFirstSegment();
        int n2 = array.length;
    Label_0443:
        while (i < length) {
            int j;
            for (j = s.charAt(i++); j <= 127; j = s.charAt(i++)) {
                if (n >= n2) {
                    array = bytes.finishCurrentSegment();
                    n2 = array.length;
                    n = 0;
                }
                array[n++] = (byte)j;
                if (i >= length) {
                    break Label_0443;
                }
            }
            if (n >= n2) {
                array = bytes.finishCurrentSegment();
                n2 = array.length;
                n = 0;
            }
            if (j < 2048) {
                array[n++] = (byte)(0xC0 | j >> 6);
            }
            else if (j < 55296 || j > 57343) {
                array[n++] = (byte)(0xE0 | j >> 12);
                if (n >= n2) {
                    array = bytes.finishCurrentSegment();
                    n2 = array.length;
                    n = 0;
                }
                array[n++] = (byte)(0x80 | (j >> 6 & 0x3F));
            }
            else {
                if (j > 56319) {
                    _illegal(j);
                }
                if (i >= length) {
                    _illegal(j);
                }
                j = _convert(j, s.charAt(i++));
                if (j > 1114111) {
                    _illegal(j);
                }
                array[n++] = (byte)(0xF0 | j >> 18);
                if (n >= n2) {
                    array = bytes.finishCurrentSegment();
                    n2 = array.length;
                    n = 0;
                }
                array[n++] = (byte)(0x80 | (j >> 12 & 0x3F));
                if (n >= n2) {
                    array = bytes.finishCurrentSegment();
                    n2 = array.length;
                    n = 0;
                }
                array[n++] = (byte)(0x80 | (j >> 6 & 0x3F));
            }
            if (n >= n2) {
                array = bytes.finishCurrentSegment();
                n2 = array.length;
                n = 0;
            }
            array[n++] = (byte)(0x80 | (j & 0x3F));
        }
        return this._bytes.completeAndCoalesce(n);
    }
    
    private int _appendNumeric(final int n, final char[] array) {
        array[1] = 'u';
        array[4] = JsonStringEncoder.HC[n >> 4];
        array[5] = JsonStringEncoder.HC[n & 0xF];
        return 6;
    }
    
    private int _appendNamed(final int n, final char[] array) {
        array[1] = (char)n;
        return 2;
    }
    
    private int _appendByte(int n, final int n2, final ByteArrayBuilder byteArrayBuilder, final int currentSegmentLength) {
        byteArrayBuilder.setCurrentSegmentLength(currentSegmentLength);
        byteArrayBuilder.append(92);
        if (n2 < 0) {
            byteArrayBuilder.append(117);
            if (n > 255) {
                final int n3 = n >> 8;
                byteArrayBuilder.append(JsonStringEncoder.HB[n3 >> 4]);
                byteArrayBuilder.append(JsonStringEncoder.HB[n3 & 0xF]);
                n &= 0xFF;
            }
            else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byteArrayBuilder.append(JsonStringEncoder.HB[n >> 4]);
            byteArrayBuilder.append(JsonStringEncoder.HB[n & 0xF]);
        }
        else {
            byteArrayBuilder.append((byte)n2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }
    
    private static int _convert(final int n, final int n2) {
        if (n2 < 56320 || n2 > 57343) {
            throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(n) + ", second 0x" + Integer.toHexString(n2) + "; illegal combination");
        }
        return 65536 + (n - 55296 << 10) + (n2 - 56320);
    }
    
    private static void _illegal(final int n) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(n));
    }
    
    static {
        HC = CharTypes.copyHexChars();
        HB = CharTypes.copyHexBytes();
    }
}
