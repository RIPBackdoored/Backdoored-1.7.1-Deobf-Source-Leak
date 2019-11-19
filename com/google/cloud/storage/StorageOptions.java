package com.google.cloud.storage;

import com.google.cloud.ServiceFactory;
import com.google.cloud.spi.ServiceRpcFactory;
import com.google.cloud.TransportOptions;
import com.google.cloud.storage.spi.v1.HttpStorageRpc;
import com.google.cloud.ServiceRpc;
import com.google.cloud.Service;
import com.google.common.collect.ImmutableSet;
import com.google.auth.Credentials;
import com.google.cloud.NoCredentials;
import com.google.cloud.storage.spi.v1.StorageRpc;
import com.google.cloud.http.HttpTransportOptions;
import com.google.cloud.ServiceDefaults;
import com.google.cloud.storage.spi.StorageRpcFactory;
import java.util.Set;
import com.google.cloud.ServiceOptions;

public class StorageOptions extends ServiceOptions<Storage, StorageOptions>
{
    private static final long serialVersionUID = -2907268477247502947L;
    private static final String API_SHORT_NAME = "Storage";
    private static final String GCS_SCOPE = "https://www.googleapis.com/auth/devstorage.full_control";
    private static final Set<String> SCOPES;
    
    private StorageOptions(final Builder builder) {
        super((Class)StorageFactory.class, (Class)StorageRpcFactory.class, (ServiceOptions.Builder)builder, (ServiceDefaults)new StorageDefaults());
    }
    
    public static HttpTransportOptions getDefaultHttpTransportOptions() {
        return HttpTransportOptions.newBuilder().build();
    }
    
    protected boolean projectIdRequired() {
        return false;
    }
    
    protected Set<String> getScopes() {
        return StorageOptions.SCOPES;
    }
    
    protected StorageRpc getStorageRpcV1() {
        return (StorageRpc)this.getRpc();
    }
    
    public static StorageOptions getDefaultInstance() {
        return newBuilder().build();
    }
    
    public static StorageOptions getUnauthenticatedInstance() {
        return ((Builder)newBuilder().setCredentials((Credentials)NoCredentials.getInstance())).build();
    }
    
    public Builder toBuilder() {
        return new Builder(this);
    }
    
    public int hashCode() {
        return this.baseHashCode();
    }
    
    public boolean equals(final Object o) {
        return o instanceof StorageOptions && this.baseEquals((ServiceOptions)o);
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public /* bridge */ ServiceOptions.Builder toBuilder() {
        return this.toBuilder();
    }
    
    StorageOptions(final Builder builder, final StorageOptions$1 object) {
        this(builder);
    }
    
    static {
        SCOPES = ImmutableSet.<String>of("https://www.googleapis.com/auth/devstorage.full_control");
    }
}
