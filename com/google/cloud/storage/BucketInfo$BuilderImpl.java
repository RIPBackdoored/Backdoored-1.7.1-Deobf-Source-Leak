package com.google.cloud.storage;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList;
import com.google.common.base.MoreObjects;
import com.google.api.client.util.Data;
import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.List;

static final class BuilderImpl extends Builder
{
    private String generatedId;
    private String name;
    private Acl.Entity owner;
    private String selfLink;
    private Boolean requesterPays;
    private Boolean versioningEnabled;
    private String indexPage;
    private String notFoundPage;
    private List<DeleteRule> deleteRules;
    private List<LifecycleRule> lifecycleRules;
    private StorageClass storageClass;
    private String location;
    private String etag;
    private Long createTime;
    private Long metageneration;
    private List<Cors> cors;
    private List<Acl> acl;
    private List<Acl> defaultAcl;
    private Map<String, String> labels;
    private String defaultKmsKeyName;
    private Boolean defaultEventBasedHold;
    private Long retentionEffectiveTime;
    private Boolean retentionPolicyIsLocked;
    private Long retentionPeriod;
    private IamConfiguration iamConfiguration;
    private String locationType;
    
    BuilderImpl(final String name) {
        super();
        this.name = name;
    }
    
    BuilderImpl(final BucketInfo bucketInfo) {
        super();
        this.generatedId = BucketInfo.access$1200(bucketInfo);
        this.name = BucketInfo.access$1300(bucketInfo);
        this.etag = BucketInfo.access$1400(bucketInfo);
        this.createTime = BucketInfo.access$1500(bucketInfo);
        this.metageneration = BucketInfo.access$1600(bucketInfo);
        this.location = BucketInfo.access$1700(bucketInfo);
        this.storageClass = BucketInfo.access$1800(bucketInfo);
        this.cors = (List<Cors>)BucketInfo.access$1900(bucketInfo);
        this.acl = (List<Acl>)BucketInfo.access$2000(bucketInfo);
        this.defaultAcl = (List<Acl>)BucketInfo.access$2100(bucketInfo);
        this.owner = BucketInfo.access$2200(bucketInfo);
        this.selfLink = BucketInfo.access$2300(bucketInfo);
        this.versioningEnabled = BucketInfo.access$2400(bucketInfo);
        this.indexPage = BucketInfo.access$2500(bucketInfo);
        this.notFoundPage = BucketInfo.access$2600(bucketInfo);
        this.deleteRules = (List<DeleteRule>)BucketInfo.access$2700(bucketInfo);
        this.lifecycleRules = (List<LifecycleRule>)BucketInfo.access$2800(bucketInfo);
        this.labels = (Map<String, String>)BucketInfo.access$2900(bucketInfo);
        this.requesterPays = BucketInfo.access$3000(bucketInfo);
        this.defaultKmsKeyName = BucketInfo.access$3100(bucketInfo);
        this.defaultEventBasedHold = BucketInfo.access$3200(bucketInfo);
        this.retentionEffectiveTime = BucketInfo.access$3300(bucketInfo);
        this.retentionPolicyIsLocked = BucketInfo.access$3400(bucketInfo);
        this.retentionPeriod = BucketInfo.access$3500(bucketInfo);
        this.iamConfiguration = BucketInfo.access$3600(bucketInfo);
        this.locationType = BucketInfo.access$3700(bucketInfo);
    }
    
    @Override
    public Builder setName(final String reference) {
        this.name = Preconditions.<String>checkNotNull(reference);
        return this;
    }
    
    @Override
    Builder setGeneratedId(final String generatedId) {
        this.generatedId = generatedId;
        return this;
    }
    
    @Override
    Builder setOwner(final Acl.Entity owner) {
        this.owner = owner;
        return this;
    }
    
    @Override
    Builder setSelfLink(final String selfLink) {
        this.selfLink = selfLink;
        return this;
    }
    
