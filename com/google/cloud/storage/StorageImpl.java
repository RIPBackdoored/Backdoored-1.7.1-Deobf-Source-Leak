package com.google.cloud.storage;

import com.google.cloud.WriteChannel;
import com.google.common.collect.ImmutableMap;
import java.util.Set;
import java.util.Collection;
import com.google.common.collect.ImmutableSet;
import com.google.api.services.storage.model.TestIamPermissionsResponse;
import com.google.cloud.Policy;
import com.google.api.services.storage.model.HmacKeyMetadata;
import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.api.services.storage.model.BucketAccessControl;
import com.google.cloud.BatchResult;
import java.util.Collections;
import java.util.EnumMap;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.URI;
import com.google.common.net.UrlEscapers;
import com.google.common.base.Strings;
import com.google.common.base.CharMatcher;
import com.google.auth.ServiceAccountSigner;
import com.google.common.collect.Maps;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import com.google.cloud.ReadChannel;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Lists;
import com.google.cloud.PageImpl;
import com.google.common.collect.Iterables;
import com.google.common.collect.ImmutableList;
import com.google.api.gax.paging.Page;
import com.google.api.services.storage.model.StorageObject;
import com.google.common.base.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import com.google.common.primitives.Ints;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import com.google.common.base.MoreObjects;
import com.google.api.gax.retrying.ResultRetryAlgorithm;
import com.google.cloud.RetryHelper;
import java.util.Map;
import java.util.concurrent.Callable;
import com.google.cloud.ServiceOptions;
import com.google.cloud.storage.spi.v1.StorageRpc;
import com.google.cloud.Tuple;
import com.google.common.base.Function;
import com.google.cloud.BaseService;

final class StorageImpl extends BaseService<StorageOptions> implements Storage
{
    private static final byte[] EMPTY_BYTE_ARRAY;
    private static final String EMPTY_BYTE_ARRAY_MD5 = "1B2M2Y8AsgTpgAmY7PhCfg==";
    private static final String EMPTY_BYTE_ARRAY_CRC32C = "AAAAAA==";
    private static final String PATH_DELIMITER = "/";
    private static final String STORAGE_XML_HOST_NAME = "https://storage.googleapis.com";
    private static final Function<Tuple<Storage, Boolean>, Boolean> DELETE_FUNCTION;
    private final StorageRpc storageRpc;
    
    StorageImpl(final StorageOptions storageOptions) {
        super((ServiceOptions)storageOptions);
        this.storageRpc = storageOptions.getStorageRpcV1();
    }
    
