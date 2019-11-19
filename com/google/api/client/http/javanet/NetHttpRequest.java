package com.google.api.client.http.javanet;

import java.io.OutputStream;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import com.google.api.client.http.LowLevelHttpResponse;
import java.net.HttpURLConnection;
import com.google.api.client.http.LowLevelHttpRequest;

final class NetHttpRequest extends LowLevelHttpRequest
{
    private final HttpURLConnection connection;
    
    NetHttpRequest(final HttpURLConnection connection) {
        super();
        (this.connection = connection).setInstanceFollowRedirects(false);
    }
    
    @Override
    public void addHeader(final String s, final String s2) {
        this.connection.addRequestProperty(s, s2);
    }
    
    @Override
    public void setTimeout(final int connectTimeout, final int readTimeout) {
        this.connection.setReadTimeout(readTimeout);
        this.connection.setConnectTimeout(connectTimeout);
    }
    
    @Override
    public LowLevelHttpResponse execute() throws IOException {
        final HttpURLConnection connection = this.connection;
        if (this.getStreamingContent() != null) {
            final String contentType = this.getContentType();
            if (contentType != null) {
                this.addHeader("Content-Type", contentType);
            }
            final String contentEncoding = this.getContentEncoding();
            if (contentEncoding != null) {
                this.addHeader("Content-Encoding", contentEncoding);
            }
            final long contentLength = this.getContentLength();
            if (contentLength >= 0L) {
                connection.setRequestProperty("Content-Length", Long.toString(contentLength));
            }
            final String requestMethod = connection.getRequestMethod();
            if ("POST".equals(requestMethod) || "PUT".equals(requestMethod)) {
                connection.setDoOutput(true);
                if (contentLength >= 0L && contentLength <= 0L) {
                    connection.setFixedLengthStreamingMode((int)contentLength);
                }
                else {
                    connection.setChunkedStreamingMode(0);
                }
                final OutputStream outputStream = connection.getOutputStream();
                try {
                    this.getStreamingContent().writeTo(outputStream);
                }
                finally {
                    try {
                        outputStream.close();
                    }
                    catch (IOException ex) {
                        throw ex;
                    }
                }
            }
            else {
                Preconditions.checkArgument(contentLength == 0L, "%s with non-zero content length is not supported", requestMethod);
            }
        }
        try {
            connection.connect();
            return new NetHttpResponse(connection);
        }
        finally {
            connection.disconnect();
        }
    }
}
