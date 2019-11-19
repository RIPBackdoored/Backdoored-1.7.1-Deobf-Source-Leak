package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.cloud.Tuple;
import java.util.concurrent.Callable;

class BlobReadChannel$1 implements Callable<Tuple<String, byte[]>> {
    final /* synthetic */ int val$toRead;
    final /* synthetic */ BlobReadChannel this$0;
    
    BlobReadChannel$1(final BlobReadChannel this$0, final int val$toRead) {
        this.this$0 = this$0;
        this.val$toRead = val$toRead;
        super();
    }
    
    @Override
    public Tuple<String, byte[]> call() {
        return BlobReadChannel.access$300(this.this$0).read(BlobReadChannel.access$000(this.this$0), BlobReadChannel.access$100(this.this$0), BlobReadChannel.access$200(this.this$0), this.val$toRead);
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}