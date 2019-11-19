package com.google.cloud.storage;

import com.google.cloud.Restorable;
import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.util.Objects;
import com.google.api.gax.retrying.ResultRetryAlgorithm;
import com.google.cloud.RetryHelper;
import java.util.concurrent.Callable;
import com.google.cloud.Tuple;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import com.google.cloud.RestorableState;
import com.google.api.services.storage.model.StorageObject;
import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.cloud.ReadChannel;

class BlobReadChannel implements ReadChannel
{
    private static final int DEFAULT_CHUNK_SIZE = 2097152;
    private final StorageOptions serviceOptions;
    private final BlobId blob;
    private final Map<StorageRpc.Option, ?> requestOptions;
    private String lastEtag;
    private long position;
    private boolean isOpen;
    private boolean endOfStream;
    private int chunkSize;
    private final StorageRpc storageRpc;
    private final StorageObject storageObject;
    private int bufferPos;
    private byte[] buffer;
    
    BlobReadChannel(final StorageOptions serviceOptions, final BlobId blob, final Map<StorageRpc.Option, ?> requestOptions) {
        super();
        this.chunkSize = 2097152;
        this.serviceOptions = serviceOptions;
        this.blob = blob;
        this.requestOptions = requestOptions;
        this.isOpen = true;
        this.storageRpc = serviceOptions.getStorageRpcV1();
        this.storageObject = blob.toPb();
    }
    
    public RestorableState<ReadChannel> capture() {
        final StateImpl.Builder setChunkSize = StateImpl.builder(this.serviceOptions, this.blob, this.requestOptions).setPosition(this.position).setIsOpen(this.isOpen).setEndOfStream(this.endOfStream).setChunkSize(this.chunkSize);
        if (this.buffer != null) {
            setChunkSize.setPosition(this.position + this.bufferPos);
            setChunkSize.setEndOfStream(false);
        }
        return setChunkSize.build();
    }
    
    public boolean isOpen() {
        return this.isOpen;
    }
    
    public void close() {
        if (this.isOpen) {
            this.buffer = null;
            this.isOpen = false;
        }
    }
    
    private void validateOpen() throws ClosedChannelException {
        if (!this.isOpen) {
            throw new ClosedChannelException();
        }
    }
    
    public void seek(final long position) throws IOException {
        this.validateOpen();
        this.position = position;
        this.buffer = null;
        this.bufferPos = 0;
        this.endOfStream = false;
    }
    
    public void setChunkSize(final int n) {
        this.chunkSize = ((n <= 0) ? 2097152 : n);
    }
    
    public int read(final ByteBuffer byteBuffer) throws IOException {
        this.validateOpen();
        if (this.buffer == null) {
            if (this.endOfStream) {
                return -1;
            }
            final int max = Math.max(byteBuffer.remaining(), this.chunkSize);
            try {
                final Tuple tuple = (Tuple)RetryHelper.runWithRetries((Callable)new Callable<Tuple<String, byte[]>>() {
                    final /* synthetic */ int val$toRead;
                    final /* synthetic */ BlobReadChannel this$0;
                    
                    BlobReadChannel$1() {
                        this.this$0 = this$0;
                        super();
                    }
                    
                    @Override
                    public Tuple<String, byte[]> call() {
                        return this.this$0.storageRpc.read(this.this$0.storageObject, this.this$0.requestOptions, this.this$0.position, max);
                    }
                    
                    @Override
                    public /* bridge */ Object call() throws Exception {
                        return this.call();
                    }
                }, this.serviceOptions.getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, this.serviceOptions.getClock());
                if (((byte[])tuple.y()).length > 0 && this.lastEtag != null && !Objects.equals(tuple.x(), this.lastEtag)) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Blob ").append(this.blob).append(" was updated while reading");
                    throw new StorageException(0, sb.toString());
                }
                this.lastEtag = (String)tuple.x();
                this.buffer = (byte[])tuple.y();
            }
            catch (RetryHelper.RetryHelperException ex) {
                throw StorageException.translateAndThrow(ex);
            }
            if (max > this.buffer.length) {
                this.endOfStream = true;
                if (this.buffer.length == 0) {
                    this.buffer = null;
                    return -1;
                }
            }
        }
        final int min = Math.min(this.buffer.length - this.bufferPos, byteBuffer.remaining());
        byteBuffer.put(this.buffer, this.bufferPos, min);
        this.bufferPos += min;
        if (this.bufferPos >= this.buffer.length) {
            this.position += this.buffer.length;
            this.buffer = null;
            this.bufferPos = 0;
        }
        return min;
    }
    
    static /* synthetic */ StorageObject access$000(final BlobReadChannel blobReadChannel) {
        return blobReadChannel.storageObject;
    }
    
    static /* synthetic */ Map access$100(final BlobReadChannel blobReadChannel) {
        return blobReadChannel.requestOptions;
    }
    
    static /* synthetic */ long access$200(final BlobReadChannel blobReadChannel) {
        return blobReadChannel.position;
    }
    
    static /* synthetic */ StorageRpc access$300(final BlobReadChannel blobReadChannel) {
        return blobReadChannel.storageRpc;
    }
    
    static /* synthetic */ String access$1302(final BlobReadChannel blobReadChannel, final String lastEtag) {
        return blobReadChannel.lastEtag = lastEtag;
    }
    
    static /* synthetic */ long access$202(final BlobReadChannel blobReadChannel, final long position) {
        return blobReadChannel.position = position;
    }
    
    static /* synthetic */ boolean access$1402(final BlobReadChannel blobReadChannel, final boolean isOpen) {
        return blobReadChannel.isOpen = isOpen;
    }
    
    static /* synthetic */ boolean access$1502(final BlobReadChannel blobReadChannel, final boolean endOfStream) {
        return blobReadChannel.endOfStream = endOfStream;
    }
    
    static /* synthetic */ int access$1602(final BlobReadChannel blobReadChannel, final int chunkSize) {
        return blobReadChannel.chunkSize = chunkSize;
    }
}
