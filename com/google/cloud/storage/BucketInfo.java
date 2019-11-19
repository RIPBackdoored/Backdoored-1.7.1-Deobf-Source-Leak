package com.google.cloud.storage;

import com.google.common.collect.ImmutableMap;
import com.google.common.base.Preconditions;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.HashSet;
import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.api.services.storage.model.BucketAccessControl;
import com.google.common.collect.Lists;
import com.google.api.client.util.DateTime;
import com.google.common.base.MoreObjects;
import java.util.Objects;
import com.google.api.core.BetaApi;
import com.google.api.client.util.Data;
import java.util.Map;
import java.util.List;
import com.google.api.services.storage.model.Bucket;
import com.google.common.base.Function;
import java.io.Serializable;

public class BucketInfo implements Serializable
{
    static final Function<Bucket, BucketInfo> FROM_PB_FUNCTION;
    static final Function<BucketInfo, Bucket> TO_PB_FUNCTION;
    private static final long serialVersionUID = -4712013629621638459L;
    private final String generatedId;
    private final String name;
    private final Acl.Entity owner;
    private final String selfLink;
    private final Boolean requesterPays;
    private final Boolean versioningEnabled;
    private final String indexPage;
    private final String notFoundPage;
    private final List<DeleteRule> deleteRules;
    private final List<LifecycleRule> lifecycleRules;
    private final String etag;
    private final Long createTime;
    private final Long metageneration;
    private final List<Cors> cors;
    private final List<Acl> acl;
    private final List<Acl> defaultAcl;
    private final String location;
    private final StorageClass storageClass;
    private final Map<String, String> labels;
    private final String defaultKmsKeyName;
    private final Boolean defaultEventBasedHold;
    private final Long retentionEffectiveTime;
    private final Boolean retentionPolicyIsLocked;
    private final Long retentionPeriod;
    private final IamConfiguration iamConfiguration;
    private final String locationType;
    
    BucketInfo(final BuilderImpl builderImpl) {
        super();
        this.generatedId = builderImpl.generatedId;
        this.name = builderImpl.name;
        this.etag = builderImpl.etag;
        this.createTime = builderImpl.createTime;
        this.metageneration = builderImpl.metageneration;
        this.location = builderImpl.location;
        this.storageClass = builderImpl.storageClass;
        this.cors = builderImpl.cors;
        this.acl = builderImpl.acl;
        this.defaultAcl = builderImpl.defaultAcl;
        this.owner = builderImpl.owner;
        this.selfLink = builderImpl.selfLink;
        this.versioningEnabled = builderImpl.versioningEnabled;
        this.indexPage = builderImpl.indexPage;
        this.notFoundPage = builderImpl.notFoundPage;
        this.deleteRules = builderImpl.deleteRules;
        this.lifecycleRules = builderImpl.lifecycleRules;
        this.labels = builderImpl.labels;
        this.requesterPays = builderImpl.requesterPays;
        this.defaultKmsKeyName = builderImpl.defaultKmsKeyName;
        this.defaultEventBasedHold = builderImpl.defaultEventBasedHold;
        this.retentionEffectiveTime = builderImpl.retentionEffectiveTime;
        this.retentionPolicyIsLocked = builderImpl.retentionPolicyIsLocked;
        this.retentionPeriod = builderImpl.retentionPeriod;
        this.iamConfiguration = builderImpl.iamConfiguration;
        this.locationType = builderImpl.locationType;
    }
    
