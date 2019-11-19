package com.google.cloud.storage;

import com.google.common.io.BaseEncoding;
import java.security.Key;
import java.io.ObjectInputStream;
import java.util.Objects;
import java.util.List;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import com.google.cloud.WriteChannel;
import com.google.cloud.ReadChannel;
import java.util.Arrays;
import com.google.common.base.Preconditions;
import com.google.api.gax.retrying.ResultRetryAlgorithm;
import java.util.concurrent.Callable;
import com.google.cloud.RetryHelper;
import java.util.concurrent.Executors;
import java.util.Map;
import com.google.cloud.storage.spi.v1.StorageRpc;
import com.google.common.io.CountingOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import com.google.api.services.storage.model.StorageObject;
import com.google.cloud.Tuple;
import com.google.common.base.Function;

public class Blob extends BlobInfo
{
    private static final long serialVersionUID = -6806832496717441434L;
    private final StorageOptions options;
    private transient Storage storage;
    static final Function<Tuple<Storage, StorageObject>, Blob> BLOB_FROM_PB_FUNCTION;
    private static final int DEFAULT_CHUNK_SIZE = 2097152;
    
    public void downloadTo(final Path path, final BlobSourceOption... array) {
        try (final OutputStream outputStream = Files.newOutputStream(path, new OpenOption[0])) {
            this.downloadTo(outputStream, array);
        }
        catch (IOException ex) {
            throw new StorageException(ex);
        }
    }
    
    public void downloadTo(final OutputStream outputStream, final BlobSourceOption... array) {
        RetryHelper.runWithRetries((Callable)Executors.callable(new Runnable() {
            final /* synthetic */ StorageRpc val$storageRpc = Blob.this.options.getStorageRpcV1();
            final /* synthetic */ Map val$requestOptions = StorageImpl.optionMap(this.getBlobId(), (Option[])array);
            final /* synthetic */ CountingOutputStream val$countingOutputStream = new CountingOutputStream(outputStream);
            final /* synthetic */ Blob this$0;
            
            Blob$2() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public void run() {
                this.val$storageRpc.read(this.this$0.getBlobId().toPb(), this.val$requestOptions, this.val$countingOutputStream.getCount(), (OutputStream)this.val$countingOutputStream);
            }
        }), this.options.getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, this.options.getClock());
    }
    
    public void downloadTo(final Path path) {
        this.downloadTo(path, new BlobSourceOption[0]);
    }
    
    Blob(final Storage reference, final BuilderImpl builderImpl) {
        super(builderImpl);
        this.storage = Preconditions.<Storage>checkNotNull(reference);
        this.options = (StorageOptions)reference.getOptions();
    }
    
    public boolean exists(final BlobSourceOption... array) {
        final int length = array.length;
        final Storage.BlobGetOption[] array2 = Arrays.<Storage.BlobGetOption>copyOf(BlobSourceOption.toGetOptions(this, array), length + 1);
        array2[length] = Storage.BlobGetOption.fields(new Storage.BlobField[0]);
        return this.storage.get(this.getBlobId(), array2) != null;
    }
    
    public byte[] getContent(final BlobSourceOption... array) {
        return this.storage.readAllBytes(this.getBlobId(), BlobSourceOption.toSourceOptions(this, array));
    }
    
    public Blob reload(final BlobSourceOption... array) {
        return this.storage.get(this.getBlobId(), BlobSourceOption.toGetOptions(this, array));
    }
    
    public Blob update(final Storage.BlobTargetOption... array) {
        return this.storage.update(this, array);
    }
    
    public boolean delete(final BlobSourceOption... array) {
        return this.storage.delete(this.getBlobId(), BlobSourceOption.toSourceOptions(this, array));
    }
    
    public CopyWriter copyTo(final BlobId target, final BlobSourceOption... array) {
        return this.storage.copy(Storage.CopyRequest.newBuilder().setSource(this.getBucket(), this.getName()).setSourceOptions(BlobSourceOption.toSourceOptions(this, array)).setTarget(target).build());
    }
    
    public CopyWriter copyTo(final String s, final BlobSourceOption... array) {
        return this.copyTo(s, this.getName(), array);
    }
    
    public CopyWriter copyTo(final String s, final String s2, final BlobSourceOption... array) {
        return this.copyTo(BlobId.of(s, s2), array);
    }
    
    public ReadChannel reader(final BlobSourceOption... array) {
        return this.storage.reader(this.getBlobId(), BlobSourceOption.toSourceOptions(this, array));
    }
    
    public WriteChannel writer(final Storage.BlobWriteOption... array) {
        return this.storage.writer(this, array);
    }
    
    public URL signUrl(final long n, final TimeUnit timeUnit, final Storage.SignUrlOption... array) {
        return this.storage.signUrl(this, n, timeUnit, array);
    }
    
    public Acl getAcl(final Acl.Entity entity) {
        return this.storage.getAcl(this.getBlobId(), entity);
    }
    
    public boolean deleteAcl(final Acl.Entity entity) {
        return this.storage.deleteAcl(this.getBlobId(), entity);
    }
    
    public Acl createAcl(final Acl acl) {
        return this.storage.createAcl(this.getBlobId(), acl);
    }
    
    public Acl updateAcl(final Acl acl) {
        return this.storage.updateAcl(this.getBlobId(), acl);
    }
    
    public List<Acl> listAcls() {
        return this.storage.listAcls(this.getBlobId());
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
        if (o == null || !o.getClass().equals(Blob.class)) {
            return false;
        }
        final Blob blob = (Blob)o;
        return Objects.equals(this.toPb(), blob.toPb()) && Objects.equals(this.options, blob.options);
    }
    
    @Override
    public final int hashCode() {
        return Objects.hash(super.hashCode(), this.options);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.storage = (Storage)this.options.getService();
    }
    
    static Blob fromPb(final Storage storage, final StorageObject storageObject) {
        return new Blob(storage, new BuilderImpl(BlobInfo.fromPb(storageObject)));
    }
    
    @Override
    public /* bridge */ BlobInfo.Builder toBuilder() {
        return this.toBuilder();
    }
    
    static {
        BLOB_FROM_PB_FUNCTION = new Function<Tuple<Storage, StorageObject>, Blob>() {
            Blob$1() {
                super();
            }
            
            @Override
            public Blob apply(final Tuple<Storage, StorageObject> tuple) {
                return Blob.fromPb((Storage)tuple.x(), (StorageObject)tuple.y());
            }
            
            @Override
            public /* bridge */ Object apply(final Object o) {
                return this.apply((Tuple<Storage, StorageObject>)o);
            }
        };
    }
}
