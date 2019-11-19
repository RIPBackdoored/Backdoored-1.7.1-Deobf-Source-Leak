package com.google.cloud.storage;

import com.google.common.io.BaseEncoding;
import java.security.Key;
import com.google.cloud.storage.spi.v1.StorageRpc;

public static class BlobSourceOption extends Option
{
    private static final long serialVersionUID = 214616862061934846L;
    
    private BlobSourceOption(final StorageRpc.Option option) {
        super(option, null);
    }
    
    private BlobSourceOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    private Storage.BlobSourceOption toSourceOptions(final BlobInfo blobInfo) {
        switch (this.getRpcOption()) {
            case IF_GENERATION_MATCH:
                return Storage.BlobSourceOption.generationMatch(blobInfo.getGeneration());
            case IF_GENERATION_NOT_MATCH:
                return Storage.BlobSourceOption.generationNotMatch(blobInfo.getGeneration());
            case IF_METAGENERATION_MATCH:
                return Storage.BlobSourceOption.metagenerationMatch(blobInfo.getMetageneration());
            case IF_METAGENERATION_NOT_MATCH:
                return Storage.BlobSourceOption.metagenerationNotMatch(blobInfo.getMetageneration());
            case CUSTOMER_SUPPLIED_KEY:
                return Storage.BlobSourceOption.decryptionKey((String)this.getValue());
            case USER_PROJECT:
                return Storage.BlobSourceOption.userProject((String)this.getValue());
            default:
                throw new AssertionError((Object)"Unexpected enum value");
        }
    }
    
    private Storage.BlobGetOption toGetOption(final BlobInfo blobInfo) {
        switch (this.getRpcOption()) {
            case IF_GENERATION_MATCH:
                return Storage.BlobGetOption.generationMatch(blobInfo.getGeneration());
            case IF_GENERATION_NOT_MATCH:
                return Storage.BlobGetOption.generationNotMatch(blobInfo.getGeneration());
            case IF_METAGENERATION_MATCH:
                return Storage.BlobGetOption.metagenerationMatch(blobInfo.getMetageneration());
            case IF_METAGENERATION_NOT_MATCH:
                return Storage.BlobGetOption.metagenerationNotMatch(blobInfo.getMetageneration());
            case USER_PROJECT:
                return Storage.BlobGetOption.userProject((String)this.getValue());
            case CUSTOMER_SUPPLIED_KEY:
                return Storage.BlobGetOption.decryptionKey((String)this.getValue());
            default:
                throw new AssertionError((Object)"Unexpected enum value");
        }
    }
    
    public static BlobSourceOption generationMatch() {
        return new BlobSourceOption(StorageRpc.Option.IF_GENERATION_MATCH);
    }
    
    public static BlobSourceOption generationNotMatch() {
        return new BlobSourceOption(StorageRpc.Option.IF_GENERATION_NOT_MATCH);
    }
    
    public static BlobSourceOption metagenerationMatch() {
        return new BlobSourceOption(StorageRpc.Option.IF_METAGENERATION_MATCH);
    }
    
    public static BlobSourceOption metagenerationNotMatch() {
        return new BlobSourceOption(StorageRpc.Option.IF_METAGENERATION_NOT_MATCH);
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
    
    static Storage.BlobSourceOption[] toSourceOptions(final BlobInfo blobInfo, final BlobSourceOption... array) {
        final Storage.BlobSourceOption[] array2 = new Storage.BlobSourceOption[array.length];
        int n = 0;
        for (int length = array.length, i = 0; i < length; ++i) {
            array2[n++] = array[i].toSourceOptions(blobInfo);
        }
        return array2;
    }
    
    static Storage.BlobGetOption[] toGetOptions(final BlobInfo blobInfo, final BlobSourceOption... array) {
        final Storage.BlobGetOption[] array2 = new Storage.BlobGetOption[array.length];
        int n = 0;
        for (int length = array.length, i = 0; i < length; ++i) {
            array2[n++] = array[i].toGetOption(blobInfo);
        }
        return array2;
    }
}
