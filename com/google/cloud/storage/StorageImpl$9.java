package com.google.cloud.storage;

import com.google.api.services.storage.model.StorageObject;
import com.google.common.base.Function;

static final class StorageImpl$9 implements Function<StorageObject, Blob> {
    final /* synthetic */ StorageOptions val$serviceOptions;
    
    StorageImpl$9(final StorageOptions val$serviceOptions) {
        this.val$serviceOptions = val$serviceOptions;
        super();
    }
    
    @Override
    public Blob apply(final StorageObject storageObject) {
        return Blob.fromPb((Storage)this.val$serviceOptions.getService(), storageObject);
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((StorageObject)o);
    }
}