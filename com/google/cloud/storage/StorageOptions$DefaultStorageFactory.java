package com.google.cloud.storage;

import com.google.cloud.Service;
import com.google.cloud.ServiceOptions;

public static class DefaultStorageFactory implements StorageFactory
{
    private static final StorageFactory INSTANCE;
    
    public DefaultStorageFactory() {
        super();
    }
    
    public Storage create(final StorageOptions storageOptions) {
        return new StorageImpl(storageOptions);
    }
    
    public /* bridge */ Service create(final ServiceOptions serviceOptions) {
        return (Service)this.create((StorageOptions)serviceOptions);
    }
    
    static /* synthetic */ StorageFactory access$200() {
        return DefaultStorageFactory.INSTANCE;
    }
    
    static {
        INSTANCE = new DefaultStorageFactory();
    }
}
