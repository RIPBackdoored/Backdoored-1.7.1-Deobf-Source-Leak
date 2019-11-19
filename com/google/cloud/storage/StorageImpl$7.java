package com.google.cloud.storage;

import com.google.api.services.storage.model.Bucket;
import com.google.common.base.Function;

static final class StorageImpl$7 implements Function<Bucket, com.google.cloud.storage.Bucket> {
    final /* synthetic */ StorageOptions val$serviceOptions;
    
    StorageImpl$7(final StorageOptions val$serviceOptions) {
        this.val$serviceOptions = val$serviceOptions;
        super();
    }
    
    @Override
    public com.google.cloud.storage.Bucket apply(final Bucket bucket) {
        return com.google.cloud.storage.Bucket.fromPb((Storage)this.val$serviceOptions.getService(), bucket);
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((Bucket)o);
    }
}