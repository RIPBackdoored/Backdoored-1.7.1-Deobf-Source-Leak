package com.google.api.client.googleapis.services.json;

import com.google.api.client.util.GenericData;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpResponse;
import java.io.IOException;
import com.google.api.client.googleapis.batch.BatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonErrorContainer;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpContent;
import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;

public abstract class AbstractGoogleJsonClientRequest<T> extends AbstractGoogleClientRequest<T>
{
    private final Object jsonContent;
    
    protected AbstractGoogleJsonClientRequest(final AbstractGoogleJsonClient abstractGoogleJsonClient, final String s, final String s2, final Object jsonContent, final Class<T> clazz) {
        super(abstractGoogleJsonClient, s, s2, (jsonContent == null) ? null : new JsonHttpContent(abstractGoogleJsonClient.getJsonFactory(), jsonContent).setWrapperKey(abstractGoogleJsonClient.getObjectParser().getWrapperKeys().isEmpty() ? null : "data"), clazz);
        this.jsonContent = jsonContent;
    }
    
    @Override
    public AbstractGoogleJsonClient getAbstractGoogleClient() {
        return (AbstractGoogleJsonClient)super.getAbstractGoogleClient();
    }
    
    @Override
    public AbstractGoogleJsonClientRequest<T> setDisableGZipContent(final boolean disableGZipContent) {
        return (AbstractGoogleJsonClientRequest)super.setDisableGZipContent(disableGZipContent);
    }
    
    @Override
    public AbstractGoogleJsonClientRequest<T> setRequestHeaders(final HttpHeaders requestHeaders) {
        return (AbstractGoogleJsonClientRequest)super.setRequestHeaders(requestHeaders);
    }
    
    public final void queue(final BatchRequest batchRequest, final JsonBatchCallback<T> jsonBatchCallback) throws IOException {
        super.<GoogleJsonErrorContainer>queue(batchRequest, GoogleJsonErrorContainer.class, jsonBatchCallback);
    }
    
    @Override
    protected GoogleJsonResponseException newExceptionOnError(final HttpResponse httpResponse) {
        return GoogleJsonResponseException.from(this.getAbstractGoogleClient().getJsonFactory(), httpResponse);
    }
    
    public Object getJsonContent() {
        return this.jsonContent;
    }
    
    @Override
    public AbstractGoogleJsonClientRequest<T> set(final String s, final Object o) {
        return (AbstractGoogleJsonClientRequest)super.set(s, o);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClientRequest set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    protected /* bridge */ IOException newExceptionOnError(final HttpResponse httpResponse) {
        return this.newExceptionOnError(httpResponse);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClientRequest setRequestHeaders(final HttpHeaders requestHeaders) {
        return this.setRequestHeaders(requestHeaders);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClient getAbstractGoogleClient() {
        return this.getAbstractGoogleClient();
    }
    
    @Override
    public /* bridge */ AbstractGoogleClientRequest setDisableGZipContent(final boolean disableGZipContent) {
        return this.setDisableGZipContent(disableGZipContent);
    }
    
    @Override
    public /* bridge */ GenericData set(final String s, final Object o) {
        return this.set(s, o);
    }
}
