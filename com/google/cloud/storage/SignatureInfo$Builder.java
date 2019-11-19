package com.google.cloud.storage;

import java.util.HashMap;
import com.google.common.base.Preconditions;
import java.net.URI;
import java.util.Map;

public static final class Builder
{
    private final HttpMethod httpVerb;
    private String contentMd5;
    private String contentType;
    private final long expiration;
    private Map<String, String> canonicalizedExtensionHeaders;
    private final URI canonicalizedResource;
    private Storage.SignUrlOption.SignatureVersion signatureVersion;
    private String accountEmail;
    private long timestamp;
    
    public Builder(final HttpMethod httpVerb, final long expiration, final URI canonicalizedResource) {
        super();
        this.httpVerb = httpVerb;
        this.expiration = expiration;
        this.canonicalizedResource = canonicalizedResource;
    }
    
    public Builder(final SignatureInfo signatureInfo) {
        super();
        this.httpVerb = SignatureInfo.access$900(signatureInfo);
        this.contentMd5 = SignatureInfo.access$1000(signatureInfo);
        this.contentType = SignatureInfo.access$1100(signatureInfo);
        this.expiration = SignatureInfo.access$1200(signatureInfo);
        this.canonicalizedExtensionHeaders = (Map<String, String>)SignatureInfo.access$1300(signatureInfo);
        this.canonicalizedResource = SignatureInfo.access$1400(signatureInfo);
        this.signatureVersion = SignatureInfo.access$1500(signatureInfo);
        this.accountEmail = SignatureInfo.access$1600(signatureInfo);
        this.timestamp = SignatureInfo.access$1700(signatureInfo);
    }
    
    public Builder setContentMd5(final String contentMd5) {
        this.contentMd5 = contentMd5;
        return this;
    }
    
    public Builder setContentType(final String contentType) {
        this.contentType = contentType;
        return this;
    }
    
    public Builder setCanonicalizedExtensionHeaders(final Map<String, String> canonicalizedExtensionHeaders) {
        this.canonicalizedExtensionHeaders = canonicalizedExtensionHeaders;
        return this;
    }
    
    public Builder setSignatureVersion(final Storage.SignUrlOption.SignatureVersion signatureVersion) {
        this.signatureVersion = signatureVersion;
        return this;
    }
    
    public Builder setAccountEmail(final String accountEmail) {
        this.accountEmail = accountEmail;
        return this;
    }
    
    public Builder setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    
    public SignatureInfo build() {
        Preconditions.checkArgument(this.httpVerb != null, (Object)"Required HTTP method");
        Preconditions.checkArgument(this.canonicalizedResource != null, (Object)"Required canonicalized resource");
        Preconditions.checkArgument(this.expiration >= 0L, (Object)"Expiration must be greater than or equal to zero");
        if (Storage.SignUrlOption.SignatureVersion.V4.equals(this.signatureVersion)) {
            Preconditions.checkArgument(this.accountEmail != null, (Object)"Account email required to use V4 signing");
            Preconditions.checkArgument(this.timestamp > 0L, (Object)"Timestamp required to use V4 signing");
            Preconditions.checkArgument(this.expiration <= 604800L, (Object)"Expiration can't be longer than 7 days to use V4 signing");
        }
        if (this.canonicalizedExtensionHeaders == null) {
            this.canonicalizedExtensionHeaders = new HashMap<String, String>();
        }
        return new SignatureInfo(this, null);
    }
    
    static /* synthetic */ HttpMethod access$000(final Builder builder) {
        return builder.httpVerb;
    }
    
    static /* synthetic */ String access$100(final Builder builder) {
        return builder.contentMd5;
    }
    
    static /* synthetic */ String access$200(final Builder builder) {
        return builder.contentType;
    }
    
    static /* synthetic */ long access$300(final Builder builder) {
        return builder.expiration;
    }
    
    static /* synthetic */ URI access$400(final Builder builder) {
        return builder.canonicalizedResource;
    }
    
    static /* synthetic */ Storage.SignUrlOption.SignatureVersion access$500(final Builder builder) {
        return builder.signatureVersion;
    }
    
    static /* synthetic */ String access$600(final Builder builder) {
        return builder.accountEmail;
    }
    
    static /* synthetic */ long access$700(final Builder builder) {
        return builder.timestamp;
    }
    
    static /* synthetic */ Map access$800(final Builder builder) {
        return builder.canonicalizedExtensionHeaders;
    }
}
