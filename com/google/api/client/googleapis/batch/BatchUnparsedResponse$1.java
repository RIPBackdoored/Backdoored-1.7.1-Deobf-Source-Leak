package com.google.api.client.googleapis.batch;

import java.io.InputStream;
import java.io.FilterInputStream;

class BatchUnparsedResponse$1 extends FilterInputStream {
    final /* synthetic */ BatchUnparsedResponse this$0;
    
    BatchUnparsedResponse$1(final BatchUnparsedResponse this$0, final InputStream inputStream) {
        this.this$0 = this$0;
        super(inputStream);
    }
    
    @Override
    public void close() {
    }
}