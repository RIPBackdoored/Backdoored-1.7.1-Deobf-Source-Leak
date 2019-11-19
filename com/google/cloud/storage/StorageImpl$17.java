package com.google.cloud.storage;

import java.util.List;
import com.google.cloud.BatchResult;

class StorageImpl$17 implements BatchResult.Callback<Blob, StorageException> {
    final /* synthetic */ List val$results;
    final /* synthetic */ StorageImpl this$0;
    
    StorageImpl$17(final StorageImpl this$0, final List val$results) {
        this.this$0 = this$0;
        this.val$results = val$results;
        super();
    }
    
    public void success(final Blob blob) {
        this.val$results.add(blob);
    }
    
    public void error(final StorageException ex) {
        this.val$results.add(null);
    }
    
    public /* bridge */ void error(final Object o) {
        this.error((StorageException)o);
    }
    
    public /* bridge */ void success(final Object o) {
        this.success((Blob)o);
    }
}