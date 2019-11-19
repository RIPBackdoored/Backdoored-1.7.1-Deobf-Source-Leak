package com.google.api.client.http;

import com.google.api.client.util.FieldInfo;
import java.util.HashMap;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import java.util.Map;
import com.google.api.client.util.Data;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.OutputStream;

public class UrlEncodedContent extends AbstractHttpContent
{
    private Object data;
    
    public UrlEncodedContent(final Object data) {
        super(UrlEncodedParser.MEDIA_TYPE);
        this.setData(data);
    }
    
    @Override
    public void writeTo(final OutputStream outputStream) throws IOException {
        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, this.getCharset()));
        boolean b = true;
        for (final Map.Entry<String, Object> entry : Data.mapOf(this.data).entrySet()) {
            final Object value = entry.getValue();
            if (value != null) {
                final String escapeUri = CharEscapers.escapeUri(entry.getKey());
                final Class<?> class1 = value.getClass();
                if (value instanceof Iterable || class1.isArray()) {
                    final Iterator<Object> iterator2 = Types.<Object>iterableOf(value).iterator();
                    while (iterator2.hasNext()) {
                        b = appendParam(b, bufferedWriter, escapeUri, iterator2.next());
                    }
                }
                else {
                    b = appendParam(b, bufferedWriter, escapeUri, value);
                }
            }
        }
        bufferedWriter.flush();
    }
    
    @Override
    public UrlEncodedContent setMediaType(final HttpMediaType mediaType) {
        super.setMediaType(mediaType);
        return this;
    }
    
    public final Object getData() {
        return this.data;
    }
    
    public UrlEncodedContent setData(final Object o) {
        this.data = Preconditions.<Object>checkNotNull(o);
        return this;
    }
    
    public static UrlEncodedContent getContent(final HttpRequest httpRequest) {
        final HttpContent content = httpRequest.getContent();
        if (content != null) {
            return (UrlEncodedContent)content;
        }
        final UrlEncodedContent content2 = new UrlEncodedContent(new HashMap());
        httpRequest.setContent(content2);
        return content2;
    }
    
    private static boolean appendParam(boolean b, final Writer writer, final String s, final Object o) throws IOException {
        if (o == null || Data.isNull(o)) {
            return b;
        }
        if (b) {
            b = false;
        }
        else {
            writer.write("&");
        }
        writer.write(s);
        final String escapeUri = CharEscapers.escapeUri((o instanceof Enum) ? FieldInfo.of((Enum<?>)o).getName() : o.toString());
        if (escapeUri.length() != 0) {
            writer.write("=");
            writer.write(escapeUri);
        }
        return b;
    }
    
    @Override
    public /* bridge */ AbstractHttpContent setMediaType(final HttpMediaType mediaType) {
        return this.setMediaType(mediaType);
    }
}
