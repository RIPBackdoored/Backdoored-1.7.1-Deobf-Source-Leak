package com.google.api.client.util;

public final class Throwables
{
    public static RuntimeException propagate(final Throwable t) {
        return com.google.api.client.repackaged.com.google.common.base.Throwables.propagate(t);
    }
    
    public static void propagateIfPossible(final Throwable t) {
        if (t != null) {
            com.google.api.client.repackaged.com.google.common.base.Throwables.throwIfUnchecked(t);
        }
    }
    
    public static <X extends Throwable> void propagateIfPossible(final Throwable t, final Class<X> clazz) throws X, Throwable {
        com.google.api.client.repackaged.com.google.common.base.Throwables.<X>propagateIfPossible(t, clazz);
    }
    
    private Throwables() {
        super();
    }
}
