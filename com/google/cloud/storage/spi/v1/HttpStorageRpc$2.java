package com.google.cloud.storage.spi.v1;

import java.math.BigInteger;
import com.google.api.services.storage.model.StorageObject;
import com.google.common.base.Function;

static final class HttpStorageRpc$2 implements Function<String, StorageObject> {
    final /* synthetic */ String val$bucket;
    
    HttpStorageRpc$2(final String val$bucket) {
        this.val$bucket = val$bucket;
        super();
    }
    
    @Override
    public StorageObject apply(final String name) {
        return new StorageObject().set("isDirectory", (Object)true).setBucket(this.val$bucket).setName(name).setSize(BigInteger.ZERO);
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((String)o);
    }
}