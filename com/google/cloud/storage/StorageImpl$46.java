package com.google.cloud.storage;

import com.google.api.services.storage.model.ServiceAccount;
import java.util.concurrent.Callable;

class StorageImpl$46 implements Callable<ServiceAccount> {
    final /* synthetic */ String val$projectId;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$46(final StorageImpl this$0, final String val$projectId) {
        this.this$0 = this$0;
        this.val$projectId = val$projectId;
        super();
    }
    
    @Override
    public ServiceAccount call() {
        return StorageImpl.access$000(this.this$0).getServiceAccount(this.val$projectId);
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}