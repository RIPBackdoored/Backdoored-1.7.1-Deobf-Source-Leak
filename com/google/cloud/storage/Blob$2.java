package com.google.cloud.storage;

import java.io.OutputStream;
import com.google.common.io.CountingOutputStream;
import java.util.Map;
import com.google.cloud.storage.spi.v1.StorageRpc;

class Blob$2 implements Runnable {
    final /* synthetic */ StorageRpc val$storageRpc;
    final /* synthetic */ Map val$requestOptions;
    final /* synthetic */ CountingOutputStream val$countingOutputStream;
    final /* synthetic */ Blob this$0;
    
    Blob$2(final Blob this$0, final StorageRpc val$storageRpc, final Map val$requestOptions, final CountingOutputStream val$countingOutputStream) {
        this.this$0 = this$0;
        this.val$storageRpc = val$storageRpc;
        this.val$requestOptions = val$requestOptions;
        this.val$countingOutputStream = val$countingOutputStream;
        super();
    }
    
    @Override
    public void run() {
        this.val$storageRpc.read(this.this$0.getBlobId().toPb(), this.val$requestOptions, this.val$countingOutputStream.getCount(), (OutputStream)this.val$countingOutputStream);
    }
}