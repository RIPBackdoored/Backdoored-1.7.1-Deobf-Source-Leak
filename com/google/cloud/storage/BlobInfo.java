package com.google.cloud.storage;

import java.util.HashMap;
import java.util.Arrays;
import java.util.Collection;
import com.google.common.collect.ImmutableList;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import java.util.AbstractMap;
import java.util.Iterator;
import com.google.common.collect.Maps;
import java.math.BigInteger;
import com.google.api.client.util.DateTime;
import com.google.common.collect.Lists;
import com.google.api.services.storage.model.ObjectAccessControl;
import java.util.Objects;
import com.google.api.core.BetaApi;
import java.util.Collections;
import com.google.common.io.BaseEncoding;
import com.google.api.client.util.Data;
import com.google.common.base.MoreObjects;
import java.util.Map;
import java.util.List;
import com.google.api.services.storage.model.StorageObject;
import com.google.common.base.Function;
import java.io.Serializable;

public class BlobInfo implements Serializable
{
    static final Function<BlobInfo, StorageObject> INFO_TO_PB_FUNCTION;
    private static final long serialVersionUID = -5625857076205028976L;
    private final BlobId blobId;
    private final String generatedId;
    private final String selfLink;
    private final String cacheControl;
    private final List<Acl> acl;
    private final Acl.Entity owner;
    private final Long size;
    private final String etag;
    private final String md5;
    private final String crc32c;
    private final String mediaLink;
    private final Map<String, String> metadata;
    private final Long metageneration;
    private final Long deleteTime;
    private final Long updateTime;
    private final Long createTime;
    private final String contentType;
    private final String contentEncoding;
    private final String contentDisposition;
    private final String contentLanguage;
    private final StorageClass storageClass;
    private final Integer componentCount;
    private final boolean isDirectory;
    private final CustomerEncryption customerEncryption;
    private final String kmsKeyName;
    private final Boolean eventBasedHold;
    private final Boolean temporaryHold;
    private final Long retentionExpirationTime;
    
    BlobInfo(final BuilderImpl builderImpl) {
        super();
        this.blobId = builderImpl.blobId;
        this.generatedId = builderImpl.generatedId;
        this.cacheControl = builderImpl.cacheControl;
        this.contentEncoding = builderImpl.contentEncoding;
        this.contentType = builderImpl.contentType;
        this.contentDisposition = builderImpl.contentDisposition;
        this.contentLanguage = builderImpl.contentLanguage;
        this.componentCount = builderImpl.componentCount;
        this.customerEncryption = builderImpl.customerEncryption;
        this.acl = builderImpl.acl;
        this.owner = builderImpl.owner;
        this.size = builderImpl.size;
        this.etag = builderImpl.etag;
        this.selfLink = builderImpl.selfLink;
        this.md5 = builderImpl.md5;
        this.crc32c = builderImpl.crc32c;
        this.mediaLink = builderImpl.mediaLink;
        this.metadata = builderImpl.metadata;
        this.metageneration = builderImpl.metageneration;
        this.deleteTime = builderImpl.deleteTime;
        this.updateTime = builderImpl.updateTime;
        this.createTime = builderImpl.createTime;
        this.isDirectory = MoreObjects.<Boolean>firstNonNull(builderImpl.isDirectory, Boolean.FALSE);
        this.storageClass = builderImpl.storageClass;
        this.kmsKeyName = builderImpl.kmsKeyName;
        this.eventBasedHold = builderImpl.eventBasedHold;
        this.temporaryHold = builderImpl.temporaryHold;
        this.retentionExpirationTime = builderImpl.retentionExpirationTime;
    }
    
    public BlobId getBlobId() {
        return this.blobId;
    }
    
    public String getBucket() {
        return this.getBlobId().getBucket();
    }
    
    public String getGeneratedId() {
        return this.generatedId;
    }
    
    public String getName() {
        return this.getBlobId().getName();
    }
    
    public String getCacheControl() {
        return Data.isNull(this.cacheControl) ? null : this.cacheControl;
    }
    
    public List<Acl> getAcl() {
        return this.acl;
    }
    
    public Acl.Entity getOwner() {
        return this.owner;
    }
    
    public Long getSize() {
        return this.size;
    }
    
    public String getContentType() {
        return Data.isNull(this.contentType) ? null : this.contentType;
    }
    
    public String getContentEncoding() {
        return Data.isNull(this.contentEncoding) ? null : this.contentEncoding;
    }
    
    public String getContentDisposition() {
        return Data.isNull(this.contentDisposition) ? null : this.contentDisposition;
    }
    
    public String getContentLanguage() {
        return Data.isNull(this.contentLanguage) ? null : this.contentLanguage;
    }
    
