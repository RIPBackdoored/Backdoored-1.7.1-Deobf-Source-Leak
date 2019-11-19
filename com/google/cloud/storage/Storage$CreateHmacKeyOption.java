package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;

public static class CreateHmacKeyOption extends Option
{
    private CreateHmacKeyOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    public static CreateHmacKeyOption userProject(final String s) {
        return new CreateHmacKeyOption(StorageRpc.Option.USER_PROJECT, s);
    }
    
    public static CreateHmacKeyOption projectId(final String s) {
        return new CreateHmacKeyOption(StorageRpc.Option.PROJECT_ID, s);
    }
}