    public String getGeneratedId() {
        return this.generatedId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Acl.Entity getOwner() {
        return this.owner;
    }
    
    public String getSelfLink() {
        return this.selfLink;
    }
    
    public Boolean versioningEnabled() {
        return Data.isNull(this.versioningEnabled) ? null : this.versioningEnabled;
    }
    
    public Boolean requesterPays() {
        return Data.isNull(this.requesterPays) ? null : this.requesterPays;
    }
    
    public String getIndexPage() {
        return this.indexPage;
    }
    
    public String getNotFoundPage() {
        return this.notFoundPage;
    }
    
    @Deprecated
    public List<? extends DeleteRule> getDeleteRules() {
        return this.deleteRules;
    }
    
    public List<? extends LifecycleRule> getLifecycleRules() {
        return this.lifecycleRules;
    }
    
    public String getEtag() {
        return this.etag;
    }
    
    public Long getCreateTime() {
        return this.createTime;
    }
    
    public Long getMetageneration() {
        return this.metageneration;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public String getLocationType() {
        return this.locationType;
    }
    
    public StorageClass getStorageClass() {
        return this.storageClass;
    }
    
    public List<Cors> getCors() {
        return this.cors;
    }
    
    public List<Acl> getAcl() {
        return this.acl;
    }
    
    public List<Acl> getDefaultAcl() {
        return this.defaultAcl;
    }
    
    public Map<String, String> getLabels() {
        return this.labels;
    }
    
    public String getDefaultKmsKeyName() {
        return this.defaultKmsKeyName;
    }
    
    @BetaApi
    public Boolean getDefaultEventBasedHold() {
        return Data.isNull(this.defaultEventBasedHold) ? null : this.defaultEventBasedHold;
    }
    
    @BetaApi
    public Long getRetentionEffectiveTime() {
        return this.retentionEffectiveTime;
    }
    
    @BetaApi
    public Boolean retentionPolicyIsLocked() {
        return Data.isNull(this.retentionPolicyIsLocked) ? null : this.retentionPolicyIsLocked;
    }
    
    @BetaApi
    public Long getRetentionPeriod() {
        return this.retentionPeriod;
    }
    
    @BetaApi
    public IamConfiguration getIamConfiguration() {
        return this.iamConfiguration;
    }
    
    public Builder toBuilder() {
        return new BuilderImpl(this);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o != null && o.getClass().equals(BucketInfo.class) && Objects.equals(this.toPb(), ((BucketInfo)o).toPb()));
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.name).toString();
    }
    
    Bucket toPb() {
        final Bucket bucket = new Bucket();
        bucket.setId(this.generatedId);
        bucket.setName(this.name);
        bucket.setEtag(this.etag);
        if (this.createTime != null) {
            bucket.setTimeCreated(new DateTime(this.createTime));
        }
        if (this.metageneration != null) {
            bucket.setMetageneration(this.metageneration);
        }
        if (this.location != null) {
            bucket.setLocation(this.location);
        }
        if (this.locationType != null) {
            bucket.setLocationType(this.locationType);
        }
        if (this.storageClass != null) {
            bucket.setStorageClass(this.storageClass.toString());
        }
        if (this.cors != null) {
            bucket.setCors((List)Lists.<Cors, Object>transform(this.cors, (Function<? super Cors, ?>)Cors.TO_PB_FUNCTION));
        }
        if (this.acl != null) {
            bucket.setAcl((List)Lists.<Acl, Object>transform(this.acl, (Function<? super Acl, ?>)new Function<Acl, BucketAccessControl>() {
                final /* synthetic */ BucketInfo this$0;
                
                BucketInfo$3() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public BucketAccessControl apply(final Acl acl) {
                    return acl.toBucketPb();
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((Acl)o);
                }
            }));
        }
        if (this.defaultAcl != null) {
            bucket.setDefaultObjectAcl((List)Lists.<Acl, Object>transform(this.defaultAcl, (Function<? super Acl, ?>)new Function<Acl, ObjectAccessControl>() {
                final /* synthetic */ BucketInfo this$0;
                
                BucketInfo$4() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public ObjectAccessControl apply(final Acl acl) {
                    return acl.toObjectPb();
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((Acl)o);
                }
            }));
        }
        if (this.owner != null) {
            bucket.setOwner(new Bucket.Owner().setEntity(this.owner.toPb()));
        }
        bucket.setSelfLink(this.selfLink);
        if (this.versioningEnabled != null) {
            bucket.setVersioning(new Bucket.Versioning().setEnabled(this.versioningEnabled));
        }
        if (this.requesterPays != null) {
            final Bucket.Billing billing = new Bucket.Billing();
            billing.setRequesterPays(this.requesterPays);
            bucket.setBilling(billing);
        }
        if (this.indexPage != null || this.notFoundPage != null) {
            final Bucket.Website website = new Bucket.Website();
            website.setMainPageSuffix(this.indexPage);
            website.setNotFoundPage(this.notFoundPage);
            bucket.setWebsite(website);
        }
        final HashSet<Object> elements = new HashSet<Object>();
        if (this.deleteRules != null) {
            elements.addAll(Lists.<DeleteRule, Object>transform(this.deleteRules, (Function<? super DeleteRule, ?>)new Function<DeleteRule, Bucket.Lifecycle.Rule>() {
                final /* synthetic */ BucketInfo this$0;
                
                BucketInfo$5() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public Bucket.Lifecycle.Rule apply(final DeleteRule deleteRule) {
                    return deleteRule.toPb();
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((DeleteRule)o);
                }
            }));
        }
        if (this.lifecycleRules != null) {
            elements.addAll(Lists.<LifecycleRule, Object>transform(this.lifecycleRules, (Function<? super LifecycleRule, ?>)new Function<LifecycleRule, Bucket.Lifecycle.Rule>() {
                final /* synthetic */ BucketInfo this$0;
                
                BucketInfo$6() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public Bucket.Lifecycle.Rule apply(final LifecycleRule lifecycleRule) {
                    return lifecycleRule.toPb();
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((LifecycleRule)o);
                }
            }));
        }
        if (!elements.isEmpty()) {
            final Bucket.Lifecycle lifecycle = new Bucket.Lifecycle();
            lifecycle.setRule((List)ImmutableList.<Object>copyOf((Collection<?>)elements));
            bucket.setLifecycle(lifecycle);
        }
        if (this.labels != null) {
            bucket.setLabels((Map)this.labels);
        }
        if (this.defaultKmsKeyName != null) {
            bucket.setEncryption(new Bucket.Encryption().setDefaultKmsKeyName(this.defaultKmsKeyName));
        }
        if (this.defaultEventBasedHold != null) {
            bucket.setDefaultEventBasedHold(this.defaultEventBasedHold);
        }
        if (this.retentionPeriod != null) {
            if (Data.isNull(this.retentionPeriod)) {
                bucket.setRetentionPolicy((Bucket.RetentionPolicy)Data.<Bucket.RetentionPolicy>nullOf(Bucket.RetentionPolicy.class));
            }
            else {
                final Bucket.RetentionPolicy retentionPolicy = new Bucket.RetentionPolicy();
                retentionPolicy.setRetentionPeriod(this.retentionPeriod);
                if (this.retentionEffectiveTime != null) {
                    retentionPolicy.setEffectiveTime(new DateTime(this.retentionEffectiveTime));
                }
                if (this.retentionPolicyIsLocked != null) {
                    retentionPolicy.setIsLocked(this.retentionPolicyIsLocked);
                }
                bucket.setRetentionPolicy(retentionPolicy);
            }
        }
        if (this.iamConfiguration != null) {
            bucket.setIamConfiguration(this.iamConfiguration.toPb());
        }
        return bucket;
    }
    
