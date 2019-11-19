package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.api.services.storage.model.StorageObject;
import java.util.concurrent.Callable;

class StorageImpl$5 implements Callable<StorageObject> {
    final /* synthetic */ StorageObject val$storedObject;
    final /* synthetic */ Map val$optionsMap;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$5(final StorageImpl this$0, final StorageObject val$storedObject, final Map val$optionsMap) {
        this.this$0 = this$0;
        this.val$storedObject = val$storedObject;
        this.val$optionsMap = val$optionsMap;
        super();
    }
    
    @Override
    public StorageObject call() {
        return StorageImpl.access$000(this.this$0).get(this.val$storedObject, this.val$optionsMap);
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}