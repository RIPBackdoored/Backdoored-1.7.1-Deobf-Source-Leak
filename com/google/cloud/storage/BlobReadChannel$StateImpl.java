package com.google.cloud.storage;

import com.google.cloud.Restorable;
import com.google.common.base.MoreObjects;
import java.util.Objects;
import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import java.io.Serializable;
import com.google.cloud.ReadChannel;
import com.google.cloud.RestorableState;

static class StateImpl implements RestorableState<ReadChannel>, Serializable
{
    private static final long serialVersionUID = 3889420316004453706L;
    private final StorageOptions serviceOptions;
    private final BlobId blob;
    private final Map<StorageRpc.Option, ?> requestOptions;
    private final String lastEtag;
    private final long position;
    private final boolean isOpen;
    private final boolean endOfStream;
    private final int chunkSize;
    
    StateImpl(final Builder builder) {
        super();
        this.serviceOptions = builder.serviceOptions;
        this.blob = builder.blob;
        this.requestOptions = builder.requestOptions;
        this.lastEtag = builder.lastEtag;
        this.position = builder.position;
        this.isOpen = builder.isOpen;
        this.endOfStream = builder.endOfStream;
        this.chunkSize = builder.chunkSize;
    }
    
    static Builder builder(final StorageOptions storageOptions, final BlobId blobId, final Map<StorageRpc.Option, ?> map) {
        return new Builder(storageOptions, blobId, (Map)map);
    }
    
    public ReadChannel restore() {
        final BlobReadChannel blobReadChannel = new BlobReadChannel(this.serviceOptions, this.blob, this.requestOptions);
        BlobReadChannel.access$1302(blobReadChannel, this.lastEtag);
        BlobReadChannel.access$202(blobReadChannel, this.position);
        BlobReadChannel.access$1402(blobReadChannel, this.isOpen);
        BlobReadChannel.access$1502(blobReadChannel, this.endOfStream);
        BlobReadChannel.access$1602(blobReadChannel, this.chunkSize);
        return (ReadChannel)blobReadChannel;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.serviceOptions, this.blob, this.requestOptions, this.lastEtag, this.position, this.isOpen, this.endOfStream, this.chunkSize);
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
        return Objects.equals(this.serviceOptions, stateImpl.serviceOptions) && Objects.equals(this.blob, stateImpl.blob) && Objects.equals(this.requestOptions, stateImpl.requestOptions) && Objects.equals(this.lastEtag, stateImpl.lastEtag) && this.position == stateImpl.position && this.isOpen == stateImpl.isOpen && this.endOfStream == stateImpl.endOfStream && this.chunkSize == stateImpl.chunkSize;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("blob", this.blob).add("position", this.position).add("isOpen", this.isOpen).add("endOfStream", this.endOfStream).toString();
    }
    
    public /* bridge */ Restorable restore() {
        return (Restorable)this.restore();
    }
}
