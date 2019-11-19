package com.google.cloud.storage;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;

public static class Builder
{
    private final List<SourceBlob> sourceBlobs;
    private final Set<BlobTargetOption> targetOptions;
    private BlobInfo target;
    
    public Builder() {
        super();
        this.sourceBlobs = new LinkedList<SourceBlob>();
        this.targetOptions = new LinkedHashSet<BlobTargetOption>();
    }
    
    public Builder addSource(final Iterable<String> iterable) {
        final Iterator<String> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.sourceBlobs.add(new SourceBlob(iterator.next()));
        }
        return this;
    }
    
    public Builder addSource(final String... array) {
        return this.addSource(Arrays.<String>asList(array));
    }
    
    public Builder addSource(final String s, final long n) {
        this.sourceBlobs.add(new SourceBlob(s, n));
        return this;
    }
    
    public Builder setTarget(final BlobInfo target) {
        this.target = target;
        return this;
    }
    
    public Builder setTargetOptions(final BlobTargetOption... array) {
        Collections.<BlobTargetOption>addAll(this.targetOptions, array);
        return this;
    }
    
    public Builder setTargetOptions(final Iterable<BlobTargetOption> elementsToAdd) {
        Iterables.<BlobTargetOption>addAll(this.targetOptions, elementsToAdd);
        return this;
    }
    
    public ComposeRequest build() {
        Preconditions.checkArgument(!this.sourceBlobs.isEmpty());
        Preconditions.<BlobInfo>checkNotNull(this.target);
        return new ComposeRequest(this);
    }
    
    static /* synthetic */ List access$300(final Builder builder) {
        return builder.sourceBlobs;
    }
    
    static /* synthetic */ BlobInfo access$400(final Builder builder) {
        return builder.target;
    }
    
    static /* synthetic */ Set access$500(final Builder builder) {
        return builder.targetOptions;
    }
}
