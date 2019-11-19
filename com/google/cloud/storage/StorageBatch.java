package com.google.cloud.storage;

import com.google.api.services.storage.model.StorageObject;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.common.annotations.VisibleForTesting;
import com.google.cloud.storage.spi.v1.StorageRpc;
import com.google.cloud.storage.spi.v1.RpcBatch;

public class StorageBatch
{
    private final RpcBatch batch;
    private final StorageRpc storageRpc;
    private final StorageOptions options;
    
    StorageBatch(final StorageOptions options) {
        super();
        this.options = options;
        this.storageRpc = options.getStorageRpcV1();
        this.batch = this.storageRpc.createBatch();
    }
    
    @VisibleForTesting
    Object getBatch() {
        return this.batch;
    }
    
    @VisibleForTesting
    StorageRpc getStorageRpc() {
        return this.storageRpc;
    }
    
    @VisibleForTesting
    StorageOptions getOptions() {
        return this.options;
    }
    
    public StorageBatchResult<Boolean> delete(final String s, final String s2, final Storage.BlobSourceOption... array) {
        return this.delete(BlobId.of(s, s2), array);
    }
    
    public StorageBatchResult<Boolean> delete(final BlobId blobId, final Storage.BlobSourceOption... array) {
        final StorageBatchResult<Boolean> storageBatchResult = new StorageBatchResult<Boolean>();
        this.batch.addDelete(blobId.toPb(), this.createDeleteCallback(storageBatchResult), StorageImpl.optionMap(blobId, (Option[])array));
        return storageBatchResult;
    }
    
    public StorageBatchResult<Blob> update(final BlobInfo blobInfo, final Storage.BlobTargetOption... array) {
        final StorageBatchResult<Blob> storageBatchResult = new StorageBatchResult<Blob>();
        this.batch.addPatch(blobInfo.toPb(), this.createUpdateCallback(this.options, storageBatchResult), StorageImpl.optionMap(blobInfo, (Option[])array));
        return storageBatchResult;
    }
    
    public StorageBatchResult<Blob> get(final String s, final String s2, final Storage.BlobGetOption... array) {
        return this.get(BlobId.of(s, s2), array);
    }
    
    public StorageBatchResult<Blob> get(final BlobId blobId, final Storage.BlobGetOption... array) {
        final StorageBatchResult<Blob> storageBatchResult = new StorageBatchResult<Blob>();
        this.batch.addGet(blobId.toPb(), this.createGetCallback(this.options, storageBatchResult), StorageImpl.optionMap(blobId, (Option[])array));
        return storageBatchResult;
    }
    
    public void submit() {
        this.batch.submit();
    }
    
    private RpcBatch.Callback<Void> createDeleteCallback(final StorageBatchResult<Boolean> storageBatchResult) {
        return new RpcBatch.Callback<Void>() {
            final /* synthetic */ StorageBatchResult val$result;
            final /* synthetic */ StorageBatch this$0;
            
            StorageBatch$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public void onSuccess(final Void void1) {
                storageBatchResult.success(true);
            }
            
            @Override
            public void onFailure(final GoogleJsonError googleJsonError) {
                final StorageException ex = new StorageException(googleJsonError);
                if (ex.getCode() == 404) {
                    storageBatchResult.success(false);
                }
                else {
                    storageBatchResult.error(ex);
                }
            }
            
            @Override
            public /* bridge */ void onSuccess(final Object o) {
                this.onSuccess((Void)o);
            }
        };
    }
    
    private RpcBatch.Callback<StorageObject> createGetCallback(final StorageOptions storageOptions, final StorageBatchResult<Blob> storageBatchResult) {
        return new RpcBatch.Callback<StorageObject>() {
            final /* synthetic */ StorageBatchResult val$result;
            final /* synthetic */ StorageOptions val$serviceOptions;
            final /* synthetic */ StorageBatch this$0;
            
            StorageBatch$2() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public void onSuccess(final StorageObject storageObject) {
                storageBatchResult.success((storageObject == null) ? null : Blob.fromPb((Storage)storageOptions.getService(), storageObject));
            }
            
            @Override
            public void onFailure(final GoogleJsonError googleJsonError) {
                final StorageException ex = new StorageException(googleJsonError);
                if (ex.getCode() == 404) {
                    storageBatchResult.success(null);
                }
                else {
                    storageBatchResult.error(ex);
                }
            }
            
            @Override
            public /* bridge */ void onSuccess(final Object o) {
                this.onSuccess((StorageObject)o);
            }
        };
    }
    
    private RpcBatch.Callback<StorageObject> createUpdateCallback(final StorageOptions storageOptions, final StorageBatchResult<Blob> storageBatchResult) {
        return new RpcBatch.Callback<StorageObject>() {
            final /* synthetic */ StorageBatchResult val$result;
            final /* synthetic */ StorageOptions val$serviceOptions;
            final /* synthetic */ StorageBatch this$0;
            
            StorageBatch$3() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public void onSuccess(final StorageObject storageObject) {
                storageBatchResult.success((storageObject == null) ? null : Blob.fromPb((Storage)storageOptions.getService(), storageObject));
            }
            
            @Override
            public void onFailure(final GoogleJsonError googleJsonError) {
                storageBatchResult.error(new StorageException(googleJsonError));
            }
            
            @Override
            public /* bridge */ void onSuccess(final Object o) {
                this.onSuccess((StorageObject)o);
            }
        };
    }
}
