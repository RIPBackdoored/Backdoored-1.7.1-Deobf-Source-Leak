package com.google.api.client.util;

public final class Joiner
{
    private final com.google.api.client.repackaged.com.google.common.base.Joiner wrapped;
    
    public static Joiner on(final char c) {
        return new Joiner(com.google.api.client.repackaged.com.google.common.base.Joiner.on(c));
    }
    
    private Joiner(final com.google.api.client.repackaged.com.google.common.base.Joiner wrapped) {
        super();
        this.wrapped = wrapped;
    }
    
    public final String join(final Iterable<?> iterable) {
        return this.wrapped.join(iterable);
    }
}
