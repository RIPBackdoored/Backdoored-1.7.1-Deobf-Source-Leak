package com.google.api.client.googleapis.batch;

import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.HttpTransport;
import java.io.ByteArrayInputStream;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.BackOffPolicy;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.HttpResponse;
import java.io.FilterInputStream;
import com.google.api.client.util.ByteStreams;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStream;
import java.util.List;

final class BatchUnparsedResponse
{
    private final String boundary;
    private final List<BatchRequest.RequestInfo<?, ?>> requestInfos;
    private final InputStream inputStream;
    boolean hasNext;
    List<BatchRequest.RequestInfo<?, ?>> unsuccessfulRequestInfos;
    boolean backOffRequired;
    private int contentId;
    private final boolean retryAllowed;
    
    BatchUnparsedResponse(final InputStream inputStream, final String boundary, final List<BatchRequest.RequestInfo<?, ?>> requestInfos, final boolean retryAllowed) throws IOException {
        super();
        this.hasNext = true;
        this.unsuccessfulRequestInfos = new ArrayList<BatchRequest.RequestInfo<?, ?>>();
        this.contentId = 0;
        this.boundary = boundary;
        this.requestInfos = requestInfos;
        this.retryAllowed = retryAllowed;
        this.inputStream = inputStream;
        this.checkForFinalBoundary(this.readLine());
    }
    
    void parseNextResponse() throws IOException {
        ++this.contentId;
        String line;
        while ((line = this.readLine()) != null && !line.equals("")) {}
        final int int1 = Integer.parseInt(this.readLine().split(" ")[1]);
        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<String> list2 = new ArrayList<String>();
        long long1 = -1L;
        String s;
        while ((s = this.readLine()) != null && !s.equals("")) {
            final String[] split = s.split(": ", 2);
            final String s2 = split[0];
            final String s3 = split[1];
            list.add(s2);
            list2.add(s3);
            if ("Content-Length".equalsIgnoreCase(s2.trim())) {
                long1 = Long.parseLong(s3);
            }
        }
        InputStream trimCrlf;
        if (long1 == -1L) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            String rawLine;
            while ((rawLine = this.readRawLine()) != null && !rawLine.startsWith(this.boundary)) {
                byteArrayOutputStream.write(rawLine.getBytes("ISO-8859-1"));
            }
            trimCrlf = trimCrlf(byteArrayOutputStream.toByteArray());
            s = trimCrlf(rawLine);
        }
        else {
            trimCrlf = new FilterInputStream(ByteStreams.limit(this.inputStream, long1)) {
                final /* synthetic */ BatchUnparsedResponse this$0;
                
                BatchUnparsedResponse$1(final InputStream inputStream) {
                    this.this$0 = this$0;
                    super(inputStream);
                }
                
                @Override
                public void close() {
                }
            };
        }
        this.<?, ?>parseAndCallback(this.requestInfos.get(this.contentId - 1), int1, this.getFakeResponse(int1, trimCrlf, list, list2));
        while (true) {
            if (trimCrlf.skip(long1) <= 0L) {
                if (trimCrlf.read() != -1) {
                    continue;
                }
                break;
            }
        }
        if (long1 != -1L) {
            s = this.readLine();
        }
        while (s != null && s.length() == 0) {
            s = this.readLine();
        }
        this.checkForFinalBoundary(s);
    }
    
    private <T, E> void parseAndCallback(final BatchRequest.RequestInfo<T, E> requestInfo, final int n, final HttpResponse httpResponse) throws IOException {
        final BatchCallback<T, E> callback = requestInfo.callback;
        final HttpHeaders headers = httpResponse.getHeaders();
        final HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler = requestInfo.request.getUnsuccessfulResponseHandler();
        final BackOffPolicy backOffPolicy = requestInfo.request.getBackOffPolicy();
        this.backOffRequired = false;
        if (HttpStatusCodes.isSuccess(n)) {
            if (callback == null) {
                return;
            }
            callback.onSuccess(this.<T, T, E>getParsedDataClass(requestInfo.dataClass, httpResponse, requestInfo), headers);
        }
        else {
            final HttpContent content = requestInfo.request.getContent();
            final boolean b = this.retryAllowed && (content == null || content.retrySupported());
            boolean b2 = false;
            if (unsuccessfulResponseHandler != null) {
                unsuccessfulResponseHandler.handleResponse(requestInfo.request, httpResponse, b);
            }
            if (requestInfo.request.handleRedirect(httpResponse.getStatusCode(), httpResponse.getHeaders())) {
                b2 = true;
            }
            else if (b && backOffPolicy != null && backOffPolicy.isBackOffRequired(httpResponse.getStatusCode())) {
                this.backOffRequired = true;
            }
            if (b && (this.backOffRequired || b2)) {
                this.unsuccessfulRequestInfos.add(requestInfo);
            }
            else {
                if (callback == null) {
                    return;
                }
                callback.onFailure(this.<E, T, E>getParsedDataClass(requestInfo.errorClass, httpResponse, requestInfo), headers);
            }
        }
    }
    
    private <A, T, E> A getParsedDataClass(final Class<A> clazz, final HttpResponse httpResponse, final BatchRequest.RequestInfo<T, E> requestInfo) throws IOException {
        if (clazz == Void.class) {
            return null;
        }
        return requestInfo.request.getParser().<A>parseAndClose(httpResponse.getContent(), httpResponse.getContentCharset(), clazz);
    }
    
    private HttpResponse getFakeResponse(final int n, final InputStream inputStream, final List<String> list, final List<String> list2) throws IOException {
        final HttpRequest buildPostRequest = new FakeResponseHttpTransport(n, inputStream, list, list2).createRequestFactory().buildPostRequest(new GenericUrl("http://google.com/"), null);
        buildPostRequest.setLoggingEnabled(false);
        buildPostRequest.setThrowExceptionOnExecuteError(false);
        return buildPostRequest.execute();
    }
    
    private String readRawLine() throws IOException {
        int i = this.inputStream.read();
        if (i == -1) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        while (i != -1) {
            sb.append((char)i);
            if (i == 10) {
                break;
            }
            i = this.inputStream.read();
        }
        return sb.toString();
    }
    
    private String readLine() throws IOException {
        return trimCrlf(this.readRawLine());
    }
    
    private static String trimCrlf(final String s) {
        if (s.endsWith("\r\n")) {
            return s.substring(0, s.length() - 2);
        }
        if (s.endsWith("\n")) {
            return s.substring(0, s.length() - 1);
        }
        return s;
    }
    
    private static InputStream trimCrlf(final byte[] array) {
        int length = array.length;
        if (length > 0 && array[length - 1] == 10) {
            --length;
        }
        if (length > 0 && array[length - 1] == 13) {
            --length;
        }
        return new ByteArrayInputStream(array, 0, length);
    }
    
    private void checkForFinalBoundary(final String s) throws IOException {
        if (s.equals(String.valueOf(this.boundary).concat("--"))) {
            this.hasNext = false;
            this.inputStream.close();
        }
    }
}
