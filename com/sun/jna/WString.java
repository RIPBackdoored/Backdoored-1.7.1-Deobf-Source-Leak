package com.sun.jna;

public final class WString implements CharSequence, Comparable
{
    private String string;
    
    public WString(final String string) {
        super();
        if (string == null) {
            throw new NullPointerException("String initializer must be non-null");
        }
        this.string = string;
    }
    
    @Override
    public String toString() {
        return this.string;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof WString && this.toString().equals(o.toString());
    }
    
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    @Override
    public int compareTo(final Object o) {
        return this.toString().compareTo(o.toString());
    }
    
    @Override
    public int length() {
        return this.toString().length();
    }
    
    @Override
    public char charAt(final int n) {
        return this.toString().charAt(n);
    }
    
    @Override
    public CharSequence subSequence(final int n, final int n2) {
        return this.toString().subSequence(n, n2);
    }
}
