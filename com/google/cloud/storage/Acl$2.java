package com.google.cloud.storage;

import com.google.api.services.storage.model.BucketAccessControl;
import com.google.common.base.Function;

static final class Acl$2 implements Function<BucketAccessControl, Acl> {
    Acl$2() {
        super();
    }
    
    @Override
    public Acl apply(final BucketAccessControl bucketAccessControl) {
        return Acl.fromPb(bucketAccessControl);
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((BucketAccessControl)o);
    }
}