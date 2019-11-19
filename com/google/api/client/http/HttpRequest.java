package com.google.api.client.http;

import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.Executor;
import java.io.InputStream;
import java.util.logging.Logger;
import java.io.IOException;
import com.google.api.client.util.IOUtils;
import com.google.api.client.util.StreamingContent;
import com.google.api.client.util.LoggingStreamingContent;
import com.google.api.client.util.StringUtils;
import java.util.logging.Level;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Beta;

public final class HttpRequest
{
    public static final String VERSION = "1.25.0";
    public static final String USER_AGENT_SUFFIX = "Google-HTTP-Java-Client/1.25.0 (gzip)";
    public static final int DEFAULT_NUMBER_OF_RETRIES = 10;
    private HttpExecuteInterceptor executeInterceptor;
    private HttpHeaders headers;
    private HttpHeaders responseHeaders;
    private int numRetries;
    private int contentLoggingLimit;
    private boolean loggingEnabled;
    private boolean curlLoggingEnabled;
    private HttpContent content;
    private final HttpTransport transport;
    private String requestMethod;
    private GenericUrl url;
    private int connectTimeout;
    private int readTimeout;
    private HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler;
    @Beta
    private HttpIOExceptionHandler ioExceptionHandler;
    private HttpResponseInterceptor responseInterceptor;
    private ObjectParser objectParser;
    private HttpEncoding encoding;
    @Deprecated
    @Beta
    private BackOffPolicy backOffPolicy;
    private boolean followRedirects;
    private boolean throwExceptionOnExecuteError;
    @Deprecated
    @Beta
    private boolean retryOnExecuteIOException;
    private boolean suppressUserAgentSuffix;
    private Sleeper sleeper;
    
    HttpRequest(final HttpTransport transport, final String requestMethod) {
        super();
        this.headers = new HttpHeaders();
        this.responseHeaders = new HttpHeaders();
        this.numRetries = 10;
        this.contentLoggingLimit = 16384;
        this.loggingEnabled = true;
        this.curlLoggingEnabled = true;
        this.connectTimeout = 20000;
        this.readTimeout = 20000;
        this.followRedirects = true;
        this.throwExceptionOnExecuteError = true;
        this.retryOnExecuteIOException = false;
        this.sleeper = Sleeper.DEFAULT;
        this.transport = transport;
        this.setRequestMethod(requestMethod);
    }
    
    public HttpTransport getTransport() {
        return this.transport;
    }
    
    public String getRequestMethod() {
        return this.requestMethod;
    }
    
    public HttpRequest setRequestMethod(final String requestMethod) {
        Preconditions.checkArgument(requestMethod == null || HttpMediaType.matchesToken(requestMethod));
        this.requestMethod = requestMethod;
        return this;
    }
    
    public GenericUrl getUrl() {
        return this.url;
    }
    
    public HttpRequest setUrl(final GenericUrl genericUrl) {
        this.url = Preconditions.<GenericUrl>checkNotNull(genericUrl);
        return this;
    }
    
    public HttpContent getContent() {
        return this.content;
    }
    
    public HttpRequest setContent(final HttpContent content) {
        this.content = content;
        return this;
    }
    
    public HttpEncoding getEncoding() {
        return this.encoding;
    }
    
    public HttpRequest setEncoding(final HttpEncoding encoding) {
        this.encoding = encoding;
        return this;
    }
    
    @Deprecated
    @Beta
    public BackOffPolicy getBackOffPolicy() {
        return this.backOffPolicy;
    }
    
    @Deprecated
    @Beta
    public HttpRequest setBackOffPolicy(final BackOffPolicy backOffPolicy) {
        this.backOffPolicy = backOffPolicy;
        return this;
    }
    
    public int getContentLoggingLimit() {
        return this.contentLoggingLimit;
    }
    
    public HttpRequest setContentLoggingLimit(final int contentLoggingLimit) {
        Preconditions.checkArgument(contentLoggingLimit >= 0, (Object)"The content logging limit must be non-negative.");
        this.contentLoggingLimit = contentLoggingLimit;
        return this;
    }
    
    public boolean isLoggingEnabled() {
        return this.loggingEnabled;
    }
    
