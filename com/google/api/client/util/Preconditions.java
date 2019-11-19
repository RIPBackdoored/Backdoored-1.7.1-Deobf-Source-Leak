package com.google.api.client.util;

public final class Preconditions
{
    public static void checkArgument(final boolean b) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkArgument(b);
    }
    
    public static void checkArgument(final boolean b, final Object o) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkArgument(b, o);
    }
    
    public static void checkArgument(final boolean b, final String s, final Object... array) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkArgument(b, s, array);
    }
    
    public static void checkState(final boolean b) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkState(b);
    }
    
    public static void checkState(final boolean b, final Object o) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkState(b, o);
    }
    
    public static void checkState(final boolean b, final String s, final Object... array) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkState(b, s, array);
    }
    
    public static <T> T checkNotNull(final T t) {
        return com.google.api.client.repackaged.com.google.common.base.Preconditions.<T>checkNotNull(t);
    }
    
    public static <T> T checkNotNull(final T t, final Object o) {
        return com.google.api.client.repackaged.com.google.common.base.Preconditions.<T>checkNotNull(t, o);
    }
    
    public static <T> T checkNotNull(final T t, final String s, final Object... array) {
        return com.google.api.client.repackaged.com.google.common.base.Preconditions.<T>checkNotNull(t, s, array);
    }
    
    private Preconditions() {
        super();
    }
}
