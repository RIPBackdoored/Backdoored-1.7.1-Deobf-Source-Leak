package com.google.cloud.storage;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public static class Builder
{
    private final Set<BlobSourceOption> sourceOptions;
    private final Set<BlobTargetOption> targetOptions;
    private BlobId source;
    private boolean overrideInfo;
    private BlobInfo target;
    private Long megabytesCopiedPerChunk;
    
    public Builder() {
        super();
        this.sourceOptions = new LinkedHashSet<BlobSourceOption>();
        this.targetOptions = new LinkedHashSet<BlobTargetOption>();
    }
    
    public Builder setSource(final String s, final String s2) {
        this.source = BlobId.of(s, s2);
        return this;
    }
    
    public Builder setSource(final BlobId source) {
        this.source = source;
        return this;
    }
    
    public Builder setSourceOptions(final BlobSourceOption... array) {
        Collections.<BlobSourceOption>addAll(this.sourceOptions, array);
        return this;
    }
    
    public Builder setSourceOptions(final Iterable<BlobSourceOption> elementsToAdd) {
        Iterables.<BlobSourceOption>addAll(this.sourceOptions, elementsToAdd);
        return this;
    }
    
    public Builder setTarget(final BlobId blobId) {
        this.overrideInfo = false;
        this.target = BlobInfo.newBuilder(blobId).build();
        return this;
    }
    
    public Builder setTarget(final BlobId blobId, final BlobTargetOption... array) {
        this.overrideInfo = false;
        this.target = BlobInfo.newBuilder(blobId).build();
        Collections.<BlobTargetOption>addAll(this.targetOptions, array);
        return this;
    }
    
    public Builder setTarget(final BlobInfo reference, final BlobTargetOption... array) {
        this.overrideInfo = true;
        this.target = Preconditions.<BlobInfo>checkNotNull(reference);
        Collections.<BlobTargetOption>addAll(this.targetOptions, array);
        return this;
    }
    
    public Builder setTarget(final BlobInfo reference, final Iterable<BlobTargetOption> elementsToAdd) {
        this.overrideInfo = true;
        this.target = Preconditions.<BlobInfo>checkNotNull(reference);
        Iterables.<BlobTargetOption>addAll(this.targetOptions, elementsToAdd);
        return this;
    }
    
    public Builder setTarget(final BlobId blobId, final Iterable<BlobTargetOption> elementsToAdd) {
        this.overrideInfo = false;
        this.target = BlobInfo.newBuilder(blobId).build();
        Iterables.<BlobTargetOption>addAll(this.targetOptions, elementsToAdd);
        return this;
    }
    
    public Builder setMegabytesCopiedPerChunk(final Long megabytesCopiedPerChunk) {
        this.megabytesCopiedPerChunk = megabytesCopiedPerChunk;
        return this;
    }
    
    public CopyRequest build() {
        return new CopyRequest(this);
    }
    
    static /* synthetic */ BlobId access$700(final Builder builder) {
        return builder.source;
    }
    
    static /* synthetic */ Set access$800(final Builder builder) {
        return builder.sourceOptions;
    }
    
    static /* synthetic */ boolean access$900(final Builder builder) {
        return builder.overrideInfo;
    }
    
    static /* synthetic */ BlobInfo access$1000(final Builder builder) {
        return builder.target;
    }
    
    static /* synthetic */ Set access$1100(final Builder builder) {
        return builder.targetOptions;
    }
    
    static /* synthetic */ Long access$1200(final Builder builder) {
        return builder.megabytesCopiedPerChunk;
    }
}
