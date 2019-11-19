package com.google.cloud.storage;

import com.google.api.client.util.DateTime;
import java.util.Objects;
import java.io.Serializable;

public static class HmacKeyMetadata implements Serializable
{
    private static final long serialVersionUID = 4571684785352640737L;
    private final String accessId;
    private final String etag;
    private final String id;
    private final String projectId;
    private final ServiceAccount serviceAccount;
    private final HmacKeyState state;
    private final Long createTime;
    private final Long updateTime;
    
    private HmacKeyMetadata(final Builder builder) {
        super();
        this.accessId = builder.accessId;
        this.etag = builder.etag;
        this.id = builder.id;
        this.projectId = builder.projectId;
        this.serviceAccount = builder.serviceAccount;
        this.state = builder.state;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
    }
    
    public static Builder newBuilder(final ServiceAccount serviceAccount) {
        return new Builder(serviceAccount);
    }
    
    public Builder toBuilder() {
        return new Builder(this);
    }
    
    public static HmacKeyMetadata of(final ServiceAccount serviceAccount, final String accessId, final String projectId) {
        return newBuilder(serviceAccount).setAccessId(accessId).setProjectId(projectId).build();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.accessId, this.projectId);
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
        return Objects.equals(this.accessId, hmacKeyMetadata.accessId) && Objects.equals(this.etag, hmacKeyMetadata.etag) && Objects.equals(this.id, hmacKeyMetadata.id) && Objects.equals(this.projectId, hmacKeyMetadata.projectId) && Objects.equals(this.serviceAccount, hmacKeyMetadata.serviceAccount) && Objects.equals(this.state, hmacKeyMetadata.state) && Objects.equals(this.createTime, hmacKeyMetadata.createTime) && Objects.equals(this.updateTime, hmacKeyMetadata.updateTime);
    }
    
    public com.google.api.services.storage.model.HmacKeyMetadata toPb() {
        final com.google.api.services.storage.model.HmacKeyMetadata hmacKeyMetadata = new com.google.api.services.storage.model.HmacKeyMetadata();
        hmacKeyMetadata.setAccessId(this.accessId);
        hmacKeyMetadata.setEtag(this.etag);
        hmacKeyMetadata.setId(this.id);
        hmacKeyMetadata.setProjectId(this.projectId);
        hmacKeyMetadata.setServiceAccountEmail((this.serviceAccount == null) ? null : this.serviceAccount.getEmail());
        hmacKeyMetadata.setState((this.state == null) ? null : this.state.toString());
        hmacKeyMetadata.setTimeCreated((this.createTime == null) ? null : new DateTime(this.createTime));
        hmacKeyMetadata.setUpdated((this.updateTime == null) ? null : new DateTime(this.updateTime));
        return hmacKeyMetadata;
    }
    
    static HmacKeyMetadata fromPb(final com.google.api.services.storage.model.HmacKeyMetadata hmacKeyMetadata) {
        return newBuilder(ServiceAccount.of(hmacKeyMetadata.getServiceAccountEmail())).setAccessId(hmacKeyMetadata.getAccessId()).setCreateTime(hmacKeyMetadata.getTimeCreated().getValue()).setEtag(hmacKeyMetadata.getEtag()).setId(hmacKeyMetadata.getId()).setProjectId(hmacKeyMetadata.getProjectId()).setState(HmacKeyState.valueOf(hmacKeyMetadata.getState())).setUpdateTime(hmacKeyMetadata.getUpdated().getValue()).build();
    }
    
    public String getAccessId() {
        return this.accessId;
    }
    
    public String getEtag() {
        return this.etag;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getProjectId() {
        return this.projectId;
    }
    
    public ServiceAccount getServiceAccount() {
        return this.serviceAccount;
    }
    
    public HmacKeyState getState() {
        return this.state;
    }
    
    public Long getCreateTime() {
        return this.createTime;
    }
    
    public Long getUpdateTime() {
        return this.updateTime;
    }
    
    static /* synthetic */ String access$1400(final HmacKeyMetadata hmacKeyMetadata) {
        return hmacKeyMetadata.accessId;
    }
    
    static /* synthetic */ String access$1500(final HmacKeyMetadata hmacKeyMetadata) {
        return hmacKeyMetadata.etag;
    }
    
    static /* synthetic */ String access$1600(final HmacKeyMetadata hmacKeyMetadata) {
        return hmacKeyMetadata.id;
    }
    
    static /* synthetic */ String access$1700(final HmacKeyMetadata hmacKeyMetadata) {
        return hmacKeyMetadata.projectId;
    }
    
    static /* synthetic */ ServiceAccount access$1800(final HmacKeyMetadata hmacKeyMetadata) {
        return hmacKeyMetadata.serviceAccount;
    }
    
    static /* synthetic */ HmacKeyState access$1900(final HmacKeyMetadata hmacKeyMetadata) {
        return hmacKeyMetadata.state;
    }
    
    static /* synthetic */ Long access$2000(final HmacKeyMetadata hmacKeyMetadata) {
        return hmacKeyMetadata.createTime;
    }
    
    static /* synthetic */ Long access$2100(final HmacKeyMetadata hmacKeyMetadata) {
        return hmacKeyMetadata.updateTime;
    }
    
    HmacKeyMetadata(final Builder builder, final HmacKey$1 object) {
        this(builder);
    }
}
