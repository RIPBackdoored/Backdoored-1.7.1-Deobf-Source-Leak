package com.google.api.client.googleapis.media;

import com.google.api.client.util.Beta;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.util.ByteStreams;
import com.google.api.client.http.HttpEncoding;
import com.google.api.client.http.GZipEncoding;
import com.google.api.client.googleapis.MethodOverride;
import com.google.api.client.http.EmptyContent;
import java.io.BufferedInputStream;
import java.util.Map;
import java.util.Collection;
import java.util.Arrays;
import com.google.api.client.http.MultipartContent;
import java.io.IOException;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Preconditions;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.Sleeper;
import java.io.InputStream;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.AbstractInputStreamContent;

public final class MediaHttpUploader
{
    public static final String CONTENT_LENGTH_HEADER = "X-Upload-Content-Length";
    public static final String CONTENT_TYPE_HEADER = "X-Upload-Content-Type";
    private UploadState uploadState;
    static final int MB = 1048576;
    private static final int KB = 1024;
    public static final int MINIMUM_CHUNK_SIZE = 262144;
    public static final int DEFAULT_CHUNK_SIZE = 10485760;
    private final AbstractInputStreamContent mediaContent;
    private final HttpRequestFactory requestFactory;
    private final HttpTransport transport;
    private HttpContent metadata;
    private long mediaContentLength;
    private boolean isMediaContentLengthCalculated;
    private String initiationRequestMethod;
    private HttpHeaders initiationHeaders;
    private HttpRequest currentRequest;
    private InputStream contentInputStream;
    private boolean directUploadEnabled;
    private MediaHttpUploaderProgressListener progressListener;
    String mediaContentLengthStr;
    private long totalBytesServerReceived;
    private int chunkSize;
    private Byte cachedByte;
    private long totalBytesClientSent;
    private int currentChunkLength;
    private byte[] currentRequestContentBuffer;
    private boolean disableGZipContent;
    Sleeper sleeper;
    
    public MediaHttpUploader(final AbstractInputStreamContent abstractInputStreamContent, final HttpTransport httpTransport, final HttpRequestInitializer httpRequestInitializer) {
        super();
        this.uploadState = UploadState.NOT_STARTED;
        this.initiationRequestMethod = "POST";
        this.initiationHeaders = new HttpHeaders();
        this.mediaContentLengthStr = "*";
        this.chunkSize = 10485760;
        this.sleeper = Sleeper.DEFAULT;
        this.mediaContent = Preconditions.<AbstractInputStreamContent>checkNotNull(abstractInputStreamContent);
        this.transport = Preconditions.<HttpTransport>checkNotNull(httpTransport);
        this.requestFactory = ((httpRequestInitializer == null) ? httpTransport.createRequestFactory() : httpTransport.createRequestFactory(httpRequestInitializer));
    }
    
    public HttpResponse upload(final GenericUrl genericUrl) throws IOException {
        Preconditions.checkArgument(this.uploadState == UploadState.NOT_STARTED);
        if (this.directUploadEnabled) {
            return this.directUpload(genericUrl);
        }
        return this.resumableUpload(genericUrl);
    }
    
    private HttpResponse directUpload(final GenericUrl genericUrl) throws IOException {
        this.updateStateAndNotifyListener(UploadState.MEDIA_IN_PROGRESS);
        HttpContent httpContent = this.mediaContent;
        if (this.metadata != null) {
            httpContent = new MultipartContent().setContentParts(Arrays.<HttpContent>asList(this.metadata, this.mediaContent));
            genericUrl.put("uploadType", "multipart");
        }
        else {
            genericUrl.put("uploadType", "media");
        }
        final HttpRequest buildRequest = this.requestFactory.buildRequest(this.initiationRequestMethod, genericUrl, httpContent);
        buildRequest.getHeaders().putAll(this.initiationHeaders);
        final HttpResponse executeCurrentRequest = this.executeCurrentRequest(buildRequest);
        try {
            if (this.isMediaLengthKnown()) {
                this.totalBytesServerReceived = this.getMediaContentLength();
            }
            this.updateStateAndNotifyListener(UploadState.MEDIA_COMPLETE);
        }
        finally {
            executeCurrentRequest.disconnect();
        }
        return executeCurrentRequest;
    }
    
