package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;

public static class BucketSourceOption extends Option
{
    private static final long serialVersionUID = 6928872234155522371L;
    
    private BucketSourceOption(final StorageRpc.Option option) {
        super(option, null);
    }
    
    private BucketSourceOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    private Storage.BucketSourceOption toSourceOption(final BucketInfo bucketInfo) {
        switch (this.getRpcOption()) {
            case IF_METAGENERATION_MATCH:
                return Storage.BucketSourceOption.metagenerationMatch(bucketInfo.getMetageneration());
            case IF_METAGENERATION_NOT_MATCH:
                return Storage.BucketSourceOption.metagenerationNotMatch(bucketInfo.getMetageneration());
            default:
                throw new AssertionError((Object)"Unexpected enum value");
        }
    }
    
    private Storage.BucketGetOption toGetOption(final BucketInfo bucketInfo) {
        switch (this.getRpcOption()) {
            case IF_METAGENERATION_MATCH:
                return Storage.BucketGetOption.metagenerationMatch(bucketInfo.getMetageneration());
            case IF_METAGENERATION_NOT_MATCH:
                return Storage.BucketGetOption.metagenerationNotMatch(bucketInfo.getMetageneration());
            default:
                throw new AssertionError((Object)"Unexpected enum value");
        }
    }
    
    public static BucketSourceOption metagenerationMatch() {
        return new BucketSourceOption(StorageRpc.Option.IF_METAGENERATION_MATCH);
    }
    
    public static BucketSourceOption metagenerationNotMatch() {
        return new BucketSourceOption(StorageRpc.Option.IF_METAGENERATION_NOT_MATCH);
    }
    
    public static BucketSourceOption userProject(final String s) {
        return new BucketSourceOption(StorageRpc.Option.USER_PROJECT, s);
    }
    
    static Storage.BucketSourceOption[] toSourceOptions(final BucketInfo bucketInfo, final BucketSourceOption... array) {
        final Storage.BucketSourceOption[] array2 = new Storage.BucketSourceOption[array.length];
        int n = 0;
        for (int length = array.length, i = 0; i < length; ++i) {
            array2[n++] = array[i].toSourceOption(bucketInfo);
        }
        return array2;
    }
    
    static Storage.BucketGetOption[] toGetOptions(final BucketInfo bucketInfo, final BucketSourceOption... array) {
        final Storage.BucketGetOption[] array2 = new Storage.BucketGetOption[array.length];
        int n = 0;
        for (int length = array.length, i = 0; i < length; ++i) {
            array2[n++] = array[i].toGetOption(bucketInfo);
        }
        return array2;
    }
}
