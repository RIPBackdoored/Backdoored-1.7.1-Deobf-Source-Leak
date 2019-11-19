package com.google.cloud.storage;

import com.google.api.services.storage.model.ObjectAccessControl;
import java.util.concurrent.Callable;

class StorageImpl$28 implements Callable<ObjectAccessControl> {
    final /* synthetic */ ObjectAccessControl val$aclPb;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$28(final StorageImpl this$0, final ObjectAccessControl val$aclPb) {
        this.this$0 = this$0;
        this.val$aclPb = val$aclPb;
        super();
    }
    
    @Override
    public ObjectAccessControl call() {
        return StorageImpl.access$000(this.this$0).patchDefaultAcl(this.val$aclPb);
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}