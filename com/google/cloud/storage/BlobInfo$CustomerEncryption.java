package com.google.cloud.storage;

import com.google.api.services.storage.model.StorageObject;
import java.util.Objects;
import com.google.common.base.MoreObjects;
import java.io.Serializable;

public static class CustomerEncryption implements Serializable
{
    private static final long serialVersionUID = -2133042982786959351L;
    private final String encryptionAlgorithm;
    private final String keySha256;
    
    CustomerEncryption(final String encryptionAlgorithm, final String keySha256) {
        super();
        this.encryptionAlgorithm = encryptionAlgorithm;
        this.keySha256 = keySha256;
    }
    
    public String getEncryptionAlgorithm() {
        return this.encryptionAlgorithm;
    }
    
    public String getKeySha256() {
        return this.keySha256;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("encryptionAlgorithm", this.getEncryptionAlgorithm()).add("keySha256", this.getKeySha256()).toString();
    }
    
    @Override
    public final int hashCode() {
        return Objects.hash(this.encryptionAlgorithm, this.keySha256);
    }
    
    @Override
    public final boolean equals(final Object o) {
        return o == this || (o != null && o.getClass().equals(CustomerEncryption.class) && Objects.equals(this.toPb(), ((CustomerEncryption)o).toPb()));
    }
    
    StorageObject.CustomerEncryption toPb() {
        return new StorageObject.CustomerEncryption().setEncryptionAlgorithm(this.encryptionAlgorithm).setKeySha256(this.keySha256);
    }
    
    static CustomerEncryption fromPb(final StorageObject.CustomerEncryption customerEncryption) {
        return new CustomerEncryption(customerEncryption.getEncryptionAlgorithm(), customerEncryption.getKeySha256());
    }
}
