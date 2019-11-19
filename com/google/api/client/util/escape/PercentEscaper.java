package com.google.api.client.util.escape;

public class PercentEscaper extends UnicodeEscaper
{
    public static final String SAFECHARS_URLENCODER = "-_.*";
    public static final String SAFEPATHCHARS_URLENCODER = "-_.!~*'()@:$&,;=";
    public static final String SAFE_PLUS_RESERVED_CHARS_URLENCODER = "-_.!~*'()@:$&,;=+/?";
    public static final String SAFEUSERINFOCHARS_URLENCODER = "-_.!~*'():$&,;=";
    public static final String SAFEQUERYSTRINGCHARS_URLENCODER = "-_.!~*'()@:$,;/?:";
    private static final char[] URI_ESCAPED_SPACE;
    private static final char[] UPPER_HEX_DIGITS;
    private final boolean plusForSpace;
    private final boolean[] safeOctets;
    
    public PercentEscaper(final String s, final boolean plusForSpace) {
        super();
        if (s.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        if (plusForSpace && s.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        if (s.contains("%")) {
            throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
        }
        this.plusForSpace = plusForSpace;
        this.safeOctets = createSafeOctets(s);
    }
    
    private static boolean[] createSafeOctets(final String s) {
        int max = 122;
        final char[] charArray;
        final char[] array = charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            max = Math.max(charArray[i], max);
        }
        final boolean[] array2 = new boolean[max + 1];
        for (int j = 48; j <= 57; ++j) {
            array2[j] = true;
        }
        for (int k = 65; k <= 90; ++k) {
            array2[k] = true;
        }
        for (int l = 97; l <= 122; ++l) {
            array2[l] = true;
        }
        final char[] array3 = array;
        for (int length2 = array3.length, n = 0; n < length2; ++n) {
            array2[array3[n]] = true;
        }
        return array2;
    }
    
    @Override
    protected int nextEscapeIndex(final CharSequence charSequence, int i, final int n) {
        while (i < n) {
            final char char1 = charSequence.charAt(i);
            if (char1 >= this.safeOctets.length) {
                break;
            }
            if (!this.safeOctets[char1]) {
                break;
            }
            ++i;
        }
        return i;
    }
    
    @Override
    public String escape(final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= this.safeOctets.length || !this.safeOctets[char1]) {
                return this.escapeSlow(s, i);
            }
        }
        return s;
    }
    
    @Override
    protected char[] escape(int n) {
        if (n < this.safeOctets.length && this.safeOctets[n]) {
            return null;
        }
        if (n == 32 && this.plusForSpace) {
            return PercentEscaper.URI_ESCAPED_SPACE;
        }
        if (n <= 127) {
            return new char[] { '%', PercentEscaper.UPPER_HEX_DIGITS[n >>> 4], PercentEscaper.UPPER_HEX_DIGITS[n & 0xF] };
        }
        if (n <= 2047) {
            final char[] array = { '%', '\0', '\0', '%', '\0', PercentEscaper.UPPER_HEX_DIGITS[n & 0xF] };
            n >>>= 4;
            array[4] = PercentEscaper.UPPER_HEX_DIGITS[0x8 | (n & 0x3)];
            n >>>= 2;
            array[2] = PercentEscaper.UPPER_HEX_DIGITS[n & 0xF];
            n >>>= 4;
            array[1] = PercentEscaper.UPPER_HEX_DIGITS[0xC | n];
            return array;
        }
        if (n <= 65535) {
            final char[] array2 = { '%', 'E', '\0', '%', '\0', '\0', '%', '\0', PercentEscaper.UPPER_HEX_DIGITS[n & 0xF] };
            n >>>= 4;
            array2[7] = PercentEscaper.UPPER_HEX_DIGITS[0x8 | (n & 0x3)];
            n >>>= 2;
            array2[5] = PercentEscaper.UPPER_HEX_DIGITS[n & 0xF];
            n >>>= 4;
            array2[4] = PercentEscaper.UPPER_HEX_DIGITS[0x8 | (n & 0x3)];
            n >>>= 2;
            array2[2] = PercentEscaper.UPPER_HEX_DIGITS[n];
            return array2;
        }
        if (n <= 1114111) {
            final char[] array3 = { '%', 'F', '\0', '%', '\0', '\0', '%', '\0', '\0', '%', '\0', PercentEscaper.UPPER_HEX_DIGITS[n & 0xF] };
            n >>>= 4;
            array3[10] = PercentEscaper.UPPER_HEX_DIGITS[0x8 | (n & 0x3)];
            n >>>= 2;
            array3[8] = PercentEscaper.UPPER_HEX_DIGITS[n & 0xF];
            n >>>= 4;
            array3[7] = PercentEscaper.UPPER_HEX_DIGITS[0x8 | (n & 0x3)];
            n >>>= 2;
            array3[5] = PercentEscaper.UPPER_HEX_DIGITS[n & 0xF];
            n >>>= 4;
            array3[4] = PercentEscaper.UPPER_HEX_DIGITS[0x8 | (n & 0x3)];
            n >>>= 2;
            array3[2] = PercentEscaper.UPPER_HEX_DIGITS[n & 0x7];
            return array3;
        }
        throw new IllegalArgumentException("Invalid unicode character value " + n);
    }
    
    static {
        URI_ESCAPED_SPACE = new char[] { '+' };
        UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    }
}
