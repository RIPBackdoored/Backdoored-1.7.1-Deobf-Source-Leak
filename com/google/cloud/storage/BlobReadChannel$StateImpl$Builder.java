package com.google.cloud.storage;

import com.google.cloud.ReadChannel;
import com.google.cloud.RestorableState;
import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;

static class Builder
{
    private final StorageOptions serviceOptions;
    private final BlobId blob;
    private final Map<StorageRpc.Option, ?> requestOptions;
    private String lastEtag;
    private long position;
    private boolean isOpen;
    private boolean endOfStream;
    private int chunkSize;
    
    private Builder(final StorageOptions serviceOptions, final BlobId blob, final Map<StorageRpc.Option, ?> requestOptions) {
        super();
        this.serviceOptions = serviceOptions;
        this.blob = blob;
        this.requestOptions = requestOptions;
    }
    
    Builder setLastEtag(final String lastEtag) {
        this.lastEtag = lastEtag;
        return this;
    }
    
    Builder setPosition(final long position) {
        this.position = position;
        return this;
    }
    
    Builder setIsOpen(final boolean isOpen) {
        this.isOpen = isOpen;
        return this;
    }
    
    Builder setEndOfStream(final boolean endOfStream) {
        this.endOfStream = endOfStream;
        return this;
    }
    
    Builder setChunkSize(final int chunkSize) {
        this.chunkSize = chunkSize;
        return this;
    }
    
    RestorableState<ReadChannel> build() {
        return (RestorableState<ReadChannel>)new StateImpl(this);
    }
    
    static /* synthetic */ StorageOptions access$400(final Builder builder) {
        return builder.serviceOptions;
    }
    
    static /* synthetic */ BlobId access$500(final Builder builder) {
        return builder.blob;
    }
    
    static /* synthetic */ Map access$600(final Builder builder) {
        return builder.requestOptions;
    }
    
    static /* synthetic */ String access$700(final Builder builder) {
        return builder.lastEtag;
    }
    
    static /* synthetic */ long access$800(final Builder builder) {
        return builder.position;
    }
    
    static /* synthetic */ boolean access$900(final Builder builder) {
        return builder.isOpen;
    }
    
    static /* synthetic */ boolean access$1000(final Builder builder) {
        return builder.endOfStream;
    }
    
    static /* synthetic */ int access$1100(final Builder builder) {
        return builder.chunkSize;
    }
    
    Builder(final StorageOptions storageOptions, final BlobId blobId, final Map map, final BlobReadChannel$1 callable) {
        this(storageOptions, blobId, map);
    }
}
