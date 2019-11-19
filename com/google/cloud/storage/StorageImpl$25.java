package com.google.cloud.storage;

import com.google.api.services.storage.model.ObjectAccessControl;
import java.util.concurrent.Callable;

class StorageImpl$25 implements Callable<ObjectAccessControl> {
    final /* synthetic */ String val$bucket;
    final /* synthetic */ Acl.Entity val$entity;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$25(final StorageImpl this$0, final String val$bucket, final Acl.Entity val$entity) {
        this.this$0 = this$0;
        this.val$bucket = val$bucket;
        this.val$entity = val$entity;
        super();
    }
    
    @Override
    public ObjectAccessControl call() {
        return StorageImpl.access$000(this.this$0).getDefaultAcl(this.val$bucket, this.val$entity.toPb());
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}