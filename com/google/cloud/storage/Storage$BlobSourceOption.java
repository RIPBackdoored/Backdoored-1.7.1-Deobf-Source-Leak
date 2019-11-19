package com.google.cloud.storage;

import com.google.common.io.BaseEncoding;
import java.security.Key;
import com.google.cloud.storage.spi.v1.StorageRpc;

public static class BlobSourceOption extends Option
{
    private static final long serialVersionUID = -3712768261070182991L;
    
    private BlobSourceOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    public static BlobSourceOption generationMatch() {
        return new BlobSourceOption(StorageRpc.Option.IF_GENERATION_MATCH, null);
    }
    
    public static BlobSourceOption generationMatch(final long n) {
        return new BlobSourceOption(StorageRpc.Option.IF_GENERATION_MATCH, n);
    }
    
    public static BlobSourceOption generationNotMatch() {
        return new BlobSourceOption(StorageRpc.Option.IF_GENERATION_NOT_MATCH, null);
    }
    
    public static BlobSourceOption generationNotMatch(final long n) {
        return new BlobSourceOption(StorageRpc.Option.IF_GENERATION_NOT_MATCH, n);
    }
    
    public static BlobSourceOption metagenerationMatch(final long n) {
        return new BlobSourceOption(StorageRpc.Option.IF_METAGENERATION_MATCH, n);
    }
    
    public static BlobSourceOption metagenerationNotMatch(final long n) {
        return new BlobSourceOption(StorageRpc.Option.IF_METAGENERATION_NOT_MATCH, n);
    }
    
    public static BlobSourceOption decryptionKey(final Key key) {
        return new BlobSourceOption(StorageRpc.Option.CUSTOMER_SUPPLIED_KEY, BaseEncoding.base64().encode(key.getEncoded()));
    }
    
    public static BlobSourceOption decryptionKey(final String s) {
        return new BlobSourceOption(StorageRpc.Option.CUSTOMER_SUPPLIED_KEY, s);
    }
    
    public static BlobSourceOption userProject(final String s) {
        return new BlobSourceOption(StorageRpc.Option.USER_PROJECT, s);
    }
}
