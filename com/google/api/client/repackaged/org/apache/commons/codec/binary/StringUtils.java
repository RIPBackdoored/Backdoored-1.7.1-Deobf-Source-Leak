package com.google.api.client.repackaged.org.apache.commons.codec.binary;

import java.io.UnsupportedEncodingException;

public class StringUtils
{
    public StringUtils() {
        super();
    }
    
    public static byte[] getBytesIso8859_1(final String s) {
        return getBytesUnchecked(s, "ISO-8859-1");
    }
    
    public static byte[] getBytesUsAscii(final String s) {
        return getBytesUnchecked(s, "US-ASCII");
    }
    
    public static byte[] getBytesUtf16(final String s) {
        return getBytesUnchecked(s, "UTF-16");
    }
    
    public static byte[] getBytesUtf16Be(final String s) {
        return getBytesUnchecked(s, "UTF-16BE");
    }
    
    public static byte[] getBytesUtf16Le(final String s) {
        return getBytesUnchecked(s, "UTF-16LE");
    }
    
    public static byte[] getBytesUtf8(final String s) {
        return getBytesUnchecked(s, "UTF-8");
    }
    
    public static byte[] getBytesUnchecked(final String s, final String s2) {
        if (s == null) {
            return null;
        }
        try {
            return s.getBytes(s2);
        }
        catch (UnsupportedEncodingException ex) {
            throw newIllegalStateException(s2, ex);
        }
    }
    
    private static IllegalStateException newIllegalStateException(final String s, final UnsupportedEncodingException ex) {
        return new IllegalStateException(s + ": " + ex);
    }
    
    public static String newString(final byte[] array, final String s) {
        if (array == null) {
            return null;
        }
        try {
            return new String(array, s);
        }
        catch (UnsupportedEncodingException ex) {
            throw newIllegalStateException(s, ex);
        }
    }
    
    public static String newStringIso8859_1(final byte[] array) {
        return newString(array, "ISO-8859-1");
    }
    
    public static String newStringUsAscii(final byte[] array) {
        return newString(array, "US-ASCII");
    }
    
    public static String newStringUtf16(final byte[] array) {
        return newString(array, "UTF-16");
    }
    
    public static String newStringUtf16Be(final byte[] array) {
        return newString(array, "UTF-16BE");
    }
    
    public static String newStringUtf16Le(final byte[] array) {
        return newString(array, "UTF-16LE");
    }
    
    public static String newStringUtf8(final byte[] array) {
        return newString(array, "UTF-8");
    }
}
