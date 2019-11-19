package com.google.api.client.http;

import java.io.IOException;
import java.io.OutputStream;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StreamingContent;

public final class HttpEncodingStreamingContent implements StreamingContent
{
    private final StreamingContent content;
    private final HttpEncoding encoding;
    
    public HttpEncodingStreamingContent(final StreamingContent streamingContent, final HttpEncoding httpEncoding) {
        super();
        this.content = Preconditions.<StreamingContent>checkNotNull(streamingContent);
        this.encoding = Preconditions.<HttpEncoding>checkNotNull(httpEncoding);
    }
    
    @Override
    public void writeTo(final OutputStream outputStream) throws IOException {
        this.encoding.encode(this.content, outputStream);
    }
    
    public StreamingContent getContent() {
        return this.content;
    }
    
    public HttpEncoding getEncoding() {
        return this.encoding;
    }
}
