package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;

public static class UpdateHmacKeyOption extends Option
{
    private UpdateHmacKeyOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    public static UpdateHmacKeyOption userProject(final String s) {
        return new UpdateHmacKeyOption(StorageRpc.Option.USER_PROJECT, s);
    }
}
