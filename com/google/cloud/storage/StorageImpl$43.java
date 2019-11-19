package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import java.util.List;
import com.google.api.services.storage.model.TestIamPermissionsResponse;
import java.util.concurrent.Callable;

class StorageImpl$43 implements Callable<TestIamPermissionsResponse> {
    final /* synthetic */ String val$bucket;
    final /* synthetic */ List val$permissions;
    final /* synthetic */ Map val$optionsMap;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$43(final StorageImpl this$0, final String val$bucket, final List val$permissions, final Map val$optionsMap) {
        this.this$0 = this$0;
        this.val$bucket = val$bucket;
        this.val$permissions = val$permissions;
        this.val$optionsMap = val$optionsMap;
        super();
    }
    
    @Override
    public TestIamPermissionsResponse call() {
        return StorageImpl.access$000(this.this$0).testIamPermissions(this.val$bucket, this.val$permissions, this.val$optionsMap);
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}