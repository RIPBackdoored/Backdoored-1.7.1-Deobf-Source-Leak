package com.google.api.client.http.apache;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StreamingContent;
import org.apache.http.entity.AbstractHttpEntity;

final class ContentEntity extends AbstractHttpEntity
{
    private final long contentLength;
    private final StreamingContent streamingContent;
    
    ContentEntity(final long contentLength, final StreamingContent streamingContent) {
        super();
        this.contentLength = contentLength;
        this.streamingContent = Preconditions.<StreamingContent>checkNotNull(streamingContent);
    }
    
    public InputStream getContent() {
        throw new UnsupportedOperationException();
    }
    
    public long getContentLength() {
        return this.contentLength;
    }
    
    public boolean isRepeatable() {
        return false;
    }
    
    public boolean isStreaming() {
        return true;
    }
    
    public void writeTo(final OutputStream outputStream) throws IOException {
        if (this.contentLength != 0L) {
            this.streamingContent.writeTo(outputStream);
        }
    }
}