    public HttpRequest setLoggingEnabled(final boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
        return this;
    }
    
    public boolean isCurlLoggingEnabled() {
        return this.curlLoggingEnabled;
    }
    
    public HttpRequest setCurlLoggingEnabled(final boolean curlLoggingEnabled) {
        this.curlLoggingEnabled = curlLoggingEnabled;
        return this;
    }
    
    public int getConnectTimeout() {
        return this.connectTimeout;
    }
    
    public HttpRequest setConnectTimeout(final int connectTimeout) {
        Preconditions.checkArgument(connectTimeout >= 0);
        this.connectTimeout = connectTimeout;
        return this;
    }
    
    public int getReadTimeout() {
        return this.readTimeout;
    }
    
    public HttpRequest setReadTimeout(final int readTimeout) {
        Preconditions.checkArgument(readTimeout >= 0);
        this.readTimeout = readTimeout;
        return this;
    }
    
    public HttpHeaders getHeaders() {
        return this.headers;
    }
    
    public HttpRequest setHeaders(final HttpHeaders httpHeaders) {
        this.headers = Preconditions.<HttpHeaders>checkNotNull(httpHeaders);
        return this;
    }
    
    public HttpHeaders getResponseHeaders() {
        return this.responseHeaders;
    }
    
    public HttpRequest setResponseHeaders(final HttpHeaders httpHeaders) {
        this.responseHeaders = Preconditions.<HttpHeaders>checkNotNull(httpHeaders);
        return this;
    }
    
    public HttpExecuteInterceptor getInterceptor() {
        return this.executeInterceptor;
    }
    
    public HttpRequest setInterceptor(final HttpExecuteInterceptor executeInterceptor) {
        this.executeInterceptor = executeInterceptor;
        return this;
    }
    
    public HttpUnsuccessfulResponseHandler getUnsuccessfulResponseHandler() {
        return this.unsuccessfulResponseHandler;
    }
    
    public HttpRequest setUnsuccessfulResponseHandler(final HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler) {
        this.unsuccessfulResponseHandler = unsuccessfulResponseHandler;
        return this;
    }
    
    @Beta
    public HttpIOExceptionHandler getIOExceptionHandler() {
        return this.ioExceptionHandler;
    }
    
    @Beta
    public HttpRequest setIOExceptionHandler(final HttpIOExceptionHandler ioExceptionHandler) {
        this.ioExceptionHandler = ioExceptionHandler;
        return this;
    }
    
    public HttpResponseInterceptor getResponseInterceptor() {
        return this.responseInterceptor;
    }
    
    public HttpRequest setResponseInterceptor(final HttpResponseInterceptor responseInterceptor) {
        this.responseInterceptor = responseInterceptor;
        return this;
    }
    
    public int getNumberOfRetries() {
        return this.numRetries;
    }
    
    public HttpRequest setNumberOfRetries(final int numRetries) {
        Preconditions.checkArgument(numRetries >= 0);
        this.numRetries = numRetries;
        return this;
    }
    
    public HttpRequest setParser(final ObjectParser objectParser) {
        this.objectParser = objectParser;
        return this;
    }
    
    public final ObjectParser getParser() {
        return this.objectParser;
    }
    
    public boolean getFollowRedirects() {
        return this.followRedirects;
    }
    
    public HttpRequest setFollowRedirects(final boolean followRedirects) {
        this.followRedirects = followRedirects;
        return this;
    }
    
    public boolean getThrowExceptionOnExecuteError() {
        return this.throwExceptionOnExecuteError;
    }
    
    public HttpRequest setThrowExceptionOnExecuteError(final boolean throwExceptionOnExecuteError) {
        this.throwExceptionOnExecuteError = throwExceptionOnExecuteError;
        return this;
    }
    
    @Deprecated
    @Beta
    public boolean getRetryOnExecuteIOException() {
        return this.retryOnExecuteIOException;
    }
    
    @Deprecated
    @Beta
    public HttpRequest setRetryOnExecuteIOException(final boolean retryOnExecuteIOException) {
        this.retryOnExecuteIOException = retryOnExecuteIOException;
        return this;
    }
    
