package com.google.cloud.storage;

import java.util.List;
import com.google.cloud.FieldSelector;
import com.google.cloud.storage.spi.v1.StorageRpc;

public static class BucketGetOption extends Option
{
    private static final long serialVersionUID = 1901844869484087395L;
    
    private BucketGetOption(final StorageRpc.Option option, final long n) {
        super(option, n);
    }
    
    private BucketGetOption(final StorageRpc.Option option, final String s) {
        super(option, s);
    }
    
    public static BucketGetOption metagenerationMatch(final long n) {
        return new BucketGetOption(StorageRpc.Option.IF_METAGENERATION_MATCH, n);
    }
    
    public static BucketGetOption metagenerationNotMatch(final long n) {
        return new BucketGetOption(StorageRpc.Option.IF_METAGENERATION_NOT_MATCH, n);
    }
    
    public static BucketGetOption userProject(final String s) {
        return new BucketGetOption(StorageRpc.Option.USER_PROJECT, s);
    }
    
    public static BucketGetOption fields(final BucketField... array) {
        return new BucketGetOption(StorageRpc.Option.FIELDS, FieldSelector.Helper.selector((List)BucketField.REQUIRED_FIELDS, (FieldSelector[])array));
    }
}