    private HttpResponse resumableUpload(final GenericUrl genericUrl) throws IOException {
        final HttpResponse executeUploadInitiation = this.executeUploadInitiation(genericUrl);
        if (!executeUploadInitiation.isSuccessStatusCode()) {
            return executeUploadInitiation;
        }
        GenericUrl genericUrl2;
        try {
            genericUrl2 = new GenericUrl(executeUploadInitiation.getHeaders().getLocation());
        }
        finally {
            executeUploadInitiation.disconnect();
        }
        this.contentInputStream = this.mediaContent.getInputStream();
        if (!this.contentInputStream.markSupported() && this.isMediaLengthKnown()) {
            this.contentInputStream = new BufferedInputStream(this.contentInputStream);
        }
        while (true) {
            this.currentRequest = this.requestFactory.buildPutRequest(genericUrl2, null);
            this.setContentAndHeadersOnCurrentRequest();
            new MediaUploadErrorHandler(this, this.currentRequest);
            HttpResponse httpResponse;
            if (this.isMediaLengthKnown()) {
                httpResponse = this.executeCurrentRequestWithoutGZip(this.currentRequest);
            }
            else {
                httpResponse = this.executeCurrentRequest(this.currentRequest);
            }
            try {
                if (httpResponse.isSuccessStatusCode()) {
                    this.totalBytesServerReceived = this.getMediaContentLength();
                    if (this.mediaContent.getCloseInputStream()) {
                        this.contentInputStream.close();
                    }
                    this.updateStateAndNotifyListener(UploadState.MEDIA_COMPLETE);
                    return httpResponse;
                }
                if (httpResponse.getStatusCode() != 308) {
                    return httpResponse;
                }
                final String location = httpResponse.getHeaders().getLocation();
                if (location != null) {
                    genericUrl2 = new GenericUrl(location);
                }
                final long nextByteIndex = this.getNextByteIndex(httpResponse.getHeaders().getRange());
                final long n = nextByteIndex - this.totalBytesServerReceived;
                Preconditions.checkState(n >= 0L && n <= this.currentChunkLength);
                final long n2 = this.currentChunkLength - n;
                if (this.isMediaLengthKnown()) {
                    if (n2 > 0L) {
                        this.contentInputStream.reset();
                        Preconditions.checkState(n == this.contentInputStream.skip(n));
                    }
                }
                else if (n2 == 0L) {
                    this.currentRequestContentBuffer = null;
                }
                this.totalBytesServerReceived = nextByteIndex;
                this.updateStateAndNotifyListener(UploadState.MEDIA_IN_PROGRESS);
            }
            finally {
                httpResponse.disconnect();
            }
        }
    }
    
    private boolean isMediaLengthKnown() throws IOException {
        return this.getMediaContentLength() >= 0L;
    }
    
    private long getMediaContentLength() throws IOException {
        if (!this.isMediaContentLengthCalculated) {
            this.mediaContentLength = this.mediaContent.getLength();
            this.isMediaContentLengthCalculated = true;
        }
        return this.mediaContentLength;
    }
    
    private HttpResponse executeUploadInitiation(final GenericUrl genericUrl) throws IOException {
        this.updateStateAndNotifyListener(UploadState.INITIATION_STARTED);
        genericUrl.put("uploadType", "resumable");
        final HttpRequest buildRequest = this.requestFactory.buildRequest(this.initiationRequestMethod, genericUrl, (this.metadata == null) ? new EmptyContent() : this.metadata);
        this.initiationHeaders.set("X-Upload-Content-Type", this.mediaContent.getType());
        if (this.isMediaLengthKnown()) {
            this.initiationHeaders.set("X-Upload-Content-Length", this.getMediaContentLength());
        }
        buildRequest.getHeaders().putAll(this.initiationHeaders);
        final HttpResponse executeCurrentRequest = this.executeCurrentRequest(buildRequest);
        try {
            this.updateStateAndNotifyListener(UploadState.INITIATION_COMPLETE);
        }
        finally {
            executeCurrentRequest.disconnect();
        }
        return executeCurrentRequest;
    }
    
