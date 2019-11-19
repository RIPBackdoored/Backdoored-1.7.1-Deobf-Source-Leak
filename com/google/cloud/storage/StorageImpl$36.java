package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.api.services.storage.model.HmacKeyMetadata;
import java.util.concurrent.Callable;

class StorageImpl$36 implements Callable<HmacKeyMetadata> {
    final /* synthetic */ String val$accessId;
    final /* synthetic */ GetHmacKeyOption[] val$options;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$36(final StorageImpl this$0, final String val$accessId, final GetHmacKeyOption[] val$options) {
        this.this$0 = this$0;
        this.val$accessId = val$accessId;
        this.val$options = val$options;
        super();
    }
    
    @Override
    public HmacKeyMetadata call() {
        return StorageImpl.access$000(this.this$0).getHmacKey(this.val$accessId, StorageImpl.access$400(this.val$options));
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}