package com.google.api.client.http;

import com.google.api.client.util.Charsets;
import java.nio.charset.Charset;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import com.google.api.client.util.IOUtils;
import java.io.OutputStream;
import java.io.EOFException;
import com.google.api.client.util.LoggingInputStream;
import java.util.zip.GZIPInputStream;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.logging.Logger;
import com.google.api.client.util.StringUtils;
import java.util.logging.Level;
import java.io.InputStream;

public final class HttpResponse
{
    private InputStream content;
    private final String contentEncoding;
    private final String contentType;
    private final HttpMediaType mediaType;
    LowLevelHttpResponse response;
    private final int statusCode;
    private final String statusMessage;
    private final HttpRequest request;
    private int contentLoggingLimit;
    private boolean loggingEnabled;
    private boolean contentRead;
    
    HttpResponse(final HttpRequest request, final LowLevelHttpResponse response) throws IOException {
        super();
        this.request = request;
        this.contentLoggingLimit = request.getContentLoggingLimit();
        this.loggingEnabled = request.isLoggingEnabled();
        this.response = response;
        this.contentEncoding = response.getContentEncoding();
        final int statusCode = response.getStatusCode();
        this.statusCode = ((statusCode < 0) ? 0 : statusCode);
        final String reasonPhrase = response.getReasonPhrase();
        this.statusMessage = reasonPhrase;
        final Logger logger = HttpTransport.LOGGER;
        final boolean b = this.loggingEnabled && logger.isLoggable(Level.CONFIG);
        StringBuilder sb = null;
        if (b) {
            sb = new StringBuilder();
            sb.append("-------------- RESPONSE --------------").append(StringUtils.LINE_SEPARATOR);
            final String statusLine = response.getStatusLine();
            if (statusLine != null) {
                sb.append(statusLine);
            }
            else {
                sb.append(this.statusCode);
                if (reasonPhrase != null) {
                    sb.append(' ').append(reasonPhrase);
                }
            }
            sb.append(StringUtils.LINE_SEPARATOR);
        }
        request.getResponseHeaders().fromHttpResponse(response, b ? sb : null);
        String contentType = response.getContentType();
        if (contentType == null) {
            contentType = request.getResponseHeaders().getContentType();
        }
        this.mediaType = (((this.contentType = contentType) == null) ? null : new HttpMediaType(contentType));
        if (b) {
            logger.config(sb.toString());
        }
    }
    
    public int getContentLoggingLimit() {
        return this.contentLoggingLimit;
    }
    
    public HttpResponse setContentLoggingLimit(final int contentLoggingLimit) {
        Preconditions.checkArgument(contentLoggingLimit >= 0, (Object)"The content logging limit must be non-negative.");
        this.contentLoggingLimit = contentLoggingLimit;
        return this;
    }
    
    public boolean isLoggingEnabled() {
        return this.loggingEnabled;
    }
    
    public HttpResponse setLoggingEnabled(final boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
        return this;
    }
    
    public String getContentEncoding() {
        return this.contentEncoding;
    }
    
    public String getContentType() {
        return this.contentType;
    }
    
    public HttpMediaType getMediaType() {
        return this.mediaType;
    }
    
    public HttpHeaders getHeaders() {
        return this.request.getResponseHeaders();
    }
    
    public boolean isSuccessStatusCode() {
        return HttpStatusCodes.isSuccess(this.statusCode);
    }
    
    public int getStatusCode() {
        return this.statusCode;
    }
    
    public String getStatusMessage() {
        return this.statusMessage;
    }
    
    public HttpTransport getTransport() {
        return this.request.getTransport();
    }
    
    public HttpRequest getRequest() {
        return this.request;
    }
    
    public InputStream getContent() throws IOException {
        if (!this.contentRead) {
            InputStream content = this.response.getContent();
            if (content != null) {
                try {
                    final String contentEncoding = this.contentEncoding;
                    if (contentEncoding != null && contentEncoding.contains("gzip")) {
                        content = new GZIPInputStream(content);
                    }
                    final Logger logger = HttpTransport.LOGGER;
                    if (this.loggingEnabled && logger.isLoggable(Level.CONFIG)) {
                        content = new LoggingInputStream(content, logger, Level.CONFIG, this.contentLoggingLimit);
                    }
                    this.content = content;
                }
                catch (EOFException ex) {}
                finally {
                    content.close();
                }
            }
            this.contentRead = true;
        }
        return this.content;
    }
    
    public void download(final OutputStream outputStream) throws IOException {
        IOUtils.copy(this.getContent(), outputStream);
    }
    
    public void ignore() throws IOException {
        final InputStream content = this.getContent();
        if (content != null) {
            content.close();
        }
    }
    
    public void disconnect() throws IOException {
        this.ignore();
        this.response.disconnect();
    }
    
    public <T> T parseAs(final Class<T> clazz) throws IOException {
        if (!this.hasMessageBody()) {
            return null;
        }
        return this.request.getParser().<T>parseAndClose(this.getContent(), this.getContentCharset(), clazz);
    }
    
    private boolean hasMessageBody() throws IOException {
        final int statusCode = this.getStatusCode();
        if (this.getRequest().getRequestMethod().equals("HEAD") || statusCode / 100 == 1 || statusCode == 204 || statusCode == 304) {
            this.ignore();
            return false;
        }
        return true;
    }
    
    public Object parseAs(final Type type) throws IOException {
        if (!this.hasMessageBody()) {
            return null;
        }
        return this.request.getParser().parseAndClose(this.getContent(), this.getContentCharset(), type);
    }
    
    public String parseAsString() throws IOException {
        final InputStream content = this.getContent();
        if (content == null) {
            return "";
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IOUtils.copy(content, byteArrayOutputStream);
        return byteArrayOutputStream.toString(this.getContentCharset().name());
    }
    
    public Charset getContentCharset() {
        return (this.mediaType == null || this.mediaType.getCharsetParameter() == null) ? Charsets.ISO_8859_1 : this.mediaType.getCharsetParameter();
    }
}
