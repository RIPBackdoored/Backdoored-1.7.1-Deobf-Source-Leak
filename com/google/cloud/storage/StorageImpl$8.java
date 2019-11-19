package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.api.services.storage.model.StorageObject;
import com.google.cloud.Tuple;
import java.util.concurrent.Callable;

static final class StorageImpl$8 implements Callable<Tuple<String, Iterable<StorageObject>>> {
    final /* synthetic */ StorageOptions val$serviceOptions;
    final /* synthetic */ String val$bucket;
    final /* synthetic */ Map val$optionsMap;
    
    StorageImpl$8(final StorageOptions val$serviceOptions, final String val$bucket, final Map val$optionsMap) {
        this.val$serviceOptions = val$serviceOptions;
        this.val$bucket = val$bucket;
        this.val$optionsMap = val$optionsMap;
        super();
    }
    
    @Override
    public Tuple<String, Iterable<StorageObject>> call() {
        return this.val$serviceOptions.getStorageRpcV1().list(this.val$bucket, this.val$optionsMap);
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}