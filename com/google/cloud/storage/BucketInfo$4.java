package com.google.cloud.storage;

import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.common.base.Function;

class BucketInfo$4 implements Function<Acl, ObjectAccessControl> {
    final /* synthetic */ BucketInfo this$0;
    
    BucketInfo$4(final BucketInfo this$0) {
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