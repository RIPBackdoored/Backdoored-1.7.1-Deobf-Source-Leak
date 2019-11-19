package com.google.cloud.storage.testing;

import java.util.List;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.StorageException;
import com.google.common.base.Strings;
import com.google.cloud.storage.BlobId;
import java.util.ArrayList;
import org.threeten.bp.Duration;
import com.google.api.gax.retrying.RetrySettings;
import java.io.IOException;
import java.util.logging.Level;
import com.google.cloud.TransportOptions;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.util.logging.Logger;

public class RemoteStorageHelper
{
    private static final Logger log;
    private static final String BUCKET_NAME_PREFIX = "gcloud-test-bucket-temp-";
    private final StorageOptions options;
    
    private RemoteStorageHelper(final StorageOptions options) {
        super();
        this.options = options;
    }
    
    public StorageOptions getOptions() {
        return this.options;
    }
    
    public static void cleanBuckets(final Storage storage, final long n, final long n2) {
        final Thread thread = new Thread(new Runnable() {
            final /* synthetic */ Storage val$storage;
            final /* synthetic */ long val$olderThan;
            
            RemoteStorageHelper$1() {
                super();
            }
            
            @Override
            public void run() {
                for (final Bucket bucket : storage.list(Storage.BucketListOption.prefix("gcloud-test-bucket-temp-")).iterateAll()) {
                    if (bucket.getCreateTime() < n) {
                        try {
                            for (final Blob blob : bucket.list(Storage.BlobListOption.fields(Storage.BlobField.EVENT_BASED_HOLD, Storage.BlobField.TEMPORARY_HOLD)).iterateAll()) {
                                if (blob.getEventBasedHold() || blob.getTemporaryHold()) {
                                    storage.update(blob.toBuilder().setTemporaryHold(false).setEventBasedHold(false).build());
                                }
                            }
                            RemoteStorageHelper.forceDelete(storage, bucket.getName());
                        }
                        catch (Exception ex) {}
                    }
                }
            }
        });
        thread.start();
        try {
            thread.join(n2);
        }
        catch (InterruptedException ex) {
            RemoteStorageHelper.log.info("cleanBuckets interrupted");
        }
    }
    
    public static Boolean forceDelete(final Storage storage, final String s, final long n, final TimeUnit timeUnit) throws InterruptedException, ExecutionException {
        return forceDelete(storage, s, n, timeUnit, "");
    }
    
    public static Boolean forceDelete(final Storage storage, final String s, final long n, final TimeUnit timeUnit, final String s2) throws InterruptedException, ExecutionException {
        final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        final Future<Boolean> submit = singleThreadExecutor.<Boolean>submit((Callable<Boolean>)new DeleteBucketTask(storage, s, s2));
        try {
            return submit.get(n, timeUnit);
        }
        catch (TimeoutException ex) {
            return false;
        }
        finally {
            singleThreadExecutor.shutdown();
        }
    }
    
    public static void forceDelete(final Storage storage, final String s) {
        new DeleteBucketTask(storage, s).call();
    }
    
    public static String generateBucketName() {
        return "gcloud-test-bucket-temp-" + UUID.randomUUID().toString();
    }
    
    public static RemoteStorageHelper create(final String projectId, final InputStream inputStream) throws StorageHelperException {
        try {
            return new RemoteStorageHelper(((StorageOptions.Builder)((StorageOptions.Builder)((StorageOptions.Builder)StorageOptions.newBuilder().setCredentials((Credentials)GoogleCredentials.fromStream(inputStream))).setProjectId(projectId)).setRetrySettings(retrySettings())).setTransportOptions((TransportOptions)StorageOptions.getDefaultHttpTransportOptions().toBuilder().setConnectTimeout(60000).setReadTimeout(60000).build()).build());
        }
        catch (IOException ex) {
            if (RemoteStorageHelper.log.isLoggable(Level.WARNING)) {
                RemoteStorageHelper.log.log(Level.WARNING, ex.getMessage());
            }
            throw StorageHelperException.translate(ex);
        }
    }
    
    public static RemoteStorageHelper create() throws StorageHelperException {
        return new RemoteStorageHelper(((StorageOptions.Builder)StorageOptions.newBuilder().setRetrySettings(retrySettings())).setTransportOptions((TransportOptions)StorageOptions.getDefaultHttpTransportOptions().toBuilder().setConnectTimeout(60000).setReadTimeout(60000).build()).build());
    }
    
    private static RetrySettings retrySettings() {
        return RetrySettings.newBuilder().setMaxAttempts(10).setMaxRetryDelay(Duration.ofMillis(30000L)).setTotalTimeout(Duration.ofMillis(120000L)).setInitialRetryDelay(Duration.ofMillis(250L)).setRetryDelayMultiplier(1.0).setInitialRpcTimeout(Duration.ofMillis(120000L)).setRpcTimeoutMultiplier(1.0).setMaxRpcTimeout(Duration.ofMillis(120000L)).build();
    }
    
    static {
        log = Logger.getLogger(RemoteStorageHelper.class.getName());
    }
}
