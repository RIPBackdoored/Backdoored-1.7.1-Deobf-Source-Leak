package com.google.cloud.storage.spi.v1;

import java.util.Iterator;
import io.opencensus.common.Scope;
import io.opencensus.trace.Span;
import io.opencensus.trace.Status;
import com.google.api.client.http.GenericUrl;
import io.opencensus.trace.AttributeValue;
import java.io.IOException;
import java.util.Map;
import com.google.api.services.storage.model.StorageObject;
import com.google.api.client.googleapis.batch.BatchRequest;
import java.util.LinkedList;
import com.google.api.services.storage.Storage;

private class DefaultRpcBatch implements RpcBatch
{
    private static final int MAX_BATCH_SIZE = 100;
    private final Storage storage;
    private final LinkedList<BatchRequest> batches;
    private int currentBatchSize;
    final /* synthetic */ HttpStorageRpc this$0;
    
    private DefaultRpcBatch(final HttpStorageRpc this$0, final Storage storage) {
        this.this$0 = this$0;
        super();
        this.storage = storage;
        (this.batches = new LinkedList<BatchRequest>()).add(storage.batch(HttpStorageRpc.access$000(this$0)));
    }
    
    @Override
    public void addDelete(final StorageObject storageObject, final Callback<Void> callback, final Map<Option, ?> map) {
        try {
            if (this.currentBatchSize == 100) {
                this.batches.add(this.storage.batch());
                this.currentBatchSize = 0;
            }
            HttpStorageRpc.access$200(this.this$0, storageObject, map).queue((BatchRequest)this.batches.getLast(), HttpStorageRpc.access$100(callback));
            ++this.currentBatchSize;
        }
        catch (IOException ex) {
            throw HttpStorageRpc.access$300(ex);
        }
    }
    
    @Override
    public void addPatch(final StorageObject storageObject, final Callback<StorageObject> callback, final Map<Option, ?> map) {
        try {
            if (this.currentBatchSize == 100) {
                this.batches.add(this.storage.batch());
                this.currentBatchSize = 0;
            }
            HttpStorageRpc.access$400(this.this$0, storageObject, map).queue((BatchRequest)this.batches.getLast(), HttpStorageRpc.access$100(callback));
            ++this.currentBatchSize;
        }
        catch (IOException ex) {
            throw HttpStorageRpc.access$300(ex);
        }
    }
    
    @Override
    public void addGet(final StorageObject storageObject, final Callback<StorageObject> callback, final Map<Option, ?> map) {
        try {
            if (this.currentBatchSize == 100) {
                this.batches.add(this.storage.batch());
                this.currentBatchSize = 0;
            }
            HttpStorageRpc.access$500(this.this$0, storageObject, map).queue((BatchRequest)this.batches.getLast(), HttpStorageRpc.access$100(callback));
            ++this.currentBatchSize;
        }
        catch (IOException ex) {
            throw HttpStorageRpc.access$300(ex);
        }
    }
    
    @Override
    public void submit() {
        final Span access$600 = HttpStorageRpc.access$600(this.this$0, HttpStorageRpcSpans.SPAN_NAME_BATCH_SUBMIT);
        final Scope withSpan = HttpStorageRpc.access$700(this.this$0).withSpan(access$600);
        try {
            access$600.putAttribute("batch size", AttributeValue.longAttributeValue((long)this.batches.size()));
            for (final BatchRequest batchRequest : this.batches) {
                access$600.addAnnotation("Execute batch request");
                batchRequest.setBatchUrl(new GenericUrl(String.format("%s/batch/storage/v1", HttpStorageRpc.access$800(this.this$0).getHost())));
                batchRequest.execute();
            }
        }
        catch (IOException ex) {
            access$600.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw HttpStorageRpc.access$300(ex);
        }
        finally {
            withSpan.close();
            access$600.end();
        }
    }
    
    DefaultRpcBatch(final HttpStorageRpc httpStorageRpc, final Storage storage, final HttpStorageRpc$1 jsonBatchCallback) {
        this(httpStorageRpc, storage);
    }
}
