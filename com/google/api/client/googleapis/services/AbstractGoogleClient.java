package com.google.api.client.googleapis.services;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Preconditions;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.googleapis.batch.BatchRequest;
import java.io.IOException;
import com.google.api.client.util.Strings;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.http.HttpRequestFactory;
import java.util.logging.Logger;

public abstract class AbstractGoogleClient
{
    static final Logger logger;
    private final HttpRequestFactory requestFactory;
    private final GoogleClientRequestInitializer googleClientRequestInitializer;
    private final String rootUrl;
    private final String servicePath;
    private final String batchPath;
    private final String applicationName;
    private final ObjectParser objectParser;
    private final boolean suppressPatternChecks;
    private final boolean suppressRequiredParameterChecks;
    
    protected AbstractGoogleClient(final Builder builder) {
        super();
        this.googleClientRequestInitializer = builder.googleClientRequestInitializer;
        this.rootUrl = normalizeRootUrl(builder.rootUrl);
        this.servicePath = normalizeServicePath(builder.servicePath);
        this.batchPath = builder.batchPath;
        if (Strings.isNullOrEmpty(builder.applicationName)) {
            AbstractGoogleClient.logger.warning("Application name is not set. Call Builder#setApplicationName.");
        }
        this.applicationName = builder.applicationName;
        this.requestFactory = ((builder.httpRequestInitializer == null) ? builder.transport.createRequestFactory() : builder.transport.createRequestFactory(builder.httpRequestInitializer));
        this.objectParser = builder.objectParser;
        this.suppressPatternChecks = builder.suppressPatternChecks;
        this.suppressRequiredParameterChecks = builder.suppressRequiredParameterChecks;
    }
    
    public final String getRootUrl() {
        return this.rootUrl;
    }
    
    public final String getServicePath() {
        return this.servicePath;
    }
    
    public final String getBaseUrl() {
        final String value = String.valueOf(this.rootUrl);
        final String value2 = String.valueOf(this.servicePath);
        return (value2.length() != 0) ? value.concat(value2) : new String(value);
    }
    
    public final String getApplicationName() {
        return this.applicationName;
    }
    
    public final HttpRequestFactory getRequestFactory() {
        return this.requestFactory;
    }
    
    public final GoogleClientRequestInitializer getGoogleClientRequestInitializer() {
        return this.googleClientRequestInitializer;
    }
    
    public ObjectParser getObjectParser() {
        return this.objectParser;
    }
    
    protected void initialize(final AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
        if (this.getGoogleClientRequestInitializer() != null) {
            this.getGoogleClientRequestInitializer().initialize(abstractGoogleClientRequest);
        }
    }
    
    public final BatchRequest batch() {
        return this.batch(null);
    }
    
    public final BatchRequest batch(final HttpRequestInitializer httpRequestInitializer) {
        final BatchRequest batchRequest = new BatchRequest(this.getRequestFactory().getTransport(), httpRequestInitializer);
        final String value = String.valueOf(this.getRootUrl());
        final String value2 = String.valueOf(this.batchPath);
        batchRequest.setBatchUrl(new GenericUrl((value2.length() != 0) ? value.concat(value2) : new String(value)));
        return batchRequest;
    }
    
    public final boolean getSuppressPatternChecks() {
        return this.suppressPatternChecks;
    }
    
    public final boolean getSuppressRequiredParameterChecks() {
        return this.suppressRequiredParameterChecks;
    }
    
    static String normalizeRootUrl(String concat) {
        Preconditions.<String>checkNotNull(concat, (Object)"root URL cannot be null.");
        if (!concat.endsWith("/")) {
            concat = String.valueOf(concat).concat("/");
        }
        return concat;
    }
    
    static String normalizeServicePath(String s) {
        Preconditions.<String>checkNotNull(s, (Object)"service path cannot be null");
        if (s.length() == 1) {
            Preconditions.checkArgument("/".equals(s), (Object)"service path must equal \"/\" if it is of length 1.");
            s = "";
        }
        else if (s.length() > 0) {
            if (!s.endsWith("/")) {
                s = String.valueOf(s).concat("/");
            }
            if (s.startsWith("/")) {
                s = s.substring(1);
            }
        }
        return s;
    }
    
    static {
        logger = Logger.getLogger(AbstractGoogleClient.class.getName());
    }
}
