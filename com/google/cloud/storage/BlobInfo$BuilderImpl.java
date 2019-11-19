package com.google.cloud.storage;

import java.util.HashMap;
import com.google.common.io.BaseEncoding;
import java.util.Arrays;
import java.math.BigInteger;
import java.util.Collection;
import com.google.common.collect.ImmutableList;
import com.google.common.base.MoreObjects;
import com.google.api.client.util.Data;
import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.List;

static final class BuilderImpl extends Builder
{
    private BlobId blobId;
    private String generatedId;
    private String contentType;
    private String contentEncoding;
    private String contentDisposition;
    private String contentLanguage;
    private Integer componentCount;
    private String cacheControl;
    private List<Acl> acl;
    private Acl.Entity owner;
    private Long size;
    private String etag;
    private String selfLink;
    private String md5;
    private String crc32c;
    private String mediaLink;
    private Map<String, String> metadata;
    private Long metageneration;
    private Long deleteTime;
    private Long updateTime;
    private Long createTime;
    private Boolean isDirectory;
    private CustomerEncryption customerEncryption;
    private StorageClass storageClass;
    private String kmsKeyName;
    private Boolean eventBasedHold;
    private Boolean temporaryHold;
    private Long retentionExpirationTime;
    
    BuilderImpl(final BlobId blobId) {
        super();
        this.blobId = blobId;
    }
    
    BuilderImpl(final BlobInfo blobInfo) {
        super();
        this.blobId = BlobInfo.access$000(blobInfo);
        this.generatedId = BlobInfo.access$100(blobInfo);
        this.cacheControl = BlobInfo.access$200(blobInfo);
        this.contentEncoding = BlobInfo.access$300(blobInfo);
        this.contentType = BlobInfo.access$400(blobInfo);
        this.contentDisposition = BlobInfo.access$500(blobInfo);
        this.contentLanguage = BlobInfo.access$600(blobInfo);
        this.componentCount = BlobInfo.access$700(blobInfo);
        this.customerEncryption = BlobInfo.access$800(blobInfo);
        this.acl = (List<Acl>)BlobInfo.access$900(blobInfo);
        this.owner = BlobInfo.access$1000(blobInfo);
        this.size = BlobInfo.access$1100(blobInfo);
        this.etag = BlobInfo.access$1200(blobInfo);
        this.selfLink = BlobInfo.access$1300(blobInfo);
        this.md5 = BlobInfo.access$1400(blobInfo);
        this.crc32c = BlobInfo.access$1500(blobInfo);
        this.mediaLink = BlobInfo.access$1600(blobInfo);
        this.metadata = (Map<String, String>)BlobInfo.access$1700(blobInfo);
        this.metageneration = BlobInfo.access$1800(blobInfo);
        this.deleteTime = BlobInfo.access$1900(blobInfo);
        this.updateTime = BlobInfo.access$2000(blobInfo);
        this.createTime = BlobInfo.access$2100(blobInfo);
        this.isDirectory = BlobInfo.access$2200(blobInfo);
        this.storageClass = BlobInfo.access$2300(blobInfo);
        this.kmsKeyName = BlobInfo.access$2400(blobInfo);
        this.eventBasedHold = BlobInfo.access$2500(blobInfo);
        this.temporaryHold = BlobInfo.access$2600(blobInfo);
        this.retentionExpirationTime = BlobInfo.access$2700(blobInfo);
    }
    
    @Override
    public Builder setBlobId(final BlobId reference) {
        this.blobId = Preconditions.<BlobId>checkNotNull(reference);
        return this;
    }
    
    @Override
    Builder setGeneratedId(final String generatedId) {
        this.generatedId = generatedId;
        return this;
    }
    
    @Override
    public Builder setContentType(final String first) {
        this.contentType = MoreObjects.<String>firstNonNull(first, Data.<String>nullOf(String.class));
        return this;
    }
    
    @Override
    public Builder setContentDisposition(final String first) {
        this.contentDisposition = MoreObjects.<String>firstNonNull(first, Data.<String>nullOf(String.class));
        return this;
    }
    
