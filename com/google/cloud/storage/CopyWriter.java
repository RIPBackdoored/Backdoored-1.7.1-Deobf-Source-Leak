package com.google.cloud.storage;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import java.util.Map;
import java.io.Serializable;
import com.google.cloud.RestorableState;
import com.google.api.gax.retrying.ResultRetryAlgorithm;
import com.google.cloud.RetryHelper;
import java.util.concurrent.Callable;
import com.google.cloud.storage.spi.v1.StorageRpc;
import com.google.cloud.Restorable;

public class CopyWriter implements Restorable<CopyWriter>
{
    private final StorageOptions serviceOptions;
    private final StorageRpc storageRpc;
    private StorageRpc.RewriteResponse rewriteResponse;
    
    CopyWriter(final StorageOptions serviceOptions, final StorageRpc.RewriteResponse rewriteResponse) {
        super();
        this.serviceOptions = serviceOptions;
        this.rewriteResponse = rewriteResponse;
        this.storageRpc = serviceOptions.getStorageRpcV1();
    }
    
    public Blob getResult() {
        while (!this.isDone()) {
            this.copyChunk();
        }
        return Blob.fromPb((Storage)this.serviceOptions.getService(), this.rewriteResponse.result);
    }
    
    public long getBlobSize() {
        return this.rewriteResponse.blobSize;
    }
    
    public boolean isDone() {
        return this.rewriteResponse.isDone;
    }
    
    public long getTotalBytesCopied() {
        return this.rewriteResponse.totalBytesRewritten;
    }
    
    public void copyChunk() {
        if (!this.isDone()) {
            try {
                this.rewriteResponse = (StorageRpc.RewriteResponse)RetryHelper.runWithRetries((Callable)new Callable<StorageRpc.RewriteResponse>() {
                    final /* synthetic */ CopyWriter this$0;
                    
                    CopyWriter$1() {
                        this.this$0 = this$0;
                        super();
                    }
                    
                    @Override
                    public StorageRpc.RewriteResponse call() {
                        return this.this$0.storageRpc.continueRewrite(this.this$0.rewriteResponse);
                    }
                    
                    @Override
                    public /* bridge */ Object call() throws Exception {
                        return this.call();
                    }
                }, this.serviceOptions.getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, this.serviceOptions.getClock());
            }
            catch (RetryHelper.RetryHelperException ex) {
                throw StorageException.translateAndThrow(ex);
            }
        }
    }
    
    public RestorableState<CopyWriter> capture() {
        return StateImpl.newBuilder(this.serviceOptions, BlobId.fromPb(this.rewriteResponse.rewriteRequest.source), this.rewriteResponse.rewriteRequest.sourceOptions, this.rewriteResponse.rewriteRequest.overrideInfo, BlobInfo.fromPb(this.rewriteResponse.rewriteRequest.target), this.rewriteResponse.rewriteRequest.targetOptions).setResult((this.rewriteResponse.result != null) ? BlobInfo.fromPb(this.rewriteResponse.result) : null).setBlobSize(this.getBlobSize()).setIsDone(this.isDone()).setMegabytesCopiedPerChunk(this.rewriteResponse.rewriteRequest.megabytesRewrittenPerCall).setRewriteToken(this.rewriteResponse.rewriteToken).setTotalBytesRewritten(this.getTotalBytesCopied()).build();
    }
    
    static /* synthetic */ StorageRpc.RewriteResponse access$000(final CopyWriter copyWriter) {
        return copyWriter.rewriteResponse;
    }
    
    static /* synthetic */ StorageRpc access$100(final CopyWriter copyWriter) {
        return copyWriter.storageRpc;
    }
}
