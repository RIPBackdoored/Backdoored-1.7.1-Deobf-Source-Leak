package com.google.cloud.storage;

import java.util.Map;
import java.io.Serializable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.io.BaseEncoding;
import java.security.Key;
import com.google.common.base.Function;
import com.google.cloud.storage.spi.v1.StorageRpc;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;
import java.io.InputStream;
import com.google.cloud.Tuple;
import java.util.Iterator;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import com.google.common.collect.Lists;
import java.util.List;
import com.google.api.gax.paging.Page;
import java.util.Arrays;
import com.google.common.base.Preconditions;

public class Bucket extends BucketInfo
{
    private static final long serialVersionUID = 8574601739542252586L;
    private final StorageOptions options;
    private transient Storage storage;
    
    Bucket(final Storage reference, final BuilderImpl builderImpl) {
        super(builderImpl);
        this.storage = Preconditions.<Storage>checkNotNull(reference);
        this.options = (StorageOptions)reference.getOptions();
    }
    
    public boolean exists(final BucketSourceOption... array) {
        final int length = array.length;
        final Storage.BucketGetOption[] array2 = Arrays.<Storage.BucketGetOption>copyOf(BucketSourceOption.toGetOptions(this, array), length + 1);
        array2[length] = Storage.BucketGetOption.fields(new Storage.BucketField[0]);
        return this.storage.get(this.getName(), array2) != null;
    }
    
    public Bucket reload(final BucketSourceOption... array) {
        return this.storage.get(this.getName(), BucketSourceOption.toGetOptions(this, array));
    }
    
    public Bucket update(final Storage.BucketTargetOption... array) {
        return this.storage.update(this, array);
    }
    
    public boolean delete(final BucketSourceOption... array) {
        return this.storage.delete(this.getName(), BucketSourceOption.toSourceOptions(this, array));
    }
    
    public Page<Blob> list(final Storage.BlobListOption... array) {
        return this.storage.list(this.getName(), array);
    }
    
    public Blob get(final String s, final Storage.BlobGetOption... array) {
        return this.storage.get(BlobId.of(this.getName(), s), array);
    }
    
    public List<Blob> get(final String s, final String s2, final String... array) {
        final ArrayList arrayListWithCapacity = Lists.newArrayListWithCapacity(array.length + 2);
        arrayListWithCapacity.add(BlobId.of(this.getName(), s));
        arrayListWithCapacity.add(BlobId.of(this.getName(), s2));
        for (int length = array.length, i = 0; i < length; ++i) {
            arrayListWithCapacity.add(BlobId.of(this.getName(), array[i]));
        }
        return this.storage.get(arrayListWithCapacity);
    }
    
    public List<Blob> get(final Iterable<String> iterable) {
        final ImmutableList.Builder builder = ImmutableList.builder();
        final Iterator<String> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            builder.add(BlobId.of(this.getName(), iterator.next()));
        }
        return this.storage.get(builder.build());
    }
    
    public Blob create(final String s, final byte[] array, final String contentType, final BlobTargetOption... array2) {
        final Tuple<BlobInfo, Storage.BlobTargetOption[]> targetOptions = BlobTargetOption.toTargetOptions(BlobInfo.newBuilder(BlobId.of(this.getName(), s)).setContentType(contentType).build(), array2);
        return this.storage.create((BlobInfo)targetOptions.x(), array, (Storage.BlobTargetOption[])targetOptions.y());
    }
    
    public Blob create(final String s, final InputStream inputStream, final String contentType, final BlobWriteOption... array) {
        final Tuple<BlobInfo, Storage.BlobWriteOption[]> writeOptions = BlobWriteOption.toWriteOptions(BlobInfo.newBuilder(BlobId.of(this.getName(), s)).setContentType(contentType).build(), array);
        return this.storage.create((BlobInfo)writeOptions.x(), inputStream, (Storage.BlobWriteOption[])writeOptions.y());
    }
    
    public Blob create(final String s, final byte[] array, final BlobTargetOption... array2) {
        final Tuple<BlobInfo, Storage.BlobTargetOption[]> targetOptions = BlobTargetOption.toTargetOptions(BlobInfo.newBuilder(BlobId.of(this.getName(), s)).build(), array2);
        return this.storage.create((BlobInfo)targetOptions.x(), array, (Storage.BlobTargetOption[])targetOptions.y());
    }
    
    public Blob create(final String s, final InputStream inputStream, final BlobWriteOption... array) {
        final Tuple<BlobInfo, Storage.BlobWriteOption[]> writeOptions = BlobWriteOption.toWriteOptions(BlobInfo.newBuilder(BlobId.of(this.getName(), s)).build(), array);
        return this.storage.create((BlobInfo)writeOptions.x(), inputStream, (Storage.BlobWriteOption[])writeOptions.y());
    }
    
    public Acl getAcl(final Acl.Entity entity) {
        return this.storage.getAcl(this.getName(), entity);
    }
    
    public boolean deleteAcl(final Acl.Entity entity) {
        return this.storage.deleteAcl(this.getName(), entity);
    }
    
    public Acl createAcl(final Acl acl) {
        return this.storage.createAcl(this.getName(), acl);
    }
    
    public Acl updateAcl(final Acl acl) {
        return this.storage.updateAcl(this.getName(), acl);
    }
    
    public List<Acl> listAcls() {
        return this.storage.listAcls(this.getName());
    }
    
    public Acl getDefaultAcl(final Acl.Entity entity) {
        return this.storage.getDefaultAcl(this.getName(), entity);
    }
    
    public boolean deleteDefaultAcl(final Acl.Entity entity) {
        return this.storage.deleteDefaultAcl(this.getName(), entity);
    }
    
    public Acl createDefaultAcl(final Acl acl) {
        return this.storage.createDefaultAcl(this.getName(), acl);
    }
    
    public Acl updateDefaultAcl(final Acl acl) {
        return this.storage.updateDefaultAcl(this.getName(), acl);
    }
    
    public List<Acl> listDefaultAcls() {
        return this.storage.listDefaultAcls(this.getName());
    }
    
    public Bucket lockRetentionPolicy(final Storage.BucketTargetOption... array) {
        return this.storage.lockRetentionPolicy(this, array);
    }
    
    public Storage getStorage() {
        return this.storage;
    }
    
    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !o.getClass().equals(Bucket.class)) {
            return false;
        }
        final Bucket bucket = (Bucket)o;
        return Objects.equals(this.toPb(), bucket.toPb()) && Objects.equals(this.options, bucket.options);
    }
    
    @Override
    public final int hashCode() {
        return Objects.hash(super.hashCode(), this.options);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.storage = (Storage)this.options.getService();
    }
    
    static Bucket fromPb(final Storage storage, final com.google.api.services.storage.model.Bucket bucket) {
        return new Bucket(storage, new BuilderImpl(BucketInfo.fromPb(bucket)));
    }
    
    @Override
    public /* bridge */ BucketInfo.Builder toBuilder() {
        return this.toBuilder();
    }
    
    static /* synthetic */ Storage access$100(final Bucket bucket) {
        return bucket.storage;
    }
}
