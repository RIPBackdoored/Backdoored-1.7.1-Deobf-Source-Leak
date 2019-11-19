package com.google.cloud.storage;

import com.google.cloud.RestorableState;
import com.google.cloud.Restorable;
import com.google.cloud.WriteChannel;
import com.google.cloud.BaseWriteChannel.BaseState;
import com.google.api.gax.retrying.ResultRetryAlgorithm;
import java.util.concurrent.Callable;
import com.google.cloud.RetryHelper;
import java.util.concurrent.Executors;
import java.io.Serializable;
import com.google.cloud.ServiceOptions;
import java.net.URL;
import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.cloud.BaseWriteChannel;

class BlobWriteChannel extends BaseWriteChannel<StorageOptions, BlobInfo>
{
    BlobWriteChannel(final StorageOptions storageOptions, final BlobInfo blobInfo, final Map<StorageRpc.Option, ?> map) {
        this(storageOptions, blobInfo, open(storageOptions, blobInfo, map));
    }
    
    BlobWriteChannel(final StorageOptions storageOptions, final URL url) {
        this(storageOptions, open(url, storageOptions));
    }
    
    BlobWriteChannel(final StorageOptions storageOptions, final BlobInfo blobInfo, final String s) {
        super((ServiceOptions)storageOptions, (Serializable)blobInfo, s);
    }
    
    BlobWriteChannel(final StorageOptions storageOptions, final String s) {
        super((ServiceOptions)storageOptions, (Serializable)null, s);
    }
    
    protected void flushBuffer(final int n, final boolean b) {
        try {
            RetryHelper.runWithRetries((Callable)Executors.callable(new Runnable() {
                final /* synthetic */ int val$length;
                final /* synthetic */ boolean val$last;
                final /* synthetic */ BlobWriteChannel this$0;
                
                BlobWriteChannel$1() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public void run() {
                    ((StorageOptions)BlobWriteChannel.access$300(this.this$0)).getStorageRpcV1().write(BlobWriteChannel.access$000(this.this$0), BlobWriteChannel.access$100(this.this$0), 0, BlobWriteChannel.access$200(this.this$0), n, b);
                }
            }), ((StorageOptions)this.getOptions()).getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, ((StorageOptions)this.getOptions()).getClock());
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    protected StateImpl.Builder stateBuilder() {
        return StateImpl.builder((StorageOptions)this.getOptions(), (BlobInfo)this.getEntity(), this.getUploadId());
    }
    
    private static String open(final StorageOptions storageOptions, final BlobInfo blobInfo, final Map<StorageRpc.Option, ?> map) {
        try {
            return (String)RetryHelper.runWithRetries((Callable)new Callable<String>() {
                final /* synthetic */ StorageOptions val$options;
                final /* synthetic */ BlobInfo val$blob;
                final /* synthetic */ Map val$optionsMap;
                
                BlobWriteChannel$2() {
                    super();
                }
                
                @Override
                public String call() {
                    return storageOptions.getStorageRpcV1().open(blobInfo.toPb(), map);
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, storageOptions.getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, storageOptions.getClock());
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    private static String open(final URL url, final StorageOptions storageOptions) {
        try {
            return (String)RetryHelper.runWithRetries((Callable)new Callable<String>() {
                final /* synthetic */ URL val$signedURL;
                final /* synthetic */ StorageOptions val$options;
                
                BlobWriteChannel$3() {
                    super();
                }
                
                @Override
                public String call() {
                    if (!isValidSignedURL(url.getQuery())) {
                        throw new StorageException(2, "invalid signedURL");
                    }
                    return storageOptions.getStorageRpcV1().open(url.toString());
                }
                
                @Override
                public /* bridge */ Object call() throws Exception {
                    return this.call();
                }
            }, storageOptions.getRetrySettings(), (ResultRetryAlgorithm)StorageImpl.EXCEPTION_HANDLER, storageOptions.getClock());
        }
        catch (RetryHelper.RetryHelperException ex) {
            throw StorageException.translateAndThrow(ex);
        }
    }
    
    private static boolean isValidSignedURL(final String s) {
        boolean b = true;
        if (s.startsWith("X-Goog-Algorithm=")) {
            if (!s.contains("&X-Goog-Credential=") || !s.contains("&X-Goog-Date=") || !s.contains("&X-Goog-Expires=") || !s.contains("&X-Goog-SignedHeaders=") || !s.contains("&X-Goog-Signature=")) {
                b = false;
            }
        }
        else if (s.startsWith("GoogleAccessId=")) {
            if (!s.contains("&Expires=") || !s.contains("&Signature=")) {
                b = false;
            }
        }
        else {
            b = false;
        }
        return b;
    }
    
    protected /* bridge */ BaseState.Builder stateBuilder() {
        return this.stateBuilder();
    }
    
    static /* synthetic */ String access$000(final BlobWriteChannel blobWriteChannel) {
        return blobWriteChannel.getUploadId();
    }
    
    static /* synthetic */ byte[] access$100(final BlobWriteChannel blobWriteChannel) {
        return blobWriteChannel.getBuffer();
    }
    
    static /* synthetic */ long access$200(final BlobWriteChannel blobWriteChannel) {
        return blobWriteChannel.getPosition();
    }
    
    static /* synthetic */ ServiceOptions access$300(final BlobWriteChannel blobWriteChannel) {
        return blobWriteChannel.getOptions();
    }
    
    static /* synthetic */ boolean access$400(final String s) {
        return isValidSignedURL(s);
    }
    
    static /* synthetic */ void access$600(final BlobWriteChannel blobWriteChannel, final BaseState baseState) {
        blobWriteChannel.restore(baseState);
    }
}
