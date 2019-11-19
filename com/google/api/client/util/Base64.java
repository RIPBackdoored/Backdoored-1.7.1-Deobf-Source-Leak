package com.google.api.client.util;

public class Base64
{
    public static byte[] encodeBase64(final byte[] array) {
        return com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64.encodeBase64(array);
    }
    
    public static String encodeBase64String(final byte[] array) {
        return com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64.encodeBase64String(array);
    }
    
    public static byte[] encodeBase64URLSafe(final byte[] array) {
        return com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64.encodeBase64URLSafe(array);
    }
    
    public static String encodeBase64URLSafeString(final byte[] array) {
        return com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(array);
    }
    
    public static byte[] decodeBase64(final byte[] array) {
        return com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64.decodeBase64(array);
    }
    
    public static byte[] decodeBase64(final String s) {
        return com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64.decodeBase64(s);
    }
    
    private Base64() {
        super();
    }
}
