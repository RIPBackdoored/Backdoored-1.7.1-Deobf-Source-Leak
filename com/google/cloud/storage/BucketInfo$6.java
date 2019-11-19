package com.google.cloud.storage;

import com.google.api.services.storage.model.Bucket;
import com.google.common.base.Function;

class BucketInfo$6 implements Function<LifecycleRule, Bucket.Lifecycle.Rule> {
    final /* synthetic */ BucketInfo this$0;
    
    BucketInfo$6(final BucketInfo this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public Bucket.Lifecycle.Rule apply(final LifecycleRule lifecycleRule) {
        return lifecycleRule.toPb();
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((LifecycleRule)o);
    }
}