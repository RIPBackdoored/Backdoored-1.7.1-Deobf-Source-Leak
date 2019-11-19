package com.google.cloud.storage.testing;

public static class StorageHelperException extends RuntimeException
{
    private static final long serialVersionUID = -7756074894502258736L;
    
    public StorageHelperException(final String s) {
        super(s);
    }
    
    public StorageHelperException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public static StorageHelperException translate(final Exception ex) {
        return new StorageHelperException(ex.getMessage(), ex);
    }
}
