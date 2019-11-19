package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.api.services.storage.model.HmacKeyMetadata;
import java.util.concurrent.Callable;

class StorageImpl$37 implements Callable<HmacKeyMetadata> {
    final /* synthetic */ HmacKey.HmacKeyMetadata val$hmacKeyMetadata;
    final /* synthetic */ UpdateHmacKeyOption[] val$options;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$37(final StorageImpl this$0, final HmacKey.HmacKeyMetadata val$hmacKeyMetadata, final UpdateHmacKeyOption[] val$options) {
        this.this$0 = this$0;
        this.val$hmacKeyMetadata = val$hmacKeyMetadata;
        this.val$options = val$options;
        super();
    }
    
    @Override
    public HmacKeyMetadata call() {
        return StorageImpl.access$000(this.this$0).updateHmacKey(this.val$hmacKeyMetadata.toPb(), StorageImpl.access$400(this.val$options));
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}