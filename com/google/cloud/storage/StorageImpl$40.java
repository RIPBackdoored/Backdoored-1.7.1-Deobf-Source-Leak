package com.google.cloud.storage;

import com.google.api.services.storage.model.HmacKeyMetadata;
import com.google.common.base.Function;

static final class StorageImpl$40 implements Function<HmacKeyMetadata, HmacKey.HmacKeyMetadata> {
    StorageImpl$40() {
        super();
    }
    
    @Override
    public HmacKey.HmacKeyMetadata apply(final HmacKeyMetadata hmacKeyMetadata) {
        return HmacKey.HmacKeyMetadata.fromPb(hmacKeyMetadata);
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((HmacKeyMetadata)o);
    }
}