package com.google.cloud.storage;

import com.google.common.base.Preconditions;
import com.google.api.services.storage.model.StorageObject;
import java.util.Objects;
import com.google.common.base.MoreObjects;
import java.io.Serializable;

public final class BlobId implements Serializable
{
    private static final long serialVersionUID = -6156002883225601925L;
    private final String bucket;
    private final String name;
    private final Long generation;
    
    private BlobId(final String bucket, final String name, final Long generation) {
        super();
        this.bucket = bucket;
        this.name = name;
        this.generation = generation;
    }
    
    public String getBucket() {
        return this.bucket;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Long getGeneration() {
        return this.generation;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("bucket", this.getBucket()).add("name", this.getName()).add("generation", this.getGeneration()).toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.bucket, this.name, this.generation);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !o.getClass().equals(BlobId.class)) {
            return false;
        }
        final BlobId blobId = (BlobId)o;
        return Objects.equals(this.bucket, blobId.bucket) && Objects.equals(this.name, blobId.name) && Objects.equals(this.generation, blobId.generation);
    }
    
    StorageObject toPb() {
        final StorageObject storageObject = new StorageObject();
        storageObject.setBucket(this.bucket);
        storageObject.setName(this.name);
        storageObject.setGeneration(this.generation);
        return storageObject;
    }
    
    public static BlobId of(final String reference, final String reference2) {
        return new BlobId(Preconditions.<String>checkNotNull(reference), Preconditions.<String>checkNotNull(reference2), null);
    }
    
    public static BlobId of(final String reference, final String reference2, final Long n) {
        return new BlobId(Preconditions.<String>checkNotNull(reference), Preconditions.<String>checkNotNull(reference2), n);
    }
    
    static BlobId fromPb(final StorageObject storageObject) {
        return of(storageObject.getBucket(), storageObject.getName(), storageObject.getGeneration());
    }
}
