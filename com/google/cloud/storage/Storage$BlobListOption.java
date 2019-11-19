package com.google.cloud.storage;

import java.util.List;
import com.google.cloud.FieldSelector;
import com.google.cloud.storage.spi.v1.StorageRpc;

public static class BlobListOption extends Option
{
    private static final String[] TOP_LEVEL_FIELDS;
    private static final long serialVersionUID = 9083383524788661294L;
    
    private BlobListOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    public static BlobListOption pageSize(final long n) {
        return new BlobListOption(StorageRpc.Option.MAX_RESULTS, n);
    }
    
    public static BlobListOption pageToken(final String s) {
        return new BlobListOption(StorageRpc.Option.PAGE_TOKEN, s);
    }
    
    public static BlobListOption prefix(final String s) {
        return new BlobListOption(StorageRpc.Option.PREFIX, s);
    }
    
    public static BlobListOption currentDirectory() {
        return new BlobListOption(StorageRpc.Option.DELIMITER, true);
    }
    
    public static BlobListOption userProject(final String s) {
        return new BlobListOption(StorageRpc.Option.USER_PROJECT, s);
    }
    
    public static BlobListOption versions(final boolean b) {
        return new BlobListOption(StorageRpc.Option.VERSIONS, b);
    }
    
    public static BlobListOption fields(final BlobField... array) {
        return new BlobListOption(StorageRpc.Option.FIELDS, FieldSelector.Helper.listSelector(BlobListOption.TOP_LEVEL_FIELDS, "items", (List)BlobField.REQUIRED_FIELDS, (FieldSelector[])array, new String[0]));
    }
    
    static {
        TOP_LEVEL_FIELDS = new String[] { "prefixes" };
    }
}
