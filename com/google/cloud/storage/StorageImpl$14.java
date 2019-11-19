package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import java.util.List;
import com.google.api.services.storage.model.StorageObject;
import java.util.concurrent.Callable;

class StorageImpl$14 implements Callable<StorageObject> {
    final /* synthetic */ List val$sources;
    final /* synthetic */ StorageObject val$target;
    final /* synthetic */ Map val$targetOptions;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$14(final StorageImpl this$0, final List val$sources, final StorageObject val$target, final Map val$targetOptions) {
        this.this$0 = this$0;
        this.val$sources = val$sources;
        this.val$target = val$target;
        this.val$targetOptions = val$targetOptions;
        super();
    }
    
    @Override
    public StorageObject call() {
        return StorageImpl.access$000(this.this$0).compose(this.val$sources, this.val$target, this.val$targetOptions);
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}