    public Bucket create(final BucketInfo bucketInfo, final BucketTargetOption... array) {
        final com.google.api.services.storage.model.Bucket pb = bucketInfo.toPb();
        final Map<StorageRpc.Option, ?> optionMap = optionMap(bucketInfo, (Option[])array);
        try {
            return Bucket.fromPb(this, (com.google.api.services.storage.model.Bucket)RetryHelper.runWithRetries((Callable)new Callable<com.google.api.services.storage.model.Bucket>() {
                final /* synthetic */ com.google.api.services.storage.model.Bucket val$bucketPb;
                final /* synthetic */ Map val$optionsMap;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$2() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public com.google.api.services.storage.model.Bucket call() {
                    return this.this$0.storageRpc.create(pb, optionMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Blob create(final BlobInfo blobInfo, final BlobTargetOption... array) {
        return this.internalCreate(blobInfo.toBuilder().setMd5("1B2M2Y8AsgTpgAmY7PhCfg==").setCrc32c("AAAAAA==").build(), StorageImpl.EMPTY_BYTE_ARRAY, array);
    }
    
    public Blob create(final BlobInfo blobInfo, byte[] first, final BlobTargetOption... array) {
        first = MoreObjects.<byte[]>firstNonNull(first, StorageImpl.EMPTY_BYTE_ARRAY);
        return this.internalCreate(blobInfo.toBuilder().setMd5(BaseEncoding.base64().encode(Hashing.md5().hashBytes(first).asBytes())).setCrc32c(BaseEncoding.base64().encode(Ints.toByteArray(Hashing.crc32c().hashBytes(first).asInt()))).build(), first, array);
    }
    
    public Blob create(final BlobInfo blobInfo, byte[] first, final int n, final int n2, final BlobTargetOption... array) {
        first = MoreObjects.<byte[]>firstNonNull(first, StorageImpl.EMPTY_BYTE_ARRAY);
        final byte[] copyOfRange = Arrays.copyOfRange(first, n, n + n2);
        return this.internalCreate(blobInfo.toBuilder().setMd5(BaseEncoding.base64().encode(Hashing.md5().hashBytes(copyOfRange).asBytes())).setCrc32c(BaseEncoding.base64().encode(Ints.toByteArray(Hashing.crc32c().hashBytes(copyOfRange).asInt()))).build(), copyOfRange, array);
    }
    
    @Deprecated
    public Blob create(final BlobInfo blobInfo, final InputStream first, final BlobWriteOption... array) {
        final Tuple<BlobInfo, BlobTargetOption[]> convert = BlobTargetOption.convert(blobInfo, array);
        return Blob.fromPb(this, this.storageRpc.create(((BlobInfo)convert.x()).toPb(), MoreObjects.<InputStream>firstNonNull(first, new ByteArrayInputStream(StorageImpl.EMPTY_BYTE_ARRAY)), optionMap((BlobInfo)convert.x(), (Option[])convert.y())));
    }
    
    private Blob internalCreate(final BlobInfo blobInfo, final byte[] reference, final BlobTargetOption... array) {
        Preconditions.<byte[]>checkNotNull(reference);
        final StorageObject pb = blobInfo.toPb();
        final Map<StorageRpc.Option, ?> optionMap = optionMap(blobInfo, (Option[])array);
        try {
            return Blob.fromPb(this, (StorageObject)RetryHelper.runWithRetries((Callable)new Callable<StorageObject>() {
                final /* synthetic */ StorageObject val$blobPb;
                final /* synthetic */ byte[] val$content;
                final /* synthetic */ Map val$optionsMap;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$3() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public StorageObject call() {
                    return this.this$0.storageRpc.create(pb, new ByteArrayInputStream(reference), optionMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Bucket get(final String s, final BucketGetOption... array) {
        final com.google.api.services.storage.model.Bucket pb = BucketInfo.of(s).toPb();
        final Map<StorageRpc.Option, ?> optionMap = optionMap((Option[])array);
        try {
            final com.google.api.services.storage.model.Bucket bucket = (com.google.api.services.storage.model.Bucket)RetryHelper.runWithRetries((Callable)new Callable<com.google.api.services.storage.model.Bucket>() {
                final /* synthetic */ com.google.api.services.storage.model.Bucket val$bucketPb;
                final /* synthetic */ Map val$optionsMap;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$4() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public com.google.api.services.storage.model.Bucket call() {
                    return this.this$0.storageRpc.get(pb, optionMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
            return (bucket == null) ? null : Bucket.fromPb(this, bucket);
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Blob get(final String s, final String s2, final BlobGetOption... array) {
        return this.get(BlobId.of(s, s2), array);
    }
    
    public Blob get(final BlobId blobId, final BlobGetOption... array) {
        final StorageObject pb = blobId.toPb();
        final Map<StorageRpc.Option, ?> optionMap = optionMap(blobId, (Option[])array);
        try {
            final StorageObject storageObject = (StorageObject)RetryHelper.runWithRetries((Callable)new Callable<StorageObject>() {
                final /* synthetic */ StorageObject val$storedObject;
                final /* synthetic */ Map val$optionsMap;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$5() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public StorageObject call() {
                    return this.this$0.storageRpc.get(pb, optionMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
            return (storageObject == null) ? null : Blob.fromPb(this, storageObject);
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Blob get(final BlobId blobId) {
        return this.get(blobId, new BlobGetOption[0]);
    }
    
    public Page<Bucket> list(final BucketListOption... array) {
        return listBuckets((StorageOptions)this.getOptions(), optionMap((Option[])array));
    }
    
    public Page<Blob> list(final String s, final BlobListOption... array) {
        return listBlobs(s, (StorageOptions)this.getOptions(), optionMap((Option[])array));
    }
    
    private static Page<Bucket> listBuckets(final StorageOptions storageOptions, final Map<StorageRpc.Option, ?> map) {
        try {
            final Tuple tuple = (Tuple)RetryHelper.runWithRetries((Callable)new Callable<Tuple<String, Iterable<com.google.api.services.storage.model.Bucket>>>() {
                final /* synthetic */ StorageOptions val$serviceOptions;
                final /* synthetic */ Map val$optionsMap;
                
                StorageImpl$6() {
                    super();
                }
                
                @Override
                public Tuple<String, Iterable<com.google.api.services.storage.model.Bucket>> call() {
                    return storageOptions.getStorageRpcV1().list(map);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, storageOptions.getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, storageOptions.getClock());
            final String s = (String)tuple.x();
            return (Page<Bucket>)new PageImpl((PageImpl.NextPageFetcher)new BucketPageFetcher(storageOptions, s, map), s, (Iterable)((tuple.y() == null) ? ImmutableList.<Object>of() : Iterables.<Object, Object>transform((Iterable<Object>)tuple.y(), (Function<? super Object, ?>)new Function<com.google.api.services.storage.model.Bucket, Bucket>() {
                final /* synthetic */ StorageOptions val$serviceOptions;
                
                StorageImpl$7() {
                    super();
                }
                
                @Override
                public Bucket apply(final com.google.api.services.storage.model.Bucket bucket) {
                    return Bucket.fromPb((Storage)storageOptions.getService(), bucket);
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((com.google.api.services.storage.model.Bucket)o);
                }
            })));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    private static Page<Blob> listBlobs(final String s, final StorageOptions storageOptions, final Map<StorageRpc.Option, ?> map) {
        try {
            final Tuple tuple = (Tuple)RetryHelper.runWithRetries((Callable)new Callable<Tuple<String, Iterable<StorageObject>>>() {
                final /* synthetic */ StorageOptions val$serviceOptions;
                final /* synthetic */ String val$bucket;
                final /* synthetic */ Map val$optionsMap;
                
                StorageImpl$8() {
                    super();
                }
                
                @Override
                public Tuple<String, Iterable<StorageObject>> call() {
                    return storageOptions.getStorageRpcV1().list(s, map);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, storageOptions.getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, storageOptions.getClock());
            final String s2 = (String)tuple.x();
            return (Page<Blob>)new PageImpl((PageImpl.NextPageFetcher)new BlobPageFetcher(s, storageOptions, s2, map), s2, (Iterable)((tuple.y() == null) ? ImmutableList.<Object>of() : Iterables.<Object, Object>transform((Iterable<Object>)tuple.y(), (Function<? super Object, ?>)new Function<StorageObject, Blob>() {
                final /* synthetic */ StorageOptions val$serviceOptions;
                
                StorageImpl$9() {
                    super();
                }
                
                @Override
                public Blob apply(final StorageObject storageObject) {
                    return Blob.fromPb((Storage)storageOptions.getService(), storageObject);
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((StorageObject)o);
                }
            })));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Bucket update(final BucketInfo bucketInfo, final BucketTargetOption... array) {
        final com.google.api.services.storage.model.Bucket pb = bucketInfo.toPb();
        final Map<StorageRpc.Option, ?> optionMap = optionMap(bucketInfo, (Option[])array);
        try {
            return Bucket.fromPb(this, (com.google.api.services.storage.model.Bucket)RetryHelper.runWithRetries((Callable)new Callable<com.google.api.services.storage.model.Bucket>() {
                final /* synthetic */ com.google.api.services.storage.model.Bucket val$bucketPb;
                final /* synthetic */ Map val$optionsMap;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$10() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public com.google.api.services.storage.model.Bucket call() {
                    return this.this$0.storageRpc.patch(pb, optionMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Blob update(final BlobInfo blobInfo, final BlobTargetOption... array) {
        final StorageObject pb = blobInfo.toPb();
        final Map<StorageRpc.Option, ?> optionMap = optionMap(blobInfo, (Option[])array);
        try {
            return Blob.fromPb(this, (StorageObject)RetryHelper.runWithRetries((Callable)new Callable<StorageObject>() {
                final /* synthetic */ StorageObject val$storageObject;
                final /* synthetic */ Map val$optionsMap;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$11() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public StorageObject call() {
                    return this.this$0.storageRpc.patch(pb, optionMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Blob update(final BlobInfo blobInfo) {
        return this.update(blobInfo, new BlobTargetOption[0]);
    }
    
    public boolean delete(final String s, final BucketSourceOption... array) {
        final com.google.api.services.storage.model.Bucket pb = BucketInfo.of(s).toPb();
        final Map<StorageRpc.Option, ?> optionMap = optionMap((Option[])array);
        try {
            return (boolean)RetryHelper.runWithRetries((Callable)new Callable<Boolean>() {
                final /* synthetic */ com.google.api.services.storage.model.Bucket val$bucketPb;
                final /* synthetic */ Map val$optionsMap;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$12() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public Boolean call() {
                    return this.this$0.storageRpc.delete(pb, optionMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public boolean delete(final String s, final String s2, final BlobSourceOption... array) {
        return this.delete(BlobId.of(s, s2), array);
    }
    
    public boolean delete(final BlobId blobId, final BlobSourceOption... array) {
        final StorageObject pb = blobId.toPb();
        final Map<StorageRpc.Option, ?> optionMap = optionMap(blobId, (Option[])array);
        try {
            return (boolean)RetryHelper.runWithRetries((Callable)new Callable<Boolean>() {
                final /* synthetic */ StorageObject val$storageObject;
                final /* synthetic */ Map val$optionsMap;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$13() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public Boolean call() {
                    return this.this$0.storageRpc.delete(pb, optionMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public boolean delete(final BlobId blobId) {
        return this.delete(blobId, new BlobSourceOption[0]);
    }
    
    public Blob compose(final ComposeRequest composeRequest) {
        final ArrayList arrayListWithCapacity = Lists.newArrayListWithCapacity(composeRequest.getSourceBlobs().size());
        for (final ComposeRequest.SourceBlob sourceBlob : composeRequest.getSourceBlobs()) {
            arrayListWithCapacity.add(BlobInfo.newBuilder(BlobId.of(composeRequest.getTarget().getBucket(), sourceBlob.getName(), sourceBlob.getGeneration())).build().toPb());
        }
        final StorageObject pb = composeRequest.getTarget().toPb();
        final Map<StorageRpc.Option, ?> optionMap = optionMap(composeRequest.getTarget().getGeneration(), composeRequest.getTarget().getMetageneration(), composeRequest.getTargetOptions());
        try {
            return Blob.fromPb(this, (StorageObject)RetryHelper.runWithRetries((Callable)new Callable<StorageObject>() {
                final /* synthetic */ List val$sources;
                final /* synthetic */ StorageObject val$target;
                final /* synthetic */ Map val$targetOptions;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$14() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public StorageObject call() {
                    return this.this$0.storageRpc.compose(arrayListWithCapacity, pb, optionMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public CopyWriter copy(final CopyRequest copyRequest) {
        final StorageObject pb = copyRequest.getSource().toPb();
        final Map<StorageRpc.Option, ?> optionMap = optionMap(copyRequest.getSource().getGeneration(), null, copyRequest.getSourceOptions(), true);
        final StorageObject pb2 = copyRequest.getTarget().toPb();
        final Map<StorageRpc.Option, ?> optionMap2 = optionMap(copyRequest.getTarget().getGeneration(), copyRequest.getTarget().getMetageneration(), copyRequest.getTargetOptions());
        try {
            return new CopyWriter((StorageOptions)this.getOptions(), (StorageRpc.RewriteResponse)RetryHelper.runWithRetries((Callable)new Callable<StorageRpc.RewriteResponse>() {
                final /* synthetic */ StorageObject val$source;
                final /* synthetic */ Map val$sourceOptions;
                final /* synthetic */ CopyRequest val$copyRequest;
                final /* synthetic */ StorageObject val$targetObject;
                final /* synthetic */ Map val$targetOptions;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$15() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public StorageRpc.RewriteResponse call() {
                    return this.this$0.storageRpc.openRewrite(new StorageRpc.RewriteRequest(pb, optionMap, copyRequest.overrideInfo(), pb2, optionMap2, copyRequest.getMegabytesCopiedPerChunk()));
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public byte[] readAllBytes(final String s, final String s2, final BlobSourceOption... array) {
        return this.readAllBytes(BlobId.of(s, s2), array);
    }
    
    public byte[] readAllBytes(final BlobId blobId, final BlobSourceOption... array) {
        final StorageObject pb = blobId.toPb();
        final Map<StorageRpc.Option, ?> optionMap = optionMap(blobId, (Option[])array);
        try {
            return (byte[])RetryHelper.runWithRetries((Callable)new Callable<byte[]>() {
                final /* synthetic */ StorageObject val$storageObject;
                final /* synthetic */ Map val$optionsMap;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$16() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public byte[] call() {
                    return this.this$0.storageRpc.load(pb, optionMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public StorageBatch batch() {
        return new StorageBatch((StorageOptions)this.getOptions());
    }
    
    public ReadChannel reader(final String s, final String s2, final BlobSourceOption... array) {
        return (ReadChannel)new BlobReadChannel((StorageOptions)this.getOptions(), BlobId.of(s, s2), optionMap((Option[])array));
    }
    
    public ReadChannel reader(final BlobId blobId, final BlobSourceOption... array) {
        return (ReadChannel)new BlobReadChannel((StorageOptions)this.getOptions(), blobId, optionMap(blobId, (Option[])array));
    }
    
    public BlobWriteChannel writer(final BlobInfo blobInfo, final BlobWriteOption... array) {
        final Tuple<BlobInfo, BlobTargetOption[]> convert = BlobTargetOption.convert(blobInfo, array);
        return this.writer((BlobInfo)convert.x(), (BlobTargetOption[])convert.y());
    }
    
    public BlobWriteChannel writer(final URL url) {
        return new BlobWriteChannel((StorageOptions)this.getOptions(), url);
    }
    
    private BlobWriteChannel writer(final BlobInfo blobInfo, final BlobTargetOption... array) {
        return new BlobWriteChannel((StorageOptions)this.getOptions(), blobInfo, optionMap(blobInfo, (Option[])array));
    }
    
    public URL signUrl(final BlobInfo blobInfo, final long n, final TimeUnit timeUnit, final SignUrlOption... array) {
        final EnumMap enumMap = Maps.newEnumMap((Class)SignUrlOption.Option.class);
        for (final SignUrlOption signUrlOption : array) {
            enumMap.put(signUrlOption.getOption(), signUrlOption.getValue());
        }
        final boolean equals = SignUrlOption.SignatureVersion.V4.equals(enumMap.get(SignUrlOption.Option.SIGNATURE_VERSION));
        ServiceAccountSigner serviceAccountSigner = enumMap.get(SignUrlOption.Option.SERVICE_ACCOUNT_CRED);
        if (serviceAccountSigner == null) {
            Preconditions.checkState(((StorageOptions)this.getOptions()).getCredentials() instanceof ServiceAccountSigner, "Signing key was not provided and could not be derived");
            serviceAccountSigner = (ServiceAccountSigner)((StorageOptions)this.getOptions()).getCredentials();
        }
        final long n2 = equals ? TimeUnit.SECONDS.convert(timeUnit.toMillis(n), TimeUnit.MILLISECONDS) : TimeUnit.SECONDS.convert(((StorageOptions)this.getOptions()).getClock().millisTime() + timeUnit.toMillis(n), TimeUnit.MILLISECONDS);
        final String s = (enumMap.get(SignUrlOption.Option.HOST_NAME) != null) ? enumMap.get(SignUrlOption.Option.HOST_NAME) : "https://storage.googleapis.com";
        final String trim = CharMatcher.anyOf((CharSequence)"/").trimFrom((CharSequence)blobInfo.getBucket());
        String replace = "";
        if (!Strings.isNullOrEmpty(blobInfo.getName())) {
            replace = UrlEscapers.urlFragmentEscaper().escape(blobInfo.getName()).replace("?", "%3F").replace(";", "%3B");
        }
        final URI create = URI.create(this.constructResourceUriPath(trim, replace));
        try {
            final SignatureInfo buildSignatureInfo = this.buildSignatureInfo(enumMap, blobInfo, n2, create, serviceAccountSigner.getAccount());
            final byte[] sign = serviceAccountSigner.sign(buildSignatureInfo.constructUnsignedPayload().getBytes(StandardCharsets.UTF_8));
            final StringBuilder sb = new StringBuilder();
            sb.append(s).append(create);
            if (equals) {
                final String encode = URLEncoder.encode(BaseEncoding.base16().lowerCase().encode(sign), StandardCharsets.UTF_8.name());
                sb.append("?");
                sb.append(buildSignatureInfo.constructV4QueryString());
                sb.append("&X-Goog-Signature=").append(encode);
            }
            else {
                final String encode2 = URLEncoder.encode(BaseEncoding.base64().encode(sign), StandardCharsets.UTF_8.name());
                sb.append("?");
                sb.append("GoogleAccessId=").append(serviceAccountSigner.getAccount());
                sb.append("&Expires=").append(n2);
                sb.append("&Signature=").append(encode2);
            }
            return new URL(sb.toString());
        }
        catch (MalformedURLException | UnsupportedEncodingException ex) {
            final Object o;
            throw new IllegalStateException((Throwable)o);
        }
    }
    
    private String constructResourceUriPath(final String s, final String string) {
        final StringBuilder sb = new StringBuilder();
        sb.append("/").append(s);
        if (Strings.isNullOrEmpty(string)) {
            return sb.toString();
        }
        if (!string.startsWith("/")) {
            sb.append("/");
        }
        sb.append(string);
        return sb.toString();
    }
    
    private SignatureInfo buildSignatureInfo(final Map<SignUrlOption.Option, Object> map, final BlobInfo blobInfo, final long n, final URI uri, final String accountEmail) {
        final SignatureInfo.Builder builder = new SignatureInfo.Builder(map.containsKey(SignUrlOption.Option.HTTP_METHOD) ? map.get(SignUrlOption.Option.HTTP_METHOD) : HttpMethod.GET, n, uri);
        if (MoreObjects.<Boolean>firstNonNull(map.get(SignUrlOption.Option.MD5), false)) {
            Preconditions.checkArgument(blobInfo.getMd5() != null, (Object)"Blob is missing a value for md5");
            builder.setContentMd5(blobInfo.getMd5());
        }
        if (MoreObjects.<Boolean>firstNonNull(map.get(SignUrlOption.Option.CONTENT_TYPE), false)) {
            Preconditions.checkArgument(blobInfo.getContentType() != null, (Object)"Blob is missing a value for content-type");
            builder.setContentType(blobInfo.getContentType());
        }
        builder.setSignatureVersion((SignUrlOption.SignatureVersion)map.get(SignUrlOption.Option.SIGNATURE_VERSION));
        builder.setAccountEmail(accountEmail);
        builder.setTimestamp(((StorageOptions)this.getOptions()).getClock().millisTime());
        return builder.setCanonicalizedExtensionHeaders(map.containsKey(SignUrlOption.Option.EXT_HEADERS) ? ((Map)map.get(SignUrlOption.Option.EXT_HEADERS)) : Collections.<String, String>emptyMap()).build();
    }
    
    public List<Blob> get(final BlobId... array) {
        return this.get(Arrays.<BlobId>asList(array));
    }
    
    public List<Blob> get(final Iterable<BlobId> iterable) {
        final StorageBatch batch = this.batch();
        final ArrayList<Object> arrayList = Lists.<Object>newArrayList();
        final Iterator<BlobId> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            batch.get(iterator.next(), new BlobGetOption[0]).notify((BatchResult.Callback)new BatchResult.Callback<Blob, StorageException>() {
                final /* synthetic */ List val$results;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$17() {
                    this.this$0 = this$0;
                    super();
                }
                
                public void success(final Blob blob) {
                    arrayList.add(blob);
                }
                
                public void error(final StorageException ex) {
                    arrayList.add(null);
                }
                
                public /* bridge */ void error(final Object o) {
                    this.error((StorageException)o);
                }
                
                public /* bridge */ void success(final Object o) {
                    this.success((Blob)o);
                }
            });
        }
        batch.submit();
        return Collections.<Blob>unmodifiableList((List<? extends Blob>)arrayList);
    }
    
    public List<Blob> update(final BlobInfo... array) {
        return this.update(Arrays.<BlobInfo>asList(array));
    }
    
    public List<Blob> update(final Iterable<BlobInfo> iterable) {
        final StorageBatch batch = this.batch();
        final ArrayList<Object> arrayList = Lists.<Object>newArrayList();
        final Iterator<BlobInfo> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            batch.update(iterator.next(), new BlobTargetOption[0]).notify((BatchResult.Callback)new BatchResult.Callback<Blob, StorageException>() {
                final /* synthetic */ List val$results;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$18() {
                    this.this$0 = this$0;
                    super();
                }
                
                public void success(final Blob blob) {
                    arrayList.add(blob);
                }
                
                public void error(final StorageException ex) {
                    arrayList.add(null);
                }
                
                public /* bridge */ void error(final Object o) {
                    this.error((StorageException)o);
                }
                
                public /* bridge */ void success(final Object o) {
                    this.success((Blob)o);
                }
            });
        }
        batch.submit();
        return Collections.<Blob>unmodifiableList((List<? extends Blob>)arrayList);
    }
    
    public List<Boolean> delete(final BlobId... array) {
        return this.delete(Arrays.<BlobId>asList(array));
    }
    
    public List<Boolean> delete(final Iterable<BlobId> iterable) {
        final StorageBatch batch = this.batch();
        final ArrayList<Object> arrayList = Lists.<Object>newArrayList();
        final Iterator<BlobId> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            batch.delete(iterator.next(), new BlobSourceOption[0]).notify((BatchResult.Callback)new BatchResult.Callback<Boolean, StorageException>() {
                final /* synthetic */ List val$results;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$19() {
                    this.this$0 = this$0;
                    super();
                }
                
                public void success(final Boolean b) {
                    arrayList.add(b);
                }
                
                public void error(final StorageException ex) {
                    arrayList.add(Boolean.FALSE);
                }
                
                public /* bridge */ void error(final Object o) {
                    this.error((StorageException)o);
                }
                
                public /* bridge */ void success(final Object o) {
                    this.success((Boolean)o);
                }
            });
        }
        batch.submit();
        return Collections.<Boolean>unmodifiableList((List<? extends Boolean>)arrayList);
    }
    
    public Acl getAcl(final String s, final Acl.Entity entity, final BucketSourceOption... array) {
        try {
            final BucketAccessControl bucketAccessControl = (BucketAccessControl)RetryHelper.runWithRetries((Callable)new Callable<BucketAccessControl>() {
                final /* synthetic */ String val$bucket;
                final /* synthetic */ Acl.Entity val$entity;
                final /* synthetic */ Map val$optionsMap = optionMap((Option[])array);
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$20() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public BucketAccessControl call() {
                    return this.this$0.storageRpc.getAcl(s, entity.toPb(), this.val$optionsMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
            return (bucketAccessControl == null) ? null : Acl.fromPb(bucketAccessControl);
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Acl getAcl(final String s, final Acl.Entity entity) {
        return this.getAcl(s, entity, new BucketSourceOption[0]);
    }
    
    public boolean deleteAcl(final String s, final Acl.Entity entity, final BucketSourceOption... array) {
        try {
            return (boolean)RetryHelper.runWithRetries((Callable)new Callable<Boolean>() {
                final /* synthetic */ String val$bucket;
                final /* synthetic */ Acl.Entity val$entity;
                final /* synthetic */ Map val$optionsMap = optionMap((Option[])array);
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$21() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public Boolean call() {
                    return this.this$0.storageRpc.deleteAcl(s, entity.toPb(), this.val$optionsMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public boolean deleteAcl(final String s, final Acl.Entity entity) {
        return this.deleteAcl(s, entity, new BucketSourceOption[0]);
    }
    
    public Acl createAcl(final String bucket, final Acl acl, final BucketSourceOption... array) {
        final BucketAccessControl setBucket = acl.toBucketPb().setBucket(bucket);
        try {
            return Acl.fromPb((BucketAccessControl)RetryHelper.runWithRetries((Callable)new Callable<BucketAccessControl>() {
                final /* synthetic */ BucketAccessControl val$aclPb;
                final /* synthetic */ Map val$optionsMap = optionMap((Option[])array);
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$22() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public BucketAccessControl call() {
                    return this.this$0.storageRpc.createAcl(setBucket, this.val$optionsMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Acl createAcl(final String s, final Acl acl) {
        return this.createAcl(s, acl, new BucketSourceOption[0]);
    }
    
    public Acl updateAcl(final String bucket, final Acl acl, final BucketSourceOption... array) {
        final BucketAccessControl setBucket = acl.toBucketPb().setBucket(bucket);
        try {
            return Acl.fromPb((BucketAccessControl)RetryHelper.runWithRetries((Callable)new Callable<BucketAccessControl>() {
                final /* synthetic */ BucketAccessControl val$aclPb;
                final /* synthetic */ Map val$optionsMap = optionMap((Option[])array);
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$23() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public BucketAccessControl call() {
                    return this.this$0.storageRpc.patchAcl(setBucket, this.val$optionsMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Acl updateAcl(final String s, final Acl acl) {
        return this.updateAcl(s, acl, new BucketSourceOption[0]);
    }
    
    public List<Acl> listAcls(final String s, final BucketSourceOption... array) {
        try {
            return Lists.<Object, Acl>transform((List<Object>)RetryHelper.runWithRetries((Callable)new Callable<List<BucketAccessControl>>() {
                final /* synthetic */ String val$bucket;
                final /* synthetic */ Map val$optionsMap = optionMap((Option[])array);
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$24() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public List<BucketAccessControl> call() {
                    return this.this$0.storageRpc.listAcls(s, this.val$optionsMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()), (Function<? super Object, ? extends Acl>)Acl.FROM_BUCKET_PB_FUNCTION);
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public List<Acl> listAcls(final String s) {
        return this.listAcls(s, new BucketSourceOption[0]);
    }
    
    public Acl getDefaultAcl(final String s, final Acl.Entity entity) {
        try {
            final ObjectAccessControl objectAccessControl = (ObjectAccessControl)RetryHelper.runWithRetries((Callable)new Callable<ObjectAccessControl>() {
                final /* synthetic */ String val$bucket;
                final /* synthetic */ Acl.Entity val$entity;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$25() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public ObjectAccessControl call() {
                    return this.this$0.storageRpc.getDefaultAcl(s, entity.toPb());
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
            return (objectAccessControl == null) ? null : Acl.fromPb(objectAccessControl);
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public boolean deleteDefaultAcl(final String s, final Acl.Entity entity) {
        try {
            return (boolean)RetryHelper.runWithRetries((Callable)new Callable<Boolean>() {
                final /* synthetic */ String val$bucket;
                final /* synthetic */ Acl.Entity val$entity;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$26() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public Boolean call() {
                    return this.this$0.storageRpc.deleteDefaultAcl(s, entity.toPb());
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Acl createDefaultAcl(final String bucket, final Acl acl) {
        final ObjectAccessControl setBucket = acl.toObjectPb().setBucket(bucket);
        try {
            return Acl.fromPb((ObjectAccessControl)RetryHelper.runWithRetries((Callable)new Callable<ObjectAccessControl>() {
                final /* synthetic */ ObjectAccessControl val$aclPb;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$27() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public ObjectAccessControl call() {
                    return this.this$0.storageRpc.createDefaultAcl(setBucket);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Acl updateDefaultAcl(final String bucket, final Acl acl) {
        final ObjectAccessControl setBucket = acl.toObjectPb().setBucket(bucket);
        try {
            return Acl.fromPb((ObjectAccessControl)RetryHelper.runWithRetries((Callable)new Callable<ObjectAccessControl>() {
                final /* synthetic */ ObjectAccessControl val$aclPb;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$28() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public ObjectAccessControl call() {
                    return this.this$0.storageRpc.patchDefaultAcl(setBucket);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public List<Acl> listDefaultAcls(final String s) {
        try {
            return Lists.<Object, Acl>transform((List<Object>)RetryHelper.runWithRetries((Callable)new Callable<List<ObjectAccessControl>>() {
                final /* synthetic */ String val$bucket;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$29() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public List<ObjectAccessControl> call() {
                    return this.this$0.storageRpc.listDefaultAcls(s);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()), (Function<? super Object, ? extends Acl>)Acl.FROM_OBJECT_PB_FUNCTION);
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Acl getAcl(final BlobId blobId, final Acl.Entity entity) {
        try {
            final ObjectAccessControl objectAccessControl = (ObjectAccessControl)RetryHelper.runWithRetries((Callable)new Callable<ObjectAccessControl>() {
                final /* synthetic */ BlobId val$blob;
                final /* synthetic */ Acl.Entity val$entity;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$30() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public ObjectAccessControl call() {
                    return this.this$0.storageRpc.getAcl(blobId.getBucket(), blobId.getName(), blobId.getGeneration(), entity.toPb());
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
            return (objectAccessControl == null) ? null : Acl.fromPb(objectAccessControl);
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public boolean deleteAcl(final BlobId blobId, final Acl.Entity entity) {
        try {
            return (boolean)RetryHelper.runWithRetries((Callable)new Callable<Boolean>() {
                final /* synthetic */ BlobId val$blob;
                final /* synthetic */ Acl.Entity val$entity;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$31() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public Boolean call() {
                    return this.this$0.storageRpc.deleteAcl(blobId.getBucket(), blobId.getName(), blobId.getGeneration(), entity.toPb());
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Acl createAcl(final BlobId blobId, final Acl acl) {
        final ObjectAccessControl setGeneration = acl.toObjectPb().setBucket(blobId.getBucket()).setObject(blobId.getName()).setGeneration(blobId.getGeneration());
        try {
            return Acl.fromPb((ObjectAccessControl)RetryHelper.runWithRetries((Callable)new Callable<ObjectAccessControl>() {
                final /* synthetic */ ObjectAccessControl val$aclPb;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$32() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public ObjectAccessControl call() {
                    return this.this$0.storageRpc.createAcl(setGeneration);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Acl updateAcl(final BlobId blobId, final Acl acl) {
        final ObjectAccessControl setGeneration = acl.toObjectPb().setBucket(blobId.getBucket()).setObject(blobId.getName()).setGeneration(blobId.getGeneration());
        try {
            return Acl.fromPb((ObjectAccessControl)RetryHelper.runWithRetries((Callable)new Callable<ObjectAccessControl>() {
                final /* synthetic */ ObjectAccessControl val$aclPb;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$33() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public ObjectAccessControl call() {
                    return this.this$0.storageRpc.patchAcl(setGeneration);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public List<Acl> listAcls(final BlobId blobId) {
        try {
            return Lists.<Object, Acl>transform((List<Object>)RetryHelper.runWithRetries((Callable)new Callable<List<ObjectAccessControl>>() {
                final /* synthetic */ BlobId val$blob;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$34() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public List<ObjectAccessControl> call() {
                    return this.this$0.storageRpc.listAcls(blobId.getBucket(), blobId.getName(), blobId.getGeneration());
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()), (Function<? super Object, ? extends Acl>)Acl.FROM_OBJECT_PB_FUNCTION);
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public HmacKey createHmacKey(final ServiceAccount serviceAccount, final CreateHmacKeyOption... array) {
        try {
            return HmacKey.fromPb((com.google.api.services.storage.model.HmacKey)RetryHelper.runWithRetries((Callable)new Callable<com.google.api.services.storage.model.HmacKey>() {
                final /* synthetic */ ServiceAccount val$serviceAccount;
                final /* synthetic */ CreateHmacKeyOption[] val$options;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$35() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public com.google.api.services.storage.model.HmacKey call() {
                    return this.this$0.storageRpc.createHmacKey(serviceAccount.getEmail(), optionMap(array));
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Page<HmacKey.HmacKeyMetadata> listHmacKeys(final ListHmacKeysOption... array) {
        return listHmacKeys((StorageOptions)this.getOptions(), optionMap((Option[])array));
    }
    
    public HmacKey.HmacKeyMetadata getHmacKey(final String s, final GetHmacKeyOption... array) {
        try {
            return HmacKey.HmacKeyMetadata.fromPb((HmacKeyMetadata)RetryHelper.runWithRetries((Callable)new Callable<HmacKeyMetadata>() {
                final /* synthetic */ String val$accessId;
                final /* synthetic */ GetHmacKeyOption[] val$options;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$36() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public HmacKeyMetadata call() {
                    return this.this$0.storageRpc.getHmacKey(s, optionMap(array));
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    private HmacKey.HmacKeyMetadata updateHmacKey(final HmacKey.HmacKeyMetadata hmacKeyMetadata, final UpdateHmacKeyOption... array) {
        try {
            return HmacKey.HmacKeyMetadata.fromPb((HmacKeyMetadata)RetryHelper.runWithRetries((Callable)new Callable<HmacKeyMetadata>() {
                final /* synthetic */ HmacKey.HmacKeyMetadata val$hmacKeyMetadata;
                final /* synthetic */ UpdateHmacKeyOption[] val$options;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$37() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public HmacKeyMetadata call() {
                    return this.this$0.storageRpc.updateHmacKey(hmacKeyMetadata.toPb(), optionMap(array));
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public HmacKey.HmacKeyMetadata updateHmacKeyState(final HmacKey.HmacKeyMetadata hmacKeyMetadata, final HmacKey.HmacKeyState state, final UpdateHmacKeyOption... array) {
        return this.updateHmacKey(HmacKey.HmacKeyMetadata.newBuilder(hmacKeyMetadata.getServiceAccount()).setProjectId(hmacKeyMetadata.getProjectId()).setAccessId(hmacKeyMetadata.getAccessId()).setState(state).build(), array);
    }
    
    public void deleteHmacKey(final HmacKey.HmacKeyMetadata hmacKeyMetadata, final DeleteHmacKeyOption... array) {
        try {
            RetryHelper.runWithRetries((Callable)new Callable<Void>() {
                final /* synthetic */ HmacKey.HmacKeyMetadata val$metadata;
                final /* synthetic */ DeleteHmacKeyOption[] val$options;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$38() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public Void call() {
                    this.this$0.storageRpc.deleteHmacKey(hmacKeyMetadata.toPb(), optionMap(array));
                    return null;
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    private static Page<HmacKey.HmacKeyMetadata> listHmacKeys(final StorageOptions storageOptions, final Map<StorageRpc.Option, ?> map) {
        try {
            final Tuple tuple = (Tuple)RetryHelper.runWithRetries((Callable)new Callable<Tuple<String, Iterable<HmacKeyMetadata>>>() {
                final /* synthetic */ StorageOptions val$serviceOptions;
                final /* synthetic */ Map val$options;
                
                StorageImpl$39() {
                    super();
                }
                
                @Override
                public Tuple<String, Iterable<HmacKeyMetadata>> call() {
                    return storageOptions.getStorageRpcV1().listHmacKeys(map);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, storageOptions.getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, storageOptions.getClock());
            return (Page<HmacKey.HmacKeyMetadata>)new PageImpl((PageImpl.NextPageFetcher)new HmacKeyMetadataPageFetcher(storageOptions, map), (String)tuple.x(), (Iterable)((tuple.y() == null) ? ImmutableList.<Object>of() : Iterables.<Object, Object>transform((Iterable<Object>)tuple.y(), (Function<? super Object, ?>)new Function<HmacKeyMetadata, HmacKey.HmacKeyMetadata>() {
                StorageImpl$40() {
                    super();
                }
                
                @Override
                public HmacKey.HmacKeyMetadata apply(final HmacKeyMetadata hmacKeyMetadata) {
                    return HmacKey.HmacKeyMetadata.fromPb(hmacKeyMetadata);
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((HmacKeyMetadata)o);
                }
            })));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Policy getIamPolicy(final String s, final BucketSourceOption... array) {
        try {
            return PolicyHelper.convertFromApiPolicy((com.google.api.services.storage.model.Policy)RetryHelper.runWithRetries((Callable)new Callable<com.google.api.services.storage.model.Policy>() {
                final /* synthetic */ String val$bucket;
                final /* synthetic */ Map val$optionsMap = optionMap((Option[])array);
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$41() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public com.google.api.services.storage.model.Policy call() {
                    return this.this$0.storageRpc.getIamPolicy(s, this.val$optionsMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Policy setIamPolicy(final String s, final Policy policy, final BucketSourceOption... array) {
        try {
            return PolicyHelper.convertFromApiPolicy((com.google.api.services.storage.model.Policy)RetryHelper.runWithRetries((Callable)new Callable<com.google.api.services.storage.model.Policy>() {
                final /* synthetic */ String val$bucket;
                final /* synthetic */ Policy val$policy;
                final /* synthetic */ Map val$optionsMap = optionMap((Option[])array);
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$42() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public com.google.api.services.storage.model.Policy call() {
                    return this.this$0.storageRpc.setIamPolicy(s, PolicyHelper.convertToApiPolicy(policy), this.val$optionsMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public List<Boolean> testIamPermissions(final String s, final List<String> fromList, final BucketSourceOption... array) {
        try {
            final TestIamPermissionsResponse testIamPermissionsResponse = (TestIamPermissionsResponse)RetryHelper.runWithRetries((Callable)new Callable<TestIamPermissionsResponse>() {
                final /* synthetic */ String val$bucket;
                final /* synthetic */ List val$permissions;
                final /* synthetic */ Map val$optionsMap = optionMap((Option[])array);
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$43() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public TestIamPermissionsResponse call() {
                    return this.this$0.storageRpc.testIamPermissions(s, fromList, this.val$optionsMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
            return (List<Boolean>)Lists.<Object, Object>transform((List<Object>)fromList, (Function<? super Object, ?>)new Function<String, Boolean>() {
                final /* synthetic */ Set val$heldPermissions = (testIamPermissionsResponse.getPermissions() != null) ? ImmutableSet.<Object>copyOf((Collection<?>)testIamPermissionsResponse.getPermissions()) : ImmutableSet.<Object>of();
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$44() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public Boolean apply(final String s) {
                    return this.val$heldPermissions.contains(s);
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((String)o);
                }
            });
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public Bucket lockRetentionPolicy(final BucketInfo bucketInfo, final BucketTargetOption... array) {
        final com.google.api.services.storage.model.Bucket pb = bucketInfo.toPb();
        final Map<StorageRpc.Option, ?> optionMap = optionMap(bucketInfo, (Option[])array);
        try {
            return Bucket.fromPb(this, (com.google.api.services.storage.model.Bucket)RetryHelper.runWithRetries((Callable)new Callable<com.google.api.services.storage.model.Bucket>() {
                final /* synthetic */ com.google.api.services.storage.model.Bucket val$bucketPb;
                final /* synthetic */ Map val$optionsMap;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$45() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public com.google.api.services.storage.model.Bucket call() {
                    return this.this$0.storageRpc.lockRetentionPolicy(pb, optionMap);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock()));
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    public ServiceAccount getServiceAccount(final String s) {
        try {
            final com.google.api.services.storage.model.ServiceAccount serviceAccount = (com.google.api.services.storage.model.ServiceAccount)RetryHelper.runWithRetries((Callable)new Callable<com.google.api.services.storage.model.ServiceAccount>() {
                final /* synthetic */ String val$projectId;
                final /* synthetic */ StorageImpl this$0;
                
                StorageImpl$46() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public com.google.api.services.storage.model.ServiceAccount call() {
                    return this.this$0.storageRpc.getServiceAccount(s);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
            return (serviceAccount == null) ? null : ServiceAccount.fromPb(serviceAccount);
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    private static <T> void addToOptionMap(final StorageRpc.Option option, final T t, final Map<StorageRpc.Option, Object> map) {
        StorageImpl.<T>addToOptionMap(option, option, t, map);
    }
    
    private static <T> void addToOptionMap(final StorageRpc.Option option, final StorageRpc.Option option2, final T second, final Map<StorageRpc.Option, Object> map) {
        if (map.containsKey(option)) {
            final Object remove = map.remove(option);
            Preconditions.checkArgument(remove != null || second != null, (Object)("Option " + option.value() + " is missing a value"));
            map.put(option2, MoreObjects.<Object>firstNonNull(remove, second));
        }
    }
    
    private static Map<StorageRpc.Option, ?> optionMap(final Long n, final Long n2, final Iterable<? extends Option> iterable) {
        return optionMap(n, n2, iterable, false);
    }
    
    private static Map<StorageRpc.Option, ?> optionMap(final Long n, final Long n2, final Iterable<? extends Option> iterable, final boolean b) {
        final EnumMap enumMap = Maps.newEnumMap((Class)StorageRpc.Option.class);
        for (final Option option : iterable) {
            Preconditions.checkArgument(enumMap.put(option.getRpcOption(), (Boolean)option.getValue()) == null, "Duplicate option %s", (Object)option);
        }
        if (Boolean.TRUE.equals(enumMap.remove(StorageRpc.Option.DELIMITER))) {
            enumMap.put(StorageRpc.Option.DELIMITER, (Boolean)"/");
        }
        if (b) {
            StorageImpl.<Long>addToOptionMap(StorageRpc.Option.IF_GENERATION_MATCH, StorageRpc.Option.IF_SOURCE_GENERATION_MATCH, n, enumMap);
            StorageImpl.<Long>addToOptionMap(StorageRpc.Option.IF_GENERATION_NOT_MATCH, StorageRpc.Option.IF_SOURCE_GENERATION_NOT_MATCH, n, enumMap);
            StorageImpl.<Long>addToOptionMap(StorageRpc.Option.IF_METAGENERATION_MATCH, StorageRpc.Option.IF_SOURCE_METAGENERATION_MATCH, n2, enumMap);
            StorageImpl.<Long>addToOptionMap(StorageRpc.Option.IF_METAGENERATION_NOT_MATCH, StorageRpc.Option.IF_SOURCE_METAGENERATION_NOT_MATCH, n2, enumMap);
        }
        else {
            StorageImpl.<Long>addToOptionMap(StorageRpc.Option.IF_GENERATION_MATCH, n, enumMap);
            StorageImpl.<Long>addToOptionMap(StorageRpc.Option.IF_GENERATION_NOT_MATCH, n, enumMap);
            StorageImpl.<Long>addToOptionMap(StorageRpc.Option.IF_METAGENERATION_MATCH, n2, enumMap);
            StorageImpl.<Long>addToOptionMap(StorageRpc.Option.IF_METAGENERATION_NOT_MATCH, n2, enumMap);
        }
        return (Map<StorageRpc.Option, ?>)ImmutableMap.copyOf((Map)enumMap);
    }
    
    private static Map<StorageRpc.Option, ?> optionMap(final Option... array) {
        return optionMap(null, null, Arrays.<Option>asList(array));
    }
    
    private static Map<StorageRpc.Option, ?> optionMap(final Long n, final Long n2, final Option... array) {
        return optionMap(n, n2, Arrays.<Option>asList(array));
    }
    
    private static Map<StorageRpc.Option, ?> optionMap(final BucketInfo bucketInfo, final Option... array) {
        return optionMap(null, bucketInfo.getMetageneration(), array);
    }
    
    static Map<StorageRpc.Option, ?> optionMap(final BlobInfo blobInfo, final Option... array) {
        return optionMap(blobInfo.getGeneration(), blobInfo.getMetageneration(), array);
    }
    
    static Map<StorageRpc.Option, ?> optionMap(final BlobId blobId, final Option... array) {
        return optionMap(blobId.getGeneration(), null, array);
    }
    
    public /* bridge */ WriteChannel writer(final URL url) {
        return (WriteChannel)this.writer(url);
    }
    
    public /* bridge */ WriteChannel writer(final BlobInfo blobInfo, final BlobWriteOption[] array) {
        return (WriteChannel)this.writer(blobInfo, array);
    }
    
    static /* synthetic */ StorageRpc access$000(final StorageImpl storageImpl) {
        return storageImpl.storageRpc;
    }
    
    static /* synthetic */ Page access$100(final StorageOptions storageOptions, final Map map) {
        return listBuckets(storageOptions, map);
    }
    
    static /* synthetic */ Page access$200(final String s, final StorageOptions storageOptions, final Map map) {
        return listBlobs(s, storageOptions, map);
    }
    
    static /* synthetic */ Page access$300(final StorageOptions storageOptions, final Map map) {
        return listHmacKeys(storageOptions, map);
    }
    
    static /* synthetic */ Map access$400(final Option[] array) {
        return optionMap(array);
    }
    
    static {
        EMPTY_BYTE_ARRAY = new byte[0];
        DELETE_FUNCTION = new Function<Tuple<Storage, Boolean>, Boolean>() {
            StorageImpl$1() {
                super();
            }
            
            @Override
            public Boolean apply(final Tuple<Storage, Boolean> tuple) {
                return (Boolean)tuple.y();
            }
            
            @Override
            public /* bridge */ Object apply(final Object o) {
                return this.apply((Tuple<Storage, Boolean>)o);
            }
        };
    }
}