    @Override
    public Builder setVersioningEnabled(final Boolean first) {
        this.versioningEnabled = MoreObjects.<Boolean>firstNonNull(first, Data.<Boolean>nullOf(Boolean.class));
        return this;
    }
    
    @Override
    public Builder setRequesterPays(final Boolean first) {
        this.requesterPays = MoreObjects.<Boolean>firstNonNull(first, Data.<Boolean>nullOf(Boolean.class));
        return this;
    }
    
    @Override
    public Builder setIndexPage(final String indexPage) {
        this.indexPage = indexPage;
        return this;
    }
    
    @Override
    public Builder setNotFoundPage(final String notFoundPage) {
        this.notFoundPage = notFoundPage;
        return this;
    }
    
    @Deprecated
    @Override
    public Builder setDeleteRules(final Iterable<? extends DeleteRule> elements) {
        this.deleteRules = (List<DeleteRule>)((elements != null) ? ImmutableList.<Object>copyOf((Iterable<?>)elements) : null);
        return this;
    }
    
    @Override
    public Builder setLifecycleRules(final Iterable<? extends LifecycleRule> elements) {
        this.lifecycleRules = (List<LifecycleRule>)((elements != null) ? ImmutableList.<Object>copyOf((Iterable<?>)elements) : null);
        return this;
    }
    
    @Override
    public Builder setStorageClass(final StorageClass storageClass) {
        this.storageClass = storageClass;
        return this;
    }
    
    @Override
    public Builder setLocation(final String location) {
        this.location = location;
        return this;
    }
    
    @Override
    Builder setEtag(final String etag) {
        this.etag = etag;
        return this;
    }
    
    @Override
    Builder setCreateTime(final Long createTime) {
        this.createTime = createTime;
        return this;
    }
    
    @Override
    Builder setMetageneration(final Long metageneration) {
        this.metageneration = metageneration;
        return this;
    }
    
    @Override
    public Builder setCors(final Iterable<Cors> elements) {
        this.cors = (List<Cors>)((elements != null) ? ImmutableList.<Object>copyOf((Iterable<?>)elements) : null);
        return this;
    }
    
    @Override
    public Builder setAcl(final Iterable<Acl> elements) {
        this.acl = (List<Acl>)((elements != null) ? ImmutableList.<Object>copyOf((Iterable<?>)elements) : null);
        return this;
    }
    
    @Override
    public Builder setDefaultAcl(final Iterable<Acl> elements) {
        this.defaultAcl = (List<Acl>)((elements != null) ? ImmutableList.<Object>copyOf((Iterable<?>)elements) : null);
        return this;
    }
    
    @Override
    public Builder setLabels(final Map<String, String> map) {
        this.labels = (Map<String, String>)((map != null) ? ImmutableMap.copyOf((Map)map) : null);
        return this;
    }
    
    @Override
    public Builder setDefaultKmsKeyName(final String s) {
        this.defaultKmsKeyName = ((s != null) ? s : Data.<String>nullOf(String.class));
        return this;
    }
    
    @Override
    public Builder setDefaultEventBasedHold(final Boolean first) {
        this.defaultEventBasedHold = MoreObjects.<Boolean>firstNonNull(first, Data.<Boolean>nullOf(Boolean.class));
        return this;
    }
    
    @Override
    Builder setRetentionEffectiveTime(final Long first) {
        this.retentionEffectiveTime = MoreObjects.<Long>firstNonNull(first, Data.<Long>nullOf(Long.class));
        return this;
    }
    
    @Override
    Builder setRetentionPolicyIsLocked(final Boolean first) {
        this.retentionPolicyIsLocked = MoreObjects.<Boolean>firstNonNull(first, Data.<Boolean>nullOf(Boolean.class));
        return this;
    }
    
    @Override
    public Builder setRetentionPeriod(final Long first) {
        this.retentionPeriod = MoreObjects.<Long>firstNonNull(first, Data.<Long>nullOf(Long.class));
        return this;
    }
    