    private HttpResponse executeCurrentRequestWithoutGZip(final HttpRequest httpRequest) throws IOException {
        new MethodOverride().intercept(httpRequest);
        httpRequest.setThrowExceptionOnExecuteError(false);
        return httpRequest.execute();
    }
    
    private HttpResponse executeCurrentRequest(final HttpRequest httpRequest) throws IOException {
        if (!this.disableGZipContent && !(httpRequest.getContent() instanceof EmptyContent)) {
            httpRequest.setEncoding(new GZipEncoding());
        }
        return this.executeCurrentRequestWithoutGZip(httpRequest);
    }
    
    private void setContentAndHeadersOnCurrentRequest() throws IOException {
        int chunkSize;
        if (this.isMediaLengthKnown()) {
            chunkSize = (int)Math.min(this.chunkSize, this.getMediaContentLength() - this.totalBytesServerReceived);
        }
        else {
            chunkSize = this.chunkSize;
        }
        int currentChunkLength = chunkSize;
        AbstractInputStreamContent setCloseInputStream;
        if (this.isMediaLengthKnown()) {
            this.contentInputStream.mark(chunkSize);
            setCloseInputStream = new InputStreamContent(this.mediaContent.getType(), ByteStreams.limit(this.contentInputStream, chunkSize)).setRetrySupported(true).setLength(chunkSize).setCloseInputStream(false);
            this.mediaContentLengthStr = String.valueOf(this.getMediaContentLength());
        }
        else {
            int n = 0;
            int n2;
            if (this.currentRequestContentBuffer == null) {
                n2 = ((this.cachedByte == null) ? (chunkSize + 1) : chunkSize);
                this.currentRequestContentBuffer = new byte[chunkSize + 1];
                if (this.cachedByte != null) {
                    this.currentRequestContentBuffer[0] = this.cachedByte;
                }
            }
            else {
                n = (int)(this.totalBytesClientSent - this.totalBytesServerReceived);
                System.arraycopy(this.currentRequestContentBuffer, this.currentChunkLength - n, this.currentRequestContentBuffer, 0, n);
                if (this.cachedByte != null) {
                    this.currentRequestContentBuffer[n] = this.cachedByte;
                }
                n2 = chunkSize - n;
            }
            final int read = ByteStreams.read(this.contentInputStream, this.currentRequestContentBuffer, chunkSize + 1 - n2, n2);
            if (read < n2) {
                currentChunkLength = n + Math.max(0, read);
                if (this.cachedByte != null) {
                    ++currentChunkLength;
                    this.cachedByte = null;
                }
                if (this.mediaContentLengthStr.equals("*")) {
                    this.mediaContentLengthStr = String.valueOf(this.totalBytesServerReceived + currentChunkLength);
                }
            }
            else {
                this.cachedByte = this.currentRequestContentBuffer[chunkSize];
            }
            setCloseInputStream = new ByteArrayContent(this.mediaContent.getType(), this.currentRequestContentBuffer, 0, currentChunkLength);
            this.totalBytesClientSent = this.totalBytesServerReceived + currentChunkLength;
        }
        this.currentChunkLength = currentChunkLength;
        this.currentRequest.setContent(setCloseInputStream);
        final HttpHeaders headers = this.currentRequest.getHeaders();
        final String s = "bytes */";
        final String value = String.valueOf(this.mediaContentLengthStr);
        headers.setContentRange((value.length() != 0) ? s.concat(value) : new String(s));
    }
    
