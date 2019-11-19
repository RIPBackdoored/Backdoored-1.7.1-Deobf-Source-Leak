package com.google.cloud.storage;

import com.google.common.base.Function;

static final class BucketInfo$LifecycleRule$1 implements Function<String, StorageClass> {
    BucketInfo$LifecycleRule$1() {
        super();
    }
    
    @Override
    public StorageClass apply(final String s) {
        return StorageClass.valueOf(s);
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((String)o);
    }
}