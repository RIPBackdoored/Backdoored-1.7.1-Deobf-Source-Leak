package com.google.cloud.storage;

import com.google.cloud.Restorable;
import com.google.common.base.MoreObjects;
import java.util.Objects;
import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import java.io.Serializable;
import com.google.cloud.RestorableState;

static class StateImpl implements RestorableState<CopyWriter>, Serializable
{
    private static final long serialVersionUID = 1693964441435822700L;
    private final StorageOptions serviceOptions;
    private final BlobId source;
    private final Map<StorageRpc.Option, ?> sourceOptions;
    private final boolean overrideInfo;
    private final BlobInfo target;
    private final Map<StorageRpc.Option, ?> targetOptions;
    private final BlobInfo result;
    private final long blobSize;
    private final boolean isDone;
    private final String rewriteToken;
    private final long totalBytesCopied;
    private final Long megabytesCopiedPerChunk;
    
    StateImpl(final Builder builder) {
        super();
        this.serviceOptions = builder.serviceOptions;
        this.source = builder.source;
        this.sourceOptions = builder.sourceOptions;
        this.overrideInfo = builder.overrideInfo;
        this.target = builder.target;
        this.targetOptions = builder.targetOptions;
        this.result = builder.result;
        this.blobSize = builder.blobSize;
        this.isDone = builder.isDone;
        this.rewriteToken = builder.rewriteToken;
        this.totalBytesCopied = builder.totalBytesCopied;
        this.megabytesCopiedPerChunk = builder.megabytesCopiedPerChunk;
    }
    
    static Builder newBuilder(final StorageOptions storageOptions, final BlobId blobId, final Map<StorageRpc.Option, ?> map, final boolean b, final BlobInfo blobInfo, final Map<StorageRpc.Option, ?> map2) {
        return new Builder(storageOptions, blobId, (Map)map, b, blobInfo, (Map)map2);
    }
    
    public CopyWriter restore() {
        return new CopyWriter(this.serviceOptions, new StorageRpc.RewriteResponse(new StorageRpc.RewriteRequest(this.source.toPb(), this.sourceOptions, this.overrideInfo, this.target.toPb(), this.targetOptions, this.megabytesCopiedPerChunk), (this.result != null) ? this.result.toPb() : null, this.blobSize, this.isDone, this.rewriteToken, this.totalBytesCopied));
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.serviceOptions, this.source, this.sourceOptions, this.overrideInfo, this.target, this.targetOptions, this.result, this.blobSize, this.isDone, this.megabytesCopiedPerChunk, this.rewriteToken, this.totalBytesCopied);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof StateImpl)) {
            return false;
        }
        final StateImpl stateImpl = (StateImpl)o;
        return Objects.equals(this.serviceOptions, stateImpl.serviceOptions) && Objects.equals(this.source, stateImpl.source) && Objects.equals(this.sourceOptions, stateImpl.sourceOptions) && Objects.equals(this.overrideInfo, stateImpl.overrideInfo) && Objects.equals(this.target, stateImpl.target) && Objects.equals(this.targetOptions, stateImpl.targetOptions) && Objects.equals(this.result, stateImpl.result) && Objects.equals(this.rewriteToken, stateImpl.rewriteToken) && Objects.equals(this.megabytesCopiedPerChunk, stateImpl.megabytesCopiedPerChunk) && this.blobSize == stateImpl.blobSize && this.isDone == stateImpl.isDone && this.totalBytesCopied == stateImpl.totalBytesCopied;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("source", this.source).add("overrideInfo", this.overrideInfo).add("target", this.target).add("result", this.result).add("blobSize", this.blobSize).add("isDone", this.isDone).add("rewriteToken", this.rewriteToken).add("totalBytesCopied", this.totalBytesCopied).add("megabytesCopiedPerChunk", this.megabytesCopiedPerChunk).toString();
    }
    
    public /* bridge */ Restorable restore() {
        return (Restorable)this.restore();
    }
}
