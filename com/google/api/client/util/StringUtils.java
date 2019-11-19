package com.google.api.client.util;

public class StringUtils
{
    public static final String LINE_SEPARATOR;
    
    public static byte[] getBytesUtf8(final String s) {
        return com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils.getBytesUtf8(s);
    }
    
    public static String newStringUtf8(final byte[] array) {
        return com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils.newStringUtf8(array);
    }
    
    private StringUtils() {
        super();
    }
    
    static {
        LINE_SEPARATOR = System.getProperty("line.separator");
    }
}
