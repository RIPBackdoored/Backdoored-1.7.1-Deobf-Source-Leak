package com.google.api.client.googleapis.services;

import com.google.api.client.googleapis.batch.BatchCallback;
import com.google.api.client.googleapis.batch.BatchRequest;
import java.io.OutputStream;
import java.io.InputStream;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseInterceptor;
import com.google.api.client.http.HttpEncoding;
import com.google.api.client.http.GZipEncoding;
import java.util.Map;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.googleapis.MethodOverride;
import java.io.IOException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.UriTemplate;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.util.Preconditions;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpContent;
import com.google.api.client.util.GenericData;

public abstract class AbstractGoogleClientRequest<T> extends GenericData
{
    public static final String USER_AGENT_SUFFIX = "Google-API-Java-Client";
    private final AbstractGoogleClient abstractGoogleClient;
    private final String requestMethod;
    private final String uriTemplate;
    private final HttpContent httpContent;
    private HttpHeaders requestHeaders;
    private HttpHeaders lastResponseHeaders;
    private int lastStatusCode;
    private String lastStatusMessage;
    private boolean disableGZipContent;
    private Class<T> responseClass;
    private MediaHttpUploader uploader;
    private MediaHttpDownloader downloader;
    
    protected AbstractGoogleClientRequest(final AbstractGoogleClient abstractGoogleClient, final String s, final String s2, final HttpContent httpContent, final Class<T> clazz) {
        super();
        this.requestHeaders = new HttpHeaders();
        this.lastStatusCode = -1;
        this.responseClass = Preconditions.<Class<T>>checkNotNull(clazz);
        this.abstractGoogleClient = Preconditions.<AbstractGoogleClient>checkNotNull(abstractGoogleClient);
        this.requestMethod = Preconditions.<String>checkNotNull(s);
        this.uriTemplate = Preconditions.<String>checkNotNull(s2);
        this.httpContent = httpContent;
        final String applicationName = abstractGoogleClient.getApplicationName();
        if (applicationName != null) {
            final HttpHeaders requestHeaders = this.requestHeaders;
            final String value = String.valueOf(String.valueOf(applicationName));
            final String value2 = String.valueOf(String.valueOf("Google-API-Java-Client"));
            requestHeaders.setUserAgent(new StringBuilder(1 + value.length() + value2.length()).append(value).append(" ").append(value2).toString());
        }
        else {
            this.requestHeaders.setUserAgent("Google-API-Java-Client");
        }
    }
    
    public final boolean getDisableGZipContent() {
        return this.disableGZipContent;
    }
    
    public AbstractGoogleClientRequest<T> setDisableGZipContent(final boolean disableGZipContent) {
        this.disableGZipContent = disableGZipContent;
        return this;
    }
    
    public final String getRequestMethod() {
        return this.requestMethod;
    }
    
    public final String getUriTemplate() {
        return this.uriTemplate;
    }
    
    public final HttpContent getHttpContent() {
        return this.httpContent;
    }
    
    public AbstractGoogleClient getAbstractGoogleClient() {
        return this.abstractGoogleClient;
    }
    
    public final HttpHeaders getRequestHeaders() {
        return this.requestHeaders;
    }
    
    public AbstractGoogleClientRequest<T> setRequestHeaders(final HttpHeaders requestHeaders) {
        this.requestHeaders = requestHeaders;
        return this;
    }
    
    public final HttpHeaders getLastResponseHeaders() {
        return this.lastResponseHeaders;
    }
    
    public final int getLastStatusCode() {
        return this.lastStatusCode;
    }
    
    public final String getLastStatusMessage() {
        return this.lastStatusMessage;
    }
    
    public final Class<T> getResponseClass() {
        return this.responseClass;
    }
    
    public final MediaHttpUploader getMediaHttpUploader() {
        return this.uploader;
    }
    
    protected final void initializeMediaUpload(final AbstractInputStreamContent abstractInputStreamContent) {
        final HttpRequestFactory requestFactory = this.abstractGoogleClient.getRequestFactory();
        (this.uploader = new MediaHttpUploader(abstractInputStreamContent, requestFactory.getTransport(), requestFactory.getInitializer())).setInitiationRequestMethod(this.requestMethod);
        if (this.httpContent != null) {
            this.uploader.setMetadata(this.httpContent);
        }
    }
    
    public final MediaHttpDownloader getMediaHttpDownloader() {
        return this.downloader;
    }
    
    protected final void initializeMediaDownload() {
        final HttpRequestFactory requestFactory = this.abstractGoogleClient.getRequestFactory();
        this.downloader = new MediaHttpDownloader(requestFactory.getTransport(), requestFactory.getInitializer());
    }
    
    public GenericUrl buildHttpRequestUrl() {
        return new GenericUrl(UriTemplate.expand(this.abstractGoogleClient.getBaseUrl(), this.uriTemplate, this, true));
    }
    
    public HttpRequest buildHttpRequest() throws IOException {
        return this.buildHttpRequest(false);
    }
    
    protected HttpRequest buildHttpRequestUsingHead() throws IOException {
        return this.buildHttpRequest(true);
    }
    
