package com.google.cloud.storage;

import com.google.api.client.util.DateTime;
import com.google.api.services.storage.model.HmacKeyMetadata;
import java.util.Objects;
import java.io.Serializable;

public class HmacKey implements Serializable
{
    private static final long serialVersionUID = -1809610424373783062L;
    private final String secretKey;
    private final HmacKeyMetadata metadata;
    
    private HmacKey(final Builder builder) {
        super();
        this.secretKey = builder.secretKey;
        this.metadata = builder.metadata;
    }
    
    public static Builder newBuilder(final String s) {
        return new Builder(s);
    }
    
    public String getSecretKey() {
        return this.secretKey;
    }
    
    public HmacKeyMetadata getMetadata() {
        return this.metadata;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.secretKey, this.metadata);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final HmacKeyMetadata hmacKeyMetadata = (HmacKeyMetadata)o;
        return Objects.equals(this.secretKey, this.secretKey) && Objects.equals(this.metadata, this.metadata);
    }
    
    com.google.api.services.storage.model.HmacKey toPb() {
        final com.google.api.services.storage.model.HmacKey hmacKey = new com.google.api.services.storage.model.HmacKey();
        hmacKey.setSecret(this.secretKey);
        if (this.metadata != null) {
            hmacKey.setMetadata(this.metadata.toPb());
        }
        return hmacKey;
    }
    
    static HmacKey fromPb(final com.google.api.services.storage.model.HmacKey hmacKey) {
        return newBuilder(hmacKey.getSecret()).setMetadata(HmacKeyMetadata.fromPb(hmacKey.getMetadata())).build();
    }
    
    HmacKey(final Builder builder, final HmacKey$1 object) {
        this(builder);
    }
}