    @Override
    public Builder setIamConfiguration(final IamConfiguration iamConfiguration) {
        this.iamConfiguration = iamConfiguration;
        return this;
    }
    
    @Override
    Builder setLocationType(final String locationType) {
        this.locationType = locationType;
        return this;
    }
    
    @Override
    public BucketInfo build() {
        Preconditions.<String>checkNotNull(this.name);
        return new BucketInfo(this);
    }
    
    static /* synthetic */ String access$3800(final BuilderImpl builderImpl) {
        return builderImpl.generatedId;
    }
    
    static /* synthetic */ String access$3900(final BuilderImpl builderImpl) {
        return builderImpl.name;
    }
    
    static /* synthetic */ String access$4000(final BuilderImpl builderImpl) {
        return builderImpl.etag;
    }
    
    static /* synthetic */ Long access$4100(final BuilderImpl builderImpl) {
        return builderImpl.createTime;
    }
    
    static /* synthetic */ Long access$4200(final BuilderImpl builderImpl) {
        return builderImpl.metageneration;
    }
    
    static /* synthetic */ String access$4300(final BuilderImpl builderImpl) {
        return builderImpl.location;
    }
    
    static /* synthetic */ StorageClass access$4400(final BuilderImpl builderImpl) {
        return builderImpl.storageClass;
    }
    
    static /* synthetic */ List access$4500(final BuilderImpl builderImpl) {
        return builderImpl.cors;
    }
    
    static /* synthetic */ List access$4600(final BuilderImpl builderImpl) {
        return builderImpl.acl;
    }
    
    static /* synthetic */ List access$4700(final BuilderImpl builderImpl) {
        return builderImpl.defaultAcl;
    }
    
    static /* synthetic */ Acl.Entity access$4800(final BuilderImpl builderImpl) {
        return builderImpl.owner;
    }
    
    static /* synthetic */ String access$4900(final BuilderImpl builderImpl) {
        return builderImpl.selfLink;
    }
    
    static /* synthetic */ Boolean access$5000(final BuilderImpl builderImpl) {
        return builderImpl.versioningEnabled;
    }
    
    static /* synthetic */ String access$5100(final BuilderImpl builderImpl) {
        return builderImpl.indexPage;
    }
    
    static /* synthetic */ String access$5200(final BuilderImpl builderImpl) {
        return builderImpl.notFoundPage;
    }
    
    static /* synthetic */ List access$5300(final BuilderImpl builderImpl) {
        return builderImpl.deleteRules;
    }
    
    static /* synthetic */ List access$5400(final BuilderImpl builderImpl) {
        return builderImpl.lifecycleRules;
    }
    
    static /* synthetic */ Map access$5500(final BuilderImpl builderImpl) {
        return builderImpl.labels;
    }
    
    static /* synthetic */ Boolean access$5600(final BuilderImpl builderImpl) {
        return builderImpl.requesterPays;
    }
    
    static /* synthetic */ String access$5700(final BuilderImpl builderImpl) {
        return builderImpl.defaultKmsKeyName;
    }
    
    static /* synthetic */ Boolean access$5800(final BuilderImpl builderImpl) {
        return builderImpl.defaultEventBasedHold;
    }
    
    static /* synthetic */ Long access$5900(final BuilderImpl builderImpl) {
        return builderImpl.retentionEffectiveTime;
    }
    
    static /* synthetic */ Boolean access$6000(final BuilderImpl builderImpl) {
        return builderImpl.retentionPolicyIsLocked;
    }
    
    static /* synthetic */ Long access$6100(final BuilderImpl builderImpl) {
        return builderImpl.retentionPeriod;
    }
    
    static /* synthetic */ IamConfiguration access$6200(final BuilderImpl builderImpl) {
        return builderImpl.iamConfiguration;
    }
    
    static /* synthetic */ String access$6300(final BuilderImpl builderImpl) {
        return builderImpl.locationType;
    }
}