    public static BucketInfo of(final String s) {
        return newBuilder(s).build();
    }
    
    public static Builder newBuilder(final String s) {
        return new BuilderImpl(s);
    }
    
    static BucketInfo fromPb(final Bucket bucket) {
        final BuilderImpl builderImpl = new BuilderImpl(bucket.getName());
        if (bucket.getId() != null) {
            builderImpl.setGeneratedId(bucket.getId());
        }
        if (bucket.getEtag() != null) {
            builderImpl.setEtag(bucket.getEtag());
        }
        if (bucket.getMetageneration() != null) {
            builderImpl.setMetageneration(bucket.getMetageneration());
        }
        if (bucket.getSelfLink() != null) {
            builderImpl.setSelfLink(bucket.getSelfLink());
        }
        if (bucket.getTimeCreated() != null) {
            builderImpl.setCreateTime(bucket.getTimeCreated().getValue());
        }
        if (bucket.getLocation() != null) {
            builderImpl.setLocation(bucket.getLocation());
        }
        if (bucket.getStorageClass() != null) {
            builderImpl.setStorageClass(StorageClass.valueOf(bucket.getStorageClass()));
        }
        if (bucket.getCors() != null) {
            builderImpl.setCors((Iterable<Cors>)Lists.<Object, Object>transform((List<Object>)bucket.getCors(), (Function<? super Object, ?>)Cors.FROM_PB_FUNCTION));
        }
        if (bucket.getAcl() != null) {
            builderImpl.setAcl((Iterable<Acl>)Lists.<Object, Object>transform((List<Object>)bucket.getAcl(), (Function<? super Object, ?>)new Function<BucketAccessControl, Acl>() {
                BucketInfo$7() {
                    super();
                }
                
                @Override
                public Acl apply(final BucketAccessControl bucketAccessControl) {
                    return Acl.fromPb(bucketAccessControl);
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((BucketAccessControl)o);
                }
            }));
        }
        if (bucket.getDefaultObjectAcl() != null) {
            builderImpl.setDefaultAcl((Iterable<Acl>)Lists.<Object, Object>transform((List<Object>)bucket.getDefaultObjectAcl(), (Function<? super Object, ?>)new Function<ObjectAccessControl, Acl>() {
                BucketInfo$8() {
                    super();
                }
                
                @Override
                public Acl apply(final ObjectAccessControl objectAccessControl) {
                    return Acl.fromPb(objectAccessControl);
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((ObjectAccessControl)o);
                }
            }));
        }
        if (bucket.getOwner() != null) {
            builderImpl.setOwner(Acl.Entity.fromPb(bucket.getOwner().getEntity()));
        }
        if (bucket.getVersioning() != null) {
            builderImpl.setVersioningEnabled(bucket.getVersioning().getEnabled());
        }
        final Bucket.Website website = bucket.getWebsite();
        if (website != null) {
            builderImpl.setIndexPage(website.getMainPageSuffix());
            builderImpl.setNotFoundPage(website.getNotFoundPage());
        }
        if (bucket.getLifecycle() != null && bucket.getLifecycle().getRule() != null) {
            builderImpl.setLifecycleRules((Iterable<? extends LifecycleRule>)Lists.<Object, Object>transform((List<Object>)bucket.getLifecycle().getRule(), (Function<? super Object, ?>)new Function<Bucket.Lifecycle.Rule, LifecycleRule>() {
                BucketInfo$9() {
                    super();
                }
                
                @Override
                public LifecycleRule apply(final Bucket.Lifecycle.Rule rule) {
                    return LifecycleRule.fromPb(rule);
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((Bucket.Lifecycle.Rule)o);
                }
            }));
            builderImpl.setDeleteRules((Iterable<? extends DeleteRule>)Lists.<Object, Object>transform((List<Object>)bucket.getLifecycle().getRule(), (Function<? super Object, ?>)new Function<Bucket.Lifecycle.Rule, DeleteRule>() {
                BucketInfo$10() {
                    super();
                }
                
                @Override
                public DeleteRule apply(final Bucket.Lifecycle.Rule rule) {
                    return DeleteRule.fromPb(rule);
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((Bucket.Lifecycle.Rule)o);
                }
            }));
        }
        if (bucket.getLabels() != null) {
            builderImpl.setLabels(bucket.getLabels());
        }
        final Bucket.Billing billing = bucket.getBilling();
        if (billing != null) {
            builderImpl.setRequesterPays(billing.getRequesterPays());
        }
        final Bucket.Encryption encryption = bucket.getEncryption();
        if (encryption != null && encryption.getDefaultKmsKeyName() != null && !encryption.getDefaultKmsKeyName().isEmpty()) {
            builderImpl.setDefaultKmsKeyName(encryption.getDefaultKmsKeyName());
        }
        if (bucket.getDefaultEventBasedHold() != null) {
            builderImpl.setDefaultEventBasedHold(bucket.getDefaultEventBasedHold());
        }
        final Bucket.RetentionPolicy retentionPolicy = bucket.getRetentionPolicy();
        if (retentionPolicy != null) {
            if (retentionPolicy.getEffectiveTime() != null) {
                builderImpl.setRetentionEffectiveTime(retentionPolicy.getEffectiveTime().getValue());
            }
            if (retentionPolicy.getIsLocked() != null) {
                builderImpl.setRetentionPolicyIsLocked(retentionPolicy.getIsLocked());
            }
            if (retentionPolicy.getRetentionPeriod() != null) {
                builderImpl.setRetentionPeriod(retentionPolicy.getRetentionPeriod());
            }
        }
        final Bucket.IamConfiguration iamConfiguration = bucket.getIamConfiguration();
        if (bucket.getLocationType() != null) {
            builderImpl.setLocationType(bucket.getLocationType());
        }
        if (iamConfiguration != null) {
            builderImpl.setIamConfiguration(IamConfiguration.fromPb(iamConfiguration));
        }
        return builderImpl.build();
    }
    
