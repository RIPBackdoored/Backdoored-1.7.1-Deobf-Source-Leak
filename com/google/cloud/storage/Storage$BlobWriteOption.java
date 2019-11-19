package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;
import com.google.common.io.BaseEncoding;
import java.security.Key;
import java.util.Objects;
import java.io.Serializable;

public static class BlobWriteOption implements Serializable
{
    private static final long serialVersionUID = -3880421670966224580L;
    private final Option option;
    private final Object value;
    
    BlobTargetOption toTargetOption() {
        return new BlobTargetOption(this.option.toRpcOption(), this.value);
    }
    
    private BlobWriteOption(final Option option, final Object value) {
        super();
        this.option = option;
        this.value = value;
    }
    
    private BlobWriteOption(final Option option) {
        this(option, null);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.option, this.value);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof BlobWriteOption)) {
            return false;
        }
        final BlobWriteOption blobWriteOption = (BlobWriteOption)o;
        return this.option == blobWriteOption.option && Objects.equals(this.value, blobWriteOption.value);
    }
    
    public static BlobWriteOption predefinedAcl(final PredefinedAcl predefinedAcl) {
        return new BlobWriteOption(Option.PREDEFINED_ACL, predefinedAcl.getEntry());
    }
    
    public static BlobWriteOption doesNotExist() {
        return new BlobWriteOption(Option.IF_GENERATION_MATCH, 0L);
    }
    
    public static BlobWriteOption generationMatch() {
        return new BlobWriteOption(Option.IF_GENERATION_MATCH);
    }
    
    public static BlobWriteOption generationNotMatch() {
        return new BlobWriteOption(Option.IF_GENERATION_NOT_MATCH);
    }
    
    public static BlobWriteOption metagenerationMatch() {
        return new BlobWriteOption(Option.IF_METAGENERATION_MATCH);
    }
    
    public static BlobWriteOption metagenerationNotMatch() {
        return new BlobWriteOption(Option.IF_METAGENERATION_NOT_MATCH);
    }
    
    public static BlobWriteOption md5Match() {
        return new BlobWriteOption(Option.IF_MD5_MATCH, true);
    }
    
    public static BlobWriteOption crc32cMatch() {
        return new BlobWriteOption(Option.IF_CRC32C_MATCH, true);
    }
    
    public static BlobWriteOption encryptionKey(final Key key) {
        return new BlobWriteOption(Option.CUSTOMER_SUPPLIED_KEY, BaseEncoding.base64().encode(key.getEncoded()));
    }
    
    public static BlobWriteOption encryptionKey(final String s) {
        return new BlobWriteOption(Option.CUSTOMER_SUPPLIED_KEY, s);
    }
    
    public static BlobWriteOption kmsKeyName(final String s) {
        return new BlobWriteOption(Option.KMS_KEY_NAME, s);
    }
    
    public static BlobWriteOption userProject(final String s) {
        return new BlobWriteOption(Option.USER_PROJECT, s);
    }
    
    static /* synthetic */ Option access$000(final BlobWriteOption blobWriteOption) {
        return blobWriteOption.option;
    }
}
