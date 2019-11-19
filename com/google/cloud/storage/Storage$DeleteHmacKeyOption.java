package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;

public static class DeleteHmacKeyOption extends Option
{
    private DeleteHmacKeyOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    public static DeleteHmacKeyOption userProject(final String s) {
        return new DeleteHmacKeyOption(StorageRpc.Option.USER_PROJECT, s);
    }
}
