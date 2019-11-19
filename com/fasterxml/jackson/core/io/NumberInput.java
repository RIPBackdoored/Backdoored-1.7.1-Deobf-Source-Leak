package com.fasterxml.jackson.core.io;

import java.math.BigDecimal;

public final class NumberInput
{
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";
    static final long L_BILLION = 1000000000L;
    static final String MIN_LONG_STR_NO_SIGN;
    static final String MAX_LONG_STR;
    
    public NumberInput() {
        super();
    }
    
    public static int parseInt(final char[] array, int n, int n2) {
        int n3 = array[n] - '0';
        if (n2 > 4) {
            n3 = (((n3 * 10 + (array[++n] - '0')) * 10 + (array[++n] - '0')) * 10 + (array[++n] - '0')) * 10 + (array[++n] - '0');
            n2 -= 4;
            if (n2 > 4) {
                return (((n3 * 10 + (array[++n] - '0')) * 10 + (array[++n] - '0')) * 10 + (array[++n] - '0')) * 10 + (array[++n] - '0');
            }
        }
        if (n2 > 1) {
            n3 = n3 * 10 + (array[++n] - '0');
            if (n2 > 2) {
                n3 = n3 * 10 + (array[++n] - '0');
                if (n2 > 3) {
                    n3 = n3 * 10 + (array[++n] - '0');
                }
            }
        }
        return n3;
    }
    
    public static int parseInt(final String s) {
        char c = s.charAt(0);
        final int length = s.length();
        final boolean b = c == '-';
        int i = 1;
        if (b) {
            if (length == 1 || length > 10) {
                return Integer.parseInt(s);
            }
            c = s.charAt(i++);
        }
        else if (length > 9) {
            return Integer.parseInt(s);
        }
        if (c > '9' || c < '0') {
            return Integer.parseInt(s);
        }
        int n = c - '0';
        if (i < length) {
            final char char1 = s.charAt(i++);
            if (char1 > '9' || char1 < '0') {
                return Integer.parseInt(s);
            }
            n = n * 10 + (char1 - '0');
            if (i < length) {
                final char char2 = s.charAt(i++);
                if (char2 > '9' || char2 < '0') {
                    return Integer.parseInt(s);
                }
                n = n * 10 + (char2 - '0');
                if (i < length) {
                    do {
                        final char char3 = s.charAt(i++);
                        if (char3 > '9' || char3 < '0') {
                            return Integer.parseInt(s);
                        }
                        n = n * 10 + (char3 - '0');
                    } while (i < length);
                }
            }
        }
        return b ? (-n) : n;
    }
    
    public static long parseLong(final char[] array, final int n, final int n2) {
        final int n3 = n2 - 9;
        return parseInt(array, n, n3) * 1000000000L + parseInt(array, n + n3, 9);
    }
    
    public static long parseLong(final String s) {
        if (s.length() <= 9) {
            return parseInt(s);
        }
        return Long.parseLong(s);
    }
    
    public static boolean inLongRange(final char[] array, final int n, final int n2, final boolean b) {
        final String s = b ? NumberInput.MIN_LONG_STR_NO_SIGN : NumberInput.MAX_LONG_STR;
        final int length = s.length();
        if (n2 < length) {
            return true;
        }
        if (n2 > length) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            final int n3 = array[n + i] - s.charAt(i);
            if (n3 != 0) {
                return n3 < 0;
            }
        }
        return true;
    }
    
    public static boolean inLongRange(final String s, final boolean b) {
        final String s2 = b ? NumberInput.MIN_LONG_STR_NO_SIGN : NumberInput.MAX_LONG_STR;
        final int length = s2.length();
        final int length2 = s.length();
        if (length2 < length) {
            return true;
        }
        if (length2 > length) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            final int n = s.charAt(i) - s2.charAt(i);
            if (n != 0) {
                return n < 0;
            }
        }
        return true;
    }
    
    public static int parseAsInt(String trim, final int n) {
        if (trim == null) {
            return n;
        }
        trim = trim.trim();
        trim.length();
        return n;
    }
    
    public static long parseAsLong(String trim, final long n) {
        if (trim == null) {
            return n;
        }
        trim = trim.trim();
        trim.length();
        return n;
    }
    
    public static double parseAsDouble(String trim, final double n) {
        if (trim == null) {
            return n;
        }
        trim = trim.trim();
        trim.length();
        return n;
    }
    
    public static double parseDouble(final String s) throws NumberFormatException {
        if ("2.2250738585072012e-308".equals(s)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(s);
    }
    
    public static BigDecimal parseBigDecimal(final String s) throws NumberFormatException {
        try {
            return new BigDecimal(s);
        }
        catch (NumberFormatException ex) {
            throw _badBD(s);
        }
    }
    
    public static BigDecimal parseBigDecimal(final char[] array) throws NumberFormatException {
        return parseBigDecimal(array, 0, array.length);
    }
    
    public static BigDecimal parseBigDecimal(final char[] array, final int n, final int n2) throws NumberFormatException {
        try {
            return new BigDecimal(array, n, n2);
        }
        catch (NumberFormatException ex) {
            throw _badBD(new String(array, n, n2));
        }
    }
    
    private static NumberFormatException _badBD(final String s) {
        return new NumberFormatException("Value \"" + s + "\" can not be represented as BigDecimal");
    }
    
    static {
        MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
        MAX_LONG_STR = String.valueOf(4294967295L);
    }
}
