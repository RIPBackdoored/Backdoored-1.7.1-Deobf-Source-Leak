package com.google.cloud.storage;

import com.google.cloud.BaseServiceException;
import com.google.cloud.BatchResult;

public class StorageBatchResult<T> extends BatchResult<T, StorageException>
{
    StorageBatchResult() {
        super();
    }
    
    protected void error(final StorageException ex) {
        super.error((BaseServiceException)ex);
    }
    
    protected void success(final T t) {
        super.success((Object)t);
    }
    
    protected /* bridge */ void error(final BaseServiceException ex) {
        this.error((StorageException)ex);
    }
}
