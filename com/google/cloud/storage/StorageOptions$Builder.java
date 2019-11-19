package com.google.cloud.storage;

import com.google.cloud.http.HttpTransportOptions;
import com.google.cloud.TransportOptions;
import com.google.cloud.ServiceOptions;

public static class Builder extends ServiceOptions.Builder<Storage, StorageOptions, Builder>
{
    private Builder() {
        super();
    }
    
    private Builder(final StorageOptions storageOptions) {
        super((ServiceOptions)storageOptions);
    }
    
    public Builder setTransportOptions(final TransportOptions transportOptions) {
        if (!(transportOptions instanceof HttpTransportOptions)) {
            throw new IllegalArgumentException("Only http transport is allowed for Storage.");
        }
        return (Builder)super.setTransportOptions(transportOptions);
    }
    
    public StorageOptions build() {
        return new StorageOptions(this, null);
    }
    
    public /* bridge */ ServiceOptions.Builder setTransportOptions(final TransportOptions transportOptions) {
        return this.setTransportOptions(transportOptions);
    }
    
    public /* bridge */ ServiceOptions build() {
        return this.build();
    }
    
    Builder(final StorageOptions storageOptions, final StorageOptions$1 object) {
        this(storageOptions);
    }
    
    Builder(final StorageOptions$1 object) {
        this();
    }
}
