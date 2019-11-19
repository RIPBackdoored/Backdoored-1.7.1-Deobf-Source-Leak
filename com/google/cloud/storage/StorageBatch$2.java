package com.google.cloud.storage;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.services.storage.model.StorageObject;
import com.google.cloud.storage.spi.v1.RpcBatch;

class StorageBatch$2 implements RpcBatch.Callback<StorageObject> {
    final /* synthetic */ StorageBatchResult val$result;
    final /* synthetic */ StorageOptions val$serviceOptions;
    final /* synthetic */ StorageBatch this$0;
    
    StorageBatch$2(final StorageBatch this$0, final StorageBatchResult val$result, final StorageOptions val$serviceOptions) {
        this.this$0 = this$0;
        this.val$result = val$result;
        this.val$serviceOptions = val$serviceOptions;
        super();
    }
    
    @Override
    public void onSuccess(final StorageObject storageObject) {
        this.val$result.success((storageObject == null) ? null : Blob.fromPb((Storage)this.val$serviceOptions.getService(), storageObject));
    }
    
    @Override
    public void onFailure(final GoogleJsonError googleJsonError) {
        final StorageException ex = new StorageException(googleJsonError);
        if (ex.getCode() == 404) {
            this.val$result.success(null);
        }
        else {
            this.val$result.error(ex);
        }
    }
    
    @Override
    public /* bridge */ void onSuccess(final Object o) {
        this.onSuccess((StorageObject)o);
    }
}