    @Beta
    @Beta
    void serverErrorCallback() throws IOException {
        Preconditions.<HttpRequest>checkNotNull(this.currentRequest, (Object)"The current request should not be null");
        this.currentRequest.setContent(new EmptyContent());
        final HttpHeaders headers = this.currentRequest.getHeaders();
        final String s = "bytes */";
        final String value = String.valueOf(this.mediaContentLengthStr);
        headers.setContentRange((value.length() != 0) ? s.concat(value) : new String(s));
    }
    
    private long getNextByteIndex(final String s) {
        if (s == null) {
            return 0L;
        }
        return Long.parseLong(s.substring(s.indexOf(45) + 1)) + 1L;
    }
    
    public HttpContent getMetadata() {
        return this.metadata;
    }
    
    public MediaHttpUploader setMetadata(final HttpContent metadata) {
        this.metadata = metadata;
        return this;
    }
    
    public HttpContent getMediaContent() {
        return this.mediaContent;
    }
    
    public HttpTransport getTransport() {
        return this.transport;
    }
    
    public MediaHttpUploader setDirectUploadEnabled(final boolean directUploadEnabled) {
        this.directUploadEnabled = directUploadEnabled;
        return this;
    }
    
    public boolean isDirectUploadEnabled() {
        return this.directUploadEnabled;
    }
    
    public MediaHttpUploader setProgressListener(final MediaHttpUploaderProgressListener progressListener) {
        this.progressListener = progressListener;
        return this;
    }
    
    public MediaHttpUploaderProgressListener getProgressListener() {
        return this.progressListener;
    }
    
    public MediaHttpUploader setChunkSize(final int chunkSize) {
        Preconditions.checkArgument(chunkSize > 0 && chunkSize % 262144 == 0, (Object)"chunkSize must be a positive multiple of 262144.");
        this.chunkSize = chunkSize;
        return this;
    }
    
    public int getChunkSize() {
        return this.chunkSize;
    }
    
    public boolean getDisableGZipContent() {
        return this.disableGZipContent;
    }
    
    public MediaHttpUploader setDisableGZipContent(final boolean disableGZipContent) {
        this.disableGZipContent = disableGZipContent;
        return this;
    }
    
    public Sleeper getSleeper() {
        return this.sleeper;
    }
    
    public MediaHttpUploader setSleeper(final Sleeper sleeper) {
        this.sleeper = sleeper;
        return this;
    }
    
    public String getInitiationRequestMethod() {
        return this.initiationRequestMethod;
    }
    
    public MediaHttpUploader setInitiationRequestMethod(final String initiationRequestMethod) {
        Preconditions.checkArgument(initiationRequestMethod.equals("POST") || initiationRequestMethod.equals("PUT") || initiationRequestMethod.equals("PATCH"));
        this.initiationRequestMethod = initiationRequestMethod;
        return this;
    }
    
    public MediaHttpUploader setInitiationHeaders(final HttpHeaders initiationHeaders) {
        this.initiationHeaders = initiationHeaders;
        return this;
    }
    
    public HttpHeaders getInitiationHeaders() {
        return this.initiationHeaders;
    }
    
    public long getNumBytesUploaded() {
        return this.totalBytesServerReceived;
    }
    
    private void updateStateAndNotifyListener(final UploadState uploadState) throws IOException {
        this.uploadState = uploadState;
        if (this.progressListener != null) {
            this.progressListener.progressChanged(this);
        }
    }
    
    public UploadState getUploadState() {
        return this.uploadState;
    }
    
    public double getProgress() throws IOException {
        Preconditions.checkArgument(this.isMediaLengthKnown(), (Object)"Cannot call getProgress() if the specified AbstractInputStreamContent has no content length. Use  getNumBytesUploaded() to denote progress instead.");
        return (this.getMediaContentLength() == 0L) ? 0.0 : (this.totalBytesServerReceived / (double)this.getMediaContentLength());
    }
}
