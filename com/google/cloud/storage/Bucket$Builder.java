package com.google.cloud.storage;

import java.util.Map;

public static class Builder extends BucketInfo.Builder
{
    private final Storage storage;
    private final BuilderImpl infoBuilder;
    
    Builder(final Bucket bucket) {
        super();
        this.storage = Bucket.access$100(bucket);
        this.infoBuilder = new BuilderImpl(bucket);
    }
    
    @Override
    public Builder setName(final String name) {
        this.infoBuilder.setName(name);
        return this;
    }
    
    @Override
    Builder setGeneratedId(final String generatedId) {
        this.infoBuilder.setGeneratedId(generatedId);
        return this;
    }
    
    @Override
    Builder setOwner(final Acl.Entity owner) {
        this.infoBuilder.setOwner(owner);
        return this;
    }
    
    @Override
    Builder setSelfLink(final String selfLink) {
        this.infoBuilder.setSelfLink(selfLink);
        return this;
    }
    
    @Override
    public Builder setVersioningEnabled(final Boolean versioningEnabled) {
        this.infoBuilder.setVersioningEnabled(versioningEnabled);
        return this;
    }
    
    @Override
    public Builder setRequesterPays(final Boolean requesterPays) {
        this.infoBuilder.setRequesterPays(requesterPays);
        return this;
    }
    
    @Override
    public Builder setIndexPage(final String indexPage) {
        this.infoBuilder.setIndexPage(indexPage);
        return this;
    }
    
    @Override
    public Builder setNotFoundPage(final String notFoundPage) {
        this.infoBuilder.setNotFoundPage(notFoundPage);
        return this;
    }
    
    @Deprecated
    @Override
    public Builder setDeleteRules(final Iterable<? extends DeleteRule> deleteRules) {
        this.infoBuilder.setDeleteRules(deleteRules);
        return this;
    }
    
    @Override
    public Builder setLifecycleRules(final Iterable<? extends LifecycleRule> lifecycleRules) {
        this.infoBuilder.setLifecycleRules(lifecycleRules);
        return this;
    }
    
    @Override
    public Builder setStorageClass(final StorageClass storageClass) {
        this.infoBuilder.setStorageClass(storageClass);
        return this;
    }
    
    @Override
    public Builder setLocation(final String location) {
        this.infoBuilder.setLocation(location);
        return this;
    }
    
    @Override
    Builder setEtag(final String etag) {
        this.infoBuilder.setEtag(etag);
        return this;
    }
    
    @Override
    Builder setCreateTime(final Long createTime) {
        this.infoBuilder.setCreateTime(createTime);
        return this;
    }
    
    @Override
    Builder setMetageneration(final Long metageneration) {
        this.infoBuilder.setMetageneration(metageneration);
        return this;
    }
    
    @Override
    public Builder setCors(final Iterable<Cors> cors) {
        this.infoBuilder.setCors(cors);
        return this;
    }
    
    @Override
    public Builder setAcl(final Iterable<Acl> acl) {
        this.infoBuilder.setAcl(acl);
        return this;
    }
    
    @Override
    public Builder setDefaultAcl(final Iterable<Acl> defaultAcl) {
        this.infoBuilder.setDefaultAcl(defaultAcl);
        return this;
    }
    
    @Override
    public Builder setLabels(final Map<String, String> labels) {
        this.infoBuilder.setLabels(labels);
        return this;
    }
    
    @Override
    public Builder setDefaultKmsKeyName(final String defaultKmsKeyName) {
        this.infoBuilder.setDefaultKmsKeyName(defaultKmsKeyName);
        return this;
    }
    
    @Override
    public Builder setDefaultEventBasedHold(final Boolean defaultEventBasedHold) {
        this.infoBuilder.setDefaultEventBasedHold(defaultEventBasedHold);
        return this;
    }
    
    @Override
    Builder setRetentionEffectiveTime(final Long retentionEffectiveTime) {
        this.infoBuilder.setRetentionEffectiveTime(retentionEffectiveTime);
        return this;
    }
    
