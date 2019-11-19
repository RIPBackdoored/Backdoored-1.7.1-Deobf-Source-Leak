package com.google.api.client.util;

import java.util.Collection;

public final class Collections2
{
    static <T> Collection<T> cast(final Iterable<T> iterable) {
        return (Collection<T>)iterable;
    }
    
    private Collections2() {
        super();
    }
}
