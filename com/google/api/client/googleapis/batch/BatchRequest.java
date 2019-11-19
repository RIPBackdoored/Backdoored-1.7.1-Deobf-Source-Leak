package com.google.api.client.googleapis.batch;

import com.google.api.client.http.HttpResponse;
import java.util.Iterator;
import com.google.api.client.http.BackOffPolicy;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.MultipartContent;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpContent;
import java.io.IOException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.Preconditions;
import java.util.ArrayList;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Sleeper;
import java.util.List;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.GenericUrl;

public final class BatchRequest
{
    private GenericUrl batchUrl;
    private final HttpRequestFactory requestFactory;
    List<RequestInfo<?, ?>> requestInfos;
    private Sleeper sleeper;
    
    public BatchRequest(final HttpTransport httpTransport, final HttpRequestInitializer httpRequestInitializer) {
        super();
        this.batchUrl = new GenericUrl("https://www.googleapis.com/batch");
        this.requestInfos = new ArrayList<RequestInfo<?, ?>>();
        this.sleeper = Sleeper.DEFAULT;
        this.requestFactory = ((httpRequestInitializer == null) ? httpTransport.createRequestFactory() : httpTransport.createRequestFactory(httpRequestInitializer));
    }
    
    public BatchRequest setBatchUrl(final GenericUrl batchUrl) {
        this.batchUrl = batchUrl;
        return this;
    }
    
    public GenericUrl getBatchUrl() {
        return this.batchUrl;
    }
    
    public Sleeper getSleeper() {
        return this.sleeper;
    }
    
    public BatchRequest setSleeper(final Sleeper sleeper) {
        this.sleeper = Preconditions.<Sleeper>checkNotNull(sleeper);
        return this;
    }
    
    public <T, E> BatchRequest queue(final HttpRequest httpRequest, final Class<T> clazz, final Class<E> clazz2, final BatchCallback<T, E> batchCallback) throws IOException {
        Preconditions.<HttpRequest>checkNotNull(httpRequest);
        Preconditions.<BatchCallback<T, E>>checkNotNull(batchCallback);
        Preconditions.<Class<T>>checkNotNull(clazz);
        Preconditions.<Class<E>>checkNotNull(clazz2);
        this.requestInfos.add(new RequestInfo<Object, Object>((BatchCallback<Object, Object>)batchCallback, (Class<Object>)clazz, (Class<Object>)clazz2, httpRequest));
        return this;
    }
    
    public int size() {
        return this.requestInfos.size();
    }
    
    public void execute() throws IOException {
        Preconditions.checkState(!this.requestInfos.isEmpty());
        final HttpRequest buildPostRequest = this.requestFactory.buildPostRequest(this.batchUrl, null);
        buildPostRequest.setInterceptor(new BatchInterceptor(buildPostRequest.getInterceptor()));
        int numberOfRetries = buildPostRequest.getNumberOfRetries();
        final BackOffPolicy backOffPolicy = buildPostRequest.getBackOffPolicy();
        if (backOffPolicy != null) {
            backOffPolicy.reset();
        }
        final boolean b = numberOfRetries > 0;
        final MultipartContent content = new MultipartContent();
        content.getMediaType().setSubType("mixed");
        int n = 1;
        for (final Object o : this.requestInfos) {
            content.addPart(new MultipartContent.Part(new HttpHeaders().setAcceptEncoding(null).set("Content-ID", n++), new HttpRequestContent(((RequestInfo)o).request)));
        }
        buildPostRequest.setContent(content);
        final HttpResponse execute = buildPostRequest.execute();
        Object o;
        try {
            final String s = "--";
            final String value = String.valueOf(execute.getMediaType().getParameter("boundary"));
            o = new BatchUnparsedResponse(execute.getContent(), (value.length() != 0) ? s.concat(value) : new String(s), this.requestInfos, b);
            while (((BatchUnparsedResponse)o).hasNext) {
                ((BatchUnparsedResponse)o).parseNextResponse();
            }
        }
        finally {
            execute.disconnect();
        }
        final List<RequestInfo<?, ?>> unsuccessfulRequestInfos = ((BatchUnparsedResponse)o).unsuccessfulRequestInfos;
        if (!unsuccessfulRequestInfos.isEmpty()) {
            this.requestInfos = unsuccessfulRequestInfos;
            if (((BatchUnparsedResponse)o).backOffRequired && backOffPolicy != null) {
                final long nextBackOffMillis = backOffPolicy.getNextBackOffMillis();
                if (nextBackOffMillis != -1L) {
                    try {
                        this.sleeper.sleep(nextBackOffMillis);
                    }
                    catch (InterruptedException ex) {}
                }
            }
            --numberOfRetries;
        }
        this.requestInfos.clear();
    }
}