    @Override
    Builder setRetentionPolicyIsLocked(final Boolean retentionPolicyIsLocked) {
        this.infoBuilder.setRetentionPolicyIsLocked(retentionPolicyIsLocked);
        return this;
    }
    
    @Override
    public Builder setRetentionPeriod(final Long retentionPeriod) {
        this.infoBuilder.setRetentionPeriod(retentionPeriod);
        return this;
    }
    
    @Override
    public Builder setIamConfiguration(final IamConfiguration iamConfiguration) {
        this.infoBuilder.setIamConfiguration(iamConfiguration);
        return this;
    }
    
    @Override
    Builder setLocationType(final String locationType) {
        this.infoBuilder.setLocationType(locationType);
        return this;
    }
    
    @Override
    public Bucket build() {
        return new Bucket(this.storage, this.infoBuilder);
    }
    
    @Override
    public /* bridge */ BucketInfo build() {
        return this.build();
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setIamConfiguration(final IamConfiguration iamConfiguration) {
        return this.setIamConfiguration(iamConfiguration);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setRetentionPeriod(final Long retentionPeriod) {
        return this.setRetentionPeriod(retentionPeriod);
    }
    
    @Override
    /* bridge */ BucketInfo.Builder setRetentionPolicyIsLocked(final Boolean retentionPolicyIsLocked) {
        return this.setRetentionPolicyIsLocked(retentionPolicyIsLocked);
    }
    
    @Override
    /* bridge */ BucketInfo.Builder setRetentionEffectiveTime(final Long retentionEffectiveTime) {
        return this.setRetentionEffectiveTime(retentionEffectiveTime);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setDefaultEventBasedHold(final Boolean defaultEventBasedHold) {
        return this.setDefaultEventBasedHold(defaultEventBasedHold);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setDefaultKmsKeyName(final String defaultKmsKeyName) {
        return this.setDefaultKmsKeyName(defaultKmsKeyName);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setLabels(final Map labels) {
        return this.setLabels(labels);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setDefaultAcl(final Iterable defaultAcl) {
        return this.setDefaultAcl(defaultAcl);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setAcl(final Iterable acl) {
        return this.setAcl(acl);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setCors(final Iterable cors) {
        return this.setCors(cors);
    }
    
    @Override
    /* bridge */ BucketInfo.Builder setLocationType(final String locationType) {
        return this.setLocationType(locationType);
    }
    
    @Override
    /* bridge */ BucketInfo.Builder setMetageneration(final Long metageneration) {
        return this.setMetageneration(metageneration);
    }
    
    @Override
    /* bridge */ BucketInfo.Builder setCreateTime(final Long createTime) {
        return this.setCreateTime(createTime);
    }
    
    @Override
    /* bridge */ BucketInfo.Builder setEtag(final String etag) {
        return this.setEtag(etag);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setLocation(final String location) {
        return this.setLocation(location);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setStorageClass(final StorageClass storageClass) {
        return this.setStorageClass(storageClass);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setLifecycleRules(final Iterable lifecycleRules) {
        return this.setLifecycleRules(lifecycleRules);
    }
    
    @Deprecated
    @Override
    public /* bridge */ BucketInfo.Builder setDeleteRules(final Iterable deleteRules) {
        return this.setDeleteRules(deleteRules);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setNotFoundPage(final String notFoundPage) {
        return this.setNotFoundPage(notFoundPage);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setIndexPage(final String indexPage) {
        return this.setIndexPage(indexPage);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setVersioningEnabled(final Boolean versioningEnabled) {
        return this.setVersioningEnabled(versioningEnabled);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setRequesterPays(final Boolean requesterPays) {
        return this.setRequesterPays(requesterPays);
    }
    
    @Override
    /* bridge */ BucketInfo.Builder setSelfLink(final String selfLink) {
        return this.setSelfLink(selfLink);
    }
    
    @Override
    /* bridge */ BucketInfo.Builder setOwner(final Acl.Entity owner) {
        return this.setOwner(owner);
    }
    
    @Override
    /* bridge */ BucketInfo.Builder setGeneratedId(final String generatedId) {
        return this.setGeneratedId(generatedId);
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder setName(final String name) {
        return this.setName(name);
    }
}
