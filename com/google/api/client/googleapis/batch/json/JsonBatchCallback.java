package com.google.api.client.googleapis.batch.json;

import com.google.api.client.googleapis.json.GoogleJsonError;
import java.io.IOException;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.googleapis.json.GoogleJsonErrorContainer;
import com.google.api.client.googleapis.batch.BatchCallback;

public abstract class JsonBatchCallback<T> implements BatchCallback<T, GoogleJsonErrorContainer>
{
    public JsonBatchCallback() {
        super();
    }
    
    public final void onFailure(final GoogleJsonErrorContainer googleJsonErrorContainer, final HttpHeaders httpHeaders) throws IOException {
        this.onFailure(googleJsonErrorContainer.getError(), httpHeaders);
    }
    
    public abstract void onFailure(final GoogleJsonError p0, final HttpHeaders p1) throws IOException;
    
    public /* bridge */ void onFailure(final Object o, final HttpHeaders httpHeaders) throws IOException {
        this.onFailure((GoogleJsonErrorContainer)o, httpHeaders);
    }
}
