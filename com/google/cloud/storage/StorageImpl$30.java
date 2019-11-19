package com.google.cloud.storage;

import com.google.api.services.storage.model.ObjectAccessControl;
import java.util.concurrent.Callable;

class StorageImpl$30 implements Callable<ObjectAccessControl> {
    final /* synthetic */ BlobId val$blob;
    final /* synthetic */ Acl.Entity val$entity;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$30(final StorageImpl this$0, final BlobId val$blob, final Acl.Entity val$entity) {
        this.this$0 = this$0;
        this.val$blob = val$blob;
        this.val$entity = val$entity;
        super();
    }
    
    @Override
    public ObjectAccessControl call() {
        return StorageImpl.access$000(this.this$0).getAcl(this.val$blob.getBucket(), this.val$blob.getName(), this.val$blob.getGeneration(), this.val$entity.toPb());
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}