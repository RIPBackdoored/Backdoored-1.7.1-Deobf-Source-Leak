package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import java.util.concurrent.Callable;

static final class BlobWriteChannel$2 implements Callable<String> {
    final /* synthetic */ StorageOptions val$options;
    final /* synthetic */ BlobInfo val$blob;
    final /* synthetic */ Map val$optionsMap;
    
    BlobWriteChannel$2(final StorageOptions val$options, final BlobInfo val$blob, final Map val$optionsMap) {
        this.val$options = val$options;
        this.val$blob = val$blob;
        this.val$optionsMap = val$optionsMap;
        super();
    }
    
    @Override
    public String call() {
        return this.val$options.getStorageRpcV1().open(this.val$blob.toPb(), this.val$optionsMap);
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}