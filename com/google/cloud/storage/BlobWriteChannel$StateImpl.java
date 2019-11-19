package com.google.cloud.storage;

import com.google.cloud.RestorableState;
import java.io.Serializable;
import com.google.cloud.ServiceOptions;
import com.google.cloud.Restorable;
import com.google.cloud.WriteChannel;
import com.google.cloud.BaseWriteChannel.BaseState;
import com.google.cloud.BaseWriteChannel;

static class StateImpl extends BaseState<StorageOptions, BlobInfo>
{
    private static final long serialVersionUID = -9028324143780151286L;
    
    StateImpl(final Builder builder) {
        super((BaseState.Builder)builder);
    }
    
    static Builder builder(final StorageOptions storageOptions, final BlobInfo blobInfo, final String s) {
        return new Builder(storageOptions, blobInfo, s);
    }
    
    public WriteChannel restore() {
        final BlobWriteChannel blobWriteChannel = new BlobWriteChannel((StorageOptions)this.serviceOptions, (BlobInfo)this.entity, this.uploadId);
        BlobWriteChannel.access$600(blobWriteChannel, this);
        return (WriteChannel)blobWriteChannel;
    }
    
    public /* bridge */ Restorable restore() {
        return (Restorable)this.restore();
    }
}
