package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;

public static class BucketSourceOption extends Option
{
    private static final long serialVersionUID = 5185657617120212117L;
    
    private BucketSourceOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    public static BucketSourceOption metagenerationMatch(final long n) {
        return new BucketSourceOption(StorageRpc.Option.IF_METAGENERATION_MATCH, n);
    }
    
    public static BucketSourceOption metagenerationNotMatch(final long n) {
        return new BucketSourceOption(StorageRpc.Option.IF_METAGENERATION_NOT_MATCH, n);
    }
    
    public static BucketSourceOption userProject(final String s) {
        return new BucketSourceOption(StorageRpc.Option.USER_PROJECT, s);
    }
}
