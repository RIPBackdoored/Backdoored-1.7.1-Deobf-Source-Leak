package com.google.cloud.storage;

import java.util.List;
import com.google.cloud.FieldSelector;
import com.google.cloud.storage.spi.v1.StorageRpc;

public static class BucketListOption extends Option
{
    private static final long serialVersionUID = 8754017079673290353L;
    
    private BucketListOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    public static BucketListOption pageSize(final long n) {
        return new BucketListOption(StorageRpc.Option.MAX_RESULTS, n);
    }
    
    public static BucketListOption pageToken(final String s) {
        return new BucketListOption(StorageRpc.Option.PAGE_TOKEN, s);
    }
    
    public static BucketListOption prefix(final String s) {
        return new BucketListOption(StorageRpc.Option.PREFIX, s);
    }
    
    public static BucketListOption userProject(final String s) {
        return new BucketListOption(StorageRpc.Option.USER_PROJECT, s);
    }
    
    public static BucketListOption fields(final BucketField... array) {
        return new BucketListOption(StorageRpc.Option.FIELDS, FieldSelector.Helper.listSelector("items", (List)BucketField.REQUIRED_FIELDS, (FieldSelector[])array));
    }
}
