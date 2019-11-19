package com.google.cloud.storage;

import com.google.api.services.storage.model.Bucket;
import com.google.common.base.Function;

static final class BucketInfo$9 implements Function<Bucket.Lifecycle.Rule, LifecycleRule> {
    BucketInfo$9() {
        super();
    }
    
    @Override
    public LifecycleRule apply(final Bucket.Lifecycle.Rule rule) {
        return LifecycleRule.fromPb(rule);
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((Bucket.Lifecycle.Rule)o);
    }
}