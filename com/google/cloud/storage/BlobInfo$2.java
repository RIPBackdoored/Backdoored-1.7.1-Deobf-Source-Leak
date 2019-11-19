package com.google.cloud.storage;

import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.common.base.Function;

class BlobInfo$2 implements Function<Acl, ObjectAccessControl> {
    final /* synthetic */ BlobInfo this$0;
    
    BlobInfo$2(final BlobInfo this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public ObjectAccessControl apply(final Acl acl) {
        return acl.toObjectPb();
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((Acl)o);
    }
}