package com.google.cloud.storage;

public static class Builder
{
    private String accessId;
    private String etag;
    private String id;
    private String projectId;
    private ServiceAccount serviceAccount;
    private HmacKeyState state;
    private Long createTime;
    private Long updateTime;
    
    private Builder(final ServiceAccount serviceAccount) {
        super();
        this.serviceAccount = serviceAccount;
    }
    
    private Builder(final HmacKeyMetadata hmacKeyMetadata) {
        super();
        this.accessId = HmacKeyMetadata.access$1400(hmacKeyMetadata);
        this.etag = HmacKeyMetadata.access$1500(hmacKeyMetadata);
        this.id = HmacKeyMetadata.access$1600(hmacKeyMetadata);
        this.projectId = HmacKeyMetadata.access$1700(hmacKeyMetadata);
        this.serviceAccount = HmacKeyMetadata.access$1800(hmacKeyMetadata);
        this.state = HmacKeyMetadata.access$1900(hmacKeyMetadata);
        this.createTime = HmacKeyMetadata.access$2000(hmacKeyMetadata);
        this.updateTime = HmacKeyMetadata.access$2100(hmacKeyMetadata);
    }
    
    public Builder setAccessId(final String accessId) {
        this.accessId = accessId;
        return this;
    }
    
    public Builder setEtag(final String etag) {
        this.etag = etag;
        return this;
    }
    
    public Builder setId(final String id) {
        this.id = id;
        return this;
    }
    
    public Builder setServiceAccount(final ServiceAccount serviceAccount) {
        this.serviceAccount = serviceAccount;
        return this;
    }
    
    public Builder setState(final HmacKeyState state) {
        this.state = state;
        return this;
    }
    
    public Builder setCreateTime(final long n) {
        this.createTime = n;
        return this;
    }
    
    public Builder setProjectId(final String projectId) {
        this.projectId = projectId;
        return this;
    }
    
    public HmacKeyMetadata build() {
        return new HmacKeyMetadata(this);
    }
    
    public Builder setUpdateTime(final long n) {
        this.updateTime = n;
        return this;
    }
    
    static /* synthetic */ String access$400(final Builder builder) {
        return builder.accessId;
    }
    
    static /* synthetic */ String access$500(final Builder builder) {
        return builder.etag;
    }
    
    static /* synthetic */ String access$600(final Builder builder) {
        return builder.id;
    }
    
    static /* synthetic */ String access$700(final Builder builder) {
        return builder.projectId;
    }
    
    static /* synthetic */ ServiceAccount access$800(final Builder builder) {
        return builder.serviceAccount;
    }
    
    static /* synthetic */ HmacKeyState access$900(final Builder builder) {
        return builder.state;
    }
    
    static /* synthetic */ Long access$1000(final Builder builder) {
        return builder.createTime;
    }
    
    static /* synthetic */ Long access$1100(final Builder builder) {
        return builder.updateTime;
    }
    
    Builder(final ServiceAccount serviceAccount, final HmacKey$1 object) {
        this(serviceAccount);
    }
    
    Builder(final HmacKeyMetadata hmacKeyMetadata, final HmacKey$1 object) {
        this(hmacKeyMetadata);
    }
}
