package com.google.cloud.storage;

import com.google.cloud.ServiceOptions;
import com.google.cloud.storage.spi.v1.HttpStorageRpc;
import com.google.cloud.ServiceRpc;
import com.google.cloud.storage.spi.StorageRpcFactory;

public static class DefaultStorageRpcFactory implements StorageRpcFactory
{
    private static final StorageRpcFactory INSTANCE;
    
    public DefaultStorageRpcFactory() {
        super();
    }
    
    public ServiceRpc create(final StorageOptions storageOptions) {
        return (ServiceRpc)new HttpStorageRpc(storageOptions);
    }
    
    public /* bridge */ ServiceRpc create(final ServiceOptions serviceOptions) {
        return this.create((StorageOptions)serviceOptions);
    }
    
    static /* synthetic */ StorageRpcFactory access$300() {
        return DefaultStorageRpcFactory.INSTANCE;
    }
    
    static {
        INSTANCE = new DefaultStorageRpcFactory();
    }
}
