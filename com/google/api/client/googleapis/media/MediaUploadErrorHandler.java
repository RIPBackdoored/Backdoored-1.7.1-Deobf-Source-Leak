package com.google.api.client.googleapis.media;

import com.google.api.client.http.HttpResponse;
import java.io.IOException;
import java.util.logging.Level;
import com.google.api.client.util.Preconditions;
import com.google.api.client.http.HttpRequest;
import java.util.logging.Logger;
import com.google.api.client.util.Beta;
import com.google.api.client.http.HttpIOExceptionHandler;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;

@Beta
class MediaUploadErrorHandler implements HttpUnsuccessfulResponseHandler, HttpIOExceptionHandler
{
    static final Logger LOGGER;
    private final MediaHttpUploader uploader;
    private final HttpIOExceptionHandler originalIOExceptionHandler;
    private final HttpUnsuccessfulResponseHandler originalUnsuccessfulHandler;
    
    public MediaUploadErrorHandler(final MediaHttpUploader mediaHttpUploader, final HttpRequest httpRequest) {
        super();
        this.uploader = Preconditions.<MediaHttpUploader>checkNotNull(mediaHttpUploader);
        this.originalIOExceptionHandler = httpRequest.getIOExceptionHandler();
        this.originalUnsuccessfulHandler = httpRequest.getUnsuccessfulResponseHandler();
        httpRequest.setIOExceptionHandler(this);
        httpRequest.setUnsuccessfulResponseHandler(this);
    }
    
    public boolean handleIOException(final HttpRequest httpRequest, final boolean b) throws IOException {
        final boolean b2 = this.originalIOExceptionHandler != null && this.originalIOExceptionHandler.handleIOException(httpRequest, b);
        if (b2) {
            try {
                this.uploader.serverErrorCallback();
            }
            catch (IOException ex) {
                MediaUploadErrorHandler.LOGGER.log(Level.WARNING, "exception thrown while calling server callback", ex);
            }
        }
        return b2;
    }
    
    public boolean handleResponse(final HttpRequest httpRequest, final HttpResponse httpResponse, final boolean b) throws IOException {
        final boolean b2 = this.originalUnsuccessfulHandler != null && this.originalUnsuccessfulHandler.handleResponse(httpRequest, httpResponse, b);
        if (b2 && b && httpResponse.getStatusCode() / 100 == 5) {
            try {
                this.uploader.serverErrorCallback();
            }
            catch (IOException ex) {
                MediaUploadErrorHandler.LOGGER.log(Level.WARNING, "exception thrown while calling server callback", ex);
            }
        }
        return b2;
    }
    
    static {
        LOGGER = Logger.getLogger(MediaUploadErrorHandler.class.getName());
    }
}
