package com.google.cloud.storage;

import java.net.URISyntaxException;
import java.net.URI;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Iterables;
import com.google.common.base.Functions;
import java.util.Objects;
import java.util.List;
import com.google.common.collect.ImmutableList;
import com.google.api.services.storage.model.Bucket;
import com.google.common.base.Function;
import java.io.Serializable;

public final class Cors implements Serializable
{
    private static final long serialVersionUID = -8637770919343335655L;
    static final Function<Bucket.Cors, Cors> FROM_PB_FUNCTION;
    static final Function<Cors, Bucket.Cors> TO_PB_FUNCTION;
    private final Integer maxAgeSeconds;
    private final ImmutableList<HttpMethod> methods;
    private final ImmutableList<Origin> origins;
    private final ImmutableList<String> responseHeaders;
    
    private Cors(final Builder builder) {
        super();
        this.maxAgeSeconds = builder.maxAgeSeconds;
        this.methods = builder.methods;
        this.origins = builder.origins;
        this.responseHeaders = builder.responseHeaders;
    }
    
    public Integer getMaxAgeSeconds() {
        return this.maxAgeSeconds;
    }
    
    public List<HttpMethod> getMethods() {
        return this.methods;
    }
    
    public List<Origin> getOrigins() {
        return this.origins;
    }
    
    public List<String> getResponseHeaders() {
        return this.responseHeaders;
    }
    
    public Builder toBuilder() {
        return newBuilder().setMaxAgeSeconds(this.maxAgeSeconds).setMethods(this.methods).setOrigins(this.origins).setResponseHeaders(this.responseHeaders);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.maxAgeSeconds, this.methods, this.origins, this.responseHeaders);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Cors)) {
            return false;
        }
        final Cors cors = (Cors)o;
        return Objects.equals(this.maxAgeSeconds, cors.maxAgeSeconds) && Objects.equals(this.methods, cors.methods) && Objects.equals(this.origins, cors.origins) && Objects.equals(this.responseHeaders, cors.responseHeaders);
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    Bucket.Cors toPb() {
        final Bucket.Cors cors = new Bucket.Cors();
        cors.setMaxAgeSeconds(this.maxAgeSeconds);
        cors.setResponseHeader((List)this.responseHeaders);
        if (this.methods != null) {
            cors.setMethod((List)Lists.<Object>newArrayList(Iterables.<HttpMethod, ?>transform((Iterable<HttpMethod>)this.methods, (Function<? super HttpMethod, ?>)Functions.toStringFunction())));
        }
        if (this.origins != null) {
            cors.setOrigin((List)Lists.<Object>newArrayList(Iterables.<Origin, ?>transform((Iterable<Origin>)this.origins, (Function<? super Origin, ?>)Functions.toStringFunction())));
        }
        return cors;
    }
    
    static Cors fromPb(final Bucket.Cors cors) {
        final Builder setMaxAgeSeconds = newBuilder().setMaxAgeSeconds(cors.getMaxAgeSeconds());
        if (cors.getMethod() != null) {
            setMaxAgeSeconds.setMethods(Iterables.<Object, HttpMethod>transform((Iterable<Object>)cors.getMethod(), (Function<? super Object, ? extends HttpMethod>)new Function<String, HttpMethod>() {
                Cors$3() {
                    super();
                }
                
                @Override
                public HttpMethod apply(final String s) {
                    return HttpMethod.valueOf(s.toUpperCase());
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((String)o);
                }
            }));
        }
        if (cors.getOrigin() != null) {
            setMaxAgeSeconds.setOrigins(Iterables.<Object, Origin>transform((Iterable<Object>)cors.getOrigin(), (Function<? super Object, ? extends Origin>)new Function<String, Origin>() {
                Cors$4() {
                    super();
                }
                
                @Override
                public Origin apply(final String s) {
                    return Origin.of(s);
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((String)o);
                }
            }));
        }
        setMaxAgeSeconds.setResponseHeaders(cors.getResponseHeader());
        return setMaxAgeSeconds.build();
    }
    
    Cors(final Builder builder, final Cors$1 function) {
        this(builder);
    }
    
    static {
        FROM_PB_FUNCTION = new Function<Bucket.Cors, Cors>() {
            Cors$1() {
                super();
            }
            
            @Override
            public Cors apply(final Bucket.Cors cors) {
                return Cors.fromPb(cors);
            }
            
            @Override
            public /* bridge */ Object apply(final Object o) {
                return this.apply((Bucket.Cors)o);
            }
        };
        TO_PB_FUNCTION = new Function<Cors, Bucket.Cors>() {
            Cors$2() {
                super();
            }
            
            @Override
            public Bucket.Cors apply(final Cors cors) {
                return cors.toPb();
            }
            
            @Override
            public /* bridge */ Object apply(final Object o) {
                return this.apply((Cors)o);
            }
        };
    }
}
