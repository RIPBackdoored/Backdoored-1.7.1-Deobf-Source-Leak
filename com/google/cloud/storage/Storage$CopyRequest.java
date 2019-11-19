package com.google.cloud.storage;

import com.google.common.collect.Iterables;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Collection;
import com.google.common.collect.ImmutableList;
import com.google.common.base.Preconditions;
import java.util.List;
import java.io.Serializable;

public static class CopyRequest implements Serializable
{
    private static final long serialVersionUID = -4498650529476219937L;
    private final BlobId source;
    private final List<BlobSourceOption> sourceOptions;
    private final boolean overrideInfo;
    private final BlobInfo target;
    private final List<BlobTargetOption> targetOptions;
    private final Long megabytesCopiedPerChunk;
    
    private CopyRequest(final Builder builder) {
        super();
        this.source = Preconditions.<BlobId>checkNotNull(builder.source);
        this.sourceOptions = (List<BlobSourceOption>)ImmutableList.<Object>copyOf((Collection<?>)builder.sourceOptions);
        this.overrideInfo = builder.overrideInfo;
        this.target = Preconditions.<BlobInfo>checkNotNull(builder.target);
        this.targetOptions = (List<BlobTargetOption>)ImmutableList.<Object>copyOf((Collection<?>)builder.targetOptions);
        this.megabytesCopiedPerChunk = builder.megabytesCopiedPerChunk;
    }
    
    public BlobId getSource() {
        return this.source;
    }
    
    public List<BlobSourceOption> getSourceOptions() {
        return this.sourceOptions;
    }
    
    public BlobInfo getTarget() {
        return this.target;
    }
    
    public boolean overrideInfo() {
        return this.overrideInfo;
    }
    
    public List<BlobTargetOption> getTargetOptions() {
        return this.targetOptions;
    }
    
    public Long getMegabytesCopiedPerChunk() {
        return this.megabytesCopiedPerChunk;
    }
    
    public static CopyRequest of(final String s, final String s2, final BlobInfo blobInfo) {
        return newBuilder().setSource(s, s2).setTarget(blobInfo, new BlobTargetOption[0]).build();
    }
    
    public static CopyRequest of(final BlobId source, final BlobInfo blobInfo) {
        return newBuilder().setSource(source).setTarget(blobInfo, new BlobTargetOption[0]).build();
    }
    
    public static CopyRequest of(final String s, final String s2, final String s3) {
        return newBuilder().setSource(s, s2).setTarget(BlobId.of(s, s3)).build();
    }
    
    public static CopyRequest of(final String s, final String s2, final BlobId target) {
        return newBuilder().setSource(s, s2).setTarget(target).build();
    }
    
    public static CopyRequest of(final BlobId source, final String s) {
        return newBuilder().setSource(source).setTarget(BlobId.of(source.getBucket(), s)).build();
    }
    
    public static CopyRequest of(final BlobId source, final BlobId target) {
        return newBuilder().setSource(source).setTarget(target).build();
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    CopyRequest(final Builder builder, final Storage$1 object) {
        this(builder);
    }
}
