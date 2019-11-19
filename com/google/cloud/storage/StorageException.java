package com.google.cloud.storage;

import com.google.common.collect.ImmutableSet;
import com.google.cloud.RetryHelper;
import com.google.api.client.googleapis.json.GoogleJsonError;
import java.io.IOException;
import com.google.cloud.BaseServiceException;
import java.util.Set;
import com.google.api.core.InternalApi;
import com.google.cloud.http.BaseHttpServiceException;

@InternalApi
public final class StorageException extends BaseHttpServiceException
{
    private static final Set<BaseServiceException.Error> RETRYABLE_ERRORS;
    private static final long serialVersionUID = -4168430271327813063L;
    
    public StorageException(final int n, final String s) {
        this(n, s, null);
    }
    
    public StorageException(final int n, final String s, final Throwable t) {
        super(n, s, (String)null, true, (Set)StorageException.RETRYABLE_ERRORS, t);
    }
    
    public StorageException(final IOException ex) {
        super(ex, true, (Set)StorageException.RETRYABLE_ERRORS);
    }
    
    public StorageException(final GoogleJsonError googleJsonError) {
        super(googleJsonError, true, (Set)StorageException.RETRYABLE_ERRORS);
    }
    
    public static StorageException translateAndThrow(final RetryHelper.RetryHelperException ex) {
        BaseServiceException.translate(ex);
        throw new StorageException(0, ex.getMessage(), ex.getCause());
    }
    
    static {
        RETRYABLE_ERRORS = ImmutableSet.of((Object)new BaseServiceException.Error(Integer.valueOf(504), (String)null), (Object)new BaseServiceException.Error(Integer.valueOf(503), (String)null), (Object)new BaseServiceException.Error(Integer.valueOf(502), (String)null), (Object)new BaseServiceException.Error(Integer.valueOf(500), (String)null), (Object)new BaseServiceException.Error(Integer.valueOf(429), (String)null), (Object)new BaseServiceException.Error(Integer.valueOf(408), (String)null), (Object[])new BaseServiceException.Error[] { new BaseServiceException.Error((Integer)null, "internalError") });
    }
}
