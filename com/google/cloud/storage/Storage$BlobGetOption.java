package com.google.cloud.storage;

import com.google.common.io.BaseEncoding;
import java.security.Key;
import java.util.List;
import com.google.cloud.FieldSelector;
import com.google.cloud.storage.spi.v1.StorageRpc;

public static class BlobGetOption extends Option
{
    private static final long serialVersionUID = 803817709703661480L;
    
    private BlobGetOption(final StorageRpc.Option option, final Long n) {
        super(option, n);
    }
    
    private BlobGetOption(final StorageRpc.Option option, final String s) {
        super(option, s);
    }
    
    public static BlobGetOption generationMatch() {
        return new BlobGetOption(StorageRpc.Option.IF_GENERATION_MATCH, (Long)null);
    }
    
    public static BlobGetOption generationMatch(final long n) {
        return new BlobGetOption(StorageRpc.Option.IF_GENERATION_MATCH, n);
    }
    
    public static BlobGetOption generationNotMatch() {
        return new BlobGetOption(StorageRpc.Option.IF_GENERATION_NOT_MATCH, (Long)null);
    }
    
    public static BlobGetOption generationNotMatch(final long n) {
        return new BlobGetOption(StorageRpc.Option.IF_GENERATION_NOT_MATCH, n);
    }
    
    public static BlobGetOption metagenerationMatch(final long n) {
        return new BlobGetOption(StorageRpc.Option.IF_METAGENERATION_MATCH, n);
    }
    
    public static BlobGetOption metagenerationNotMatch(final long n) {
        return new BlobGetOption(StorageRpc.Option.IF_METAGENERATION_NOT_MATCH, n);
    }
    
    public static BlobGetOption fields(final BlobField... array) {
        return new BlobGetOption(StorageRpc.Option.FIELDS, FieldSelector.Helper.selector((List)BlobField.REQUIRED_FIELDS, (FieldSelector[])array));
    }
    
    public static BlobGetOption userProject(final String s) {
        return new BlobGetOption(StorageRpc.Option.USER_PROJECT, s);
    }
    
    public static BlobGetOption decryptionKey(final Key key) {
        return new BlobGetOption(StorageRpc.Option.CUSTOMER_SUPPLIED_KEY, BaseEncoding.base64().encode(key.getEncoded()));
    }
    
    public static BlobGetOption decryptionKey(final String s) {
        return new BlobGetOption(StorageRpc.Option.CUSTOMER_SUPPLIED_KEY, s);
    }
}
