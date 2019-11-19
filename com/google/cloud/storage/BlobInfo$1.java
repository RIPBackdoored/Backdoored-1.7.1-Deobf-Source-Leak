package com.google.cloud.storage;

import com.google.api.services.storage.model.StorageObject;
import com.google.common.base.Function;

static final class BlobInfo$1 implements Function<BlobInfo, StorageObject> {
    BlobInfo$1() {
        super();
    }
    
    @Override
    public StorageObject apply(final BlobInfo blobInfo) {
        return blobInfo.toPb();
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((BlobInfo)o);
    }
}