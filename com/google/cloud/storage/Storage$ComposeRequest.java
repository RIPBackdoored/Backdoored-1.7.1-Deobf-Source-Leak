package com.google.cloud.storage;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import java.util.Collections;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Collection;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.io.Serializable;

public static class ComposeRequest implements Serializable
{
    private static final long serialVersionUID = -7385681353748590911L;
    private final List<SourceBlob> sourceBlobs;
    private final BlobInfo target;
    private final List<BlobTargetOption> targetOptions;
    
    private ComposeRequest(final Builder builder) {
        super();
        this.sourceBlobs = (List<SourceBlob>)ImmutableList.<Object>copyOf((Collection<?>)builder.sourceBlobs);
        this.target = builder.target;
        this.targetOptions = (List<BlobTargetOption>)ImmutableList.<Object>copyOf((Collection<?>)builder.targetOptions);
    }
    
    public List<SourceBlob> getSourceBlobs() {
        return this.sourceBlobs;
    }
    
    public BlobInfo getTarget() {
        return this.target;
    }
    
    public List<BlobTargetOption> getTargetOptions() {
        return this.targetOptions;
    }
    
    public static ComposeRequest of(final Iterable<String> iterable, final BlobInfo target) {
        return newBuilder().setTarget(target).addSource(iterable).build();
    }
    
    public static ComposeRequest of(final String s, final Iterable<String> iterable, final String s2) {
        return of(iterable, BlobInfo.newBuilder(BlobId.of(s, s2)).build());
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    ComposeRequest(final Builder builder, final Storage$1 object) {
        this(builder);
    }
}
