package com.google.api.client.http;

import java.util.zip.GZIPOutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import com.google.api.client.util.StreamingContent;

public class GZipEncoding implements HttpEncoding
{
    public GZipEncoding() {
        super();
    }
    
    @Override
    public String getName() {
        return "gzip";
    }
    
    @Override
    public void encode(final StreamingContent streamingContent, final OutputStream outputStream) throws IOException {
        final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new BufferedOutputStream(outputStream) {
            final /* synthetic */ GZipEncoding this$0;
            
            GZipEncoding$1(final OutputStream outputStream) {
                this.this$0 = this$0;
                super(outputStream);
            }
            
            @Override
            public void close() throws IOException {
                try {
                    this.flush();
                }
                catch (IOException ex) {}
            }
        });
        streamingContent.writeTo(gzipOutputStream);
        gzipOutputStream.close();
    }
}
