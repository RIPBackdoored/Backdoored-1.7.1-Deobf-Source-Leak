package com.google.api.client.util;

public static final class Section
{
    private final String title;
    private final byte[] base64decodedBytes;
    
    Section(final String s, final byte[] array) {
        super();
        this.title = Preconditions.<String>checkNotNull(s);
        this.base64decodedBytes = Preconditions.<byte[]>checkNotNull(array);
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public byte[] getBase64DecodedBytes() {
        return this.base64decodedBytes;
    }
}
