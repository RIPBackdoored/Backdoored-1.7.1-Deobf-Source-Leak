package com.google.api.client.googleapis.media;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.IOUtils;
import java.util.Map;
import com.google.api.client.http.HttpResponse;
import java.io.IOException;
import com.google.api.client.http.HttpHeaders;
import java.io.OutputStream;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Preconditions;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpRequestFactory;

public final class MediaHttpDownloader
{
    public static final int MAXIMUM_CHUNK_SIZE = 33554432;
    private final HttpRequestFactory requestFactory;
    private final HttpTransport transport;
    private boolean directDownloadEnabled;
    private MediaHttpDownloaderProgressListener progressListener;
    private int chunkSize;
    private long mediaContentLength;
    private DownloadState downloadState;
    private long bytesDownloaded;
    private long lastBytePos;
    
    public MediaHttpDownloader(final HttpTransport httpTransport, final HttpRequestInitializer httpRequestInitializer) {
        super();
        this.directDownloadEnabled = false;
        this.chunkSize = 33554432;
        this.downloadState = DownloadState.NOT_STARTED;
        this.lastBytePos = -1L;
        this.transport = Preconditions.<HttpTransport>checkNotNull(httpTransport);
        this.requestFactory = ((httpRequestInitializer == null) ? httpTransport.createRequestFactory() : httpTransport.createRequestFactory(httpRequestInitializer));
    }
    
    public void download(final GenericUrl genericUrl, final OutputStream outputStream) throws IOException {
        this.download(genericUrl, null, outputStream);
    }
    
    public void download(final GenericUrl genericUrl, final HttpHeaders httpHeaders, final OutputStream outputStream) throws IOException {
        Preconditions.checkArgument(this.downloadState == DownloadState.NOT_STARTED);
        genericUrl.put("alt", "media");
        if (this.directDownloadEnabled) {
            this.updateStateAndNotifyListener(DownloadState.MEDIA_IN_PROGRESS);
            this.mediaContentLength = this.executeCurrentRequest(this.lastBytePos, genericUrl, httpHeaders, outputStream).getHeaders().getContentLength();
            this.bytesDownloaded = this.mediaContentLength;
            this.updateStateAndNotifyListener(DownloadState.MEDIA_COMPLETE);
            return;
        }
        while (true) {
            long min = this.bytesDownloaded + this.chunkSize - 1L;
            if (this.lastBytePos != -1L) {
                min = Math.min(this.lastBytePos, min);
            }
            final String contentRange = this.executeCurrentRequest(min, genericUrl, httpHeaders, outputStream).getHeaders().getContentRange();
            final long nextByteIndex = this.getNextByteIndex(contentRange);
            this.setMediaContentLength(contentRange);
            if (this.mediaContentLength <= nextByteIndex) {
                break;
            }
            this.bytesDownloaded = nextByteIndex;
            this.updateStateAndNotifyListener(DownloadState.MEDIA_IN_PROGRESS);
        }
        this.bytesDownloaded = this.mediaContentLength;
        this.updateStateAndNotifyListener(DownloadState.MEDIA_COMPLETE);
    }
    
    private HttpResponse executeCurrentRequest(final long n, final GenericUrl genericUrl, final HttpHeaders httpHeaders, final OutputStream outputStream) throws IOException {
        final HttpRequest buildGetRequest = this.requestFactory.buildGetRequest(genericUrl);
        if (httpHeaders != null) {
            buildGetRequest.getHeaders().putAll(httpHeaders);
        }
        if (this.bytesDownloaded != 0L || n != -1L) {
            final StringBuilder sb = new StringBuilder();
            sb.append("bytes=").append(this.bytesDownloaded).append("-");
            if (n != -1L) {
                sb.append(n);
            }
            buildGetRequest.getHeaders().setRange(sb.toString());
        }
        final HttpResponse execute = buildGetRequest.execute();
        try {
            IOUtils.copy(execute.getContent(), outputStream);
        }
        finally {
            execute.disconnect();
        }
        return execute;
    }
    
    private long getNextByteIndex(final String s) {
        if (s == null) {
            return 0L;
        }
        return Long.parseLong(s.substring(s.indexOf(45) + 1, s.indexOf(47))) + 1L;
    }
    
    public MediaHttpDownloader setBytesDownloaded(final long bytesDownloaded) {
        Preconditions.checkArgument(bytesDownloaded >= 0L);
        this.bytesDownloaded = bytesDownloaded;
        return this;
    }
    
    public MediaHttpDownloader setContentRange(final long bytesDownloaded, final int n) {
        Preconditions.checkArgument(n >= bytesDownloaded);
        this.setBytesDownloaded(bytesDownloaded);
        this.lastBytePos = n;
        return this;
    }
    
    private void setMediaContentLength(final String s) {
        if (s == null) {
            return;
        }
        if (this.mediaContentLength == 0L) {
            this.mediaContentLength = Long.parseLong(s.substring(s.indexOf(47) + 1));
        }
    }
    
    public boolean isDirectDownloadEnabled() {
        return this.directDownloadEnabled;
    }
    
    public MediaHttpDownloader setDirectDownloadEnabled(final boolean directDownloadEnabled) {
        this.directDownloadEnabled = directDownloadEnabled;
        return this;
    }
    
    public MediaHttpDownloader setProgressListener(final MediaHttpDownloaderProgressListener progressListener) {
        this.progressListener = progressListener;
        return this;
    }
    
    public MediaHttpDownloaderProgressListener getProgressListener() {
        return this.progressListener;
    }
    
    public HttpTransport getTransport() {
        return this.transport;
    }
    
    public MediaHttpDownloader setChunkSize(final int chunkSize) {
        Preconditions.checkArgument(chunkSize > 0 && chunkSize <= 33554432);
        this.chunkSize = chunkSize;
        return this;
    }
    
    public int getChunkSize() {
        return this.chunkSize;
    }
    
    public long getNumBytesDownloaded() {
        return this.bytesDownloaded;
    }
    
    public long getLastBytePosition() {
        return this.lastBytePos;
    }
    
    private void updateStateAndNotifyListener(final DownloadState downloadState) throws IOException {
        this.downloadState = downloadState;
        if (this.progressListener != null) {
            this.progressListener.progressChanged(this);
        }
    }
    
    public DownloadState getDownloadState() {
        return this.downloadState;
    }
    
    public double getProgress() {
        return (this.mediaContentLength == 0L) ? 0.0 : (this.bytesDownloaded / (double)this.mediaContentLength);
    }
}
