package com.google.api.client.util.escape;

public abstract class UnicodeEscaper extends Escaper
{
    private static final int DEST_PAD = 32;
    
    public UnicodeEscaper() {
        super();
    }
    
    protected abstract char[] escape(final int p0);
    
    protected abstract int nextEscapeIndex(final CharSequence p0, final int p1, final int p2);
    
    @Override
    public abstract String escape(final String p0);
    
    protected final String escapeSlow(final String s, int i) {
        final int length = s.length();
        char[] array = Platform.charBufferFromThreadLocal();
        int n = 0;
        int n2 = 0;
        while (i < length) {
            final int codePoint = codePointAt(s, i, length);
            if (codePoint < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            final char[] escape = this.escape(codePoint);
            final int n3 = i + (Character.isSupplementaryCodePoint(codePoint) ? 2 : 1);
            if (escape != null) {
                final int n4 = i - n2;
                final int n5 = n + n4 + escape.length;
                if (array.length < n5) {
                    array = growBuffer(array, n, n5 + length - i + 32);
                }
                if (n4 > 0) {
                    s.getChars(n2, i, array, n);
                    n += n4;
                }
                if (escape.length > 0) {
                    System.arraycopy(escape, 0, array, n, escape.length);
                    n += escape.length;
                }
                n2 = n3;
            }
            i = this.nextEscapeIndex(s, n3, length);
        }
        final int n6 = length - n2;
        if (n6 > 0) {
            final int n7 = n + n6;
            if (array.length < n7) {
                array = growBuffer(array, n, n7);
            }
            s.getChars(n2, length, array, n);
            n = n7;
        }
        return new String(array, 0, n);
    }
    
    protected static int codePointAt(final CharSequence charSequence, int n, final int n2) {
        if (n >= n2) {
            throw new IndexOutOfBoundsException("Index exceeds specified range");
        }
        final char char1 = charSequence.charAt(n++);
        if (char1 < '\ud800' || char1 > '\udfff') {
            return char1;
        }
        if (char1 > '\udbff') {
            throw new IllegalArgumentException("Unexpected low surrogate character '" + char1 + "' with value " + (int)char1 + " at index " + (n - 1));
        }
        if (n == n2) {
            return -char1;
        }
        final char char2 = charSequence.charAt(n);
        if (Character.isLowSurrogate(char2)) {
            return Character.toCodePoint(char1, char2);
        }
        throw new IllegalArgumentException("Expected low surrogate but got char '" + char2 + "' with value " + (int)char2 + " at index " + n);
    }
    
    private static char[] growBuffer(final char[] array, final int n, final int n2) {
        final char[] array2 = new char[n2];
        if (n > 0) {
            System.arraycopy(array, 0, array2, 0, n);
        }
        return array2;
    }
}
