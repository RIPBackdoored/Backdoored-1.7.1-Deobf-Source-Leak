package com.google.cloud.storage;

import java.net.URISyntaxException;
import java.net.URI;
import com.google.common.base.Preconditions;
import java.io.Serializable;

public static final class Origin implements Serializable
{
    private static final long serialVersionUID = -4447958124895577993L;
    private static final String ANY_URI = "*";
    private final String value;
    private static final Origin ANY;
    
    private Origin(final String reference) {
        super();
        this.value = Preconditions.<String>checkNotNull(reference);
    }
    
    public static Origin any() {
        return Origin.ANY;
    }
    
    public static Origin of(final String s, final String s2, final int n) {
        try {
            return of(new URI(s, null, s2, n, null, null, null).toString());
        }
        catch (URISyntaxException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
    
    public static Origin of(final String s) {
        if ("*".equals(s)) {
            return any();
        }
        return new Origin(s);
    }
    
    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof Origin && this.value.equals(((Origin)o).value);
    }
    
    @Override
    public String toString() {
        return this.getValue();
    }
    
    public String getValue() {
        return this.value;
    }
    
    static {
        ANY = new Origin("*");
    }
}
