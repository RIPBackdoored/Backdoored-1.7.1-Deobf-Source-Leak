package com.google.api.client.testing.http;

import com.google.api.client.util.Charsets;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.util.IOUtils;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import com.google.api.client.http.LowLevelHttpResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.api.client.util.Beta;
import com.google.api.client.http.LowLevelHttpRequest;

@Beta
public class MockLowLevelHttpRequest extends LowLevelHttpRequest
{
    private String url;
    private final Map<String, List<String>> headersMap;
    private MockLowLevelHttpResponse response;
    
    public MockLowLevelHttpRequest() {
        super();
        this.headersMap = new HashMap<String, List<String>>();
        this.response = new MockLowLevelHttpResponse();
    }
    
    public MockLowLevelHttpRequest(final String url) {
        super();
        this.headersMap = new HashMap<String, List<String>>();
        this.response = new MockLowLevelHttpResponse();
        this.url = url;
    }
    
    @Override
    public void addHeader(String lowerCase, final String s) throws IOException {
        lowerCase = lowerCase.toLowerCase(Locale.US);
        List<String> list = this.headersMap.get(lowerCase);
        if (list == null) {
            list = new ArrayList<String>();
            this.headersMap.put(lowerCase, list);
        }
        list.add(s);
    }
    
    @Override
    public LowLevelHttpResponse execute() throws IOException {
        return this.response;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Map<String, List<String>> getHeaders() {
        return Collections.<String, List<String>>unmodifiableMap((Map<? extends String, ? extends List<String>>)this.headersMap);
    }
    
    public String getFirstHeaderValue(final String s) {
        final List<String> list = this.headersMap.get(s.toLowerCase(Locale.US));
        return (list == null) ? null : list.get(0);
    }
    
    public List<String> getHeaderValues(final String s) {
        final List<String> list = this.headersMap.get(s.toLowerCase(Locale.US));
        return (List<String>)((list == null) ? Collections.<Object>emptyList() : Collections.<Object>unmodifiableList((List<?>)list));
    }
    
    public MockLowLevelHttpRequest setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public String getContentAsString() throws IOException {
        if (this.getStreamingContent() == null) {
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.getStreamingContent().writeTo(byteArrayOutputStream);
        final String contentEncoding = this.getContentEncoding();
        if (contentEncoding != null && contentEncoding.contains("gzip")) {
            final GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            byteArrayOutputStream = new ByteArrayOutputStream();
            IOUtils.copy(gzipInputStream, byteArrayOutputStream);
        }
        final String contentType = this.getContentType();
        final HttpMediaType httpMediaType = (contentType != null) ? new HttpMediaType(contentType) : null;
        return byteArrayOutputStream.toString(((httpMediaType == null || httpMediaType.getCharsetParameter() == null) ? Charsets.ISO_8859_1 : httpMediaType.getCharsetParameter()).name());
    }
    
    public MockLowLevelHttpResponse getResponse() {
        return this.response;
    }
    
    public MockLowLevelHttpRequest setResponse(final MockLowLevelHttpResponse response) {
        this.response = response;
        return this;
    }
}