    public boolean getSuppressUserAgentSuffix() {
        return this.suppressUserAgentSuffix;
    }
    
    public HttpRequest setSuppressUserAgentSuffix(final boolean suppressUserAgentSuffix) {
        this.suppressUserAgentSuffix = suppressUserAgentSuffix;
        return this;
    }
    
    public HttpResponse execute() throws IOException {
        Preconditions.checkArgument(this.numRetries >= 0);
        int numRetries = this.numRetries;
        if (this.backOffPolicy != null) {
            this.backOffPolicy.reset();
        }
        final HttpResponse httpResponse = null;
        Preconditions.<String>checkNotNull(this.requestMethod);
        Preconditions.<GenericUrl>checkNotNull(this.url);
        if (httpResponse != null) {
            httpResponse.ignore();
        }
        HttpResponse httpResponse2 = null;
        IOException ex = null;
        if (this.executeInterceptor != null) {
            this.executeInterceptor.intercept(this);
        }
        final String build = this.url.build();
        final LowLevelHttpRequest buildRequest = this.transport.buildRequest(this.requestMethod, build);
        final Logger logger = HttpTransport.LOGGER;
        final boolean b = this.loggingEnabled && logger.isLoggable(Level.CONFIG);
        StringBuilder sb = null;
        StringBuilder sb2 = null;
        if (b) {
            sb = new StringBuilder();
            sb.append("-------------- REQUEST  --------------").append(StringUtils.LINE_SEPARATOR);
            sb.append(this.requestMethod).append(' ').append(build).append(StringUtils.LINE_SEPARATOR);
            if (this.curlLoggingEnabled) {
                sb2 = new StringBuilder("curl -v --compressed");
                if (!this.requestMethod.equals("GET")) {
                    sb2.append(" -X ").append(this.requestMethod);
                }
            }
        }
        final String userAgent = this.headers.getUserAgent();
        if (!this.suppressUserAgentSuffix) {
            if (userAgent == null) {
                this.headers.setUserAgent("Google-HTTP-Java-Client/1.25.0 (gzip)");
            }
            else {
                this.headers.setUserAgent(userAgent + " " + "Google-HTTP-Java-Client/1.25.0 (gzip)");
            }
        }
        HttpHeaders.serializeHeaders(this.headers, sb, sb2, logger, buildRequest);
        if (!this.suppressUserAgentSuffix) {
            this.headers.setUserAgent(userAgent);
        }
        Object content = this.content;
        final boolean b2 = content == null || this.content.retrySupported();
        if (content != null) {
            final String type = this.content.getType();
            if (b) {
                content = new LoggingStreamingContent((StreamingContent)content, HttpTransport.LOGGER, Level.CONFIG, this.contentLoggingLimit);
            }
            String name;
            long length;
            if (this.encoding == null) {
                name = null;
                length = this.content.getLength();
            }
            else {
                name = this.encoding.getName();
                content = new HttpEncodingStreamingContent((StreamingContent)content, this.encoding);
                length = (b2 ? IOUtils.computeLength((StreamingContent)content) : -1L);
            }
            if (b) {
                if (type != null) {
                    final String string = "Content-Type: " + type;
                    sb.append(string).append(StringUtils.LINE_SEPARATOR);
                    if (sb2 != null) {
                        sb2.append(" -H '" + string + "'");
                    }
                }
                if (name != null) {
                    final String string2 = "Content-Encoding: " + name;
                    sb.append(string2).append(StringUtils.LINE_SEPARATOR);
                    if (sb2 != null) {
                        sb2.append(" -H '" + string2 + "'");
                    }
                }
                if (length >= 0L) {
                    sb.append("Content-Length: " + length).append(StringUtils.LINE_SEPARATOR);
                }
            }
            if (sb2 != null) {
                sb2.append(" -d '@-'");
            }
            buildRequest.setContentType(type);
            buildRequest.setContentEncoding(name);
            buildRequest.setContentLength(length);
            buildRequest.setStreamingContent((StreamingContent)content);
        }
        if (b) {
            logger.config(sb.toString());
            if (sb2 != null) {
                sb2.append(" -- '");
                sb2.append(build.replaceAll("'", "'\"'\"'"));
                sb2.append("'");
                if (content != null) {
                    sb2.append(" << $$$");
                }
                logger.config(sb2.toString());
            }
        }
        final boolean b3 = b2 && numRetries > 0;
        buildRequest.setTimeout(this.connectTimeout, this.readTimeout);
        try {
            final LowLevelHttpResponse execute = buildRequest.execute();
            try {
                httpResponse2 = new HttpResponse(this, execute);
            }
            finally {
                final InputStream content2 = execute.getContent();
                if (content2 != null) {
                    content2.close();
                }
            }
        }
        catch (IOException ex2) {
            if (!this.retryOnExecuteIOException && (this.ioExceptionHandler == null || !this.ioExceptionHandler.handleIOException(this, b3))) {
                throw ex2;
            }
            ex = ex2;
            if (b) {
                logger.log(Level.WARNING, "exception thrown while executing request", ex2);
            }
        }
        try {
            if (httpResponse2 != null && !httpResponse2.isSuccessStatusCode()) {
                boolean handleResponse = false;
                if (this.unsuccessfulResponseHandler != null) {
                    handleResponse = this.unsuccessfulResponseHandler.handleResponse(this, httpResponse2, b3);
                }
                if (this.handleRedirect(httpResponse2.getStatusCode(), httpResponse2.getHeaders())) {
                    handleResponse = true;
                }
                else if (b3 && this.backOffPolicy != null && this.backOffPolicy.isBackOffRequired(httpResponse2.getStatusCode())) {
                    final long nextBackOffMillis = this.backOffPolicy.getNextBackOffMillis();
                    if (nextBackOffMillis != -1L) {
                        try {
                            this.sleeper.sleep(nextBackOffMillis);
                        }
                        catch (InterruptedException ex3) {}
                        handleResponse = true;
                    }
                }
                if (b3 & handleResponse) {
                    httpResponse2.ignore();
                }
            }
            else {
                final boolean b4 = b3 & httpResponse2 == null;
            }
            --numRetries;
        }
        finally {
            if (httpResponse2 != null) {
                httpResponse2.disconnect();
            }
        }
        if (httpResponse2 == null) {
            throw ex;
        }
        if (this.responseInterceptor != null) {
            this.responseInterceptor.interceptResponse(httpResponse2);
        }
        if (this.throwExceptionOnExecuteError && !httpResponse2.isSuccessStatusCode()) {
            try {
                throw new HttpResponseException(httpResponse2);
            }
            finally {
                httpResponse2.disconnect();
            }
        }
        return httpResponse2;
    }
    
