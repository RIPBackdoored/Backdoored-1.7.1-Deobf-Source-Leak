package com.google.cloud.storage;

import com.google.cloud.ServiceFactory;
import com.google.cloud.spi.ServiceRpcFactory;
import com.google.cloud.TransportOptions;
import com.google.cloud.storage.spi.StorageRpcFactory;
import com.google.cloud.ServiceDefaults;

private static class StorageDefaults implements ServiceDefaults<Storage, StorageOptions>
{
    private StorageDefaults() {
        super();
    }
    
    public StorageFactory getDefaultServiceFactory() {
        return DefaultStorageFactory.INSTANCE;
    }
    
    public StorageRpcFactory getDefaultRpcFactory() {
        return DefaultStorageRpcFactory.INSTANCE;
    }
    
    public TransportOptions getDefaultTransportOptions() {
        return (TransportOptions)StorageOptions.getDefaultHttpTransportOptions();
    }
    
    public /* bridge */ ServiceRpcFactory getDefaultRpcFactory() {
        return (ServiceRpcFactory)this.getDefaultRpcFactory();
    }
    
    public /* bridge */ ServiceFactory getDefaultServiceFactory() {
        return (ServiceFactory)this.getDefaultServiceFactory();
    }
    
    StorageDefaults(final StorageOptions$1 object) {
        this();
    }
}
