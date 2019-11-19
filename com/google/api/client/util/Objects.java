package com.google.api.client.util;

public final class Objects
{
    public static boolean equal(final Object o, final Object o2) {
        return com.google.api.client.repackaged.com.google.common.base.Objects.equal(o, o2);
    }
    
    public static ToStringHelper toStringHelper(final Object o) {
        return new ToStringHelper(o.getClass().getSimpleName());
    }
    
    private Objects() {
        super();
    }
}
