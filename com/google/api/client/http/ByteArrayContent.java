package com.google.api.client.http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Preconditions;

public final class ByteArrayContent extends AbstractInputStreamContent
{
    private final byte[] byteArray;
    private final int offset;
    private final int length;
    
    public ByteArrayContent(final String s, final byte[] array) {
        this(s, array, 0, array.length);
    }
    
    public ByteArrayContent(final String s, final byte[] array, final int offset, final int length) {
        super(s);
        this.byteArray = Preconditions.<byte[]>checkNotNull(array);
        Preconditions.checkArgument(offset >= 0 && length >= 0 && offset + length <= array.length, "offset %s, length %s, array length %s", offset, length, array.length);
        this.offset = offset;
        this.length = length;
    }
    
    public static ByteArrayContent fromString(final String s, final String s2) {
        return new ByteArrayContent(s, StringUtils.getBytesUtf8(s2));
    }
    
    @Override
    public long getLength() {
        return this.length;
    }
    
    @Override
    public boolean retrySupported() {
        return true;
    }
    
    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.byteArray, this.offset, this.length);
    }
    
    @Override
    public ByteArrayContent setType(final String type) {
        return (ByteArrayContent)super.setType(type);
    }
    
    @Override
    public ByteArrayContent setCloseInputStream(final boolean closeInputStream) {
        return (ByteArrayContent)super.setCloseInputStream(closeInputStream);
    }
    
    @Override
    public /* bridge */ AbstractInputStreamContent setCloseInputStream(final boolean closeInputStream) {
        return this.setCloseInputStream(closeInputStream);
    }
    
    @Override
    public /* bridge */ AbstractInputStreamContent setType(final String type) {
        return this.setType(type);
    }
}
