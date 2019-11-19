package com.google.cloud.storage;

import com.google.common.collect.ImmutableList;

public static final class Builder
{
    private Integer maxAgeSeconds;
    private ImmutableList<HttpMethod> methods;
    private ImmutableList<Origin> origins;
    private ImmutableList<String> responseHeaders;
    
    private Builder() {
        super();
    }
    
    public Builder setMaxAgeSeconds(final Integer maxAgeSeconds) {
        this.maxAgeSeconds = maxAgeSeconds;
        return this;
    }
    
    public Builder setMethods(final Iterable<HttpMethod> elements) {
        this.methods = ((elements != null) ? ImmutableList.<HttpMethod>copyOf((Iterable<? extends HttpMethod>)elements) : null);
        return this;
    }
    
    public Builder setOrigins(final Iterable<Origin> elements) {
        this.origins = ((elements != null) ? ImmutableList.<Origin>copyOf((Iterable<? extends Origin>)elements) : null);
        return this;
    }
    
    public Builder setResponseHeaders(final Iterable<String> elements) {
        this.responseHeaders = ((elements != null) ? ImmutableList.<String>copyOf((Iterable<? extends String>)elements) : null);
        return this;
    }
    
    public Cors build() {
        return new Cors(this, null);
    }
    
    static /* synthetic */ Integer access$100(final Builder builder) {
        return builder.maxAgeSeconds;
    }
    
    static /* synthetic */ ImmutableList access$200(final Builder builder) {
        return builder.methods;
    }
    
    static /* synthetic */ ImmutableList access$300(final Builder builder) {
        return builder.origins;
    }
    
    static /* synthetic */ ImmutableList access$400(final Builder builder) {
        return builder.responseHeaders;
    }
    
    Builder(final Cors$1 function) {
        this();
    }
}
