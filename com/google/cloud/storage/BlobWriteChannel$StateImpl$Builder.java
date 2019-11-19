package com.google.cloud.storage;

import com.google.cloud.WriteChannel;
import com.google.cloud.RestorableState;
import java.io.Serializable;
import com.google.cloud.ServiceOptions;
import com.google.cloud.BaseWriteChannel.BaseState;

static class Builder extends BaseState.Builder<StorageOptions, BlobInfo>
{
    private Builder(final StorageOptions storageOptions, final BlobInfo blobInfo, final String s) {
        super((ServiceOptions)storageOptions, (Serializable)blobInfo, s);
    }
    
    public RestorableState<WriteChannel> build() {
        return (RestorableState<WriteChannel>)new StateImpl(this);
    }
    
    Builder(final StorageOptions storageOptions, final BlobInfo blobInfo, final String s, final BlobWriteChannel$1 runnable) {
        this(storageOptions, blobInfo, s);
    }
}
