package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;

public static class GetHmacKeyOption extends Option
{
    private GetHmacKeyOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    public static GetHmacKeyOption userProject(final String s) {
        return new GetHmacKeyOption(StorageRpc.Option.USER_PROJECT, s);
    }
    
    public static GetHmacKeyOption projectId(final String s) {
        return new GetHmacKeyOption(StorageRpc.Option.PROJECT_ID, s);
    }
}