    private HttpRequest buildHttpRequest(final boolean b) throws IOException {
        Preconditions.checkArgument(this.uploader == null);
        Preconditions.checkArgument(!b || this.requestMethod.equals("GET"));
        final HttpRequest buildRequest = this.getAbstractGoogleClient().getRequestFactory().buildRequest(b ? "HEAD" : this.requestMethod, this.buildHttpRequestUrl(), this.httpContent);
        new MethodOverride().intercept(buildRequest);
        buildRequest.setParser(this.getAbstractGoogleClient().getObjectParser());
        if (this.httpContent == null && (this.requestMethod.equals("POST") || this.requestMethod.equals("PUT") || this.requestMethod.equals("PATCH"))) {
            buildRequest.setContent(new EmptyContent());
        }
        buildRequest.getHeaders().putAll(this.requestHeaders);
        if (!this.disableGZipContent) {
            buildRequest.setEncoding(new GZipEncoding());
        }
        buildRequest.setResponseInterceptor(new HttpResponseInterceptor() {
            final /* synthetic */ HttpResponseInterceptor val$responseInterceptor = buildRequest.getResponseInterceptor();
            final /* synthetic */ HttpRequest val$httpRequest;
            final /* synthetic */ AbstractGoogleClientRequest this$0;
            
            AbstractGoogleClientRequest$1() {
                this.this$0 = this$0;
                super();
            }
            
            public void interceptResponse(final HttpResponse httpResponse) throws IOException {
                if (this.val$responseInterceptor != null) {
                    this.val$responseInterceptor.interceptResponse(httpResponse);
                }
                if (!httpResponse.isSuccessStatusCode() && buildRequest.getThrowExceptionOnExecuteError()) {
                    throw this.this$0.newExceptionOnError(httpResponse);
                }
            }
        });
        return buildRequest;
    }
    
    public HttpResponse executeUnparsed() throws IOException {
        return this.executeUnparsed(false);
    }
    
    protected HttpResponse executeMedia() throws IOException {
        this.set("alt", "media");
        return this.executeUnparsed();
    }
    
    protected HttpResponse executeUsingHead() throws IOException {
        Preconditions.checkArgument(this.uploader == null);
        final HttpResponse executeUnparsed = this.executeUnparsed(true);
        executeUnparsed.ignore();
        return executeUnparsed;
    }
    
    private HttpResponse executeUnparsed(final boolean b) throws IOException {
        HttpResponse httpResponse;
        if (this.uploader == null) {
            httpResponse = this.buildHttpRequest(b).execute();
        }
        else {
            final GenericUrl buildHttpRequestUrl = this.buildHttpRequestUrl();
            final boolean throwExceptionOnExecuteError = this.getAbstractGoogleClient().getRequestFactory().buildRequest(this.requestMethod, buildHttpRequestUrl, this.httpContent).getThrowExceptionOnExecuteError();
            httpResponse = this.uploader.setInitiationHeaders(this.requestHeaders).setDisableGZipContent(this.disableGZipContent).upload(buildHttpRequestUrl);
            httpResponse.getRequest().setParser(this.getAbstractGoogleClient().getObjectParser());
            if (throwExceptionOnExecuteError && !httpResponse.isSuccessStatusCode()) {
                throw this.newExceptionOnError(httpResponse);
            }
        }
        this.lastResponseHeaders = httpResponse.getHeaders();
        this.lastStatusCode = httpResponse.getStatusCode();
        this.lastStatusMessage = httpResponse.getStatusMessage();
        return httpResponse;
    }
    
    protected IOException newExceptionOnError(final HttpResponse httpResponse) {
        return new HttpResponseException(httpResponse);
    }
    
    public T execute() throws IOException {
        return this.executeUnparsed().<T>parseAs(this.responseClass);
    }
    
    public InputStream executeAsInputStream() throws IOException {
        return this.executeUnparsed().getContent();
    }
    
    protected InputStream executeMediaAsInputStream() throws IOException {
        return this.executeMedia().getContent();
    }
    
    public void executeAndDownloadTo(final OutputStream outputStream) throws IOException {
        this.executeUnparsed().download(outputStream);
    }
    
    protected void executeMediaAndDownloadTo(final OutputStream outputStream) throws IOException {
        if (this.downloader == null) {
            this.executeMedia().download(outputStream);
        }
        else {
            this.downloader.download(this.buildHttpRequestUrl(), this.requestHeaders, outputStream);
        }
    }
    
    public final <E> void queue(final BatchRequest batchRequest, final Class<E> clazz, final BatchCallback<T, E> batchCallback) throws IOException {
        Preconditions.checkArgument(this.uploader == null, (Object)"Batching media requests is not supported");
        batchRequest.<T, E>queue(this.buildHttpRequest(), this.getResponseClass(), clazz, batchCallback);
    }
    
    @Override
    public AbstractGoogleClientRequest<T> set(final String s, final Object o) {
        return (AbstractGoogleClientRequest)super.set(s, o);
    }
    
    protected final void checkRequiredParameter(final Object o, final String s) {
        Preconditions.checkArgument(this.abstractGoogleClient.getSuppressRequiredParameterChecks() || o != null, "Required parameter %s must be specified", s);
    }
    
    @Override
    public /* bridge */ GenericData set(final String s, final Object o) {
        return this.set(s, o);
    }
}