    public Integer getComponentCount() {
        return this.componentCount;
    }
    
    public String getEtag() {
        return this.etag;
    }
    
    public String getSelfLink() {
        return this.selfLink;
    }
    
    public String getMd5() {
        return Data.isNull(this.md5) ? null : this.md5;
    }
    
    public String getMd5ToHexString() {
        if (this.md5 == null) {
            return null;
        }
        final byte[] decode = BaseEncoding.base64().decode((CharSequence)this.md5);
        final StringBuilder sb = new StringBuilder();
        final byte[] array = decode;
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(String.format("%02x", array[i] & 0xFF));
        }
        return sb.toString();
    }
    
    public String getCrc32c() {
        return Data.isNull(this.crc32c) ? null : this.crc32c;
    }
    
    public String getCrc32cToHexString() {
        if (this.crc32c == null) {
            return null;
        }
        final byte[] decode = BaseEncoding.base64().decode((CharSequence)this.crc32c);
        final StringBuilder sb = new StringBuilder();
        final byte[] array = decode;
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(String.format("%02x", array[i] & 0xFF));
        }
        return sb.toString();
    }
    
    public String getMediaLink() {
        return this.mediaLink;
    }
    
    public Map<String, String> getMetadata() {
        return (this.metadata == null || Data.isNull(this.metadata)) ? null : Collections.<String, String>unmodifiableMap((Map<? extends String, ? extends String>)this.metadata);
    }
    
    public Long getGeneration() {
        return this.getBlobId().getGeneration();
    }
    
    public Long getMetageneration() {
        return this.metageneration;
    }
    
    public Long getDeleteTime() {
        return this.deleteTime;
    }
    
    public Long getUpdateTime() {
        return this.updateTime;
    }
    
    public Long getCreateTime() {
        return this.createTime;
    }
    
    public boolean isDirectory() {
        return this.isDirectory;
    }
    
    public CustomerEncryption getCustomerEncryption() {
        return this.customerEncryption;
    }
    
    public StorageClass getStorageClass() {
        return this.storageClass;
    }
    
    public String getKmsKeyName() {
        return this.kmsKeyName;
    }
    
    @BetaApi
    public Boolean getEventBasedHold() {
        return Data.isNull(this.eventBasedHold) ? null : this.eventBasedHold;
    }
    
    @BetaApi
    public Boolean getTemporaryHold() {
        return Data.isNull(this.temporaryHold) ? null : this.temporaryHold;
    }
    
    @BetaApi
    public Long getRetentionExpirationTime() {
        return Data.isNull(this.retentionExpirationTime) ? null : this.retentionExpirationTime;
    }
    
    public Builder toBuilder() {
        return new BuilderImpl(this);
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("bucket", this.getBucket()).add("name", this.getName()).add("generation", this.getGeneration()).add("size", this.getSize()).add("content-type", this.getContentType()).add("metadata", this.getMetadata()).toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.blobId);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o != null && o.getClass().equals(BlobInfo.class) && Objects.equals(this.toPb(), ((BlobInfo)o).toPb()));
    }
    
    StorageObject toPb() {
        final StorageObject pb = this.blobId.toPb();
        if (this.acl != null) {
            pb.setAcl((List)Lists.<Acl, Object>transform(this.acl, (Function<? super Acl, ?>)new Function<Acl, ObjectAccessControl>() {
                final /* synthetic */ BlobInfo this$0;
                
                BlobInfo$2() {
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
        if (this.deleteTime != null) {
            pb.setTimeDeleted(new DateTime(this.deleteTime));
        }
        if (this.updateTime != null) {
            pb.setUpdated(new DateTime(this.updateTime));
        }
        if (this.createTime != null) {
            pb.setTimeCreated(new DateTime(this.createTime));
        }
        if (this.size != null) {
            pb.setSize(BigInteger.valueOf(this.size));
        }
        if (this.owner != null) {
            pb.setOwner(new StorageObject.Owner().setEntity(this.owner.toPb()));
        }
        if (this.storageClass != null) {
            pb.setStorageClass(this.storageClass.toString());
        }
        Map<String, String> metadata = this.metadata;
        if (this.metadata != null && !Data.isNull(this.metadata)) {
            metadata = (Map<String, String>)Maps.newHashMapWithExpectedSize(this.metadata.size());
            for (final Map.Entry<String, String> entry : this.metadata.entrySet()) {
                metadata.put(entry.getKey(), (String)MoreObjects.<V>firstNonNull(entry.getValue(), Data.<V>nullOf(String.class)));
            }
        }
        if (this.customerEncryption != null) {
            pb.setCustomerEncryption(this.customerEncryption.toPb());
        }
        if (this.retentionExpirationTime != null) {
            pb.setRetentionExpirationTime(new DateTime(this.retentionExpirationTime));
        }
        pb.setKmsKeyName(this.kmsKeyName);
        pb.setEventBasedHold(this.eventBasedHold);
        pb.setTemporaryHold(this.temporaryHold);
        pb.setMetadata((Map)metadata);
        pb.setCacheControl(this.cacheControl);
        pb.setContentEncoding(this.contentEncoding);
        pb.setCrc32c(this.crc32c);
        pb.setContentType(this.contentType);
        pb.setMd5Hash(this.md5);
        pb.setMediaLink(this.mediaLink);
        pb.setMetageneration(this.metageneration);
        pb.setContentDisposition(this.contentDisposition);
        pb.setComponentCount(this.componentCount);
        pb.setContentLanguage(this.contentLanguage);
        pb.setEtag(this.etag);
        pb.setId(this.generatedId);
        pb.setSelfLink(this.selfLink);
        return pb;
    }
    
    public static Builder newBuilder(final BucketInfo bucketInfo, final String s) {
        return newBuilder(bucketInfo.getName(), s);
    }
    
    public static Builder newBuilder(final String s, final String s2) {
        return newBuilder(BlobId.of(s, s2));
    }
    
    public static Builder newBuilder(final BucketInfo bucketInfo, final String s, final Long n) {
        return newBuilder(bucketInfo.getName(), s, n);
    }
    
    public static Builder newBuilder(final String s, final String s2, final Long n) {
        return newBuilder(BlobId.of(s, s2, n));
    }
    
    public static Builder newBuilder(final BlobId blobId) {
        return new BuilderImpl(blobId);
    }
    
    static BlobInfo fromPb(final StorageObject storageObject) {
        final Builder builder = newBuilder(BlobId.fromPb(storageObject));
        if (storageObject.getCacheControl() != null) {
            builder.setCacheControl(storageObject.getCacheControl());
        }
        if (storageObject.getContentEncoding() != null) {
            builder.setContentEncoding(storageObject.getContentEncoding());
        }
        if (storageObject.getCrc32c() != null) {
            builder.setCrc32c(storageObject.getCrc32c());
        }
        if (storageObject.getContentType() != null) {
            builder.setContentType(storageObject.getContentType());
        }
        if (storageObject.getMd5Hash() != null) {
            builder.setMd5(storageObject.getMd5Hash());
        }
        if (storageObject.getMediaLink() != null) {
            builder.setMediaLink(storageObject.getMediaLink());
        }
        if (storageObject.getMetageneration() != null) {
            builder.setMetageneration(storageObject.getMetageneration());
        }
        if (storageObject.getContentDisposition() != null) {
            builder.setContentDisposition(storageObject.getContentDisposition());
        }
        if (storageObject.getComponentCount() != null) {
            builder.setComponentCount(storageObject.getComponentCount());
        }
        if (storageObject.getContentLanguage() != null) {
            builder.setContentLanguage(storageObject.getContentLanguage());
        }
        if (storageObject.getEtag() != null) {
            builder.setEtag(storageObject.getEtag());
        }
        if (storageObject.getId() != null) {
            builder.setGeneratedId(storageObject.getId());
        }
        if (storageObject.getSelfLink() != null) {
            builder.setSelfLink(storageObject.getSelfLink());
        }
        if (storageObject.getMetadata() != null) {
            builder.setMetadata(storageObject.getMetadata());
        }
        if (storageObject.getTimeDeleted() != null) {
            builder.setDeleteTime(storageObject.getTimeDeleted().getValue());
        }
        if (storageObject.getUpdated() != null) {
            builder.setUpdateTime(storageObject.getUpdated().getValue());
        }
        if (storageObject.getTimeCreated() != null) {
            builder.setCreateTime(storageObject.getTimeCreated().getValue());
        }
        if (storageObject.getSize() != null) {
            builder.setSize(storageObject.getSize().longValue());
        }
        if (storageObject.getOwner() != null) {
            builder.setOwner(Acl.Entity.fromPb(storageObject.getOwner().getEntity()));
        }
        if (storageObject.getAcl() != null) {
            builder.setAcl(Lists.<Object, Acl>transform((List<Object>)storageObject.getAcl(), (Function<? super Object, ? extends Acl>)new Function<ObjectAccessControl, Acl>() {
                BlobInfo$3() {
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
        if (storageObject.containsKey((Object)"isDirectory")) {
            builder.setIsDirectory(Boolean.TRUE);
        }
        if (storageObject.getCustomerEncryption() != null) {
            builder.setCustomerEncryption(CustomerEncryption.fromPb(storageObject.getCustomerEncryption()));
        }
        if (storageObject.getStorageClass() != null) {
            builder.setStorageClass(StorageClass.valueOf(storageObject.getStorageClass()));
        }
        if (storageObject.getKmsKeyName() != null) {
            builder.setKmsKeyName(storageObject.getKmsKeyName());
        }
        if (storageObject.getEventBasedHold() != null) {
            builder.setEventBasedHold(storageObject.getEventBasedHold());
        }
        if (storageObject.getTemporaryHold() != null) {
            builder.setTemporaryHold(storageObject.getTemporaryHold());
        }
        if (storageObject.getRetentionExpirationTime() != null) {
            builder.setRetentionExpirationTime(storageObject.getRetentionExpirationTime().getValue());
        }
        return builder.build();
    }
    
    static /* synthetic */ BlobId access$000(final BlobInfo blobInfo) {
        return blobInfo.blobId;
    }
    
    static /* synthetic */ String access$100(final BlobInfo blobInfo) {
        return blobInfo.generatedId;
    }
    
    static /* synthetic */ String access$200(final BlobInfo blobInfo) {
        return blobInfo.cacheControl;
    }
    
    static /* synthetic */ String access$300(final BlobInfo blobInfo) {
        return blobInfo.contentEncoding;
    }
    
    static /* synthetic */ String access$400(final BlobInfo blobInfo) {
        return blobInfo.contentType;
    }
    
    static /* synthetic */ String access$500(final BlobInfo blobInfo) {
        return blobInfo.contentDisposition;
    }
    
    static /* synthetic */ String access$600(final BlobInfo blobInfo) {
        return blobInfo.contentLanguage;
    }
    
    static /* synthetic */ Integer access$700(final BlobInfo blobInfo) {
        return blobInfo.componentCount;
    }
    
    static /* synthetic */ CustomerEncryption access$800(final BlobInfo blobInfo) {
        return blobInfo.customerEncryption;
    }
    
    static /* synthetic */ List access$900(final BlobInfo blobInfo) {
        return blobInfo.acl;
    }
    
    static /* synthetic */ Acl.Entity access$1000(final BlobInfo blobInfo) {
        return blobInfo.owner;
    }
    
    static /* synthetic */ Long access$1100(final BlobInfo blobInfo) {
        return blobInfo.size;
    }
    
    static /* synthetic */ String access$1200(final BlobInfo blobInfo) {
        return blobInfo.etag;
    }
    
    static /* synthetic */ String access$1300(final BlobInfo blobInfo) {
        return blobInfo.selfLink;
    }
    
    static /* synthetic */ String access$1400(final BlobInfo blobInfo) {
        return blobInfo.md5;
    }
    
    static /* synthetic */ String access$1500(final BlobInfo blobInfo) {
        return blobInfo.crc32c;
    }
    
    static /* synthetic */ String access$1600(final BlobInfo blobInfo) {
        return blobInfo.mediaLink;
    }
    
    static /* synthetic */ Map access$1700(final BlobInfo blobInfo) {
        return blobInfo.metadata;
    }
    
    static /* synthetic */ Long access$1800(final BlobInfo blobInfo) {
        return blobInfo.metageneration;
    }
    
    static /* synthetic */ Long access$1900(final BlobInfo blobInfo) {
        return blobInfo.deleteTime;
    }
    
    static /* synthetic */ Long access$2000(final BlobInfo blobInfo) {
        return blobInfo.updateTime;
    }
    
    static /* synthetic */ Long access$2100(final BlobInfo blobInfo) {
        return blobInfo.createTime;
    }
    
    static /* synthetic */ boolean access$2200(final BlobInfo blobInfo) {
        return blobInfo.isDirectory;
    }
    
    static /* synthetic */ StorageClass access$2300(final BlobInfo blobInfo) {
        return blobInfo.storageClass;
    }
    
    static /* synthetic */ String access$2400(final BlobInfo blobInfo) {
        return blobInfo.kmsKeyName;
    }
    
    static /* synthetic */ Boolean access$2500(final BlobInfo blobInfo) {
        return blobInfo.eventBasedHold;
    }
    
    static /* synthetic */ Boolean access$2600(final BlobInfo blobInfo) {
        return blobInfo.temporaryHold;
    }
    
    static /* synthetic */ Long access$2700(final BlobInfo blobInfo) {
        return blobInfo.retentionExpirationTime;
    }
    
    static {
        INFO_TO_PB_FUNCTION = new Function<BlobInfo, StorageObject>() {
            BlobInfo$1() {
                super();
            }
            
            @Override
            public StorageObject apply(final BlobInfo blobInfo) {
                return blobInfo.toPb();
            }
            
            @Override
            public /* bridge */ Object apply(final Object o) {
                return this.apply((BlobInfo)o);
            }
        };
    }
}
