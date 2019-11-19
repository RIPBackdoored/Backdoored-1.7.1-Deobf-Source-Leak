package com.google.cloud.storage;

import java.util.Map;
import java.util.List;

public static class Builder extends BlobInfo.Builder
{
    private final Storage storage;
    private final BuilderImpl infoBuilder;
    
    Builder(final Blob blob) {
        super();
        this.storage = blob.getStorage();
        this.infoBuilder = new BuilderImpl(blob);
    }
    
    @Override
    public Builder setBlobId(final BlobId blobId) {
        this.infoBuilder.setBlobId(blobId);
        return this;
    }
    
    @Override
    Builder setGeneratedId(final String generatedId) {
        this.infoBuilder.setGeneratedId(generatedId);
        return this;
    }
    
    @Override
    public Builder setContentType(final String contentType) {
        this.infoBuilder.setContentType(contentType);
        return this;
    }
    
    @Override
    public Builder setContentDisposition(final String contentDisposition) {
        this.infoBuilder.setContentDisposition(contentDisposition);
        return this;
    }
    
    @Override
    public Builder setContentLanguage(final String contentLanguage) {
        this.infoBuilder.setContentLanguage(contentLanguage);
        return this;
    }
    
    @Override
    public Builder setContentEncoding(final String contentEncoding) {
        this.infoBuilder.setContentEncoding(contentEncoding);
        return this;
    }
    
    @Override
    Builder setComponentCount(final Integer componentCount) {
        this.infoBuilder.setComponentCount(componentCount);
        return this;
    }
    
    @Override
    public Builder setCacheControl(final String cacheControl) {
        this.infoBuilder.setCacheControl(cacheControl);
        return this;
    }
    
    @Override
    public Builder setAcl(final List<Acl> acl) {
        this.infoBuilder.setAcl(acl);
        return this;
    }
    
    @Override
    Builder setOwner(final Acl.Entity owner) {
        this.infoBuilder.setOwner(owner);
        return this;
    }
    
    @Override
    Builder setSize(final Long size) {
        this.infoBuilder.setSize(size);
        return this;
    }
    
    @Override
    Builder setEtag(final String etag) {
        this.infoBuilder.setEtag(etag);
        return this;
    }
    
    @Override
    Builder setSelfLink(final String selfLink) {
        this.infoBuilder.setSelfLink(selfLink);
        return this;
    }
    
    @Override
    public Builder setMd5(final String md5) {
        this.infoBuilder.setMd5(md5);
        return this;
    }
    
    @Override
    public Builder setMd5FromHexString(final String md5FromHexString) {
        this.infoBuilder.setMd5FromHexString(md5FromHexString);
        return this;
    }
    
    @Override
    public Builder setCrc32c(final String crc32c) {
        this.infoBuilder.setCrc32c(crc32c);
        return this;
    }
    
    @Override
    public Builder setCrc32cFromHexString(final String crc32cFromHexString) {
        this.infoBuilder.setCrc32cFromHexString(crc32cFromHexString);
        return this;
    }
    
    @Override
    Builder setMediaLink(final String mediaLink) {
        this.infoBuilder.setMediaLink(mediaLink);
        return this;
    }
    
    @Override
    public Builder setMetadata(final Map<String, String> metadata) {
        this.infoBuilder.setMetadata(metadata);
        return this;
    }
    
    @Override
    public Builder setStorageClass(final StorageClass storageClass) {
        this.infoBuilder.setStorageClass(storageClass);
        return this;
    }
    
    @Override
    Builder setMetageneration(final Long metageneration) {
        this.infoBuilder.setMetageneration(metageneration);
        return this;
    }
    
    @Override
    Builder setDeleteTime(final Long deleteTime) {
        this.infoBuilder.setDeleteTime(deleteTime);
        return this;
    }
    
    @Override
    Builder setUpdateTime(final Long updateTime) {
        this.infoBuilder.setUpdateTime(updateTime);
        return this;
    }
    
    @Override
    Builder setCreateTime(final Long createTime) {
        this.infoBuilder.setCreateTime(createTime);
        return this;
    }
    
