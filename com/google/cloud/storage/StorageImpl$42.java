package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.api.services.storage.model.Policy;
import java.util.concurrent.Callable;

class StorageImpl$42 implements Callable<Policy> {
    final /* synthetic */ String val$bucket;
    final /* synthetic */ com.google.cloud.Policy val$policy;
    final /* synthetic */ Map val$optionsMap;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$42(final StorageImpl this$0, final String val$bucket, final com.google.cloud.Policy val$policy, final Map val$optionsMap) {
        this.this$0 = this$0;
        this.val$bucket = val$bucket;
        this.val$policy = val$policy;
        this.val$optionsMap = val$optionsMap;
        super();
    }
    
    @Override
    public Policy call() {
        return StorageImpl.access$000(this.this$0).setIamPolicy(this.val$bucket, PolicyHelper.convertToApiPolicy(this.val$policy), this.val$optionsMap);
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}