package com.google.cloud.storage.spi.v1;

import io.opencensus.trace.AttributeValue;
import com.google.api.client.googleapis.batch.BatchRequest;
import java.util.LinkedList;
import com.google.api.services.storage.model.ServiceAccount;
import com.google.api.services.storage.model.Notifications;
import com.google.api.services.storage.model.Notification;
import com.google.api.services.storage.model.TestIamPermissionsResponse;
import com.google.api.services.storage.model.Policy;
import com.google.api.services.storage.model.HmacKeysMetadata;
import com.google.api.services.storage.model.HmacKeyMetadata;
import com.google.api.services.storage.model.HmacKey;
import com.google.api.services.storage.model.ObjectAccessControls;
import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.api.services.storage.model.BucketAccessControls;
import com.google.api.services.storage.model.BucketAccessControl;
import com.google.api.services.storage.model.RewriteResponse;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.http.GenericUrl;
import com.google.common.base.Preconditions;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.api.services.storage.model.ComposeRequest;
import java.math.BigInteger;
import com.google.common.collect.Iterables;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import java.util.List;
import com.google.api.services.storage.model.Objects;
import com.google.api.services.storage.model.Buckets;
import com.google.cloud.Tuple;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.InputStreamContent;
import java.io.InputStream;
import com.google.api.services.storage.model.StorageObject;
import io.opencensus.common.Scope;
import io.opencensus.trace.Status;
import com.google.api.services.storage.model.Bucket;
import io.opencensus.trace.Span;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import java.util.Map;
import com.google.cloud.storage.StorageException;
import com.google.api.client.googleapis.json.GoogleJsonError;
import java.io.IOException;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.cloud.ServiceOptions;
import com.google.cloud.http.HttpTransportOptions;
import io.opencensus.trace.Tracing;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.cloud.http.CensusHttpModule;
import io.opencensus.trace.Tracer;
import com.google.api.services.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class HttpStorageRpc implements StorageRpc
{
    public static final String DEFAULT_PROJECTION = "full";
    public static final String NO_ACL_PROJECTION = "noAcl";
    private static final String ENCRYPTION_KEY_PREFIX = "x-goog-encryption-";
    private static final String SOURCE_ENCRYPTION_KEY_PREFIX = "x-goog-copy-source-encryption-";
    private static final int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    private final StorageOptions options;
    private final Storage storage;
    private final Tracer tracer;
    private final CensusHttpModule censusHttpModule;
    private final HttpRequestInitializer batchRequestInitializer;
    private static final long MEGABYTE = 1048576L;
    
    public HttpStorageRpc(final StorageOptions options) {
        super();
        this.tracer = Tracing.getTracer();
        final HttpTransportOptions httpTransportOptions = (HttpTransportOptions)options.getTransportOptions();
        final HttpTransport create = httpTransportOptions.getHttpTransportFactory().create();
        final HttpRequestInitializer httpRequestInitializer = httpTransportOptions.getHttpRequestInitializer((ServiceOptions)options);
        this.options = options;
        this.censusHttpModule = new CensusHttpModule(this.tracer, true);
        final HttpRequestInitializer httpRequestInitializer2 = this.censusHttpModule.getHttpRequestInitializer(httpRequestInitializer);
        this.batchRequestInitializer = this.censusHttpModule.getHttpRequestInitializer((HttpRequestInitializer)null);
        HttpStorageRpcSpans.registerAllSpanNamesForCollection();
        this.storage = new Storage.Builder(create, (JsonFactory)new JacksonFactory(), httpRequestInitializer2).setRootUrl(options.getHost()).setApplicationName(options.getApplicationName()).build();
    }
    
    private static <T> JsonBatchCallback<T> toJsonCallback(final RpcBatch.Callback<T> callback) {
        return new JsonBatchCallback<T>() {
            final /* synthetic */ RpcBatch.Callback val$callback;
            
            HttpStorageRpc$1() {
                super();
            }
            
            @Override
            public void onSuccess(final T t, final HttpHeaders httpHeaders) throws IOException {
                callback.onSuccess(t);
            }
            
            @Override
            public void onFailure(final GoogleJsonError googleJsonError, final HttpHeaders httpHeaders) throws IOException {
                callback.onFailure(googleJsonError);
            }
        };
    }
    
    private static StorageException translate(final IOException ex) {
        return new StorageException(ex);
    }
    
    private static StorageException translate(final GoogleJsonError googleJsonError) {
        return new StorageException(googleJsonError);
    }
    
    private static void setEncryptionHeaders(final HttpHeaders httpHeaders, final String s, final Map<Option, ?> map) {
        final String string = Option.CUSTOMER_SUPPLIED_KEY.getString(map);
        if (string != null) {
            final BaseEncoding base64 = BaseEncoding.base64();
            final HashFunction sha256 = Hashing.sha256();
            httpHeaders.set(s + "algorithm", "AES256");
            httpHeaders.set(s + "key", string);
            httpHeaders.set(s + "key-sha256", base64.encode(sha256.hashBytes(base64.decode((CharSequence)string)).asBytes()));
        }
    }
    
    private Span startSpan(final String s) {
        return this.tracer.spanBuilder(s).setRecordEvents(this.censusHttpModule.isRecordEvents()).startSpan();
    }
    
    @Override
    public Bucket create(final Bucket bucket, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_CREATE_BUCKET);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (Bucket)this.storage.buckets().insert(this.options.getProjectId(), bucket).setProjection("full").setPredefinedAcl(Option.PREDEFINED_ACL.getString(map)).setPredefinedDefaultObjectAcl(Option.PREDEFINED_DEFAULT_OBJECT_ACL.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public StorageObject create(final StorageObject storageObject, final InputStream inputStream, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_CREATE_OBJECT);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            final Storage.Objects.Insert insert = this.storage.objects().insert(storageObject.getBucket(), storageObject, (AbstractInputStreamContent)new InputStreamContent(storageObject.getContentType(), inputStream));
            insert.getMediaHttpUploader().setDirectUploadEnabled(true);
            final Boolean boolean1 = Option.IF_DISABLE_GZIP_CONTENT.getBoolean(map);
            if (boolean1 != null) {
                insert.setDisableGZipContent((boolean)boolean1);
            }
            setEncryptionHeaders(insert.getRequestHeaders(), "x-goog-encryption-", map);
            return (StorageObject)insert.setProjection("full").setPredefinedAcl(Option.PREDEFINED_ACL.getString(map)).setIfMetagenerationMatch(Option.IF_METAGENERATION_MATCH.getLong(map)).setIfMetagenerationNotMatch(Option.IF_METAGENERATION_NOT_MATCH.getLong(map)).setIfGenerationMatch(Option.IF_GENERATION_MATCH.getLong(map)).setIfGenerationNotMatch(Option.IF_GENERATION_NOT_MATCH.getLong(map)).setUserProject(Option.USER_PROJECT.getString(map)).setKmsKeyName(Option.KMS_KEY_NAME.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public Tuple<String, Iterable<Bucket>> list(final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_LIST_BUCKETS);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            final Buckets buckets = (Buckets)this.storage.buckets().list(this.options.getProjectId()).setProjection("full").setPrefix(Option.PREFIX.getString(map)).setMaxResults(Option.MAX_RESULTS.getLong(map)).setPageToken(Option.PAGE_TOKEN.getString(map)).setFields(Option.FIELDS.getString(map)).setUserProject(Option.USER_PROJECT.getString(map)).execute();
            return (Tuple<String, Iterable<Bucket>>)Tuple.of((Object)buckets.getNextPageToken(), (Object)buckets.getItems());
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public Tuple<String, Iterable<StorageObject>> list(final String s, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_LIST_OBJECTS);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            final Objects objects = (Objects)this.storage.objects().list(s).setProjection("full").setVersions(Option.VERSIONS.getBoolean(map)).setDelimiter(Option.DELIMITER.getString(map)).setPrefix(Option.PREFIX.getString(map)).setMaxResults(Option.MAX_RESULTS.getLong(map)).setPageToken(Option.PAGE_TOKEN.getString(map)).setFields(Option.FIELDS.getString(map)).setUserProject(Option.USER_PROJECT.getString(map)).execute();
            return (Tuple<String, Iterable<StorageObject>>)Tuple.of((Object)objects.getNextPageToken(), (Object)Iterables.<Object>concat((Iterable<?>)MoreObjects.<List<? extends T>>firstNonNull(objects.getItems(), ImmutableList.<Object>of()), (Iterable<?>)((objects.getPrefixes() != null) ? Lists.<Object, Object>transform((List<Object>)objects.getPrefixes(), (Function<? super Object, ?>)objectFromPrefix(s)) : ImmutableList.<Object>of())));
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    private static Function<String, StorageObject> objectFromPrefix(final String s) {
        return new Function<String, StorageObject>() {
            final /* synthetic */ String val$bucket;
            
            HttpStorageRpc$2() {
                super();
            }
            
            @Override
            public StorageObject apply(final String name) {
                return new StorageObject().set("isDirectory", (Object)true).setBucket(s).setName(name).setSize(BigInteger.ZERO);
            }
            
            @Override
            public /* bridge */ Object apply(final Object o) {
                return this.apply((String)o);
            }
        };
    }
    
    @Override
    public Bucket get(final Bucket bucket, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_GET_BUCKET);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (Bucket)this.storage.buckets().get(bucket.getName()).setProjection("full").setIfMetagenerationMatch(Option.IF_METAGENERATION_MATCH.getLong(map)).setIfMetagenerationNotMatch(Option.IF_METAGENERATION_NOT_MATCH.getLong(map)).setFields(Option.FIELDS.getString(map)).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            final StorageException translate = translate(ex);
            if (translate.getCode() == 404) {
                return null;
            }
            throw translate;
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    private Storage.Objects.Get getCall(final StorageObject storageObject, final Map<Option, ?> map) throws IOException {
        final Storage.Objects.Get value = this.storage.objects().get(storageObject.getBucket(), storageObject.getName());
        setEncryptionHeaders(value.getRequestHeaders(), "x-goog-encryption-", map);
        return value.setGeneration(storageObject.getGeneration()).setProjection("full").setIfMetagenerationMatch(Option.IF_METAGENERATION_MATCH.getLong(map)).setIfMetagenerationNotMatch(Option.IF_METAGENERATION_NOT_MATCH.getLong(map)).setIfGenerationMatch(Option.IF_GENERATION_MATCH.getLong(map)).setIfGenerationNotMatch(Option.IF_GENERATION_NOT_MATCH.getLong(map)).setFields(Option.FIELDS.getString(map)).setUserProject(Option.USER_PROJECT.getString(map));
    }
    
    @Override
    public StorageObject get(final StorageObject storageObject, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_GET_OBJECT);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (StorageObject)this.getCall(storageObject, map).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            final StorageException translate = translate(ex);
            if (translate.getCode() == 404) {
                return null;
            }
            throw translate;
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public Bucket patch(final Bucket bucket, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_PATCH_BUCKET);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            String string = Option.PROJECTION.getString(map);
            if (bucket.getIamConfiguration() != null && bucket.getIamConfiguration().getBucketPolicyOnly() != null && bucket.getIamConfiguration().getBucketPolicyOnly().getEnabled() != null && bucket.getIamConfiguration().getBucketPolicyOnly().getEnabled()) {
                bucket.setDefaultObjectAcl((List)null);
                bucket.setAcl((List)null);
                if (string == null) {
                    string = "noAcl";
                }
            }
            return (Bucket)this.storage.buckets().patch(bucket.getName(), bucket).setProjection((string == null) ? "full" : string).setPredefinedAcl(Option.PREDEFINED_ACL.getString(map)).setPredefinedDefaultObjectAcl(Option.PREDEFINED_DEFAULT_OBJECT_ACL.getString(map)).setIfMetagenerationMatch(Option.IF_METAGENERATION_MATCH.getLong(map)).setIfMetagenerationNotMatch(Option.IF_METAGENERATION_NOT_MATCH.getLong(map)).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    private Storage.Objects.Patch patchCall(final StorageObject storageObject, final Map<Option, ?> map) throws IOException {
        return this.storage.objects().patch(storageObject.getBucket(), storageObject.getName(), storageObject).setProjection("full").setPredefinedAcl(Option.PREDEFINED_ACL.getString(map)).setIfMetagenerationMatch(Option.IF_METAGENERATION_MATCH.getLong(map)).setIfMetagenerationNotMatch(Option.IF_METAGENERATION_NOT_MATCH.getLong(map)).setIfGenerationMatch(Option.IF_GENERATION_MATCH.getLong(map)).setIfGenerationNotMatch(Option.IF_GENERATION_NOT_MATCH.getLong(map)).setUserProject(Option.USER_PROJECT.getString(map));
    }
    
    @Override
    public StorageObject patch(final StorageObject storageObject, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_PATCH_OBJECT);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (StorageObject)this.patchCall(storageObject, map).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public boolean delete(final Bucket bucket, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_DELETE_BUCKET);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            this.storage.buckets().delete(bucket.getName()).setIfMetagenerationMatch(Option.IF_METAGENERATION_MATCH.getLong(map)).setIfMetagenerationNotMatch(Option.IF_METAGENERATION_NOT_MATCH.getLong(map)).setUserProject(Option.USER_PROJECT.getString(map)).execute();
            return true;
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            final StorageException translate = translate(ex);
            if (translate.getCode() == 404) {
                return false;
            }
            throw translate;
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    private Storage.Objects.Delete deleteCall(final StorageObject storageObject, final Map<Option, ?> map) throws IOException {
        return this.storage.objects().delete(storageObject.getBucket(), storageObject.getName()).setGeneration(storageObject.getGeneration()).setIfMetagenerationMatch(Option.IF_METAGENERATION_MATCH.getLong(map)).setIfMetagenerationNotMatch(Option.IF_METAGENERATION_NOT_MATCH.getLong(map)).setIfGenerationMatch(Option.IF_GENERATION_MATCH.getLong(map)).setIfGenerationNotMatch(Option.IF_GENERATION_NOT_MATCH.getLong(map)).setUserProject(Option.USER_PROJECT.getString(map));
    }
    
    @Override
    public boolean delete(final StorageObject storageObject, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_DELETE_OBJECT);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            this.deleteCall(storageObject, map).execute();
            return true;
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            final StorageException translate = translate(ex);
            if (translate.getCode() == 404) {
                return false;
            }
            throw translate;
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public StorageObject compose(final Iterable<StorageObject> iterable, final StorageObject destination, final Map<Option, ?> map) {
        final ComposeRequest composeRequest = new ComposeRequest();
        composeRequest.setDestination(destination);
        final ArrayList<ComposeRequest.SourceObjects> sourceObjects = new ArrayList<ComposeRequest.SourceObjects>();
        for (final StorageObject storageObject : iterable) {
            final ComposeRequest.SourceObjects sourceObjects2 = new ComposeRequest.SourceObjects();
            sourceObjects2.setName(storageObject.getName());
            final Long generation = storageObject.getGeneration();
            if (generation != null) {
                sourceObjects2.setGeneration(generation);
                sourceObjects2.setObjectPreconditions(new ComposeRequest.SourceObjects.ObjectPreconditions().setIfGenerationMatch(generation));
            }
            sourceObjects.add(sourceObjects2);
        }
        composeRequest.setSourceObjects((List)sourceObjects);
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_COMPOSE);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (StorageObject)this.storage.objects().compose(destination.getBucket(), destination.getName(), composeRequest).setIfMetagenerationMatch(Option.IF_METAGENERATION_MATCH.getLong(map)).setIfGenerationMatch(Option.IF_GENERATION_MATCH.getLong(map)).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public byte[] load(final StorageObject storageObject, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_LOAD);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            final Storage.Objects.Get setUserProject = this.storage.objects().get(storageObject.getBucket(), storageObject.getName()).setGeneration(storageObject.getGeneration()).setIfMetagenerationMatch(Option.IF_METAGENERATION_MATCH.getLong(map)).setIfMetagenerationNotMatch(Option.IF_METAGENERATION_NOT_MATCH.getLong(map)).setIfGenerationMatch(Option.IF_GENERATION_MATCH.getLong(map)).setIfGenerationNotMatch(Option.IF_GENERATION_NOT_MATCH.getLong(map)).setUserProject(Option.USER_PROJECT.getString(map));
            setEncryptionHeaders(setUserProject.getRequestHeaders(), "x-goog-encryption-", map);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            setUserProject.executeMedia().download(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public RpcBatch createBatch() {
        return new DefaultRpcBatch(this, this.storage);
    }
    
    private Storage.Objects.Get createReadRequest(final StorageObject storageObject, final Map<Option, ?> map) throws IOException {
        final Storage.Objects.Get setUserProject = this.storage.objects().get(storageObject.getBucket(), storageObject.getName()).setGeneration(storageObject.getGeneration()).setIfMetagenerationMatch(Option.IF_METAGENERATION_MATCH.getLong(map)).setIfMetagenerationNotMatch(Option.IF_METAGENERATION_NOT_MATCH.getLong(map)).setIfGenerationMatch(Option.IF_GENERATION_MATCH.getLong(map)).setIfGenerationNotMatch(Option.IF_GENERATION_NOT_MATCH.getLong(map)).setUserProject(Option.USER_PROJECT.getString(map));
        setEncryptionHeaders(setUserProject.getRequestHeaders(), "x-goog-encryption-", map);
        setUserProject.setReturnRawInputStream(true);
        return setUserProject;
    }
    
    @Override
    public long read(final StorageObject storageObject, final Map<Option, ?> map, final long bytesDownloaded, final OutputStream outputStream) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_READ);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            final Storage.Objects.Get readRequest = this.createReadRequest(storageObject, map);
            readRequest.getMediaHttpDownloader().setBytesDownloaded(bytesDownloaded);
            readRequest.getMediaHttpDownloader().setDirectDownloadEnabled(true);
            readRequest.executeMediaAndDownloadTo(outputStream);
            return readRequest.getMediaHttpDownloader().getNumBytesDownloaded();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            final StorageException translate = translate(ex);
            if (translate.getCode() == 416) {
                return 0L;
            }
            throw translate;
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public Tuple<String, byte[]> read(final StorageObject storageObject, final Map<Option, ?> map, final long n, final int n2) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_READ);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            Preconditions.checkArgument(n >= 0L, "Position should be non-negative, is %d", n);
            final Storage.Objects.Get readRequest = this.createReadRequest(storageObject, map);
            final StringBuilder sb = new StringBuilder();
            sb.append("bytes=").append(n).append("-").append(n + n2 - 1L);
            readRequest.getRequestHeaders().setRange(sb.toString());
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(n2);
            readRequest.executeMedia().download(byteArrayOutputStream);
            return (Tuple<String, byte[]>)Tuple.of((Object)readRequest.getLastResponseHeaders().getETag(), (Object)byteArrayOutputStream.toByteArray());
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            final StorageException translate = translate(ex);
            if (translate.getCode() == 416) {
                return (Tuple<String, byte[]>)Tuple.of((Object)null, (Object)new byte[0]);
            }
            throw translate;
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public void write(final String p0, final byte[] p1, final int p2, final long p3, final int p4, final boolean p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getstatic       com/google/cloud/storage/spi/v1/HttpStorageRpcSpans.SPAN_NAME_WRITE:Ljava/lang/String;
        //     4: invokespecial   com/google/cloud/storage/spi/v1/HttpStorageRpc.startSpan:(Ljava/lang/String;)Lio/opencensus/trace/Span;
        //     7: astore          8
        //     9: aload_0        
        //    10: getfield        com/google/cloud/storage/spi/v1/HttpStorageRpc.tracer:Lio/opencensus/trace/Tracer;
        //    13: aload           8
        //    15: invokevirtual   io/opencensus/trace/Tracer.withSpan:(Lio/opencensus/trace/Span;)Lio/opencensus/common/Scope;
        //    18: astore          9
        //    20: aload           9
        //    22: invokeinterface io/opencensus/common/Scope.close:()V
        //    27: aload           8
        //    29: invokevirtual   io/opencensus/trace/Span.end:()V
        //    32: return         
        //    33: new             Lcom/google/api/client/http/GenericUrl;
        //    36: dup            
        //    37: aload_1        
        //    38: invokespecial   com/google/api/client/http/GenericUrl.<init>:(Ljava/lang/String;)V
        //    41: astore          10
        //    43: aload_0        
        //    44: getfield        com/google/cloud/storage/spi/v1/HttpStorageRpc.storage:Lcom/google/api/services/storage/Storage;
        //    47: invokevirtual   com/google/api/services/storage/Storage.getRequestFactory:()Lcom/google/api/client/http/HttpRequestFactory;
        //    50: aload           10
        //    52: new             Lcom/google/api/client/http/ByteArrayContent;
        //    55: dup            
        //    56: aconst_null    
        //    57: aload_2        
        //    58: iload_3        
        //    59: iload           6
        //    61: invokespecial   com/google/api/client/http/ByteArrayContent.<init>:(Ljava/lang/String;[BII)V
        //    64: invokevirtual   com/google/api/client/http/HttpRequestFactory.buildPutRequest:(Lcom/google/api/client/http/GenericUrl;Lcom/google/api/client/http/HttpContent;)Lcom/google/api/client/http/HttpRequest;
        //    67: astore          11
        //    69: lload           4
        //    71: iload           6
        //    73: i2l            
        //    74: ladd           
        //    75: lstore          12
        //    77: new             Ljava/lang/StringBuilder;
        //    80: dup            
        //    81: ldc_w           "bytes "
        //    84: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    87: astore          14
        //    89: aload           14
        //    91: bipush          42
        //    93: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    96: pop            
        //    97: goto            120
        //   100: aload           14
        //   102: lload           4
        //   104: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   107: bipush          45
        //   109: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   112: lload           12
        //   114: lconst_1       
        //   115: lsub           
        //   116: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   119: pop            
        //   120: aload           14
        //   122: bipush          47
        //   124: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   127: pop            
        //   128: iload           7
        //   130: ifeq            144
        //   133: aload           14
        //   135: lload           12
        //   137: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   140: pop            
        //   141: goto            152
        //   144: aload           14
        //   146: bipush          42
        //   148: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   151: pop            
        //   152: aload           11
        //   154: invokevirtual   com/google/api/client/http/HttpRequest.getHeaders:()Lcom/google/api/client/http/HttpHeaders;
        //   157: aload           14
        //   159: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   162: invokevirtual   com/google/api/client/http/HttpHeaders.setContentRange:(Ljava/lang/String;)Lcom/google/api/client/http/HttpHeaders;
        //   165: pop            
        //   166: aconst_null    
        //   167: astore          17
        //   169: aconst_null    
        //   170: astore          18
        //   172: aload           11
        //   174: invokevirtual   com/google/api/client/http/HttpRequest.execute:()Lcom/google/api/client/http/HttpResponse;
        //   177: astore          18
        //   179: aload           18
        //   181: invokevirtual   com/google/api/client/http/HttpResponse.getStatusCode:()I
        //   184: istore          15
        //   186: aload           18
        //   188: invokevirtual   com/google/api/client/http/HttpResponse.getStatusMessage:()Ljava/lang/String;
        //   191: astore          16
        //   193: aload           18
        //   195: ifnull          254
        //   198: aload           18
        //   200: invokevirtual   com/google/api/client/http/HttpResponse.disconnect:()V
        //   203: goto            254
        //   206: astore          19
        //   208: aload           19
        //   210: astore          17
        //   212: aload           19
        //   214: invokevirtual   com/google/api/client/http/HttpResponseException.getStatusCode:()I
        //   217: istore          15
        //   219: aload           19
        //   221: invokevirtual   com/google/api/client/http/HttpResponseException.getStatusMessage:()Ljava/lang/String;
        //   224: astore          16
        //   226: aload           18
        //   228: ifnull          254
        //   231: aload           18
        //   233: invokevirtual   com/google/api/client/http/HttpResponse.disconnect:()V
        //   236: goto            254
        //   239: astore          20
        //   241: aload           18
        //   243: ifnull          251
        //   246: aload           18
        //   248: invokevirtual   com/google/api/client/http/HttpResponse.disconnect:()V
        //   251: aload           20
        //   253: athrow         
        //   254: iload           15
        //   256: sipush          308
        //   259: if_icmpne       283
        //   262: iload           7
        //   264: ifeq            320
        //   267: iload           15
        //   269: sipush          200
        //   272: if_icmpeq       320
        //   275: iload           15
        //   277: sipush          201
        //   280: if_icmpeq       320
        //   283: aload           17
        //   285: ifnull          291
        //   288: aload           17
        //   290: athrow         
        //   291: new             Lcom/google/api/client/googleapis/json/GoogleJsonError;
        //   294: dup            
        //   295: invokespecial   com/google/api/client/googleapis/json/GoogleJsonError.<init>:()V
        //   298: astore          19
        //   300: aload           19
        //   302: iload           15
        //   304: invokevirtual   com/google/api/client/googleapis/json/GoogleJsonError.setCode:(I)V
        //   307: aload           19
        //   309: aload           16
        //   311: invokevirtual   com/google/api/client/googleapis/json/GoogleJsonError.setMessage:(Ljava/lang/String;)V
        //   314: aload           19
        //   316: invokestatic    com/google/cloud/storage/spi/v1/HttpStorageRpc.translate:(Lcom/google/api/client/googleapis/json/GoogleJsonError;)Lcom/google/cloud/storage/StorageException;
        //   319: athrow         
        //   320: aload           9
        //   322: invokeinterface io/opencensus/common/Scope.close:()V
        //   327: aload           8
        //   329: invokevirtual   io/opencensus/trace/Span.end:()V
        //   332: goto            376
        //   335: astore          10
        //   337: aload           8
        //   339: getstatic       io/opencensus/trace/Status.UNKNOWN:Lio/opencensus/trace/Status;
        //   342: aload           10
        //   344: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   347: invokevirtual   io/opencensus/trace/Status.withDescription:(Ljava/lang/String;)Lio/opencensus/trace/Status;
        //   350: invokevirtual   io/opencensus/trace/Span.setStatus:(Lio/opencensus/trace/Status;)V
        //   353: aload           10
        //   355: invokestatic    com/google/cloud/storage/spi/v1/HttpStorageRpc.translate:(Ljava/io/IOException;)Lcom/google/cloud/storage/StorageException;
        //   358: athrow         
        //   359: astore          21
        //   361: aload           9
        //   363: invokeinterface io/opencensus/common/Scope.close:()V
        //   368: aload           8
        //   370: invokevirtual   io/opencensus/trace/Span.end:()V
        //   373: aload           21
        //   375: athrow         
        //   376: return         
        //    StackMapTable: 00 10 FD 00 21 07 01 B5 07 01 B0 FF 00 42 00 0D 07 00 02 07 01 6D 07 04 30 01 04 01 01 07 01 B5 07 01 B0 07 04 18 07 04 32 04 07 01 41 00 00 13 17 07 FF 00 35 00 11 07 00 02 07 01 6D 07 04 30 01 04 01 01 07 01 B5 07 01 B0 07 04 18 07 04 32 04 07 01 41 00 00 05 07 03 C1 00 01 07 04 13 FF 00 20 00 11 07 00 02 07 01 6D 07 04 30 01 04 01 01 07 01 B5 07 01 B0 07 04 18 07 04 32 04 07 01 41 00 00 07 04 13 07 03 C1 00 01 07 01 CF FD 00 0B 00 07 01 CF FF 00 02 00 11 07 00 02 07 01 6D 07 04 30 01 04 01 01 07 01 B5 07 01 B0 07 04 18 07 04 32 04 07 01 41 01 07 01 6D 07 04 13 07 03 C1 00 00 07 14 07 1C FF 00 0E 00 09 07 00 02 07 01 6D 07 04 30 01 04 01 01 07 01 B5 07 01 B0 00 01 07 01 86 57 07 01 CF FF 00 10 00 11 07 00 02 07 01 6D 07 04 30 01 04 01 01 07 01 B5 07 01 B0 07 04 18 07 04 32 04 07 01 41 01 07 01 6D 07 04 13 07 03 C1 00 00
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                              
        //  -----  -----  -----  -----  --------------------------------------------------
        //  172    193    206    226    Lcom/google/api/client/http/HttpResponseException;
        //  172    193    239    241    Any
        //  206    226    239    241    Any
        //  239    241    239    241    Any
        //  20     20     335    359    Ljava/io/IOException;
        //  33     320    335    359    Ljava/io/IOException;
        //  20     20     359    361    Any
        //  33     320    359    361    Any
        //  335    361    359    361    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public String open(final StorageObject storageObject, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_OPEN);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            final GenericUrl url = this.storage.objects().insert(storageObject.getBucket(), storageObject).buildHttpRequest().getUrl();
            final String scheme = url.getScheme();
            final String host = url.getHost();
            final int port = url.getPort();
            final GenericUrl genericUrl = new GenericUrl(scheme + "://" + host + ":" + ((port > 0) ? port : url.toURL().getDefaultPort()) + ("/upload" + url.getRawPath()));
            genericUrl.set("uploadType", "resumable");
            genericUrl.set("name", storageObject.getName());
            for (final Option option : map.keySet()) {
                final Object value = option.<Object>get(map);
                if (value != null) {
                    genericUrl.set(option.value(), value.toString());
                }
            }
            final HttpRequest buildPostRequest = this.storage.getRequestFactory().buildPostRequest(genericUrl, new JsonHttpContent(this.storage.getJsonFactory(), storageObject));
            final HttpHeaders headers = buildPostRequest.getHeaders();
            headers.set("X-Upload-Content-Type", MoreObjects.<String>firstNonNull(storageObject.getContentType(), "application/octet-stream"));
            final String string = Option.CUSTOMER_SUPPLIED_KEY.getString(map);
            if (string != null) {
                final BaseEncoding base64 = BaseEncoding.base64();
                final HashFunction sha256 = Hashing.sha256();
                headers.set("x-goog-encryption-algorithm", "AES256");
                headers.set("x-goog-encryption-key", string);
                headers.set("x-goog-encryption-key-sha256", base64.encode(sha256.hashBytes(base64.decode((CharSequence)string)).asBytes()));
            }
            final HttpResponse execute = buildPostRequest.execute();
            if (execute.getStatusCode() != 200) {
                final GoogleJsonError googleJsonError = new GoogleJsonError();
                googleJsonError.setCode(execute.getStatusCode());
                googleJsonError.setMessage(execute.getStatusMessage());
                throw translate(googleJsonError);
            }
            return execute.getHeaders().getLocation();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public String open(final String s) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_OPEN);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            final GenericUrl genericUrl = new GenericUrl(s);
            genericUrl.set("uploadType", "resumable");
            final byte[] array = new byte["".length()];
            final HttpRequest buildPostRequest = this.storage.getRequestFactory().buildPostRequest(genericUrl, new ByteArrayContent("", array, 0, array.length));
            final HttpHeaders headers = buildPostRequest.getHeaders();
            headers.set("X-Upload-Content-Type", "");
            headers.set("x-goog-resumable", "start");
            final HttpResponse execute = buildPostRequest.execute();
            if (execute.getStatusCode() != 201) {
                final GoogleJsonError googleJsonError = new GoogleJsonError();
                googleJsonError.setCode(execute.getStatusCode());
                googleJsonError.setMessage(execute.getStatusMessage());
                throw translate(googleJsonError);
            }
            return execute.getHeaders().getLocation();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public RewriteResponse openRewrite(final RewriteRequest rewriteRequest) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_OPEN_REWRITE);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return this.rewrite(rewriteRequest, null);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public RewriteResponse continueRewrite(final RewriteResponse rewriteResponse) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_CONTINUE_REWRITE);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return this.rewrite(rewriteResponse.rewriteRequest, rewriteResponse.rewriteToken);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    private RewriteResponse rewrite(final RewriteRequest rewriteRequest, final String rewriteToken) {
        try {
            String userProject = Option.USER_PROJECT.getString(rewriteRequest.sourceOptions);
            if (userProject == null) {
                userProject = Option.USER_PROJECT.getString(rewriteRequest.targetOptions);
            }
            final Storage.Objects.Rewrite setDestinationKmsKeyName = this.storage.objects().rewrite(rewriteRequest.source.getBucket(), rewriteRequest.source.getName(), rewriteRequest.target.getBucket(), rewriteRequest.target.getName(), rewriteRequest.overrideInfo ? rewriteRequest.target : null).setSourceGeneration(rewriteRequest.source.getGeneration()).setRewriteToken(rewriteToken).setMaxBytesRewrittenPerCall((rewriteRequest.megabytesRewrittenPerCall != null) ? Long.valueOf(rewriteRequest.megabytesRewrittenPerCall * 1048576L) : null).setProjection("full").setIfSourceMetagenerationMatch(Option.IF_SOURCE_METAGENERATION_MATCH.getLong(rewriteRequest.sourceOptions)).setIfSourceMetagenerationNotMatch(Option.IF_SOURCE_METAGENERATION_NOT_MATCH.getLong(rewriteRequest.sourceOptions)).setIfSourceGenerationMatch(Option.IF_SOURCE_GENERATION_MATCH.getLong(rewriteRequest.sourceOptions)).setIfSourceGenerationNotMatch(Option.IF_SOURCE_GENERATION_NOT_MATCH.getLong(rewriteRequest.sourceOptions)).setIfMetagenerationMatch(Option.IF_METAGENERATION_MATCH.getLong(rewriteRequest.targetOptions)).setIfMetagenerationNotMatch(Option.IF_METAGENERATION_NOT_MATCH.getLong(rewriteRequest.targetOptions)).setIfGenerationMatch(Option.IF_GENERATION_MATCH.getLong(rewriteRequest.targetOptions)).setIfGenerationNotMatch(Option.IF_GENERATION_NOT_MATCH.getLong(rewriteRequest.targetOptions)).setDestinationPredefinedAcl(Option.PREDEFINED_ACL.getString(rewriteRequest.targetOptions)).setUserProject(userProject).setDestinationKmsKeyName(Option.KMS_KEY_NAME.getString(rewriteRequest.targetOptions));
            final HttpHeaders requestHeaders = setDestinationKmsKeyName.getRequestHeaders();
            setEncryptionHeaders(requestHeaders, "x-goog-copy-source-encryption-", rewriteRequest.sourceOptions);
            setEncryptionHeaders(requestHeaders, "x-goog-encryption-", rewriteRequest.targetOptions);
            final com.google.api.services.storage.model.RewriteResponse rewriteResponse = (com.google.api.services.storage.model.RewriteResponse)setDestinationKmsKeyName.execute();
            return new RewriteResponse(rewriteRequest, rewriteResponse.getResource(), rewriteResponse.getObjectSize(), rewriteResponse.getDone(), rewriteResponse.getRewriteToken(), rewriteResponse.getTotalBytesRewritten());
        }
        catch (IOException ex) {
            this.tracer.getCurrentSpan().setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
    }
    
    @Override
    public BucketAccessControl getAcl(final String s, final String s2, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_GET_BUCKET_ACL);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (BucketAccessControl)this.storage.bucketAccessControls().get(s, s2).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            final StorageException translate = translate(ex);
            if (translate.getCode() == 404) {
                return null;
            }
            throw translate;
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public boolean deleteAcl(final String s, final String s2, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_DELETE_BUCKET_ACL);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            this.storage.bucketAccessControls().delete(s, s2).setUserProject(Option.USER_PROJECT.getString(map)).execute();
            return true;
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            final StorageException translate = translate(ex);
            if (translate.getCode() == 404) {
                return false;
            }
            throw translate;
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public BucketAccessControl createAcl(final BucketAccessControl bucketAccessControl, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_CREATE_BUCKET_ACL);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (BucketAccessControl)this.storage.bucketAccessControls().insert(bucketAccessControl.getBucket(), bucketAccessControl).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public BucketAccessControl patchAcl(final BucketAccessControl bucketAccessControl, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_PATCH_BUCKET_ACL);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (BucketAccessControl)this.storage.bucketAccessControls().patch(bucketAccessControl.getBucket(), bucketAccessControl.getEntity(), bucketAccessControl).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public List<BucketAccessControl> listAcls(final String s, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_LIST_BUCKET_ACLS);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (List<BucketAccessControl>)((BucketAccessControls)this.storage.bucketAccessControls().list(s).setUserProject(Option.USER_PROJECT.getString(map)).execute()).getItems();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public ObjectAccessControl getDefaultAcl(final String s, final String s2) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_GET_OBJECT_DEFAULT_ACL);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (ObjectAccessControl)this.storage.defaultObjectAccessControls().get(s, s2).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            final StorageException translate = translate(ex);
            if (translate.getCode() == 404) {
                return null;
            }
            throw translate;
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public boolean deleteDefaultAcl(final String s, final String s2) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_DELETE_OBJECT_DEFAULT_ACL);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            this.storage.defaultObjectAccessControls().delete(s, s2).execute();
            return true;
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            final StorageException translate = translate(ex);
            if (translate.getCode() == 404) {
                return false;
            }
            throw translate;
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public ObjectAccessControl createDefaultAcl(final ObjectAccessControl objectAccessControl) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_CREATE_OBJECT_DEFAULT_ACL);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (ObjectAccessControl)this.storage.defaultObjectAccessControls().insert(objectAccessControl.getBucket(), objectAccessControl).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public ObjectAccessControl patchDefaultAcl(final ObjectAccessControl objectAccessControl) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_PATCH_OBJECT_DEFAULT_ACL);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (ObjectAccessControl)this.storage.defaultObjectAccessControls().patch(objectAccessControl.getBucket(), objectAccessControl.getEntity(), objectAccessControl).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public List<ObjectAccessControl> listDefaultAcls(final String s) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_LIST_OBJECT_DEFAULT_ACLS);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (List<ObjectAccessControl>)((ObjectAccessControls)this.storage.defaultObjectAccessControls().list(s).execute()).getItems();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public ObjectAccessControl getAcl(final String s, final String s2, final Long generation, final String s3) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_GET_OBJECT_ACL);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (ObjectAccessControl)this.storage.objectAccessControls().get(s, s2, s3).setGeneration(generation).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            final StorageException translate = translate(ex);
            if (translate.getCode() == 404) {
                return null;
            }
            throw translate;
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public boolean deleteAcl(final String s, final String s2, final Long generation, final String s3) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_DELETE_OBJECT_ACL);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            this.storage.objectAccessControls().delete(s, s2, s3).setGeneration(generation).execute();
            return true;
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            final StorageException translate = translate(ex);
            if (translate.getCode() == 404) {
                return false;
            }
            throw translate;
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public ObjectAccessControl createAcl(final ObjectAccessControl objectAccessControl) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_CREATE_OBJECT_ACL);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (ObjectAccessControl)this.storage.objectAccessControls().insert(objectAccessControl.getBucket(), objectAccessControl.getObject(), objectAccessControl).setGeneration(objectAccessControl.getGeneration()).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public ObjectAccessControl patchAcl(final ObjectAccessControl objectAccessControl) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_PATCH_OBJECT_ACL);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (ObjectAccessControl)this.storage.objectAccessControls().patch(objectAccessControl.getBucket(), objectAccessControl.getObject(), objectAccessControl.getEntity(), objectAccessControl).setGeneration(objectAccessControl.getGeneration()).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public List<ObjectAccessControl> listAcls(final String s, final String s2, final Long generation) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_LIST_OBJECT_ACLS);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (List<ObjectAccessControl>)((ObjectAccessControls)this.storage.objectAccessControls().list(s, s2).setGeneration(generation).execute()).getItems();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public HmacKey createHmacKey(final String s, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_CREATE_HMAC_KEY);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        String s2 = Option.PROJECT_ID.getString(map);
        if (s2 == null) {
            s2 = this.options.getProjectId();
        }
        try {
            return (HmacKey)this.storage.projects().hmacKeys().create(s2, s).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public Tuple<String, Iterable<HmacKeyMetadata>> listHmacKeys(final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_LIST_HMAC_KEYS);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        String s = Option.PROJECT_ID.getString(map);
        if (s == null) {
            s = this.options.getProjectId();
        }
        try {
            final HmacKeysMetadata hmacKeysMetadata = (HmacKeysMetadata)this.storage.projects().hmacKeys().list(s).setServiceAccountEmail(Option.SERVICE_ACCOUNT_EMAIL.getString(map)).setPageToken(Option.PAGE_TOKEN.getString(map)).setMaxResults(Option.MAX_RESULTS.getLong(map)).setShowDeletedKeys(Option.SHOW_DELETED_KEYS.getBoolean(map)).execute();
            return (Tuple<String, Iterable<HmacKeyMetadata>>)Tuple.of((Object)hmacKeysMetadata.getNextPageToken(), (Object)hmacKeysMetadata.getItems());
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public HmacKeyMetadata getHmacKey(final String s, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_GET_HMAC_KEY);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        String s2 = Option.PROJECT_ID.getString(map);
        if (s2 == null) {
            s2 = this.options.getProjectId();
        }
        try {
            return (HmacKeyMetadata)this.storage.projects().hmacKeys().get(s2, s).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public HmacKeyMetadata updateHmacKey(final HmacKeyMetadata hmacKeyMetadata, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_UPDATE_HMAC_KEY);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        String s = hmacKeyMetadata.getProjectId();
        if (s == null) {
            s = this.options.getProjectId();
        }
        try {
            return (HmacKeyMetadata)this.storage.projects().hmacKeys().update(s, hmacKeyMetadata.getAccessId(), hmacKeyMetadata).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public void deleteHmacKey(final HmacKeyMetadata hmacKeyMetadata, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_DELETE_HMAC_KEY);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        String s = hmacKeyMetadata.getProjectId();
        if (s == null) {
            s = this.options.getProjectId();
        }
        try {
            this.storage.projects().hmacKeys().delete(s, hmacKeyMetadata.getAccessId()).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public Policy getIamPolicy(final String s, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_GET_BUCKET_IAM_POLICY);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (Policy)this.storage.buckets().getIamPolicy(s).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public Policy setIamPolicy(final String s, final Policy policy, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_SET_BUCKET_IAM_POLICY);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (Policy)this.storage.buckets().setIamPolicy(s, policy).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public TestIamPermissionsResponse testIamPermissions(final String s, final List<String> list, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_TEST_BUCKET_IAM_PERMISSIONS);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (TestIamPermissionsResponse)this.storage.buckets().testIamPermissions(s, (List)list).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public boolean deleteNotification(final String s, final String s2) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_DELETE_NOTIFICATION);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            this.storage.notifications().delete(s, s2).execute();
            return true;
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            final StorageException translate = translate(ex);
            if (translate.getCode() == 404) {
                return false;
            }
            throw translate;
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public List<Notification> listNotifications(final String s) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_LIST_NOTIFICATIONS);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (List<Notification>)((Notifications)this.storage.notifications().list(s).execute()).getItems();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public Notification createNotification(final String s, final Notification notification) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_CREATE_NOTIFICATION);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (Notification)this.storage.notifications().insert(s, notification).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public Bucket lockRetentionPolicy(final Bucket bucket, final Map<Option, ?> map) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_LOCK_RETENTION_POLICY);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (Bucket)this.storage.buckets().lockRetentionPolicy(bucket.getName(), Option.IF_METAGENERATION_MATCH.getLong(map)).setUserProject(Option.USER_PROJECT.getString(map)).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    @Override
    public ServiceAccount getServiceAccount(final String s) {
        final Span startSpan = this.startSpan(HttpStorageRpcSpans.SPAN_NAME_GET_SERVICE_ACCOUNT);
        final Scope withSpan = this.tracer.withSpan(startSpan);
        try {
            return (ServiceAccount)this.storage.projects().serviceAccount().get(s).execute();
        }
        catch (IOException ex) {
            startSpan.setStatus(Status.UNKNOWN.withDescription(ex.getMessage()));
            throw translate(ex);
        }
        finally {
            withSpan.close();
            startSpan.end();
        }
    }
    
    static /* synthetic */ HttpRequestInitializer access$000(final HttpStorageRpc httpStorageRpc) {
        return httpStorageRpc.batchRequestInitializer;
    }
    
    static /* synthetic */ JsonBatchCallback access$100(final RpcBatch.Callback callback) {
        return HttpStorageRpc.<Object>toJsonCallback((RpcBatch.Callback<Object>)callback);
    }
    
    static /* synthetic */ Storage.Objects.Delete access$200(final HttpStorageRpc httpStorageRpc, final StorageObject storageObject, final Map map) throws IOException {
        return httpStorageRpc.deleteCall(storageObject, map);
    }
    
    static /* synthetic */ StorageException access$300(final IOException ex) {
        return translate(ex);
    }
    
    static /* synthetic */ Storage.Objects.Patch access$400(final HttpStorageRpc httpStorageRpc, final StorageObject storageObject, final Map map) throws IOException {
        return httpStorageRpc.patchCall(storageObject, map);
    }
    
    static /* synthetic */ Storage.Objects.Get access$500(final HttpStorageRpc httpStorageRpc, final StorageObject storageObject, final Map map) throws IOException {
        return httpStorageRpc.getCall(storageObject, map);
    }
    
    static /* synthetic */ Span access$600(final HttpStorageRpc httpStorageRpc, final String s) {
        return httpStorageRpc.startSpan(s);
    }
    
    static /* synthetic */ Tracer access$700(final HttpStorageRpc httpStorageRpc) {
        return httpStorageRpc.tracer;
    }
    
    static /* synthetic */ StorageOptions access$800(final HttpStorageRpc httpStorageRpc) {
        return httpStorageRpc.options;
    }
}
