package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.api.services.storage.model.StorageObject;
import java.util.concurrent.Callable;

class StorageImpl$16 implements Callable<byte[]> {
    final /* synthetic */ StorageObject val$storageObject;
    final /* synthetic */ Map val$optionsMap;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$16(final StorageImpl this$0, final StorageObject val$storageObject, final Map val$optionsMap) {
        this.this$0 = this$0;
        this.val$storageObject = val$storageObject;
        this.val$optionsMap = val$optionsMap;
        super();
    }
    
    @Override
    public byte[] call() {
        return StorageImpl.access$000(this.this$0).load(this.val$storageObject, this.val$optionsMap);
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}