    @Beta
    public Future<HttpResponse> executeAsync(final Executor executor) {
        final FutureTask<HttpResponse> futureTask = new FutureTask<HttpResponse>(new Callable<HttpResponse>() {
            final /* synthetic */ HttpRequest this$0;
            
            HttpRequest$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public HttpResponse call() throws Exception {
                return this.this$0.execute();
            }
            
            @Override
            public /* bridge */ Object call() throws Exception {
                return this.call();
            }
        });
        executor.execute(futureTask);
        return futureTask;
    }
    
    @Beta
    public Future<HttpResponse> executeAsync() {
        return this.executeAsync(Executors.newSingleThreadExecutor());
    }
    
    public boolean handleRedirect(final int n, final HttpHeaders httpHeaders) {
        final String location = httpHeaders.getLocation();
        if (this.getFollowRedirects() && HttpStatusCodes.isRedirect(n) && location != null) {
            this.setUrl(new GenericUrl(this.url.toURL(location)));
            if (n == 303) {
                this.setRequestMethod("GET");
                this.setContent(null);
            }
            this.headers.setAuthorization((String)null);
            this.headers.setIfMatch(null);
            this.headers.setIfNoneMatch(null);
            this.headers.setIfModifiedSince(null);
            this.headers.setIfUnmodifiedSince(null);
            this.headers.setIfRange(null);
            return true;
        }
        return false;
    }
    
    public Sleeper getSleeper() {
        return this.sleeper;
    }
    
    public HttpRequest setSleeper(final Sleeper sleeper) {
        this.sleeper = Preconditions.<Sleeper>checkNotNull(sleeper);
        return this;
    }
}
