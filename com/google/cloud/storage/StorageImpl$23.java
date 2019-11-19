package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.api.services.storage.model.BucketAccessControl;
import java.util.concurrent.Callable;

class StorageImpl$23 implements Callable<BucketAccessControl> {
    final /* synthetic */ BucketAccessControl val$aclPb;
    final /* synthetic */ Map val$optionsMap;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$23(final StorageImpl this$0, final BucketAccessControl val$aclPb, final Map val$optionsMap) {
        this.this$0 = this$0;
        this.val$aclPb = val$aclPb;
        this.val$optionsMap = val$optionsMap;
        super();
    }
    
    @Override
    public BucketAccessControl call() {
        return StorageImpl.access$000(this.this$0).patchAcl(this.val$aclPb, this.val$optionsMap);
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}