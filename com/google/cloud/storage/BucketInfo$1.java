package com.google.cloud.storage;

import com.google.api.services.storage.model.Bucket;
import com.google.common.base.Function;

static final class BucketInfo$1 implements Function<Bucket, BucketInfo> {
    BucketInfo$1() {
        super();
    }
    
    @Override
    public BucketInfo apply(final Bucket bucket) {
        return BucketInfo.fromPb(bucket);
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((Bucket)o);
    }
}