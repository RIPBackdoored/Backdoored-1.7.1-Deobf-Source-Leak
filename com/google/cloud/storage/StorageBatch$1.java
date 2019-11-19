package com.google.cloud.storage;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.cloud.storage.spi.v1.RpcBatch;

class StorageBatch$1 implements RpcBatch.Callback<Void> {
    final /* synthetic */ StorageBatchResult val$result;
    final /* synthetic */ StorageBatch this$0;
    
    StorageBatch$1(final StorageBatch this$0, final StorageBatchResult val$result) {
        this.this$0 = this$0;
        this.val$result = val$result;
        super();
    }
    
    @Override
    public void onSuccess(final Void void1) {
        this.val$result.success(true);
    }
    
    @Override
    public void onFailure(final GoogleJsonError googleJsonError) {
        final StorageException ex = new StorageException(googleJsonError);
        if (ex.getCode() == 404) {
            this.val$result.success(false);
        }
        else {
            this.val$result.error(ex);
        }
    }
    
    @Override
    public /* bridge */ void onSuccess(final Object o) {
        this.onSuccess((Void)o);
    }
}