    @Override
    Builder setIsDirectory(final boolean isDirectory) {
        this.infoBuilder.setIsDirectory(isDirectory);
        return this;
    }
    
    @Override
    Builder setCustomerEncryption(final CustomerEncryption customerEncryption) {
        this.infoBuilder.setCustomerEncryption(customerEncryption);
        return this;
    }
    
    @Override
    Builder setKmsKeyName(final String kmsKeyName) {
        this.infoBuilder.setKmsKeyName(kmsKeyName);
        return this;
    }
    
    @Override
    public Builder setEventBasedHold(final Boolean eventBasedHold) {
        this.infoBuilder.setEventBasedHold(eventBasedHold);
        return this;
    }
    
    @Override
    public Builder setTemporaryHold(final Boolean temporaryHold) {
        this.infoBuilder.setTemporaryHold(temporaryHold);
        return this;
    }
    
    @Override
    Builder setRetentionExpirationTime(final Long retentionExpirationTime) {
        this.infoBuilder.setRetentionExpirationTime(retentionExpirationTime);
        return this;
    }
    
    @Override
    public Blob build() {
        return new Blob(this.storage, this.infoBuilder);
    }
    
    @Override
    public /* bridge */ BlobInfo build() {
        return this.build();
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setRetentionExpirationTime(final Long retentionExpirationTime) {
        return this.setRetentionExpirationTime(retentionExpirationTime);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setTemporaryHold(final Boolean temporaryHold) {
        return this.setTemporaryHold(temporaryHold);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setEventBasedHold(final Boolean eventBasedHold) {
        return this.setEventBasedHold(eventBasedHold);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setKmsKeyName(final String kmsKeyName) {
        return this.setKmsKeyName(kmsKeyName);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setCustomerEncryption(final CustomerEncryption customerEncryption) {
        return this.setCustomerEncryption(customerEncryption);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setIsDirectory(final boolean isDirectory) {
        return this.setIsDirectory(isDirectory);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setCreateTime(final Long createTime) {
        return this.setCreateTime(createTime);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setUpdateTime(final Long updateTime) {
        return this.setUpdateTime(updateTime);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setDeleteTime(final Long deleteTime) {
        return this.setDeleteTime(deleteTime);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setMetageneration(final Long metageneration) {
        return this.setMetageneration(metageneration);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setMetadata(final Map metadata) {
        return this.setMetadata(metadata);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setStorageClass(final StorageClass storageClass) {
        return this.setStorageClass(storageClass);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setMediaLink(final String mediaLink) {
        return this.setMediaLink(mediaLink);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setCrc32cFromHexString(final String crc32cFromHexString) {
        return this.setCrc32cFromHexString(crc32cFromHexString);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setCrc32c(final String crc32c) {
        return this.setCrc32c(crc32c);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setMd5FromHexString(final String md5FromHexString) {
        return this.setMd5FromHexString(md5FromHexString);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setMd5(final String md5) {
        return this.setMd5(md5);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setSelfLink(final String selfLink) {
        return this.setSelfLink(selfLink);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setEtag(final String etag) {
        return this.setEtag(etag);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setSize(final Long size) {
        return this.setSize(size);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setOwner(final Acl.Entity owner) {
        return this.setOwner(owner);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setAcl(final List acl) {
        return this.setAcl(acl);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setCacheControl(final String cacheControl) {
        return this.setCacheControl(cacheControl);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setComponentCount(final Integer componentCount) {
        return this.setComponentCount(componentCount);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setContentEncoding(final String contentEncoding) {
        return this.setContentEncoding(contentEncoding);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setContentLanguage(final String contentLanguage) {
        return this.setContentLanguage(contentLanguage);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setContentDisposition(final String contentDisposition) {
        return this.setContentDisposition(contentDisposition);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setContentType(final String contentType) {
        return this.setContentType(contentType);
    }
    
    @Override
    /* bridge */ BlobInfo.Builder setGeneratedId(final String generatedId) {
        return this.setGeneratedId(generatedId);
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder setBlobId(final BlobId blobId) {
        return this.setBlobId(blobId);
    }
}
