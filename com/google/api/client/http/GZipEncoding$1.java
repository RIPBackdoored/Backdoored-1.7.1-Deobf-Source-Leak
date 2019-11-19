package com.google.api.client.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;

class GZipEncoding$1 extends BufferedOutputStream {
    final /* synthetic */ GZipEncoding this$0;
    
    GZipEncoding$1(final GZipEncoding this$0, final OutputStream outputStream) {
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
}