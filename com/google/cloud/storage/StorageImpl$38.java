package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import java.util.concurrent.Callable;

class StorageImpl$38 implements Callable<Void> {
    final /* synthetic */ HmacKey.HmacKeyMetadata val$metadata;
    final /* synthetic */ DeleteHmacKeyOption[] val$options;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$38(final StorageImpl this$0, final HmacKey.HmacKeyMetadata val$metadata, final DeleteHmacKeyOption[] val$options) {
        this.this$0 = this$0;
        this.val$metadata = val$metadata;
        this.val$options = val$options;
        super();
    }
    
    @Override
    public Void call() {
        StorageImpl.access$000(this.this$0).deleteHmacKey(this.val$metadata.toPb(), StorageImpl.access$400(this.val$options));
        return null;
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}