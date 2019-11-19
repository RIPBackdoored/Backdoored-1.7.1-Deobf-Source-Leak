package com.google.cloud.storage;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import com.google.common.base.Preconditions;
import com.google.cloud.storage.spi.v1.StorageRpc;
import java.io.Serializable;

public abstract class Option implements Serializable
{
    private static final long serialVersionUID = -73199088766477208L;
    private final StorageRpc.Option rpcOption;
    private final Object value;
    
    Option(final StorageRpc.Option reference, final Object value) {
        super();
        this.rpcOption = Preconditions.<StorageRpc.Option>checkNotNull(reference);
        this.value = value;
    }
    
    StorageRpc.Option getRpcOption() {
        return this.rpcOption;
    }
    
    Object getValue() {
        return this.value;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Option)) {
            return false;
        }
        final Option option = (Option)o;
        return Objects.equals(this.rpcOption, option.rpcOption) && Objects.equals(this.value, option.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.rpcOption, this.value);
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.rpcOption.value()).add("value", this.value).toString();
    }
}
