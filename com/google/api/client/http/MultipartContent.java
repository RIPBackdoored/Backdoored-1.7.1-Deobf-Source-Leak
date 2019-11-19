package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import java.util.Collections;
import java.util.Collection;
import java.io.IOException;
import java.util.Iterator;
import java.io.Writer;
import java.util.logging.Logger;
import com.google.api.client.util.StreamingContent;
import java.util.Arrays;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.util.ArrayList;

public class MultipartContent extends AbstractHttpContent
{
    static final String NEWLINE = "\r\n";
    private static final String TWO_DASHES = "--";
    private ArrayList<Part> parts;
    
    public MultipartContent() {
        super(new HttpMediaType("multipart/related").setParameter("boundary", "__END_OF_PART__"));
        this.parts = new ArrayList<Part>();
    }
    
    @Override
    public void writeTo(final OutputStream outputStream) throws IOException {
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, this.getCharset());
        final String boundary = this.getBoundary();
        for (final Part part : this.parts) {
            final HttpHeaders setAcceptEncoding = new HttpHeaders().setAcceptEncoding(null);
            if (part.headers != null) {
                setAcceptEncoding.fromHttpHeaders(part.headers);
            }
            setAcceptEncoding.setContentEncoding(null).setUserAgent(null).setContentType(null).setContentLength(null).set("Content-Transfer-Encoding", null);
            final HttpContent content = part.content;
            StreamingContent streamingContent = null;
            if (content != null) {
                setAcceptEncoding.set("Content-Transfer-Encoding", Arrays.<String>asList("binary"));
                setAcceptEncoding.setContentType(content.getType());
                final HttpEncoding encoding = part.encoding;
                long n;
                if (encoding == null) {
                    n = content.getLength();
                    streamingContent = content;
                }
                else {
                    setAcceptEncoding.setContentEncoding(encoding.getName());
                    streamingContent = new HttpEncodingStreamingContent(content, encoding);
                    n = AbstractHttpContent.computeLength(content);
                }
                if (n != -1L) {
                    setAcceptEncoding.setContentLength(n);
                }
            }
            outputStreamWriter.write("--");
            outputStreamWriter.write(boundary);
            outputStreamWriter.write("\r\n");
            HttpHeaders.serializeHeadersForMultipartRequests(setAcceptEncoding, null, null, outputStreamWriter);
            if (streamingContent != null) {
                outputStreamWriter.write("\r\n");
                outputStreamWriter.flush();
                streamingContent.writeTo(outputStream);
            }
            outputStreamWriter.write("\r\n");
        }
        outputStreamWriter.write("--");
        outputStreamWriter.write(boundary);
        outputStreamWriter.write("--");
        outputStreamWriter.write("\r\n");
        outputStreamWriter.flush();
    }
    
    @Override
    public boolean retrySupported() {
        final Iterator<Part> iterator = this.parts.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().content.retrySupported()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public MultipartContent setMediaType(final HttpMediaType mediaType) {
        super.setMediaType(mediaType);
        return this;
    }
    
    public final Collection<Part> getParts() {
        return Collections.<Part>unmodifiableCollection((Collection<? extends Part>)this.parts);
    }
    
    public MultipartContent addPart(final Part part) {
        this.parts.add(Preconditions.<Part>checkNotNull(part));
        return this;
    }
    
    public MultipartContent setParts(final Collection<Part> collection) {
        this.parts = new ArrayList<Part>(collection);
        return this;
    }
    
    public MultipartContent setContentParts(final Collection<? extends HttpContent> collection) {
        this.parts = new ArrayList<Part>(collection.size());
        final Iterator<HttpContent> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.addPart(new Part(iterator.next()));
        }
        return this;
    }
    
    public final String getBoundary() {
        return this.getMediaType().getParameter("boundary");
    }
    
    public MultipartContent setBoundary(final String s) {
        this.getMediaType().setParameter("boundary", Preconditions.<String>checkNotNull(s));
        return this;
    }
    
    @Override
    public /* bridge */ AbstractHttpContent setMediaType(final HttpMediaType mediaType) {
        return this.setMediaType(mediaType);
    }
}