    @Override
    public Builder setContentLanguage(final String first) {
        this.contentLanguage = MoreObjects.<String>firstNonNull(first, Data.<String>nullOf(String.class));
        return this;
    }
    
    @Override
    public Builder setContentEncoding(final String first) {
        this.contentEncoding = MoreObjects.<String>firstNonNull(first, Data.<String>nullOf(String.class));
        return this;
    }
    
    @Override
    Builder setComponentCount(final Integer componentCount) {
        this.componentCount = componentCount;
        return this;
    }
    
    @Override
    public Builder setCacheControl(final String first) {
        this.cacheControl = MoreObjects.<String>firstNonNull(first, Data.<String>nullOf(String.class));
        return this;
    }
    
    @Override
    public Builder setAcl(final List<Acl> elements) {
        this.acl = (List<Acl>)((elements != null) ? ImmutableList.<Object>copyOf((Collection<?>)elements) : null);
        return this;
    }
    
    @Override
    Builder setOwner(final Acl.Entity owner) {
        this.owner = owner;
        return this;
    }
    
    @Override
    Builder setSize(final Long size) {
        this.size = size;
        return this;
    }
    
    @Override
    Builder setEtag(final String etag) {
        this.etag = etag;
        return this;
    }
    
    @Override
    Builder setSelfLink(final String selfLink) {
        this.selfLink = selfLink;
        return this;
    }
    
    @Override
    public Builder setMd5(final String first) {
        this.md5 = MoreObjects.<String>firstNonNull(first, Data.<String>nullOf(String.class));
        return this;
    }
    
    @Override
    public Builder setMd5FromHexString(final String s) {
        if (s == null) {
            return this;
        }
        byte[] array = new BigInteger(s, 16).toByteArray();
        final int n = array.length - s.length() / 2;
        if (n > 0) {
            array = Arrays.copyOfRange(array, n, array.length);
        }
        this.md5 = BaseEncoding.base64().encode(array);
        return this;
    }
    
    @Override
    public Builder setCrc32c(final String first) {
        this.crc32c = MoreObjects.<String>firstNonNull(first, Data.<String>nullOf(String.class));
        return this;
    }
    
    @Override
    public Builder setCrc32cFromHexString(final String s) {
        if (s == null) {
            return this;
        }
        byte[] array = new BigInteger(s, 16).toByteArray();
        final int n = array.length - s.length() / 2;
        if (n > 0) {
            array = Arrays.copyOfRange(array, n, array.length);
        }
        this.crc32c = BaseEncoding.base64().encode(array);
        return this;
    }
    
    @Override
    Builder setMediaLink(final String mediaLink) {
        this.mediaLink = mediaLink;
        return this;
    }
    
    @Override
    public Builder setMetadata(final Map<String, String> map) {
        if (map != null) {
            this.metadata = new HashMap<String, String>(map);
        }
        else {
            this.metadata = Data.<Map<String, String>>nullOf(ImmutableEmptyMap.class);
        }
        return this;
    }
    
    @Override
    public Builder setStorageClass(final StorageClass storageClass) {
        this.storageClass = storageClass;
        return this;
    }
    
    @Override
    Builder setMetageneration(final Long metageneration) {
        this.metageneration = metageneration;
        return this;
    }
    
    @Override
    Builder setDeleteTime(final Long deleteTime) {
        this.deleteTime = deleteTime;
        return this;
    }
    
    @Override
    Builder setUpdateTime(final Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }
    
    @Override
    Builder setCreateTime(final Long createTime) {
        this.createTime = createTime;
        return this;
    }
    
    @Override
    Builder setIsDirectory(final boolean b) {
        this.isDirectory = b;
        return this;
    }
    
    @Override
    Builder setCustomerEncryption(final CustomerEncryption customerEncryption) {
        this.customerEncryption = customerEncryption;
        return this;
    }
    
    @Override
    Builder setKmsKeyName(final String kmsKeyName) {
        this.kmsKeyName = kmsKeyName;
        return this;
    }
    
    @Override
    public Builder setEventBasedHold(final Boolean eventBasedHold) {
        this.eventBasedHold = eventBasedHold;
        return this;
    }
    
