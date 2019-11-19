package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import com.google.common.base.Function;

static final class Bucket$BlobTargetOption$1 implements Function<BlobTargetOption, StorageRpc.Option> {
    Bucket$BlobTargetOption$1() {
        super();
    }
    
    @Override
    public StorageRpc.Option apply(final BlobTargetOption blobTargetOption) {
        return blobTargetOption.getRpcOption();
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((BlobTargetOption)o);
    }
}