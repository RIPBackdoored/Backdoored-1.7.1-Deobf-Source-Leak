package com.google.cloud.storage.spi.v1;

import com.google.api.client.googleapis.json.GoogleJsonError;
import java.io.IOException;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;

static final class HttpStorageRpc$1 extends JsonBatchCallback<T> {
    final /* synthetic */ RpcBatch.Callback val$callback;
    
    HttpStorageRpc$1(final RpcBatch.Callback val$callback) {
        this.val$callback = val$callback;
        super();
    }
    
    @Override
    public void onSuccess(final T t, final HttpHeaders httpHeaders) throws IOException {
        this.val$callback.onSuccess(t);
    }
    
    @Override
    public void onFailure(final GoogleJsonError googleJsonError, final HttpHeaders httpHeaders) throws IOException {
        this.val$callback.onFailure(googleJsonError);
    }
}