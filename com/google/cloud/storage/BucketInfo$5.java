package com.google.cloud.storage;

import com.google.api.services.storage.model.Bucket;
import com.google.common.base.Function;

class BucketInfo$5 implements Function<DeleteRule, Bucket.Lifecycle.Rule> {
    final /* synthetic */ BucketInfo this$0;
    
    BucketInfo$5(final BucketInfo this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public Bucket.Lifecycle.Rule apply(final DeleteRule deleteRule) {
        return deleteRule.toPb();
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((DeleteRule)o);
    }
}