    static /* synthetic */ String access$1200(final BucketInfo bucketInfo) {
        return bucketInfo.generatedId;
    }
    
    static /* synthetic */ String access$1300(final BucketInfo bucketInfo) {
        return bucketInfo.name;
    }
    
    static /* synthetic */ String access$1400(final BucketInfo bucketInfo) {
        return bucketInfo.etag;
    }
    
    static /* synthetic */ Long access$1500(final BucketInfo bucketInfo) {
        return bucketInfo.createTime;
    }
    
    static /* synthetic */ Long access$1600(final BucketInfo bucketInfo) {
        return bucketInfo.metageneration;
    }
    
    static /* synthetic */ String access$1700(final BucketInfo bucketInfo) {
        return bucketInfo.location;
    }
    
    static /* synthetic */ StorageClass access$1800(final BucketInfo bucketInfo) {
        return bucketInfo.storageClass;
    }
    
    static /* synthetic */ List access$1900(final BucketInfo bucketInfo) {
        return bucketInfo.cors;
    }
    
    static /* synthetic */ List access$2000(final BucketInfo bucketInfo) {
        return bucketInfo.acl;
    }
    
    static /* synthetic */ List access$2100(final BucketInfo bucketInfo) {
        return bucketInfo.defaultAcl;
    }
    
