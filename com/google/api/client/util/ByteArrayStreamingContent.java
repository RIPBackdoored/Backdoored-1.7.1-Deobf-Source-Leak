package com.google.api.client.util;

import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayStreamingContent implements StreamingContent
{
    private final byte[] byteArray;
    private final int offset;
    private final int length;
    
    public ByteArrayStreamingContent(final byte[] array) {
        this(array, 0, array.length);
    }
    
    public ByteArrayStreamingContent(final byte[] array, final int offset, final int length) {
        super();
        this.byteArray = Preconditions.<byte[]>checkNotNull(array);
        Preconditions.checkArgument(offset >= 0 && length >= 0 && offset + length <= array.length);
        this.offset = offset;
        this.length = length;
    }
    
    @Override
    public void writeTo(final OutputStream outputStream) throws IOException {
        outputStream.write(this.byteArray, this.offset, this.length);
        outputStream.flush();
    }
}
