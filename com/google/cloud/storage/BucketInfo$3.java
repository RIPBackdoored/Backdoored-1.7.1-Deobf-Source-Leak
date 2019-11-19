package com.google.cloud.storage;

import com.google.api.services.storage.model.BucketAccessControl;
import com.google.common.base.Function;

class BucketInfo$3 implements Function<Acl, BucketAccessControl> {
    final /* synthetic */ BucketInfo this$0;
    
    BucketInfo$3(final BucketInfo this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public BucketAccessControl apply(final Acl acl) {
        return acl.toBucketPb();
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((Acl)o);
    }
}