    static /* synthetic */ Acl.Entity access$2200(final BucketInfo bucketInfo) {
        return bucketInfo.owner;
    }
    
    static /* synthetic */ String access$2300(final BucketInfo bucketInfo) {
        return bucketInfo.selfLink;
    }
    
    static /* synthetic */ Boolean access$2400(final BucketInfo bucketInfo) {
        return bucketInfo.versioningEnabled;
    }
    
    static /* synthetic */ String access$2500(final BucketInfo bucketInfo) {
        return bucketInfo.indexPage;
    }
    
    static /* synthetic */ String access$2600(final BucketInfo bucketInfo) {
        return bucketInfo.notFoundPage;
    }
    
    static /* synthetic */ List access$2700(final BucketInfo bucketInfo) {
        return bucketInfo.deleteRules;
    }
    
    static /* synthetic */ List access$2800(final BucketInfo bucketInfo) {
        return bucketInfo.lifecycleRules;
    }
    
    static /* synthetic */ Map access$2900(final BucketInfo bucketInfo) {
        return bucketInfo.labels;
    }
    
    static /* synthetic */ Boolean access$3000(final BucketInfo bucketInfo) {
        return bucketInfo.requesterPays;
    }
    
    static /* synthetic */ String access$3100(final BucketInfo bucketInfo) {
        return bucketInfo.defaultKmsKeyName;
    }
    
    static /* synthetic */ Boolean access$3200(final BucketInfo bucketInfo) {
        return bucketInfo.defaultEventBasedHold;
    }
    
    static /* synthetic */ Long access$3300(final BucketInfo bucketInfo) {
        return bucketInfo.retentionEffectiveTime;
    }
    
    static /* synthetic */ Boolean access$3400(final BucketInfo bucketInfo) {
        return bucketInfo.retentionPolicyIsLocked;
    }
    
    static /* synthetic */ Long access$3500(final BucketInfo bucketInfo) {
        return bucketInfo.retentionPeriod;
    }
    
    static /* synthetic */ IamConfiguration access$3600(final BucketInfo bucketInfo) {
        return bucketInfo.iamConfiguration;
    }
    
    static /* synthetic */ String access$3700(final BucketInfo bucketInfo) {
        return bucketInfo.locationType;
    }
    
    static {
        FROM_PB_FUNCTION = new Function<Bucket, BucketInfo>() {
            BucketInfo$1() {
                super();
            }
            
            @Override
            public BucketInfo apply(final Bucket bucket) {
                return BucketInfo.fromPb(bucket);
            }
            
            @Override
            public /* bridge */ Object apply(final Object o) {
                return this.apply((Bucket)o);
            }
        };
        TO_PB_FUNCTION = new Function<BucketInfo, Bucket>() {
            BucketInfo$2() {
                super();
            }
            
            @Override
            public Bucket apply(final BucketInfo bucketInfo) {
                return bucketInfo.toPb();
            }
            
            @Override
            public /* bridge */ Object apply(final Object o) {
                return this.apply((BucketInfo)o);
            }
        };
    }
}
