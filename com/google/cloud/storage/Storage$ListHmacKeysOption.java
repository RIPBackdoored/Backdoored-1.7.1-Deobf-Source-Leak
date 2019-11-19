package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;

public static class ListHmacKeysOption extends Option
{
    private ListHmacKeysOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    public static ListHmacKeysOption serviceAccount(final ServiceAccount serviceAccount) {
        return new ListHmacKeysOption(StorageRpc.Option.SERVICE_ACCOUNT_EMAIL, serviceAccount.getEmail());
    }
    
    public static ListHmacKeysOption maxResults(final long n) {
        return new ListHmacKeysOption(StorageRpc.Option.MAX_RESULTS, n);
    }
    
    public static ListHmacKeysOption pageToken(final String s) {
        return new ListHmacKeysOption(StorageRpc.Option.PAGE_TOKEN, s);
    }
    
    public static ListHmacKeysOption showDeletedKeys(final boolean b) {
        return new ListHmacKeysOption(StorageRpc.Option.SHOW_DELETED_KEYS, b);
    }
    
    public static ListHmacKeysOption userProject(final String s) {
        return new ListHmacKeysOption(StorageRpc.Option.USER_PROJECT, s);
    }
    
    public static ListHmacKeysOption projectId(final String s) {
        return new ListHmacKeysOption(StorageRpc.Option.PROJECT_ID, s);
    }
}
