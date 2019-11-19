package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.api.services.storage.model.BucketAccessControl;
import java.util.concurrent.Callable;

class StorageImpl$20 implements Callable<BucketAccessControl> {
    final /* synthetic */ String val$bucket;
    final /* synthetic */ Acl.Entity val$entity;
    final /* synthetic */ Map val$optionsMap;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$20(final StorageImpl this$0, final String val$bucket, final Acl.Entity val$entity, final Map val$optionsMap) {
        this.this$0 = this$0;
        this.val$bucket = val$bucket;
        this.val$entity = val$entity;
        this.val$optionsMap = val$optionsMap;
        super();
    }
    
    @Override
    public BucketAccessControl call() {
        return StorageImpl.access$000(this.this$0).getAcl(this.val$bucket, this.val$entity.toPb(), this.val$optionsMap);
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}