    @Override
    public Builder setTemporaryHold(final Boolean temporaryHold) {
        this.temporaryHold = temporaryHold;
        return this;
    }
    
    @Override
    Builder setRetentionExpirationTime(final Long retentionExpirationTime) {
        this.retentionExpirationTime = retentionExpirationTime;
        return this;
    }
    
    @Override
    public BlobInfo build() {
        Preconditions.<BlobId>checkNotNull(this.blobId);
        return new BlobInfo(this);
    }
    
    static /* synthetic */ BlobId access$2800(final BuilderImpl builderImpl) {
        return builderImpl.blobId;
    }
    
    static /* synthetic */ String access$2900(final BuilderImpl builderImpl) {
        return builderImpl.generatedId;
    }
    
    static /* synthetic */ String access$3000(final BuilderImpl builderImpl) {
        return builderImpl.cacheControl;
    }
    
    static /* synthetic */ String access$3100(final BuilderImpl builderImpl) {
        return builderImpl.contentEncoding;
    }
    
    static /* synthetic */ String access$3200(final BuilderImpl builderImpl) {
        return builderImpl.contentType;
    }
    
    static /* synthetic */ String access$3300(final BuilderImpl builderImpl) {
        return builderImpl.contentDisposition;
    }
    
    static /* synthetic */ String access$3400(final BuilderImpl builderImpl) {
        return builderImpl.contentLanguage;
    }
    
    static /* synthetic */ Integer access$3500(final BuilderImpl builderImpl) {
        return builderImpl.componentCount;
    }
    
    static /* synthetic */ CustomerEncryption access$3600(final BuilderImpl builderImpl) {
        return builderImpl.customerEncryption;
    }
    
    static /* synthetic */ List access$3700(final BuilderImpl builderImpl) {
        return builderImpl.acl;
    }
    
    static /* synthetic */ Acl.Entity access$3800(final BuilderImpl builderImpl) {
        return builderImpl.owner;
    }
    
    static /* synthetic */ Long access$3900(final BuilderImpl builderImpl) {
        return builderImpl.size;
    }
    
    static /* synthetic */ String access$4000(final BuilderImpl builderImpl) {
        return builderImpl.etag;
    }
    
    static /* synthetic */ String access$4100(final BuilderImpl builderImpl) {
        return builderImpl.selfLink;
    }
    
    static /* synthetic */ String access$4200(final BuilderImpl builderImpl) {
        return builderImpl.md5;
    }
    
    static /* synthetic */ String access$4300(final BuilderImpl builderImpl) {
        return builderImpl.crc32c;
    }
    
    static /* synthetic */ String access$4400(final BuilderImpl builderImpl) {
        return builderImpl.mediaLink;
    }
    
    static /* synthetic */ Map access$4500(final BuilderImpl builderImpl) {
        return builderImpl.metadata;
    }
    
    static /* synthetic */ Long access$4600(final BuilderImpl builderImpl) {
        return builderImpl.metageneration;
    }
    
    static /* synthetic */ Long access$4700(final BuilderImpl builderImpl) {
        return builderImpl.deleteTime;
    }
    
    static /* synthetic */ Long access$4800(final BuilderImpl builderImpl) {
        return builderImpl.updateTime;
    }
    
    static /* synthetic */ Long access$4900(final BuilderImpl builderImpl) {
        return builderImpl.createTime;
    }
    
    static /* synthetic */ Boolean access$5000(final BuilderImpl builderImpl) {
        return builderImpl.isDirectory;
    }
    
    static /* synthetic */ StorageClass access$5100(final BuilderImpl builderImpl) {
        return builderImpl.storageClass;
    }
    
    static /* synthetic */ String access$5200(final BuilderImpl builderImpl) {
        return builderImpl.kmsKeyName;
    }
    
    static /* synthetic */ Boolean access$5300(final BuilderImpl builderImpl) {
        return builderImpl.eventBasedHold;
    }
    
    static /* synthetic */ Boolean access$5400(final BuilderImpl builderImpl) {
        return builderImpl.temporaryHold;
    }
    
    static /* synthetic */ Long access$5500(final BuilderImpl builderImpl) {
        return builderImpl.retentionExpirationTime;
    }
}
