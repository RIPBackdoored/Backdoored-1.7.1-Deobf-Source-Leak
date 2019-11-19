package com.google.cloud.storage;

import com.google.cloud.RestorableState;
import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;

static class Builder
{
    private final StorageOptions serviceOptions;
    private final BlobId source;
    private final Map<StorageRpc.Option, ?> sourceOptions;
    private final boolean overrideInfo;
    private final BlobInfo target;
    private final Map<StorageRpc.Option, ?> targetOptions;
    private BlobInfo result;
    private long blobSize;
    private boolean isDone;
    private String rewriteToken;
    private long totalBytesCopied;
    private Long megabytesCopiedPerChunk;
    
    private Builder(final StorageOptions serviceOptions, final BlobId source, final Map<StorageRpc.Option, ?> sourceOptions, final boolean overrideInfo, final BlobInfo target, final Map<StorageRpc.Option, ?> targetOptions) {
        super();
        this.serviceOptions = serviceOptions;
        this.source = source;
        this.sourceOptions = sourceOptions;
        this.overrideInfo = overrideInfo;
        this.target = target;
        this.targetOptions = targetOptions;
    }
    
    Builder setResult(final BlobInfo result) {
        this.result = result;
        return this;
    }
    
    Builder setBlobSize(final long blobSize) {
        this.blobSize = blobSize;
        return this;
    }
    
    Builder setIsDone(final boolean isDone) {
        this.isDone = isDone;
        return this;
    }
    
    Builder setRewriteToken(final String rewriteToken) {
        this.rewriteToken = rewriteToken;
        return this;
    }
    
    Builder setTotalBytesRewritten(final long totalBytesCopied) {
        this.totalBytesCopied = totalBytesCopied;
        return this;
    }
    
    Builder setMegabytesCopiedPerChunk(final Long megabytesCopiedPerChunk) {
        this.megabytesCopiedPerChunk = megabytesCopiedPerChunk;
        return this;
    }
    
    RestorableState<CopyWriter> build() {
        return (RestorableState<CopyWriter>)new StateImpl(this);
    }
    
    static /* synthetic */ StorageOptions access$200(final Builder builder) {
        return builder.serviceOptions;
    }
    
    static /* synthetic */ BlobId access$300(final Builder builder) {
        return builder.source;
    }
    
    static /* synthetic */ Map access$400(final Builder builder) {
        return builder.sourceOptions;
    }
    
    static /* synthetic */ boolean access$500(final Builder builder) {
        return builder.overrideInfo;
    }
    
    static /* synthetic */ BlobInfo access$600(final Builder builder) {
        return builder.target;
    }
    
    static /* synthetic */ Map access$700(final Builder builder) {
        return builder.targetOptions;
    }
    
    static /* synthetic */ BlobInfo access$800(final Builder builder) {
        return builder.result;
    }
    
    static /* synthetic */ long access$900(final Builder builder) {
        return builder.blobSize;
    }
    
    static /* synthetic */ boolean access$1000(final Builder builder) {
        return builder.isDone;
    }
    
    static /* synthetic */ String access$1100(final Builder builder) {
        return builder.rewriteToken;
    }
    
    static /* synthetic */ long access$1200(final Builder builder) {
        return builder.totalBytesCopied;
    }
    
    static /* synthetic */ Long access$1300(final Builder builder) {
        return builder.megabytesCopiedPerChunk;
    }
    
    Builder(final StorageOptions storageOptions, final BlobId blobId, final Map map, final boolean b, final BlobInfo blobInfo, final Map map2, final CopyWriter$1 callable) {
        this(storageOptions, blobId, map, b, blobInfo, map2);
    }
}
