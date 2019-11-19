package com.google.api.client.googleapis.batch;

import java.io.IOException;
import com.google.api.client.http.HttpContent;
import java.io.Writer;
import java.util.logging.Logger;
import com.google.api.client.http.HttpHeaders;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.AbstractHttpContent;

class HttpRequestContent extends AbstractHttpContent
{
    static final String NEWLINE = "\r\n";
    private final HttpRequest request;
    private static final String HTTP_VERSION = "HTTP/1.1";
    
    HttpRequestContent(final HttpRequest request) {
        super("application/http");
        this.request = request;
    }
    
    public void writeTo(final OutputStream outputStream) throws IOException {
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, this.getCharset());
        outputStreamWriter.write(this.request.getRequestMethod());
        outputStreamWriter.write(" ");
        outputStreamWriter.write(this.request.getUrl().build());
        outputStreamWriter.write(" ");
        outputStreamWriter.write("HTTP/1.1");
        outputStreamWriter.write("\r\n");
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.fromHttpHeaders(this.request.getHeaders());
        httpHeaders.setAcceptEncoding(null).setUserAgent(null).setContentEncoding(null).setContentType(null).setContentLength(null);
        final HttpContent content = this.request.getContent();
        if (content != null) {
            httpHeaders.setContentType(content.getType());
            final long length = content.getLength();
            if (length != -1L) {
                httpHeaders.setContentLength(length);
            }
        }
        HttpHeaders.serializeHeadersForMultipartRequests(httpHeaders, null, null, outputStreamWriter);
        outputStreamWriter.write("\r\n");
        outputStreamWriter.flush();
        if (content != null) {
            content.writeTo(outputStream);
        }
    }
}
